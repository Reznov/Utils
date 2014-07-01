package com.jbion.utils.console.drawing.grids;

/**
 * An interface used by {@link GridDrawer} to draw grids. Implement this interface to
 * be able to draw your grid with {@link GridDrawer}.
 */
public interface Grid {

    /**
     * Returns the width of each cell of this {@code Grid}. It must correspond to
     * length of the {@code String} returned by {@link #getValueAt(int, int)}.
     *
     * @return the width of each cell of this {@code Grid}.
     * @see #getValueAt(int, int)
     */
    public int getCellWidth();

    /**
     * Returns the width of this {@code Grid}.
     *
     * @return the number of cells per row of this {@code Grid}.
     */
    public int getWidth();

    /**
     * Returns the height of this {@code Grid}.
     *
     * @return the number of cells per column of this {@code Grid}.
     */
    public int getHeight();

    /**
     * Returns the {@code String} representation of the value at the specified
     * position in this {@code Grid}.
     *
     * @param row
     *            The row of the value to get.
     * @param col
     *            The column of the value to get.
     * @return the {@code String} representation of the value at the specified
     *         position.
     */
    public String getValueAt(int row, int col);

}
