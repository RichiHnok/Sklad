package org.sklad.view.screen.client.frames;

import org.sklad.model.Client;
import org.sklad.model.Order;
import org.sklad.model.OrderStatus;
import org.sklad.model.Product;
import org.sklad.repository.ClientRepo;
import org.sklad.util.Toast;
import org.sklad.util.Utils;
import org.sklad.view.screen.client.toolbar.ClientAppToolBar;

import static javax.swing.GroupLayout.Alignment.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ClientOrdersScreenFrame {
	private JFrame frame;
	private final int WIDTH = 800;
	private final int HEIGHT = 490;
	private JPanel toolBarPanel = null;
	private JPanel ordersPanel = null;

	private ClientRepo clientRepository;
	private Client currentClient;

	public ClientOrdersScreenFrame(){
		clientRepository = new ClientRepo();
		currentClient = clientRepository.getCurrentClient();

		// Создание окна
        frame=new JFrame("Client orders");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
		toolBarPanel = (new ClientAppToolBar(frame)).getPanel();

		ordersPanel = new JPanel();
		ordersPanel.setPreferredSize(new Dimension(750, 370));
		ordersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

		// Компоновка элементов
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		// TODO(Переделать)
//		for(int i = 0; i < 3; i++){
//			panel1.add(new OrderPanel().getPanel());
//			panel1.add(Box.createVerticalStrut(10));
//		}

		for (Order order: currentClient.orders) {
			panel1.add(new OrderPanel(order).getPanel());
			panel1.add(Box.createVerticalStrut(10));
		}



		JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, ordersPanel.getWidth(), ordersPanel.getHeight());
		
		ordersPanel.setLayout(new BorderLayout());
		ordersPanel.add(scrollPane);
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
			.addComponent(ordersPanel)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(toolBarPanel)
			.addComponent(ordersPanel)
		);
	}

	private class OrderPanel{
		
		private JPanel panel = null;
		private JLabel orderIdLabel = null;

		private JPanel productsPanel = null;

		private JPanel orderInfoPanel = null;

		private Font anotherFont = new Font("Verdana", Font.PLAIN, 18);

		// Placeholder
		public OrderPanel(){
			createElements();
			compose();
		}

		public OrderPanel(Order order){
			createElements(order);
			compose();
		}

		private void createElements(){
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			orderIdLabel = new JLabel("order id placeholder");
			orderIdLabel.setFont(anotherFont);

			productsPanel = new JPanel();
			productsPanel.setPreferredSize(new Dimension(450, 250));
			productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			productsPanel.setLayout(new BorderLayout());
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

			// TODO(Переделать)
			for(int i = 0; i < 20; i++){
				panel1.add(new ProductInOrderPanel().getPanel());
				panel1.add(Box.createVerticalStrut(5));
			}
			
			JScrollPane scrollPane = new JScrollPane(panel1);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(0, 0, productsPanel.getWidth(), productsPanel.getHeight());

			productsPanel.add(scrollPane);
			panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));


			orderInfoPanel = (new OrderInfoPanel()).getPanel();
			orderInfoPanel.setPreferredSize(new Dimension(250, 250));
			orderInfoPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}

		private void createElements(Order order){
			panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			orderIdLabel = new JLabel("order id : " + order.getId());
			orderIdLabel.setFont(anotherFont);

			productsPanel = new JPanel();
			productsPanel.setPreferredSize(new Dimension(450, 250));
			productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			productsPanel.setLayout(new BorderLayout());

			JPanel panel1 = new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

			// TODO(Переделать)
//			for(int i = 0; i < 20; i++){
//				panel1.add(new ProductInOrderPanel().getPanel());
//				panel1.add(Box.createVerticalStrut(5));
//			}

			for (Product product: order.deliveryProducts) {
				panel1.add(new ProductInOrderPanel(product).getPanel());
				panel1.add(Box.createVerticalStrut(5));
			}

			JScrollPane scrollPane = new JScrollPane(panel1);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(0, 0, productsPanel.getWidth(), productsPanel.getHeight());

			productsPanel.add(scrollPane);
			panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));


//			orderInfoPanel = (new OrderInfoPanel()).getPanel();
			orderInfoPanel = (new OrderInfoPanel(order)).getPanel();
			orderInfoPanel.setPreferredSize(new Dimension(250, 250));
			orderInfoPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}

		private void compose(){
			GroupLayout l = new GroupLayout(panel);
			panel.setLayout(l);

			l.setHorizontalGroup(l.createParallelGroup(CENTER)
				.addComponent(orderIdLabel)
				.addGroup(l.createSequentialGroup()
					.addComponent(productsPanel)
					.addGap(10)
					.addComponent(orderInfoPanel)
				)
			);

			l.setVerticalGroup(l.createSequentialGroup()
				.addComponent(orderIdLabel)
				.addGroup(l.createParallelGroup()
					.addComponent(productsPanel)
					.addComponent(orderInfoPanel)
				)
			);
		}

		public JPanel getPanel(){
			return panel;
		}

		private class ProductInOrderPanel{

			private JPanel panel = null;

			private JLabel productNameLabel = null;
			private JLabel productDescriptionLabel = null;
			private JLabel productImageLabel = null;
			private JLabel amountTextLabel = null;
			private JLabel amountValueLabel = null;
			private JLabel priceTextLabel = null;
			private JLabel priceValueLabel = null;

			public ProductInOrderPanel(){
				createElements();
				compose();
			}
			public ProductInOrderPanel(Product product){
				createElements(product);
				compose();
			}

			private void createElements(){
				panel = new JPanel();
				panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

				productNameLabel = new JLabel("name placeholder");

				productDescriptionLabel = new JLabel("description placeholder");
				productDescriptionLabel.setMinimumSize(new Dimension(100, 75));
				productDescriptionLabel.setPreferredSize(new Dimension(250, 75));
				productDescriptionLabel.setMaximumSize(new Dimension(300, 75));
				productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				productImageLabel = new JLabel();
				Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
				productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
				productImageLabel.setPreferredSize(new Dimension(75, 75));

				amountTextLabel = new JLabel("Amount:");
				amountValueLabel = new JLabel("1");

				priceTextLabel = new JLabel("Price:");
				priceValueLabel = new JLabel("10");
			}

			private void createElements(Product product){
				panel = new JPanel();
				panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

				productNameLabel = new JLabel(product.name);

				productDescriptionLabel = new JLabel(product.description);
				productDescriptionLabel.setMinimumSize(new Dimension(100, 75));
				productDescriptionLabel.setPreferredSize(new Dimension(250, 75));
				productDescriptionLabel.setMaximumSize(new Dimension(300, 75));
				productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				productImageLabel = new JLabel();
				try {
					URL imageURL = new URL(product.imageUrl);
					ImageIcon icon = new ImageIcon(resizeImage(imageURL));
					productImageLabel.setIcon(icon);
				} catch (Exception e) {
					Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
					productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
					System.out.println("Проблема с картинкой");
				}
				productImageLabel.setPreferredSize(new Dimension(75, 75));

				amountTextLabel = new JLabel("Amount:");
				amountValueLabel = new JLabel("" + product.availableAmount);

				priceTextLabel = new JLabel("Price:");
				priceValueLabel = new JLabel("" + product.calculateTotalPrice());
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
						.addGroup(l.createParallelGroup(CENTER)
							.addGroup(l.createSequentialGroup()
								.addComponent(amountTextLabel)
								.addComponent(amountValueLabel)
							)
							.addGroup(l.createSequentialGroup()
								.addComponent(priceTextLabel)
								.addComponent(priceValueLabel)
							)
						)
						.addGap(10)
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
							.addGap(10)
							.addGroup(l.createParallelGroup()
								.addComponent(priceTextLabel)
								.addComponent(priceValueLabel)
							)
						)
					)
				);
	
			}

			public JPanel getPanel(){
				return panel;
			}
		}

		private class OrderInfoPanel{
			private JPanel panel = null;

			private JLabel clientNameTextLabel = null;
			private JLabel clientNameValueLabel = null;
			private JLabel phoneNumberTextLabel = null;
			private JLabel phoneNumberValueLabel = null;
			private JLabel addressTextLabel = null;
			private JLabel addressValueLabel = null;
			private JLabel deliveringDateTextLabel = null;
			private JLabel deliveringDateValueLabel = null;
			private JLabel totalPriceTextLabel = null;
			private JLabel totalPriceValueLabel = null;
			private JLabel orderStatusTextLabel = null;
			private JLabel orderStatusValueLabel = null;
			private JButton cancelOrderButton = null;

			private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
			
			public OrderInfoPanel(){
				createElements();
				compose();
			}

			public OrderInfoPanel(Order order){
				createElements(order);
				compose();
			}

			private void createElements(){
				panel = new JPanel();

				clientNameTextLabel = new JLabel("Name: ");
				clientNameTextLabel.setFont(anotherFont);
				// clientNameTextLabel.setHorizontalAlignment(JLabel.CENTER);

				clientNameValueLabel = new JLabel("Placeholder");
				// clientNameValueLabel.setHorizontalAlignment(JLabel.CENTER);

				phoneNumberTextLabel = new JLabel("Phone number: ");
				phoneNumberTextLabel.setFont(anotherFont);
				// phoneNumberTextLabel.setHorizontalAlignment(JLabel.CENTER);

				phoneNumberValueLabel = new JLabel("+01234567");
				// phoneNumberValueLabel.setHorizontalAlignment(JLabel.CENTER);

				addressTextLabel = new JLabel("Address: ");
				addressTextLabel.setFont(anotherFont);
				// addressTextLabel.setHorizontalAlignment(JLabel.CENTER);

				addressValueLabel = new JLabel("City N, Street M/B, Flat V");
				// addressValueLabel.setHorizontalAlignment(JLabel.CENTER);

				deliveringDateTextLabel = new JLabel("Delivery date: ");
				deliveringDateTextLabel.setFont(anotherFont);
				// deliveringDateTextLabel.setHorizontalAlignment(JLabel.CENTER);

				deliveringDateValueLabel = new JLabel("20.12.2021");
				// deliveringDateValueLabel.setHorizontalAlignment(JLabel.CENTER);

				totalPriceTextLabel = new JLabel("Total price: ");
				totalPriceTextLabel.setFont(anotherFont);
				// totalPriceTextLabel.setHorizontalAlignment(JLabel.CENTER);

				totalPriceValueLabel = new JLabel("100");
				// totalPriceValueLabel.setHorizontalAlignment(JLabel.CENTER);

				orderStatusTextLabel = new JLabel("Status: ");
				orderStatusTextLabel.setFont(anotherFont);

				orderStatusValueLabel = new JLabel("delivering");

				cancelOrderButton = new JButton("Cancel order");
			}

			private void createElements(Order order){
				panel = new JPanel();

				clientNameTextLabel = new JLabel("Name: ");
				clientNameTextLabel.setFont(anotherFont);
				// clientNameTextLabel.setHorizontalAlignment(JLabel.CENTER);

				clientNameValueLabel = new JLabel(order.deliveryName);
				// clientNameValueLabel.setHorizontalAlignment(JLabel.CENTER);

				phoneNumberTextLabel = new JLabel("Phone number: ");
				phoneNumberTextLabel.setFont(anotherFont);
				// phoneNumberTextLabel.setHorizontalAlignment(JLabel.CENTER);

				phoneNumberValueLabel = new JLabel(order.deliveryPhone);
				// phoneNumberValueLabel.setHorizontalAlignment(JLabel.CENTER);

				addressTextLabel = new JLabel("Address: ");
				addressTextLabel.setFont(anotherFont);
				// addressTextLabel.setHorizontalAlignment(JLabel.CENTER);

				addressValueLabel = new JLabel(order.deliveryAddress);
				// addressValueLabel.setHorizontalAlignment(JLabel.CENTER);

				deliveringDateTextLabel = new JLabel("Delivery date: ");
				deliveringDateTextLabel.setFont(anotherFont);
				// deliveringDateTextLabel.setHorizontalAlignment(JLabel.CENTER);

				deliveringDateValueLabel = new JLabel(order.deliveryDate);
				// deliveringDateValueLabel.setHorizontalAlignment(JLabel.CENTER);

				totalPriceTextLabel = new JLabel("Total price: ");
				totalPriceTextLabel.setFont(anotherFont);
				// totalPriceTextLabel.setHorizontalAlignment(JLabel.CENTER);

				totalPriceValueLabel = new JLabel("" + order.calculateTotalPrice());
				// totalPriceValueLabel.setHorizontalAlignment(JLabel.CENTER);

				orderStatusTextLabel = new JLabel("Status: ");
				orderStatusTextLabel.setFont(anotherFont);

				orderStatusValueLabel = new JLabel(Utils.getStatus(order));

				cancelOrderButton = new JButton("Cancel order");
				if (order.deliveryStatus == OrderStatus.CANCELED || order.deliveryStatus == OrderStatus.DELIVERED){
					cancelOrderButton.setEnabled(false);
				}
				cancelOrderButton.addActionListener(event -> {
					clientRepository.removeOrder(order);
					frame.dispose();
					new ClientOrdersScreenFrame();
					Toast.show("Order Canceled");
				});
			}

			private void compose(){
				JPanel panel1 = new JPanel();
				// panel.setLayout(new GridBagLayout());
				panel.add(panel1);
				GroupLayout l = new GroupLayout(panel1);
				panel1.setLayout(l);
				// oLayout.setAutoCreateGaps(true);
				// oLayout.setAutoCreateContainerGaps(true);
	
				l.setHorizontalGroup(l.createParallelGroup()
					.addGroup(l.createSequentialGroup()
						.addComponent(clientNameTextLabel)
						.addComponent(clientNameValueLabel)					
					)
					.addGroup(l.createSequentialGroup()
						.addComponent(phoneNumberTextLabel)
						.addComponent(phoneNumberValueLabel)
					)
					.addGroup(l.createSequentialGroup()
						.addComponent(addressTextLabel)
						.addComponent(addressValueLabel)
					)
					.addGroup(l.createSequentialGroup()
						.addComponent(deliveringDateTextLabel)
						.addComponent(deliveringDateValueLabel)
					)					
					.addGroup(l.createSequentialGroup()
						.addComponent(totalPriceTextLabel)
						.addComponent(totalPriceValueLabel)
					)
					.addGroup(l.createSequentialGroup()
						.addComponent(orderStatusTextLabel)
						.addComponent(orderStatusValueLabel)
					)
					.addGroup(l.createParallelGroup()
						.addGroup(l.createSequentialGroup()
							.addGap(50)
							.addComponent(cancelOrderButton)
						)
					)					
				);
	
				l.setVerticalGroup(l.createSequentialGroup()
					.addGap(5)
					.addGroup(l.createParallelGroup()
						.addComponent(clientNameTextLabel)
						.addComponent(clientNameValueLabel)
					)
					.addGap(15)
					.addGroup(l.createParallelGroup()
						.addComponent(phoneNumberTextLabel)
						.addComponent(phoneNumberValueLabel)
					)
					.addGap(15)
					.addGroup(l.createParallelGroup()
						.addComponent(addressTextLabel)
						.addComponent(addressValueLabel)
					)
					.addGap(15)
					.addGroup(l.createParallelGroup()
						.addComponent(deliveringDateTextLabel)
						.addComponent(deliveringDateValueLabel)
					)
					.addGap(15)
					.addGroup(l.createParallelGroup()
						.addComponent(totalPriceTextLabel)
						.addComponent(totalPriceValueLabel)
					)
					.addGap(15)
					.addGroup(l.createParallelGroup()
						.addComponent(orderStatusTextLabel)
						.addComponent(orderStatusValueLabel)
					)
					.addGap(15)
					.addComponent(cancelOrderButton)
				);
			}

			public JPanel getPanel(){
				return panel;
			}
		}

		private static Image resizeImage(URL imageUrl) throws IOException {
			BufferedImage originalImage = ImageIO.read(imageUrl);
			Image scaledImage = originalImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
			BufferedImage bufferedScaledImage = new BufferedImage(75, 75, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedScaledImage.createGraphics();
			g.drawImage(scaledImage, 0, 0, null);
			g.dispose();
			return bufferedScaledImage;
		}
	}
}
