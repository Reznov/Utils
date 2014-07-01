package com.jbion.utils.console.drawing.progress;

import java.io.PrintStream;

import com.jbion.utils.console.drawing.Drawing;

/**
 * A base class to build a console progress bar. The bar may be displayed via any
 * {@link PrintStream}, using the standard output {@link System#out} by default.
 */
public abstract class AbstractProgressBar {

    protected PrintStream printStream;

    protected BarStyle barStyle;
    protected HeaderStyle headerStyle;

    /**
     * The number of elements this {@code AbstractProgressBar} represents.
     */
    protected final int total;
    /**
     * The number of characters used to display this {@code AbstractProgressBar} when
     * it is complete.
     */
    protected final int length;

    /**
     * Creates a new {@link AbstractProgressBar}.
     *
     * @param total
     *            The number of elements this {@code AbstractProgressBar} represents.
     * @param length
     *            The number of characters used to display this
     *            {@code AbstractProgressBar} when it is complete.
     */
    public AbstractProgressBar(int total, int length) {
        this.length = length;
        this.total = total;
        this.barStyle = BarStyle.ASCII;
        this.headerStyle = HeaderStyle.NONE;
        this.printStream = System.out;
    }

    /**
     * @return the current {@link PrintStream} in use.
     */
    public PrintStream getPrintStream() {
        return printStream;
    }

    /**
     * @param printStream
     *            the {@link PrintStream} to use to print this bar
     */
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * @return the current style of this progress bar.
     */
    public BarStyle getBarStyle() {
        return barStyle;
    }

    /**
     * @param barStyle
     *            the style to use to display this progress bar
     */
    public void setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
    }

    /**
     * @return the {@link HeaderStyle} currently used for the header of this progress
     *         bar.
     */
    public HeaderStyle getHeaderStyle() {
        return headerStyle;
    }

    /**
     * @param headerStyle
     *            the style to use for the header of this progress bar
     */
    public void setHeaderStyle(HeaderStyle headerStyle) {
        this.headerStyle = headerStyle;
    }

    /**
     * Prints the beginning of this bar. Also prints this bar's header if any.
     */
    public abstract void begin();

    /**
     * Prints the specified progress in this bar.
     *
     * @param current
     *            The number of elements counted as complete.
     */
    public void printProgress(int current) {
        if (current == total || total < length || current % (total / length) == 0) {
            updateProgress(current * length / total);
        }
    }

    /**
     * Prints the end of this bar.
     */
    public abstract void end();

    /**
     * Called to update the bar with the specified progress when there is a change.
     *
     * @param progress
     *            The number of blocks of the bar that represents the progress done.
     */
    protected abstract void updateProgress(int progress);

    /**
     * Prints the header of this progress bar.
     */
    protected void printHeader() {
        if (headerStyle != HeaderStyle.NONE) {
            System.out.print(headerStyle.left);
            System.out.print(Drawing.repeat(headerStyle.mid, length));
            System.out.println(headerStyle.right);
        }
    }
}
