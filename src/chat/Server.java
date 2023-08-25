package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) { 
		try {
			ServerSocket ss = new ServerSocket(5900);
			Socket clientSocket = ss.accept(); 
			BufferedReader in = new BufferedReader( 
					new InputStreamReader(clientSocket.getInputStream())); 
			String str = in.readLine(); 
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
			
//			while{ define this loop
//				
//			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
