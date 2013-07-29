package com.joffrey_bion.utlis.xml.serializers;

import java.text.ParseException;

import com.joffrey_bion.utils.dates.DurationHelper;

/**
 * An XML serializer for durations expressed as {@link Long}s in milliseconds.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class DurationSerializer extends SimpleClassSerializer<Long> {

    private final String format;

    /**
     * Creates an XML serializer for durations.
     * 
     * @param format
     *            The format to use to represent the duration as a {@link String}.
     */
    public DurationSerializer(String format) {
        super(Long.class);
        this.format = format;
    }

    @Override
    protected String serialize(Long millis) {
        return DurationHelper.format(millis, format);
    }

    @Override
    protected Long deserialize(String s) throws ParseException {
        return DurationHelper.strToMillis(s, format);
    }
}
