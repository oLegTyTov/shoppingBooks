package com.example.shoppingbooks.configs;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.shoppingbooks.convertors.StringToCategoryConvertor;

import com.example.shoppingbooks.convertors.StringsToCategoriesConverter;

@Configuration
public class ConverterWebConfig implements WebMvcConfigurer {
    private final StringsToCategoriesConverter stringsToCategoriesConverter;
    private final StringToCategoryConvertor stringToCategoryConvertor;
    public ConverterWebConfig(StringToCategoryConvertor stringToCategoryConvertor,StringsToCategoriesConverter stringsToCategoriesConverter) {
        this.stringsToCategoriesConverter=stringsToCategoriesConverter;
        this.stringToCategoryConvertor=stringToCategoryConvertor;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringsToCategoriesConverter);
        registry.addConverter(stringToCategoryConvertor);
    }
}
