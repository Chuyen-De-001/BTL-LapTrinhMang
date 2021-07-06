package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeSocket {
	public final static int SERVER_PORT = 9555;
	final static String CONNECT_START = "start";
	final static String CONNECTED = "connected";
	final static String EXIT = "exit";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVER TO RUNING...");
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket socket = serverSocket.accept();

		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		while (true) {
			String input = dataInputStream.readUTF();
			if (input.equals(CONNECT_START)) {
				System.out.println("CLIENT CONNECTED");
				dataOutputStream.writeUTF(CONNECTED);
				dataOutputStream.flush();
			}
			if (input.equals(EXIT)) {
				break;
			}
 

		}

		System.out.println(EXIT);

	}

}
