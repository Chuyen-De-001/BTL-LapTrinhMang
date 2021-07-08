package Bai3;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
	static ServerSocket serverSocket;
	private JPanel contentPane;
	static JButton btnAgent;
	static int SERVER_PORT = 3000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverSocket = new ServerSocket(SERVER_PORT);
					Server frame = new Server();
					frame.setVisible(true);
					SocketSever server = new SocketSever();
					server.setServerSocket(serverSocket);
					server.setBtnAgent(btnAgent);
					server.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Server() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnAgent = new JButton("Agent");
		btnAgent.setBounds(0, 0, 89, 23);
		contentPane.add(btnAgent);
	}
}
