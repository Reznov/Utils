package com.joffrey_bion.utils.xml.serializers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.joffrey_bion.utils.xml.XmlHelper;

/**
 * An XML serializer for any class whose non-transient fields have a corresponding
 * {@link Serializer}. Only the fields that have to be serialized have to be
 * associated with a {@link Serializer}.
 * <p>
 * Note that the type variable here does not have to implement
 * {@link java.io.Serializable}. Even if the concepts of {@code transient} and
 * {@code non-transient} fields are used in the descriptions of the methods of this
 * class, the keyword {@code transient} does not have to be used.
 * </p>
 * 
 * @param <T>
 *            The type managed by this serializer.
 * @see Serializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class ObjectSerializer<T> extends Serializer<T> {

    @SuppressWarnings("serial")
    private static class IncompleteFieldsException extends RuntimeException {
        public IncompleteFieldsException() {
            super("Some fields are missing according to the specification.");
        }
    }

    private final HashMap<String, Serializer<?>> spec;

    /**
     * Creates a new {@code ObjectSerializer} to handle objects of the specified
     * class.
     * 
     * @param clazz
     *            The class this serializer handles, which should correspond to the
     *            type variable of this generic class.
     */
    public ObjectSerializer(Class<T> clazz) {
        super(clazz);
        spec = getFieldsSpec();
    }

    @Override
    protected Element nonNullObjectToXml(Document doc, String tag, T object) {
        Element root = doc.createElement(tag);
        HashMap<String, Object> fields = getFieldsValues(object);
        check(fields);
        for (String key : spec.keySet()) {
            root.appendChild(spec.get(key).objectToXml(doc, key, fields.get(key)));
        }
        return root;
    }

    @Override
    protected T xmlToNonNullObject(Element e) throws ParseException {
        LinkedList<Element> eltFields = XmlHelper.getDirectChildren(e);
        HashMap<String, Object> fields = new HashMap<>();
        for (Element field : eltFields) {
            String fName = field.getNodeName();
            Serializer<?> fType = spec.get(fName);
            fields.put(fName, fType.xmlToNonNullObject(field));
        }
        check(fields);
        return createInstanceFromFields(fields);
    }

    /**
     * Checks whether all the fields expected by the specification are actually
     * present in the specified map.
     * 
     * @param fields
     *            The map to check.
     */
    private void check(HashMap<String, Object> fields) {
        if (!fields.keySet().containsAll(spec.keySet())) {
            throw new IncompleteFieldsException();
        }
    }

    /**
     * Returns the {@link Serializer}s of the fields that have to be serialized. The
     * fields that are not present in the map will not be serialized (they have the
     * same meaning as the {@code transient} fields of a {@link java.io.Serializable}
     * ).
     * 
     * @return a map between the names of the fields and their corresponding
     *         {@link Serializer}.
     */
    protected abstract HashMap<String, Serializer<?>> getFieldsSpec();

    /**
     * Returns a map containing the value corresponding to each non-transient field
     * of the specified object. All the fields that appeared in the map returned by
     * {@link #getFieldsSpec()} have to be present in the returned map of this
     * method.
     * 
     * @param object
     *            The object to take the values from. It is guaranteed not to be
     *            null.
     * @return a map between the names of the fields and their corresponding value.
     *         The names that are used here are the same as the ones returned by
     *         {@link #getFieldsSpec()}.
     */
    protected abstract HashMap<String, Object> getFieldsValues(T object);

    /**
     * Creates a non-null object and set its fields to the specified values.
     * 
     * @param fields
     *            A map between the names of the fields and their corresponding
     *            value. It is guaranteed that all the names that were given as keys
     *            by {@link #getFieldsSpec()} will have a mapping in this map. Also,
     *            the type of each value has been verified and they can be cast
     *            safely.
     * @return The created object, which may not be null.
     */
    protected abstract T createInstanceFromFields(HashMap<String, Object> fields);
}
