import java.net.*;

import java.io.*;


public class smtpclient

{



public static void main(String[] argc)

{



Socket s1;

PrintStream p1;

DataInputStream d1;

String recvreply;



try

{

s1=new Socket("202.54.16.99",25);

d1=new DataInputStream(new 
BufferedInputStream(s1.getInputStream(),2500));

p1=new PrintStream(new 
BufferedOutputStream(s1.getOutputStream(),2500),true);

recvreply=d1.readLine();

System.out.println("Server Response : " + recvreply);

p1.println("HELO 192.168.145.129");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);



p1.println("MAIL FROM: <csd@rocketmail.com>");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);



p1.println("RCPT TO: <cdsd@rocketmail.com>");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);



p1.println("DATA");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);

p1.println("From : The God of Love<das@rocketmail.com>");

p1.println("Reply-To : asds@rocketmail.com");

p1.println("To : sdv@hotmail.com>");

p1.println("Subject : Gameplan for Disaster"); 

p1.println("Date : 5th Dec"); 

p1.println("Content-Type: text/html; charset=us-ascii"); 

p1.println("Content-Transfer-Encoding: 7-bit"); 





p1.println("<b>Hello sd,</b>");

p1.println("Howdy!!!.......So how are things with u");

p1.println("Just wanted to give u this link....check it out");

p1.println("\r\n");

p1.println("\r\n");

p1.println("<a href=http://www.yahoo.com>Search Engine</a>");

p1.println("\r\n");

p1.println("Bye for Now");

p1.println("<b>The God of Love</b>");



p1.println(".");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);



p1.println("QUIT");

recvreply=d1.readLine(); 

System.out.println("Server Response : " + recvreply);



s1.close();

System.out.println("Closed Connection with Server");



}



catch(IOException e)

{

System.out.println("Error in Connecting to Port"); 

}





}



}
