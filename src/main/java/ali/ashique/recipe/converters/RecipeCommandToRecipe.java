package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.CategoryCommand;
import ali.ashique.recipe.commandObjects.IngredientCommand;
import ali.ashique.recipe.commandObjects.RecipeCommand;
import ali.ashique.recipe.models.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private IngredientCommandToIngredient inC2In;
    private CategoryCommandToCategory cc2C;
    private NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient inC2In, CategoryCommandToCategory cc2C, NotesCommandToNotes notesCommandToNotes) {
        this.inC2In = inC2In;
        this.cc2C = cc2C;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;
        final Recipe target = new Recipe();
        target.setCookTime(source.getCookTime());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        target.setId(source.getId());
        target.setImage(source.getImage());
        target.setNotes(notesCommandToNotes.convert(source.getNotes()));
        target.setPrepTime(source.getPrepTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            for (IngredientCommand ingredientCommand : source.getIngredients()) {
                target.getIngredients().add(inC2In.convert(ingredientCommand));
            }
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            for (CategoryCommand categoryCommand : source.getCategories()) {
                target.getCategories().add(cc2C.convert(categoryCommand));
            }
        }
        return target;
    }
}
