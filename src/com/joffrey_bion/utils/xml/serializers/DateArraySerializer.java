package com.joffrey_bion.utils.xml.serializers;

/**
 * An XML serializer for arrays of dates expressed as {@link Long}s.
 */
public class DateArraySerializer extends ArraySerializer<Long> {

    /**
     * Creates a {@link DateArraySerializer}.
     * 
     * @param format
     *            The format to use to represent as {@link String}s the components of
     *            the arrays.
     */
    public DateArraySerializer(String format) {
        this(new DateSerializer(format));
    }

    /**
     * Creates a {@link DateArraySerializer}.
     * 
     * @param componentSerializer
     *            The {@link DateSerializer} to use for the components of the arrays
     *            handled by this serializer.
     */
    public DateArraySerializer(DateSerializer componentSerializer) {
        super(componentSerializer);
    }

    @Override
    protected Long[] getEmptyArray() {
        return new Long[0];
    }

}
