package com.sms.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Farooq 
 *
 */
@Configuration
@ComponentScan(basePackages = {
		"com.sms.spring" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
				"com.sms.spring.web.*" }))
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig {

	@Autowired
	ApplicationContextProvider applicationContextProvder;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
	
	@Bean
	public ApplicationContextProvider applicationContextProvder() {
		return new ApplicationContextProvider();
	}

}
