package com.joffrey_bion.utils.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.xml.sax.SAXException;

/**
 * A helper class to perform usual operations to create or read XML files.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey BION</a>
 */
public class XmlHelper {

    /**
     * Creates a new empty DOM {@link Document}.
     * 
     * @return the created {@code Document}.
     */
    public static Document createEmptyDomDocument() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Internal error: ParserConfigurationException: "
                    + e.getMessage());
        }
    }

    /**
     * Parses the specified XML file and returns it as a {@link Document}.
     * 
     * @param xmlFilePath
     *            The path to the file to parse.
     * @return The {@code Document} created from the file.
     * @throws SAXException
     *             If any parse error occurs.
     * @throws IOException
     *             If any IO error occurs.
     */
    public static Document getDomDocumentFromFile(String xmlFilePath) throws SAXException,
            IOException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(XmlHelper.fixURI(xmlFilePath));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Internal error: ParserConfigurationException: "
                    + e.getMessage());
        }
    }

    /**
     * Returns the first direct child of the given element whose name matches
     * {@code tag}.
     * <p>
     * Example: let the {@link Element} {@code root} correspond to:
     * 
     * <pre>
     * {@code <root>
     *     <name>
     *         <first>John</first>
     *         <last>Smith</last>
     *     </name>
     *     <last>BlahBlah</last>
     * </root>
     * 
     * getFirstDirectChild(root, "last"); // returns the element corresponding to <last>BlahBlah</last>}
     * </pre>
     * 
     * </p>
     * 
     * @param parent
     *            The {@link Element} to get the child from.
     * @param tag
     *            The element name of the child to look for.
     * @return The {@link Element} corresponding to the child if any was found,
     *         {@code null} otherwise.
     */
    public static Element getFirstDirectChild(Element parent, String tag) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element && tag.equals(child.getNodeName())) {
                return (Element) child;
            }
        }
        return null;
    }

    /**
     * Returns a list of the direct {@link Element} children of the given element.
     * 
     * @param parent
     *            The {@link Element} to get the children from.
     * @return A {@link LinkedList} of the children {@link Element}s.
     */
    public static LinkedList<Element> getDirectChildren(Element parent) {
        LinkedList<Element> list = new LinkedList<>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element) {
                list.add((Element) child);
            }
        }
        return list;
    }

    /**
     * Returns a list of the direct {@link Element} children of the given element
     * whose names match {@code tag}.
     * 
     * @param parent
     *            The {@link Element} to get the children from.
     * @param tag
     *            The element name of the children to look for.
     * @return A {@link LinkedList} of the children {@link Element}s.
     */
    public static LinkedList<Element> getDirectChildren(Element parent, String tag) {
        LinkedList<Element> list = new LinkedList<>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element && tag.equals(child.getNodeName())) {
                list.add((Element) child);
            }
        }
        return list;
    }

    /**
     * Creates an element representing {@code <tag>text</tag>}.
     * 
     * @param doc
     *            The {@link Document} containing the {@link Element}.
     * @param tag
     *            The tag name of the created {@link Element}.
     * @param text
     *            The content of the created {@link Element}.
     * @return the {@link Element} created.
     */
    public static Element createField(Document doc, String tag, String text) {
        Element elem = doc.createElement(tag);
        elem.appendChild(doc.createTextNode(text));
        return elem;
    }

    /**
     * Creates an element representing {@code <tag>text</tag>} and appends it to
     * {@code root}.
     * 
     * @param doc
     *            The {@link Document} containing the {@link Element}.
     * @param parent
     *            The {@link Node} to append the created {@link Element} to.
     * @param tag
     *            The tag name of the created {@link Element}.
     * @param text
     *            The content of the created {@link Element}.
     * @return the {@link Element} created.
     */
    public static Element appendField(Document doc, Element parent, String tag, String text) {
        Element elem = createField(doc, tag, text);
        parent.appendChild(elem);
        return elem;
    }

    /**
     * Returns the content of the first descendant of {@code ancestor} matching the
     * specified {@code tag}.
     * <p>
     * Example: let the {@link Element} {@code root} correspond to:
     * 
     * <pre>
     * {@code <root>
     *     <name>
     *         <first>John</first>
     *         <last>Smith</last>
     *     </name>
     *     <last>BlahBlah</last>
     * </root>
     * 
     * getField(root, "last"); // returns "Smith"}
     * </pre>
     * 
     * </p>
     * 
     * @param ancestor
     *            The starting point in the XML tree to look for descendants.
     * @param tag
     *            The tag of the desired descendant.
     * @return The content of the first descendant of {@code ancestor} matching the
     *         tag, or {@code null} if no such descendant exists.
     */
    public static String getField(Element ancestor, String tag) {
        NodeList children = ancestor.getElementsByTagName(tag);
        if (children.getLength() == 0) {
            return null;
        }
        return getContent(children.item(0));
    }

    /**
     * Returns the content of the specified node.
     * <p>
     * Example:
     * 
     * <pre>
     * {@code <name>John</name>
     * 
     * getContent(name); // returns "John"}
     * </pre>
     * 
     * </p>
     * 
     * @param node
     *            The node to get the field child from.
     * @return The content of the specified node, or {@code null} if no such content
     *         exists.
     */
    public static String getContent(Node node) {
        Node fieldNode = node.getFirstChild();
        if (fieldNode == null) {
            return null;
        }
        return fieldNode.getNodeValue();
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
     * Fix problems in the URIs (spaces for instance).
     * 
     * @param uri
     *            The original URI.
     * @return The corrected URI.
     */
    private static String fixURI(String uri) {
        // handle platform dependent strings
        String path = uri.replace(java.io.File.separatorChar, '/');
        // Windows fix
        if (path.length() >= 2) {
            char ch1 = path.charAt(1);
            // change "C:blah" to "/C:blah"
            if (ch1 == ':') {
                char ch0 = Character.toUpperCase(path.charAt(0));
                if (ch0 >= 'A' && ch0 <= 'Z') {
                    path = "/" + path;
                }
            }
            // change "//blah" to "file://blah"
            else if (ch1 == '/' && path.charAt(0) == '/') {
                path = "file:" + path;
            }
        }
        // replace spaces in file names with %20.
        // Original comment from JDK5: the following algorithm might not be
        // very performant, but people who want to use invalid URI's have to
        // pay the price.
        int pos = path.indexOf(' ');
        if (pos >= 0) {
            StringBuilder sb = new StringBuilder(path.length());
            // put characters before ' ' into the string builder
            for (int i = 0; i < pos; i++)
                sb.append(path.charAt(i));
            // and %20 for the space
            sb.append("%20");
            // for the remaining part, also convert ' ' to "%20".
            for (int i = pos + 1; i < path.length(); i++) {
                if (path.charAt(i) == ' ')
                    sb.append("%20");
                else
                    sb.append(path.charAt(i));
            }
            return sb.toString();
        }
        return path;
    }
}
