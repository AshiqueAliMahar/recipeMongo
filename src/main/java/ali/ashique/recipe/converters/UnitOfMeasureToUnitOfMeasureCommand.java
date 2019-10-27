package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.UnitOfMeasureCommand;
import ali.ashique.recipe.models.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null)
            return null;
        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(unitOfMeasure.getId());
        unitOfMeasureCommand.setUnitOfMeasure(unitOfMeasure.getUnitOfMeasure());
        return unitOfMeasureCommand;
    }
}
