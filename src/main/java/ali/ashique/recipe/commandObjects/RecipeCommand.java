package ali.ashique.recipe.commandObjects;

import ali.ashique.recipe.models.Difficulty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class RecipeCommand {

    private String id;
    @NotBlank
    @Size(min = 5, max = 255)
    private String description;
    @NotNull
    @Min(2)
    @Max(100)
    private int prepTime;
    @NotNull
    @Min(value = 1, message = "length must be more than 1")
    @Max(value = 100, message = "length must be 100")
    private int cookTime;
    @NotBlank
    @Size(min = 10, max = 255)
    private String servings;

    private String source;
    @URL
    private String url;
    private String directions;
    private Difficulty difficulty;
    private byte[] image;
    @Valid
    private NotesCommand notes;
    private List<IngredientCommand> ingredients = new LinkedList<>();
    private List<CategoryCommand> categories = new LinkedList<>();

}
