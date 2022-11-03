package com.chesschess;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseListener implements EventHandler<MouseEvent> {

    private Board board;

    public MouseListener(Board board) {
        this.board = board;
    }

    /*
     * Handles the listener of the board.
     */
    @Override
    public void handle(MouseEvent event) {
        Pos clickPos = getPos(event.getSceneX(), event.getSceneY());

        Tile tileClickedOn = board.getTile(clickPos.getX(), clickPos.getY(), clickPos.getZ());
        
        // If a piece is clicked on, and the color matches
        if (tileClickedOn.isOccupied() && tileClickedOn.getPiece().isBlack() == board.isBlackPLaying()) {
            board.setPieceSelected(tileClickedOn.getPiece());
            board.highlightLegalMoves();
        }

        // If a empty space is clicked on, or a piece of opposite color is clicked on
        if (!tileClickedOn.isOccupied()
                || (tileClickedOn.isOccupied() && tileClickedOn.getPiece().isBlack() != board.isBlackPLaying())) {
            board.makeMove(tileClickedOn);
        }
    }

    /*
     * Used to convert the coordinates when the mouse clicks on the board, relative
     * to the side of the board.
     */

    private Pos getPos(double x, double y) {
    	if(x > Board.TILE_SIZE * Board.WIDTH * 2) {
    		x -= 20;
    	}
    	else if(x > Board.TILE_SIZE * Board.WIDTH ){
    		x -= 10;
    	}
    	
        int level = (int) (x / (Board.TILE_SIZE * Board.WIDTH));
        int pX = ((int) x % (Board.TILE_SIZE * Board.WIDTH)) / Board.TILE_SIZE;
        int pY = (int) y / Board.TILE_SIZE;
      
        return  new Pos(pX, pY, level);
    }

}
