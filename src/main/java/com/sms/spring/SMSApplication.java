package com.sms.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class SMSApplication {
	   @Bean
	    public Java8TimeDialect java8TimeDialect() {
	        return new Java8TimeDialect();
	    }
	   
	   @Bean
	    public SpringSecurityDialect springSecurityDialect() {
	        return new SpringSecurityDialect();
	    }
	   
    public static void main(String[] args) {    	                   
        SpringApplication.run(SMSApplication.class, args);
    }
}
