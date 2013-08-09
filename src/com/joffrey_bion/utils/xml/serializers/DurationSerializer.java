package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

import com.joffrey_bion.utils.dates.DurationHelper;

/**
 * An XML serializer for durations expressed as {@link Long}s in milliseconds.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class DurationSerializer extends SimpleSerializer<Long> {

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
    public String serialize(Object millis) throws ClassCastException {
        return DurationHelper.format((Long) millis, format);
    }

    @Override
    public Long deserialize(String s) throws ParseException {
        return DurationHelper.strToMillis(s, format);
    }
}
