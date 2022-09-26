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
    public WhiteCapturesSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        pawnImage = BitmapFactory.decodeResource(getResources(),R.drawable.wp);
        pawnImage = Bitmap.createScaledBitmap(pawnImage,120,100,false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint imagePaint = new Paint();
        imagePaint.setColor(Color.BLACK);
        canvas.drawBitmap(pawnImage,0, 0, imagePaint);
    }
}
