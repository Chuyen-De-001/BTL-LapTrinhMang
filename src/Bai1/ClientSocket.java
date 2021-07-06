package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("CLIENT TO RUNING....");
		Socket socket = new Socket("localhost", ServeSocket.SERVER_PORT);
		Scanner scanner = new Scanner(System.in);
		DataOutputStream dataOutputStream = new DataOutputStream(
				socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(
				socket.getInputStream());
		dataOutputStream.writeUTF(ServeSocket.CONNECT_START);
		dataOutputStream.flush();

		String input = dataInputStream.readUTF();
		if (input.equals(ServeSocket.CONNECTED)) {
			System.out.println("CONNECTED TO SERVER");
		}
		while (true) {
			if (input.equals(ServeSocket.EXIT)) {
				break;
			}
			System.out.print("Nhập key:");
			String output = scanner.nextLine();
			System.out.println();
			dataOutputStream.writeUTF(output);
			dataOutputStream.flush();
			if (output.equals(ServeSocket.EXIT)) {
				break;
			}

			input = dataInputStream.readUTF();
			System.out.println(input);

		}
		System.out.println(ServeSocket.EXIT);
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

}
