import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadExample {

	public static void main(String[] args) throws SAXException, IOException {

	  	try {
			File xmlFile = new File("F:\\LOB\\lob.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			    NodeList nodes = doc.getElementsByTagName("AWD");
			    for (int i = 0; i < nodes.getLength(); i++) {
			      Element element = (Element) nodes.item(i);
			      NodeList title = element.getElementsByTagName("TASK");
			      Element line = (Element) title.item(0);
			      String task =getCharacterDataFromElement(line);
			      System.out.println("TASK: " + task);
			    }
			    //String task =getCharacterDataFromElement(line);
			    
			     /*NodeList node1 = doc.getElementsByTagName("createInstences");
			     for (int i = 0; i < node1.getLength(); i++) {
			      Element element = (Element) nodes.item(i);
			      NodeList title = element.getElementsByTagName("createInstence");
			      Element line = (Element) title.item(0);
			      NodeList fieldValues = element.getElementsByTagName("fieldValues");
			      Element fielLine = (Element) title.item(0);
			      NodeList fieldValue = fielLine.getChildNodes();
			      System.out.println("child>>>>>>>>>>"+fieldValue);
			      //System.out.println("Title: " + getCharacterDataFromElement(line));
			      
			     // NodeList nlist = node.getChildNodes();
			      for (int j = 0 ; j <fieldValue.getLength();  j++) {
			          Node child = title.item(i);
			         System.out.println("child>>>>>>>>>>"+child);
			          // process the child node here
			      }
			    } */
	  	
			  }	 catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   
	  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	     if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
}
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	try {
			File xmlfile = new File("F:\\LOB\\lob.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(xmlfile);
			
			// Create NodeList of element tag "CRAWL"
			NodeList feeds = doc.getElementsByTagName("TASK");
			
			
			//int num= crawlNodeList.getLength();
			
			System.out.println(feeds.getLength());
			// Now iterate through each item in the NodeList and get the values of 
			// each of the elements in Name, Price, Desc etc.
			for (int i = 0; i < feeds.getLength(); i++) {
				Node mainNode = feeds.item(i);
                if (mainNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstElement = (Element) mainNode;
                    System.out.println("First element " + firstElement.getTagName());
                    NodeList forumidNameList = firstElement.getElementsByTagName("createInstences");
                    
                    for (int j = 0; j < forumidNameList.getLength(); ++j) {
                        Element value = (Element) forumidNameList.item(j);

                        NodeList conditionList = value.getElementsByTagName("fieldValues");
                        for (int k = 0; k < conditionList.getLength(); ++k) {
                            Element condition = (Element) conditionList.item(k);
                            String conditionText = condition.getFirstChild().getNodeValue();
                            System.out.println("fieldValues " + conditionText);
                        }
			    }
			}
		}
	}*/
	  
/*
}
	
	
	public static String extractTextChildren(Element parentNode) {
	    NodeList childNodes = parentNode.getChildNodes();
	    String result = new String();
	    for (int i = 0; i < childNodes.getLength(); i++) {
	      Node node = childNodes.item(i);
	      if (node.getNodeType() == Node.TEXT_NODE) {
	        result += node.getNodeValue();
	        System.out.println("Result?????????????????????"+result);
	      }
	    }
	    return result;
	  }

}
	*/		


			
			/*NodeList dataTag = doc.getElementsByTagName("TASK");
			NodeList dataItems = dataTag.item(0).getChildNodes();
			System.out.println("<<<dataTag>>>"+dataItems);
			
			String  item = null;
			List<String> items = new LinkedList<String>();
			for (int j = 1; j < dataItems.getLength(); j+=2) {
			if (dataItems.item(j).getNodeName().equalsIgnoreCase("value")) {
			    item = new DataItem();
			        items.add(item);
			    }
			setValue(item, dataItems.item(j).getNodeName(), dataItems.item(j).getTextContent());
			}*/
			
			
			/*
			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes());

			}*/

		

	/*private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					printNote(tempNode.getChildNodes());

				}

				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}

		}

	}*/

