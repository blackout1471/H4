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

    public void createGame() {
        manager.createGame(3, 3);

        Puzzle p = manager.getPuzzle();

        view.createGame(p.getPieces(), p.getWidth(), p.getHeight());
    }

    public interface View {
        void createGame(Piece[] pieces, int width, int height);
        void gameUpdate(Piece[] pieces, int width, int height);
    }
}
