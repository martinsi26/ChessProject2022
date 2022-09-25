package com.example.chessproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

public class ChessSurfaceView extends SurfaceView {

    //private Paint black;
    //private Rect newRect;

    public ChessSurfaceView(Context context) {
        super(context);
        setWillNotDraw(false);

        //newRect = new Rect(10,10,50,50);

        //black = new Paint();
        //black.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //canvas.drawRect(newRect, black);


    }
}
