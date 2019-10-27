package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.UnitOfMeasureCommand;
import ali.ashique.recipe.models.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (unitOfMeasureCommand == null)
            return null;
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        unitOfMeasure.setUnitOfMeasure(unitOfMeasureCommand.getUnitOfMeasure());
        return unitOfMeasure;
    }
}
