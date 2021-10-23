package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.storage.ModuleStorage;
import terminus.ui.Ui;

public class DeleteNoteCommandTest {

    private NoteCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;
    private ModuleStorage moduleStorage;

    private String tempModule = "test";

    Class<Note> type = Note.class;

    @BeforeEach
    void setUp() throws IOException {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(TestFilePath.SAVE_FILE);
        this.moduleStorage.createModuleDirectory(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = NoteCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
        this.ui = new Ui();
    }

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test");
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
        }

        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command deleteCommand = commandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteCommand.execute(ui, moduleManager);
        assertTrue(deleteResult.isOk());
        assertEquals(4, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        for (int i = 2; i < 4; i++) {
            deleteCommand = commandParser.parseCommand("delete " + i);
            deleteResult = deleteCommand.execute(ui, moduleManager);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(2, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_throwsException() throws InvalidCommandException, InvalidArgumentException {
        Command deleteCommand = commandParser.parseCommand("delete 100");
        assertThrows(InvalidArgumentException.class, () -> deleteCommand.execute(ui, moduleManager));
    }
}
