package com.viettel.demo.common.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "success")
@Configuration
@PropertySource(value="classpath:messages.properties")
@Getter
@Setter
public class SuccessMessage {
    private String delete;
    private String add;
    private String edit;
    private String view;
}
