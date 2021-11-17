package com.example.puzzleboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Business.BoardManager;
import Model.Piece;
import Model.Puzzle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BoardManager manager = new BoardManager();

        manager.createGame(3, 3);

        Piece[] pieces = manager.getPuzzle().getPieces();

    }
}