package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.DeleteCommand;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.ViewCommand;
import terminus.command.note.AddNoteCommand;
import terminus.exception.InvalidLinkException;
import terminus.exception.InvalidTimeFormatException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidDayException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class NoteCommandParserTest {

    private NoteCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("adda"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("vie wer"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("deleterr"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
    }

    @Test
    void parseCommand_resolveExitCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("eXiT a") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HeLp    a") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveAddCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"test1\"test2\""));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test1\" \"test2\""));
    }

    @Test
    void parseCommand_resolveAddCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(commandParser.parseCommand("add \"test\" \"test1\"") instanceof AddNoteCommand);
        assertTrue(commandParser.parseCommand("add \"    test     \" \"    test1   \"") instanceof AddNoteCommand);
        assertTrue(commandParser.parseCommand("add \"username\" \"password\"") instanceof AddNoteCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete -5"));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(commandParser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(commandParser.parseCommand("delete 2") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("view abcd"));
    }

    @Test
    void parseCommand_resolveViewCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(commandParser.parseCommand("view") instanceof ViewCommand);
        assertTrue(commandParser.parseCommand("view 1") instanceof ViewCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("add"));
        assertTrue(commandParser.getCommandList().contains("back"));
        assertTrue(commandParser.getCommandList().contains("delete"));
        assertTrue(commandParser.getCommandList().contains("view"));
        assertTrue(commandParser.getCommandList().contains("help"));
    }

    @Test
    void getWorkspace_isNote() {
        assertEquals("note", commandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(commandParser.getHelpMenu().length > 0);
    }
}
