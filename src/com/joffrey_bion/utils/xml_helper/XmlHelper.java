package com.joffrey_bion.utils.xml_helper;

import java.io.FileNotFoundException;
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

public class XmlHelper {

    public static Document createEmptyDomDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

    public static Document getDomDocumentFromFile(String xmlFilePath)
            throws ParserConfigurationException, SAXException, IOException, FileNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // use the factory to take an instance of the document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        // parse using the builder to get the DOM mapping of the
        // XML file
        return db.parse(XmlHelper.fixURI(xmlFilePath));
    }

    public static Element getFirstDirectChild(Element parent, String tag) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element && tag.equals(child.getNodeName())) {
                return (Element) child;
            }
        }
        return null;
    }

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
     * specified tag.
     * <p>
     * Example:
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
     * getField(name); // returns "John"}
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
    public static String fixURI(String uri) {
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
