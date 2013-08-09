package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

/**
 * An XML serializer for any enum type, using {@link Enum#toString()} and
 * {@link Enum#valueOf(Class, String)}. If this behaviour is not sufficient,
 * {@link SimpleSerializer} can be subclassed instead of using this class.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class EnumSerializer<T extends Enum<T>> extends SimpleSerializer<T> {

    /**
     * Creates a new {@link EnumSerializer} for the specified enum class.
     * 
     * @param clazz
     *            A class that extends {@link Enum}, and which has to be consistent with
     *            the type variable.
     */
    public EnumSerializer(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T deserialize(String s) throws ParseException {
        return Enum.valueOf(clazz, s);
    }

}
