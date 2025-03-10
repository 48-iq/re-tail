package dev.ilya_anna.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Component
public class CorsConfig implements WebMvcConfigurer{
    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${app.cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${app.cors.allowed-methods}")
    private String allowedMethods;

    @Value("${app.cors.allow-credentials}")
    private Boolean allowCredentials;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Cors origins: {}", allowedOrigins);
        log.info("Cors methods: {}", allowedMethods);
        log.info("Cors headers: {}", allowedHeaders);
        log.info("Cors credentials: {}", allowCredentials);
        registry.addMapping("/**")
                .allowCredentials(allowCredentials)
                .allowedOrigins(allowedOrigins)
                .allowedHeaders(allowedHeaders)
                .allowedMethods(allowedMethods)
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }
}
