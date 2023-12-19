package org.sklad.view.screen.manager;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManaderOrdersScreenFrame {

    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    private JPanel productManagerToolBarPanel = null;

    private JPanel productManagerOrdersPanel = null;

    public ProductManaderOrdersScreenFrame(){
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
        for(int i = 0; i < 3; i++){
            panel1.add(new ProductManagerOrderPanel().getPanel());
            panel1.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

        public ProductManagerOrderPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            orderIdLabel = new JLabel("order id placeholder");
            orderIdLabel.setFont(anotherFont);

            orderInfoPanel = new JPanel();

            productsPanel = new JPanel();
            productsPanel.setPreferredSize(new Dimension(450, 250));
            productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            productsPanel.setLayout(new BorderLayout());

            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            for(int i = 0; i < 20; i++){
                panel1.add(new ProductInOrderPanel().getPanel());
                panel1.add(Box.createVerticalStrut(5));
            }

            JScrollPane scrollPane = new JScrollPane(panel1);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(0, 0, productsPanel.getWidth(), productsPanel.getHeight());

            productsPanel.add(scrollPane);
            panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            orderInfoPanel = (new OrderInfoPanel()).getPanel();
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
            private JTextField orderStatusTextField = null;

            private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

            public OrderInfoPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();

                clientNameTextLabel = new JLabel("Client name: ");
                clientNameTextLabel.setFont(anotherFont);

                clientNameValueLabel = new JLabel("Placeholder");

                phoneNumberTextLabel = new JLabel("Phone number: ");
                phoneNumberTextLabel.setFont(anotherFont);

                phoneNumberValueLabel = new JLabel("+01234567");

                addressTextLabel = new JLabel("Address: ");
                addressTextLabel.setFont(anotherFont);

                addressValueLabel = new JLabel("City N, Street M/B, Flat V");

                deliveringDateTextLabel = new JLabel("Delivery date: ");
                deliveringDateTextLabel.setFont(anotherFont);

                deliveringDateValueLabel = new JLabel("20.12.2021");

                totalPriceTextLabel = new JLabel("Total price: ");
                totalPriceTextLabel.setFont(anotherFont);

                totalPriceValueLabel = new JLabel("100");

                orderStatusTextLabel = new JLabel("Status: ");
                orderStatusTextLabel.setFont(anotherFont);

                orderStatusTextField = new JTextField("delivering");
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
                                .addComponent(orderStatusTextField)
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
                        .addGroup(l.createParallelGroup(CENTER)
                                .addComponent(orderStatusTextLabel)
                                .addComponent(orderStatusTextField)
                        )
                );
            }

            public JPanel getPanel(){
                return panel;
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

            public ProductInOrderPanel(){
                createElements();
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