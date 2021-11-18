package com.example.puzzleboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import Business.BoardManager;
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

    }

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

    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data, shadow, v, 0);

            return true;
        }
    };

    private View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            Log.i("DragEvent", "event: " + dragEvent);

            switch (dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    PieceView curView = (PieceView)v;
                    PieceView dragView = (PieceView)event.getLocalState();

                    int xDir = dragView.getxPos() - curView.getxPos();
                    int yDir = dragView.getyPos() - curView.getyPos();

                    Puzzle.Direction dir = getDirection(xDir, yDir);

                    if (dir == Puzzle.Direction.INVALID)
                        curView.setBackgroundColor(Color.RED);
                    break;
                case DragEvent.ACTION_DROP:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    PieceView cv = (PieceView)v;
                    cv.setBackgroundColor(Color.WHITE);
                    break;
            }

            return false;
        }
    };

    private Puzzle.Direction getDirection(int xVal, int yVal) {
        if (xVal != 0 && yVal != 0)
            return Puzzle.Direction.INVALID;

        if (xVal > 0)
            return Puzzle.Direction.RIGHT;

        if (xVal < 0)
            return Puzzle.Direction.LEFT;

        if (yVal > 0)
            return Puzzle.Direction.UP;

        if (yVal < 0)
            return Puzzle.Direction.DOWN;

        return Puzzle.Direction.INVALID;
    }
}