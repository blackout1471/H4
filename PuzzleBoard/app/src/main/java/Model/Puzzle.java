package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle {

    /**
     * Gets the width of the board
     * @return returns the width as int.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the board
     * @param width The width to be assigned
     */
    private void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the board
     * @return Returns the height as int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the board
     * @param height the value to be assigned as height
     */
    private void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the board represented as a 1d array, calculated by width * height
     * @return The board as an array of pieces.
     */
    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * Set the boards pieces.
     * @param pieces The pieces to be assigned
     */
    private void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }

    /**
     * Set the piece from a x pos and y pos.
     */
    public void setPiece(int xPos, int yPos, Piece value) {
        this.pieces[yPos * getWidth() + xPos] = value;
    }

    /**
     * Get the piece from a x & y pos.
     */
    public Piece getPiece(int xPos, int yPos) {
        return this.pieces[yPos * getWidth() + xPos];
    }

    /**
     * Set the piece from a given position in the 1d array.
     * @param pos the position calculated as (y pos * width + x pos)
     * @param value The new value to be assigned
     */
    private void setPiece(int pos, Piece value) {
        this.pieces[pos] = value;
    }

    private int width;
    private int height;
    private Piece[] pieces;

    public Puzzle(int _width, int _height)
    {
        setWidth(_width);
        setHeight(_height);
        setupPuzzle();
    }

    /**
     * Setup the board and shuffle the pieces.
     */
    private void setupPuzzle()
    {
        List<Piece> pieces = new ArrayList<Piece>(getWidth() * getHeight());
        for (int i = 0; i < pieces.size(); i++)
            pieces.add(new Piece(i));

        Collections.shuffle(pieces);

        Piece[] puzzlePieces = new Piece[pieces.size()];
        pieces.toArray(puzzlePieces);
        setPieces(puzzlePieces);
    }

}
