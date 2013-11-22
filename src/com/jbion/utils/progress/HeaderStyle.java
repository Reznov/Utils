package com.jbion.utils.progress;

import com.jbion.utils.drawing.grids.BoxChars;

/**
 * An enum representing the different possible styles for the header of a
 * {@link AbstractProgressBar}. The header is important for the user to know the
 * length of bar, if it is not fully displayed. This situation happens when the
 * console can't print {@code \b} or {@code \r} characters to remove the older bar.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public enum HeaderStyle {

    /**
     * No header.
     */
    NONE(' ', ' ', ' '),
    /**
     * A thin ASCII line made out of underscore characters.
     */
    LINE(' ', '_', ' '),
    /**
     * A line with angles using box drawing characters. The header looks like the
     * upper half of a thin and long rectangle.
     */
    ANGLES(BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT, BoxChars.BOX_DRAWINGS_LIGHT_HORIZONTAL,
            BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT);

    /**
     * The {@code char} for the header's left bound. It is located right above
     * {@link BarStyle#left}.
     */
    public final char left;
    /**
     * The {@code char} repeated in the header's middle part.
     */
    public final char mid;
    /**
     * The {@code char} for the header's right bound. It is located right above
     * {@link BarStyle#right}.
     */
    public final char right;

    private HeaderStyle(char left, char mid, char right) {
        this.left = left;
        this.mid = mid;
        this.right = right;
    }
}
