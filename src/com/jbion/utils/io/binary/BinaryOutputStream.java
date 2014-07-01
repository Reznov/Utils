package com.jbion.utils.io.binary;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryOutputStream extends BufferedOutputStream {

    private String buffer = "";

    public BinaryOutputStream(OutputStream out) {
        super(out);
    }

    public BinaryOutputStream(OutputStream out, int size) {
        super(out, size);
    }

    /**
     * Writes the given binary string to a buffer that will be written byte by byte.
     *
     * @param binaryString
     *            A binary {@code String}. This {@code String} must contain only the
     *            characters '0' or '1'.
     * @throws IOException
     *             if an I/O error occurs
     */
    public void write(String binaryString) throws IOException {
        if (!binaryString.matches("{0|1}*")) {
            throw new IllegalArgumentException("The input string '" + binaryString + "' must contain only 0s and 1s.");
        }
        buffer += binaryString;
        // write the excess byte by byte
        while (buffer.length() >= 8) {
            writeByte(buffer.substring(0, 8));
            buffer = buffer.substring(8);
        }
    }

    /**
     * Writes the specified bit to this stream.
     *
     * @param bit
     *            the bit to write: 0 or 1
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeBit(int bit) throws IOException {
        if (bit == 1) {
            write("1");
        } else if (bit == 0) {
            write("0");
        } else {
            throw new IllegalArgumentException("The specified integer is neither 0 nor 1.");
        }
    }

    /**
     * Writes the specified bit to this stream.
     *
     * @param bit
     *            the bit to write: {@code true} for 1, {@code false} for 0
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeBit(boolean bit) throws IOException {
        if (bit) {
            write("1");
        } else {
            write("0");
        }
    }

    /**
     * Writes the given long to this stream, with leading zeros to reach
     * {@link Long#SIZE}.
     *
     * @param value
     *            The value to write.
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeLong(long value) throws IOException {
        writeValue(value, Long.SIZE);
    }

    /**
     * Writes the given integer to this stream, with leading zeros to reach
     * {@link Integer#SIZE}.
     *
     * @param value
     *            The value to write.
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeInteger(int value) throws IOException {
        writeValue(value, Integer.SIZE);
    }

    /**
     * Writes the given character's code to this stream, with leading zeros to reach
     * {@link Character#SIZE}.
     *
     * @param value
     *            The value to write.
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeCharacter(char value) throws IOException {
        writeValue(value, Character.SIZE);
    }

    /**
     * Writes the given value to this stream, with leading zeros to reach
     * {@code size}.
     *
     * @param value
     *            The value to write to this stream.
     * @param size
     *            The number of bits to use to write the value.
     * @throws IOException
     *             if an I/O error occurs
     */
    private void writeValue(long value, int size) throws IOException {
        final String binStr = BinHelper.addLeadingZeros(Long.toBinaryString(value), size);
        write(binStr);
    }

    /**
     * Writes the given int value to this stream, preceded by 5 bits indicating the
     * number of bits used to write it.
     *
     * @param value
     *            The value to write.
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeIntegerWithLength(int value) throws IOException {
        writeWithLength(value, 5);
    }

    /**
     * Writes the given long value to this stream, preceded by 6 bits indicating the
     * number of bits used to write it.
     *
     * @param value
     *            the value to write
     * @throws IOException
     *             if an I/O error occurs
     */
    public void writeLongWithLength(long value) throws IOException {
        writeWithLength(value, 6);
    }

    /**
     * Writes the given {@code value} to this stream, preceded by
     * {@code magnitudeLength} bits indicating the number of bits used to write
     * {@code value}.
     *
     * @param value
     *            the value to write to this stream
     * @param magnitudeLength
     *            the number of bits used to indicate the number of bits used for
     *            {@code value} (beware of the number-of-bit-ception!)
     * @throws IOException
     *             if an I/O error occurs
     */
    private void writeWithLength(long value, int magnitudeLength) throws IOException {
        final String binStr = Long.toBinaryString(value);
        String magnitudeStr = Integer.toBinaryString(binStr.length() - 1);
        magnitudeStr = BinHelper.addLeadingZeros(magnitudeStr, magnitudeLength);
        write(magnitudeStr);
        write(binStr);
    }

    /**
     * Only call this method from {@link #write(String)}.
     *
     * @param byteStr
     *            a 8-char long binary String to write
     * @throws IOException
     *             if an I/O error occurs
     */
    private void writeByte(String byteStr) throws IOException {
        if (byteStr.length() != 8) {
            throw new IllegalArgumentException("Wrong length (" + byteStr.length() + ") for a byte, 8 expected");
        }
        try {
            final int b = Integer.parseInt(byteStr, 2);
            write(b); // TODO test with an integer > 255
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Binary string expected, got '" + byteStr + "'");
        }

    }

    /**
     * Closes this stream, flushing the buffer.
     */
    @Override
    public void close() {
        try {
            if (buffer.length() != 0) {
                writeByte(completeByte(buffer));
            }
            super.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Completes a binary {@code String} shorter than 8 bits to a full byte.
     *
     * @param bin
     *            a {@code String} containing only characters '0' and '1', and
     *            shorter than 8 chars
     * @return the input {@code String} plus as many trailing 0s as needed to
     *         complete a byte
     */
    private static String completeByte(String bin) {
        final int length = bin.length();
        if (length > 8) {
            throw new IllegalArgumentException("The string must be shorter than a byte.");
        }
        String res = bin;
        for (int i = 8; i > length; i--) {
            res += "0";
        }
        return res;
    }
}
