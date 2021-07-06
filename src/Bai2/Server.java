package Bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	final static int SERVER_PORT = 3000;
	final static byte[] BYTE = new byte[4096];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVER RUNNING...");
		DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
		InetAddress ipAddress = InetAddress.getLocalHost();

		while (true) {
			DatagramPacket dataInputGramPacket = new DatagramPacket(BYTE,
					BYTE.length);
			serverSocket.receive(dataInputGramPacket);
			String input = new String(dataInputGramPacket.getData(), 0,
					dataInputGramPacket.getLength());
			System.out.println("Client send to Server: " + input);

			String output = "Bạn đã chọn màu: " + input;
			byte[] outputByte = output.getBytes();
			DatagramPacket dataOutputGramPacket = new DatagramPacket(outputByte,
					outputByte.length, dataInputGramPacket.getAddress(),
					dataInputGramPacket.getPort());
			serverSocket.send(dataOutputGramPacket);
		}
	}

}
