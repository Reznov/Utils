package com.joffrey_bion.utlis.xml.serializers;

/**
 * An XML serializer for arrays of durations expressed as {@link Long}s.
 */
public class DurationArraySerializer extends ArraySerializer<Long> {

    /**
     * Creates a {@link DateArraySerializer}.
     * 
     * @param format
     *            The format to use to represent as {@link String}s the components of
     *            the arrays.
     */
    public DurationArraySerializer(String format) {
        this(new DurationSerializer(format));
    }

    /**
     * Creates a {@code DurationArraySerializer}.
     * 
     * @param componentSerializer
     *            The {@link DurationSerializer} to use for the components of the
     *            arrays handled by this serializer.
     */
    public DurationArraySerializer(DurationSerializer componentSerializer) {
        super(componentSerializer);
    }

    @Override
    protected Long[] getEmptyArray() {
        return new Long[0];
    }

}
