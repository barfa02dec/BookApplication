package com.app;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserURL {
	
	@Bean
	public String getUserURL() {
		return "http://User/";
	}
	
}