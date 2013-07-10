package com.joffrey_bion.utils.stats;


public class StatsLine {

    private FlowStats[] columns;

    public StatsLine(int nbOfColumns) {
        columns = new FlowStats[nbOfColumns];
        for (int i = 0; i < nbOfColumns; i++) {
            columns[i] = new FlowStats();
        }
    }

    public void add(String[] line) {
        if (columns.length != line.length)
            throw new RuntimeException("wrong number of columns in the line");
        for (int i = 0; i < columns.length; i++)
            columns[i].add(Double.valueOf(line[i]));
    }

    public void remove(String[] line) {
        if (columns.length != line.length)
            throw new RuntimeException("wrong number of columns in the line");
        for (int i = 0; i < columns.length; i++)
            columns[i].remove(Double.valueOf(line[i]));
    }

    public void clear() {
        for (FlowStats column : columns) {
            column.clear();
        }
    }

    public int getNbColumns() {
        return columns.length;
    }

    public String[] toStringArray() {
        String[] line = new String[columns.length * 2];
        int j = 0;
        for (int i = 0; i < columns.length; i++) {
            line[j++] = "" + columns[i].mean();
            line[j++] = "" + columns[i].standardDeviation();
        }
        return line;
    }
}
