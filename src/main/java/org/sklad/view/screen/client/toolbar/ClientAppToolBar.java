package org.sklad.view.screen.client.toolbar;

import org.sklad.view.screen.client.frames.*;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAppToolBar {

	private JFrame frame = null;

	private JPanel toolBarPanel = null;
	private final int WIDTH = 750;
	private final int HEIGHT = 50;

	private JButton catalogScreenButton = null;
	private JButton cartScreenButton = null;
	private JButton ordersScreenButton = null;
	private JButton profileScreenButton = null;
	private JButton exitButton = null;
	
	public ClientAppToolBar(JFrame frame){
		this.frame = frame;

		// Создание элементов панели
		toolBarPanel = new JPanel();
		toolBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		toolBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		catalogScreenButton = new JButton("Catalog");
		cartScreenButton = new JButton("Cart");
		ordersScreenButton = new JButton("Orders");
		profileScreenButton = new JButton("Profile");
		exitButton = new JButton("Exit");

		catalogScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogScreenButtonFunction();
			}
		});

		cartScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartScreenButtonFunction();
			}
		});

		ordersScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ordersScreenButtonFunction();
			}
		});

		profileScreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				profileScreenButtonFunction();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitButtonFunction();
			}
		});

		JPanel panel0 = new JPanel();
		toolBarPanel.add(panel0);
		GroupLayout tbLayout = new GroupLayout(panel0);
		panel0.setLayout(tbLayout);
		panel0.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		// tbLayout.setAutoCreateGaps(true);
		// tbLayout.setAutoCreateContainerGaps(true);
		
		tbLayout.setHorizontalGroup(tbLayout.createSequentialGroup()
			.addGap(320)
			.addComponent(catalogScreenButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
			.addComponent(cartScreenButton)
			.addComponent(ordersScreenButton)
			.addComponent(profileScreenButton)
			.addComponent(exitButton)			
		);

		tbLayout.setVerticalGroup(tbLayout.createSequentialGroup()
			.addGroup(tbLayout.createParallelGroup(CENTER)
				.addGap(50)
				.addComponent(catalogScreenButton)
				.addComponent(cartScreenButton)
				.addComponent(ordersScreenButton)
				.addComponent(profileScreenButton)
				.addComponent(exitButton)
			)
		);
	}

	public JPanel getPanel(){
		return toolBarPanel;
	}

	private void catalogScreenButtonFunction(){
		if(!(frame.getTitle().equals("Client catalog"))){
			new ClientCatalogScreenFrame();
			frame.dispose();
		}
	}

	private void cartScreenButtonFunction(){
		if(!(frame.getTitle().equals("Client cart"))){
			new ClientCartScreenFrame();
			frame.dispose();
		}		
	}

	private void ordersScreenButtonFunction(){
		if(!(frame.getTitle().equals("Client orders"))){
			new ClientOrdersScreenFrame();
			frame.dispose();
		}
	}

	private void profileScreenButtonFunction(){
		if(!(frame.getTitle().equals("Profile"))){
			new ProfileScreenFrame();
			frame.dispose();
		}
	}

	private void exitButtonFunction(){
		frame.dispose();
		new ClientScreenFrame();
	}
}
