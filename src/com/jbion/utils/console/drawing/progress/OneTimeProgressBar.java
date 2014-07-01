package com.jbion.utils.console.drawing.progress;

/**
 * A progress bar that does not need to be erased by backspaces and does not create
 * new lines at each update. For the user to be able to visualize the length of such
 * a bar, it needs to be used with a header.
 *
 * @see HeaderStyle
 * @see AbstractProgressBar#setHeaderStyle(HeaderStyle)
 */
public class OneTimeProgressBar extends AbstractProgressBar {

    private int currentLength;

    /**
     * Creates a new {@link OneTimeProgressBar} with a default header.
     *
     * @param total
     *            The number of elements this {@code ProgressBar} represents.
     * @param length
     *            The number of characters used to display this
     *            {@code AbstractProgressBar} when it is complete.
     */
    public OneTimeProgressBar(int total, int length) {
        super(total, length);
        setBarStyle(BarStyle.ASCII2);
        setHeaderStyle(HeaderStyle.LINE);
        currentLength = 0;
    }

    @Override
    public void begin() {
        printHeader();
        printStream.print(barStyle.left);
    }

    @Override
    protected void updateProgress(int progress) {
        while (currentLength < progress) {
            printStream.print(barStyle.done);
            currentLength++;
        }
    }

    @Override
    public void end() {
        printStream.println(barStyle.right);
    }
}
