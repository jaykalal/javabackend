package com.jayk.amazonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class AmazonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonApiApplication.class, args);
	}

}
