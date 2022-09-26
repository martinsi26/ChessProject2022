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
        blackPawnImage = Bitmap.createScaledBitmap(blackPawnImage,120,100,false);
        blackKnightImage = Bitmap.createScaledBitmap(blackKnightImage,120,100,false);
        blackBishopImage = Bitmap.createScaledBitmap(blackBishopImage,120,100,false);
    }

    protected void onDraw(Canvas canvas) {
        imagePaint.setColor(Color.WHITE);
        //draw images on the screen
        canvas.drawBitmap(blackPawnImage,-10, 51, imagePaint);
        canvas.drawBitmap(blackPawnImage,30,51,imagePaint);
        canvas.drawBitmap(blackKnightImage,100,51,imagePaint);
        canvas.drawBitmap(blackBishopImage,195,51,imagePaint);

    }
}
