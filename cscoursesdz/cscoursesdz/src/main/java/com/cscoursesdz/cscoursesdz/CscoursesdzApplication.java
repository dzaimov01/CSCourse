package com.cscoursesdz.cscoursesdz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CscoursesdzApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscoursesdzApplication.class, args);
		log.info("Application started...");
	}

}
