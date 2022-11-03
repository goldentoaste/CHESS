package com.chesschess;

import java.util.ArrayList;


import javafx.scene.image.Image;

public class Bishop extends Piece {

    /*
     * Loads the image of the bishop on the board
     */
    public Bishop(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackBishop.png");
        } else {
            image = new Image("file:Pieces/whiteBishop.png");
        }
    }

    /*
     * Returns an arraylist of all the moves that a bishop can perform.
     */
    @Override
    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> moves = new ArrayList<Pos>();

        
        /*
         * 0 -> upperLeft 1 -> lowerLeft 2 -> upperRight 3 -> lowerRight
         */
        for (int z = 0; z < Board.LEVEL; z++) {
        	boolean upperLeft = true;
            boolean lowerLeft = true;
            boolean upperRight = true;
            boolean lowerRight = true;
            for (int i = 1; i < 8; i++) {
                if (pos.getX() + i < 8) {
                    if (pos.getY() - i > -1 && lowerRight) {
                        if (board.getTile(pos.getX() + i, pos.getY() - i,z).isOccupied()) {
                            lowerRight = false;
                            if (board.getTile(pos.getX() + i, pos.getY() - i,z).getPiece().isBlack()
                                    != this.isBlack()) {
                                moves.add(new Pos(pos.getX() + i, pos.getY() - i,z));
                            }
                        } else {
                            moves.add(new Pos(pos.getX() + i, pos.getY() - i,z));
                        }
                    }
                    if (pos.getY() + i < 8 && upperRight) {

                        if (board.getTile(pos.getX() + i, pos.getY() + i,z).isOccupied()) {
                            upperRight = false;
                            if (board.getTile(pos.getX() + i, pos.getY() + i,z).getPiece().isBlack()
                                    != this.isBlack()) {
                                moves.add(new Pos(pos.getX() + i, pos.getY() + i,z));
                            }
                        } else {
                            moves.add(new Pos(pos.getX() + i, pos.getY() + i,z));
                        }
                    }
                }
                if (pos.getX() - i > -1 && lowerLeft) {
                    if (pos.getY() - i > -1) {
                        if (board.getTile(pos.getX() - i, pos.getY() - i,z).isOccupied()) {
                            lowerLeft = false;
                            if (board.getTile(pos.getX() - i, pos.getY() - i,z).getPiece().isBlack()
                                    != this.isBlack()) {
                                moves.add(new Pos(pos.getX() - i, pos.getY() - i,z));
                            }
                        } else {
                            moves.add(new Pos(pos.getX() - i, pos.getY() - i,z));
                        }
                    }
                    if (pos.getY() + i < 8 && upperLeft) {
                        if (board.getTile(pos.getX() - i, pos.getY() + i,z).isOccupied()) {
                            upperLeft = false;
                            if (board.getTile(pos.getX() - i, pos.getY() + i,z).getPiece().isBlack()
                                    != this.isBlack()) {
                                moves.add(new Pos(pos.getX() - i, pos.getY() + i,z));
                            }
                        } else {
                            moves.add(new Pos(pos.getX() - i, pos.getY() + i,z));
                        }
                    }
                }
            }
        }
        return moves;
    }

}