package com.chesschess;

import java.util.ArrayList;
import javafx.scene.image.Image;




public class Queen extends Piece {

    /*
     * Loads the image of the queen.
     */
    public Queen(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackQueen.png");
        } else {
            image = new Image("file:Pieces/whiteQueen.png");
        }
    }

    /*
     * Returns an Arraylist of all the moves that a queen can perform
     */
    @Override
    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> moves = new ArrayList<Pos>();
       
        for (int z = 0; z < Board.LEVEL; z++) {

            for (int x = pos.getX() - 1; x > -1; x--) {
                if (board.getTile(x, pos.getY(), z).isOccupied()) {
                    if (board.getTile(x, pos.getY(), z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(x, pos.getY(), z));
                    }
                    break;
                }
                moves.add(new Pos(x, pos.getY(), z));
            }
            for (int x = pos.getX() + 1; x < 8; x++) {
                if (board.getTile(x, pos.getY(), z).isOccupied()) {
                    if (board.getTile(x, pos.getY(), z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(x, pos.getY(), z));
                    }
                    break;
                }
                moves.add(new Pos(x, pos.getY(), z));

            }
            for (int y = pos.getY() - 1; y > -1; y--) {
                if (board.getTile(pos.getX(), y, z).isOccupied()) {
                    if (board.getTile(pos.getX(), y, z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(pos.getX(), y, z));
                    }
                    break;
                }
                moves.add(new Pos(pos.getX(), y, z));

            }
            for (int y = pos.getY() + 1; y < 8; y++) {
                if (board.getTile(pos.getX(), y, z).isOccupied()) {
                    if (board.getTile(pos.getX(), y, z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(pos.getX(), y, z));
                    }
                    break;
                }
                moves.add(new Pos(pos.getX(), y, z));

            }

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