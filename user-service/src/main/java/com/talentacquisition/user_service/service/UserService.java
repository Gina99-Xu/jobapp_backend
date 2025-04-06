package com.talentacquisition.user_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.talentacquisition.user_service.client.JobFeignClient;
import com.talentacquisition.user_service.dto.JobPostQueryResponseDTO;
import com.talentacquisition.user_service.dto.UserDTO;
import com.talentacquisition.user_service.dto.UserResumeAnalysisDTO;
import com.talentacquisition.user_service.utilities.OllamaRequest;
import com.talentacquisition.user_service.dto.UserResumeRequestDTO;
import com.talentacquisition.user_service.entity.UserEntity;
import com.talentacquisition.user_service.entity.UserResumeAnalysisEntity;
import com.talentacquisition.user_service.repository.UserRepository;
import com.talentacquisition.user_service.repository.UserResumeAnalysisEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserResumeAnalysisEntityRepository userResumeAnalysisEntityRepository;

	@Autowired
	private JobFeignClient jobFeignClient;

	private final ModelMapper modelMapper = new ModelMapper();

	private final WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:11434")
			.build();

	public UserDTO findUserResumeByEmail(String userEmail) {
		UserEntity userEntity = userRepository.findUserByUserEmail(userEmail);
		return convertToDTO(userEntity);
	}

	public UserDTO saveUserAndResumeAnalysis(String userDataJson, MultipartFile resumeFile) throws IOException {

		UserResumeRequestDTO userResumeRequestData = new ObjectMapper().readValue(userDataJson, UserResumeRequestDTO.class);
		UserEntity userEntity;
		String userEmail = userResumeRequestData.getUserEmail();

		if(userRepository.findUserByUserEmail(userEmail) != null) {
			userEntity = userRepository.findUserByUserEmail(userEmail);
		}else{
			userEntity= new UserEntity();
			userEntity.setUserEmail(userResumeRequestData.getUserEmail());
			userEntity.setFirstName(userResumeRequestData.getFirstName());
			userEntity.setLastName(userResumeRequestData.getLastName());
			userEntity.setMobileNumber(userResumeRequestData.getMobileNumber());

		}
		userEntity = userRepository.save(userEntity);
		String jobPostId = userResumeRequestData.getJobPostId();
		UserResumeAnalysisEntity savedUserResumeAnalysisEntity = saveUserResumeAnalysisEntity(userEntity, resumeFile, jobPostId);

		if(userEntity.getUserJobAppliedEntityList() == null) {
			userEntity.setUserJobAppliedEntityList(new ArrayList<>());
		}

		userEntity.getUserJobAppliedEntityList().add(savedUserResumeAnalysisEntity);
		userEntity = userRepository.save(userEntity);

		return convertToDTO(userEntity);
	}

	private UserDTO convertToDTO(UserEntity userEntity) {
		UserDTO userDTO =  modelMapper.map(userEntity, UserDTO.class);

		if(userEntity.getUserJobAppliedEntityList() != null){
			List<UserResumeAnalysisDTO> analysisDTOList =
					userEntity.getUserJobAppliedEntityList().stream()
							.map(this::convertToResumeAnalysisDTO)
							.collect(Collectors.toList());
			userDTO.setUserJobAppliedList(analysisDTOList);
		}

		return userDTO;
	}

	private UserResumeAnalysisDTO convertToResumeAnalysisDTO(UserResumeAnalysisEntity userResumeAnalysisEntity) {
		return modelMapper.map(userResumeAnalysisEntity, UserResumeAnalysisDTO.class);
	}

	private UserResumeAnalysisEntity saveUserResumeAnalysisEntity (UserEntity userEntity, MultipartFile resumeFile, String jobPostId) throws IOException {
		//1. Extract text for analysis
		String pdfContent = extractTextFromPdf(resumeFile);

		//2. Get job details
		JobPostQueryResponseDTO jobPost = jobFeignClient.findJobPostById(jobPostId).getBody();


		//3. Get analysis result
		String jsonResult = analyzeResumeContent(pdfContent, jobPost);
		String cleanJson = extractStrictJson(jsonResult);
		validatAnalysisReponse(cleanJson);

		//4. Create and populate entity
		UserResumeAnalysisEntity userResumeAnalysisEntity = new ObjectMapper()
				.readValue(cleanJson, UserResumeAnalysisEntity.class);
		userResumeAnalysisEntity.setUserEntity(userEntity);
		userResumeAnalysisEntity.setJobPostId(jobPostId);
		userResumeAnalysisEntity.setResumePdf(resumeFile.getBytes());
		userResumeAnalysisEntity.setResumeFileName(resumeFile.getOriginalFilename());

		//5. Save entity
		return userResumeAnalysisEntityRepository.save(userResumeAnalysisEntity);
	}

	private String extractStrictJson(String llmResponse) throws JsonProcessingException {
		// Find the first { and last } to extract pure JSON
		int start = llmResponse.indexOf('{');
		int end = llmResponse.lastIndexOf('}') + 1;

		if (start == -1 || end == -1) {
			throw new IllegalArgumentException("No JSON found in response");
		}

		String json = llmResponse.substring(start, end);

		// Parse and rebuild with only allowed fields
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);

		ObjectNode filteredJson = mapper.createObjectNode();
		filteredJson.put("experienceScore", node.path("experienceScore").asDouble());
		filteredJson.put("skillsScore", node.path("skillsScore").asDouble());
		filteredJson.put("educationScore", node.path("educationScore").asDouble());
		filteredJson.put("overallScore", node.path("overallScore").asDouble());
		filteredJson.put("overallAnalysis", node.path("overallAnalysis").asText());

		return mapper.writeValueAsString(filteredJson);
	}

	private void validatAnalysisReponse(String JsonResponse) {
		try{
			JsonNode node = new ObjectMapper().readTree(JsonResponse);
			if(!node.has("experienceScore") || !node.has("skillsScore")
					|| !node.has("educationScore")
					|| !node.has("overallScore")
			|| (!node.get("experienceScore").isDouble() || !node.get("skillsScore").isDouble()
					|| !node.get("educationScore").isDouble() || !node.get("overallScore").isDouble())
					|| !node.get("overallAnalysis").isTextual() || !node.has("overallAnalysis")
			) {
				throw new RuntimeException("Invalid analysis response");
			}
		}catch(IOException e){
			throw new RuntimeException("Failed to convert jobpost to json", e);
		}
	}

	private String extractTextFromPdf(MultipartFile file) throws IOException {
		try (PDDocument document = PDDocument.load(file.getInputStream())) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);
		}
	}

	private String analyzeResumeContent(String pdfContent, JobPostQueryResponseDTO jobPost) {

		String jobPostJson;
		try {
			jobPostJson = new ObjectMapper().writeValueAsString(jobPost)
					.replace("\"", "\\\"")  // Escape quotes
					.replace("\n", " ");     // Remove newlines

			String prompt =
					"You are a JSON output generator. " +
							"Analyze this resume against the job post and provide ONLY the following JSON structure with no additional text or explanation:\n\n" +
					"{\n" +
					"  \"experienceScore\": [0-100],\n" +
					"  \"skillsScore\": [0-100],\n" +
					"  \"educationScore\": [0-100],\n" +
					"  \"overallScore\": [0-100],\n" +
					"  \"overallAnalysis\": \"Concise professional summary\"\n" +
					"}\n\n" +
					"Important: Your response must begin with { and end with }. Do not include any text outside these brackets.\n\n" +
					"### Job Post ###\n" + jobPostJson + "\n\n" +
					"### Resume ###\n" + pdfContent;

			return webClient.post()
					.uri("/api/generate")
					.bodyValue(new OllamaRequest("neural-chat:latest", prompt))
					.retrieve()
					.bodyToFlux(String.class)
					.reduce(new StringBuilder(), (sb, chunk) -> {
						// Extract the "response" field from each chunk
						try {
							JsonNode node = new ObjectMapper().readTree(chunk);
							sb.append(node.path("response").asText());
						} catch (Exception e) {
							log.error("Error parsing chunk: {}", chunk, e);
						}
						return sb;
					})
					.map(StringBuilder::toString)
					.block();

		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to convert jobpost to json", e);
		}
	}
}
