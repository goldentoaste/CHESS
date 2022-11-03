package com.chesschess;

import java.util.ArrayList;
import javafx.scene.image.Image;

/*
 * Knight piece.
 */
public class Knight extends Piece {

    /*
     * Loads the knight on the board.
     */
    public Knight(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackKnight.png");
        } else {
            image = new Image("file:Pieces/whiteKnight.png");
        }
    }

    /*
     * Returns an Arraylist of all the valid positions that a knight can move to.
     */
    @Override
    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> moves = new ArrayList<Pos>();

        for (int z = 0; z < Board.LEVEL; z++) {
            for (int x = max((pos.getX() - 2), 0); x < min(pos.getX() + 3, 8); x++) {
                for (int y = max(pos.getY() - 2, 0); y < min(pos.getY() + 3, 8); y++) {
                    if ((Math.abs(x - pos.getX()) == 2) ^ (Math.abs(y - pos.getY()) == 2) && x != pos.getX()
                            && y != pos.getY()) {
                        if (board.getTile(x, y, z).isOccupied()) {
                            if (board.getTile(x, y, z).getPiece().isBlack() != this.isBlack()) {
                                moves.add(new Pos(x, y, z));
                            }
                        } else {
                            moves.add(new Pos(x, y, z));
                        }
                    }
                }
            }
        }
        return moves;
    }

    int min(int a, int b) {
        return (a < b) ? a : b;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

}