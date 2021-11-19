package Business;

import Model.Puzzle;

public interface BoardManageAble {

    /**
     * Getter for the puzzle
     * @return the puzzle
     */
    Puzzle getPuzzle();

    /**
     * Creates the board game with the specified dimension.
     */
    void createGame(int width, int height);

    /**
     * Checks whether the piece at pos given can move up.
     * @param xPos The position to check from.
     * @param yPos The position to check from.
     * @return True if can move, false if not.
     */
    boolean canPieceMoveUp(int xPos, int yPos);

    /**
     * Checks whether the piece at pos given can move down.
     * @param xPos The position to check from.
     * @param yPos The position to check from.
     * @return True if can move, false if not.
     */
    boolean canPieceMoveDown(int xPos, int yPos);

    /**
     *Checks whether the piece at pos given can move right.
     * @param xPos The position to check from.
     * @param yPos The position to check from.
     * @return True if can move, false if not.
     */
    boolean canPieceMoveRight(int xPos, int yPos);

    /**
     *Checks whether the piece at pos given can move left.
     * @param xPos The position to check from.
     * @param yPos The position to check from.
     * @return True if can move, false if not.
     */
    boolean canPieceMoveLeft(int xPos, int yPos);

    /**
     * Tries to move the piece up.
     * @param xPos The position to move from.
     * @param yPos The position to move from.
     * @return True moved, false if not.
     */
    boolean movePieceUp(int xPos, int yPos);

    /**
     * Tries to move down.
     * @param xPos The position to move from.
     * @param yPos The position to move from.
     * @return True d, false if not.
     */
    boolean movePieceDown(int xPos, int yPos);

    /**
     * Tries to move right.
     * @param xPos The position to move from.
     * @param yPos The position to move from.
     * @return True moved, false if not.
     */
    boolean movePieceRight(int xPos, int yPos);

    /**
     * Tries to move left.
     * @param xPos The position to move from.
     * @param yPos The position to move from.
     * @return True moved, false if not.
     */
    boolean movePieceLeft(int xPos, int yPos);


    /**
     * Returns whether the game is won or not.
     */
    boolean isGameWon();

}
