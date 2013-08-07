package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Represents an XML serializer for the class given to the constructor, which also
 * corresponds to the type variable of this generic class.
 * <p>
 * This base abstract class is already subclassed into {@link SimpleClassSerializer}s
 * and {@link ArraySerializer}s for some basic classes. These anonymous subclasses
 * are instantiated in the public fields of this class. In particular, there are
 * {@code Serializer}s for every primitive type wrapper class, and the corresponding
 * array classes.
 * </p>
 * <p>
 * This class should not be directly subclassed. To create your own
 * {@link Serializer}, extending either {@link ObjectSerializer},
 * {@link SimpleClassSerializer} or {@link ArraySerializer} should be sufficient.
 * </p>
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class Serializer<T> {

    /**
     * An XML serializer for {@link Boolean}s.
     */
    public static final SimpleClassSerializer<Boolean> BOOLEAN = SimpleClassSerializer.BOOLEAN;
    /**
     * An XML serializer for {@link Byte}s.
     */
    public static final SimpleClassSerializer<Byte> BYTE = SimpleClassSerializer.BYTE;
    /**
     * An XML serializer for {@link Character}s.
     */
    public static final SimpleClassSerializer<Character> CHARACTER = SimpleClassSerializer.CHARACTER;
    /**
     * An XML serializer for {@link Double}s.
     */
    public static final SimpleClassSerializer<Double> DOUBLE = SimpleClassSerializer.DOUBLE;
    /**
     * An XML serializer for {@link Float}s.
     */
    public static final SimpleClassSerializer<Float> FLOAT = SimpleClassSerializer.FLOAT;
    /**
     * An XML serializer for {@link Integer}s.
     */
    public static final SimpleClassSerializer<Integer> INTEGER = SimpleClassSerializer.INTEGER;
    /**
     * An XML serializer for {@link Long}s.
     */
    public static final SimpleClassSerializer<Long> LONG = SimpleClassSerializer.LONG;
    /**
     * An XML serializer for {@link Short}s.
     */
    public static final SimpleClassSerializer<Short> SHORT = SimpleClassSerializer.SHORT;
    /**
     * An XML serializer for {@link String}s.
     */
    public static final SimpleClassSerializer<String> STRING = SimpleClassSerializer.STRING;
    /**
     * An XML serializer for arrays of {@link Boolean}.
     */
    public static final ArraySerializer<Boolean> BOOLEAN_ARRAY = ArraySerializer.BOOLEAN_ARRAY;
    /**
     * An XML serializer for arrays of {@link Byte}.
     */
    public static final ArraySerializer<Byte> BYTE_ARRAY = ArraySerializer.BYTE_ARRAY;
    /**
     * An XML serializer for arrays of {@link Character}.
     */
    public static final ArraySerializer<Character> CHARACTER_ARRAY = ArraySerializer.CHARACTER_ARRAY;
    /**
     * An XML serializer for arrays of {@link Double}.
     */
    public static final ArraySerializer<Double> DOUBLE_ARRAY = ArraySerializer.DOUBLE_ARRAY;
    /**
     * An XML serializer for arrays of {@link Float}.
     */
    public static final ArraySerializer<Float> FLOAT_ARRAY = ArraySerializer.FLOAT_ARRAY;
    /**
     * An XML serializer for arrays of {@link Integer}.
     */
    public static final ArraySerializer<Integer> INTEGER_ARRAY = ArraySerializer.INTEGER_ARRAY;
    /**
     * An XML serializer for arrays of {@link Long}.
     */
    public static final ArraySerializer<Long> LONG_ARRAY = ArraySerializer.LONG_ARRAY;
    /**
     * An XML serializer for arrays of {@link Short}.
     */
    public static final ArraySerializer<Short> SHORT_ARRAY = ArraySerializer.SHORT_ARRAY;
    /**
     * An XML serializer for arrays of {@link String}.
     */
    public static final ArraySerializer<String> STRING_ARRAY = ArraySerializer.STRING_ARRAY;

    /**
     * The attribute name to indicate a {@code null} object.
     */
    private static final String IS_NULL = "null";
    /**
     * The attribute value to indicate a {@code null} object.
     */
    private static final String TRUE = "true";

    /**
     * The class of the objects handled by this serializer.
     */
    protected final Class<T> clazz;

    /**
     * Creates a new serializer to handle objects of the specified class.
     * 
     * @param clazz
     *            The class this serializer handles, which should correspond to the
     *            type variable of this generic class.
     */
    protected Serializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Returns the name of the class handled by this serializer.
     * 
     * @return the name of the class handled by this serializer.
     */
    public String getTypeName() {
        return clazz.getSimpleName();
    }

    /**
     * Casts the specified {@code Object} into an object of the handled class.
     * 
     * @param obj
     *            The {@code Object} to convert.
     * @return The converted object.
     * @throws ClassCastException
     *             If the specified object does not match the class handled by this
     *             serializer.
     */
    public T cast(Object obj) throws ClassCastException {
        return clazz.cast(obj);
    }

    /**
     * Creates an XML {@link Element} representing the specified object.
     * 
     * @param doc
     *            The {@link Document} to use to create the {@code Element}.
     * @param tag
     *            The name of the {@code Element} node to use.
     * @param object
     *            The object to serialize, which may be null.
     * @return The created {@link Element}.
     * @throws ClassCastException
     *             If the specified object does not match the class handled by this
     *             serializer.
     */
    public Element objectToXml(Document doc, String tag, Object object) throws ClassCastException {
        if (object == null) {
            Element root = doc.createElement(tag);
            root.setAttribute(IS_NULL, TRUE);
            return root;
        }
        return nonNullObjectToXml(doc, tag, cast(object));
    }

    /**
     * Creates an object corresponding to the specified {@link Element}.
     * 
     * @param e
     *            The {@code Element} to deserialize.
     * @return The created object, which may be {@code null}.
     * @throws ParseException
     *             If the XML {@code Element} does not correspond to an object of the
     *             class this serializer handles.
     */
    public T xmlToObject(Element e) throws ParseException {
        if (TRUE.equals(e.getAttribute(IS_NULL))) {
            return null;
        }
        return xmlToNonNullObject(e);
    }

    /**
     * Creates an XML {@link Element} representing the specified object.
     * 
     * @param doc
     *            The {@link Document} to use to create the {@code Element}.
     * @param tag
     *            The name of the {@code Element} node to use.
     * @param object
     *            The object to serialize, which is guaranteed not to be {@code null}
     *            .
     * @return The created {@link Element}.
     */
    protected abstract Element nonNullObjectToXml(Document doc, String tag, T object);

    /**
     * Creates an object corresponding to the specified {@link Element}.
     * 
     * @param e
     *            The {@code Element} to deserialize.
     * @return The created object, which may not be {@code null}.
     * @throws ParseException
     *             If the XML {@code Element} does not correspond to an object of the
     *             class this serializer handles.
     */
    protected abstract T xmlToNonNullObject(Element e) throws ParseException;

    @Override
    public String toString() {
        return getTypeName() + " serializer";
    }
}
