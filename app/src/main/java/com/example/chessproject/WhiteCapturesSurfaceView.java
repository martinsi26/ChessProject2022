package com.example.chessproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class WhiteCapturesSurfaceView extends SurfaceView {

    private Bitmap pawnImage;
    private Bitmap knightImage;
    private Bitmap bishopImage;

    public WhiteCapturesSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        pawnImage = BitmapFactory.decodeResource(getResources(),R.drawable.wp);
        knightImage = BitmapFactory.decodeResource(getResources(),R.drawable.wn);
        bishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.wb);
        pawnImage = Bitmap.createScaledBitmap(pawnImage,120,100,false);
        knightImage = Bitmap.createScaledBitmap(knightImage,120,100,false);
        bishopImage = Bitmap.createScaledBitmap(bishopImage,120,100,false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint imagePaint = new Paint();
        imagePaint.setColor(Color.BLACK);
        canvas.drawBitmap(pawnImage,-10, 50, imagePaint);
        canvas.drawBitmap(pawnImage,30,50,imagePaint);
        canvas.drawBitmap(knightImage,100,50,imagePaint);
        canvas.drawBitmap(bishopImage,195,50,imagePaint);

    }
}
