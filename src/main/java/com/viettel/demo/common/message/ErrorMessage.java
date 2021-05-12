package com.viettel.demo.common.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "error")
@Configuration
@PropertySource(value="classpath:messages.properties")
@Getter
@Setter
public class ErrorMessage {
    private String recordNotFound;
    private String unexpectedError;
    private String duplicateId;
    private String invalidInput;
}
