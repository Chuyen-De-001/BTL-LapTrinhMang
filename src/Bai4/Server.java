package Bai4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
	static int SERVER_PORT = 3000;
	static String CONNECT_START = "start";
	static int RANDOM_NUMBER = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVER RUNING...");
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket socket = serverSocket.accept();

		DataInputStream dataInputStream = new DataInputStream(
				socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(
				socket.getOutputStream());

		while (true) {
			String input = dataInputStream.readUTF();
			String output = "";
			if (input.equals(CONNECT_START)) {
				RANDOM_NUMBER = RadomInt();
				System.out.println("random number is: " + RANDOM_NUMBER);
				output = String.valueOf(RANDOM_NUMBER);
				System.out.println(output);
			} else {
				try {
					int number = Integer.parseInt(input);
					if (number == RANDOM_NUMBER) {
						output = "Chính xác";
					} else {
						output = "Không chính xác";
					}
				} catch (Exception e) {
					output = "Hãy nhập số";
				}

			}
			dataOutputStream.writeUTF(output);
			dataOutputStream.flush();
		}
	}

	public static int RadomInt() {
		Random generator = new Random();
		return generator.nextInt(100);
	}
}
