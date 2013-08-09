package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

import com.joffrey_bion.utils.dates.DateHelper;

/**
 * An XML serializer for dates expressed as {@link Long}s in milliseconds.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class DateSerializer extends SimpleSerializer<Long> {

    private final String format;

    /**
     * Creates an XML serializer for dates.
     * 
     * @param format
     *            The format to use to represent the date as a {@link String}.
     */
    public DateSerializer(String format) {
        super(Long.class);
        this.format = format;
    }

    @Override
    public String serialize(Object millis) throws ClassCastException {
        return DateHelper.format((Long) millis, format);
    }

    @Override
    public Long deserialize(String s) throws ParseException {
        return DateHelper.timestampStrToMillis(s, format);
    }

}
