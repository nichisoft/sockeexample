package nicola.modugno.socketexample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {
	
	private ServerSocket server;
	private final int PORT = 9876;

	public static void main(String[] args) {
		MySocketServer mss=new MySocketServer();
		mss.openConnection();
		
	}

	private void openConnection() {
		try {
			server=new ServerSocket(PORT);
			
			while(true) {
				System.out.println("Waiting for the client request");
				Socket socket=server.accept();
				
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				try {
					String message=(String)ois.readObject();
					System.out.println("Message received: "+message);
					
					oos.writeObject("Hi Client: "+message);
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					if(oos!=null)
						oos.close();
					if(ois!=null)
						ois.close();
					socket.close();
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
