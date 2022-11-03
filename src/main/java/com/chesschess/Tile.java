package com.chesschess;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Piece piece;
    private boolean isBlack;
    private Pos pos;

    private static final Color WHITE = Color.PERU;
    private static final Color BLACK = Color.SADDLEBROWN;
    public static final Color EMPTY_HIGHLIGHT = Color.PEACHPUFF;
    public static final Color ENEMY_HIGHLIGHT = Color.LIGHTSALMON;
    public static final Color CHECK = Color.INDIANRED;
    public static final Color CHECK_MATE = Color.FIREBRICK;

    public Tile(boolean white, int x, int y, int z) {
        isBlack = !white;
        setWidth(Board.TILE_SIZE);
        setHeight(Board.TILE_SIZE);

        relocate(x * Board.TILE_SIZE + z * 10, y * Board.TILE_SIZE);

        resetColor();
        pos = new Pos(x % Board.WIDTH, y, z);
    }
    
    /*
     * Sets the color of the tiles.
     */
    public void resetColor() {
        if (isBlack) {
            setFill(BLACK);
        } else {
            setFill(WHITE);
        }
    }

    /*
     * Sets the tile to a highlighted tile.
     */
    public void setHighlight(Color state) {
        setFill(state);
    }

    /*
     * Gets a piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /*
     * Sets a piece.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /*
     * Checks if the tile is occupied.
     */
    public boolean isOccupied() {

        return piece != null;
    }

    /*
     * Gets the position.
     */
    public Pos getPos() {
        return pos;
    }
    public String toString(){
    	return pos.toString();
    }
}
