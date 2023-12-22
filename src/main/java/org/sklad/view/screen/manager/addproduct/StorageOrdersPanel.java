package org.sklad.view.screen.manager.addproduct;

import org.sklad.model.OrderStatus;
import org.sklad.model.Product;
import org.sklad.model.StorageOrder;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;
import org.sklad.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

class StorageOrdersPanel {

    private JPanel panel = null;

    private JLabel titleStorageOrdersLabel = null;
    private JPanel storageOrdersPanel = null;

    private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private Font titleFont = new Font("Verdana", Font.BOLD, 18);
    private ManagerRepo repository;
    private ArrayList<StorageOrder> storageOrders;
    private JFrame frame;

    public StorageOrdersPanel(JFrame frame) {
        this.frame = frame;
        repository = new ManagerRepo();
        storageOrders = repository.getAllStorageOrders();
        createElements();
        compose();
    }

    private void createElements() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 350));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        titleStorageOrdersLabel = new JLabel("STORAGE ORDERS");
        titleStorageOrdersLabel.setFont(titleFont);
        titleStorageOrdersLabel.setVerticalAlignment(JLabel.CENTER);
        // titleStorageOrdersLabel.setPreferredSize(new Dimension(titleStorageOrdersLabel.getWidth(), titleStorageOrdersLabel.getHeight() + 30));

        JPanel panel1 = new JPanel();
//        for (int i = 0; i < 2; i++) {
//            panel1.add(new StorageOrderPanel().getPanel());
//            panel1.add(Box.createVerticalStrut(15));
//        }

        for (StorageOrder order: storageOrders) {
            panel1.add(new StorageOrderPanel(order).getPanel());
            panel1.add(Box.createVerticalStrut(15));
        }

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        storageOrdersPanel = new JPanel();
        storageOrdersPanel.setLayout(new BorderLayout());
        storageOrdersPanel.setSize(new Dimension(750, 338));
        storageOrdersPanel.setPreferredSize(new Dimension(750, 348));
        storageOrdersPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        storageOrdersPanel.add(scrollPane);
    }

    private void compose() {
        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(titleStorageOrdersLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(storageOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(titleStorageOrdersLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(storageOrdersPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

    }

    public JPanel getPanel() {
        return panel;
    }

    private class StorageOrderPanel {

        private JPanel panel = null;

        private JLabel storageOrderIdLabel = null;

        private JPanel productsInOrderPanel = null;
        private JPanel orderInfoPanel = null;

//        public StorageOrderPanel() {
//            createElements();
//            compose();
//        }

        public StorageOrderPanel(StorageOrder order) {
            createElements(order);
            compose();
        }

//        private void createElements() {
//            panel = new JPanel();
//            panel.setPreferredSize(new Dimension(100, 220));
//            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//            String orderId = "z000";
//            storageOrderIdLabel = new JLabel("Order " + orderId);
//            storageOrderIdLabel.setFont(titleFont);
//
//            JPanel panel1 = new JPanel();
//            GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
//            panel1.setLayout(gridLayout);
//            for (int i = 0; i < 3; i++) {
//                panel1.add(new ProductPanel().getPanel());
//            }
//
//            JScrollPane scrollPane = new JScrollPane(panel1);
//            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//            productsInOrderPanel = new JPanel();
//            productsInOrderPanel.setLayout(new BorderLayout());
//            productsInOrderPanel.setPreferredSize(new Dimension(472, 195));
//            productsInOrderPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 200, 78), 2));
//            productsInOrderPanel.add(scrollPane);
//
//            orderInfoPanel = new OrderInfoPanel().getPanel();
//        }

        private void createElements(StorageOrder order) {
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(100, 220));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            storageOrderIdLabel = new JLabel("Order " + order.getId());
            storageOrderIdLabel.setFont(titleFont);

            JPanel panel1 = new JPanel();
            GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
            panel1.setLayout(gridLayout);

            for (Product product: order.getProducts()) {
                panel1.add(new ProductPanel(product).getPanel());
            }

            JScrollPane scrollPane = new JScrollPane(panel1);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            productsInOrderPanel = new JPanel();
            productsInOrderPanel.setLayout(new BorderLayout());
            productsInOrderPanel.setPreferredSize(new Dimension(472, 195));
            productsInOrderPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 200, 78), 2));
            productsInOrderPanel.add(scrollPane);

            orderInfoPanel = new OrderInfoPanel(order).getPanel();
        }

        private void compose() {
            panel.setLayout(new GridBagLayout());

            JPanel panel1 = new JPanel();
            GroupLayout l = new GroupLayout(panel1);
            panel1.setLayout(l);

            panel.add(panel1);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(storageOrderIdLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(l.createSequentialGroup()
                            .addComponent(productsInOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(storageOrderIdLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(l.createParallelGroup()
                            .addComponent(productsInOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
            );
        }

        public JPanel getPanel() {
            return panel;
        }

        private class ProductPanel {

            private JPanel panel = null;

            // private JLabel testLabel = new JLabel("storage order test label");

            private JLabel productIdTextLabel = null;
            private JLabel productIdValueLabel = null;
            private JLabel productNameTextLabel = null;
            private JLabel productNameValueLabel = null;
            private JLabel productsTotalPriceTextLabel = null;
            private JLabel productsTotalPriceValueLabel = null;
            private JLabel productAmountTextLabel = null;
            private JLabel productAmountValueLabel = null;

//            public ProductPanel() {
//                createElements();
//                compose();
//            }

            public ProductPanel(Product product) {
                createElements(product);
                compose();
            }

//            private void createElements() {
//                panel = new JPanel();
//                panel.setPreferredSize(new Dimension(210, 150));
//                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//                productIdTextLabel = new JLabel("Product ID:");
//                productIdTextLabel.setFont(anotherFont);
//
//                productIdValueLabel = new JLabel("z000");
//
//                productNameTextLabel = new JLabel("Product Name:");
//                productNameTextLabel.setFont(anotherFont);
//
//                productNameValueLabel = new JLabel("Hurma");
//
//                productsTotalPriceTextLabel = new JLabel("Price:");
//                productsTotalPriceTextLabel.setFont(anotherFont);
//
//                productsTotalPriceValueLabel = new JLabel("100");
//
//                productAmountTextLabel = new JLabel("Amount:");
//                productAmountTextLabel.setFont(anotherFont);
//
//                productAmountValueLabel = new JLabel("10");
//            }

            private void createElements(Product product) {
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(210, 150));
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                productIdTextLabel = new JLabel("Product ID:");
                productIdTextLabel.setFont(anotherFont);

                productIdValueLabel = new JLabel("" + product.id);

                productNameTextLabel = new JLabel("Product Name:");
                productNameTextLabel.setFont(anotherFont);

                productNameValueLabel = new JLabel(product.name);

                productsTotalPriceTextLabel = new JLabel("Price:");
                productsTotalPriceTextLabel.setFont(anotherFont);

                productsTotalPriceValueLabel = new JLabel("" + product.calculateTotalPrice());

                productAmountTextLabel = new JLabel("Amount:");
                productAmountTextLabel.setFont(anotherFont);

                productAmountValueLabel = new JLabel("" + product.availableAmount);
            }

            private void compose() {
                panel.setLayout(new GridBagLayout());

                JPanel panel1 = new JPanel();
                GroupLayout l = new GroupLayout(panel1);
                panel1.setLayout(l);

                panel.add(panel1);

                l.setHorizontalGroup(l.createParallelGroup(LEADING)
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(productIdTextLabel)
                                        .addGap(10)
                                        .addComponent(productIdValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(productNameTextLabel)
                                        .addGap(10)
                                        .addComponent(productNameValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(productAmountTextLabel)
                                        .addGap(10)
                                        .addComponent(productAmountValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(productsTotalPriceTextLabel)
                                        .addGap(10)
                                        .addComponent(productsTotalPriceValueLabel)
                                )
                );

                l.setVerticalGroup(l.createSequentialGroup()
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(productIdTextLabel)
                                        .addComponent(productIdValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(productNameTextLabel)
                                        .addComponent(productNameValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(productAmountTextLabel)
                                        .addComponent(productAmountValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(productsTotalPriceTextLabel)
                                        .addComponent(productsTotalPriceValueLabel)
                                )
                );
            }

            public JPanel getPanel() {
                return panel;
            }

        }

        private class OrderInfoPanel {

            private JPanel panel = null;

            // private JLabel testLabel = new JLabel("storage order test label");
            private JLabel providerIdTextLabel = null;
            private JLabel providerIdValueLabel = null;
            private JLabel providerNameTextLabel = null;
            private JLabel providerNameValueLabel = null;
            private JLabel providerPhoneTextLabel = null;
            private JLabel providerPhoneValueLabel = null;
            private JLabel deliveryDateTextLabel = null;
            private JLabel deliveryDateValueLabel = null;

            private JLabel orderStatusTextLabel = null;
            private JLabel orderStatusValueLabel = null;

            private JButton cancelOrderButton = null;

//            public OrderInfoPanel() {
//                createElements();
//                compose();
//            }

            public OrderInfoPanel(StorageOrder order) {
                createElements(order);
                compose();
            }

//            private void createElements() {
//                panel = new JPanel();
//                panel.setPreferredSize(new Dimension(250, 195));
//                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//
//                providerIdTextLabel = new JLabel("Provider ID:");
//                providerIdTextLabel.setFont(anotherFont);
//
//                providerIdValueLabel = new JLabel("z000");
//
//                providerNameTextLabel = new JLabel("Provider Name:");
//                providerNameTextLabel.setFont(anotherFont);
//
//                providerNameValueLabel = new JLabel("Chingiz");
//
//                providerPhoneTextLabel = new JLabel("Provider phone:");
//                providerPhoneTextLabel.setFont(anotherFont);
//
//                providerPhoneValueLabel = new JLabel("+3751234567");
//
//                deliveryDateTextLabel = new JLabel("Delivering date:");
//                deliveryDateTextLabel.setFont(anotherFont);
//
//                deliveryDateValueLabel = new JLabel("30.12.2023");
//
//                orderStatusTextLabel = new JLabel("Status:");
//                orderStatusTextLabel.setFont(anotherFont);
//
//                orderStatusValueLabel = new JLabel("delivering"); // "delivered" , "canceled"
//
//                cancelOrderButton = new JButton("Cancel order");
//
//                cancelOrderButton.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        cancelOrderButtonFunction();
//                    }
//                });
//            }

            private void createElements(StorageOrder order) {
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(250, 195));
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                providerIdTextLabel = new JLabel("Provider ID:");
                providerIdTextLabel.setFont(anotherFont);

                providerIdValueLabel = new JLabel("" + order.getProviderId());

                providerNameTextLabel = new JLabel("Provider Name:");
                providerNameTextLabel.setFont(anotherFont);

                providerNameValueLabel = new JLabel(order.getProviderName());

                providerPhoneTextLabel = new JLabel("Provider phone:");
                providerPhoneTextLabel.setFont(anotherFont);

                providerPhoneValueLabel = new JLabel(order.getProviderPhone());

                deliveryDateTextLabel = new JLabel("Delivering date:");
                deliveryDateTextLabel.setFont(anotherFont);

                deliveryDateValueLabel = new JLabel(order.getDeliveryDate());

                orderStatusTextLabel = new JLabel("Status:");
                orderStatusTextLabel.setFont(anotherFont);

                orderStatusValueLabel = new JLabel(Utils.getStatus(order.getStatus())); // "delivered" , "canceled"

                cancelOrderButton = new JButton("Cancel order");
                if (order.getStatus() != OrderStatus.BEING_DELIVERED){
                    cancelOrderButton.setEnabled(false);
                }
                cancelOrderButton.addActionListener(e -> cancelOrderButtonFunction(order));
            }

            private void compose() {
                panel.setLayout(new GridBagLayout());

                JPanel panel1 = new JPanel();
                GroupLayout l = new GroupLayout(panel1);
                panel1.setLayout(l);

                panel.add(panel1);

                l.setHorizontalGroup(l.createParallelGroup(LEADING)
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(providerIdTextLabel)
                                        .addGap(5)
                                        .addComponent(providerIdValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(providerNameTextLabel)
                                        .addGap(5)
                                        .addComponent(providerNameValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(providerPhoneTextLabel)
                                        .addGap(5)
                                        .addComponent(providerPhoneValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(deliveryDateTextLabel)
                                        .addGap(5)
                                        .addComponent(deliveryDateValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addComponent(orderStatusTextLabel)
                                        .addGap(5)
                                        .addComponent(orderStatusValueLabel)
                                )
                                .addGroup(l.createSequentialGroup()
                                        .addGap(45)
                                        .addComponent(cancelOrderButton)
                                )
                        // .addComponent(testLabel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                                .addGroup(l.createParallelGroup()
                                        .addComponent(providerIdTextLabel)
                                        .addComponent(providerIdValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(providerNameTextLabel)
                                        .addComponent(providerNameValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(providerPhoneTextLabel)
                                        .addComponent(providerPhoneValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(deliveryDateTextLabel)
                                        .addComponent(deliveryDateValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup(CENTER)
                                        .addComponent(orderStatusTextLabel)
                                        .addComponent(orderStatusValueLabel)
                                )
                                .addGap(10)
                                .addGroup(l.createParallelGroup()
                                        .addComponent(cancelOrderButton)
                                )
                        // .addComponent(testLabel)
                );

            }

            private void cancelOrderButtonFunction(StorageOrder order) {
                order.setStatus(OrderStatus.CANCELED);
                Toast.show("Order canceled");
                frame.dispose();
                new ProductManagerStorageOrdersScreenFrame();
            }

            public JPanel getPanel() {
                return panel;
            }
        }
    }
}