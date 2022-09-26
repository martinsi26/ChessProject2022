package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button surrenderButton = findViewById(R.id.surrenderButton);
        ChessSurfaceView chessSurfaceView = findViewById(R.id.chessBoard);
        TextView movesLog = findViewById(R.id.movesLog);
        chessSurfaceView.setMovesLog(movesLog);
        chessSurfaceView.displayMovesLog();

        chessSurfaceView.setOnTouchListener(chessSurfaceView);

        ViewListener viewListener = new ViewListener(surrenderButton);
        surrenderButton.setOnClickListener(viewListener);

    }
}