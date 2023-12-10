package org.sklad;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class ClientCatalogScreenFrame {
	private JFrame frame;
	private final int WIDTH = 800;
	private final int HEIGHT = 490;

	private JPanel toolBarPanel = null;

	private JPanel catalogProductsPanel = null;
	private ArrayList<ProductIncatalogProductsPanel> productscatalogProductsPanel = null; 

	public ClientCatalogScreenFrame(){
		// Создание окна
        frame=new JFrame("Client catalog");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
		toolBarPanel = (new ClientAppToolBar(frame)).getPanel();

		catalogProductsPanel = new JPanel();
		catalogProductsPanel.setPreferredSize(new Dimension(750, 370));
		catalogProductsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

		// Компоновка элементов
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		for(int i = 0; i < 6; i++){
			panel1.add(new ProductIncatalogProductsPanel().getPanel());
			panel1.add(Box.createVerticalStrut(5));
		}
		JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, catalogProductsPanel.getWidth(), catalogProductsPanel.getHeight());
		
		catalogProductsPanel.setLayout(new BorderLayout());
		catalogProductsPanel.add(scrollPane);
		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JPanel panel = new JPanel();
		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		frame.add(panel);

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup(CENTER, false)
			.addComponent(toolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
			.addComponent(catalogProductsPanel)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(toolBarPanel)
			.addComponent(catalogProductsPanel)
		);
	}

	private class ProductIncatalogProductsPanel{
		JPanel panel = null;

		private JLabel productNameLabel = null;
		private JLabel productDescriptionLabel = null;
		private JLabel productImageLabel = null;
		private JLabel availableAmountTextLabel = null;
		private JLabel availableAmountValuLabel = null;
		private JLabel pricePerPieceTextLable = null;
		private JLabel pricePerPieceValueLabel = null;
		private JTextField choosedAmountTextField = null;
		private JLabel priceOfChoosedValueLabel = null;
		// private JLabel moneyUnitLabel = null;
		private JButton plusPieceButton = null;
		private JButton minusPieceButton = null;
		private JButton addToCartButton = null;

		// Placeholder
		public ProductIncatalogProductsPanel(){
			createElements();
			compose();
		}

		private void createElements(){
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

			productNameLabel = new JLabel("name placeholder");

			productDescriptionLabel = new JLabel("description placeholder");
			productDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
			// productDescriptionLabel.setMinimumSize(new Dimension(150, 50));
			productDescriptionLabel.setPreferredSize(new Dimension(370, 75));
			// productDescriptionLabel.setMaximumSize(new Dimension(500, 100));
			productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			productImageLabel = new JLabel();
			Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
			productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
			productImageLabel.setPreferredSize(new Dimension(75, 75));

			availableAmountTextLabel = new JLabel("Available amount: ");
			availableAmountValuLabel = new JLabel("10");

			pricePerPieceTextLable = new JLabel("Price per piece: ");
			pricePerPieceValueLabel = new JLabel("1");

			choosedAmountTextField = new JTextField("0");
			choosedAmountTextField.setColumns(4);
			choosedAmountTextField.setHorizontalAlignment(JTextField.CENTER);
			
			priceOfChoosedValueLabel = new JLabel("0");
			priceOfChoosedValueLabel.setPreferredSize(new Dimension(30, 10));

			// moneyUnitLabel = new JLabel(" $");

			plusPieceButton = new JButton("+");
			plusPieceButton.setPreferredSize(new Dimension(30, 50));
			plusPieceButton.setMaximumSize(new Dimension(30, 70));
			minusPieceButton = new JButton("-");
			minusPieceButton.setPreferredSize(new Dimension(30, 50));
			minusPieceButton.setMaximumSize(new Dimension(30, 70));

			addToCartButton = new JButton("Add to cart");

            
		}

		private void compose(){
			GroupLayout l = new GroupLayout(panel);
			panel.setLayout(l);

			l.setHorizontalGroup(l.createParallelGroup(CENTER)
				.addComponent(productNameLabel)
				.addGroup(l.createSequentialGroup()
					.addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(l.createParallelGroup()
						.addGroup(l.createSequentialGroup()
							.addComponent(availableAmountTextLabel)
							.addComponent(availableAmountValuLabel)
						)
						.addGroup(l.createSequentialGroup()
							.addComponent(pricePerPieceTextLable)
							.addComponent(pricePerPieceValueLabel)
						)
					)
					.addGap(10)
					.addGroup(l.createParallelGroup(CENTER)
						.addGroup(l.createSequentialGroup()
							.addComponent(minusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(l.createParallelGroup(CENTER)
								.addComponent(choosedAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(l.createSequentialGroup()
									.addComponent(priceOfChoosedValueLabel)
									// .addComponent(moneyUnitLabel)
								)
							)
							.addComponent(plusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						)
						.addComponent(addToCartButton)
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
							.addComponent(availableAmountTextLabel)
							.addComponent(availableAmountValuLabel)
						)
						.addGap(15)
						.addGroup(l.createParallelGroup()
							.addComponent(pricePerPieceTextLable)
							.addComponent(pricePerPieceValueLabel)
						)
					)
					.addGroup(l.createSequentialGroup()
						.addGroup(l.createParallelGroup()
							.addComponent(minusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(l.createSequentialGroup()
								.addComponent(choosedAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(l.createParallelGroup()
									.addComponent(priceOfChoosedValueLabel)
									// .addComponent(moneyUnitLabel)
								)
							)
							.addComponent(plusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						)
						.addComponent(addToCartButton)
					)
				)
			);
		}

		public JPanel getPanel(){
			return panel;
		}
	}
}
