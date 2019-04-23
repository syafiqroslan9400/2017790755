import java.net.*;

import java.io.*;

class tcpclient1 

{

	public static void main( String args[]) throws Exception

	{

		Socket cs = new Socket("192.168.145.129",6789);

	BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

	BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));

	DataOutputStream dos = new DataOutputStream(cs.getOutputStream());



	System.out.println(" Enter text..");

	

	  

	String s1,s4=null; 

	while(!(s1=kb.readLine()).equals("exit"))

	{

		//System.out.println(" data  send to server machine");

	 	dos.writeBytes(s1+"\n");

		

		

		s4 = br.readLine();

		//System.out.println(" data  receive from  server machine");

		System.out.println("Server said : "+s4);

		System.out.println("Enter text ");

		

	}

		System.out.println("Terminated..");

		cs.close(); 

		dos.close();

		kb.close();

	}

}
