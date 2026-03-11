package com.sthi.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            connector.setProperty("maxParameterCount", "10000");
            connector.setProperty("maxPostSize", "209715200"); // 200MB
            
            // Access the protocol handler to set maxFileCount
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>) {
                AbstractHttp11Protocol<?> protocol = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
                protocol.setMaxSavePostSize(209715200); // 200MB
            }
            
            // Try setting maxFileCount directly - this works in Tomcat 10.1+
            try {
                connector.setProperty("maxFileCount", "10000");
            } catch (Exception e) {
                System.err.println("Warning: Could not set maxFileCount property: " + e.getMessage());
            }
        });
    }
}
