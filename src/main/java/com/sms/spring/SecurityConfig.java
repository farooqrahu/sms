package com.sms.spring;

import com.sms.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * @author Farooq, Hassan Mustafa Baig
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	@Autowired
	AccessDeniedExceptionResolver accessDeniedExceptionResolver;
	/*@Autowired
	DriverManagerDataSource dataSource;
	@Autowired
	private Environment env;*/

/*	@Bean
	public DriverManagerDataSource dataSource(){
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource; 
	}*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/resources/**").permitAll();

		http.csrf().disable().authorizeRequests()
				.antMatchers("/login", "/logout", "/appLogin", "/appCreateCustomer", "/appOrderRequest", "/appCityList",
						"/appProductNameList", "/appColorList", "/appCupList", "/appSizeList", "/appProductList",
						"/appProductTypeList", "/appProductCategoryList", "/appMaterialList",
						"/appMaterialCompositionList", "/appTransportList", "/appBrandList", "/appWarehouseList",
						"/appCustomerListBySalesRepCode", "/appPaymentMethodList", "/appOrderRequestList",
						"/appCheckProductQuantity", "/appGetOrderRequestUpdate")
				.permitAll()
				
				.and().authorizeRequests().antMatchers("/index**")
				.hasAnyRole("SMS_OWNER", "SALE_REP", "SALE_MANAGER", "DATA_ENTRY","WAREHOUSE_MANAGER")
				
				.and().authorizeRequests().antMatchers("/orderlist**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER")

				.and().authorizeRequests().antMatchers("/ordercreation**","/saveOrder")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER","SALE_REP")
				
				.and().authorizeRequests().antMatchers("/history**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","SALE_REP")	

				.and().authorizeRequests().antMatchers("/invoiceList**","/invoice**","/invoicegenerated**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","SALE_REP")	

				.and().authorizeRequests().antMatchers("/returnNoteList**","/returnNote**","/returnnotegenerated**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","SALE_REP")	

				.and().authorizeRequests().antMatchers("/addpro**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","DATA_ENTRY")	

				.and().authorizeRequests().antMatchers("/priceList**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","SALE_REP")	

				.and().authorizeRequests().antMatchers("/customerlist**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER","SALE_REP","DATA_ENTRY")	

				.and().authorizeRequests().antMatchers("/manageuser**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER", "WAREHOUSE_MANAGER","DATA_ENTRY")	
				
				.and().authorizeRequests().antMatchers("/accounts**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER")	

				.and().authorizeRequests().antMatchers("/reports**")
				.hasAnyRole("SMS_OWNER", "SALE_MANAGER")	

				
				
				
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").successHandler(customSuccessHandler).loginProcessingUrl("/login")
				.failureUrl("/login?error").permitAll().and().logout().invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").logoutUrl("/logout").and().exceptionHandling()
				.accessDeniedPage("/accessDenied");

		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired").maxSessionsPreventsLogin(true)
				.sessionRegistry(sessionRegistry()).and().invalidSessionUrl("/login?logout");

	}

	@Bean
	public AccessDeniedExceptionResolver accessDeniedExceptionResolver() {
		AccessDeniedExceptionResolver accessDeniedExceptionResolver = new AccessDeniedExceptionResolver();
		return accessDeniedExceptionResolver;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(encoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bootstrap/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/less/**");
		web.ignoring().antMatchers("/pages/**");
		web.ignoring().antMatchers("/plugins/**");
		web.ignoring().antMatchers("/images/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/fonts/**");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public IDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}