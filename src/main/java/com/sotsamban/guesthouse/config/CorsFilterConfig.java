package com.sotsamban.guesthouse.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class CorsFilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfigurationSource source = request -> {
            String origin = request.getHeader(HttpHeaders.ORIGIN);

            if (!StringUtils.hasText(origin)) {
                // Not a CORS request
                return null;
            }

            // Create CORS configuration
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.addAllowedOrigin(origin);

            String accessControlRequestHeaders = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            if (StringUtils.hasText(accessControlRequestHeaders)) {
                Stream.of(accessControlRequestHeaders.split(","))
                        .map(String::trim)
                        .distinct()
                        .forEach(configuration::addAllowedHeader);
            }

            configuration.addExposedHeader("*");
            configuration.setAllowCredentials(true);
            configuration.setAllowedMethods(List.of(
                    "GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE"
            ));

            return configuration;
        };

        FilterRegistrationBean<CorsFilter> bean =
                new FilterRegistrationBean<>(new CorsFilter(source));
        bean.addUrlPatterns("/*");
        bean.setOrder(Integer.MIN_VALUE); // Ensure it runs first
        return bean;
    }
}
