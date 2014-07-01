package com.jbion.utils.console.drawing;

/**
 * An utility class containing interesting characters and methods to help drawing
 * stuff in a console.
 */
public class Drawing {

    /**
     * System-dependent new line character.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final char MIDDLE_DOT = Character.toChars(0x00B7)[0];

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
    public static String repeat(Object cBase, int nbSections, int sectionSize, Object cSep, Object prefix, Object suffix) {
        final StringBuilder sb = new StringBuilder();
        sb.append(prefix.toString());
        for (int i = 0; i < nbSections; i++) {
            sb.append(Drawing.repeat(cBase, sectionSize));
            if (i != nbSections - 1) {
                sb.append(cSep);
            }
        }
        sb.append(suffix.toString());
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
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

}
