package ali.ashique.recipe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data()
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    private String id;
    private String notes;
}
