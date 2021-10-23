package terminus.common;

public class Messages {

    public static final String MESSAGE_COMMAND_ADD = "add an item into your list.";
    public static final String MESSAGE_COMMAND_DELETE = "delete an item from your list.";
    public static final String MESSAGE_COMMAND_VIEW = "view all items or view an individual items";
    public static final String MESSAGE_COMMAND_BACK = "Returns to the parent workspace.";
    public static final String MESSAGE_COMMAND_EXIT = "Exits the program.";
    public static final String MESSAGE_COMMAND_HELP = "Prints the help page.";
    public static final String MESSAGE_COMMAND_NOTE = "Move to notes workspace.";
    public static final String MESSAGE_COMMAND_SCHEDULE = "Move to schedules workspace.";
    public static final String MESSAGE_COMMAND_QUESTION = "Move to questions workspace.";
    public static final String MESSAGE_COMMAND_TEST_QUESTION = "Test yourself with Active Recall.";
    public static final String MESSAGE_COMMAND_TIMETABLE = "Displays all your schedule.";

    public static final String MESSAGE_RESPONSE_DELETE = "Your %s on '%s' has been deleted!";
    public static final String MESSAGE_RESPONSE_ADD = "Your %s on '%s' has been added!";

    public static final String ERROR_MESSAGE_TAG = "Error: ";

    public static final String ERROR_MESSAGE_INVALID_INPUT = ERROR_MESSAGE_TAG + "Invalid input provided.";
    public static final String ERROR_MESSAGE_MISSING_ARGUMENTS = ERROR_MESSAGE_TAG + "Missing arguments.";
    public static final String ERROR_MESSAGE_EMPTY_CONTENTS = ERROR_MESSAGE_TAG + "Content not found.";
    public static final String ERROR_MESSAGE_INVALID_NUMBER = ERROR_MESSAGE_TAG + "Invalid numerical value provided.";
    public static final String ERROR_MESSAGE_INVALID_TIME_FORMAT = ERROR_MESSAGE_TAG + "Invalid time format %s.";
    public static final String ERROR_MESSAGE_INVALID_LINK = ERROR_MESSAGE_TAG + "Invalid link %s.";
    public static final String ERROR_MESSAGE_INVALID_DAY = ERROR_MESSAGE_TAG + "Invalid day %s.";
    public static final String ERROR_MESSAGE_DUPLICATE_NAME = ERROR_MESSAGE_TAG + "Duplicate name found.";

    public static final String ERROR_FILE_TOO_LARGE = "Unable to read large files.";
    public static final String ERROR_FILE_NOT_DELETED = "Unable to delete the file.";
    public static final String ERROR_FILES_NOT_DELETED = "Unable to delete some file.";
    public static final String ERROR_MESSAGE_FILE = "Unable to save/load file: %s";
    public static final String ERROR_MESSAGE_FOLDER = "Unable to save/load folder: %s";
    public static final String EMPTY_CONTENT_LIST_MESSAGE = "You do not have any content in this workspace.\n";
    public static final String CONTENT_MESSAGE_HEADER = "List of Content\n---------------\n";

    public static final String EMPTY_SCHEDULE_FOR_THE_DAY = "You have no schedule for %s\n";
    
    public static final String MAIN_BANNER = "Welcome to TermiNUS!\n";
    public static final String MAIN_REMINDER = "This is your schedule today:\n";
    public static final String NOTE_BANNER = "You have %d note(s) inside this workspace.";
    public static final String SCHEDULE_BANNER = "You have %d link(s) in this workspace.";
    public static final String QUESTION_BANNER = "You have %d question(s) in this workspace.";
    public static final String INVALID_ARGUMENT_FORMAT_MESSAGE_EXCEPTION = "%s %s";
    public static final String INVALID_ARGUMENT_FORMAT_MESSAGE = "Format: %s";
    public static final String MESSAGE_COMMAND_MODULE = "Move to the module workspace";
    public static final String MESSAGE_COMMAND_ADD_MODULE = "Adds a module";
    public static final String ERROR_MESSAGE_MODULE_WHITESPACE = "Module name cannot contain any whitespaces!";
    public static final String ERROR_MESSAGE_MODULE_EXIST = "Module already exist!";
    public static final String MESSAGE_RESPONSE_MODULE_ADD = "Module %s has been added";
    public static final String MESSAGE_RESPONSE_MODULE_DELETE = "Deleted module %s.";
    public static final String MESSAGE_COMMAND_MODULE_DELETE = "Deletes a module";
    public static final String MESSAGE_COMMAND_MODULE_VIEW = "View all modules available";
    public static final String MESSAGE_RESPONSE_MODULE_FORMAT = "%d. %s";
    public static final String MESSAGE_RESPONSE_NO_MODULES = "You do not have any modules.";
    public static final String NO_QUESTIONS_ERROR_MESSAGE =
        "There are no questions to be tested on. Type 'questions add' to get started";
    public static final String ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE =
        "When you are ready, press [Enter] to continue.";
    public static final String[] ACTIVE_RECALL_SESSION_END_MESSAGE = {"This training session has ended.",
        "Returning you back to main program."};
    public static final String[] ACTIVE_RECALL_ASK_QUESTION_DIFFICULTY_MESSAGE = {"",
        "How did you find the question? (Compare against past attempts if any)",
        "[1] Easy; [2] Normal / Same; [3] Hard; [E] Exit"};
    public static final String MESSAGE_EMPTY_DAILY_SCHEDULE = "You have no schedule for today.";
    public static final String HELP_MENU_MESSAGE = "\nHelp Menu\n---------\n";
}
