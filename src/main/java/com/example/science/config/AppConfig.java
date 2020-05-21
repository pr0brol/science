package com.example.science.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan(basePackages = {"com.example.science"})
public class AppConfig implements WebMvcConfigurer {

}
