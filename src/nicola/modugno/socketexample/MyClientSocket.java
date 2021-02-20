package nicola.modugno.socketexample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientSocket {

	private final int PORT = 9876;
	
	public static void main(String[] args) {
		MyClientSocket mcs=new MyClientSocket();
		mcs.openConnection();

	}

	private void openConnection() {
		try {
			InetAddress host=InetAddress.getLocalHost();
			Socket socket=null;
			ObjectOutputStream oos=null;
			ObjectInputStream ois=null;
			try {
				socket=new Socket(host.getHostName(), PORT);
				oos=new ObjectOutputStream(socket.getOutputStream());
				System.out.println("Sending request to Socket Server");
				oos.writeObject("pippo");
				ois=new ObjectInputStream(socket.getInputStream());
				try {
					String message=(String)ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}		
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(ois!=null)
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				if(oos!=null)
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
