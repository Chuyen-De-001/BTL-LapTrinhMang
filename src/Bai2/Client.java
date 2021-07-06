package Bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("CLIENT RUNNING...");
		DatagramSocket dataGramSocket = new DatagramSocket();
		InetAddress ipAddress = InetAddress.getLocalHost();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Nhập key: ");
			String output = scanner.nextLine();

			// send packet
			byte[] outputByte = output.getBytes();
			DatagramPacket dataGramPacketOutput = new DatagramPacket(outputByte,
					outputByte.length, ipAddress, Server.SERVER_PORT);
			dataGramSocket.send(dataGramPacketOutput);

			// receive packet
			DatagramPacket dataGramPacketInput = new DatagramPacket(Server.BYTE,
					Server.BYTE.length);
			dataGramSocket.receive(dataGramPacketInput);
			String input = new String(dataGramPacketInput.getData());
		}

	}

}
