package com.joffrey_bion.utlis.xml.serializers;

import java.text.ParseException;

import com.joffrey_bion.utils.dates.DateHelper;

/**
 * An XML serializer for dates expressed as {@link Long}s in milliseconds.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class DateSerializer extends SimpleClassSerializer<Long> {

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
    protected String serialize(Long millis) {
        return DateHelper.format(millis, format);
    }

    @Override
    protected Long deserialize(String s) throws ParseException {
        return DateHelper.timestampStrToMillis(s, format);
    }

}
