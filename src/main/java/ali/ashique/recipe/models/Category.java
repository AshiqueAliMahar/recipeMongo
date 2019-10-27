package ali.ashique.recipe.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Category {

    @Id
    private String id;
    private String categoryName;
    @DBRef
    private List<Recipe> recipes = new LinkedList<>();
}
