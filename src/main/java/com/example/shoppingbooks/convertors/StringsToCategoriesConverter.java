package com.example.shoppingbooks.convertors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.*;
import com.example.shoppingbooks.entities.Category;
@Component
public class StringsToCategoriesConverter implements Converter<String[],Set<Category>> {

@Override
    public Set<Category> convert(String[] source) {
        // Створюємо Set об'єктів Category
        Set<Category> categories = new HashSet<>();
        
        // Перетворюємо кожен рядок у об'єкт Category і додаємо до Set
        for (String categoryName : source) {
            Category category = new Category();
            category.setName(categoryName);
            categories.add(category);
        }
        
        return categories;
    }

}
