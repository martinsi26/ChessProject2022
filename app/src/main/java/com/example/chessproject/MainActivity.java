package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChessSurfaceView chessSurfaceView = findViewById(R.id.chessBoard);
        TextView movesLog = findViewById(R.id.movesLog);
        chessSurfaceView.setMovesLog(movesLog);
        chessSurfaceView.displayMovesLog();

    }
}