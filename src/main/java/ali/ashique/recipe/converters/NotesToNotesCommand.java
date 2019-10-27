package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.NotesCommand;
import ali.ashique.recipe.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null)
            return null;
        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setNotes(notes.getNotes());
        return notesCommand;
    }
}
