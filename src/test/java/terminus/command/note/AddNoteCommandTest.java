package terminus.command.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class AddNoteCommandTest {

    private NoteCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    Class<Note> type = Note.class;

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_success()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        Command addCommand = commandParser.parseCommand("add \"test\" \"test1\"");
        CommandResult addResult = addCommand.execute(ui, nusModule);
        assertTrue(addResult.isOk());
        assertEquals(1, nusModule.getContentManager(type).getTotalContents());
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("test"));
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("test1"));
        for (int i = 0; i < 5; i++) {
            addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            addResult = addCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }
        assertEquals(6, nusModule.getContentManager(type).getTotalContents());
    }
}
