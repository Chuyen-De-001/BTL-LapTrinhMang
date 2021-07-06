package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
	final static int SERVER_PORT = 9555;
	final static String CONNECT_START = "start";
	final static String CONNECTED = "connected";
	final static String EXIT = "exit";
	@SuppressWarnings("resource")

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("CLIENT TO RUNING....");
		Socket socket = new Socket("localhost", SERVER_PORT);
		Scanner scanner = new Scanner(System.in);
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream.writeUTF(CONNECT_START);
		dataOutputStream.flush();

		while (true) {
			String input = dataInputStream.readUTF();
			if (input.equals(CONNECTED)) {
				System.out.println("CONNECTED TO SERVER");
			}
			if (input.equals(EXIT)) {
				break;
			}
			if (!input.equals(CONNECT_START) || !input.equals(CONNECTED) || !input.equals(EXIT)) {

				System.out.print("Nhập key:");
				String output = scanner.nextLine();
				System.out.println();
				dataOutputStream.writeUTF(output);
				dataOutputStream.flush();
			}

		}
		System.out.println(EXIT);
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

}
