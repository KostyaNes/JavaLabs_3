package com.javalabs.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties(prefix = "gateway")
public class ConfigClientAppConfiguration {

    @Value("${gateway.property1}")
    private String prop1;

    @Value("${gateway.property2}")
    private String prop2;

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp4(String prop2) {
        this.prop2 = prop2;
    }
}
