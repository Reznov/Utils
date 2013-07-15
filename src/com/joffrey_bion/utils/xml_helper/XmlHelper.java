package com.joffrey_bion.utils.xml_helper;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlHelper {

    public static Element getDirectChild(Element parent, String name) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element && name.equals(child.getNodeName()))
                return (Element) child;
        }
        return null;
    }

    /**
     * Creates an element representing {@code <tag>text</tag>} and appends it to
     * {@code root}.
     * 
     * @param doc
     *            The {@link Document} containing the {@link Element}.
     * @param root
     *            The {@link Node} to append the created {@link Element} to.
     * @param tag
     *            The tag name of the created {@link Element}.
     * @param text
     *            The content of the created {@link Element}.
     * @return the {@link Element} created.
     */
    public static Element appendField(Document doc, Element root, String tag, String text) {
        Element elem = doc.createElement(tag);
        elem.appendChild(doc.createTextNode(text));
        root.appendChild(elem);
        return elem;
    }

    /**
     * Writes the specified DOM {@link Document} to the specified XML file.
     * 
     * @param filePath
     *            The path to the XML output file.
     * @param doc
     *            The {@code Document} to write.
     * @throws IOException
     *             If an error occurs while writing to the file.
     */
    public static void writeXml(String filePath, Document doc) throws IOException {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            // send DOM to file
            FileOutputStream fos = new FileOutputStream(filePath);
            tr.transform(new DOMSource(doc), new StreamResult(fos));
            fos.close();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the content of the first descendant node matching the specified tag.
     * <p>
     * Example:
     * 
     * <pre>
     * {@code <root>
     *     <name>
     *         <first>John</first>
     *         <last>Smith</last>
     *     </name>
     * </root>
     * 
     * getField(root, "last"); // returns "Smith"}
     * </pre>
     * 
     * </p>
     * 
     * @param root
     *            The starting point in the XML tree to look for descendants.
     * @param tag
     *            The tag of the desired descendant.
     * @return The content of the first descendant matching the tag.
     */
    public static String getField(Element root, String tag) {
        NodeList children = root.getElementsByTagName(tag);
        if (children.getLength() == 0) {
            return null;
        }
        Node fieldNode = children.item(0).getFirstChild();
        if (fieldNode == null) {
            return null;
        }
        return fieldNode.getNodeValue();
    }
}
