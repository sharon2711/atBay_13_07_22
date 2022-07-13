package com.atBay.assignment;

import com.atBay.assignment.config.redis.RedisDetailsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties(value = { RedisDetailsConfig.class })
public class AtBayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtBayApplication.class, args);
	}

}
