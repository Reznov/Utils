package com.joffrey_bion.utils.xml.serializers;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.joffrey_bion.utils.xml.XmlHelper;

/**
 * Represents an XML serializer for arrays of objects from the type variable's class.
 * The class of the components of such arrays must have a corresponding
 * {@link SimpleClassSerializer}.
 * 
 * @see Serializer
 * @see SimpleClassSerializer
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public abstract class ArraySerializer<T> extends Serializer<T[]> {

    /**
     * The separator to use between each component of the array in the serialized
     * string.
     */
    private static final String ITEM_TAG = "item";

    /**
     * An XML serializer for arrays of {@link Boolean}.
     */
    static final ArraySerializer<Boolean> BOOLEAN_ARRAY = new ArraySerializer<Boolean>(BOOLEAN) {
        @Override
        protected Boolean[] getEmptyArray() {
            return new Boolean[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Byte}.
     */
    static final ArraySerializer<Byte> BYTE_ARRAY = new ArraySerializer<Byte>(BYTE) {
        @Override
        protected Byte[] getEmptyArray() {
            return new Byte[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Character}.
     */
    static final ArraySerializer<Character> CHARACTER_ARRAY = new ArraySerializer<Character>(
            CHARACTER) {
        @Override
        protected Character[] getEmptyArray() {
            return new Character[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Double}.
     */
    static final ArraySerializer<Double> DOUBLE_ARRAY = new ArraySerializer<Double>(DOUBLE) {
        @Override
        protected Double[] getEmptyArray() {
            return new Double[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Float}.
     */
    static final ArraySerializer<Float> FLOAT_ARRAY = new ArraySerializer<Float>(FLOAT) {
        @Override
        protected Float[] getEmptyArray() {
            return new Float[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Integer}.
     */
    static final ArraySerializer<Integer> INTEGER_ARRAY = new ArraySerializer<Integer>(INTEGER) {
        @Override
        protected Integer[] getEmptyArray() {
            return new Integer[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Long}.
     */
    static final ArraySerializer<Long> LONG_ARRAY = new ArraySerializer<Long>(LONG) {
        @Override
        protected Long[] getEmptyArray() {
            return new Long[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link Short}.
     */
    static final ArraySerializer<Short> SHORT_ARRAY = new ArraySerializer<Short>(SHORT) {
        @Override
        protected Short[] getEmptyArray() {
            return new Short[0];
        }
    };
    /**
     * An XML serializer for arrays of {@link String}.
     */
    static final ArraySerializer<String> STRING_ARRAY = new ArraySerializer<String>(STRING) {
        @Override
        protected String[] getEmptyArray() {
            return new String[0];
        }
    };

    /**
     * The serializer to use for each component of the array.
     */
    private SimpleClassSerializer<T> componentSerializer;

    /**
     * Creates an {@link ArraySerializer} for arrays of objects that can be
     * serialized by {@code componentSerializer}.
     * 
     * @param componentSerializer
     *            The {@code Serializer} that handles the component class of the
     *            arrays handled by this {@code ArraySerializer}.
     */
    @SuppressWarnings("unchecked")
    public ArraySerializer(SimpleClassSerializer<T> componentSerializer) {
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
     * Returns an empty array of the class handled by this serializer.
     * 
     * @return an empty array of the class handled by this serializer.
     */
    protected abstract T[] getEmptyArray();
}
