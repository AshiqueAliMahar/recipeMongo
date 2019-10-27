package ali.ashique.recipe.commandObjects;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class IngredientCommand {
    private String id;

    private String recipeId;
    @NotBlank
    @Size(min = 5, max = 255, message = "Description Must be of 5-255 characters")
    private String description;

    private double amount;
    private UnitOfMeasureCommand unitOfMeasureCommand;
}
