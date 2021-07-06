package Bai2;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ClientJframe extends JFrame {

	private JPanel contentPane;
	static DatagramSocket dataGramSocket;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dataGramSocket = new DatagramSocket();
					ClientJframe frame = new ClientJframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientJframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0,
				Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0,
				Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JButton btnRed = new JButton("\u0110\u1ECF");
		GridBagConstraints gbc_btnRed = new GridBagConstraints();
		gbc_btnRed.insets = new Insets(0, 0, 5, 5);
		gbc_btnRed.gridx = 0;
		gbc_btnRed.gridy = 0;
		contentPane.add(btnRed, gbc_btnRed);

		JButton btnGreen = new JButton("Xanh");

		GridBagConstraints gbc_btnGreen = new GridBagConstraints();
		gbc_btnGreen.insets = new Insets(0, 0, 5, 5);
		gbc_btnGreen.gridx = 1;
		gbc_btnGreen.gridy = 0;
		contentPane.add(btnGreen, gbc_btnGreen);

		JButton btnWhite = new JButton("Tr\u1EAFng");

		GridBagConstraints gbc_btnWhite = new GridBagConstraints();
		gbc_btnWhite.insets = new Insets(0, 0, 5, 0);
		gbc_btnWhite.gridx = 2;
		gbc_btnWhite.gridy = 0;
		contentPane.add(btnWhite, gbc_btnWhite);

		JLabel txtResponse = new JLabel("Response");
		GridBagConstraints gbc_txtResponse = new GridBagConstraints();
		gbc_txtResponse.gridwidth = 3;
		gbc_txtResponse.insets = new Insets(0, 0, 0, 5);
		gbc_txtResponse.gridx = 0;
		gbc_txtResponse.gridy = 2;
		contentPane.add(txtResponse, gbc_txtResponse);
		btnRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Sendpacket("Đỏ", txtResponse);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Sendpacket("Xanh", txtResponse);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Sendpacket("Trắng", txtResponse);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public void Sendpacket(String color, JLabel txtResponse)
			throws IOException {
		InetAddress ipAddress = InetAddress.getLocalHost();
		byte[] outputByte = color.getBytes();

		DatagramPacket dataGramPacket = new DatagramPacket(outputByte,
				outputByte.length, ipAddress, Server.SERVER_PORT);
		dataGramSocket.send(dataGramPacket);

		// receive packet
		DatagramPacket dataGramPacketInput = new DatagramPacket(Server.BYTE,
				Server.BYTE.length);
		dataGramSocket.receive(dataGramPacketInput);
		String input = new String(dataGramPacketInput.getData(), 0,
				dataGramPacketInput.getLength());
		txtResponse.setText(input);
		System.out.println(input);

	}

}
