package ali.ashique.recipe.repositories;

import ali.ashique.recipe.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface
IngredientRepo extends CrudRepository<Ingredient, Long> {
}
