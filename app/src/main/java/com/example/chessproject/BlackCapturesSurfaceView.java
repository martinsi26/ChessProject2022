package com.example.chessproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BlackCapturesSurfaceView extends SurfaceView {

    private Paint imagePaint;


    public BlackCapturesSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        imagePaint = new Paint();
        imagePaint.setColor(Color.BLACK);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
