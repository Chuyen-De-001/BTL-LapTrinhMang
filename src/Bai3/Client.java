package Bai3;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {

	private JPanel contentPane;
	static Socket socket;
	static DataInputStream dataInputStream;
	static DataOutputStream dataOutputStream;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socket = new Socket("localhost", Server.SERVER_PORT);
					dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream = new DataOutputStream(socket.getOutputStream());
					Client frame = new Client();
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
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnTop = new JButton("Top");

		GridBagConstraints gbc_btnTop = new GridBagConstraints();
		gbc_btnTop.insets = new Insets(0, 0, 5, 5);
		gbc_btnTop.gridx = 1;
		gbc_btnTop.gridy = 0;
		contentPane.add(btnTop, gbc_btnTop);

		JButton btnLeft = new JButton("Left");

		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 0;
		gbc_btnLeft.gridy = 1;
		contentPane.add(btnLeft, gbc_btnLeft);

		JButton btnRight = new JButton("Right");

		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.gridx = 2;
		gbc_btnRight.gridy = 1;
		contentPane.add(btnRight, gbc_btnRight);

		JButton btnBottom = new JButton("Bottom");

		GridBagConstraints gbc_btnBottom = new GridBagConstraints();
		gbc_btnBottom.insets = new Insets(0, 0, 0, 5);
		gbc_btnBottom.gridx = 1;
		gbc_btnBottom.gridy = 2;
		contentPane.add(btnBottom, gbc_btnBottom);

		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sendAction(SocketSever.ACTION_RIGHT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnBottom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sendAction(SocketSever.ACTION_BOTTOM);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sendAction(SocketSever.ACTION_LEFT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sendAction(SocketSever.ACTION_TOP);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void sendAction(int action) throws IOException {
		dataOutputStream.writeUTF(String.valueOf(action));
		dataOutputStream.flush();
	}

}
