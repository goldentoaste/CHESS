package com.chesschess;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Board {

    /*
     * GUI related instance variables
     */
    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final int LEVEL = 3;
    private Group tileGroup = new Group();
    private Group tileGroup1 = new Group();
    private Group tileGroup2 = new Group();
    private Canvas canvas = new Canvas(TILE_SIZE * WIDTH * LEVEL + 20, TILE_SIZE * HEIGHT);

    /*
     * Game related instance variables
     */
    private Piece pieceSelected;
    private boolean isBlackPlaying;

    private ArrayList<Piece> blackGraveyard;
    private ArrayList<Piece> whiteGraveyard;

    private Tile[][][] board = new Tile[WIDTH][HEIGHT][LEVEL];

    public Board() {
        pieceSelected = null;
        isBlackPlaying = false;

        blackGraveyard = new ArrayList<Piece>();
        whiteGraveyard = new ArrayList<Piece>();
    }

    /*
     * Gets the selected piece
     */
    public Piece getPieceSelected() {
        return pieceSelected;
    }

    /*
     * Sets the piece selected
     */
    public void setPieceSelected(Piece piece) {
        pieceSelected = piece;
    }

    /*
     * Gets the tile
     */
    public Tile getTile(int x, int y, int level) {
        return board[x][y][level];
    }

    /*
     * Returns a boolean that indicates whether or not the black player is playing
     */
    public boolean isBlackPLaying() {
        return isBlackPlaying;
    }

    /*
     * Makes the move
     */
    public void makeMove(Tile target) {

        if (pieceSelected != null) {
        
            if (isLegalMove(target)) {

                Pos selectedPos = pieceSelected.getPos();
                pieceSelected.pos = target.getPos();
                ///////////// Pawn exception handling/////////////////////////////
                if (pieceSelected.getClass().equals(Pawn.class)) {
                    // if pawn is make the first move, run p.firstMove to change the first move flag
                    ((Pawn) (pieceSelected)).makeFirstMove();
                    if (target.getPos().getY() == 0 || target.getPos().getY() == 7) {

                        pieceSelected = new Queen(this, pieceSelected.isBlack(), new Pos(pieceSelected.getPos().getX(),
                                pieceSelected.getPos().getY(), pieceSelected.getPos().getZ()));
                    }
                }

                if (target.isOccupied()) {

                    // if there is a piece at target, assumed to be oppisite color by
                    // mouseListener's implementation
                    if (target.getPiece().isBlack()) {
                        blackGraveyard.add(target.getPiece());
                    } else {
                        whiteGraveyard.add(target.getPiece());
                    }

                    target.setPiece(pieceSelected);

                 

                } else {
                    // if target is a empty space and legal
                    target.setPiece(pieceSelected);

                  
                }
                board[selectedPos.getX()][selectedPos.getY()][selectedPos.getZ()].setPiece(null);
                pieceSelected = null;
                resetColor();
                draw();
                
              
                if (isBlackPlaying) {
                    isBlackPlaying = false;
                } else {
                    isBlackPlaying = true;
                }
                System.out.println(target);
            }
            
        }
        
    }

    /*
     * Resets the color
     */
    private void resetColor() {
        for (int z = 0; z < LEVEL; z++) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    board[x][y][z].resetColor();
                }
            }
        }
    }

    /*
     * Defines whether the move is a legal move
     */
    private boolean isLegalMove(Tile target) {
        // check if selected piece can move to target
        boolean containing = false;
        
        if (pieceSelected != null) {
            ArrayList<Pos> validMoves = pieceSelected.getMoves();
            for (Pos p : validMoves) {
           
                if (p.equal(target.getPos())) {
                    containing = true;
                }
            }
        }
        return containing;
    }

    /*
     * Highlights all the legal moves a selected piece can make
     */
    public void highlightLegalMoves() {
        resetColor();
        if (pieceSelected != null) {
            ArrayList<Pos> validMoves = pieceSelected.getMoves();
            for (Pos pos : validMoves) {
                Tile current = board[pos.getX()][pos.getY()][pos.getZ()];
                if (current.isOccupied() && current.getPiece().isBlack() != isBlackPlaying) {
                    // If is an enemy piece
                    current.setHighlight(Tile.ENEMY_HIGHLIGHT);
                }
                if (!current.isOccupied()) {
                    current.setHighlight(Tile.EMPTY_HIGHLIGHT);
                }
            }
        }
    }

    /*
     * Draws the pieces on the board
     */
    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, TILE_SIZE * WIDTH * LEVEL + 20, TILE_SIZE * HEIGHT);

        for (int z = 0; z < LEVEL; z++) {
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (board[x][y][z].isOccupied()) {
                        gc.drawImage(board[x][y][z].getPiece().getImage(), x * TILE_SIZE + z * TILE_SIZE * WIDTH + z * 10,
                                y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }
    }

    /*
     * Creates the board and the pieces
     */
    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize((WIDTH * TILE_SIZE * LEVEL + 20), HEIGHT * TILE_SIZE); // changed
        root.getChildren().addAll(tileGroup, tileGroup1, tileGroup2,canvas);

        for (int z = 0; z < LEVEL; z++) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Tile tile = new Tile((x + y) % 2 == 0, x + z * WIDTH, y, z);
                    board[x][y][z] = tile;

                    switch (z) {
                        case 0:
                            tileGroup.getChildren().add(tile);
                            break;
                        case 1:
                            tileGroup1.getChildren().add(tile);
                            break;
                        case 2:
                            tileGroup2.getChildren().add(tile);
                            break;
                        default:
                            break;
                    }

                    Piece piece = null;

                    if (z == 0) {
                        if (y == 1) {
                            // black pawn
                            piece = new Pawn(this, true, new Pos(x, y, z));
                        }
                        if (y == 0) {
                            if (x == 0 || x == 7) {
                                piece = new Rook(this, true, new Pos(x, y, z));
                            }
                            if (x == 1 || x == 6) {
                                piece = new Knight(this, true, new Pos(x, y, z));
                            }
                            if (x == 2 || x == 5) {
                                piece = new Bishop(this, true, new Pos(x, y, z));
                            }
                            if (x == 3) {
                                piece = new Queen(this, true, new Pos(x, y, z));
                            }
                            if (x == 4) {
                                piece = new King(this, true, new Pos(x, y, z));
                            }
                        }
                        if (y == 6) {
                            // white pawn
                            piece = new Pawn(this, false, new Pos(x, y, z));
                        }
                        if (y == 7) {
                            if (x == 0 || x == 7) {
                                piece = new Rook(this, false, new Pos(x, y, z));
                            }
                            if (x == 1 || x == 6) {
                                piece = new Knight(this, false, new Pos(x, y, z));
                            }
                            if (x == 2 || x == 5) {
                                piece = new Bishop(this, false, new Pos(x, y, z));
                            }
                            if (x == 3) {
                                piece = new Queen(this, false, new Pos(x, y, z));
                            }
                            if (x == 4) {
                                piece = new King(this, false, new Pos(x, y, z));
                            }

                        }

                        board[x][y][z].setPiece(piece);
                    }

                }

            }
        }

        draw();
        return root;

    }

}
