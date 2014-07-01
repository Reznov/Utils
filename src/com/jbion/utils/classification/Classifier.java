package com.jbion.utils.classification;

import java.util.LinkedList;

/**
 * A simple classifier that allows to decide a level (category) based on a
 * {@link Double} value. It can be used in any problem where some levels are
 * separated by numeric thresholds, and where the first level starts at value 0 and
 * the last has no upper bound.
 *
 * @param <Level>
 *            The type (java class) of the classification classes.
 */
public class Classifier<Level> {

    private final LinkedList<CutPoint> cutPoints;
    private final Level[] levels;

    /**
     * A little helper class to specify a level and its upper limit.
     */
    private class CutPoint {
        /** The name of the level of activity corresponding to this CutPoint. */
        public Level level;
        /** The upper limit for the CPM before going to the next level. */
        public double upperLimit;

        public CutPoint(Level level, double upperLimit) {
            this.level = level;
            this.upperLimit = upperLimit;
        }
    }

    /**
     * Creates a set of cut points that separate different levels by different
     * thresholds.
     *
     * @param levels
     *            The different possible levels in this classifier.
     * @param thresholds
     *            The thresholds to decide between the levels. For all {@code i},
     *            {@code threshold[i]} is the limit between level {@code levels[i]}
     *            (just below the threshold) and {@code levels[i+1]} (just above the
     *            threshold). A value equal to {@code threshold[i]} is classified as
     *            {@code levels[i]}.
     */
    public Classifier(Level[] levels, double[] thresholds) {
        if (levels.length != thresholds.length + 1) {
            throw new IllegalArgumentException("There must be n-1 values to separate n labels.");
        }
        this.levels = levels;
        this.cutPoints = new LinkedList<>();
        for (int i = 0; i < thresholds.length; i++) {
            this.cutPoints.add(new CutPoint(levels[i], thresholds[i]));
        }
        this.cutPoints.add(new CutPoint(levels[levels.length - 1], Double.MAX_VALUE));
    }

    /**
     * Determines the level corresponding to the specified value.
     *
     * @param value
     *            The value to convert into a level.
     * @return the level corresponding to the specified value.
     */
    public Level valueToLevel(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("The value must be positive");
        }
        for (final CutPoint cutPoint : cutPoints) {
            if (value <= cutPoint.upperLimit) {
                return cutPoint.level;
            }
        }
        throw new RuntimeException("Internal error: no level matched the value " + value + ".");
    }

    /**
     * Return the possible levels of classification.
     *
     * @return the possible levels of classification.
     */
    public Level[] getLevels() {
        return levels;
    }
}
