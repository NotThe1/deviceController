package device;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class TestDeviceController {

	private JFrame frame;
	private JTextArea txtLog;
	DeviceController deviceController;
	private JFormattedTextField ftfByteToSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestDeviceController window = new TestDeviceController();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try
			}// run
		});
	}// main

	private void initApplication() {
		deviceController = new DeviceController();

	}// initApplication
		// ----------------------------------------------------------------------------------------

	/**
	 * Create the application.
	 */
	public TestDeviceController() {
		initialize();
		initApplication();
	}// TestDeviceController

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 666);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelFromCPU = new JPanel();
		panelFromCPU.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"From CPU to Device Controller", TitledBorder.CENTER, TitledBorder.TOP, null, null), null));
		panelFromCPU.setBounds(10, 23, 303, 224);
		frame.getContentPane().add(panelFromCPU);
		panelFromCPU.setLayout(null);

		JButton btnSendByte = new JButton("Send Byte");
		btnSendByte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int thisValue = Integer.valueOf((String) ftfByteToSend.getValue(), 16);
				deviceController.byteToDevice((byte) 01, (byte) thisValue);
			}
		});
		btnSendByte.setBounds(10, 25, 89, 23);
		panelFromCPU.add(btnSendByte);

		JButton btnSendString = new JButton("Send String");
		btnSendString.setBounds(10, 59, 89, 23);
		panelFromCPU.add(btnSendString);

		try {
			ftfByteToSend = new JFormattedTextField(new MaskFormatter("HH"));
		} catch ( ParseException pe) {

		}
		ftfByteToSend.setBounds(110, 26, 86, 20);
		panelFromCPU.add(ftfByteToSend);

		JTextField txtStringToSend = new JTextField();
		txtStringToSend.setBounds(110, 60, 86, 20);
		panelFromCPU.add(txtStringToSend);
		txtStringToSend.setColumns(10);

		JPanel panelFromDevice = new JPanel();
		panelFromDevice.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"From Device Controller to CPU ", TitledBorder.CENTER, TitledBorder.TOP, null, null), null));
		panelFromDevice.setBounds(10, 325, 303, 224);
		frame.getContentPane().add(panelFromDevice);
		panelFromDevice.setLayout(null);

		JButton btnGetByte = new JButton("Get Byte");
		btnGetByte.setBounds(10, 33, 89, 23);
		panelFromDevice.add(btnGetByte);

		JFormattedTextField ftfByteReceived = new JFormattedTextField();
		ftfByteReceived.setBounds(110, 34, 86, 20);
		panelFromDevice.add(ftfByteReceived);

		JPanel panelLog = new JPanel();
		panelLog.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Log",
				TitledBorder.CENTER, TitledBorder.TOP, null, null), null));
		panelLog.setBounds(407, 35, 303, 514);
		frame.getContentPane().add(panelLog);
		panelLog.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 22, 294, 488);
		panelLog.add(scrollPane);

		JTextArea txtLog = new JTextArea();
		txtLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() >= 2) {
					JTextArea source = (JTextArea) me.getSource();
					source.setText("");
				}// if - double click
			}// mouseClicked
		});
		scrollPane.setViewportView(txtLog);
	}// initialize
}//
