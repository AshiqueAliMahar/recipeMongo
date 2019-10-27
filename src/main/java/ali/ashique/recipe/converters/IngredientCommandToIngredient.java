package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.IngredientCommand;
import ali.ashique.recipe.models.Ingredient;
import ali.ashique.recipe.models.Recipe;
import ali.ashique.recipe.models.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {


    private UnitOfMeasureCommandToUnitOfMeasure uomc2Uom;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomc2Uom) {

        this.uomc2Uom = uomc2Uom;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null)
            return null;
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        UnitOfMeasure unitOfMeasure = uomc2Uom.convert(ingredientCommand.getUnitOfMeasureCommand());
        ingredient.setUnitOfMeasure(unitOfMeasure);
        Recipe recipe = new Recipe();
        recipe.setId(ingredientCommand.getRecipeId());
        return ingredient;
    }
}
