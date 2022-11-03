package com.chesschess;

import java.util.ArrayList;



import javafx.scene.image.Image;

public class King extends Piece {

    /*
     * Loads the image of the king on the board.
     */
    public King(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackKing.png");
        } else {
            image = new Image("file:Pieces/whiteKing.png");
        }
    }

    /*
     * Returns an ArrayList of all the moves that a King can perform.
     * 
     * There isn't a lot, if you ask me.
     */
    @Override
    public ArrayList<Pos> getMoves() {

        ArrayList<Pos> moves = new ArrayList<>();

        for (int z = 0; z < Board.LEVEL; z++)
            for (int x = Math.max(pos.getX() - 1, 0); x < Math.min(pos.getX() + 2, 8); x++) {
                for (int y = Math.max(pos.getY() - 1, 0); y < Math.min(pos.getY() + 2, 8); y++) {
                    if((pos.getX() != x || pos.getY() != y)) {
                    	if (board.getTile(x, y,z).isOccupied()) {
                            if (board.getTile(x, y,z).getPiece().isBlack() != this.isBlack()) {
                                moves.add(new Pos(x, y,z));
                            }
                        } else {
                            moves.add(new Pos(x, y,z));
                        }
                    }
                }
            }
            return moves;
        }
}

