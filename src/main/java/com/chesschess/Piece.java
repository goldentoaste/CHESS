package com.chesschess;

import java.util.ArrayList;
import javafx.scene.image.Image;

public abstract class Piece {

    protected Board board;
    protected Image image;

    public Piece(Board board, boolean isBlack, Pos pos) {
        this.board = board;
        this.isBlack = isBlack;
        this.pos = pos;
    }

    private boolean isBlack;

    /*
     * Boolean to differentiate between the players.
     */
    public boolean isBlack() {
        return isBlack;
    }

    public String type;

    protected Pos pos;

    /*
     * Gets the position.
     */
    public Pos getPos() {
        return pos;
    }

    /*
     * Sets the position.
     */
    public void setPos(Pos pos) {
        this.pos = pos;
    }

    /*
     * Gets the specified image.
     */
    public Image getImage() {
        return image;
    }

    public abstract ArrayList<Pos> getMoves();

}
