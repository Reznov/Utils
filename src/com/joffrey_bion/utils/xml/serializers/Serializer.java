package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * An XML serializer for the class given to the constructor, which also corresponds
 * to the type variable of this generic class.
 * <p>
 * This base abstract class is already subclassed into {@link SimpleSerializer}s and
 * {@link ArraySerializer}s for some basic classes. These anonymous subclasses are
 * instantiated in the public fields of this class. In particular, there are
 * {@code Serializer}s for every primitive type wrapper class, and the corresponding
 * array classes.
 * </p>
 * <p>
 * This class should not be directly subclassed. To create your own
 * {@link Serializer}, extending either {@link ObjectSerializer},
 * {@link SimpleSerializer} or {@link ArraySerializer} should be sufficient.
 * </p>
 * 
 * @param <T>
 *            The type managed by this serializer.
 * @see SimpleSerializer
 * @see ArraySerializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class Serializer<T> {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Serializer)) {
            return false;
        }
        return clazz.equals(((Serializer<?>) o).clazz);
    }
}
