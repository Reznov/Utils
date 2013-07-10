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

    private double totalWeight;
    private double sum; // the sum of the added values
    private double squaresSum; // the sum of the squares of the added values

    /**
     * Creates a new {@code FlowStats} object.
     */
    public FlowStats() {
        clear();
    }

    /**
     * Resets this {@code FlowStats}, as if all values were removed from the series.
     */
    public void clear() {
        totalWeight = 0;
        sum = 0;
        squaresSum = 0;
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
     * Adds a value to this series.
     * <p>
     * The value is actually not stored but used in a pre-computing of the stats
     * provided through other methods of this class. However, conceptually, it is
     * added to the series on which this class computes statisctics values.
     * </p>
     * 
     * @param value
     *            The value to be added.
     * @see #remove(double)
     */
    public void add(double value) {
        totalWeight++;
        sum += value;
        squaresSum += value * value;
    }

    /**
     * Removes a value from this series.
     * <p>
     * The value is actually not removed since it was never added, but it is
     * conceptually what is done. It is the exact opposite of {@link #add(double)}.
     * </p>
     * 
     * @param value
     *            The value to be removed.
     * @see #add(double)
     */
    public void remove(double value) {
        totalWeight--;
        sum -= value;
        squaresSum -= value * value;
    }

    /**
     * Adds a value to this series.
     * <p>
     * The value is actually not stored but used in a pre-computing of the stats
     * provided through other methods of this class. However, conceptually, it is
     * added to the series on which this class computes statisctics values.
     * </p>
     * 
     * @param value
     *            The value to be added.
     * @see #remove(double)
     */
    public void add(double value, double weight) {
        totalWeight += weight;
        sum += value;
        squaresSum += value * value;
    }

    /**
     * Removes a value from this series.
     * <p>
     * The value is actually not removed since it was never added, but it is
     * conceptually what is done. It is the exact opposite of {@link #add(double)}.
     * </p>
     * 
     * @param value
     *            The value to be removed.
     * @see #add(double)
     */
    public void remove(double value, double weight) {
        totalWeight -= weight;
        sum -= value;
        squaresSum -= value * value;
    }

    /**
     * Returns the mean of this series of values.
     */
    public double mean() {
        if (totalWeight == 0) {
            return 0;
        }
        return sum / totalWeight;
    }

    /**
     * Returns the variance of this series of values.
     */
    public double variance() {
        if (totalWeight == 0) {
            return 0;
        }
        double avg = mean();
        return squaresSum / totalWeight - avg * avg;
    }

    /**
     * Returns the standard deviation of this series of values.
     */
    public double standardDeviation() {
        double var = variance();
        return Math.sqrt(var);
    }
}
