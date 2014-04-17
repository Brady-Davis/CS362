package Control;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class JavaHasher
{
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
    	String StringPass = "testing";
    	StringPass +="john";
    	MessageDigest cript = MessageDigest.getInstance("SHA-1");
        cript.reset();
        cript.update(StringPass.getBytes("utf8"));
        String hex = new BigInteger(1, cript.digest()).toString(16);
    	System.out.println(hex.length());
        System.out.println(hex);
    }
    
    public static String HashMyPass(String password, String user)
    {
    	String hashedPassword;
		password +=user;
		 try {
    	MessageDigest cript = MessageDigest.getInstance("SHA-1");
        cript.reset();
       
			cript.update(password.getBytes("utf8"));
			hashedPassword = new BigInteger(1, cript.digest()).toString(16);
		} catch (Exception e) {
			System.err.println("Couldn't hash, sorry");
			System.err.println(e.getLocalizedMessage());
			hashedPassword= "Not the Password Hash you are looking for";
		} 
        
       
		
    	
    	return hashedPassword;
    }
    
    public static Boolean login(String username, String password)
    {
		if(username == null || password ==null)
			return false;
		if(username.length()<=0 || password.length()<=0)
		{
			return false;
		}
		
		 String hostName = "proj-309-11.cs.iastate.edu";
		 String creds = username + ":" + HashMyPass(username, password);
	        int portNumber = 443;
	 
	        try (
	            Socket echoSocket = new Socket(hostName, portNumber);
	            PrintWriter out =
	                new PrintWriter(echoSocket.getOutputStream(), true);
	            BufferedReader response =
	                new BufferedReader(
	                    new InputStreamReader(echoSocket.getInputStream()));
	            
	        ) {
	            String Response = "";
	            
	                out.println(creds);
	                Response = response.readLine();
	                //System.out.println("echo: " + Response);
	                if(Response.equals("true"))
	                	return true;
	            
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + hostName);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
	            e.printStackTrace();
	            System.exit(1);
	        } 
    	
    	
    	return false;
   
    }
}