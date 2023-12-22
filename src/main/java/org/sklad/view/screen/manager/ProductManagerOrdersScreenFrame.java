package org.sklad.view.screen.manager;

import org.sklad.model.ClientOrder;
import org.sklad.model.OrderStatus;
import org.sklad.model.Product;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;
import org.sklad.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerOrdersScreenFrame {

    private JFrame frame = new JFrame();
    private final int WIDTH = 800;
    private final int HEIGHT = 500;
    private JPanel productManagerToolBarPanel = null;
    private JPanel productManagerOrdersPanel = null;
    private ManagerRepo managerRepo;
    private ArrayList<ClientOrder> allClientOrders;

    public ProductManagerOrdersScreenFrame(){
        managerRepo = new ManagerRepo();
        allClientOrders = managerRepo.getOrdersBy(false);
        for (ClientOrder order: allClientOrders) {
            System.out.println(order);
        }
        createElements();
        compose();
    }

    private void createElements(){
        frame=new JFrame("Clients orders");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = (new ProductManagerAppToolBarPanel(frame).getPanel());

        productManagerOrdersPanel = new JPanel();
        productManagerOrdersPanel.setPreferredSize(new Dimension(750, 370));
        productManagerOrdersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//        for(int i = 0; i < 3; i++){
//            panel1.add(new ProductManagerOrderPanel().getPanel());
//            panel1.add(Box.createVerticalStrut(10));
//        }

        for (ClientOrder order: allClientOrders) {
            panel1.add(new ProductManagerOrderPanel(order).getPanel());
            panel1.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, productManagerOrdersPanel.getWidth(), productManagerOrdersPanel.getHeight());

        productManagerOrdersPanel.setLayout(new BorderLayout());
        productManagerOrdersPanel.add(scrollPane);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    }

    private void compose(){
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(CENTER, false)
                .addComponent(productManagerToolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(productManagerOrdersPanel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(productManagerToolBarPanel)
                .addComponent(productManagerOrdersPanel)
        );
    }

    private class ProductManagerOrderPanel{

        private JPanel panel = null;

        private JLabel orderIdLabel = null;

        private JPanel orderInfoPanel = null;

        private JPanel productsPanel = null;

        // private ArrayList<ProductInOrderPanel> arrayProductsPanels = null;

        private Font anotherFont = new Font("Verdana", Font.PLAIN, 18);

        // DEPRECATED
//        public ProductManagerOrderPanel(){
//            createElements();
//            compose();
//        }

        public ProductManagerOrderPanel(ClientOrder order){
            createElements(order);
            compose();
        }

        // DEPRECATED
//        private void createElements(){
//            panel = new JPanel();
//            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//            orderIdLabel = new JLabel("order id placeholder");
//            orderIdLabel.setFont(anotherFont);
//
//            orderInfoPanel = new JPanel();
//
//            productsPanel = new JPanel();
//            productsPanel.setPreferredSize(new Dimension(450, 250));
//            productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
//            productsPanel.setLayout(new BorderLayout());
//
//            JPanel panel1 = new JPanel();
//            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//            for(int i = 0; i < 5; i++){
//                panel1.add(new ProductInOrderPanel().getPanel());
//                panel1.add(Box.createVerticalStrut(5));
//            }
//
//            JScrollPane scrollPane = new JScrollPane(panel1);
//            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            scrollPane.setBounds(0, 0, productsPanel.getWidth(), productsPanel.getHeight());
//
//            productsPanel.add(scrollPane);
//            panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//            orderInfoPanel = (new OrderInfoPanel()).getPanel();
//            orderInfoPanel.setPreferredSize(new Dimension(250, 250));
//            orderInfoPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
//        }

        private void createElements(ClientOrder order){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            orderIdLabel = new JLabel("order id : " + order.getId());
            orderIdLabel.setFont(anotherFont);

            orderInfoPanel = new JPanel();

            productsPanel = new JPanel();
            productsPanel.setPreferredSize(new Dimension(450, 250));
            productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            productsPanel.setLayout(new BorderLayout());

            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            for (Product product: order.deliveryProducts) {
                panel1.add(new ProductInOrderPanel(product).getPanel());
                panel1.add(Box.createVerticalStrut(5));
            }

            JScrollPane scrollPane = new JScrollPane(panel1);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBounds(0, 0, productsPanel.getWidth(), productsPanel.getHeight());

            productsPanel.add(scrollPane);
            panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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

            private JButton formPackageOfOrderButton = null;

            private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

            // DEPRECATED
//            public OrderInfoPanel(){
//                createElements();
//                compose();
//            }
            public OrderInfoPanel(ClientOrder order){
                createElements(order);
                compose();
            }

            // DEPRECATED
//            private void createElements(){
//                panel = new JPanel();
//
//                clientNameTextLabel = new JLabel("Client name:");
//                clientNameTextLabel.setFont(anotherFont);
//
//                clientNameValueLabel = new JLabel("Placeholder");
//
//                phoneNumberTextLabel = new JLabel("Phone number:");
//                phoneNumberTextLabel.setFont(anotherFont);
//
//                phoneNumberValueLabel = new JLabel("+01234567");
//
//                addressTextLabel = new JLabel("Address:");
//                addressTextLabel.setFont(anotherFont);
//
//                addressValueLabel = new JLabel("City N, Street M/B, Flat V");
//
//                deliveringDateTextLabel = new JLabel("Delivery date:");
//                deliveringDateTextLabel.setFont(anotherFont);
//
//                deliveringDateValueLabel = new JLabel("20.12.2021");
//
//                totalPriceTextLabel = new JLabel("Total price:");
//                totalPriceTextLabel.setFont(anotherFont);
//
//                totalPriceValueLabel = new JLabel("100");
//
//                orderStatusTextLabel = new JLabel("Order status:");
//                orderStatusTextLabel.setFont(anotherFont);
//
//                orderStatusValueLabel = new JLabel("waiting payment");
//
//                formPackageOfOrderButton = new JButton("Form Package");
//                formPackageOfOrderButton.setEnabled(false);
//                formPackageOfOrderButton.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e){
//                        // add new package to list
//                        formPackageOfOrderButtonFunction();
//                    }
//                });
//            }

            private void createElements(ClientOrder order){
                panel = new JPanel();

                clientNameTextLabel = new JLabel("Client name:");
                clientNameTextLabel.setFont(anotherFont);

                clientNameValueLabel = new JLabel(order.deliveryName);

                phoneNumberTextLabel = new JLabel("Phone number:");
                phoneNumberTextLabel.setFont(anotherFont);

                phoneNumberValueLabel = new JLabel(order.deliveryPhone);

                addressTextLabel = new JLabel("Address:");
                addressTextLabel.setFont(anotherFont);

                addressValueLabel = new JLabel(order.deliveryPhone);

                deliveringDateTextLabel = new JLabel("Delivery date:");
                deliveringDateTextLabel.setFont(anotherFont);

                deliveringDateValueLabel = new JLabel(order.deliveryDate);

                totalPriceTextLabel = new JLabel("Total price:");
                totalPriceTextLabel.setFont(anotherFont);

                totalPriceValueLabel = new JLabel("" + order.calculateTotalPrice());

                orderStatusTextLabel = new JLabel("Order status:");
                orderStatusTextLabel.setFont(anotherFont);

                orderStatusValueLabel = new JLabel(Utils.getStatus(order));

                formPackageOfOrderButton = new JButton("Form Package");
                formPackageOfOrderButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        // add new package to list
                        formPackageOfOrderButtonFunction(order);
                    }
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
                                .addGap(5)
                                .addComponent(clientNameValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                                .addComponent(phoneNumberTextLabel)
                                .addGap(5)
                                .addComponent(phoneNumberValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                                .addComponent(addressTextLabel)
                                .addGap(5)
                                .addComponent(addressValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                                .addComponent(deliveringDateTextLabel)
                                .addGap(5)
                                .addComponent(deliveringDateValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                                .addComponent(totalPriceTextLabel)
                                .addGap(5)
                                .addComponent(totalPriceValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                                .addComponent(orderStatusTextLabel)
                                .addGap(5)
                                .addComponent(orderStatusValueLabel)
                        )
                        .addComponent(formPackageOfOrderButton)
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
                        .addGroup(l.createParallelGroup(CENTER)
                                .addComponent(orderStatusTextLabel)
                                .addComponent(orderStatusValueLabel)
                        )
                        .addGap(15)
                        .addComponent(formPackageOfOrderButton)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

            private void formPackageOfOrderButtonFunction(ClientOrder order){
                order.deliveryStatus = OrderStatus.READY_TO_DELIVER;
                Toast.show("Package ready bratishka!");
                frame.dispose();
                new ProductManagerOrdersScreenFrame();
            }
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

            // DEPRECATED
//            public ProductInOrderPanel(){
//                createElements();
//                compose();
//            }
            public ProductInOrderPanel(Product product){
                createElements(product);
                compose();
            }

            // DEPRECATED
//            private void createElements(){
//                panel = new JPanel();
//                panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
//
//                productNameLabel = new JLabel("name placeholder");
//
//                productDescriptionLabel = new JLabel("description placeholder");
//                productDescriptionLabel.setMinimumSize(new Dimension(100, 75));
//                productDescriptionLabel.setPreferredSize(new Dimension(250, 75));
//                productDescriptionLabel.setMaximumSize(new Dimension(300, 75));
//                productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//                productImageLabel = new JLabel();
//                Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
//                productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
//                productImageLabel.setPreferredSize(new Dimension(75, 75));
//
//                amountTextLabel = new JLabel("Amount:");
//                amountValueLabel = new JLabel("1");
//
//                priceTextLabel = new JLabel("Price:");
//                priceValueLabel = new JLabel("10");
//            }

            private void createElements(Product product){
                panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

                productNameLabel = new JLabel("product : " + product.name);

                productDescriptionLabel = new JLabel("" + product.description);
                productDescriptionLabel.setMinimumSize(new Dimension(100, 75));
                productDescriptionLabel.setPreferredSize(new Dimension(250, 75));
                productDescriptionLabel.setMaximumSize(new Dimension(300, 75));
                productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                //------
                productImageLabel = new JLabel();                                   // Image Url
                try {
                    URL imageURL = new URL(product.imageUrl);
                    ImageIcon icon = new ImageIcon(Utils.resizeImage(imageURL));
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
    }
}