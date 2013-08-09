package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.joffrey_bion.utils.xml.XmlHelper;

/**
 * Represents an XML serializer for any class simple enough to be serialized as a
 * {@code String}.
 * <p>
 * The method {@link #deserialize(String)} has to be implemented in subclasses.
 * However, it is not necessary to override {@link #serialize(Object)}. Indeed, a
 * default version using the method {@link Object#toString()} is already implemented,
 * and is sufficient in most cases.
 * </p>
 * 
 * @see Serializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class SimpleSerializer<T> extends Serializer<T> {

    /**
     * An XML serializer for {@link Boolean}s.
     */
    public static final SimpleSerializer<Boolean> BOOLEAN = new SimpleSerializer<Boolean>(
            Boolean.class) {
        /**
         * Acceptable {@code String}s for {@code true}.
         */
        private final String[] trues = { Boolean.toString(true), "true", "t", "yes", "y", "1" };
        /**
         * Acceptable {@code String}s for {@code false}.
         */
        private final String[] falses = { Boolean.toString(false), "false", "f", "no", "n", "0" };

        @Override
        public Boolean deserialize(String s) throws ParseException {
            for (String t : trues) {
                if (t.equalsIgnoreCase(s)) {
                    return true;
                }
            }
            for (String f : falses) {
                if (f.equalsIgnoreCase(s)) {
                    return false;
                }
            }
            throw new ParseException("'" + s + "' not recognized as a boolean value", 0);
        }
    };
    /**
     * An XML serializer for {@link Byte}s.
     */
    public static final SimpleSerializer<Byte> BYTE = new SimpleSerializer<Byte>(Byte.class) {
        @Override
        public Byte deserialize(String s) {
            return Byte.parseByte(s);
        }
    };
    /**
     * An XML serializer for {@link Character}s.
     */
    public static final SimpleSerializer<Character> CHARACTER = new SimpleSerializer<Character>(
            Character.class) {
        @Override
        public Character deserialize(String s) throws ParseException {
            if (s.length() != 1) {
                throw new ParseException("Cannot parse '" + s + "' as a Character.", 1);
            }
            return s.charAt(0);
        }
    };
    /**
     * An XML serializer for {@link Double}s.
     */
    public static final SimpleSerializer<Double> DOUBLE = new SimpleSerializer<Double>(Double.class) {
        @Override
        public Double deserialize(String s) {
            return Double.parseDouble(s);
        }
    };
    /**
     * An XML serializer for {@link Float}s.
     */
    public static final SimpleSerializer<Float> FLOAT = new SimpleSerializer<Float>(Float.class) {
        @Override
        public Float deserialize(String s) {
            return Float.parseFloat(s);
        }
    };
    /**
     * An XML serializer for {@link Integer}s.
     */
    public static final SimpleSerializer<Integer> INTEGER = new SimpleSerializer<Integer>(
            Integer.class) {
        @Override
        public Integer deserialize(String s) {
            return Integer.parseInt(s);
        }
    };
    /**
     * An XML serializer for {@link Long}s.
     */
    public static final SimpleSerializer<Long> LONG = new SimpleSerializer<Long>(Long.class) {
        @Override
        public Long deserialize(String s) {
            return Long.parseLong(s);
        }
    };
    /**
     * An XML serializer for {@link Short}s.
     */
    public static final SimpleSerializer<Short> SHORT = new SimpleSerializer<Short>(Short.class) {
        @Override
        public Short deserialize(String s) {
            return Short.parseShort(s);
        }
    };
    /**
     * An XML serializer for {@link String}s.
     */
    public static final SimpleSerializer<String> STRING = new SimpleSerializer<String>(String.class) {
        @Override
        public String deserialize(String s) {
            return s;
        }
    };

    /**
     * Creates a {@link SimpleSerializer} for the specified class.
     * 
     * @param clazz
     *            The class handled by this serializer, which should correspond to
     *            the type variable of this generic class.
     */
    public SimpleSerializer(Class<T> clazz) {
        super(clazz);
    }

    /**
     * Converts the specified object into a {@code String}.
     * 
     * @param object
     *            The object to serialize.
     * @return The serialized {@code String}.
     * @throws ClassCastException
     *             If the class of the specified object is not consistent with the
     *             class handled by this serializer.
     */
    public String serialize(Object object) throws ClassCastException {
        if (object == null) {
            return "null";
        }
        return cast(object).toString();
    }

    /**
     * Retrieves an object from the specified {@code String}.
     * 
     * @param s
     *            The {@code String} to convert, which is guaranteed not to be null,
     *            though it may be equal to "null".
     * @return The created object.
     * @throws ParseException
     *             If the {@code String} cannot be parsed as an object of the class
     *             handled by this serializer.
     */
    public abstract T deserialize(String s) throws ParseException;

    @Override
    protected Element nonNullObjectToXml(Document doc, String tag, T object) {
        return XmlHelper.createField(doc, tag, serialize(object));
    }

    @Override
    protected T xmlToNonNullObject(Element e) throws ParseException {
        String s = XmlHelper.getContent(e);
        if (s == null) {
            return null;
        }
        return deserialize(s);
    }
}
