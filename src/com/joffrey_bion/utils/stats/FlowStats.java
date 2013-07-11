package com.joffrey_bion.utils.stats;

/**
 * This class provide statistics methods such as mean, standard deviation and
 * variance over a series of values received in a flow. The most interesting thing is
 * that the whole series of values is never stored internally, only sums.
 * <p>
 * The way it works is quite simple: the {@link FlowStats} object must be fed with
 * double values through the methods {@link #add(double)} and {@link #remove(double)}
 * , and then the statistics methods can be called.
 * </p>
 * 
 * <pre>
 * {@code 
 * FlowStats stats = new FlowStats();
 * stats.add(1.0);
 * stats.add(4.0);
 * stats.add(4.0);
 * stats.mean(); // returns 3.0
 * stats.remove(4.0)
 * stats.mean(); // returns 2.5
 * }
 * </pre>
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class FlowStats implements Cloneable {

    /** The number of values in this series. */
    private int nbValues;

    /** The sum of the weights of the values in this series. */
    private double totalWeight;

    /** The weighted sum of the added values. */
    private double weightedSum;

    /** The weighted sum of the squares of the added values. */
    private double weightedSquaresSum;

    /**
     * Creates a new {@code FlowStats} object.
     */
    public FlowStats() {
        clear();
    }

    /**
     * Returns a copy of this {@code FlowStats} object. The changes made to this
     * object after a call to {@link #getCopy()} do not affect the returned object.
     * 
     * @return A copy of this object.
     */
    public FlowStats getCopy() {
        try {
            return (FlowStats) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Internal Error: this cloning problem cannot happen");
        }
    }

    /**
     * Return the number of values that were put in this series.
     */
    public int getNbValues() {
        return nbValues;
    }

    /**
     * Return the total weight of the values that were put in this series.
     */
    public double getTotalWeight() {
        return totalWeight;
    }

    /**
     * Resets this {@code FlowStats}, as if all values were removed from the series.
     */
    public void clear() {
        nbValues = 0;
        totalWeight = 0;
        weightedSum = 0;
        weightedSquaresSum = 0;
    }

    /**
     * Adds a value to this series, with a weight of 1.
     * 
     * @param value
     *            The value to be added.
     * @see #add(double, double)
     */
    public void add(double value) {
        add(value, 1);
    }

    /**
     * Removes a value from this series, that was previously added via
     * {@link #add(double)} (with a weight of 1).
     * 
     * @param value
     *            The value to be removed.
     * @see #remove(double, double)
     */
    public void remove(double value) {
        remove(value, 1);
    }

    /**
     * Adds a value to this series.
     * 
     * @param value
     *            The value to be added.
     * @param weight
     *            The weight to give to this value.
     * @see #add(double)
     */
    public void add(double value, double weight) {
        nbValues++;
        totalWeight += weight;
        weightedSum += value * weight;
        weightedSquaresSum += value * value * weight;
    }

    /**
     * Removes a value from this series.
     * 
     * @param value
     *            The value to be removed.
     * @param weight
     *            The weight that was given to the specified value when added.
     * @see #remove(double)
     */
    public void remove(double value, double weight) {
        nbValues--;
        totalWeight -= weight;
        weightedSum -= value * weight;
        weightedSquaresSum -= value * value * weight;
    }

    /**
     * Returns the mean of this series of values.
     */
    public double mean() {
        if (totalWeight == 0) {
            return 0;
        }
        return weightedSum / totalWeight;
    }

    /**
     * Returns the variance of this series of values.
     */
    public double variance() {
        if (totalWeight == 0) {
            return 0;
        }
        double mean = mean();
        return weightedSquaresSum / totalWeight - mean * mean;
    }

    /**
     * Returns the standard deviation of this series of values.
     */
    public double standardDeviation() {
        double var = variance();
        return Math.sqrt(var);
    }

    /**
     * Returns the coefficient of variation of this series of values.
     */
    public double coeffOfVariation() {
        double mean = mean();
        if (mean == 0) {
            if (totalWeight == 0) {
                return 0;
            }
            return Double.POSITIVE_INFINITY;
        }
        return standardDeviation() / mean;
    }
}
