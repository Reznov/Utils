package com.jbion.utils.io.binary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BinaryInputStream extends BufferedInputStream {

	private String buffer = "";

	public BinaryInputStream(InputStream in) {
		super(in);
	}

	public BinaryInputStream(InputStream in, int size) {
		super(in, size);
	}

	/**
	 * Reads the specified number of bits.
	 * 
	 * @param length
	 *            The number of bits to read.
	 * @return A binary {@code String} representing the bits read. The left-most
	 *         characters are the first bits read from the stream.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public String readBits(int length) throws IOException {
		int octet;
		while (buffer.length() < length && (octet = read()) != -1) {
			buffer += BinHelper.addLeadingZeros(Integer.toBinaryString(octet),
					8);
		}
		if (buffer.length() < length) {
			throw new IOException("No more bits to read!");
		}
		String res = buffer.substring(0, length);
		buffer = buffer.substring(length);
		return res;
	}

	/**
	 * Reads a bit from this stream.
	 * 
	 * @return 1 or 0.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public int readBitAsInt() throws IOException {
		String bit = readBits(1);
		if ("0".equals(bit)) {
			return 0;
		} else if ("1".equals(bit)) {
			return 1;
		} else {
			// impossible situation
			throw new RuntimeException("Non binary string read.");
		}
	}

	/**
	 * Reads a bit from this stream.
	 * 
	 * @return {@code true} for a 1 and {@code false} for a 0.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public boolean readBitAsBoolean() throws IOException {
		String bit = readBits(1);
		if ("0".equals(bit)) {
			return false;
		} else if ("1".equals(bit)) {
			return true;
		} else {
			// impossible situation
			throw new RuntimeException("Non binary string read.");
		}
	}

	/**
	 * Reads {@link Integer#SIZE} bits as an {@code int} from this stream.
	 * 
	 * @return The read {@code int}.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public int readInt() throws IOException {
		return (int) readValue(Integer.SIZE);
	}

	/**
	 * Reads {@link Long#SIZE} bits as a {@code long} from this stream.
	 * 
	 * @return The read {@code long}.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public long readLong() throws IOException {
		return readValue(Long.SIZE);
	}

	/**
	 * Reads {@link Character#SIZE} bits as a {@code char} from this stream.
	 * 
	 * @return The read {@code char}.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public char readChar() throws IOException {
		return (char) readValue(Character.SIZE);
	}

	/**
	 * Reads a numeric value from the next {@code length} bits.
	 * 
	 * @param length
	 *            the number of bits to convert to a long value.
	 * @return The value represented by the next {@code length} bits.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	private long readValue(int length) throws IOException {
		String binStr = readBits(length);
		return Long.parseLong(binStr, 2);
	}

	/**
	 * Reads a long value preceded by 6 bits indicating the number of bits used
	 * to write it. As an example, 000000 means that 1 bit follows, 111111
	 * meaning that 64 bits follow.
	 * 
	 * @return The read {@code Long}.
	 * @throws IOException
	 *             If any I/O error occurs.
	 */
	public Long readLongWithLength() throws IOException {
		int length = (int) readValue(6) + 1;
		return readValue(length);
	}
}
