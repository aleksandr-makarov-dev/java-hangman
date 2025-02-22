import java.util.Objects;

public class Letter {
    private final char value;

    public Letter(char value) {
        this.value = value;
    }

    public static Letter of(char value){
        return new Letter(value);
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equals(value, letter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
