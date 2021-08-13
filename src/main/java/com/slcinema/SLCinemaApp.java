package com.slcinema;

import com.slcinema.models.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SLCinemaApp {
	public static void main(String[] args) {
		SpringApplication.run(SLCinemaApp.class, args);
	}
}
