package ali.ashique.recipe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data
@Document
public class Recipe {

    @Id
    private String id;
    @NotNull
    private String description;
    private int prepTime;
    private int cookTime;
    private String servings;
    private String source;
    private String url;
    private String directions;

    private Difficulty difficulty;

    private byte[] image;
    private Notes notes;
    private List<Ingredient> ingredients = new LinkedList<>();
    @DBRef
    private List<Category> categories = new LinkedList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

}
