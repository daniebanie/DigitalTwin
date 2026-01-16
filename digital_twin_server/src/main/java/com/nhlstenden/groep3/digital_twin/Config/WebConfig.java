package com.nhlstenden.groep3.digital_twin.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // COMMENT: Map /uploads/** URLs to the container filesystem
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/uploads/");
    }
}
