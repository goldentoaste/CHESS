package com.chesschess;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Rook extends Piece {

    /*
     * Loads the image of a rook.
     */
    public Rook(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackRook.png");
        } else {
            image = new Image("file:Pieces/whiteRook.png");
        }
    }

    /*
     * Returns an ArrayList of all the moves that a rook can perform.
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
        }
        return moves;
    }

}