package Business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Model.Piece;
import Model.Puzzle;

public class BoardManager implements BoardManageAble {

    /**
     * Get the current puzzle
     * @return the puzzle
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Set the current puzzle
     * @param puzzle The puzzle to be assigned
     */
    private void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    private Puzzle puzzle;

    public BoardManager() {

    }

    @Override
    public void createGame(int width, int height) {
        setPuzzle(new Puzzle(width, height));
    }

    @Override
    public boolean canPieceMove(int xPos, int yPos) {
        return false;
    }
}
