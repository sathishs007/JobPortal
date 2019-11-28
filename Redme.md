/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 804222
 *
 */
public class HttpConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try 
         { 

             // url for microsoft congnitive server. 
             URL url =  
          new URL("http://localhost:8080/test"); 
             HttpURLConnection con = 
                     (HttpURLConnection) url.openConnection(); 

             // set the request method and properties. 
             con.setRequestMethod("GET"); 
             //con.setRequestProperty("Ocp-Apim-Subscription-Key", key); 
             con.setRequestProperty("Content-Type", "application/json"); 
             con.setRequestProperty("Accept", "application/json"); 

             // As we know the length of our content, 
             // the following function sets the fixed  
             // streaming mode length 83 bytes. If 
             // content length not known, comment the below line. 
             //con.setFixedLengthStreamingMode(83); 

             // set the auto redirection to true 
             HttpURLConnection.setFollowRedirects(true); 

             // override the default value set by 
             // the static method setFollowRedirect above 
             con.setInstanceFollowRedirects(false); 

             // set the doOutput to true. 
             con.setDoOutput(true); 

            // OutputStream out = con.getOutputStream(); 
             // System.out.println(ezm.toString().getBytes().length); 

             // write on the output stream 
             //out.write(ezm.toString().getBytes()); 
             InputStream ip = con.getInputStream(); 
             BufferedReader br1 =  
                     new BufferedReader(new InputStreamReader(ip)); 

             // Print the response code 
             // and response message from server. 
             System.out.println("Response Code:" 
                                     + con.getResponseCode()); 
             System.out.println("Response Message:" 
                                 + con.getResponseMessage()); 

             // uncomment the following line to 
             // print the status of 
             // FollowRedirect property. 
             // System.out.println("FollowRedirects:"  
             //              + HttpURLConnection.getFollowRedirects()); 

             // to print the status of  
             // instanceFollowRedirect property. 
             System.out.println("InstanceFollowRedirects:" 
                             + con.getInstanceFollowRedirects()); 

             // to print the 1st header field. 
             System.out.println("Header field 1:" 
                                 + con.getHeaderField(1)); 

             // to print if usingProxy flag set or not. 
             System.out.println("Using proxy:" + con.usingProxy()); 

             StringBuilder response = new StringBuilder(); 
             String responseSingle = null; 
             while ((responseSingle = br1.readLine()) != null)  
             { 
                 response.append(responseSingle); 
             } 
             String xx = response.toString(); 
             System.out.println(xx); 

         } catch (Exception e)  
           
         { 
             System.out.println(e.getMessage()); 
         }
	}

}

