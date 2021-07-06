package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class ServeSocket {
	final static int SERVER_PORT = 9555;
	final static String CONNECT_START = "start";
	final static String CONNECTED = "connected";
	final static String EXIT = "exit";
	final static String KEY_TIME = "time";
	final static String KEY_DATE = "date";
	final static String KEY_NOW = "now";
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVER TO RUNING...");
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket socket = serverSocket.accept();

		DataOutputStream dataOutputStream = new DataOutputStream(
				socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(
				socket.getInputStream());

		String input = dataInputStream.readUTF();
		if (input.equals(CONNECT_START)) {
			System.out.println("CLIENT CONNECTED");
			dataOutputStream.writeUTF(CONNECTED);
			dataOutputStream.flush();
		}

		while (true) {
			String output = "";
			input = dataInputStream.readUTF();
			if (input.equals(EXIT)) {
				break;
			}
			if (!input.equals(CONNECT_START)) {
				Calendar calendar = Calendar.getInstance();
				switch (input.toLowerCase()) {
					case KEY_TIME :

						output = "Thời gian của hệ thống là: "
								+ calendar.get(Calendar.HOUR_OF_DAY) + " giờ";
						break;
					case KEY_DATE :
						output = "Ngày hệ của hệ thống là: "
								+ calendar.get(Calendar.DATE) + " tháng "
								+ (calendar.get(Calendar.MONTH) + 1);
						break;
					case KEY_NOW :
						output = "Giờ và ngày hệ thống là: "
								+ calendar.get(Calendar.HOUR_OF_DAY)
								+ "giờ, ngày " + calendar.get(Calendar.DATE)
								+ " tháng "
								+ (calendar.get(Calendar.MONTH) + 1);
						break;
					default :
						output = "sai cú pháp";
						break;
				}
				dataOutputStream.writeUTF(output);
				dataOutputStream.flush();
			}

		}
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
		serverSocket.close();
	}

	public static String getDate() {

		return null;
	}

}
