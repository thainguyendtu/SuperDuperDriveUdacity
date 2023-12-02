package com.udacity.jwdnd.course1.superduperdriver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.servlet.multipart")
public class FileUploadProperties {

    @Setter
    @Getter
    private String maxFileSize;
}
