import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    private final Scanner scanner = new Scanner(System.in);

    private final List<String> dictionary;
    private final List<MaskedLetter> maskedLetters;
    private final Set<Letter> usedLetters;
    private final int totalAttempts = 6;
    private GameState state;
    private int attempts = 0;
    private int hints = 1;

    public Game() {
        state = GameState.SELECT_MENU_OPTION;

        maskedLetters = new ArrayList<>();
        usedLetters = new LinkedHashSet<>();
        dictionary = new ArrayList<>();
    }

    public void startLoop() {
        while (state != GameState.EXIT) {
            switch (state) {
                case LOAD_DICTIONARY -> handleLoadDictionary();
                case SELECT_MENU_OPTION -> handleSelectMenuOption();
                case SHOW_INSTRUCTIONS -> handleShowInstructions();
                case INIT -> handleInitState();
                case PLAYING -> handlePlayingState();
                case WIN -> handleWinState();
                case LOSE -> handleLoseState();
            }
        }
    }


    private void handleSelectMenuOption() {
        while (state == GameState.SELECT_MENU_OPTION) {
            printMenu();
            String input = getUserInput(Constants.SELECT_OPTION_TEXT);
            switch (input) {
                case "1" -> state = GameState.LOAD_DICTIONARY;
                case "2" -> state = GameState.SHOW_INSTRUCTIONS;
                case "3" -> state = GameState.EXIT;
                default -> System.out.println(Constants.INVALID_OPTION_TEXT);
            }
        }
    }

    private void handleShowInstructions() {
        System.out.println(Constants.INSTRUCTIONS_TEXT);
        getUserInput(Constants.PRESS_ANY_KEY_TEXT);
        state = GameState.SELECT_MENU_OPTION;
    }

    private void handleLoadDictionary() {
        try {
            File file = new File(Constants.FILE_NAME);
            if (file.exists() && !file.isDirectory()) {
                dictionary.clear();
                dictionary.addAll(Files.readAllLines(Path.of(Constants.FILE_NAME)));
                state = GameState.INIT;
            } else {
                System.out.println(Constants.FAILED_TO_FIND_FILE_TEXT);
                state = GameState.EXIT;
            }
        } catch (IOException e) {
            System.out.println(Constants.FAILED_TO_LOAD_DICTIONARY_TEXT);
            state = GameState.EXIT;
        }
    }

    private void handleInitState() {
        int length = dictionary.size();
        String word = dictionary.get(ThreadLocalRandom.current().nextInt(0, length))
                .trim()
                .toLowerCase();

        maskedLetters.clear();
        usedLetters.clear();
        hints = 1;
        attempts = 0;

        for (char ch : word.toCharArray()) {
            maskedLetters.add(new MaskedLetter(String.valueOf(ch)));
        }

        state = GameState.PLAYING;
    }

    private void handlePlayingState() {
        printAttemptImage();
        printMaskedLetters();
        printUsedLetters();
        String letter = getLetterInput();

        if (letter.equals("!")) {
            state = GameState.SELECT_MENU_OPTION;
            return;
        }

        if (letter.equals("?")) {
            if (hints > 0) {
                useHint();
            } else {
                System.out.println(Constants.ALREADY_USED_HINTS_TEXT);
            }

            return;
        }

        LetterCheckResult result = updateLetters(Letter.of(letter));

        switch (result) {
            case CORRECT -> System.out.println(Constants.CORRECT_LETTER_TEXT + letter);
            case INCORRECT -> {
                System.out.println(Constants.INCORRECT_LETTER_TEXT + letter);
                attempts++;
            }
            case ALREADY_USED -> System.out.println(Constants.ALREADY_USED_TEXT + letter);
        }

        if (attempts == totalAttempts) {
            state = GameState.LOSE;
        } else if (maskedLetters.stream().allMatch(MaskedLetter::getVisible)) {
            state = GameState.WIN;
        }
    }

    private void handleWinState() {
        handleResultState(Constants.WON_TEXT);
    }

    private void handleLoseState() {
        printAttemptImage();
        handleResultState(Constants.LOST_TEXT);
    }

    private void handleResultState(String message) {
        System.out.println(message);
        System.out.println(Constants.MASKED_WORD_TEXT + getMaskedLettersAsString());
        getUserInput(Constants.PRESS_ANY_KEY_TEXT);
        state = GameState.SELECT_MENU_OPTION;
    }

    private void printMenu() {
        System.out.println(Constants.MENU_TEXT);
    }

    private void printMaskedLetters() {
        String text = maskedLetters.stream()
                .map(Letter::toString)
                .collect(Collectors.joining(" "));
        System.out.println(Constants.GUESS_WORD_TEXT + text);
    }

    private void printUsedLetters() {
        String text = usedLetters.stream()
                .map(Letter::toString)
                .collect(Collectors.joining(","));
        System.out.println(Constants.INVALID_ATTEMPTS_TEXT.formatted(attempts, totalAttempts) + text);
    }

    private void printAttemptImage() {
        System.out.println(Constants.IMAGES[attempts]);
    }

    private String getMaskedLettersAsString() {
        return maskedLetters.stream().map(Letter::getValue).collect(Collectors.joining());
    }

    private String getUserInput(final String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private String getLetterInput() {
        while (true) {
            String input = getUserInput(Constants.INPUT_PROMPT_TEXT.formatted(hints))
                    .trim()
                    .toLowerCase();

            if (input.matches("[a-zа-я!?]")) {
                return input;
            }

            System.out.println(Constants.INVALID_LETTER_TEXT);
        }
    }

    private LetterCheckResult updateLetters(final Letter letter) {
        if (usedLetters.contains(letter)) {
            return LetterCheckResult.ALREADY_USED;
        }

        LetterCheckResult result = LetterCheckResult.INCORRECT;

        for (MaskedLetter maskedLetter : maskedLetters) {
            if (maskedLetter.getValue().equals(letter.getValue())) {
                if (maskedLetter.getVisible()) {
                    return LetterCheckResult.ALREADY_USED;
                }

                maskedLetter.setVisible(true);
                result = LetterCheckResult.CORRECT;
            }
        }

        if (result != LetterCheckResult.CORRECT) {
            usedLetters.add(letter);
        }

        return result;
    }

    private void useHint() {
        List<Integer> indexes = IntStream.range(0, maskedLetters.size())
                .filter(index -> !maskedLetters.get(index).getVisible())
                .boxed()
                .toList();

        int randomIndex = ThreadLocalRandom.current().nextInt(0, indexes.size());
        updateLetters(maskedLetters.get(randomIndex));
        System.out.println(Constants.HINT_USED_TEXT);
        hints--;
    }
}
