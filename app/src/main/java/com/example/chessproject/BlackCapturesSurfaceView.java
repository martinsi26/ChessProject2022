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

    private Bitmap pawnImage;
    private Bitmap knightImage;
    private Bitmap bishopImage;

    public BlackCapturesSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        pawnImage = BitmapFactory.decodeResource(getResources(),R.drawable.bp);
        knightImage = BitmapFactory.decodeResource(getResources(),R.drawable.bn);
        bishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.bb);
        pawnImage = Bitmap.createScaledBitmap(pawnImage,120,100,false);
        knightImage = Bitmap.createScaledBitmap(knightImage,120,100,false);
        bishopImage = Bitmap.createScaledBitmap(bishopImage,120,100,false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint imagePaint = new Paint();
        imagePaint.setColor(Color.WHITE);
        canvas.drawBitmap(pawnImage,-10, 51, imagePaint);
        canvas.drawBitmap(pawnImage,30,51,imagePaint);
        canvas.drawBitmap(knightImage,100,51,imagePaint);
        canvas.drawBitmap(bishopImage,195,51,imagePaint);

    }
}
