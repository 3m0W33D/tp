package terminus.command.link;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.content.Link;
import terminus.exception.InvalidLinkException;
import terminus.exception.InvalidTimeFormatException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidDayException;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddLinkCommandTest {

    private LinkCommandParser linkCommandParser;
    private NusModule nusModule;
    private Ui ui;

    Class<Link> type = Link.class;

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void parseArguments_addLinkCommand_success() {
        String addLinkInput = "add \"test\" \"Thursday\" \"00:00\" \"Test.com\"";
        ArrayList<String> parsedArguments = CommonFormat.findArguments(addLinkInput);
        assertEquals("test", parsedArguments.get(0));
        assertEquals("Thursday", parsedArguments.get(1));
        assertEquals("00:00", parsedArguments.get(2));
        assertEquals("Test.com", parsedArguments.get(3));
    }

    @Test
    void execute_addLinkCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"https://zoom.us/test\"");
        CommandResult addResult = addLinkCommand.execute(ui, nusModule);
        assertTrue(addResult.isOk());
        assertEquals(1, nusModule.getContentManager(type).getTotalContents());
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("test"));
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("Monday"));
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("00:00"));
        assertTrue(nusModule.getContentManager(type).getContentData(1).contains("https://zoom.us/test"));

        for (int i = 0; i < 5; i++) {
            addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"https://zoom.us/test" + i + "\"");
            addResult = addLinkCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }
        assertEquals(6, nusModule.getContentManager(type).getTotalContents());
    }
}