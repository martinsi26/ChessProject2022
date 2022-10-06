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

    private TextView movesLog;

    //paint variables
    protected Paint imagePaint;
    private Paint colorSquare;
    private Paint highlightPaint;
    private Paint dotPaint;
    private Paint textPaint;

    //variables for creating board
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

    private Piece[][] pieces;
    private int[][] board;
    private ArrayList<Integer> xMovement = new ArrayList<>();
    private ArrayList<Integer> yMovement = new ArrayList<>();
    public ArrayList<Integer> capturedBlack = new ArrayList<>();
    private int x = 8;
    private int y = 8;

    public ChessSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        pieces = new Piece[8][8];
        board = new int[8][8];

        size = 115;
        left = top = 40;
        right = left + size;
        bottom = top + size;

        colorSquare = new Paint();
        colorSquare.setColor(Color.WHITE);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        highlightPaint = new Paint();
        highlightPaint.setColor(Color.YELLOW);
        dotPaint = new Paint();
        dotPaint.setColor(Color.LTGRAY);
        imagePaint = new Paint();
        imagePaint.setColor(Color.WHITE);

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

        placePieces();
    }

    public void placePieces() {
        // Setting the initial position of all of the pieces
        for (int row = 0; row < pieces.length; row++) {
            for (int col = 0; col < pieces[row].length; col++) {
                if (col == 0) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.BLACK);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.BLACK);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                } else if (col == 1) {
                    pieces[row][1] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.BLACK);
                } else if (col == 6) {
                    pieces[row][6] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.WHITE);
                } else if (col == 7) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.WHITE);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.WHITE);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                } else {
                    pieces[row][col] = new Piece(Piece.PieceType.EMPTY, Piece.ColorType.EMPTY);
                }
            }
        }
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
                if(pieces[i][j].getPieceType() == Piece.PieceType.PAWN && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whitePawnImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.BISHOP && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whiteBishopImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.KNIGHT && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whiteKnightImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.ROOK && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whiteRookImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.QUEEN && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whiteQueenImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.KING && pieces[i][j].getPieceColor() == Piece.ColorType.WHITE) {
                    canvas.drawBitmap(whiteKingImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                }
                if(pieces[i][j].getPieceType() == Piece.PieceType.PAWN && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackPawnImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.BISHOP && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackBishopImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.KNIGHT && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackKnightImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.ROOK && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackRookImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.QUEEN && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackQueenImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                } else if(pieces[i][j].getPieceType() == Piece.PieceType.KING && pieces[i][j].getPieceColor() == Piece.ColorType.BLACK) {
                    canvas.drawBitmap(blackKingImage, 40 + (i * 115), 40 + (j * 115), imagePaint);
                }
            }
        }

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

    //general method that determines what piece should move by their corresponding number
    public void moveWhitePieces() {
        if(pieces[x][y].getPieceType() == Piece.PieceType.PAWN) {
            movePawn();
        } else if(pieces[x][y].getPieceType() == Piece.PieceType.BISHOP) {
            moveBishop();
        } else if (pieces[x][y].getPieceType() == Piece.PieceType.KNIGHT) {
            moveKnight();
        } else if(pieces[x][y].getPieceType() == Piece.PieceType.ROOK) {
            moveRook();
        } else if (pieces[x][y].getPieceType() == Piece.PieceType.QUEEN) {
            moveQueen();
        } else if(pieces[x][y].getPieceType() == Piece.PieceType.KING) {
            moveKing();
        }
    }

    //general diagonal movement for bishops and queen
    public void generalDiagonalMove() {
        boolean stopUpLeft = false;
        boolean stopUpRight = false;
        boolean stopDownLeft = false;
        boolean stopDownRight = false;

        for (int i = 1; i < 8; i++) {
            if (y - i >= 0 && x - i >= 0) {
                if (pieces[x - i][y - i].getPieceColor() == Piece.ColorType.BLACK && !stopUpLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y - i);
                    stopUpLeft = true;
                }
                if (pieces[x - i][y - i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopUpLeft = true;
                }
                if (!stopUpLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y - i);
                }
            }
            if (y - i >= 0 && x + i < 8) {
                if (pieces[x + i][y - i].getPieceColor() == Piece.ColorType.BLACK && !stopUpRight) {
                    xMovement.add(x + i);
                    yMovement.add(y - i);
                    stopUpRight = true;
                }
                if (pieces[x + i][y - i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopUpRight = true;
                }
                if (!stopUpRight) {
                    xMovement.add(x + i);
                    yMovement.add(y - i);
                }
            }
            if (y + i < 8 && x - i >= 0) {
                if (pieces[x - i][y + i].getPieceColor() == Piece.ColorType.BLACK && !stopDownLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y + i);
                    stopDownLeft = true;
                }
                if (pieces[x - i][y + i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopDownLeft = true;
                }
                if (!stopDownLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y + i);
                }
            }
            if (y + i < 8 && x + i < 8) {
                if (pieces[x + i][y + i].getPieceColor() == Piece.ColorType.BLACK && !stopDownRight) {
                    xMovement.add(x + i);
                    yMovement.add(y + i);
                    stopDownRight = true;
                }
                if (pieces[x + i][y + i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopDownRight = true;
                }
                if (!stopDownRight) {
                    xMovement.add(x + i);
                    yMovement.add(y + i);
                }
            }
        }
    }

    //general movement for rooks and queen
    public void generalSideMove() {
        boolean stopLeft = false;
        boolean stopRight = false;
        boolean stopUp = false;
        boolean stopDown = false;

        for (int i = 1; i < 8; i++) {
            if (x - i >= 0) {
                if (pieces[x - i][y].getPieceColor() == Piece.ColorType.BLACK && !stopLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y);
                    stopLeft = true;
                }
                if (pieces[x - i][y].getPieceColor() == Piece.ColorType.WHITE) {
                    stopLeft = true;
                }
                if (!stopLeft) {
                    xMovement.add(x - i);
                    yMovement.add(y);
                }
            }
            if (y - i >= 0) {
                if (pieces[x][y - i].getPieceColor() == Piece.ColorType.BLACK && !stopUp) {
                    xMovement.add(x);
                    yMovement.add(y - i);
                    stopUp = true;
                }
                if (pieces[x][y - i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopUp = true;
                }
                if (!stopUp) {
                    xMovement.add(x);
                    yMovement.add(y - i);
                }
            }
            if (y + i < 8) {
                if (pieces[x][y + i].getPieceColor() == Piece.ColorType.BLACK && !stopDown) {
                    xMovement.add(x);
                    yMovement.add(y + i);
                    stopDown = true;
                }
                if (pieces[x][y + i].getPieceColor() == Piece.ColorType.WHITE) {
                    stopDown = true;
                }
                if (!stopDown) {
                    xMovement.add(x);
                    yMovement.add(y + i);
                }
            }
            if (x + i < 8) {
                if (pieces[x + i][y].getPieceColor() == Piece.ColorType.BLACK && !stopRight) {
                    xMovement.add(x + i);
                    yMovement.add(y);
                    stopRight = true;
                }
                if (pieces[x + i][y].getPieceColor() == Piece.ColorType.WHITE) {
                    stopRight = true;
                }
                if (!stopRight) {
                    xMovement.add(x + i);
                    yMovement.add(y);
                }
            }
        }
    }

    //movement for the pawn
    public void movePawn() {
        if (y == 6) {
            if(pieces[x][y - 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x);
                yMovement.add(y - 1);
            }
            if(pieces[x][y - 2].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x);
                yMovement.add(y - 2);
            }
        } else if (y > 0) {
            if(pieces[x][y - 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x);
                yMovement.add(y - 1);
            }
        }
        if (x > 0 && y > 0) {
            if (pieces[x - 1][y - 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x - 1);
                yMovement.add(y - 1);
            }
        }
        if (x < 7 && y > 0) {
            if (pieces[x + 1][y - 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x + 1);
                yMovement.add(y - 1);
            }
        }
    }

    //movement for the bishop
    public void moveBishop() {
        generalDiagonalMove();
    }

    //movement for the knight
    public void moveKnight() {
        if (x > 1 && y > 0) {
            if (pieces[x - 2][y - 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x - 2);
                yMovement.add(y - 1);
            }
            if (pieces[x - 2][y - 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x - 2);
                yMovement.add(y - 1);
            }
        }
        if (x > 0 && y > 1) {
            if (pieces[x - 1][y - 2].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x - 1);
                yMovement.add(y - 2);
            }
            if (pieces[x - 1][y - 2].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x - 1);
                yMovement.add(y - 2);
            }
        }
        if (x < 6 && y < 7) {
            if (pieces[x + 2][y + 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x + 2);
                yMovement.add(y + 1);
            }
            if (pieces[x + 2][y + 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x + 2);
                yMovement.add(y + 1);
            }
        }
        if (x < 7 && y < 6) {
            if (pieces[x + 1][y + 2].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x + 1);
                yMovement.add(y + 2);
            }
            if (pieces[x + 1][y + 2].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x + 1);
                yMovement.add(y + 2);
            }
        }
        if (x > 1 && y < 7) {
            if (pieces[x - 2][y + 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x - 2);
                yMovement.add(y + 1);
            }
            if (pieces[x - 2][y + 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x - 2);
                yMovement.add(y + 1);
            }
        }
        if (x > 0 && y < 6) {
            if (pieces[x - 1][y + 2].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x - 1);
                yMovement.add(y + 2);
            }
            if (pieces[x - 1][y + 2].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x - 1);
                yMovement.add(y + 2);
            }
        }
        if (x < 6 && y > 0) {
            if (pieces[x + 2][y - 1].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x + 2);
                yMovement.add(y - 1);
            }
            if (pieces[x + 2][y - 1].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x + 2);
                yMovement.add(y - 1);
            }
        }
        if (x < 7 && y > 1) {
            if (pieces[x + 1][y - 2].getPieceColor() == Piece.ColorType.EMPTY) {
                xMovement.add(x + 1);
                yMovement.add(y - 2);
            }
            if (pieces[x + 1][y - 2].getPieceColor() == Piece.ColorType.BLACK) {
                xMovement.add(x + 1);
                yMovement.add(y - 2);
            }
        }
    }

    //movement for the rook
    public void moveRook() {
        generalSideMove();
    }

    //movement for the queen
    public void moveQueen() {
        generalDiagonalMove();
        generalSideMove();
    }

    //movement for the king
    public void moveKing() {

    }

    //movement for the black pieces (computer)
    public void moveBlackPieces() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if (motionEvent.getX() > 20 + (i * 115) && motionEvent.getX() < 175 + (i * 115)) {
                        if (motionEvent.getY() > 20 + (j * 115) && motionEvent.getY() < 175 + (j * 115)) {
                            for(int index = 0; index < xMovement.size(); index++) {
                                if(xMovement.get(index) == i && yMovement.get(index) == j) {
                                    //capturedBlack.add(pieces[i][j]);
                                    pieces[i][j] = pieces[x][y];
                                    pieces[x][y] = new Piece(Piece.PieceType.EMPTY, Piece.ColorType.EMPTY);
                                    board[x][y] = 0;
                                    for(int k = 0; k < xMovement.size(); k++) {
                                        board[xMovement.get(k)][yMovement.get(k)] = 0;
                                    }
                                    xMovement.clear();
                                    yMovement.clear();
                                    invalidate();
                                    return true;
                                }
                            }
                            if(x != 8 && y != 8) {
                                board[x][y] = 0;
                                for (int index = 0; index < xMovement.size(); index++) {
                                    board[xMovement.get(index)][yMovement.get(index)] = 0;
                                }
                                x = 8;
                                y = 8;
                                xMovement.clear();
                                yMovement.clear();
                                invalidate();
                                if (pieces[i][j].getPieceColor() == Piece.ColorType.BLACK || pieces[i][j].getPieceColor() == Piece.ColorType.EMPTY) {
                                    return true;
                                }
                            }
                            if(pieces[i][j].getPieceColor() == Piece.ColorType.BLACK || pieces[i][j].getPieceColor() == Piece.ColorType.EMPTY) {
                                return true;
                            }
                            x = i;
                            y = j;
                            board[x][y] = 1;

                            moveWhitePieces();
                            moveBlackPieces();

                            for(int index = 0; index < xMovement.size(); index++) {
                                board[xMovement.get(index)][yMovement.get(index)] = 2;
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
