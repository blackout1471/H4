package Business;


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
    public boolean canPieceMoveUp(int xPos, int yPos) {
        try {
            if (getPuzzle().getPiece(xPos, yPos - 1).getValue() == 0)
                return true;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }

        return false;
    }

    @Override
    public boolean canPieceMoveDown(int xPos, int yPos) {
        try {
            if (getPuzzle().getPiece(xPos, yPos + 1).getValue() == 0)
                return true;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }

        return false;
    }

    @Override
    public boolean canPieceMoveRight(int xPos, int yPos) {
        try {
            if (getPuzzle().getPiece(xPos + 1, yPos).getValue() == 0)
                return true;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }

        return false;
    }

    @Override
    public boolean canPieceMoveLeft(int xPos, int yPos) {
        try {
            if (getPuzzle().getPiece(xPos - 1, yPos).getValue() == 0)
                return true;
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            return false;
        }

        return false;
    }

    @Override
    public boolean movePieceUp(int xPos, int yPos) {
        if (!canPieceMoveUp(xPos, yPos))
            return false;

        getPuzzle().exchangePlaces(xPos, yPos, xPos, yPos-1);

        return true;
    }

    @Override
    public boolean movePieceDown(int xPos, int yPos) {
        if (!canPieceMoveDown(xPos, yPos))
            return false;

        getPuzzle().exchangePlaces(xPos, yPos, xPos, yPos+1);

        return true;
    }

    @Override
    public boolean movePieceRight(int xPos, int yPos) {
        if (!canPieceMoveRight(xPos, yPos))
            return false;

        getPuzzle().exchangePlaces(xPos, yPos, xPos+1, yPos);

        return true;
    }

    @Override
    public boolean movePieceLeft(int xPos, int yPos) {
        if (!canPieceMoveLeft(xPos, yPos))
            return false;

        getPuzzle().exchangePlaces(xPos, yPos, xPos-1, yPos);

        return true;
    }

    @Override
    public boolean isGameWon() {
        Piece[] pieces = getPuzzle().getPieces();

        for(int i = 0; i < pieces.length-1; i++)
            if (pieces[i].getValue() != i+1)
                return false;

        return pieces[pieces.length-1].getValue() == 0;
    }


}
