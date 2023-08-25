package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try { 

			System.out.println("Client Started"); 
			Socket socket = new Socket("localhost", 5900); // replace your IP here
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true); 
			BufferedReader incoming = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

			Thread receiveThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String message;
						while ((message = incoming.readLine()) != null) {
							System.out.println("Server: " + message);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			receiveThread.start();

			String userMessage;
			while((userMessage = input.readLine()) != null) { 
				output.println(userMessage); 
				if(userMessage.equals("/exit")) { 
					break; 
				}
			}

			input.close(); 
			output.close(); 
			incoming.close(); 
			socket.close(); 

		}
		catch(Exception e) { // improve granularity
			System.out.println(e.toString()); 
		}


	}	
}
