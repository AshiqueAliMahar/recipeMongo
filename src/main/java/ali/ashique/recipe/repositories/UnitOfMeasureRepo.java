package ali.ashique.recipe.repositories;

import ali.ashique.recipe.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepo extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfmeasure);
}
