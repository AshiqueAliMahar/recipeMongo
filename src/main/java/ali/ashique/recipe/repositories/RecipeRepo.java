package ali.ashique.recipe.repositories;

import ali.ashique.recipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepo extends CrudRepository<Recipe, String> {
}
