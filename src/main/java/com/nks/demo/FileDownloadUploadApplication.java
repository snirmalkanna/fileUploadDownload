package com.nks.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.nks"})
@EntityScan("com.nks.entity")
@EnableJpaRepositories("com.nks.repository")

public class FileDownloadUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDownloadUploadApplication.class, args);
	}

}
