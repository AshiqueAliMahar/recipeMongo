package ali.ashique.recipe.converters;

import ali.ashique.recipe.commandObjects.NotesCommand;
import ali.ashique.recipe.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand == null)
            return null;
        final Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setNotes(notesCommand.getNotes());
        return notes;
    }
}
