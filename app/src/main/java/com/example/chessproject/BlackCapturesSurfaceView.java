package com.example.chessproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BlackCapturesSurfaceView extends ChessSurfaceView {

    public BlackCapturesSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        //resize images
        whitePawnImage = Bitmap.createScaledBitmap(whitePawnImage,120,100,false);
        whiteKnightImage = Bitmap.createScaledBitmap(whiteKnightImage,120,100,false);
        whiteBishopImage = Bitmap.createScaledBitmap(whiteBishopImage,120,100,false);
    }

    protected void onDraw(Canvas canvas) {
        imagePaint.setColor(Color.WHITE);
        //draw images on the screen
        canvas.drawBitmap(whitePawnImage,-10, 51, imagePaint);
        canvas.drawBitmap(whitePawnImage,30,51,imagePaint);
        canvas.drawBitmap(whiteKnightImage,100,51,imagePaint);
        canvas.drawBitmap(whiteBishopImage,195,51,imagePaint);

    }
}
