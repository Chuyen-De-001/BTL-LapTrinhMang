package Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;

public class SocketSever extends Thread {
	static ServerSocket serverSocket;
	final static int ACTION_TOP = 0;
	final static int ACTION_LEFT = 1;
	final static int ACTION_RIGHT = 2;
	final static int ACTION_BOTTOM = 3;
	final static int STEP = 50;
	static int VERTICAL;
	static int HORIZONTAL;

	static JButton btnAgent;

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(ServerSocket serverSocket) {
		SocketSever.serverSocket = serverSocket;
	}

	public static JButton getBtnAgent() {
		return btnAgent;
	}

	public static void setBtnAgent(JButton btnAgent) {
		SocketSever.btnAgent = btnAgent;
	}

	public void run() {
		Socket socket;
		System.out.println("SERVER RUNNING...");
		try {
			socket = serverSocket.accept();
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutpuStream = new DataOutputStream(socket.getOutputStream());
			while (true) {
				int input = Integer.parseInt(dataInputStream.readUTF());
				int vertical;
				int horizontal;
				switch (input) {
				case ACTION_TOP:
					vertical = btnAgent.getBounds().y - STEP;
					btnAgent.setBounds(btnAgent.getBounds().x, vertical, btnAgent.getBounds().width,
							btnAgent.getBounds().height);
					System.out.println("Top");
					break;
				case ACTION_LEFT:
					horizontal = btnAgent.getBounds().x - STEP;
					btnAgent.setBounds(horizontal, btnAgent.getBounds().y, btnAgent.getBounds().width,
							btnAgent.getBounds().height);
					System.out.println("Left");
					break;
				case ACTION_RIGHT:
					horizontal = btnAgent.getBounds().x + STEP;
					btnAgent.setBounds(horizontal, btnAgent.getBounds().y, btnAgent.getBounds().width,
							btnAgent.getBounds().height);
					System.out.println("Right");
					break;
				case ACTION_BOTTOM:
					vertical = btnAgent.getBounds().y + STEP;
					btnAgent.setBounds(btnAgent.getBounds().x, vertical, btnAgent.getBounds().width,
							btnAgent.getBounds().height);
					System.out.println("Bottom");
					break;
				default:
					break;
				}

				System.out.println(input);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
