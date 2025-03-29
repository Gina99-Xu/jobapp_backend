package com.talentacquisition.talent_request_service;

import com.talentacquisition.talent_core_api.configuration.AxonStreamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AxonStreamConfig.class)
public class TalentRequestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentRequestServiceApplication.class, args);
    }

}
