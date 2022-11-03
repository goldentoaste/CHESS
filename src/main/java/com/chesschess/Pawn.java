package com.chesschess;

import java.util.ArrayList;
import javafx.scene.image.Image;


public class Pawn extends Piece {

    private boolean firstMove;

    /*
     * Loads the pawn image.
     */
    public Pawn(Board board, boolean isBlack, Pos pos) {
        super(board, isBlack, pos);
        if (isBlack) {
            image = new Image("file:Pieces/blackPawn.png");
        } else {
            image = new Image("file:Pieces/whitePawn.png");
        }

        // When a pawn is initialized, its first move differs from the other
        // moves it can perform.
        firstMove = true;
    }

    /*
     * Returns an Arraylist of all valid moves a pawn can perform.
     */
    @Override
    public ArrayList<Pos> getMoves() {
        int direction = isBlack() ? 1 : -1;

        ArrayList<Pos> moves = new ArrayList<>();
        // Won't go out of bound if promotion is in place
        for (int z = 0; z < Board.LEVEL; z++) {
            if (!board.getTile(pos.getX(), pos.getY() + direction, z).isOccupied() && Math.abs(pos.getZ() - z) < 2 ) {
                moves.add(new Pos(pos.getX(), pos.getY() + direction, z));
            }
            if (firstMove && !board.getTile(pos.getX(), pos.getY() + (direction * 2), z).isOccupied()
                    && !board.getTile(pos.getX(), pos.getY() + direction, z).isOccupied()
                    && (z == 0 || z == 2)) {
                moves.add(new Pos(pos.getX(), pos.getY() + (direction * 2), z));
              
            }

            // Checks if enemy exist on diagonal
            if (pos.getX() + 1 < 8) {
                if (board.getTile(pos.getX() + 1, pos.getY() + direction, z).isOccupied() 
                        && Math.abs(pos.getZ() - z) < 2) {
                    if (board.getTile(pos.getX() + 1, pos.getY() + direction, z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(pos.getX() + 1, pos.getY() + direction, z));
                    }
                }
            }
            if (pos.getX() - 1 > -1) {
                if (board.getTile(pos.getX() - 1, pos.getY() + direction,z).isOccupied()
                        && Math.abs(pos.getZ() - z) < 2) {
                    if (board.getTile(pos.getX() - 1, pos.getY() + direction,z).getPiece().isBlack() != this.isBlack()) {
                        moves.add(new Pos(pos.getX() - 1, pos.getY() + direction,z));
                    }
                }
            }
        }
        return moves;
    }

    /*
     * Sets the first move to false once a pawn has performed its first move.
     */
    public void makeFirstMove() {
        firstMove = false;
    }

}