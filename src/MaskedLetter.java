public class MaskedLetter extends Letter {

    private boolean visible;

    public MaskedLetter(char value) {
        super(value);

        visible = false;
    }


    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return visible ? String.valueOf(this.getValue()) : "_";
    }
}
