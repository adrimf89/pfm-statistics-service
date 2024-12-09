package com.adri.pfm.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PfmStatisticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfmStatisticsServiceApplication.class, args);
	}

}
