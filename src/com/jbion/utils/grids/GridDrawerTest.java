package com.jbion.utils.grids;

import com.jbion.utils.grids.GridDrawer.BorderType;

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
        MyGrid g = new MyGrid();
        GridDrawer gd = new GridDrawer();
        System.out.println("Default borders");
        System.out.println(gd.drawGrid(g));
        for (BorderType bt : BorderType.values()) {
            gd.setBorderType(bt);
            System.out.println("Borders: " + bt);
            System.out.println(gd.drawGrid(g));
            for (BorderType bt2 : BorderType.values()) {
                try {
                    gd.setBorderType(bt, bt2);
                    System.out.println("Borders: " + bt + "/" + bt2);
                    System.out.println(gd.drawGrid(g));
                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                }
            }
        }
    }

}
