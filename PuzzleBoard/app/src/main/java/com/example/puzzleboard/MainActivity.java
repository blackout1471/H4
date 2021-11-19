package com.example.puzzleboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import Model.Piece;
import Model.Puzzle;
import Presenter.PuzzlePresenter;
import Views.PieceView;

public class MainActivity extends AppCompatActivity implements PuzzlePresenter.View {

    private PuzzlePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new PuzzlePresenter(this);
        presenter.createGame();

        findViewById(R.id.createGameBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createGame();
            }
        });

    }


    @Override
    public void createGame(Piece[] pieces, int width, int height) {
        LinearLayout grid = findViewById(R.id.gridLinearLayout);
        grid.removeAllViews();

        for (int y = 0; y < height; y++)
        {
            LinearLayout currentRow = createRow();
            grid.addView(currentRow);

            for (int x = 0; x < width; x++)
            {
                PieceView curPiece = createColumn(x, y);
                curPiece.setText("" + pieces[y * height + x].getValue());

                currentRow.addView(curPiece);
            }
        }
    }

    @Override
    public void gameUpdate(Piece[] pieces, int width, int height) {
        createGame(pieces, width, height);
    }

    @Override
    public void gameIsWon() {
        LinearLayout grid = findViewById(R.id.gridLinearLayout);
        grid.removeAllViews();

        TextView v = new TextView(this);
        v.setText("Game is won");

        grid.addView(v);
    }

    /**
     * Creates a new row for the pieces to be in
     * @return
     */
    private LinearLayout createRow() {
        LinearLayout ll = new LinearLayout(this);

        LinearLayout.LayoutParams lll = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        ll.setLayoutParams(lll);

        return ll;
    }

    /**
     * Creates a new column, also called a piece.
     * @param xPos The x pos to create it in.
     * @param yPos The y pos to create it in.
     * @return A new piece.
     */
    private PieceView createColumn(int xPos, int yPos) {
        PieceView tv = new PieceView(this, xPos, yPos);

        tv.setLayoutParams(new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            1.0f
        ));

        tv.setGravity(Gravity.CENTER);

        // Drag
        tv.setOnLongClickListener(longClickListener);
        tv.setOnDragListener(dragListener);

        return tv;
    }

    /**
     * Long click listener
     */
    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadow, v, 0);

            return true;
        }
    };

    /**
     * Drag listener
     */
    private View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            PieceView curView;
            PieceView dragView;

            switch (dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    curView = (PieceView)v;
                    dragView = (PieceView)event.getLocalState();
                    onDragEntered(curView, dragView);
                    break;
                case DragEvent.ACTION_DROP:
                    curView = (PieceView)v;
                    dragView = (PieceView)event.getLocalState();
                    onDragDrop(curView, dragView);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    curView = (PieceView)v;
                    dragView = (PieceView)event.getLocalState();
                    onDragExited(curView, dragView);
                    break;
            }

            return true;
        }
    };

    /**
     * On drag Drop method, to handle on drop
     * @param curView
     * @param dragView
     */
    private void onDragDrop(PieceView curView, PieceView dragView) {
        Puzzle.Direction dir = presenter.getDirection(
                dragView.getxPos(), dragView.getyPos(),
                curView.getxPos(), curView.getyPos()
        );

        curView.setBackgroundToDefault();

        presenter.movePiece(dir, dragView.getxPos(), dragView.getyPos());
    }

    /**
     * On drag exited for the drag.
     * @param curView
     * @param dragView
     */
    private void onDragExited(PieceView curView, PieceView dragView) {
        curView.setBackgroundToDefault();
    }

    /**
     * Handles the on drag entered event for on drag.
     * @param curView
     * @param dragView
     */
    private void onDragEntered(PieceView curView, PieceView dragView) {
        Puzzle.Direction dir = presenter.getDirection(
                dragView.getxPos(), dragView.getyPos(),
                curView.getxPos(), curView.getyPos()
        );

        switch (dir) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
                curView.setBackgroundToValid();
                break;
            case INVALID:
                curView.setBackgroundToInvalid();
                break;
        }
    }
}