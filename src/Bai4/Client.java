package Bai4;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {

	private JPanel contentPane;
	static Socket socket;
	private JTextField txtRecive;
	private JTextField txtInt;
	private JButton btnSend;
	private JLabel lblNewLabel_1;
	private JLabel txtResult;
	static DataOutputStream dataOutputStream;
	static DataInputStream dataInputStream;
	private JButton btnRandom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socket = new Socket("localhost", Server.SERVER_PORT);
					dataOutputStream = new DataOutputStream(
							socket.getOutputStream());

					dataInputStream = new DataInputStream(
							socket.getInputStream());
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
	 * 
	 * @throws IOException
	 */
	public Client() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0,
				Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Radom Int");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		txtRecive = new JTextField();
		GridBagConstraints gbc_txtRecive = new GridBagConstraints();
		gbc_txtRecive.insets = new Insets(0, 0, 5, 5);
		gbc_txtRecive.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRecive.gridx = 1;
		gbc_txtRecive.gridy = 0;
		contentPane.add(txtRecive, gbc_txtRecive);
		txtRecive.setColumns(10);

		btnRandom = new JButton("Random");

		GridBagConstraints gbc_btnRandom = new GridBagConstraints();
		gbc_btnRandom.insets = new Insets(0, 0, 5, 0);
		gbc_btnRandom.gridx = 2;
		gbc_btnRandom.gridy = 0;
		contentPane.add(btnRandom, gbc_btnRandom);

		lblNewLabel_1 = new JLabel("Nh\u1EADp s\u1ED1 ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtInt = new JTextField();
		GridBagConstraints gbc_txtInt = new GridBagConstraints();
		gbc_txtInt.gridwidth = 2;
		gbc_txtInt.insets = new Insets(0, 0, 5, 5);
		gbc_txtInt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInt.gridx = 1;
		gbc_txtInt.gridy = 2;
		contentPane.add(txtInt, gbc_txtInt);
		txtInt.setColumns(10);

		btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 5, 5);
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 3;
		contentPane.add(btnSend, gbc_btnSend);

		txtResult = new JLabel("Result");
		GridBagConstraints gbc_txtResult = new GridBagConstraints();
		gbc_txtResult.insets = new Insets(0, 0, 0, 5);
		gbc_txtResult.gridx = 1;
		gbc_txtResult.gridy = 5;
		contentPane.add(txtResult, gbc_txtResult);
		System.out.println("CLIENT RUNING...");
		connecStart(txtRecive);

		btnRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					connecStart(txtRecive);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sendNumber(txtInt, txtResult);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	static void connecStart(JTextField txtRecive) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(
				socket.getOutputStream());

		DataInputStream dataInputStream = new DataInputStream(
				socket.getInputStream());

		dataOutputStream.writeUTF(Server.CONNECT_START);
		dataOutputStream.flush();

		String input = dataInputStream.readUTF();
		txtRecive.setText(input);
	}

	static void sendNumber(JTextField txtInt, JLabel txtResult)
			throws IOException {
		String output = txtInt.getText();
		dataOutputStream.writeUTF(output);
		dataOutputStream.flush();

		String input = dataInputStream.readUTF();
		txtResult.setText(input);
	}

}
