package com.talentacquisition.talent_fulfillment_service;

import com.talentacquisition.talent_core_api.configuration.AxonStreamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AxonStreamConfig.class)
public class TalentFulfillmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentFulfillmentServiceApplication.class, args);
    }

}
