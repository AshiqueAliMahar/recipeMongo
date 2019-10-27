package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.RecipeCommand;
import ali.ashique.recipe.models.Category;
import ali.ashique.recipe.models.Ingredient;
import ali.ashique.recipe.models.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private CategoryToCategoryCommand c2CC;
    private IngredientToIngredientCommand i2IC;
    private NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand c2CC, IngredientToIngredientCommand i2IC, NotesToNotesCommand notesToNotesCommand) {
        this.c2CC = c2CC;
        this.i2IC = i2IC;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;
        final RecipeCommand target = new RecipeCommand();
        target.setCookTime(source.getCookTime());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        target.setId(source.getId());
        target.setImage(source.getImage());
        target.setNotes(notesToNotesCommand.convert(source.getNotes()));
        target.setPrepTime(source.getPrepTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());

        for (Category category : source.getCategories()) {
            target.getCategories().add(c2CC.convert(category));
        }
        for (Ingredient ingredient : source.getIngredients()) {
            target.getIngredients().add(i2IC.convert(ingredient));
        }
        return target;
    }
}
