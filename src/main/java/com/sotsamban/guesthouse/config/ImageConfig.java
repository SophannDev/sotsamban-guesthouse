package com.sotsamban.guesthouse.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ImageConfig {

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Converter(autoApply = true) // This will auto-apply to all List<String> fields
    public static class StringListConverter implements AttributeConverter<List<String>, String> {

        private static final ObjectMapper mapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<String> stringList) {
            if (stringList == null || stringList.isEmpty()) {
                return "[]"; // Empty JSON array
            }
            try {
                return mapper.writeValueAsString(stringList);
            } catch (JsonProcessingException e) {
                return "[]";
            }
        }

        @Override
        public List<String> convertToEntityAttribute(String dbData) {
            if (dbData == null || dbData.trim().isEmpty()) {
                return new ArrayList<>();
            }
            try {
                return mapper.readValue(dbData, new TypeReference<List<String>>() {});
            } catch (JsonProcessingException e) {
                return new ArrayList<>();
            }
        }
    }
}
