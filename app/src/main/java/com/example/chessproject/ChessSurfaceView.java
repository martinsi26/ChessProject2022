package com.example.chessproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class ChessSurfaceView extends SurfaceView {

    private Paint black;
    private Rect newRect;

    public ChessSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        newRect = new Rect(100,100,200,200);


        black = new Paint();
        black.setColor(Color.WHITE);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(newRect, black);
    }
}
