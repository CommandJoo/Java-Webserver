package de.jo.webserver;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial", "unused", "static-access"})
public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Server server = new Server(57575);
		FileUtils fileUtils = new FileUtils();
		File file = new File(fileUtils.getJarFilePath()+File.separator+"files");
		if(!file.exists()) {
			file.mkdirs();
		}
		for(File file2 : file.listFiles()) {
			ArrayList<String> fileText = (ArrayList<String>) fileUtils.readFile(file2);
			if(file2.getName().toUpperCase().contains(".html".toUpperCase())) {
				String text = "";
				for(String s : fileText) {
					text += "\n" + s;
				}
				server.addSubElement(file2.getPath().replace(fileUtils.getJarFilePath()+File.separator+"files"+file.separator, ""), text);
				System.out.println(text);
				System.out.println(file2.getPath().replace(fileUtils.getJarFilePath()+File.separator+"files"+file.separator, ""));
			}else {
			}
		}
		new Main(server);
	}

	/**
	 * Create the frame.
	 */
	public Main(Server server) {
		setResizable(false);
		setTitle("Web-Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setVisible(true);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnNewButton.getText().equalsIgnoreCase("Start")) {
					server.start();;
					btnNewButton.setText("Stop");
				}
				else if(btnNewButton.getText().equalsIgnoreCase("Stop")) {
					server.stop();
					btnNewButton.setText("Start");
				}
			}
		});
		btnNewButton.setBounds(115, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.clearHTML();
				FileUtils fileUtils = new FileUtils();
				File file = new File(fileUtils.getJarFilePath()+File.separator+"files");
				if(!file.exists()) {
					file.mkdirs();
				}
				for(File file2 : file.listFiles()) {
					ArrayList<String> fileText = (ArrayList<String>) fileUtils.readFile(file2);
					if(file2.getName().toUpperCase().contains(".html".toUpperCase())) {
						String text = "";
						for(String s : fileText) {
							text += "\n" + s;
						}
						server.addSubElement(file2.getPath().replace(fileUtils.getJarFilePath()+File.separator+"files"+file.separator, ""), text);
						System.out.println(text);
						System.out.println(file2.getPath().replace(fileUtils.getJarFilePath()+File.separator+"files"+file.separator, ""));
					}else {
					}
				}
			}
		});
		btnNewButton_1.setBounds(239, 227, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
