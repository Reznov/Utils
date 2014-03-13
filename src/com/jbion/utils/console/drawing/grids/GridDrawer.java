package com.jbion.utils.console.drawing.grids;

import com.jbion.utils.console.drawing.Drawing;

/**
 * A class containing useful methods to draw grids. It uses the {@code Character}
 * constants in {@link BoxChars}.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class GridDrawer {

	private static final String LF = Drawing.NEW_LINE;

	// external borders
	private char horizontalExt;
	private char verticalExt;
	private char topLeftCorner;
	private char botLeftCorner;
	private char topRightCorner;
	private char botRightCorner;

	// internal borders
	private char horizontalInt;
	private char verticalInt;
	private char cross;

	// T-borders
	private char downT;
	private char upT;
	private char rightT;
	private char leftT;

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
		this(BorderType.LIGHT, BorderType.HEAVY);
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
	 * Returns a {@code String} representing the specified {@link Grid}.
	 * 
	 * @param g
	 *            The {@code Grid} to get the values from.
	 * @return a {@code String} representing the specified {@link Grid}.
	 */
	public String drawGrid(Grid g) {
		StringBuilder sb = new StringBuilder();
		final int cw = g.getCellWidth();
		final int w = g.getWidth();
		final int h = g.getHeight();
		sb.append(Drawing.repeat(horizontalExt, w, cw, downT, topLeftCorner, topRightCorner));
		sb.append(LF);
		for (int i = 0; i < h; i++) {
			sb.append(verticalExt);
			for (int j = 0; j < w; j++) {
				sb.append(g.getValueAt(i, j));
				if (j < w - 1) {
					sb.append(verticalInt);
				} else {
					sb.append(verticalExt);
				}
			}
			sb.append(LF);
			if (i < h - 1) {
				sb.append(Drawing.repeat(horizontalInt, w, cw, cross, rightT, leftT));
			} else {
				sb.append(Drawing.repeat(horizontalExt, w, cw, upT, botLeftCorner, botRightCorner));
			}
			sb.append(LF);
		}
		return sb.toString();
	}

	/**
	 * Updates the external border characters with the current {@link BorderType}
	 * {@link #externalType}.
	 */
	private void updateExternalBorders() {
		switch (externalType) {
		case LIGHT:
			horizontalExt = BoxChars.BOX_DRAWINGS_LIGHT_HORIZONTAL;
			verticalExt = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL;
			topLeftCorner = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT;
			botLeftCorner = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT;
			topRightCorner = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT;
			botRightCorner = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_LEFT;
			break;
		case HEAVY:
			horizontalExt = BoxChars.BOX_DRAWINGS_HEAVY_HORIZONTAL;
			verticalExt = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL;
			topLeftCorner = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_RIGHT;
			botLeftCorner = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_RIGHT;
			topRightCorner = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_LEFT;
			botRightCorner = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_LEFT;
			break;
		case DOUBLE:
			horizontalExt = BoxChars.BOX_DRAWINGS_DOUBLE_HORIZONTAL;
			verticalExt = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL;
			topLeftCorner = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_RIGHT;
			botLeftCorner = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_RIGHT;
			topRightCorner = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_LEFT;
			botRightCorner = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_LEFT;
		}
	}

	/**
	 * Updates the internal border characters with the current {@link BorderType}
	 * {@link #internalType}.
	 */
	private void updateInternalBorders() {
		switch (internalType) {
		case LIGHT:
			horizontalInt = BoxChars.BOX_DRAWINGS_LIGHT_HORIZONTAL;
			verticalInt = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL;
			cross = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL;
			break;
		case HEAVY:
			horizontalInt = BoxChars.BOX_DRAWINGS_HEAVY_HORIZONTAL;
			verticalInt = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL;
			cross = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_HORIZONTAL;
			break;
		case DOUBLE:
			horizontalInt = BoxChars.BOX_DRAWINGS_DOUBLE_HORIZONTAL;
			verticalInt = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL;
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
				downT = BoxChars.BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL;
				upT = BoxChars.BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL;
				rightT = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT;
				leftT = BoxChars.BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT;
				break;
			case HEAVY:
				downT = BoxChars.BOX_DRAWINGS_DOWN_HEAVY_AND_HORIZONTAL_LIGHT;
				upT = BoxChars.BOX_DRAWINGS_UP_HEAVY_AND_HORIZONTAL_LIGHT;
				rightT = BoxChars.BOX_DRAWINGS_VERTICAL_LIGHT_AND_RIGHT_HEAVY;
				leftT = BoxChars.BOX_DRAWINGS_VERTICAL_LIGHT_AND_LEFT_HEAVY;
				break;
			case DOUBLE:
				downT = BoxChars.BOX_DRAWINGS_DOWN_DOUBLE_AND_HORIZONTAL_SINGLE;
				upT = BoxChars.BOX_DRAWINGS_UP_DOUBLE_AND_HORIZONTAL_SINGLE;
				rightT = BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_RIGHT_DOUBLE;
				leftT = BoxChars.BOX_DRAWINGS_VERTICAL_SINGLE_AND_LEFT_DOUBLE;
			}
			break;
		case HEAVY:
			switch (internalType) {
			case LIGHT:
				downT = BoxChars.BOX_DRAWINGS_DOWN_LIGHT_AND_HORIZONTAL_HEAVY;
				upT = BoxChars.BOX_DRAWINGS_UP_LIGHT_AND_HORIZONTAL_HEAVY;
				rightT = BoxChars.BOX_DRAWINGS_VERTICAL_HEAVY_AND_RIGHT_LIGHT;
				leftT = BoxChars.BOX_DRAWINGS_VERTICAL_HEAVY_AND_LEFT_LIGHT;
				break;
			case HEAVY:
				downT = BoxChars.BOX_DRAWINGS_HEAVY_DOWN_AND_HORIZONTAL;
				upT = BoxChars.BOX_DRAWINGS_HEAVY_UP_AND_HORIZONTAL;
				rightT = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_RIGHT;
				leftT = BoxChars.BOX_DRAWINGS_HEAVY_VERTICAL_AND_LEFT;
				break;
			case DOUBLE:
				throw new IllegalStateException("Heavy/Double border combination not supported.");
			}
			break;
		case DOUBLE:
			switch (internalType) {
			case LIGHT:
				downT = BoxChars.BOX_DRAWINGS_DOWN_SINGLE_AND_HORIZONTAL_DOUBLE;
				upT = BoxChars.BOX_DRAWINGS_UP_SINGLE_AND_HORIZONTAL_DOUBLE;
				rightT = BoxChars.BOX_DRAWINGS_VERTICAL_DOUBLE_AND_RIGHT_SINGLE;
				leftT = BoxChars.BOX_DRAWINGS_VERTICAL_DOUBLE_AND_LEFT_SINGLE;
				break;
			case HEAVY:
				throw new IllegalStateException("Heavy/Double border combination not supported.");
			case DOUBLE:
				downT = BoxChars.BOX_DRAWINGS_DOUBLE_DOWN_AND_HORIZONTAL;
				upT = BoxChars.BOX_DRAWINGS_DOUBLE_UP_AND_HORIZONTAL;
				rightT = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL_AND_RIGHT;
				leftT = BoxChars.BOX_DRAWINGS_DOUBLE_VERTICAL_AND_LEFT;
			}
		}
	}
}
