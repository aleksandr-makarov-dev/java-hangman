public class MaskedLetter extends Letter {

    private boolean visible;

    public MaskedLetter(String value) {
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
        return visible ? this.getValue() : "_";
    }
}
