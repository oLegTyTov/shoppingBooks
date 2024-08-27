package com.example.shoppingbooks.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.shoppingbooks.entities.Category;
@Component
public class StringToCategoryConvertor implements Converter<String,Category>{

    @Override
    public Category convert(String source) {
        Category category=new Category();
        category.setName(source);
        return category;
    }

}
