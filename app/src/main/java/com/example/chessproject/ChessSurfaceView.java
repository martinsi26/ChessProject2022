package com.example.chessproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class ChessSurfaceView extends SurfaceView {

    private Paint colorSquare;
    private Rect newRect;

    private float top;
    private float left;

    private float bottom;
    private float right;

    private float size;



    public ChessSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        size = 75;

        left = 100;
        top = 30;

        right = left + size;
        bottom = top + size;

        //newRect = new Rect(100,100,200,200);

        colorSquare = new Paint();
        colorSquare.setColor(Color.WHITE);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //board initialization
        for(int j = 1; j <= 8; j++) {
            for(int i = 1; i <= 8; i++) {
                //alternate colors
                if((i%2 == 0 && j%2 != 0) || (j%2 == 0 && i%2 != 0)){
                    colorSquare.setColor(Color.BLACK);
                } else {
                    colorSquare.setColor(Color.WHITE);
                }

                //draw rectangle
                canvas.drawRect(left + (right - left) * i, top + (bottom - top) * j,
                        right + (right - left) * i, bottom + (bottom - top) * j, colorSquare);
            }
        }
    }
}
