package com.jbion.utils.io.binary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a stream of bits. It wraps any {@link InputStream}, buffering it so
 * that it can be accessed bit by bit. A {@link BitInputStream} also allows to access
 * unaligned {@code byte}s, {@code char} s, {@code int}s and {@code long}s.
 */
public class BitInputStream extends BufferedInputStream {

    /**
     * The buffer of bits. Only the right-most bits (least significant) are used.
     */
    private long buffer = 0;
    /**
     * Indicates how many bits of the buffer are currently used.
     */
    private int bufferLength = 0;

    /**
     * Creates a new {@link BitInputStream} wrapping the specified
     * {@link InputStream}.
     * 
     * @param in
     *            the {@link InputStream} to wrap
     */
    public BitInputStream(InputStream in) {
        super(in);
    }

    /**
     * Creates a new {@link BitInputStream} wrapping the specified
     * {@link InputStream}.
     * 
     * @param in
     *            the {@link InputStream} to wrap
     * @param size
     *            the buffer size for the underlying {@link BufferedInputStream}
     */
    public BitInputStream(InputStream in, int size) {
        super(in, size);
    }

    /**
     * Reads the next byte from this stream. If less than 8 bits are available
     * because the end of the stream has been reached, the value -1 is returned.
     * 
     * @see InputStream#read()
     */
    @Override
    public synchronized int read() throws IOException {
        try {
            return readByte();
        } catch (IllegalStateException ise) {
            // end of stream reached
            return -1;
        }
    }

    /**
     * Retrieves and removes the first {@code length} bits from the buffer.
     * 
     * @param length
     *            the number of bits to get
     * @return the long value representing the bits taken from the buffer
     */
    private synchronized long pollBitsFromBuffer(int length) {
        assert bufferLength >= length : "buffer too short!";
        long leftBits = buffer >>> (bufferLength - length);
        buffer -= leftBits << (bufferLength - length);
        bufferLength -= length;
        return leftBits;
    }

    /**
     * Reads the next bit from this stream.
     *
     * @return 1 or 0 depending on the read bit, or -1 if the end of stream was
     *         reached
     * @throws IOException
     *             if an I/O error occurs
     */
    public synchronized int readBit() throws IOException {
        assert bufferLength >= 0 : "buffer has negative length";
        if (bufferLength == 0) {
            int octet = super.read();
            if (octet == -1) {
                return -1;
            }
            buffer = octet;
            bufferLength = 8;
        }
        return (int) pollBitsFromBuffer(1);
    }

    /**
     * Reads up to {@link Long#SIZE} bits as a long value.
     * 
     * @param length
     *            the number of bits to read. Must not exceed the number of remaining
     *            bits in this stream.
     * @return the long value of the read bits
     * @throws IllegalStateException
     *             if the end of stream is reached before the specified number of
     *             bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public synchronized long readBits(int length) throws IOException {
        if (length > Long.SIZE) {
            throw new IllegalArgumentException("can't read more bits than the size of a long");
        }
        while (bufferLength < length) {
            int octet = super.read();
            if (octet == -1) {
                throw new IllegalStateException("end of stream reached, cannot feed the buffer");
            }
            buffer = (buffer << 8) + octet;
            bufferLength += 8;
        }
        return pollBitsFromBuffer(length);
    }

    /**
     * Reads a bit from this stream.
     *
     * @return {@code true} for a 1 and {@code false} for a 0, or {@code null} if the
     *         end of stream was reached
     * @throws IOException
     *             if an I/O error occurs
     */
    public Boolean readBitAsBoolean() throws IOException {
        final int bit = readBit();
        if (bit == 0) {
            return false;
        } else if (bit == 1) {
            return true;
        } else {
            return null;
        }
    }

    /**
     * Reads the next {@link Byte#SIZE} bits from this stream as a {@code byte} from
     * this stream.
     *
     * @return the read {@code byte}
     * @throws IllegalStateException
     *             if the end of stream is reached before enough bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public byte readByte() throws IOException {
        return (byte) readBits(Byte.SIZE);
    }

    /**
     * Reads the next {@link Character#SIZE} bits from this stream as a {@code char}
     * from this stream.
     *
     * @return the read {@code char}
     * @throws IllegalStateException
     *             if the end of stream is reached before enough bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public char readChar() throws IOException {
        return (char) readBits(Character.SIZE);
    }

    /**
     * Reads the next {@link Integer#SIZE} bits from this stream as an {@code int}.
     *
     * @return the read {@code int}
     * @throws IllegalStateException
     *             if the end of stream is reached before enough bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public int readInt() throws IOException {
        return (int) readBits(Integer.SIZE);
    }

    /**
     * Reads the next {@link Long#SIZE} bits from this stream as a {@code long} from
     * this stream.
     *
     * @return the read {@code long}
     * @throws IllegalStateException
     *             if the end of stream is reached before enough bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public long readLong() throws IOException {
        return readBits(Long.SIZE);
    }

    /**
     * Reads a binary String representing the next {@code length} bits in this
     * stream.
     *
     * @param length
     *            the number of bits to read
     * @return a binary {@code String} representing the bits read. The left-most
     *         characters are the first bits read from the stream.
     * @throws IllegalStateException
     *             if the end of stream is reached before the specified number of
     *             bits could be read
     * @throws IOException
     *             if an I/O error occurs
     */
    public String readString(int length) throws IOException {
        int remainingLength = length;
        final StringBuilder sb = new StringBuilder();
        while (remainingLength > 0) {
            int toRead = Math.min(remainingLength, Long.SIZE);
            String tempBits = Long.toBinaryString(readBits(toRead));
            sb.append(BinHelper.addLeadingZeros(tempBits, toRead));
            remainingLength -= toRead;
        }
        return sb.toString();
    }

    /**
     * Reads a long value preceded by 6 bits indicating the number of bits used to
     * write it. As an example, 000000 means that 1 bit follows, 111111 meaning that
     * 64 bits follow.
     *
     * @return The read {@code Long}.
     * @throws IOException
     *             If any I/O error occurs.
     */
    public Long readLongWithLength() throws IOException {
        final int length = (int) readBits(6) + 1;
        return readBits(length);
    }
}
