package com.talentacquisition.career_portal_service;

import com.talentacquisition.talent_core_api.configuration.AxonStreamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(AxonStreamConfig.class)
public class CareerPortalServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CareerPortalServiceApplication.class, args);
    }


}
