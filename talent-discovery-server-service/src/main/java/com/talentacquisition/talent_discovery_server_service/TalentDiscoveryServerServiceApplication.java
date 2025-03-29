package com.talentacquisition.talent_discovery_server_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TalentDiscoveryServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentDiscoveryServerServiceApplication.class, args);
	}

}
