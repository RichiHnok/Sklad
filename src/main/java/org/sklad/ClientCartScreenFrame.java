package org.sklad;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class ClientCartScreenFrame {
	private JFrame frame;
	private final int WIDTH = 800;
	private final int HEIGHT = 490;

	private JPanel toolBarPanel = null;

	private JPanel cartProductsPanel = null;
	private ArrayList<ProductInCartPanel> cartProductsPanels = null;
	
	private JPanel orderingPanel = null;

	public ClientCartScreenFrame(){
		// Создание окна
        frame=new JFrame("Client cart");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
		toolBarPanel = (new ClientAppToolBar(frame)).getPanel();
		
		cartProductsPanel = new JPanel();
		cartProductsPanel.setSize(100, 100);
		cartProductsPanel.setPreferredSize(new Dimension(500, 370));
		cartProductsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		
		orderingPanel = new OrderingPanel().getPanel();		

		// Компоновка элементов
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		for(int i = 0; i < 20; i++){
			panel1.add(new ProductInCartPanel().getPanel());
			panel1.add(Box.createVerticalStrut(5));
		}
		JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, cartProductsPanel.getWidth(), cartProductsPanel.getHeight());
		
		cartProductsPanel.setLayout(new BorderLayout());
		cartProductsPanel.add(scrollPane);
		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

//
		JPanel panelM = new JPanel();
		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		frame.add(panelM);

		GroupLayout layout = new GroupLayout(panelM);
		panelM.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup(CENTER, false)
			.addComponent(toolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
			.addGroup(layout.createSequentialGroup()
				.addComponent(cartProductsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
				.addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
			)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(toolBarPanel)
			.addGroup(layout.createParallelGroup(CENTER, false)
				.addComponent(cartProductsPanel)
				.addComponent(orderingPanel)
			)
		);
	}

	private class OrderingPanel{
		private JPanel panel = null;

		private JLabel nameLabel = null;
		private JTextField nameTextField = null;
		private JLabel phoneNumberLabel = null;
		private JTextField phoneNumberTextField = null;
		private JLabel addressLabel = null;
		private JTextField addressTextField = null;
		private JLabel deliveringDateLabel = null;
		private JTextField deliveringDatextField = null;
		private JButton makeOrderButton = null;

		public OrderingPanel(){
			panel = new JPanel();

			panel.setSize(100, 100);
			panel.setPreferredSize(new Dimension(250, 370));
			panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			nameLabel = new JLabel("Name:");
			nameTextField = new JTextField(15);
			phoneNumberLabel = new JLabel("Phone:");
			phoneNumberTextField = new JTextField(15);
			addressLabel = new JLabel("Address:");
			addressTextField = new JTextField(15);
			deliveringDateLabel = new JLabel("Date to deliver:");
			deliveringDatextField = new JTextField(15);

			makeOrderButton = new JButton("Make order");

			makeOrderButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					makeOrderButtonFunction();
				}
			});

			JPanel panel2 = new JPanel();
			panel.add(panel2);
			GroupLayout oLayout = new GroupLayout(panel2);
			panel2.setLayout(oLayout);
			// oLayout.setAutoCreateGaps(true);
			// oLayout.setAutoCreateContainerGaps(true);

			oLayout.setHorizontalGroup(oLayout.createParallelGroup(CENTER)
				.addComponent(nameLabel)
				.addComponent(nameTextField)
				.addComponent(phoneNumberLabel)
				.addComponent(phoneNumberTextField)
				.addComponent(addressLabel)
				.addComponent(addressTextField)
				.addComponent(deliveringDateLabel)
				.addComponent(deliveringDatextField)
				.addComponent(makeOrderButton)
			);

			oLayout.setVerticalGroup(oLayout.createParallelGroup()
				.addGroup(oLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(nameLabel)
					.addComponent(nameTextField)
					.addComponent(phoneNumberLabel)
					.addComponent(phoneNumberTextField)
					.addComponent(addressLabel)
					.addComponent(addressTextField)
					.addComponent(deliveringDateLabel)
					.addComponent(deliveringDatextField)
					.addComponent(makeOrderButton)
				)
			);
		}

		private void makeOrderButtonFunction(){

		}		

		public JPanel getPanel(){
			return panel;
		}
	}

	private class ProductInCartPanel{
		
		private JPanel panel = null;

		private JLabel productNameLabel = null;
		private JLabel productDescriptionLabel = null;
		private JLabel productImageLabel = null;
		private JLabel amountTextLabel = null;
		private JLabel amountValueLabel = null;
		private JLabel priceTextLabel = null;
		private JLabel priceValueLabel = null;
		private JButton removeButton = null;

		// Placeholder
		public ProductInCartPanel(){
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

			productNameLabel = new JLabel("name placeholder");

			productDescriptionLabel = new JLabel("description placeholder");
			productDescriptionLabel.setMinimumSize(new Dimension(150, 75));
			productDescriptionLabel.setPreferredSize(new Dimension(300, 75));
			productDescriptionLabel.setMaximumSize(new Dimension(450, 75));
			productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			productImageLabel = new JLabel();
			Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
			productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
			productImageLabel.setPreferredSize(new Dimension(75, 75));

			amountTextLabel = new JLabel("Amount:");
			amountValueLabel = new JLabel("1");

			priceTextLabel = new JLabel("Price:");
			priceValueLabel = new JLabel("10");

			removeButton = new JButton("Remove");
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					removeButtonFunction();
				}
			});

			compose();
		}

		private void compose(){
			GroupLayout l = new GroupLayout(panel);
			panel.setLayout(l);

			l.setHorizontalGroup(l.createParallelGroup(CENTER)
				.addComponent(productNameLabel)
				.addGroup(l.createSequentialGroup()
					.addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(l.createParallelGroup(CENTER)
						.addGroup(l.createSequentialGroup()
							.addComponent(amountTextLabel)
							.addComponent(amountValueLabel)
						)
						.addGroup(l.createSequentialGroup()
							.addComponent(priceTextLabel)
							.addComponent(priceValueLabel)
						)
						.addComponent(removeButton)
					)
				)
			);

			l.setVerticalGroup(l.createSequentialGroup()
				.addComponent(productNameLabel)
				.addGroup(l.createParallelGroup(CENTER)
					.addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)				
					.addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(l.createSequentialGroup()
						.addGroup(l.createParallelGroup()
							.addComponent(amountTextLabel)
							.addComponent(amountValueLabel)
						)
						.addGroup(l.createParallelGroup()
							.addComponent(priceTextLabel)
							.addComponent(priceValueLabel)
						)
						.addComponent(removeButton)
					)
				)
			);
		}

		private void removeButtonFunction(){

		}

		public JPanel getPanel(){
			return panel;
		}
	}

}
