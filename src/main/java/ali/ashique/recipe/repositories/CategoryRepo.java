package ali.ashique.recipe.repositories;

import ali.ashique.recipe.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
}
