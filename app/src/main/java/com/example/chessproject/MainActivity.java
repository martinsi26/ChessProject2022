package com.example.chessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChessSurfaceView chessSurfaceView = findViewById(R.id.chessBoard);
        BlackCapturesSurfaceView blackCapturesSurfaceView = findViewById(R.id.blackCaptures);
        WhiteCapturesSurfaceView whiteCapturesSurfaceView = findViewById(R.id.whiteCaptures);

    }
}