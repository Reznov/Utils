package com.jbion.utils.progress;

import com.jbion.utils.drawing.Drawing;

/**
 * A progress bar that is redrawn at each update. It does not need a header to show
 * the user where the bar stops because the end of the bar can be drawn even if the
 * progress is not complete.
 * <p>
 * The bar can be redrawn in 2 ways (corresponding to 2 {@link ReDrawMode}s):
 * </p>
 * <ul>
 * <li>{@link ReDrawMode#ERASE}: the previous progress bar is erased with backspace
 * or carriage return characters.</li>
 * <li> {@link ReDrawMode#NEW_LINE}: prints each new update of the progress bar on a
 * new line. Useful to redraw the bar even in Eclipse console, where
 * {@link ReDrawMode#ERASE} mode can't be used.</li>
 * </ul>
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class ReDrawnProgressBar extends AbstractProgressBar {

    private static final boolean TEST_CR = false;

    /**
     * The way to redraw the progress bar.
     */
    public static enum ReDrawMode {
        /**
         * Erase the previous progress bar with backspace or carriage return
         * characters.
         */
        ERASE,
        /**
         * Prints each new update of the progress bar on a new line. Useful to redraw
         * the bar even in Eclipse console, where {@link #ERASE} mode can't be used.
         */
        NEW_LINE;
    }

    private ReDrawMode mode;

    /**
     * Creates a new {@link ReDrawnProgressBar}.
     * 
     * @param total
     *            The number of elements this {@code ProgressBar} represents.
     * @param length
     *            The number of characters used to display this
     *            {@code AbstractProgressBar} when it is complete.
     * @param mode
     *            The way to redraw this progress bar.
     */
    public ReDrawnProgressBar(int total, int length, ReDrawMode mode) {
        super(total, length);
        this.mode = mode;
    }

    /**
     * Prints the whole bar representing the specified progress.
     * 
     * @param progress
     *            The number of blocks of the bar that represents the progress done.
     */
    protected void printWholeBar(int progress) {
        printStream.print(barStyle.left);
        for (int i = 0; i < length; i++) {
            if (i < progress || (progress == length && i == progress)) {
                printStream.print(barStyle.done);
            } else if (i == progress) {
                printStream.print(barStyle.curr);
            } else {
                printStream.print(barStyle.todo);
            }
        }
        printStream.print(barStyle.right);
    }

    /**
     * Prints backspace characters to erase the last progress bar. Does not work
     * properly in Eclipse.
     */
    protected void eraseWholeBar() {
        if (TEST_CR) {
            printStream.print('\r');
        } else {
            printStream.print("\b");
            printStream.print(Drawing.repeat("\b", length));
            printStream.print("\b");
        }
    }

    @Override
    public void begin() {
        if (!(mode == ReDrawMode.NEW_LINE)) {
            printHeader();
        }
    }

    @Override
    public void end() {
        // nothing to do here, because the bar is fully drawn already
    }

    @Override
    protected void updateProgress(int progress) {
        switch (mode) {
        case ERASE:
            eraseWholeBar();
            printWholeBar(progress);
            break;
        case NEW_LINE:
            printWholeBar(progress);
            printStream.println();
        }
    }

}
