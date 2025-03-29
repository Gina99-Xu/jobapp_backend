package com.talentacquisition.openai_pdf_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfController {

	private final WebClient webClient;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public PdfController(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
	}

	@PostMapping("/analyze-pdf")
	public Mono<String> analyzePdf(@RequestParam("file") MultipartFile file) throws IOException {
		String pdfContent = extractTextFromPdf(file);
		String prompt = "Analyze this resume and provide a match percentage score based on the following criteria in one sentence for each aspect: " +
				"1. Relevant work experience, 2. Technical skills, 3. Education. Resume content:\n" + pdfContent;
		return callOllamaApi(prompt);
	}

	private String extractTextFromPdf(MultipartFile file) throws IOException {
		try (PDDocument document = PDDocument.load(file.getInputStream())) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);
		}
	}

	private Mono<String> callOllamaApi(String prompt) {
		OllamaRequest request = new OllamaRequest("llama2:7b", prompt);
		return webClient.post()
				.uri("/api/generate")
				.bodyValue(request)
				.retrieve()
				.bodyToFlux(String.class)
				.reduce(new StringBuilder(), (sb, response) -> {
					try {
						JsonNode jsonNode = objectMapper.readTree(response);
						String chunk = jsonNode.get("response").asText();
						sb.append(chunk);
					} catch (IOException e) {
						throw new RuntimeException("Failed to parse Ollama response", e);
					}
					return sb;
				})
				.map(StringBuilder::toString);
	}

	private static class OllamaRequest {
		private String model;
		private String prompt;

		public OllamaRequest(String model, String prompt) {
			this.model = model;
			this.prompt = prompt;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getPrompt() {
			return prompt;
		}

		public void setPrompt(String prompt) {
			this.prompt = prompt;
		}
	}
}