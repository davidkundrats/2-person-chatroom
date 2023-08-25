package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final List <PrintWriter> clientWriters = new ArrayList<>(); 
	
	public static void main(String[] args) { 
		try {
			ServerSocket ss = new ServerSocket(5900);
			System.out.println("Server has started");
			while(true) { 
				
			Socket clientSocket = ss.accept(); 
			PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			clientWriters.add(clientWriter);
			
			Thread clientThread = new Thread (new ClientHandler(clientSocket, clientWriter)); 
			clientThread.start();
			} 
			
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	private static class ClientHandler implements Runnable {
		private Socket socket;
		private PrintWriter writer; 
		
		public ClientHandler(Socket clientSocket, PrintWriter clientWriter) { 
			this.socket = clientSocket; 
			this.writer = clientWriter;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try { 
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream())); 
				String message;
				while((message = in.readLine()) != null) { 
					System.out.println(message); 
					broadcast(message);
				}
				in.close(); 
				socket.close(); 
				clientWriters.remove(writer); 
			}
			catch (IOException e){ 
				e.printStackTrace();
			}
			
		}

		private void broadcast(String message) {
			for(PrintWriter writer : clientWriters) { 
					writer.println(message); 
			}
			
		}
		
	}
}
