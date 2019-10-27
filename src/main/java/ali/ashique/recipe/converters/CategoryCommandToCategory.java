package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.CategoryCommand;
import ali.ashique.recipe.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null)
            return null;
        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setCategoryName(categoryCommand.getCategoryName());
        return category;
    }
}
