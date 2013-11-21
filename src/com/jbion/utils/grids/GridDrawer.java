package com.jbion.utils.grids;

/**
 * A class containing useful methods to draw grids. It uses the {@code Character}
 * constants in {@link BoxChars}.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class GridDrawer {

    private static final BorderType INT_DEFAULT = BorderType.LIGHT;
    private static final BorderType EXT_DEFAULT = BorderType.HEAVY;

    private static final String LF = BoxChars.NEW_LINE;

    // external borders
    private char he;
    private char ve;
    private char tlCorner;
    private char blCorner;
    private char trCorner;
    private char brCorner;

    // internal borders
    private char hi;
    private char vi;
    private char cross;

    // join borders
    private char dTee;
    private char uTee;
    private char rTee;
    private char lTee;

    /**
     * Represents the style of a border.
     */
    public static enum BorderType {
        LIGHT,
        HEAVY,
        DOUBLE;
    }

    private BorderType internalType;
    private BorderType externalType;

    /**
     * Creates a new {@code GridDrawer} with default borders.
     */
    public GridDrawer() {
        this(INT_DEFAULT, EXT_DEFAULT);
    }

    /**
     * Creates a new {@code GridDrawer} with the specified {@link BorderType} for
     * both internal and external borders.
     * 
     * @param type
     *            The border type for internal and external borders.
     */
    public GridDrawer(BorderType type) {
        this(type, type);
    }

    /**
     * Creates a new {@code GridDrawer} with the specified {@link BorderType}s for
     * internal and external borders.
     * 
     * @param internal
     *            The border type for internal borders.
     * @param external
     *            The border type for external borders.
     * @throws IllegalArgumentException
     *             If {@link BorderType#HEAVY} and {@link BorderType#DOUBLE} types
     *             are combined.
     */
    public GridDrawer(BorderType internal, BorderType external) throws IllegalArgumentException {
        setBorderType(internal, external);
    }

    /**
     * Creates a new {@code GridDrawer} with the specified {@link BorderType}s for
     * internal and external borders.
     * 
     * @param type
     *            The border type for internal and external borders.
     */
    public void setBorderType(BorderType type) {
        setBorderType(type, type);
    }

    /**
     * Sets the type of the internal and external borders. Heavy/Double combinations
     * are not supported because the necessary T characters it would need don't
     * exist.
     * 
     * @param internal
     *            The border type for internal borders.
     * @param external
     *            The border type for external borders.
     * @throws IllegalArgumentException
     *             If {@link BorderType#HEAVY} and {@link BorderType#DOUBLE} types
     *             are combined.
     */
    public void setBorderType(BorderType internal, BorderType external)
            throws IllegalArgumentException {
        if ((internal == BorderType.DOUBLE && external == BorderType.HEAVY)
                || (internal == BorderType.HEAVY && external == BorderType.DOUBLE)) {
            throw new IllegalArgumentException("Heavy/Double combinations are not supported.");
        }
        this.internalType = internal;
        this.externalType = external;
        updateExternalBorders();
        updateInternalBorders();
        updateTees();
    }

    /**
     * Returns a String representing the specified {@link Grid}.
     * 
     * @param g
     *            The {@code Grid} to get the values from.
     * @return a String representing the specified {@link Grid}.
     */
    public String drawGrid(Grid g) {
        StringBuilder sb = new StringBuilder();
        final int cw = g.getCellWidth();
        final int w = g.getWidth();
        final int h = g.getHeight();
        sb.append(repeat(he, w, cw, dTee, tlCorner, trCorner));
        sb.append(LF);
        for (int i = 0; i < h; i++) {
            sb.append(ve);
            for (int j = 0; j < w; j++) {
                sb.append(g.getValueAt(i, j));
                if (j < w - 1) {
                    sb.append(vi);
                } else {
                    sb.append(ve);
                }
            }
            sb.append(LF);
            if (i < h - 1) {
                sb.append(repeat(hi, w, cw, cross, rTee, lTee));
            } else {
                sb.append(repeat(he, w, cw, uTee, blCorner, brCorner));
            }
            sb.append(LF);
        }
        return sb.toString();
    }

    /**
     * Returns a String representing {@code n} times the specified object.
     * <p>
     * Typically, {@code c} is a {@code char}, a {@link Character} or a
     * {@link String}.
     * </p>
     * 
     * @param c
     *            The object to repeat.
     * @param n
     *            The number of times to repeat.
     * @return A String representing {@code n} times the specified object.
     */
    public static String repeat(Object c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Returns the concatenation of {@code nbSections} sections, each of them being
     * the concatenation of {@code sectionSize} times the String representation of
     * {@code cBase}. Between each section, the representation of{@code cStep} is
     * inserted once.
     * <p>
     * Typically, {@code cBase} and {@code cSep} are {@code char}s, {@link Character}
     * s or {@link String}s.
     * </p>
     * 
     * @param cBase
     *            The basic character to put in the sections.
     * @param nbSections
     *            The number of sections desired.
     * @param sectionSize
     *            The number of times {@code cBase} will be repeated in each section.
     * @param cSep
     *            The separator to put between each section.
     * 
     * @return A String of {@code nbSections} sections of {@code sectionSize}
     *         {@code cBase}, separated with {@code cSep}.
     */
    public static String repeat(Object cBase, int nbSections, int sectionSize, Object cSep) {
        return repeat(cBase, nbSections, sectionSize, cSep, "", "");
    }

    /**
     * Returns the concatenation of {@code nbSections} sections, each of them being
     * the concatenation of {@code sectionSize} times the String representation of
     * {@code cBase}. Between each section, the representation of{@code cStep} is
     * inserted once.
     * <p>
     * Typically, {@code cBase} and {@code cSep} are {@code char}s, {@link Character}
     * s or {@link String}s.
     * </p>
     * 
     * @param cBase
     *            The basic character to put in the sections.
     * @param nbSections
     *            The number of sections desired.
     * @param sectionSize
     *            The number of times {@code cBase} will be repeated in each section.
     * @param cSep
     *            The separator to put between each section.
     * @param prefix
     *            The left bound, inserted at the beginning of the result.
     * @param suffix
     *            The right bound, appended to the end of the result.
     * 
     * @return A String of {@code nbSections} sections of {@code sectionSize}
     *         {@code cBase}, separated with {@code cSep}.
     */
    public static String repeat(Object cBase, int nbSections, int sectionSize, Object cSep,
            Object prefix, Object suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix.toString());
        for (int i = 0; i < nbSections; i++) {
            sb.append(repeat(cBase, sectionSize));
            if (i != nbSections - 1) {
                sb.append(cSep);
            }
        }
        sb.append(suffix.toString());
        return sb.toString();
    }

    /**
     * Updates the external border characters with the current {@link BorderType}
     * {@link #externalType}.
     */
    private void updateExternalBorders() {
        switch (externalType) {
        case LIGHT:
            he = BoxChars.BOX_DRAWINGS_LIGHT_HORIZONTAL;
            ve = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL;
            tlCorner = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT;
            blCorner = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT;
            trCorner = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT;
            brCorner = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_LEFT;
            break;
        case HEAVY:
            he = BoxChars.BOX_DRAWINGS_HEAVY_HORIZONTAL;
            ve = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL;
            tlCorner = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_RIGHT;
            blCorner = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_RIGHT;
            trCorner = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_LEFT;
            brCorner = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_LEFT;
            break;
        case DOUBLE:
            he = BoxChars.BOX_DRAWINGS_DOUBLE_HORIZONTAL;
            ve = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL;
            tlCorner = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_RIGHT;
            blCorner = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_RIGHT;
            trCorner = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_LEFT;
            brCorner = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_LEFT;
        }
    }

    /**
     * Updates the internal border characters with the current {@link BorderType}
     * {@link #internalType}.
     */
    private void updateInternalBorders() {
        switch (internalType) {
        case LIGHT:
            hi = BoxChars.BOX_DRAWINGS_LIGHT_HORIZONTAL;
            vi = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL;
            cross = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
            break;
        case HEAVY:
            hi = BoxChars.BOX_DRAWINGS_HEAVY_HORIZONTAL;
            vi = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL;
            cross = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_HORIZONTAL;
            break;
        case DOUBLE:
            hi = BoxChars.BOX_DRAWINGS_DOUBLE_HORIZONTAL;
            vi = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL;
            cross = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL_AND_HORIZONTAL;
        }
    }

    /**
     * Updates the T characters with the current {@link BorderType}s
     * {@link #internalType} and {@link #externalType}. These characters are the
     * links between internal and external borders.
     */
    private void updateTees() {
        switch (externalType) {
        case LIGHT:
            switch (internalType) {
            case LIGHT:
                dTee = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL;
                uTee = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL;
                rTee = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT;
                lTee = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT;
                break;
            case HEAVY:
                dTee = BoxChars.BOX_DRAWINGS_DOWN_HEAVY_AND_HORIZONTAL_LIGHT;
                uTee = BoxChars.BOX_DRAWINGS_UP_HEAVY_AND_HORIZONTAL_LIGHT;
                rTee = BoxChars.BOX_DRAWINGS_VERTICAL_LIGHT_AND_RIGHT_HEAVY;
                lTee = BoxChars.BOX_DRAWINGS_VERTICAL_LIGHT_AND_LEFT_HEAVY;
                break;
            case DOUBLE:
                dTee = BoxChars.BOX_DRAWINGS_DOWN_DOUBLE_AND_HORIZONTAL_SINGLE;
                uTee = BoxChars.BOX_DRAWINGS_UP_DOUBLE_AND_HORIZONTAL_SINGLE;
                rTee = BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_RIGHT_DOUBLE;
                lTee = BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_LEFT_DOUBLE;
            }
            break;
        case HEAVY:
            switch (internalType) {
            case LIGHT:
                dTee = BoxChars.BOX_DRAWINGS_DOWN_LIGHT_AND_HORIZONTAL_HEAVY;
                uTee = BoxChars.BOX_DRAWINGS_UP_LIGHT_AND_HORIZONTAL_HEAVY;
                rTee = BoxChars.BOX_DRAWINGS_VERTICAL_HEAVY_AND_RIGHT_LIGHT;
                lTee = BoxChars.BOX_DRAWINGS_VERTICAL_HEAVY_AND_LEFT_LIGHT;
                break;
            case HEAVY:
                dTee = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_HORIZONTAL;
                uTee = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_HORIZONTAL;
                rTee = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_RIGHT;
                lTee = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_LEFT;
                break;
            case DOUBLE:
                throw new IllegalStateException("Heavy/Double border combination not supported.");
            }
            break;
        case DOUBLE:
            switch (internalType) {
            case LIGHT:
                dTee = BoxChars.BOX_DRAWINGS_DOWN_SINGLE_AND_HORIZONTAL_DOUBLE;
                uTee = BoxChars.BOX_DRAWINGS_UP_SINGLE_AND_HORIZONTAL_DOUBLE;
                rTee = BoxChars.BOX_DRAWINGS_VERTICAL_DOUBLE_AND_RIGHT_SINGLE;
                lTee = BoxChars.BOX_DRAWINGS_VERTICAL_DOUBLE_AND_LEFT_SINGLE;
                break;
            case HEAVY:
                throw new IllegalStateException("Heavy/Double border combination not supported.");
            case DOUBLE:
                dTee = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_HORIZONTAL;
                uTee = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_HORIZONTAL;
                rTee = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL_AND_RIGHT;
                lTee = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL_AND_LEFT;
            }
        }
    }
}
