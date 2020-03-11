package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookURL {	
	@Bean
	public String getBookURL() {
		return "http://Book/";
	}
}