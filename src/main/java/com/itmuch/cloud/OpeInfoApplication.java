package com.itmuch.cloud;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
@SpringBootApplication
public class OpeInfoApplication  extends SpringBootServletInitializer {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(OpeInfoApplication.class, args);
	}
	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        //Demo1Application这个类是SpringApplication.run启动类
	        return application.sources(OpeInfoApplication.class);
	    }

	  @Bean  
	    public MultipartConfigElement multipartConfigElement() {  
	        MultipartConfigFactory factory = new MultipartConfigFactory();  
	        //文件最大  
	        factory.setMaxFileSize("1024000KB"); //KB,MB  
	        /// 设置总上传数据总大小  
	        factory.setMaxRequestSize("10240000KB");  
	        return factory.createMultipartConfig();  
	    }  
}
