package com.example.chessproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IntegerRes;

import java.sql.Array;
import java.util.ArrayList;

public class ChessSurfaceView extends SurfaceView implements View.OnTouchListener {

    private Paint colorSquare;
    private TextView movesLog;

    private int count = 0;

    private Paint highlightPaint;
    private Paint dotPaint;

    private Paint textPaint;

    private float top;
    private float left;

    private float bottom;
    private float right;

    private float size;

    //images for chess pieces
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
    protected Bitmap blackRookImage;
    protected Paint imagePaint;

    private int[][] pieces;
    private int[][] board;
    private ArrayList<Integer> movementX = new ArrayList<>();
    private ArrayList<Integer> movementY = new ArrayList<>();
    private int x = 8;
    private int y = 8;
    private ArrayList<Integer> blackCaptures;
    private ArrayList<Integer> whiteCaptures;

    public ChessSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        blackCaptures = new ArrayList<>();
        whiteCaptures = new ArrayList<>();
        board = new int[9][9];
        pieces = new int[8][8];

        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                if(j == 0) {
                    pieces[0][j] = -4;
                    pieces[1][j] = -3;
                    pieces[2][j] = -2;
                    pieces[3][j] = -5;
                    pieces[4][j] = -6;
                    pieces[5][j] = -2;
                    pieces[6][j] = -3;
                    pieces[7][j] = -4;
                } else if(j == 1) {
                    pieces[i][j] = -1;
                } else if(j == 6) {
                    pieces[i][j] = 1;
                } else if(j == 7) {
                    pieces[0][j] = 4;
                    pieces[1][j] = 3;
                    pieces[2][j] = 2;
                    pieces[3][j] = 5;
                    pieces[4][j] = 6;
                    pieces[5][j] = 2;
                    pieces[6][j] = 3;
                    pieces[7][j] = 4;
                } else {
                    pieces[i][j] = 0;
                }
            }
        }

        size = 115;

        left = 40;
        top = 40;

        right = left + size;
        bottom = top + size;

        colorSquare = new Paint();
        colorSquare.setColor(Color.WHITE);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);

        highlightPaint = new Paint();
        highlightPaint.setAlpha(10);
        highlightPaint.setColor(Color.YELLOW);

        dotPaint = new Paint();
        dotPaint.setColor(Color.GRAY);


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
        //** Can this be optimized? it will repeatedly be drawn
        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {

                //alternate colors
                if ((i % 2 == 0 && j % 2 != 0) || (j % 2 == 0 && i % 2 != 0)) {
                    colorSquare.setColor(Color.rgb(1, 50, 32));
                } else {
                    colorSquare.setColor(Color.WHITE);
                }

                //draw rectangle
                canvas.drawRect(left + (right - left) * i, top + (bottom - top) * j,
                        right + (right - left) * i, bottom + (bottom - top) * j, colorSquare);
            }
        }

        //draw the highlight when touched
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 1) {
                    canvas.drawRect(left + (size) * i, top + (size) * j,
                            right + (size) * i, bottom + (size) * j, highlightPaint);
                } else if(board[i][j] == 2) {
                    canvas.drawCircle(left + (size/2) + (size) * i, top + + (size/2) + (size) * j, (right - left)/5, dotPaint);
                }
            }
        }

        //draw all the pieces
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j] == 1) {
                    canvas.drawBitmap(whitePawnImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == 2) {
                    canvas.drawBitmap(whiteBishopImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == 3) {
                    canvas.drawBitmap(whiteKnightImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == 4) {
                    canvas.drawBitmap(whiteRookImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == 5) {
                    canvas.drawBitmap(whiteQueenImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == 6) {
                    canvas.drawBitmap(whiteKingImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                }
                if(pieces[i][j] == -1) {
                    canvas.drawBitmap(blackPawnImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == -2) {
                    canvas.drawBitmap(blackBishopImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == -3) {
                    canvas.drawBitmap(blackKnightImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == -4) {
                    canvas.drawBitmap(blackRookImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == -5) {
                    canvas.drawBitmap(blackQueenImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j] == -6) {
                    canvas.drawBitmap(blackKingImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                }
            }
        }

        /*
        //draw pawns for black
        for(int i = 0; i < 7; i++) {
            if(i == 3) {
                canvas.drawBitmap(blackPawnImage, 385, 265, imagePaint);
            } else if(i == 5) {
                canvas.drawBitmap(blackPawnImage, 615, 380, imagePaint);
            } else {
                canvas.drawBitmap(blackPawnImage, 40 + (i * 115), 150, imagePaint);
            }
        }
        canvas.drawBitmap(blackPawnImage, 845, 380, imagePaint);

        //draw pawns for black
        for(int i = 0; i < 7; i++) {
            if(i == 2) {
                canvas.drawBitmap(whitePawnImage, 270, 610, imagePaint);
            } else if(i == 6) {
                canvas.drawBitmap(whitePawnImage, 730, 610, imagePaint);
            } else {
                canvas.drawBitmap(whitePawnImage, 40 + (i * 115), 725, imagePaint);
            }
        }

        //draw Rooks for white and black
        canvas.drawBitmap(blackRookImage,37,35,imagePaint);
        canvas.drawBitmap(blackRookImage,843,35,imagePaint);
        canvas.drawBitmap(whiteRookImage,37,843,imagePaint);
        canvas.drawBitmap(whiteRookImage,843,843,imagePaint);

        //draw knights
        canvas.drawBitmap(blackKnightImage,495,380,imagePaint);
        canvas.drawBitmap(whiteKnightImage,383,495,imagePaint);

        //draw kings and queens
        canvas.drawBitmap(blackKingImage,500,30,imagePaint);
        canvas.drawBitmap(whiteKingImage,500,840,imagePaint);
        canvas.drawBitmap(blackQueenImage,380,30,imagePaint);
        canvas.drawBitmap(whiteQueenImage,380,840,imagePaint);

        //draw bishops
        canvas.drawBitmap(blackBishopImage,840,150,imagePaint);
        canvas.drawBitmap(whiteBishopImage,265,725,imagePaint);
        */
        //draw letters and numbers
        textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        textPaint.setTextSize(30);
        for(int i = 1; i <= 8; i++) {
            canvas.drawText(String.valueOf(i), 15, 40 + (i * 115), textPaint);
        }
        canvas.drawText("a", 135, 985, textPaint);
        canvas.drawText("b", 250, 985, textPaint);
        canvas.drawText("c", 365, 985, textPaint);
        canvas.drawText("d", 480, 985, textPaint);
        canvas.drawText("e", 595, 985, textPaint);
        canvas.drawText("f", 710, 985, textPaint);
        canvas.drawText("g", 825, 985, textPaint);
        canvas.drawText("h", 940, 985, textPaint);
    }

    public void displayMovesLog(){
        //read in moves that the user and computer have played
        //append it to movesLog
        movesLog.setTextSize(20);
        movesLog.setText(
        "1. d3 Nf6\n2. e4 e5\n3. Nf3 d6\n4. Be3 Nc6\n5.Be2 Bg4\n6. Nc3 Bxf3\n7. Qd2 Qd7\n8. O-O-O O-O-O\n9. Rf1 Bh5\n10. g3 Bxe2\n");
    }

    public void setMovesLog(TextView view) {movesLog = view;}

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if (motionEvent.getX() > 20 + (i * 115) && motionEvent.getX() < 175 + (i * 115)) {
                        if (motionEvent.getY() > 20 + (j * 115) && motionEvent.getY() < 175 + (j * 115)) {
                            for(int index = 0; index < movementX.size(); index++) {
                                if(movementX.get(index) == i && movementY.get(index) == j) {
                                    pieces[i][j] = pieces[x][y];
                                    pieces[x][y] = 0;
                                    board[x][y] = 0;
                                    for(int k = 0; k < movementX.size(); k++) {
                                        board[movementX.get(k)][movementY.get(k)] = 0;
                                    }
                                    movementX.clear();
                                    movementY.clear();
                                    invalidate();
                                    return true;
                                }
                            }
                            if(x != 8 && y != 8) {
                                if (pieces[i][j] == 0) {
                                    board[x][y] = 0;
                                    for (int index = 0; index < movementX.size(); index++) {
                                        board[movementX.get(index)][movementY.get(index)] = 0;
                                    }
                                    x = 8;
                                    y = 8;
                                    movementX.clear();
                                    movementY.clear();
                                    invalidate();
                                    return true;
                                }
                            }
                            x = i;
                            y = j;
                            board[x][y] = 1;
                            if(pieces[x][y] == 1) {
                                if(y == 6) {
                                    movementX.add(x);
                                    movementX.add(x);
                                    movementY.add(y - 1);
                                    movementY.add(y - 2);
                                } else if(y > 0) {
                                    movementX.add(x);
                                    movementY.add(y - 1);
                                }
                            } else if(pieces[x][y] == -1) {
                                if(y == 1) {
                                    movementX.add(x);
                                    movementX.add(x);
                                    movementY.add(y + 1);
                                    movementY.add(y + 2);
                                } else if(y > 0) {
                                    movementX.add(x);
                                    movementY.add(y + 1);
                                }
                            }
                            for(int k = 0; k < movementX.size(); k++) {
                                board[movementX.get(k)][movementY.get(k)] = 2;
                            }
                            invalidate();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
