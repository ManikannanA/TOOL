
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile2 {

	public static void main(String argv[]) {

		try {

			File xmlFile = new File("C:\\Users\\Administrator\\Desktop\\create.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			 NodeList elemNodeList  = doc.getElementsByTagName("fieldValues");
			 for(int j=0; j<elemNodeList .getLength(); j++) {
				 Node child1 = elemNodeList.item(j);
				 System.out.println(child1.getNodeValue().substring(100, 150));
				 Node sibling = child1.getNextSibling();
				 while (!(sibling instanceof Element) && sibling != null) {
				   sibling = sibling.getNextSibling();
				 }
				 System.out.println(" Get the Name of the Next Sibling " + sibling.getNodeName());
				 System.out.println(" Get the Value of the Next Sibling " + sibling.getTextContent()); 
			 
			 }
			 
			/* for (int i = 0; i < nodes.getLength(); i++) {
		      Element element = (Element) nodes.item(i);
		      NodeList title = element.getElementsByTagName("createInstance");
		      Element line = (Element) title.item(0);*/
		      
		      
		      /*
		      Node child1 = elemNodeList.item(j);
		      Node sibling = child1.getNextSibling();
		      while (!(sibling instanceof Element) && sibling != null) {
		        sibling = sibling.getNextSibling();
		      }
		      System.out
		            .println(" Get the Name of the Next Sibling " + sibling.getNodeName());
		      System.out
		            .println(" Get the Value of the Next Sibling " + sibling.geTextContent());
		      
		      System.out.println("line>>>>>>>>>>>"+line.getTagName());
		      */
		    //  NodeList fieldValues = element.getElementsByTagName("fieldValues");
		     // Element fielLine = (Element) title.item(0);
		     // NodeList fieldValue = fielLine.getChildNodes();
		     // System.out.println("child>>>>>>>>>>"+fieldValue);
		      
		      //System.out.println("Title: " + getCharacterDataFromElement(line));
		      
		     // NodeList nlist = node.getChildNodes();
		   /*   for (int j = 0 ; j <fieldValue.getLength();  j++) {
		          Node child = title.item(i);
		         System.out.println("child>>>>>>>>>>"+child.getNodeName());
		          // process the child node here
		      }*/
		    //}
	} catch (Exception e) {

		}

	}
}