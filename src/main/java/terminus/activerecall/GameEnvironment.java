package terminus.activerecall;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import terminus.common.Messages;
import terminus.content.Question;
import terminus.ui.Ui;

public class GameEnvironment {

    private final Ui ui;
    private final QuestionGenerator questionGenerator;

    GameEnvironment(Ui ui, QuestionGenerator generator) {
        this.ui = ui;
        this.questionGenerator = generator;
    }

    /**
     * Starts the active recall session.
     */
    public void run() {
        showPreGameInformation();
        while (questionGenerator.hasNext()) {
            Question question = questionGenerator.next();
            promptQuestion(question);
            int difficulty = getUserFeedback();
            if (difficulty == -1) {
                break;
            }
            postQuestionFeedback(question, difficulty);
        }
        ui.printSection(Messages.ACTIVE_RECALL_SESSION_END_MESSAGE);
    }

    private void showPreGameInformation() {
        int questions = questionGenerator.getQuestionPoolSize();
        ui.printSection(
            "---[Active Recall]---",
            "",
            "We will be starting your active recall training session.",
            String.format("This session will consist of %d questions.", questions),
            ""
        );
        ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
    }

    private void promptQuestion(Question question) {
        Instant start = Instant.now(); 
        ui.printSection(
            "",
            "---",
            "",
            "Question:",
            question.getQuestion(),
            ""
        );
        ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
        
        long duration = Duration.between(start, Instant.now()).getSeconds();
        ui.printSection(
            String.format("You took %d seconds to reveal the answer.", duration),
            "",
            "Answer:", 
            question.getAnswer()
        );
    }

    private int getUserFeedback() {
        int difficulty = 0;
        do {
            ui.printSection(Messages.ACTIVE_RECALL_ASK_QUESTION_DIFFICULTY_MESSAGE);
            String input = ui.getUserInput("[1/2/3/E] >> ").trim().toLowerCase();
            Pattern inputPattern = Pattern.compile("^[123e]$");
            Matcher matcher = inputPattern.matcher(input);
            if (!matcher.matches()) {
                ui.printSection(Messages.ERROR_MESSAGE_INVALID_INPUT);
                continue;
            } else if (input.equalsIgnoreCase("e")) {
                difficulty = -1;
                break;
            }
            difficulty = Integer.parseInt(input);
            
        } while (difficulty == 0);
        assert difficulty <= 3 && difficulty >= -1;
        return difficulty;
    }
    
    private void postQuestionFeedback(Question question, int difficulty) {
        assert difficulty >= 1 && difficulty <= 3;
        double weight = question.getWeight();
        if (difficulty == 1) {
            double newWeight = DifficultyModifier.tweakEasyQuestionDifficulty(weight);
            question.setWeight(newWeight);
        } else if (difficulty == 3) {
            double newWeight = DifficultyModifier.tweakHardQuestionDifficulty(weight);
            question.setWeight(newWeight);
        }
        ui.printSection("");
        if (questionGenerator.hasNext()) {
            ui.getUserInput(Messages.ACTIVE_RECALL_ENTER_TO_CONTINUE_MESSAGE);
        }
    }

    /**
     * Create a new GameEnvironment instance.
     * 
     * @param ui The UI to handle all input and output.
     * @param questions The list of questions to ask from.
     * @param questionCount The maximum number of questions.
     * @return The new GameEnvironment to start the Active Recall.
     */
    public static GameEnvironment createNewEnvironment(Ui ui, List<Question> questions, int questionCount) {
        return new GameEnvironment(ui, new QuestionGenerator(questions, questionCount));
    }

}
