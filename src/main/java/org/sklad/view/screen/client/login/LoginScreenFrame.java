package org.sklad.view.screen.client.login;

import org.sklad.model.Client;
import org.sklad.repository.ClientRepo;
import org.sklad.view.screen.ChoosingRoleFrame;
import org.sklad.view.screen.client.frames.ClientScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class LoginScreenFrame {

	private JFrame frame;

	private final int WIDTH = 200;
    private final int HEIGHT = 300;

	JLabel loginLabel = null;
	JTextField loginTextField = null;

	JLabel passwordLabel = null;
	JTextField passwordTextField = null;

	JButton acceptButton = null;
	JButton registrationButton = null;
	JButton exitButton = null;

	public LoginScreenFrame(){
		// Создание окна
		frame = new JFrame("Login Screen");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
		loginLabel = new JLabel("Login:");
		loginTextField = new JTextField(15);

		passwordLabel = new JLabel("Password:");
		passwordTextField = new JTextField();

		acceptButton = new JButton("Accept");
		registrationButton = new JButton("Registration");
		exitButton = new JButton("Exit");

		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				acceptButtonFunction();
			}
		});

		registrationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				registrationButtonFunction();
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
		// GroupLayout layout = new GroupLayout(frame.getContentPane());
		// frame.getContentPane().setLayout(layout);		
		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

		// GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		// hGroup.addGroup(layout.createParallelGroup(CENTER, false).addComponent(loginLabel));
		// layout.setHorizontalGroup(hGroup);

		// GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(CENTER, false);
		// vGroup.addGroup(layout.createSequentialGroup().addComponent(loginLabel));
		// layout.setVerticalGroup(vGroup);

		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(CENTER, false)
				.addComponent(loginLabel)
				.addComponent(loginTextField)
				.addComponent(passwordLabel)
				.addComponent(passwordTextField)
				.addComponent(acceptButton)
				.addComponent(registrationButton)
				.addGap(20)
				.addComponent(exitButton)
			)
		);
		layout.linkSize(SwingConstants.HORIZONTAL, acceptButton, registrationButton, exitButton);

		layout.setVerticalGroup(layout.createParallelGroup(CENTER, false)
			.addGroup(layout.createSequentialGroup()
				.addComponent(loginLabel)
				.addComponent(loginTextField)
				.addComponent(passwordLabel)
				.addComponent(passwordTextField)
				.addComponent(acceptButton)
				.addComponent(registrationButton)
				.addGap(20)
				.addComponent(exitButton)
			)
		);
	}

	private void acceptButtonFunction(){
		ClientRepo repository = new ClientRepo();
		if (loginTextField.getText().equals("") || loginTextField.getText() == null){
			System.out.println("Login vvedi dolben!");
		} else if (passwordTextField.getText().equals("") || (passwordTextField.getText() == null)){
			System.out.println("Password vvedi dolben!");
		} else {
			Client client = new Client(loginTextField.getText(), passwordTextField.getText());


			frame.dispose();
			if (repository.checkCurrentClient(client)){
				new ClientScreenFrame();
			} else {
				System.out.println("Oshibka!");
				new LoginScreenFrame();
			}

		}
	}
	
	private void registrationButtonFunction(){
		frame.dispose();
		new RegistrationScreenFrame();
	}

	private void exitButtonFunction(){
		ClientRepo repo = new ClientRepo();
		repo.setOrderInfo(null);
		repo.setCurrentClient(null);
		frame.dispose();
		new ChoosingRoleFrame();
	}
}
