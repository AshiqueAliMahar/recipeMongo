package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.IngredientCommand;
import ali.ashique.recipe.models.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private UnitOfMeasureToUnitOfMeasureCommand uom2Uomc;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uom2Uomc) {
        this.uom2Uomc = uom2Uomc;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null)
            return null;
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasureCommand(uom2Uomc.convert(ingredient.getUnitOfMeasure()));

        return ingredientCommand;
    }
}
