package com.pch.joomla.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressWarnings("unused")
public class XmlrederJDOM {
	
	public static Document convertToDocument(Object xmlContent) {
		  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = null;
		  Document document = null;
		  try {
		   builder = builderFactory.newDocumentBuilder();
		   if (xmlContent instanceof String) {
		    document = builder.parse((String) xmlContent);
		   } else if (xmlContent instanceof File) {
		    document = builder.parse((File) xmlContent);
		   }
		  } catch (ParserConfigurationException pce) {
		   pce.printStackTrace();
		  } catch (SAXException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  document.getDocumentElement().normalize();
		  
		  return document;
		 }
		 

	public static ArrayList<String> getNodeWithXPath(String XmlContenturl) {
		  ArrayList<String> a1=new ArrayList<String>();
		// public static void  getNodeWithXPath(String XmlContenturl ){
		  //String xmlContent="http://www.espncricinfo.com/rss/content/story/feeds/0.xml";
		  //File xmlContent = new File("NewFile.xml");
		  //String xmlContent = "https://iwe.qa.pch.com/iwe/services/winner/getWinners?limit=5&businessUnitId=31";
		  Document doc=convertToDocument(XmlContenturl);
		  doc.getDocumentElement().normalize();
		  
		  //Get file url
	//	  System.out.println(doc.getDocumentURI());

		  //Get Root Node Name
		  System.out.println("Root element :- " + doc.getDocumentElement().getNodeName());	  
		  
		  NodeList nList = doc.getElementsByTagName("prizeValue");
		  System.out.println(nList.getLength());
		  
		  for (int i = 0; i < nList.getLength(); i++) {
		   Node nNode = nList.item(i);
		  
		   
	        //Adding values to the ArrayList
	      
		  // System.out.println("\nCurrent Element :" + nNode.getNodeName());
		   Element e2=(Element)nNode;
		   Element element = (Element)nList.item(i);
		  
		   a1.add(element.getTextContent());
		 
		   //System.out.println(i+" --> "+element.getTextContent());
		  }
		  
		  
		return a1; 
	
	  
		  
		 }
		}
	

