package com.joffrey_bion.utlis.xml.serializers;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.joffrey_bion.utils.xml.XmlHelper;

/**
 * Represents an XML serializer for any class simple enough to be serialized as a
 * {@code String}.
 * 
 * @see Serializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class SimpleClassSerializer<T> extends Serializer<T> {

    /**
     * An XML serializer for {@link Boolean}s.
     */
    static final SimpleClassSerializer<Boolean> BOOLEAN = new SimpleClassSerializer<Boolean>(
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
    static final SimpleClassSerializer<Byte> BYTE = new SimpleClassSerializer<Byte>(
            Byte.class) {
        @Override
        public Byte deserialize(String s) {
            return Byte.parseByte(s);
        }
    };
    /**
     * An XML serializer for {@link Character}s.
     */
    static final SimpleClassSerializer<Character> CHARACTER = new SimpleClassSerializer<Character>(
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
    static final SimpleClassSerializer<Double> DOUBLE = new SimpleClassSerializer<Double>(
            Double.class) {
        @Override
        public Double deserialize(String s) {
            return Double.parseDouble(s);
        }
    };
    /**
     * An XML serializer for {@link Float}s.
     */
    static final SimpleClassSerializer<Float> FLOAT = new SimpleClassSerializer<Float>(
            Float.class) {
        @Override
        public Float deserialize(String s) {
            return Float.parseFloat(s);
        }
    };
    /**
     * An XML serializer for {@link Integer}s.
     */
    static final SimpleClassSerializer<Integer> INTEGER = new SimpleClassSerializer<Integer>(
            Integer.class) {
        @Override
        public Integer deserialize(String s) {
            return Integer.parseInt(s);
        }
    };
    /**
     * An XML serializer for {@link Long}s.
     */
    static final SimpleClassSerializer<Long> LONG = new SimpleClassSerializer<Long>(Long.class) {
        @Override
        public Long deserialize(String s) {
            return Long.parseLong(s);
        }
    };
    /**
     * An XML serializer for {@link Short}s.
     */
    static final SimpleClassSerializer<Short> SHORT = new SimpleClassSerializer<Short>(Short.class) {
        @Override
        public Short deserialize(String s) {
            return Short.parseShort(s);
        }
    };
    /**
     * An XML serializer for {@link String}s.
     */
    static final SimpleClassSerializer<String> STRING = new SimpleClassSerializer<String>(
            String.class) {
        @Override
        public String deserialize(String s) {
            return s;
        }
    };

    /**
     * Creates a {@code SimpleTypeSerializer} for the specified class.
     * 
     * @param clazz
     *            The class handled by this serializer, which should correspond to the
     *            type variable of this generic class.
     */
    public SimpleClassSerializer(Class<T> clazz) {
        super(clazz);
    }

    /**
     * Retrieves an object from the specified {@code String}.
     * 
     * @param s
     *            The {@code String} to convert, which is guaranteed not to be null.
     * @return The created object.
     * @throws ParseException
     *             If the {@code String} cannot be parsed as an object of the class
     *             handled by this serializer.
     */
    protected abstract T deserialize(String s) throws ParseException;

    /**
     * Converts the specified object into a {@code String}.
     * 
     * @param object
     *            The non-null object to serialize.
     * @return The serialized {@code String}.
     */
    protected String serialize(T object) {
        return object.toString();
    }

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
