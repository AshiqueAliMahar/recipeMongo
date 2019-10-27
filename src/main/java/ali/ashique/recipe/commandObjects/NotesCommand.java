package ali.ashique.recipe.commandObjects;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class NotesCommand {
    private String id;
    @NotEmpty
    @Size(min = 20, max = 255)
    private String notes;
}
