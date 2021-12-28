package com.stackroute.keepnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*This class will contain bean for viewresolver
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @ComponentScan - this annotation is used to search for the Spring components amongst the application
 * @EnableWebMvc - Adding this annotation to an @Configuration class imports the Spring MVC 
 * 				   configuration from WebMvcConfigurationSupport 
 * 
 * */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.stackroute.keepnote")
@EnableScheduling 
public class WebMvcConfig extends WebMvcConfigurerAdapter  implements WebMvcConfigurer {

	WebMvcConfig(){}
	/*
	 * Define the bean for view resolver so that it can be used to resolve the JSP
	 * files which are existing in /WEB-INF/views folder. A ViewResolver is capable
	 * of mapping logical view names to actual views, such as a JSP or a HTML page.
	 */
	
	 @Bean
	 public InternalResourceViewResolver viewresolver()
	 {
		 InternalResourceViewResolver isr=new InternalResourceViewResolver();
		 isr.setPrefix("/WEB-INF/views/");
		 isr.setSuffix(".jsp");
		 return isr;
	 }
	 
	 @Bean
	 public CommonsMultipartResolver multipartResolver() {
	     CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	     resolver.setDefaultEncoding("utf-8");
	     return resolver;
	 }
	 
	   @Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    }
	  
	  @Bean
	  public ReloadableResourceBundleMessageSource ReloadableResourceBundleMessageSource()
	  {
		  ReloadableResourceBundleMessageSource r=new ReloadableResourceBundleMessageSource();
		  r.setBasename("/WEB-INF/messages/");
		  
		  return r;
	  }
	  
	  

}
