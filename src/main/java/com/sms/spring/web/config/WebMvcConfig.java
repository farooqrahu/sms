package com.sms.spring.web.config;

import java.io.IOException;
import java.util.Locale;

import com.sms.spring.SecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
@ComponentScan(basePackages = { "com.ifg.spring.web" })
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private int maxUploadSizeInMb = 3 * 1024 * 1024; // 3 MB

	@Value("${com.ifg.spring.path}")
	private String path;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/accessDenied").setViewName("/accessDenied");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();		
		mappings.put("ExceptionControllerAdvice", "error");
		mappings.put("java.lang.Exception", "error");
		b.setExceptionMappings(mappings);
		return b;
	}*/

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();

		// Set the maximum allowed size (in bytes) for each individual file.
		resolver.setMaxUploadSize(5242880);// 5MB

		// You may also set other available properties.

		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/static/bootstrap/3.1.0/");
		registry.addResourceHandler("/jquery/**").addResourceLocations("classpath:/static/jquery/");
		registry.addResourceHandler("/jqueryui/**").addResourceLocations("classpath:/static/jqueryui/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/datepicker/**").addResourceLocations("classpath:/static/datepicker/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/pages/**").addResourceLocations("classpath:/static/pages/");
		registry.addResourceHandler("/less/**").addResourceLocations("classpath:/static/less/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");

		/* linux */
		// registry.addResourceHandler("/foto/**").addResourceLocations("file:///home/saifi/Desktop/foto/");
		/* windows */
		registry.addResourceHandler("/tutorial_pdf/**").addResourceLocations("file://" + path);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

	@Bean
	public SecurityConfig securityConfig() {
		return new SecurityConfig();
	}

	/*
	 * @Bean public CommonsMultipartResolver multipartResolver(){
	 * CommonsMultipartResolver multipartResolver = new
	 * CommonsMultipartResolver();
	 * multipartResolver.setMaxUploadSize(maxUploadSizeInMb); return
	 * multipartResolver; }
	 */

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		return multipartResolver;
	}

	/*
	 * @Override public void
	 * addArgumentResolvers(List<HandlerMethodArgumentResolver>
	 * argumentResolvers) { PageableHandlerMethodArgumentResolver resolver = new
	 * PageableHandlerMethodArgumentResolver(); resolver.setFallbackPageable(new
	 * PageRequest(1, 5) ); argumentResolvers.add(new
	 * ServletWebArgumentResolverAdapter((WebArgumentResolver) resolver)); }
	 */

	/*
	 * @Override public void
	 * addArgumentResolvers(List<HandlerMethodArgumentResolver>
	 * argumentResolvers) { PageableArgumentResolver resolver = new
	 * PageableArgumentResolver(); resolver.setFallbackPagable(new
	 * PageRequest(1, 5)); argumentResolvers.add(new
	 * ServletWebArgumentResolverAdapter(resolver)); }
	 */

	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/jasperreports/");
		resolver.setSuffix(".jasper");
		// resolver.setReportDataKey("datasource");
		resolver.setViewNames("rpt_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(0);

		return resolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// messageSource.setBasename("/i18n/ifgmsg");
		messageSource.setBasename("classpath:i18n/ifgmsg");
		messageSource.setDefaultEncoding("UTF-8");
		/*
		 * ResourceBundleMessageSource result = new
		 * ResourceBundleMessageSource();
		 * 
		 * String[] basenames = { "classpath:/i18n.ifgmsg", "classpath:messages"
		 * }; result.setDefaultEncoding("UTF-8");
		 * result.setBasenames(basenames);
		 */
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

				container.addErrorPages(error404Page, error500Page);
			}
		};
	}
	
	
	/*@Bean
	public TemplateResolver templateResolver(){
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setAdditionalDialects(securityDialect());
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
		
	@Bean
	public Set<IDialect> securityDialect(){	
		IDialect dialect = new SpringSecurityDialect();
		Set<IDialect> dialects = new HashSet();
		dialects.add(dialect);
		return dialects;
	}*/
	
}
