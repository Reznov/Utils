package com.jbion.utils.classification;

import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrix<T> {

    private final ArrayList<T> classes;
    private final int[][] matrix;
    private int total;
    private int totalCorrect;

    public ConfusionMatrix(List<T> classes) {
        this.classes = new ArrayList<>(classes);
        matrix = new int[classes.size()][classes.size()];
        for (int i = 0; i < classes.size(); i++) {
            for (int j = 0; j < classes.size(); j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private int getIndex(T clazz) {
        final int i = classes.indexOf(clazz);
        if (i == -1) {
            throw new RuntimeException("Unkown class '" + clazz + "'.");
        }
        return i;
    }

    public void add(T trueClass, T classifiedAs) {
        final int t = getIndex(trueClass);
        final int c = getIndex(classifiedAs);
        matrix[t][c]++;
        total++;
        if (t == c) {
            totalCorrect++;
        }
    }

    public void remove(T trueClass, T classifiedAs) {
        final int t = getIndex(trueClass);
        final int c = getIndex(classifiedAs);
        matrix[t][c]--;
        total++;
        if (t == c) {
            totalCorrect--;
        }
    }

    public int getCount(T trueClass, T classifiedAs) {
        return matrix[getIndex(trueClass)][getIndex(classifiedAs)];
    }

    /**
     * Returns the total number of instances.
     *
     * @return the total number of instances.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns the number of correctly classified instances.
     *
     * @return the number of correctly classified instances.
     */
    public int getTotalCorrect() {
        return totalCorrect;
    }

    /**
     * Returns the number of incorrectly classified instances.
     *
     * @return the number of incorrectly classified instances.
     */
    public int getTotalIncorrect() {
        return total - totalCorrect;
    }

    /**
     * Returns the rate of correctly classified instances.
     *
     * @return the rate of correctly classified instances.
     */
    public double getCorrectRate() {
        return (double) totalCorrect / total;
    }

    /**
     * Returns the rate of incorrectly classified instances.
     *
     * @return the rate of incorrectly classified instances.
     */
    public double getIncorrectRate() {
        return (double) (total - totalCorrect) / total;
    }

    /**
     * Return the recall for the specified class. It corresponds to the probability
     * that a (randomly selected) actual instance of the specified class is
     * classified as such.
     *
     * @param clazz
     *            The considered class.
     * @return the recall for the specified class.
     */
    public double getRecall(T clazz) {
        return (double) getTruePositives(clazz) / getTotalPositives(clazz);
    }

    /**
     * Return the precision for the specified class. It corresponds to the
     * probability that a (randomly selected) instance classified as the specified
     * class is actually of this class.
     *
     * @param clazz
     *            The considered class.
     * @return the recall for the specified class.
     */
    public double getPrecision(T clazz) {
        return (double) getTruePositives(clazz) / getTotalActual(clazz);
    }

    /**
     * Return the precision for the specified class. It corresponds to the
     * probability that a (randomly selected) instance that is not of the specified
     * class is classified as a different class than the specified class.
     *
     * @param clazz
     *            The considered class.
     * @return the recall for the specified class.
     */
    public double getTrueNegativeRate(T clazz) {
        return (double) getTrueNegatives(clazz) / getTotalNot(clazz);
    }

    /**
     * Returns the accuracy for the specified class. It corresponds to the
     * probability that a randomly selected instance will be correctly classified
     * (true positive or true negative).
     *
     * @param clazz
     *            The considered class.
     * @return the accuracy for the specified class.
     */
    public double getAccuracy(T clazz) {
        return (double) (getTruePositives(clazz) + getTrueNegatives(clazz)) / total;
    }

    /**
     * Returns the F-Measure for the specified class, with equal importance given to
     * recall and precision.
     *
     * @param clazz
     *            The considered class.
     * @return the F-Measure for the specified class.
     * @see #getFMeasure(Object, double)
     */
    public double getFMeasure(T clazz) {
        return getFMeasure(clazz, 1);
    }

    /**
     * Returns the F-Measure for the specified class.
     *
     * @param clazz
     *            The considered class.
     * @param beta
     *            How much importance is given to recall compared to precision.
     * @return the F-Measure for the specified class.
     */
    public double getFMeasure(T clazz, double beta) {
        final double precision = getPrecision(clazz);
        final double recall = getRecall(clazz);
        final double beta2 = beta * beta;
        return (1 + beta2) * precision * recall / (beta2 * precision + recall);
    }

    /**
     * Returns the number of instances correctly classified as the specified class.
     * They are actually of this class.
     *
     * @param clazz
     *            The considered class.
     * @return the number of instances correctly classified as the specified class.
     */
    public int getTruePositives(T clazz) {
        return get(clazz, Filter.MATCH, Filter.MATCH);
    }

    /**
     * Returns the number of instances incorrectly classified as the specified class.
     * They are actually not of this class.
     *
     * @param clazz
     *            The considered class.
     * @return the number of instances incorrectly classified as the specified class.
     */
    public int getFalsePositives(T clazz) {
        return get(clazz, Filter.DIFFER, Filter.MATCH);
    }

    /**
     * Returns the number of instances that are not classified as the specified
     * class, and are not of this class in reality.
     *
     * @param clazz
     *            The considered class.
     * @return Returns the number of instances that are not classified as the
     *         specified class, and are not of this class in reality.
     */
    public int getTrueNegatives(T clazz) {
        return get(clazz, Filter.DIFFER, Filter.DIFFER);
    }

    /**
     * Returns the number of instances that are (incorrectly) not classified as the
     * specified class, but are of this class in reality.
     *
     * @param clazz
     *            The considered class.
     * @return Returns the number of instances that are (incorrectly) not classified
     *         as the specified class, but are of this class in reality.
     */
    public int getFalseNegatives(T clazz) {
        return get(clazz, Filter.MATCH, Filter.DIFFER);
    }

    /**
     * Returns the number of actual instances of the specified class in reality.
     *
     * @param clazz
     *            The considered class.
     * @return the number of actual instances of the specified class in reality.
     */
    public int getTotalActual(T clazz) {
        return get(clazz, Filter.MATCH, Filter.ALL);
    }

    /**
     * Returns the total number of instances classified as the specified class.
     *
     * @param clazz
     *            The considered class.
     * @return the total number of instances classified as the specified class.
     */
    public int getTotalPositives(T clazz) {
        return get(clazz, Filter.ALL, Filter.MATCH);
    }

    /**
     * Returns the number of instances that are not of the specified class in
     * reality.
     *
     * @param clazz
     *            The considered class.
     * @return the number of instances that are not of the specified class in
     *         reality.
     */
    public int getTotalNot(T clazz) {
        return get(clazz, Filter.DIFFER, Filter.ALL);
    }

    /**
     * Returns the number of instances not classified as the specified class.
     *
     * @param clazz
     *            The considered class.
     * @return the number of instances not classified as the specified class.
     */
    public int getTotalNegatives(T clazz) {
        return get(clazz, Filter.ALL, Filter.DIFFER);
    }

    /**
     * Counts the number of instances corresponding to the given filters.
     *
     * @param clazz
     *            The considered class.
     * @param truth
     *            Whether or not to keep only the instances that are truly of
     *            {@code clazz}.
     * @param classifiedAs
     *            Whether or not to keep only the instances that are classified as
     *            {@code clazz}.
     * @return the count.
     */
    private int get(T clazz, Filter truth, Filter classifiedAs) {
        final int index = getIndex(clazz);
        int count = 0;
        for (int t = 0; t < classes.size(); t++) {
            for (int c = 0; c < classes.size(); c++) {
                if (truth.keep(index, t) && classifiedAs.keep(index, c)) {
                    count += matrix[t][c];
                }
            }
        }
        return count;
    }

    private enum Filter {
        /** Count only the instances that match. */
        MATCH {
            @Override
            public boolean keep(int classIndex, int comparedIndex) {
                return classIndex == comparedIndex;
            }
        },
        /** Count only the instances that don't match. */
        DIFFER {
            @Override
            public boolean keep(int classIndex, int comparedIndex) {
                return classIndex != comparedIndex;
            }
        },
        /** Count all instances. */
        ALL {
            @Override
            public boolean keep(int classIndex, int comparedIndex) {
                return true;
            }
        };

        /**
         * Returns whether the given instance should be counted.
         *
         * @param classIndex
         *            The index of the class to compare the other index to.
         * @param comparedIndex
         *            The index of the class of the instance to check.
         * @return {@code true} if the specified instance matches this filter.
         */
        public abstract boolean keep(int classIndex, int comparedIndex);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String newLine = System.getProperty("line.separator");
        for (int i = 0; i < classes.size(); i++) {
            sb.append((char) ('a' + i));
            sb.append("\t");
        }
        sb.append("<= classified as");
        sb.append(newLine);
        for (int t = 0; t < classes.size(); t++) {
            for (int c = 0; c < classes.size(); c++) {
                sb.append(matrix[t][c]);
                sb.append("\t");
            }
            sb.append((char) ('a' + t) + " = " + classes.get(t).toString());
            sb.append(newLine);
        }
        return sb.toString();
    }
}
