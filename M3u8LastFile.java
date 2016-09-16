/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3u8lastfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import sun.misc.IOUtils;



/**
 *
 * @author 517002
 */
public class M3u8LastFile {

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       ArrayList bitez = new ArrayList();
       bitez.add("01/");
       bitez.add("02/");
       bitez.add("03/");
       bitez.add("04/");
       bitez.add("05/");
       bitez.add("06/");
       bitez.add("07/");
       
       ArrayList basePaths = new ArrayList();
       basePaths.add("http://nfl-nds-live.dtvbb.hls.adaptive.level3.net/NFL/30/5703/");
       basePaths.add("http://nfl-aes-live.dtvbb.hls.adaptive.level3.net/NFL/10/5703/");
       
       
       String bits = "01/";
       String playlist = "playlist.m3u8";
       String basePath = "http://nfl-nds-live.dtvbb.hls.adaptive.level3.net/NFL/30/5703/";
       URL baseURL = null;
       
       while(true){
           Thread.sleep(1000);
           
       for(int jj = 0; jj< basePaths.size(); jj++){
           basePath = (String)basePaths.get(jj);
           
       for(int ii = 0; ii < bitez.size(); ii++){ 
        
        bits = (String)bitez.get(ii);
        baseURL = new URL(basePath + bits + playlist);
       
        
       //Document m3u8file = M3u8LastFile.parseXML(baseURL);
   
  
           
       RunPlaylist(bits,playlist,basePath,baseURL);
       
       }
       }
       }
    }
    public static void RunPlaylist(String bits, String playlist , String basePath, URL baseURL) throws Exception{
       
       
       try {
       InputStream in = baseURL.openStream();
       String doc = getStringFromInputStream(in);
       in.close();
       int length = doc.length();
       int tsStart = length - 21;
       String tsfile = doc.substring(tsStart,length);
       
       URL tsURL = new URL(basePath+bits+tsfile);
       
       
                    ReadableByteChannel rbc = Channels.newChannel(tsURL.openStream());
                    
                    //Make the actual file path
                    Boolean MakeFile;
                    MakeFile = (new File(".\\"+ basePath)).mkdir();
                    FileOutputStream fos = new FileOutputStream(basePath.substring(7,30)+ "_"+ bits.substring(0,2)+"_"+ tsfile);
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
       }
       catch(Exception e){
           System.out.println(e.toString());
       }
        
        
        
    }
    
    private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
    
}
