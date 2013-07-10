package com.joffrey_bion.utils.stats;

import java.util.Arrays;


public class StatsLineSkipSome {

    private StatsLine statsLine;
    private int nbSkippedCols;

    public StatsLineSkipSome(int nbOfColumns, int nbSkippedCols) {
        statsLine = new StatsLine(nbOfColumns - nbSkippedCols);
        this.nbSkippedCols = nbSkippedCols;
    }

    public void add(String[] line) {
        statsLine.add(Arrays.copyOfRange(line, nbSkippedCols, line.length));
    }

    public void remove(String[] line) {
        statsLine.remove(Arrays.copyOfRange(line, nbSkippedCols, line.length));
    }

    public int getNbColumns() {
        return nbSkippedCols + statsLine.getNbColumns();
    }

    public void clear() {
        statsLine.clear();
    }

    public String[] toStringArray(String[] placeHolder) {
        if (placeHolder.length != nbSkippedCols)
            throw new RuntimeException("wrong size " + placeHolder.length
                    + "for placeholder (expected " + nbSkippedCols + ")");
        String[] stats = statsLine.toStringArray();
        String[] output = Arrays.copyOf(placeHolder, placeHolder.length + stats.length);
        System.arraycopy(stats, 0, output, placeHolder.length, stats.length);
        return output;
    }
}
