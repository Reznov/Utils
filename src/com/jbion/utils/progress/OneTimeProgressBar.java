package com.jbion.utils.progress;

/**
 * A progress bar that does not need to be erased by backspaces and does not create
 * new lines at each update. For the user to be able to visualize the length of such
 * a bar, it needs to be used with a header.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 * @see HeaderStyle
 * @see AbstractProgressBar#setHeaderStyle(HeaderStyle)
 */
public class OneTimeProgressBar extends AbstractProgressBar {

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
    }

    @Override
    public void begin() {
        printHeader();
        printStream.print(barStyle.left);
    }

    @Override
    protected void updateProgress(int progress) {
        if (progress > 0) {
            printStream.print(barStyle.done);
        }
    }

    @Override
    public void end() {
        printStream.println(barStyle.right);
    }
}
