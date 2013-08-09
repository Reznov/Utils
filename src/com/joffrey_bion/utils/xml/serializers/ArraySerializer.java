package com.joffrey_bion.utils.xml.serializers;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.joffrey_bion.utils.xml.XmlHelper;

/**
 * An XML serializer for arrays of objects from the type variable's class. The class
 * of the components of such arrays must have a corresponding
 * {@link SimpleSerializer}.
 * 
 * @see Serializer
 * @see SimpleSerializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class ArraySerializer<T> extends Serializer<T[]> {

    /**
     * The name of the XML elements used for each component of the serialized arrays.
     */
    private static final String ITEM_TAG = "item";

    /**
     * An XML serializer for arrays of {@link Boolean}.
     */
    public static final ArraySerializer<Boolean> BOOLEAN_ARRAY = new ArraySerializer<Boolean>(
            SimpleSerializer.BOOLEAN) {
        @Override
        protected Boolean[] getEmptyArray() {
            return new Boolean[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Byte}.
     */
    public static final ArraySerializer<Byte> BYTE_ARRAY = new ArraySerializer<Byte>(
            SimpleSerializer.BYTE) {
        @Override
        protected Byte[] getEmptyArray() {
            return new Byte[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Character}.
     */
    public static final ArraySerializer<Character> CHARACTER_ARRAY = new ArraySerializer<Character>(
            SimpleSerializer.CHARACTER) {
        @Override
        protected Character[] getEmptyArray() {
            return new Character[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Double}.
     */
    public static final ArraySerializer<Double> DOUBLE_ARRAY = new ArraySerializer<Double>(
            SimpleSerializer.DOUBLE) {
        @Override
        protected Double[] getEmptyArray() {
            return new Double[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Float}.
     */
    public static final ArraySerializer<Float> FLOAT_ARRAY = new ArraySerializer<Float>(
            SimpleSerializer.FLOAT) {
        @Override
        protected Float[] getEmptyArray() {
            return new Float[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Integer}.
     */
    public static final ArraySerializer<Integer> INTEGER_ARRAY = new ArraySerializer<Integer>(
            SimpleSerializer.INTEGER) {
        @Override
        protected Integer[] getEmptyArray() {
            return new Integer[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Long}.
     */
    public static final ArraySerializer<Long> LONG_ARRAY = new ArraySerializer<Long>(
            SimpleSerializer.LONG) {
        @Override
        protected Long[] getEmptyArray() {
            return new Long[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Short}.
     */
    public static final ArraySerializer<Short> SHORT_ARRAY = new ArraySerializer<Short>(
            SimpleSerializer.SHORT) {
        @Override
        protected Short[] getEmptyArray() {
            return new Short[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link String}.
     */
    public static final ArraySerializer<String> STRING_ARRAY = new ArraySerializer<String>(
            SimpleSerializer.STRING) {
        @Override
        protected String[] getEmptyArray() {
            return new String[0];
        }
    };

    /**
     * The serializer to use for each component of the array.
     */
    private final Serializer<T> componentSerializer;

    /**
     * Creates an {@link ArraySerializer} for arrays of objects that can be
     * serialized by {@code componentSerializer}.
     * 
     * @param componentSerializer
     *            The {@code Serializer} that handles the component class of the
     *            arrays handled by this {@code ArraySerializer}.
     */
    @SuppressWarnings("unchecked")
    public ArraySerializer(Serializer<T> componentSerializer) {
        super((Class<T[]>) Array.newInstance(componentSerializer.clazz, 0).getClass());
        this.componentSerializer = componentSerializer;
    }

    @Override
    protected Element nonNullObjectToXml(Document doc, String tag, T[] array) {
        Element root = doc.createElement(tag);
        for (T item : array) {
            Element e = componentSerializer.objectToXml(doc, ITEM_TAG, item);
            root.appendChild(e);
        }
        return root;
    }

    @Override
    protected T[] xmlToNonNullObject(Element e) throws ParseException {
        LinkedList<Element> eltItems = XmlHelper.getDirectChildren(e, ITEM_TAG);
        LinkedList<T> items = new LinkedList<>();
        for (Element eItem : eltItems) {
            items.add(componentSerializer.xmlToObject(eItem));
        }
        return items.toArray(getEmptyArray());
    }

    /**
     * Returns a serialized version of the specified array.
     * 
     * @param values
     *            The array of objects to serialize.
     * @return A {@code String} array representing the serialized values of the
     *         components of this array obtained via
     *         {@link SimpleSerializer#serialize(Object)}. This method returns
     *         {@code null} if {@code values} is {@code null}.
     * @throws ClassCastException
     *             If the class of the components of the specified array is not the
     *             class handled by this {@link ArraySerializer}.
     */
    public String[] serialize(Object[] values) throws ClassCastException {
        if (values == null) {
            return null;
        }
        if (componentSerializer instanceof SimpleSerializer) {
            SimpleSerializer<T> scs = (SimpleSerializer<T>) componentSerializer;
            LinkedList<String> items = new LinkedList<>();
            for (Object item : values) {
                items.add(scs.serialize(scs.cast(item)));
            }
            return items.toArray(new String[0]);
        } else {
            throw new RuntimeException("Cannot deserialize, the component type is not a "
                    + SimpleSerializer.class.getSimpleName());
        }
    }

    /**
     * Retrieves an array of objects corresponding to the specified array of
     * {@code String}.
     * 
     * @param serializedValues
     *            The array of values to assign to this parameter, in the serialized
     *            {@link String} form.
     * @return an array of objects obtained from each {@code String} via
     *         {@link SimpleSerializer#deserialize(String)}.
     * @throws ParseException
     *             If the objects' class does not match the class handled by this
     *             {@link ArraySerializer}.
     */
    public T[] deserialize(String[] serializedValues) throws ParseException {
        if (componentSerializer instanceof SimpleSerializer) {
            SimpleSerializer<T> scs = (SimpleSerializer<T>) componentSerializer;
            LinkedList<T> items = new LinkedList<>();
            for (String item : serializedValues) {
                items.add(scs.deserialize(item));
            }
            return items.toArray(getEmptyArray());
        } else {
            throw new RuntimeException("Cannot deserialize, the component type is not a "
                    + SimpleSerializer.class.getSimpleName());
        }
    }

    /**
     * Returns an empty array of the class handled by this serializer.
     * 
     * @return an empty array of the class handled by this serializer.
     */
    protected abstract T[] getEmptyArray();
}
