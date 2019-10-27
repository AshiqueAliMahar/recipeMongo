package ali.ashique.recipe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class UnitOfMeasure {
    @Id
    private String id;
    private String unitOfMeasure;

    public UnitOfMeasure() {
    }

    public UnitOfMeasure(String id, String unitOfMeasure) {
        this.id = id;
        this.unitOfMeasure = unitOfMeasure;
    }

}
