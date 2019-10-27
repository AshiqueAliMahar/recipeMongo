package ali.ashique.recipe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"recipe"})
public class Ingredient {
    private String id;
    private String description;
    private double amount;
    @DBRef
    private UnitOfMeasure unitOfMeasure;
}
