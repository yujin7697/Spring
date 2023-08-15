package com.test.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.test.app.inerceptor.MemoInterceptor;
import com.test.app.listener.CustomContextRefreshedListener;
import com.test.app.listener.RequestHandledEventListener;



@Configuration
@EnableWebMvc
@ComponentScans({
	@ComponentScan("com.test.app.controller"),
	@ComponentScan("com.test.app.restcontroller")
})
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public MultipartResolver multipartResolver() {
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);			//20MB 전체 업로드 허용 사이즈
		multipartResolver.setMaxUploadSizePerFile(20971520);	//20MB 파일 1개당 허용가능한 업로드 사이즈
		multipartResolver.setMaxInMemorySize(20971520);			//20MB 캐시 공간
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
	}
	
//	ViewResolver
	@Bean
	public ViewResolver viewResolver(){	
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new MemoInterceptor())
		.addPathPatterns("/memo/**")
		.excludePathPatterns("/resources/**");
	}
	
//	Listener Added
	@Bean
	public CustomContextRefreshedListener customContextRefreshedListener() {
		return new CustomContextRefreshedListener();
	}
	
	@Bean
	public RequestHandledEventListener requestHandledEventListener() {
		return new RequestHandledEventListener();
	}
	
	
	
	
	
}
