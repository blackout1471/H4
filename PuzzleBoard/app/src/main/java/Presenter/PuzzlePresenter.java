package Presenter;

import Business.BoardManageAble;
import Business.BoardManager;
import Model.Piece;
import Model.Puzzle;

public class PuzzlePresenter {

    private View view;
    private BoardManageAble manager;

    public PuzzlePresenter(View _view) {
        view = _view;
        manager = new BoardManager();
    }

    /**
     * Creates the game in a 3x3 format
     */
    public void createGame() {
        manager.createGame(3, 3);

        Puzzle p = manager.getPuzzle();

        view.createGame(p.getPieces(), p.getWidth(), p.getHeight());
    }

    /**
     * Retrieves the direction, by calculation from positions
     * @param fromXPos
     * @param fromYPos
     * @param toXPos
     * @param toYPos
     * @return The directions in a enum.
     */
    public Puzzle.Direction getDirection(int fromXPos, int fromYPos, int toXPos, int toYPos) {
        int xDir = toXPos - fromXPos;
        int yDir = toYPos - fromYPos;

        if (xDir != 0 && yDir != 0)
            return Puzzle.Direction.INVALID;

        if (xDir == 1)
            if (manager.canPieceMoveRight(fromXPos, fromYPos))
                return Puzzle.Direction.RIGHT;

        if (xDir == -1)
            if (manager.canPieceMoveLeft(fromXPos, fromYPos))
                return  Puzzle.Direction.LEFT;

        if (yDir == -1)
            if (manager.canPieceMoveUp(fromXPos, fromYPos))
                return Puzzle.Direction.UP;

        if (yDir == 1)
            if (manager.canPieceMoveDown(fromXPos, fromYPos))
                return Puzzle.Direction.DOWN;

        return Puzzle.Direction.INVALID;
    }

    /**
     * Moves the piece from a direction, to the specified if allowed
     * @param dir The direction to try and move
     * @param fromX The pieces x pos.
     * @param fromY The pieces y pos.
     */
    public void movePiece(Puzzle.Direction dir, int fromX, int fromY) {
        if (dir == Puzzle.Direction.INVALID)
            return;

        switch (dir)
        {
            case UP:
                manager.movePieceUp(fromX, fromY);
            case DOWN:
                manager.movePieceDown(fromX, fromY);
            case RIGHT:
                manager.movePieceRight(fromX, fromY);
            case LEFT:
                manager.movePieceLeft(fromX, fromY);
                break;
        }

        if (manager.isGameWon())
            view.gameIsWon();
        else
            view.gameUpdate(
                    manager.getPuzzle().getPieces(),
                    manager.getPuzzle().getWidth(),
                    manager.getPuzzle().getHeight()
            );
    }

    public interface View {
        void createGame(Piece[] pieces, int width, int height);
        void gameUpdate(Piece[] pieces, int width, int height);
        void gameIsWon();
    }
}
