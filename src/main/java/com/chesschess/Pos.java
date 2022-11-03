package com.chesschess;

public class Pos {
    private int x;
    private int y;
    private int z;

    public Pos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * Gets the x value.
     */
    public int getX() {
        return x;
    }

    /*
     * Sets the x value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * Gets the y value.
     */
    public int getY() {
        return y;
    }

    /*
     * Sets the y value.
     */
    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean equal(Pos other) {
        return other.x == this.x && other.y == this.y && other.z == this.z;
    }
    public String toString() 
    {
    	return String.format("(%d, %d) on board %d", x, y, z);
    }
}