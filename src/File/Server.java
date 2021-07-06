package File;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final static int SERVER_PORT = 9555;
	final static int MAX_SIZE = 3000000;
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub;
		System.out.print("SERVER RUNING...");
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket socket = serverSocket.accept();

		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		String output = "";
		while (true) {
			String input = dataInputStream.readUTF();
			System.out.print(input);
			File file = new File(input);
			if (input.equals("exit")) {
				break;
			}

			String extention = file.getName().substring(file.getName().lastIndexOf('.') + 1);
			System.out.print(file.length());
			if (!extention.equals("jpg")) {
				output = "File wrong fomat";
			} else if (file.length() > MAX_SIZE) {
				output = "File max size";
			} else if (!file.exists()) {
				output = "File not exits";
			} else {
				// copy file
				String path = input;
				String newPath = file.getParent() + "output.jpg";
				System.out.print(newPath);
				output = CoppyFile(path, newPath);
			}
			dataOutputStream.writeUTF(output);
			dataOutputStream.flush();
		}
	}

	public static String CoppyFile(String path, String newPath) {
		try {
			FileInputStream inStream = new FileInputStream(new File(path));
			FileOutputStream outStream = new FileOutputStream(new File(newPath));

			int length;
			byte[] buffer = new byte[1024];

			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			System.out.println("File is copied successful!");
			return newPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
