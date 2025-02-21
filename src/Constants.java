import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

public class Constants {
    public static final String[] IMAGES = {
            """
          +---+
          |   |
              |
              |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
              |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        ========="""
    };

    public static final String FILE_NAME = "dictionary.txt";
    public static final String MENU_TEXT = """
            *******************************
            *    ДОБРО ПОЖАЛОВАТЬ В ИГРУ  *
            *******************************
            *  1. Начать новую игру       *
            *  2. Инструкции              *
            *  3. Выйти                   *
            *******************************
            """;

    public static final String INSTRUCTIONS_TEXT = """
            **************************************************************
            *                   ИНСТРУКЦИЯ К ИГРЕ В ВИСЕЛИЦУ             *
            **************************************************************
            * 1. Отгадывайте по одной букве за раз.                      *
            * 2. Правильные буквы открывают символы.                     *
            * 3. Неправильные буквы добавляют часть виселицы.            *
            * 4. Отгадайте слово, пока виселица не будет завершена!      *
            * 5. Если виселица будет полностью нарисована, вы проиграли. *
            **************************************************************
            """;

    public static final String PRESS_ANY_KEY_TEXT = "Нажмите любую клавишу для продолжения...";
    public static final String WON_TEXT = "Вы ПОБЕДИЛИ!";
    public static final String LOST_TEXT = "Вы ПРОИГРАЛИ!";
    public static final String CORRECT_LETTER_TEXT = "Правильная буква: ";
    public static final String INCORRECT_LETTER_TEXT = "Неправильная буква: ";
    public static final String ALREADY_USED_TEXT = "Уже использовано: ";
    public static final String ALREADY_USED_HINTS_TEXT = "Вы использовали подсказки!";
    public static final String FAILED_TO_LOAD_DICTIONARY_TEXT = "Не удалось начать игру: не удалось загрузить словарь.";
    public static final String FAILED_TO_FIND_FILE_TEXT = "Не удалось начать игру: файл не найден.";
    public static final String HINT_USED_TEXT = "Вы использовали подсказку.";
    public static final String INVALID_LETTER_TEXT = "Это не действительная буква.";
    public static final String GUESS_WORD_TEXT = "Слово для отгадывания: ";
    public static final String INVALID_ATTEMPTS_TEXT = "Неправильные попытки [%d/%d]: ";
    public static final String INPUT_PROMPT_TEXT = "Следующая попытка (Введите '?' для подсказки [%d] или '!' для выхода): ";
    public static final String MASKED_WORD_TEXT = "Загаданное слово: ";
    public static final String SELECT_OPTION_TEXT = "Пожалуйста, выберите опцию (1-3): ";
    public static final String INVALID_OPTION_TEXT = "Неверная опция.";
}
