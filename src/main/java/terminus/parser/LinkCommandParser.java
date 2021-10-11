package terminus.parser;

import terminus.command.BackCommand;
import terminus.command.zoomlink.AddLinkCommand;
import terminus.command.ViewCommand;
import terminus.command.DeleteCommand;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.content.Link;
import terminus.module.NusModule;

/**
 * LinkCommandParser class to manage schedule-related commands.
 */
public class LinkCommandParser extends CommandParser {

    public LinkCommandParser() {
        super(CommonFormat.COMMAND_SCHEDULE);
    }

    /**
     * Returns the command map for the schedule workspace.
     *
     * @return A LinkCommandParser object which contains the command map for the schedule workspace
     */
    public static LinkCommandParser getInstance() {
        LinkCommandParser parser = new LinkCommandParser();
        parser.addCommand(CommonFormat.COMMAND_BACK, new BackCommand());
        parser.addCommand(CommonFormat.COMMAND_ADD, new AddLinkCommand());
        parser.addCommand(CommonFormat.COMMAND_VIEW, new ViewCommand<Class<Link>>(Link.class));
        parser.addCommand(CommonFormat.COMMAND_DELETE, new DeleteCommand<Class<Link>>(Link.class));
        return parser;
    }

    /**
     * Returns the opening description of the workspace.
     *
     * @param module The current module containing the array list of all the links
     * @return The string containing a description of the number of links in the workspace
     */
    @Override
    public String getWorkspaceBanner(NusModule module) {
        return String.format(Messages.SCHEDULE_BANNER, module.getLinks().size());
    }
}
