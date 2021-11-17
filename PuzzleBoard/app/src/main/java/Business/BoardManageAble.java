package Business;

public interface BoardManageAble {
    /**
     * Creates the board game with the specified dimension
     */
    void createGame(int width, int height);

    /**
     * Checks whether the given position in the board can be moved.
     * @param xPos The x position on the board, starts from 0.
     * @param yPos The y position on the board, starts from 0.
     * @return True if can be moved to empty space, false if not.
     */
    boolean canPieceMove(int xPos, int yPos);
}
