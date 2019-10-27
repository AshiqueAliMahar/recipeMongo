package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.CategoryCommand;
import ali.ashique.recipe.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category category) {
        if (category == null)
            return null;
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setCategoryName(category.getCategoryName());
        return categoryCommand;
    }
}
