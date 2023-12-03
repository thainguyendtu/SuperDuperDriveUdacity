package com.udacity.jwdnd.course1.superduperdriver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
@Configuration
public class FileUploadConfig {

    @Autowired
    FileUploadProperties fileUploadProperties;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse(fileUploadProperties.getMaxFileSize()));
        return factory.createMultipartConfig();
    }
}
