package com.jbion.utils.grids;

import com.jbion.utils.console.drawing.grids.Grid;
import com.jbion.utils.console.drawing.grids.GridDrawer;
import com.jbion.utils.console.drawing.grids.GridDrawer.BorderType;

public class GridDrawerTest {

    public static class MyGrid implements Grid {

        @Override
        public int getCellWidth() {
            return 3;
        }

        @Override
        public int getWidth() {
            return 10;
        }

        @Override
        public int getHeight() {
            return 7;
        }

        @Override
        public String getValueAt(int row, int col) {
            return String.format("%03d", 2 * row + col);
        }

    }

    public static void main(String[] args) {
        final MyGrid g = new MyGrid();
        final GridDrawer gd = new GridDrawer();
        System.out.println("Default borders");
        System.out.println(gd.drawGrid(g));
        for (final BorderType bt : BorderType.values()) {
            gd.setBorderType(bt);
            System.out.println("Borders: " + bt);
            System.out.println(gd.drawGrid(g));
            for (final BorderType bt2 : BorderType.values()) {
                try {
                    gd.setBorderType(bt, bt2);
                    System.out.println("Borders: " + bt + "/" + bt2);
                    System.out.println(gd.drawGrid(g));
                } catch (final Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
            }
        }
    }

}
