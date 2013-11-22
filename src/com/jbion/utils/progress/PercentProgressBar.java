package com.jbion.utils.progress;

import com.jbion.utils.drawing.Drawing;

/**
 * A extended version of {@link ReDrawnProgressBar} that includes a percentage
 * display.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class PercentProgressBar extends ReDrawnProgressBar {

    /**
     * Creates a new {@link PercentProgressBar}.
     * 
     * @param total
     *            The number of elements this {@code ProgressBar} represents.
     * @param length
     *            The number of characters used to display this
     *            {@code AbstractProgressBar} when it is complete.
     * @param mode
     *            The way to redraw this progress bar.
     */
    public PercentProgressBar(int total, int length, ReDrawMode mode) {
        super(total, length, mode);
    }

    @Override
    protected void eraseWholeBar() {
        super.eraseWholeBar();
        printStream.print(Drawing.repeat("\b", 6)); // 4 digits + % + ' '
    }

    @Override
    protected void printWholeBar(int progress) {
        // add the percentage print to the super method
        printStream.print(String.format("% 4d", progress * 100 / length) + "% ");
        super.printWholeBar(progress);
    }
}
