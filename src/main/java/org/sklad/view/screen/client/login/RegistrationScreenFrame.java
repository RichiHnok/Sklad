package org.sklad.view.screen.client.login;

import org.sklad.model.Client;
import org.sklad.repository.ClientRepo;
import org.sklad.view.screen.ChoosingRoleFrame;
import org.sklad.view.screen.client.frames.ClientScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegistrationScreenFrame {

	private JFrame frame;
	private final int WIDTH = 200;
	private final int HEIGHT = 400;

	// Login Info
	JLabel newLoginLabel = null;
	JTextField newLoginTextField = null;
	// Password Info
	JLabel newPasswordLabel = null;
	JTextField newPasswordTextField = null;
	// Confirm Password
	JLabel newPasswordRepeatLabel = null;
	JTextField newPasswordRepeatTextField = null;
	// Address Info
	JLabel newAddressLabel = null;
	JTextField newAddressTextField = null;
	// Buttons
	JButton acceptButton = null;
	JButton loginScreenButton = null;
	JButton exitButton = null;

	public RegistrationScreenFrame() {
		// Создание окна
		frame = new JFrame("Registration Screen");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		// Создание элементов окна
		newLoginLabel = new JLabel("Login:");
		newLoginTextField = new JTextField(15);

		newPasswordLabel = new JLabel("Pasword:");
		newPasswordTextField = new JTextField();

		newPasswordRepeatLabel = new JLabel("Password repeat:");
		newPasswordRepeatTextField = new JTextField();

		newAddressLabel = new JLabel("Address:");
		newAddressTextField = new JTextField();

		acceptButton = new JButton("Accept");
		loginScreenButton = new JButton("Login");
		exitButton = new JButton("Exit");

		acceptButton = new JButton("Accept");
		loginScreenButton = new JButton("Login");
		exitButton = new JButton("Exit");

		acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				acceptButtonFunction();
			}
        });
		
		loginScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				loginScreenButtonFunction();
			}
        });		

		exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonFunction();
            }
        });

		// Компоновка элементов
		JPanel panel = new JPanel();

		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		frame.add(panel);

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(CENTER, false)
				.addComponent(newLoginLabel)
				.addComponent(newLoginTextField)
				.addComponent(newPasswordLabel)
				.addComponent(newPasswordTextField)
				.addComponent(newPasswordRepeatLabel)
				.addComponent(newPasswordRepeatTextField)
				.addComponent(newAddressLabel)
				.addComponent(newAddressTextField)
				.addComponent(acceptButton)
				.addComponent(loginScreenButton)
				.addGap(20)
				.addComponent(exitButton)				
			)
		);
		layout.linkSize(SwingConstants.HORIZONTAL, acceptButton, loginScreenButton, exitButton);

		layout.setVerticalGroup(layout.createParallelGroup(CENTER, false)
			.addGroup(layout.createSequentialGroup()
				.addComponent(newLoginLabel)
				.addComponent(newLoginTextField)
				.addComponent(newPasswordLabel)
				.addComponent(newPasswordTextField)
				.addComponent(newPasswordRepeatLabel)
				.addComponent(newPasswordRepeatTextField)
				.addComponent(newAddressLabel)
				.addComponent(newAddressTextField)
				.addComponent(acceptButton)
				.addComponent(loginScreenButton)
				.addGap(20)
				.addComponent(exitButton)
			)
		);
	}

	private void acceptButtonFunction(){
		ClientRepo repository = new ClientRepo();
		if (newLoginTextField.getText().equals("") || newLoginTextField.getText() == null){
			System.out.println("Login vvedi dolben!");
		} else if (newPasswordTextField.getText().equals("") || (newPasswordTextField.getText() == null)){
			System.out.println("Password vvedi dolben!");
		} else if (newPasswordRepeatTextField.getText().equals("") || (newPasswordRepeatTextField.getText() == null)){
			System.out.println("Password Confirm vvedi dolben!");
		} else if (!newPasswordTextField.getText().equals(newPasswordRepeatTextField.getText())){
			System.out.println("Dva odinakovyh parolya vvedi dolben!");
		} else if (newAddressTextField.getText().equals("") || (newAddressTextField.getText() == null)){
			System.out.println("Address vvedi dolben!");
		} else{

			Client client = new Client(
					newLoginTextField.getText(),
					newPasswordTextField.getText(),
					newAddressTextField.getText()
			);

			frame.dispose();
			if (repository.isExist(client)){
				System.out.println("Уже есть такой пользователь");
				new LoginScreenFrame();
			} else {
//				repository.setCurrentClient(client);
				repository.addClientToTable(client);
				new ClientScreenFrame();
			}
		}
	}

	private void loginScreenButtonFunction(){
		frame.dispose();
		new LoginScreenFrame();
	}

	private void exitButtonFunction(){
		frame.dispose();
		new ChoosingRoleFrame();
	}
}
