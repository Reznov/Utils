package com.jbion.utils.grids;

/**
 * Constants corresponding to box drawings UTF-8 characters.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class BoxChars {

    /**
     * System-dependent new line character.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final char MIDDLE_DOT = Character.toChars(0x00B7)[0];

    public static final char BOX_DRAWINGS_LIGHT_LEFT = Character.toChars(0x2574)[0];
    public static final char BOX_DRAWINGS_LIGHT_UP = Character.toChars(0x2575)[0];
    public static final char BOX_DRAWINGS_LIGHT_RIGHT = Character.toChars(0x2576)[0];
    public static final char BOX_DRAWINGS_LIGHT_DOWN = Character.toChars(0x2577)[0];
    public static final char BOX_DRAWINGS_LIGHT_HORIZONTAL = Character.toChars(0x2500)[0];
    public static final char BOX_DRAWINGS_LIGHT_VERTICAL = Character.toChars(0x2502)[0];

    public static final char BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT = Character.toChars(0x250C)[0];
    public static final char BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT = Character.toChars(0x2510)[0];
    public static final char BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL = Character.toChars(0x252C)[0];
    public static final char BOX_DRAWINGS_LIGHT_UP_AND_RIGHT = Character.toChars(0x2514)[0];
    public static final char BOX_DRAWINGS_LIGHT_UP_AND_LEFT = Character.toChars(0x2518)[0];
    public static final char BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL = Character.toChars(0x2534)[0];
    public static final char BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT = Character.toChars(0x251C)[0];
    public static final char BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT = Character.toChars(0x2524)[0];
    public static final char BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL = Character.toChars(0x253C)[0];

    public static final char BOX_DRAWINGS_LIGHT_DIAGONAL_UPPER_RIGHT_TO_LOWER_LEFT = Character
            .toChars(0x2571)[0];
    public static final char BOX_DRAWINGS_LIGHT_DIAGONAL_UPPER_LEFT_TO_LOWER_RIGHT = Character
            .toChars(0x2572)[0];
    public static final char BOX_DRAWINGS_LIGHT_DIAGONAL_CROSS = Character.toChars(0x2573)[0];

    public static final char BOX_DRAWINGS_LIGHT_DOUBLE_DASH_HORIZONTAL = Character.toChars(0x254C)[0];
    public static final char BOX_DRAWINGS_LIGHT_TRIPLE_DASH_HORIZONTAL = Character.toChars(0x2504)[0];
    public static final char BOX_DRAWINGS_LIGHT_TRIPLE_DASH_VERTICAL = Character.toChars(0x2506)[0];
    public static final char BOX_DRAWINGS_LIGHT_QUADRUPLE_DASH_HORIZONTAL = Character
            .toChars(0x2508)[0];
    public static final char BOX_DRAWINGS_LIGHT_QUADRUPLE_DASH_VERTICAL = Character.toChars(0x250A)[0];

    public static final char BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT = Character.toChars(0x256D)[0];
    public static final char BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT = Character.toChars(0x256E)[0];
    public static final char BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT = Character.toChars(0x256F)[0];
    public static final char BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT = Character.toChars(0x2570)[0];

    public static final char BOX_DRAWINGS_HEAVY_LEFT = Character.toChars(0x2578)[0];
    public static final char BOX_DRAWINGS_HEAVY_UP = Character.toChars(0x2579)[0];
    public static final char BOX_DRAWINGS_HEAVY_RIGHT = Character.toChars(0x257A)[0];
    public static final char BOX_DRAWINGS_HEAVY_DOWN = Character.toChars(0x257B)[0];
    public static final char BOX_DRAWINGS_HEAVY_HORIZONTAL = Character.toChars(0x2501)[0];
    public static final char BOX_DRAWINGS_HEAVY_VERTICAL = Character.toChars(0x2503)[0];

    public static final char BOX_DRAWINGS_HEAVY_DOWN_AND_RIGHT = Character.toChars(0x250F)[0];
    public static final char BOX_DRAWINGS_HEAVY_DOWN_AND_LEFT = Character.toChars(0x2513)[0];
    public static final char BOX_DRAWINGS_HEAVY_UP_AND_RIGHT = Character.toChars(0x2517)[0];
    public static final char BOX_DRAWINGS_HEAVY_UP_AND_LEFT = Character.toChars(0x251B)[0];
    public static final char BOX_DRAWINGS_HEAVY_UP_AND_HORIZONTAL = Character.toChars(0x253B)[0];
    public static final char BOX_DRAWINGS_HEAVY_VERTICAL_AND_RIGHT = Character.toChars(0x2523)[0];
    public static final char BOX_DRAWINGS_HEAVY_VERTICAL_AND_LEFT = Character.toChars(0x252B)[0];

    public static final char BOX_DRAWINGS_HEAVY_TRIPLE_DASH_HORIZONTAL = Character.toChars(0x2505)[0];
    public static final char BOX_DRAWINGS_HEAVY_TRIPLE_DASH_VERTICAL = Character.toChars(0x2507)[0];
    public static final char BOX_DRAWINGS_HEAVY_QUADRUPLE_DASH_HORIZONTAL = Character
            .toChars(0x2509)[0];
    public static final char BOX_DRAWINGS_HEAVY_QUADRUPLE_DASH_VERTICAL = Character.toChars(0x250B)[0];

    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_RIGHT_HEAVY = Character.toChars(0x250D)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_RIGHT_LIGHT = Character.toChars(0x250E)[0];
    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_LEFT_HEAVY = Character.toChars(0x2511)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_LEFT_LIGHT = Character.toChars(0x2512)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_RIGHT_HEAVY = Character.toChars(0x2515)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_RIGHT_LIGHT = Character.toChars(0x2516)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_LEFT_HEAVY = Character.toChars(0x2519)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_LEFT_LIGHT = Character.toChars(0x251A)[0];
    public static final char BOX_DRAWINGS_VERTICAL_LIGHT_AND_RIGHT_HEAVY = Character
            .toChars(0x251D)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_RIGHT_DOWN_LIGHT = Character.toChars(0x251E)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_RIGHT_UP_LIGHT = Character.toChars(0x251F)[0];
    public static final char BOX_DRAWINGS_VERTICAL_HEAVY_AND_RIGHT_LIGHT = Character
            .toChars(0x2520)[0];
    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_RIGHT_UP_HEAVY = Character.toChars(0x2521)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_RIGHT_DOWN_HEAVY = Character.toChars(0x2522)[0];
    public static final char BOX_DRAWINGS_VERTICAL_LIGHT_AND_LEFT_HEAVY = Character.toChars(0x2525)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_LEFT_DOWN_LIGHT = Character.toChars(0x2526)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_LEFT_UP_LIGHT = Character.toChars(0x2527)[0];
    public static final char BOX_DRAWINGS_VERTICAL_HEAVY_AND_LEFT_LIGHT = Character.toChars(0x2528)[0];
    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_LEFT_UP_HEAVY = Character.toChars(0x2529)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_LEFT_DOWN_HEAVY = Character.toChars(0x252A)[0];
    public static final char BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_DOWN_LIGHT = Character
            .toChars(0x252D)[0];
    public static final char BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_DOWN_LIGHT = Character
            .toChars(0x252E)[0];
    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_HORIZONTAL_HEAVY = Character
            .toChars(0x252F)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_HORIZONTAL_LIGHT = Character
            .toChars(0x2530)[0];
    public static final char BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_DOWN_HEAVY = Character
            .toChars(0x2531)[0];
    public static final char BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_DOWN_HEAVY = Character
            .toChars(0x2532)[0];
    public static final char BOX_DRAWINGS_HEAVY_DOWN_AND_HORIZONTAL = Character.toChars(0x2533)[0];
    public static final char BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_UP_LIGHT = Character.toChars(0x2535)[0];
    public static final char BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_UP_LIGHT = Character.toChars(0x2536)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_HORIZONTAL_HEAVY = Character.toChars(0x2537)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_HORIZONTAL_LIGHT = Character.toChars(0x2538)[0];
    public static final char BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_UP_HEAVY = Character.toChars(0x2539)[0];
    public static final char BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_UP_HEAVY = Character.toChars(0x253A)[0];
    public static final char BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_VERTICAL_LIGHT = Character
            .toChars(0x253D)[0];
    public static final char BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_VERTICAL_LIGHT = Character
            .toChars(0x253E)[0];
    public static final char BOX_DRAWINGS_VERTICAL_LIGHT_AND_HORIZONTAL_HEAVY = Character
            .toChars(0x253F)[0];
    public static final char BOX_DRAWINGS_UP_HEAVY_AND_DOWN_HORIZONTAL_LIGHT = Character
            .toChars(0x2540)[0];
    public static final char BOX_DRAWINGS_DOWN_HEAVY_AND_UP_HORIZONTAL_LIGHT = Character
            .toChars(0x2541)[0];
    public static final char BOX_DRAWINGS_VERTICAL_HEAVY_AND_HORIZONTAL_LIGHT = Character
            .toChars(0x2542)[0];
    public static final char BOX_DRAWINGS_LEFT_UP_HEAVY_AND_RIGHT_DOWN_LIGHT = Character
            .toChars(0x2543)[0];
    public static final char BOX_DRAWINGS_RIGHT_UP_HEAVY_AND_LEFT_DOWN_LIGHT = Character
            .toChars(0x2544)[0];
    public static final char BOX_DRAWINGS_LEFT_DOWN_HEAVY_AND_RIGHT_UP_LIGHT = Character
            .toChars(0x2545)[0];
    public static final char BOX_DRAWINGS_RIGHT_DOWN_HEAVY_AND_LEFT_UP_LIGHT = Character
            .toChars(0x2546)[0];
    public static final char BOX_DRAWINGS_DOWN_LIGHT_AND_UP_HORIZONTAL_HEAVY = Character
            .toChars(0x2547)[0];
    public static final char BOX_DRAWINGS_UP_LIGHT_AND_DOWN_HORIZONTAL_HEAVY = Character
            .toChars(0x2548)[0];
    public static final char BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_VERTICAL_HEAVY = Character
            .toChars(0x2549)[0];
    public static final char BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_VERTICAL_HEAVY = Character
            .toChars(0x254A)[0];
    public static final char BOX_DRAWINGS_HEAVY_VERTICAL_AND_HORIZONTAL = Character.toChars(0x254B)[0];
    public static final char BOX_DRAWINGS_HEAVY_DOUBLE_DASH_HORIZONTAL = Character.toChars(0x254D)[0];
    public static final char BOX_DRAWINGS_LIGHT_DOUBLE_DASH_VERTICAL = Character.toChars(0x254E)[0];
    public static final char BOX_DRAWINGS_HEAVY_DOUBLE_DASH_VERTICAL = Character.toChars(0x254F)[0];
    public static final char BOX_DRAWINGS_DOUBLE_HORIZONTAL = Character.toChars(0x2550)[0];
    public static final char BOX_DRAWINGS_DOUBLE_VERTICAL = Character.toChars(0x2551)[0];
    public static final char BOX_DRAWINGS_DOWN_SINGLE_AND_RIGHT_DOUBLE = Character.toChars(0x2552)[0];
    public static final char BOX_DRAWINGS_DOWN_DOUBLE_AND_RIGHT_SINGLE = Character.toChars(0x2553)[0];
    public static final char BOX_DRAWINGS_DOUBLE_DOWN_AND_RIGHT = Character.toChars(0x2554)[0];
    public static final char BOX_DRAWINGS_DOWN_SINGLE_AND_LEFT_DOUBLE = Character.toChars(0x2555)[0];
    public static final char BOX_DRAWINGS_DOWN_DOUBLE_AND_LEFT_SINGLE = Character.toChars(0x2556)[0];
    public static final char BOX_DRAWINGS_DOUBLE_DOWN_AND_LEFT = Character.toChars(0x2557)[0];
    public static final char BOX_DRAWINGS_UP_SINGLE_AND_RIGHT_DOUBLE = Character.toChars(0x2558)[0];
    public static final char BOX_DRAWINGS_UP_DOUBLE_AND_RIGHT_SINGLE = Character.toChars(0x2559)[0];
    public static final char BOX_DRAWINGS_DOUBLE_UP_AND_RIGHT = Character.toChars(0x255A)[0];
    public static final char BOX_DRAWINGS_UP_SINGLE_AND_LEFT_DOUBLE = Character.toChars(0x255B)[0];
    public static final char BOX_DRAWINGS_UP_DOUBLE_AND_LEFT_SINGLE = Character.toChars(0x255C)[0];
    public static final char BOX_DRAWINGS_DOUBLE_UP_AND_LEFT = Character.toChars(0x255D)[0];
    public static final char BOX_DRAWINGS_VERTICAL_SINGLE_AND_RIGHT_DOUBLE = Character
            .toChars(0x255E)[0];
    public static final char BOX_DRAWINGS_VERTICAL_DOUBLE_AND_RIGHT_SINGLE = Character
            .toChars(0x255F)[0];
    public static final char BOX_DRAWINGS_DOUBLE_VERTICAL_AND_RIGHT = Character.toChars(0x2560)[0];
    public static final char BOX_DRAWINGS_VERTICAL_SINGLE_AND_LEFT_DOUBLE = Character
            .toChars(0x2561)[0];
    public static final char BOX_DRAWINGS_VERTICAL_DOUBLE_AND_LEFT_SINGLE = Character
            .toChars(0x2562)[0];
    public static final char BOX_DRAWINGS_DOUBLE_VERTICAL_AND_LEFT = Character.toChars(0x2563)[0];
    public static final char BOX_DRAWINGS_DOWN_SINGLE_AND_HORIZONTAL_DOUBLE = Character
            .toChars(0x2564)[0];
    public static final char BOX_DRAWINGS_DOWN_DOUBLE_AND_HORIZONTAL_SINGLE = Character
            .toChars(0x2565)[0];
    public static final char BOX_DRAWINGS_DOUBLE_DOWN_AND_HORIZONTAL = Character.toChars(0x2566)[0];
    public static final char BOX_DRAWINGS_UP_SINGLE_AND_HORIZONTAL_DOUBLE = Character
            .toChars(0x2567)[0];
    public static final char BOX_DRAWINGS_UP_DOUBLE_AND_HORIZONTAL_SINGLE = Character
            .toChars(0x2568)[0];
    public static final char BOX_DRAWINGS_DOUBLE_UP_AND_HORIZONTAL = Character.toChars(0x2569)[0];
    public static final char BOX_DRAWINGS_VERTICAL_SINGLE_AND_HORIZONTAL_DOUBLE = Character
            .toChars(0x256A)[0];
    public static final char BOX_DRAWINGS_VERTICAL_DOUBLE_AND_HORIZONTAL_SINGLE = Character
            .toChars(0x256B)[0];
    public static final char BOX_DRAWINGS_DOUBLE_VERTICAL_AND_HORIZONTAL = Character
            .toChars(0x256C)[0];
    public static final char BOX_DRAWINGS_LIGHT_LEFT_AND_HEAVY_RIGHT = Character.toChars(0x257C)[0];
    public static final char BOX_DRAWINGS_LIGHT_UP_AND_HEAVY_DOWN = Character.toChars(0x257D)[0];
    public static final char BOX_DRAWINGS_HEAVY_LEFT_AND_LIGHT_RIGHT = Character.toChars(0x257E)[0];
    public static final char BOX_DRAWINGS_HEAVY_UP_AND_LIGHT_DOWN = Character.toChars(0x257F)[0];
    public static final char UPPER_HALF_BLOCK = Character.toChars(0x2580)[0];
    public static final char LOWER_ONE_EIGHTH_BLOCK = Character.toChars(0x2581)[0];
    public static final char LOWER_ONE_QUARTER_BLOCK = Character.toChars(0x2582)[0];
    public static final char LOWER_THREE_EIGHTHS_BLOCK = Character.toChars(0x2583)[0];
    public static final char LOWER_HALF_BLOCK = Character.toChars(0x2584)[0];
    public static final char LOWER_FIVE_EIGHTHS_BLOCK = Character.toChars(0x2585)[0];
    public static final char LOWER_THREE_QUARTERS_BLOCK = Character.toChars(0x2586)[0];
    public static final char LOWER_SEVEN_EIGHTHS_BLOCK = Character.toChars(0x2587)[0];
    public static final char FULL_BLOCK = Character.toChars(0x2588)[0];
    public static final char LEFT_SEVEN_EIGHTHS_BLOCK = Character.toChars(0x2589)[0];
    public static final char LEFT_THREE_QUARTERS_BLOCK = Character.toChars(0x258A)[0];
    public static final char LEFT_FIVE_EIGHTHS_BLOCK = Character.toChars(0x258B)[0];
    public static final char LEFT_HALF_BLOCK = Character.toChars(0x258C)[0];
    public static final char LEFT_THREE_EIGHTHS_BLOCK = Character.toChars(0x258D)[0];
    public static final char LEFT_ONE_QUARTER_BLOCK = Character.toChars(0x258E)[0];
    public static final char LEFT_ONE_EIGHTH_BLOCK = Character.toChars(0x258F)[0];
    public static final char RIGHT_HALF_BLOCK = Character.toChars(0x2590)[0];
    public static final char LIGHT_SHADE = Character.toChars(0x2591)[0];
    public static final char MEDIUM_SHADE = Character.toChars(0x2592)[0];
    public static final char DARK_SHADE = Character.toChars(0x2593)[0];
    public static final char UPPER_ONE_EIGHTH_BLOCK = Character.toChars(0x2594)[0];
    public static final char RIGHT_ONE_EIGHTH_BLOCK = Character.toChars(0x2595)[0];
    public static final char QUADRANT_LOWER_LEFT = Character.toChars(0x2596)[0];
    public static final char QUADRANT_LOWER_RIGHT = Character.toChars(0x2597)[0];
    public static final char QUADRANT_UPPER_LEFT = Character.toChars(0x2598)[0];
    public static final char QUADRANT_UPPER_LEFT_AND_LOWER_LEFT_AND_LOWER_RIGHT = Character
            .toChars(0x2599)[0];
    public static final char QUADRANT_UPPER_LEFT_AND_LOWER_RIGHT = Character.toChars(0x259A)[0];
    public static final char QUADRANT_UPPER_LEFT_AND_UPPER_RIGHT_AND_LOWER_LEFT = Character
            .toChars(0x259B)[0];
    public static final char QUADRANT_UPPER_LEFT_AND_UPPER_RIGHT_AND_LOWER_RIGHT = Character
            .toChars(0x259C)[0];
    public static final char QUADRANT_UPPER_RIGHT = Character.toChars(0x259D)[0];
    public static final char QUADRANT_UPPER_RIGHT_AND_LOWER_LEFT = Character.toChars(0x259E)[0];
    public static final char QUADRANT_UPPER_RIGHT_AND_LOWER_LEFT_AND_LOWER_RIGHT = Character
            .toChars(0x259F)[0];
    public static final char BLACK_SQUARE = Character.toChars(0x25A0)[0];
    public static final char WHITE_SQUARE = Character.toChars(0x25A1)[0];
    public static final char WHITE_SQUARE_WITH_ROUNDED_CORNERS = Character.toChars(0x25A2)[0];
    public static final char WHITE_SQUARE_CONTAINING_BLACK_SMALL_SQUARE = Character.toChars(0x25A3)[0];
    public static final char SQUARE_WITH_HORIZONTAL_FILL = Character.toChars(0x25A4)[0];
    public static final char SQUARE_WITH_VERTICAL_FILL = Character.toChars(0x25A5)[0];
    public static final char SQUARE_WITH_ORTHOGONAL_CROSSHATCH_FILL = Character.toChars(0x25A6)[0];
    public static final char SQUARE_WITH_UPPER_LEFT_TO_LOWER_RIGHT_FILL = Character.toChars(0x25A7)[0];
    public static final char SQUARE_WITH_UPPER_RIGHT_TO_LOWER_LEFT_FILL = Character.toChars(0x25A8)[0];
    public static final char SQUARE_WITH_DIAGONAL_CROSSHATCH_FILL = Character.toChars(0x25A9)[0];
    public static final char BLACK_SMALL_SQUARE = Character.toChars(0x25AA)[0];
    public static final char WHITE_SMALL_SQUARE = Character.toChars(0x25AB)[0];
    public static final char BLACK_RECTANGLE = Character.toChars(0x25AC)[0];
    public static final char WHITE_RECTANGLE = Character.toChars(0x25AD)[0];
    public static final char BLACK_VERTICAL_RECTANGLE = Character.toChars(0x25AE)[0];
    public static final char WHITE_VERTICAL_RECTANGLE = Character.toChars(0x25AF)[0];
    public static final char BLACK_PARALLELOGRAM = Character.toChars(0x25B0)[0];
    public static final char WHITE_PARALLELOGRAM = Character.toChars(0x25B1)[0];
    public static final char BLACK_UP_POINTING_TRIANGLE = Character.toChars(0x25B2)[0];
    public static final char WHITE_UP_POINTING_TRIANGLE = Character.toChars(0x25B3)[0];
    public static final char BLACK_UP_POINTING_SMALL_TRIANGLE = Character.toChars(0x25B4)[0];
    public static final char WHITE_UP_POINTING_SMALL_TRIANGLE = Character.toChars(0x25B5)[0];
    public static final char BLACK_RIGHT_POINTING_TRIANGLE = Character.toChars(0x25B6)[0];
    public static final char WHITE_RIGHT_POINTING_TRIANGLE = Character.toChars(0x25B7)[0];
    public static final char BLACK_RIGHT_POINTING_SMALL_TRIANGLE = Character.toChars(0x25B8)[0];
    public static final char WHITE_RIGHT_POINTING_SMALL_TRIANGLE = Character.toChars(0x25B9)[0];
    public static final char BLACK_RIGHT_POINTING_POINTER = Character.toChars(0x25BA)[0];
    public static final char WHITE_RIGHT_POINTING_POINTER = Character.toChars(0x25BB)[0];
    public static final char BLACK_DOWN_POINTING_TRIANGLE = Character.toChars(0x25BC)[0];
    public static final char WHITE_DOWN_POINTING_TRIANGLE = Character.toChars(0x25BD)[0];
    public static final char BLACK_DOWN_POINTING_SMALL_TRIANGLE = Character.toChars(0x25BE)[0];
    public static final char WHITE_DOWN_POINTING_SMALL_TRIANGLE = Character.toChars(0x25BF)[0];
    public static final char BLACK_LEFT_POINTING_TRIANGLE = Character.toChars(0x25C0)[0];
    public static final char WHITE_LEFT_POINTING_TRIANGLE = Character.toChars(0x25C1)[0];
    public static final char BLACK_LEFT_POINTING_SMALL_TRIANGLE = Character.toChars(0x25C2)[0];
    public static final char WHITE_LEFT_POINTING_SMALL_TRIANGLE = Character.toChars(0x25C3)[0];
    public static final char BLACK_LEFT_POINTING_POINTER = Character.toChars(0x25C4)[0];
    public static final char WHITE_LEFT_POINTING_POINTER = Character.toChars(0x25C5)[0];
    public static final char BLACK_DIAMOND = Character.toChars(0x25C6)[0];
    public static final char WHITE_DIAMOND = Character.toChars(0x25C7)[0];
    public static final char WHITE_DIAMOND_CONTAINING_BLACK_SMALL_DIAMOND = Character
            .toChars(0x25C8)[0];
    public static final char FISHEYE = Character.toChars(0x25C9)[0];
    public static final char LOZENGE = Character.toChars(0x25CA)[0];
    public static final char WHITE_CIRCLE = Character.toChars(0x25CB)[0];
    public static final char DOTTED_CIRCLE = Character.toChars(0x25CC)[0];
    public static final char CIRCLE_WITH_VERTICAL_FILL = Character.toChars(0x25CD)[0];
    public static final char BULLSEYE = Character.toChars(0x25CE)[0];
    public static final char BLACK_CIRCLE = Character.toChars(0x25CF)[0];
    public static final char CIRCLE_WITH_LEFT_HALF_BLACK = Character.toChars(0x25D0)[0];
    public static final char CIRCLE_WITH_RIGHT_HALF_BLACK = Character.toChars(0x25D1)[0];
    public static final char CIRCLE_WITH_LOWER_HALF_BLACK = Character.toChars(0x25D2)[0];
    public static final char CIRCLE_WITH_UPPER_HALF_BLACK = Character.toChars(0x25D3)[0];
    public static final char CIRCLE_WITH_UPPER_RIGHT_QUADRANT_BLACK = Character.toChars(0x25D4)[0];
    public static final char CIRCLE_WITH_ALL_BUT_UPPER_LEFT_QUADRANT_BLACK = Character
            .toChars(0x25D5)[0];
    public static final char LEFT_HALF_BLACK_CIRCLE = Character.toChars(0x25D6)[0];
    public static final char RIGHT_HALF_BLACK_CIRCLE = Character.toChars(0x25D7)[0];
    public static final char INVERSE_BULLET = Character.toChars(0x25D8)[0];
    public static final char INVERSE_WHITE_CIRCLE = Character.toChars(0x25D9)[0];
    public static final char UPPER_HALF_INVERSE_WHITE_CIRCLE = Character.toChars(0x25DA)[0];
    public static final char LOWER_HALF_INVERSE_WHITE_CIRCLE = Character.toChars(0x25DB)[0];
    public static final char UPPER_LEFT_QUADRANT_CIRCULAR_ARC = Character.toChars(0x25DC)[0];
    public static final char UPPER_RIGHT_QUADRANT_CIRCULAR_ARC = Character.toChars(0x25DD)[0];
    public static final char LOWER_RIGHT_QUADRANT_CIRCULAR_ARC = Character.toChars(0x25DE)[0];
    public static final char LOWER_LEFT_QUADRANT_CIRCULAR_ARC = Character.toChars(0x25DF)[0];
    public static final char UPPER_HALF_CIRCLE = Character.toChars(0x25E0)[0];
    public static final char LOWER_HALF_CIRCLE = Character.toChars(0x25E1)[0];
    public static final char BLACK_LOWER_RIGHT_TRIANGLE = Character.toChars(0x25E2)[0];
    public static final char BLACK_LOWER_LEFT_TRIANGLE = Character.toChars(0x25E3)[0];
    public static final char BLACK_UPPER_LEFT_TRIANGLE = Character.toChars(0x25E4)[0];
    public static final char BLACK_UPPER_RIGHT_TRIANGLE = Character.toChars(0x25E5)[0];
    public static final char WHITE_BULLET = Character.toChars(0x25E6)[0];
    public static final char SQUARE_WITH_LEFT_HALF_BLACK = Character.toChars(0x25E7)[0];
    public static final char SQUARE_WITH_RIGHT_HALF_BLACK = Character.toChars(0x25E8)[0];
    public static final char SQUARE_WITH_UPPER_LEFT_DIAGONAL_HALF_BLACK = Character.toChars(0x25E9)[0];
    public static final char SQUARE_WITH_LOWER_RIGHT_DIAGONAL_HALF_BLACK = Character
            .toChars(0x25EA)[0];
    public static final char WHITE_SQUARE_WITH_VERTICAL_BISECTING_LINE = Character.toChars(0x25EB)[0];
    public static final char WHITE_UP_POINTING_TRIANGLE_WITH_DOT = Character.toChars(0x25EC)[0];
    public static final char UP_POINTING_TRIANGLE_WITH_LEFT_HALF_BLACK = Character.toChars(0x25ED)[0];
    public static final char UP_POINTING_TRIANGLE_WITH_RIGHT_HALF_BLACK = Character.toChars(0x25EE)[0];
    public static final char LARGE_CIRCLE = Character.toChars(0x25EF)[0];
    public static final char WHITE_SQUARE_WITH_UPPER_LEFT_QUADRANT = Character.toChars(0x25F0)[0];
    public static final char WHITE_SQUARE_WITH_LOWER_LEFT_QUADRANT = Character.toChars(0x25F1)[0];
    public static final char WHITE_SQUARE_WITH_LOWER_RIGHT_QUADRANT = Character.toChars(0x25F2)[0];
    public static final char WHITE_SQUARE_WITH_UPPER_RIGHT_QUADRANT = Character.toChars(0x25F3)[0];
    public static final char WHITE_CIRCLE_WITH_UPPER_LEFT_QUADRANT = Character.toChars(0x25F4)[0];
    public static final char WHITE_CIRCLE_WITH_LOWER_LEFT_QUADRANT = Character.toChars(0x25F5)[0];
    public static final char WHITE_CIRCLE_WITH_LOWER_RIGHT_QUADRANT = Character.toChars(0x25F6)[0];
    public static final char WHITE_CIRCLE_WITH_UPPER_RIGHT_QUADRANT = Character.toChars(0x25F7)[0];
    public static final char UPPER_LEFT_TRIANGLE = Character.toChars(0x25F8)[0];
    public static final char UPPER_RIGHT_TRIANGLE = Character.toChars(0x25F9)[0];
    public static final char LOWER_LEFT_TRIANGLE = Character.toChars(0x25FA)[0];
    public static final char WHITE_MEDIUM_SQUARE = Character.toChars(0x25FB)[0];
    public static final char BLACK_MEDIUM_SQUARE = Character.toChars(0x25FC)[0];
    public static final char WHITE_MEDIUM_SMALL_SQUARE = Character.toChars(0x25FD)[0];
    public static final char BLACK_MEDIUM_SMALL_SQUARE = Character.toChars(0x25FE)[0];
    public static final char LOWER_RIGHT_TRIANGLE = Character.toChars(0x25FF)[0];
}