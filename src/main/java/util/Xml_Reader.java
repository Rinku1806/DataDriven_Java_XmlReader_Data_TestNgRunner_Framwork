package util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml_Reader {

	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	String path;

	public Xml_Reader(String path) {

		this.path = path;

		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(path);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public Object[][] getDataByTestName(String testname) {
	    document.getDocumentElement().normalize();
	    NodeList nodeList = document.getElementsByTagName(testname);
	    int rows = nodeList.getLength();

	    // Get number of columns from first element's child elements only
	    int cols = 0;
	    for (int j = 0; j < nodeList.item(0).getChildNodes().getLength(); j++) {
	        if (nodeList.item(0).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
	            cols++;
	        }
	    }

	    Object[][] temp = new Object[rows][cols];

	    for (int i = 0; i < rows; i++) {
	        Node node = nodeList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) node;
	            NodeList children = eElement.getChildNodes();

	            int colIndex = 0;
	            for (int k = 0; k < children.getLength(); k++) {
	                Node child = children.item(k);
	                if (child.getNodeType() == Node.ELEMENT_NODE) {
	                    String value = child.getTextContent().trim();
	                    temp[i][colIndex] = value;
	                    colIndex++;
	                }
	            }
	        }
	    }
	    return temp;
	}

	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {

		String xml_path = System.getProperty("user.dir") + "\\src\\test\\resources\\xmlData\\Jpetdata.xml";
		Xml_Reader xmlR = new Xml_Reader(xml_path);
		xmlR.document.getDocumentElement().normalize();
		NodeList nodeList = xmlR.document.getElementsByTagName("verifyAllPets");
		int rows = nodeList.getLength();

		Object[][] temp;
		System.out.println("\nNumber of test configs :" + rows);
		for (int i = 0; i < rows; i++) {
			Node node = nodeList.item(i);
			System.out.println("\nNode Name :" + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				String tagName = eElement.getTagName();
				// System.out.println(tagName);
				NodeList col_s = eElement.getChildNodes();
				int cols = col_s.getLength();
				temp = new Object[rows][cols];
				for (int j = 0; j < cols; j++) {
					Node node2 = col_s.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element element1 = (Element) node2;
						String tagName1 = element1.getTagName();
						String textContent = element1.getTextContent();
						temp[i][j] = textContent;
						System.out.println(textContent);
					}
				}

			}
		}

	}

}
