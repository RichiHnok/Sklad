package org.sklad.view.screen.manager;

import org.sklad.model.ClientOrder;
import org.sklad.model.OrderStatus;
import org.sklad.model.Product;
import org.sklad.model.StorageOrder;
import org.sklad.repository.ManagerRepo;
import org.sklad.repository.ProductRepo;
import org.sklad.util.Toast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerRecAndFormingScreenFrame {

    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 700;

    private JPanel productManagerToolBarPanel = null;
    private JPanel mainPanel = null;

    private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private Font titleFont = new Font("Verdana", Font.BOLD, 16);
    private ManagerRepo managerRepo;
    private ProductRepo productRepo;
    private ArrayList<ClientOrder> formedOrders;
    private ArrayList<StorageOrder> readyOrders;

    public ProductManagerRecAndFormingScreenFrame(){
        managerRepo = new ManagerRepo();
        productRepo = new ProductRepo();
        formedOrders = managerRepo.getOrdersBy(true);
        readyOrders = managerRepo.getReadyOrders();
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Receiving and Forming Packages");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        mainPanel = new MainPanel().getPanel();
    }

    private void compose(){
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);
        l.setAutoCreateGaps(true);
        l.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER, false)
                .addComponent(productManagerToolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private class MainPanel{

        private JPanel panel = null;

        private JPanel recievingProvidersOrdersPanel = null;
        private JPanel formingClientsPackagesPanel = null;

        public MainPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(750, 600));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            recievingProvidersOrdersPanel = new RecievingProvidersOrdersPanel().getPanel();

            formingClientsPackagesPanel = new FormingClientsPackagesPanel().getPanel();
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setAutoCreateGaps(true);
            l.setAutoCreateContainerGaps(true);

            l.setHorizontalGroup(l.createParallelGroup()
                    .addComponent(recievingProvidersOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(formingClientsPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(recievingProvidersOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(formingClientsPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        // TODO------------------------------------------------------------------------//
        private class RecievingProvidersOrdersPanel{

            private JPanel panel = null;

            private JLabel titleOfPanelLabel = null;
            private JButton updatePanelButton = null;
            private JPanel recievedPackagesPanel = null;

            public RecievingProvidersOrdersPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(732, 285));
                panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));

                titleOfPanelLabel = new JLabel("Receiving packages from providers");
                titleOfPanelLabel.setFont(titleFont);

                updatePanelButton = new JButton("Update");
                updatePanelButton.addActionListener(e -> updatePanelButtonFunction());

                JPanel panel1 = new JPanel();

                for (StorageOrder order: readyOrders){
                    panel1.add(new ProviderPackagePanel(order).getPanel());
                }

                GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                panel1.setLayout(gridLayout);

                JScrollPane scrollPane = new JScrollPane(panel1);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                recievedPackagesPanel = new JPanel();
                recievedPackagesPanel.setLayout(new BorderLayout());
                recievedPackagesPanel.setPreferredSize(new Dimension(750, 200));
                recievedPackagesPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                recievedPackagesPanel.add(scrollPane);
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createParallelGroup(TRAILING)
                        .addGroup(l.createSequentialGroup()
                                .addComponent(titleOfPanelLabel)
                                .addGap(135)
                                .addComponent(updatePanelButton)
                        )
                        .addComponent(recievedPackagesPanel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(CENTER)
                                .addComponent(titleOfPanelLabel)
                                .addComponent(updatePanelButton)
                        )
                        .addComponent(recievedPackagesPanel)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

            private void updatePanelButtonFunction(){
                managerRepo.updateOrderStatuses();
                frame.dispose();
                new ProductManagerRecAndFormingScreenFrame();
            }

            private class ProviderPackagePanel{
                private JPanel panel = null;

                private JLabel providerNameLabel = null;
                private JLabel messageLabel = null;
                private JLabel storageOrderIdLabel = null;

                private JPanel productsPanel = null;

                private JButton recieveDeliveryButton = null;

//                public ProviderPackagePanel(){
//                    createElements();
//                    compose();
//                }

                public ProviderPackagePanel(StorageOrder order){
                    createElements(order);
                    compose();
                }

//                private void createElements(){
//                    panel = new JPanel();
//                    panel.setPreferredSize(new Dimension(300, 230));
//                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
//
//                    providerNameLabel = new JLabel("Chingiz");
//                    providerNameLabel.setFont(anotherFont);
//                    messageLabel = new JLabel(" delivered order# ");
//
//                    storageOrderIdLabel = new JLabel("z000");
//                    storageOrderIdLabel.setFont(anotherFont);
//
//                    JPanel panel1 = new JPanel();
//                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//
////                    for(int i = 0; i < 4; i++){
////                        panel1.add(new ProductInPackage().getPanel());
////                        panel1.add(Box.createVerticalStrut(10));
////                    }
//
//                    JScrollPane scrollPane = new JScrollPane(panel1);
//                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//                    productsPanel = new JPanel();
//                    productsPanel.setPreferredSize(new Dimension(330, 150));
//                    productsPanel.setLayout(new BorderLayout());
//                    productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//                    productsPanel.add(scrollPane);
//
//                    recieveDeliveryButton = new JButton("Receive delivery");
//                    recieveDeliveryButton.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e){
//                            receiveDeliveryButtonFunction();
//                        }
//                    });
//                }

                private void createElements(StorageOrder order){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(300, 230));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

                    providerNameLabel = new JLabel(order.getProviderName());
                    providerNameLabel.setFont(anotherFont);
                    messageLabel = new JLabel(" delivered order# ");

                    storageOrderIdLabel = new JLabel("" + order.getId());
                    storageOrderIdLabel.setFont(anotherFont);

                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//                    for(int i = 0; i < 4; i++){
//                        panel1.add(new ProductInPackage().getPanel());
//                        panel1.add(Box.createVerticalStrut(10));
//                    }

                    for (Product product: order.getProducts()) {
                        panel1.add(new ProductInPackage(product).getPanel());
                        panel1.add(Box.createVerticalStrut(10));
                    }

                    JScrollPane scrollPane = new JScrollPane(panel1);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    productsPanel = new JPanel();
                    productsPanel.setPreferredSize(new Dimension(330, 150));
                    productsPanel.setLayout(new BorderLayout());
                    productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                    productsPanel.add(scrollPane);

                    recieveDeliveryButton = new JButton("Receive delivery");
                    recieveDeliveryButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            receiveDeliveryButtonFunction(order);
                        }
                    });
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup(CENTER, false)
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(providerNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(messageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(storageOrderIdLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addComponent(productsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(recieveDeliveryButton)
                            )
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup()
                                    .addComponent(providerNameLabel)
                                    .addComponent(messageLabel)
                                    .addComponent(storageOrderIdLabel)
                            )
                            .addComponent(productsPanel)
                            .addGroup(l.createParallelGroup()
                                    .addComponent(recieveDeliveryButton)
                            )
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }

                private void receiveDeliveryButtonFunction(StorageOrder order){
                    for (Product product: order.getProducts()) {
                        productRepo.addProduct(product);
                    }
                    order.setStatus(OrderStatus.DELIVERED);
                    Toast.show("Products Added to Warehouse");
                    frame.dispose();
                    new ProductManagerRecAndFormingScreenFrame();
                }
            }
        }

        private class FormingClientsPackagesPanel{

            private JPanel panel = null;

            private JLabel titleOfPanelLabel = null;
            private JPanel formedPackagesPanel = null;

            private JPanel panel1 = null;
            private int amountOfFormedPackagesInFormingPackagesPanel = 3;
            private ArrayList<JPanel> arrayOfFormedPackagesInFormingPackagePanel = null;

            public FormingClientsPackagesPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(732, 285));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

                titleOfPanelLabel = new JLabel("Forming packages for clients");
                titleOfPanelLabel.setFont(titleFont);

                arrayOfFormedPackagesInFormingPackagePanel = new ArrayList<>();
//                for(int i = 0; i < amountOfFormedPackagesInFormingPackagesPanel; i++){
//                    arrayOfFormedPackagesInFormingPackagePanel.add(new PackagePanel().getPanel());
//                }
                for (ClientOrder order: formedOrders) {
                    arrayOfFormedPackagesInFormingPackagePanel.add(new PackagePanel(order).getPanel());
                }


                panel1 = new JPanel();
                GridLayout gridLayout = new GridLayout(0,2,10,10);
                panel1.setLayout(gridLayout);
                for(JPanel formedPackage : arrayOfFormedPackagesInFormingPackagePanel){
                    panel1.add(formedPackage);
                }

                JScrollPane scrollPane = new JScrollPane(panel1);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                formedPackagesPanel = new JPanel();
                formedPackagesPanel.setLayout(new BorderLayout());
                formedPackagesPanel.setPreferredSize(new Dimension(730, 200));
                formedPackagesPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
                formedPackagesPanel.add(scrollPane);
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createParallelGroup(CENTER)
                        .addComponent(titleOfPanelLabel)
                        .addComponent(formedPackagesPanel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                        .addComponent(titleOfPanelLabel)
                        .addComponent(formedPackagesPanel)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

//            public void fillPanelWithPackages(){
//                panel1.removeAll();
//                arrayOfFormedPackagesInFormingPackagePanel = new ArrayList<>();
//                for(int i = 0; i < amountOfFormedPackagesInFormingPackagesPanel; i++){
//                    arrayOfFormedPackagesInFormingPackagePanel.add(new PackagePanel().getPanel());
//                }
//                for(JPanel formedPackage : arrayOfFormedPackagesInFormingPackagePanel){
//                    panel1.add(formedPackage);
//                }
//                panel.updateUI();
//            }

            private class PackagePanel{
                private JPanel panel = null;

                private JLabel orderIdTextLabel = null;
                private JLabel orderIdValueLabel = null;
                private JLabel clientNameTextLabel = null;
                private JLabel clientNameValueLabel = null;
                private JLabel clientAddressTextLabel = null;
                private JLabel clientAddressValueLabel = null;
                private JLabel deliveryDateTextLabel = null;
                private JLabel deliveryDateValueLabel = null;

                private JPanel productsInPackagePanel = null;

                private JButton cancelFormingButton = null;

                // DEPRECATED
//                public PackagePanel(){
//                    createElements();
//                    compose();
//                }

                public PackagePanel(ClientOrder order){
                    createElements(order);
                    compose();
                }

                // DEPRECATED
//                private void createElements(){
//                    panel = new JPanel();
//                    panel.setPreferredSize(new Dimension(300, 230));
//                    panel.setBorder(BorderFactory.createLineBorder(new Color(130,50,180), 2));
//
//                    orderIdTextLabel = new JLabel("Order ID:");
//                    orderIdTextLabel.setFont(anotherFont);
//
//                    orderIdValueLabel = new JLabel("zzz0");
//
//                    clientNameTextLabel = new JLabel("Client name:");
//                    clientNameTextLabel.setFont(anotherFont);
//
//                    clientNameValueLabel = new JLabel("Vano");
//
//                    clientAddressTextLabel = new JLabel("Address:");
//                    clientAddressTextLabel.setFont(anotherFont);
//
//                    clientAddressValueLabel = new JLabel("Zybickaia 9");
//
//                    deliveryDateTextLabel = new JLabel("Delivery date:");
//                    deliveryDateTextLabel.setFont(anotherFont);
//
//                    deliveryDateValueLabel = new JLabel("30.12.2023");
//
//                    JPanel panel1 = new JPanel();
//                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//
//                    for(int i = 0; i < 3; i++){
//                        panel1.add(new ProductInPackage().getPanel());
//                        panel1.add(Box.createVerticalStrut(10));
//                    }
//
//                    JScrollPane scrollPane = new JScrollPane(panel1);
//                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//
//                    productsInPackagePanel = new JPanel();
//                    productsInPackagePanel.setPreferredSize(new Dimension(330, 80));
//                    productsInPackagePanel.setLayout(new BorderLayout());
//                    productsInPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//                    productsInPackagePanel.add(scrollPane);
//
//                    cancelFormingButton = new JButton("Cancel forming");
//                    cancelFormingButton.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e){
//                            cancelFormingButtonFunction();
//                        }
//                    });
//                }

                private void createElements(ClientOrder order){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(300, 230));
                    panel.setBorder(BorderFactory.createLineBorder(new Color(130,50,180), 2));

                    orderIdTextLabel = new JLabel("Order ID:");
                    orderIdTextLabel.setFont(anotherFont);

                    orderIdValueLabel = new JLabel("" + order.getId());

                    clientNameTextLabel = new JLabel("Client name:");
                    clientNameTextLabel.setFont(anotherFont);

                    clientNameValueLabel = new JLabel(order.deliveryName);

                    clientAddressTextLabel = new JLabel("Address:");
                    clientAddressTextLabel.setFont(anotherFont);

                    clientAddressValueLabel = new JLabel(order.deliveryAddress);

                    deliveryDateTextLabel = new JLabel("Delivery date:");
                    deliveryDateTextLabel.setFont(anotherFont);

                    deliveryDateValueLabel = new JLabel(order.deliveryDate);

                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//                    for(int i = 0; i < 3; i++){
//                        panel1.add(new ProductInPackage().getPanel());
//                        panel1.add(Box.createVerticalStrut(10));
//                    }
                    for (Product product: order.deliveryProducts) {
                        panel1.add(new ProductInPackage(product).getPanel());
                        panel1.add(Box.createVerticalStrut(10));
                    }

                    JScrollPane scrollPane = new JScrollPane(panel1);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    productsInPackagePanel = new JPanel();
                    productsInPackagePanel.setPreferredSize(new Dimension(330, 80));
                    productsInPackagePanel.setLayout(new BorderLayout());
                    productsInPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                    productsInPackagePanel.add(scrollPane);

                    cancelFormingButton = new JButton("Revert forming");
                    cancelFormingButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            cancelFormingButtonFunction(order);
                        }
                    });
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup()
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(orderIdTextLabel)
                                    .addGap(5)
                                    .addComponent(orderIdValueLabel)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(clientNameTextLabel)
                                    .addGap(5)
                                    .addComponent(clientNameValueLabel)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(clientAddressTextLabel)
                                    .addGap(5)
                                    .addComponent(clientAddressValueLabel)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(deliveryDateTextLabel)
                                    .addGap(5)
                                    .addComponent(deliveryDateValueLabel)
                            )
                            .addComponent(productsInPackagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelFormingButton)
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup()
                                    .addComponent(orderIdTextLabel)
                                    .addComponent(orderIdValueLabel)
                            )
                            .addGap(10)
                            .addGroup(l.createParallelGroup()
                                    .addComponent(clientNameTextLabel)
                                    .addComponent(clientNameValueLabel)
                            )
                            .addGap(10)
                            .addGroup(l.createParallelGroup()
                                    .addComponent(clientAddressTextLabel)
                                    .addComponent(clientAddressValueLabel)
                            )
                            .addGap(10)
                            .addGroup(l.createParallelGroup()
                                    .addComponent(deliveryDateTextLabel)
                                    .addComponent(deliveryDateValueLabel)
                            )
                            .addGap(10)
                            .addComponent(productsInPackagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelFormingButton)
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }

                private void cancelFormingButtonFunction(ClientOrder order){
                    if(amountOfFormedPackagesInFormingPackagesPanel > 0)
                        amountOfFormedPackagesInFormingPackagesPanel--;
//                    fillPanelWithPackages();
                    order.deliveryStatus = OrderStatus.IN_PROCESS;
                    frame.dispose();
                    new ProductManagerRecAndFormingScreenFrame();
                    Toast.show("Forming Canceled");
                }
            }
        }

        private class ProductInPackage {
            private JPanel panel = null;

            private JLabel productIdLabel = null;
            private JLabel productNameLabel = null;
            private JLabel productAmountLabel = null;

            // DEPRECATED
//            public ProductInPackage(){
//                createElements();
//                compose();
//            }
            public ProductInPackage(Product product){
                createElements(product);
                compose();
            }

            // DEPRECATED
//            private void createElements(){
//                panel = new JPanel();
//                panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
//
//                productIdLabel = new JLabel("product id");
//
//                productNameLabel = new JLabel("product name");
//
//                productAmountLabel = new JLabel("product amount");
//            }

            private void createElements(Product product){
                panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

                productIdLabel = new JLabel("" + product.id);

                productNameLabel = new JLabel(product.name);

                productAmountLabel = new JLabel("" + product.availableAmount);
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createSequentialGroup()
                        .addComponent(productIdLabel)
                        .addGap(15)
                        .addComponent(productNameLabel)
                        .addGap(15)
                        .addComponent(productAmountLabel)
                );

                l.setVerticalGroup(l.createParallelGroup()
                        .addComponent(productIdLabel)
                        .addGap(15)
                        .addComponent(productNameLabel)
                        .addGap(15)
                        .addComponent(productAmountLabel)
                );
            }

            public JPanel getPanel(){
                return panel;
            }
        }
    }
}