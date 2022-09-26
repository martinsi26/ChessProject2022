package com.example.chessproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.TextView;

public class ChessSurfaceView extends SurfaceView {

    private Paint colorSquare;
    private TextView movesLog;

    private float top;
    private float left;

    private float bottom;
    private float right;

    private float size;

    protected Bitmap whitePawnImage;
    protected Bitmap whiteKnightImage;
    protected Bitmap whiteBishopImage;
    protected Bitmap whiteRookImage;
    protected Bitmap whiteKingImage;
    protected Bitmap whiteQueenImage;
    protected Bitmap blackPawnImage;
    protected Bitmap blackKnightImage;
    protected Bitmap blackBishopImage;
    protected Bitmap blackKingImage;
    protected Bitmap blackQueenImage;
    protected Paint imagePaint;
    protected Bitmap blackRookImage;

    public ChessSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        size = 115;

        left = 40;
        top = 40;

        right = left + size;
        bottom = top + size;

        colorSquare = new Paint();
        colorSquare.setColor(Color.WHITE);

        whitePawnImage = BitmapFactory.decodeResource(getResources(),R.drawable.wp);
        whiteKnightImage = BitmapFactory.decodeResource(getResources(),R.drawable.wn);
        whiteBishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.wb);
        whiteRookImage = BitmapFactory.decodeResource(getResources(),R.drawable.wr);
        whiteKingImage = BitmapFactory.decodeResource(getResources(),R.drawable.wk);
        whiteQueenImage = BitmapFactory.decodeResource(getResources(),R.drawable.wq);
        whiteBishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.wb);
        blackPawnImage = BitmapFactory.decodeResource(getResources(),R.drawable.bp);
        blackBishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.bb);
        blackKnightImage = BitmapFactory.decodeResource(getResources(),R.drawable.bn);
        blackRookImage = BitmapFactory.decodeResource(getResources(),R.drawable.br);
        blackKingImage = BitmapFactory.decodeResource(getResources(),R.drawable.bk);
        blackQueenImage = BitmapFactory.decodeResource(getResources(),R.drawable.bq);
        blackBishopImage = BitmapFactory.decodeResource(getResources(),R.drawable.bb);
        whitePawnImage = Bitmap.createScaledBitmap(whitePawnImage,120,120,false);
        whiteRookImage = Bitmap.createScaledBitmap(whiteRookImage,120,120,false);
        whiteKnightImage = Bitmap.createScaledBitmap(whiteKnightImage,120,120,false);
        whiteKingImage = Bitmap.createScaledBitmap(whiteKingImage,120,120,false);
        whiteQueenImage = Bitmap.createScaledBitmap(whiteQueenImage,120,120,false);
        whiteBishopImage = Bitmap.createScaledBitmap(whiteBishopImage,120,120,false);
        blackPawnImage = Bitmap.createScaledBitmap(blackPawnImage,120,120,false);
        blackRookImage = Bitmap.createScaledBitmap(blackRookImage,120,120,false);
        blackKnightImage = Bitmap.createScaledBitmap(blackKnightImage,120,120,false);
        blackKingImage = Bitmap.createScaledBitmap(blackKingImage,120,120,false);
        blackQueenImage = Bitmap.createScaledBitmap(blackQueenImage,120,120,false);
        blackBishopImage = Bitmap.createScaledBitmap(blackBishopImage,120,120,false);
        imagePaint = new Paint();
        imagePaint.setColor(Color.WHITE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //board initialization
        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {

                //alternate colors
                if((i%2 == 0 && j%2 != 0) || (j%2 == 0 && i%2 != 0)){
                    colorSquare.setColor(Color.rgb(1, 50, 32));
                } else {
                    colorSquare.setColor(Color.WHITE);
                }

                //draw rectangle
                canvas.drawRect(left + (right - left) * i, top + (bottom - top) * j,
                        right + (right - left) * i, bottom + (bottom - top) * j, colorSquare);
            }
        }
        for(int i = 0; i < 7; i++) {
            if(i == 3) {
                canvas.drawBitmap(whitePawnImage, 385, 265, imagePaint);
            } else if(i == 5) {
                canvas.drawBitmap(whitePawnImage, 615, 380, imagePaint);
            } else {
                canvas.drawBitmap(whitePawnImage, 40 + (i * 115), 150, imagePaint);
            }
        }
        canvas.drawBitmap(whitePawnImage, 845, 380, imagePaint);

        for(int i = 0; i < 7; i++) {
            if(i == 2) {
                canvas.drawBitmap(blackPawnImage, 270, 610, imagePaint);
            } else if(i == 6) {
                canvas.drawBitmap(blackPawnImage, 730, 610, imagePaint);
            } else {
                canvas.drawBitmap(blackPawnImage, 40 + (i * 115), 725, imagePaint);
            }
        }

        //draw Rooks for white and black
        canvas.drawBitmap(whiteRookImage,35,30,imagePaint);
        canvas.drawBitmap(whiteRookImage,840,30,imagePaint);
        canvas.drawBitmap(blackRookImage,35,840,imagePaint);
        canvas.drawBitmap(blackRookImage,840,840,imagePaint);

        //draw knights
        canvas.drawBitmap(whiteKnightImage,493,380,imagePaint);
        canvas.drawBitmap(blackKnightImage,383,490,imagePaint);

        //draw kings and queens
        canvas.drawBitmap(whiteKingImage,500,30,imagePaint);
        canvas.drawBitmap(blackKingImage,500,840,imagePaint);
        canvas.drawBitmap(whiteQueenImage,380,30,imagePaint);
        canvas.drawBitmap(blackQueenImage,380,840,imagePaint);

        //draw bishops
        canvas.drawBitmap(whiteBishopImage,840,150,imagePaint);
        canvas.drawBitmap(blackBishopImage,265,725,imagePaint);
    }

    public void displayMovesLog(){
        //read in moves that the user and computer have played
        //append it to movesLog
        movesLog.setTextSize(20);
        movesLog.setText(
        "1. d3 Nf6\n2. e4 e5\n3. Nf3 d6\n4. Be3 Nc6\n5.Be2 Bg4\n6. Nc3 Bxf3\n7. Qd2 Qd7\n8. O-O-O O-O-O\n9. Rf1 Bh5\n10. g3 Bxe2\n");
    }

    public void setMovesLog(TextView view) {movesLog = view;}
}
