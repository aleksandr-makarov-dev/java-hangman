import java.util.Objects;

public class Letter {
    private final String value;

    public Letter(String value) {
        this.value = value;
    }

    public static Letter of(String value){
        return new Letter(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
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
