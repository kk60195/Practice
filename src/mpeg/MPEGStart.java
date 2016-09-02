/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpeg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CharacterData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sun.misc.IOUtils;


/**
 *
 * @author 517002
 */
class MPEGStart {

   
public Document doc;


    public MPEGStart(String in) throws Exception{
        
        
        URL url = new URL(in);
        URLConnection connection = url.openConnection();
        
       
        //build a document from open stream
        doc = parseXML(url);
        


       
       
    }
    
    private Document parseXML(URL url) throws Exception {
        
       DocumentBuilderFactory objDocumentBuilderFactory = null;
       DocumentBuilder objDocumentBuilder = null;
       Document docIn = null;
       
       try{
           objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
           objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
           
     
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("B001438381L3.mpd");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
           
         docIn = objDocumentBuilder.parse("C:\\Users\\517002\\Downloads\\B001438381L3.mpd");
//            docIn = objDocumentBuilder.parse("B001438381L3.mpd");
           
        
       }
       catch(Exception ex){
           
        throw ex;
        
        }
       
       return docIn;
       
    }
    public Document getDocument(){
        
        return doc;
    }



    private Document BackToXML(String source) throws Exception {
        


    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(source));

    Document doc = db.parse(is);
    
    
    return doc;
    }
    
    

    

    public void printXML(Document backToXML) {
        
        NodeList ElmList = null;
        Element baseElm = null;
        NamedNodeMap baseAttr = null;
        Node attr = null;
        
        ElmList = backToXML.getChildNodes();
        
        int OneCount = 0;
        
        while(ElmList.getLength() -1 >= OneCount){
            
            baseElm = (Element)ElmList.item(OneCount);
           
            if( baseElm.hasAttributes()){
                 baseAttr = baseElm.getAttributes();
            
                 for(int i = 0 ; i< baseAttr.getLength();i++){
                    attr = baseAttr.item(i);
                    System.out.print(attr.getNodeName() + " = \"" + attr.getNodeValue() + "\" ");
                        
                 }
                 if(baseElm.hasChildNodes()){
                     this.printEmbeddedNodes(baseElm);
                 }
            }
           
           
            OneCount++;
        }
        
    }

    public void printEmbeddedNodes(Element baseElm2) {
        NodeList ElmList = baseElm2.getChildNodes();
        Element baseElm = null;
        NamedNodeMap baseAttr = null;
        Node attr = null;
        
        System.out.print("\n");
        
        int OneCount = 0;
        
        while(OneCount < ElmList.getLength()){
            
            attr = ElmList.item(OneCount);
            
            if(attr.hasAttributes()){
                baseAttr = attr.getAttributes();
                
                for(int i = 0; i < baseAttr.getLength() ; i++){
                    attr = baseAttr.item(i);
                    System.out.print(attr.getNodeName() + " = \"" + attr.getNodeValue() + "\" ");
                    
                }
                
                
            }
            
            if(attr.hasChildNodes()){
                baseElm = (Element)ElmList.item(OneCount);
                this.printEmbeddedNodes(baseElm);
            }
            
            
            
            OneCount++;
        }
     
        
        
    }
    
 public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData && child != null) {
       CharacterData cd = (CharacterData) child;
       return cd.getData();
    }
    return "?";
  }

   

    
 

    
}

