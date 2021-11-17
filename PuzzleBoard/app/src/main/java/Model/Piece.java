package Model;

public class Piece {
    /**
     * Rerieve value of the piece.
     * @return The value as int
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of the piece.
     * @param value The value which will be assigned.
     */
    private void setValue(int value) {
        this.value = value;
    }

    private int value;

    public Piece(int _value) {
        setValue(value);
    }
}
