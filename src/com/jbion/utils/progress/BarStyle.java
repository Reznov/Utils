package com.jbion.utils.progress;

import com.jbion.utils.drawing.blocks.BlockChars;
import com.jbion.utils.drawing.grids.BoxChars;

/**
 * An enum representing the different possible styles for a
 * {@link AbstractProgressBar}.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public enum BarStyle {

    /**
     * A progress bar made out of ASCII characters only. Not adapted for the use with
     * a header.
     */
    ASCII('[', '=', '>', ' ', ']'),
    /**
     * A progress bar made out of ASCII characters only. Decent in combination with
     * {@link HeaderStyle#LINE}.
     */
    ASCII2('|', '=', '>', ' ', '|'),
    /**
     * The progress bar is built with block characters. It looks like a full and
     * growing rectangle.
     */
    BLOCK(BlockChars.RIGHT_ONE_EIGHTH_BLOCK, BlockChars.FULL_BLOCK, BlockChars.LEFT_HALF_BLOCK,
            ' ', BlockChars.LEFT_ONE_EIGHTH_BLOCK),
    /**
     * A progress bar in the shape of a horizontal double line, using box drawing
     * characters. Nice in combination with {@link HeaderStyle#ANGLES}.
     */
    DOUBLE(BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_RIGHT_DOUBLE,
            BoxChars.BOX_DRAWINGS_DOUBLE_HORIZONTAL,
            BlockChars.BLACK_RIGHT_POINTING_SMALL_TRIANGLE, ' ',
            BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_LEFT_DOUBLE);

    /**
     * The {@code char} for the bar's left bound. It is located right under
     * {@link HeaderStyle#left}.
     */
    public final char left;
    /**
     * The {@code char} for the blocks representing the part that is done.
     */
    public final char done;
    /**
     * The {@code char} for the current last block of the progress.
     */
    public final char curr;
    /**
     * The {@code char} for the blocks representing the part left to do.
     */
    public final char todo;
    /**
     * The {@code char} for the bar's right bound.It is located right under
     * {@link HeaderStyle#right}.
     */
    public final char right;

    private BarStyle(char leftBound, char done, char curr, char todo, char rightBound) {
        this.left = leftBound;
        this.done = done;
        this.curr = curr;
        this.todo = todo;
        this.right = rightBound;
    }
}
