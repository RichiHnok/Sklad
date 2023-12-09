package org.sklad;

import java.util.Date;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientScreenFrame {

	private JFrame frame = null;

	private Client client = null;

	private final int WIDTH = 450;
    private final int HEIGHT = 200;

	JButton useApplicationButton = null;
	JButton checkCourierButton = null;
	JButton exitButton = null;

	public ClientScreenFrame(){
		System.out.println((new Date()).toString());
		
		// Создание окна
        frame=new JFrame("Client");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
		useApplicationButton = new JButton("Use Application");
		checkCourierButton = new JButton("Check Courier");
		exitButton = new JButton("Exit");

		useApplicationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				useApplicationButtonFunction();
			}
		});

		checkCourierButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				checkCourierButtonFunction();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
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

		layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
			.addGroup(layout.createSequentialGroup()
				.addComponent(useApplicationButton)
				.addComponent(checkCourierButton)
				.addComponent(exitButton)
			)
		);
		layout.linkSize(SwingConstants.HORIZONTAL, useApplicationButton, checkCourierButton, exitButton);

		layout.setVerticalGroup(
			layout.createParallelGroup(CENTER)
			.addComponent(useApplicationButton)
			.addComponent(checkCourierButton)
			.addComponent(exitButton)
		);
	}

	private void useApplicationButtonFunction(){
		frame.dispose();
		new ClientCatalogScreenFrame();
	}

	private void checkCourierButtonFunction(){

	}

	private void exitButtonFunction(){
		frame.dispose();
		new LoginScreenFrame();
	}
}
