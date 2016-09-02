/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpeg;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpStatus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 517002
 */
public class MPEG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
    
        try {
            Document doc;
            
            // TODO code application logic here
            // HttpClient httpclient = new HttpClient();
           String MPDName = null; 
           String expTime = null;
           String Token = null;
           String key = null;
           String CdnURL = null;
           File ff = new File("MPDFiles.txt");
           Scanner in = new Scanner(ff);
           
           if(ff.exists() && !ff.isDirectory()){
                   
                       expTime = in.nextLine();
                       Token = in.nextLine();
                       key = "?exptime=" + expTime + "&token=" + Token;
                       CdnURL = in.nextLine();
               
           }
           
         
           
           String relativePath = null;
           String baseURL = CdnURL + relativePath;
           String baseDirectory = baseURL + "01/";
           String token  = "?exptime=1472937595&token=d75b13016f1c55b963305c938ee24392";
           
           while(in.hasNext()){
               relativePath = in.nextLine();
               MPDName = relativePath.substring(45);
               relativePath = relativePath.substring(0,45);
               System.out.println("about to make" + MPDName.substring(0,12));
               Boolean MakeFile = (new File(MPDName.substring(0,12))).mkdir();
               File fff = new File(".\\" + MPDName);
               System.out.println( MakeFile.toString() +  fff.isDirectory() );
           
           
           
           
           File f = new File(baseDirectory);
           
           //MPEGStart SegVOD = new MPEGStart("http://c4--10-26-112-0-24-B001438381L3.segvod.sponsored.vcdn.att-idns.net/vod/01/movie/2016_03/B001438381/B001438381L3/B001438381L3.mpd?exptime=1472937595&token=d75b13016f1c55b963305c938ee24392");
           MPEGStart SegVOD = new MPEGStart(baseURL+relativePath+MPDName+key); 
           doc = SegVOD.getDocument();
            
            //SegVOD.printXML(doc);
            
            
            
            
            NodeList x = doc.getElementsByTagName("SegmentList");
            
          for(int jj = 0 ; jj < x.getLength(); jj++){
            for(int ii = 0 ; ii < x.item(jj).getChildNodes().getLength() ; ii++){
            //System.out.print("node Name:" + x.item(0).getChildNodes().item(ii).getNodeName());
            //System.out.println(" node value:" + x.item(jj).getChildNodes().item(ii).hasAttributes() + " length:" + x.item(jj).getChildNodes().getLength());
                if( x.item(jj).getChildNodes().item(ii).hasAttributes() ){
                    //System.out.println(" has attribute ");
                    System.out.println(jj+ " " + x.item(jj).getChildNodes().item(ii).getAttributes().item(0).getNodeValue());
                    String fileName = x.item(jj).getChildNodes().item(ii).getAttributes().item(0).getNodeValue();
                    //System.out.println(x.item(1).getChildNodes().item(ii).getAttributes().item(0).getNodeValue());
                    String path = baseDirectory + fileName + key;
                    URL website = new URL(path);
                    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                    MakeFile = (new File(".\\"+ MPDName.substring(0,12)+".\\"+jj+"\\")).mkdir();
                    FileOutputStream fos = new FileOutputStream(".\\"+ MPDName.substring(0,12)+".\\"+jj+ "\\"+ fileName);
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    //System.out.println(".\\"+MPDName + "\\" + fileName);
                       
                    
                    
                    
                   // f = new File(path);
                    
                }
                else{
                    //System.out.println(" no attributes ");
                }
                }
            
                }
            System.out.println("exited");
           }
            
        } catch (Exception ex) {
            throw ex;
        }
       
        
        
    }
    
}
