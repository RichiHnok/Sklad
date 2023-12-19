package org.sklad.ProductManager;

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

    public ProductManagerRecAndFormingScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Recieving and Forming Packages");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        mainPanel = new MainPanel().getPanel();
        // mainPanel.add(scrollPane);
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

                titleOfPanelLabel = new JLabel("Recieving packages from providers");
                titleOfPanelLabel.setFont(titleFont);

                updatePanelButton = new JButton("Update");
                updatePanelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        updatePanelButtonFunction();
                    }
                });

                JPanel panel1 = new JPanel();
                for(int i = 0; i < 3; i++){
                    panel1.add(new ProviderPackagePanel().getPanel());
                }
                GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                // gridLayout.setColumns(2);
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

            }

            private class ProviderPackagePanel{
                private JPanel panel = null;

                private JLabel providerNameLabel = null;
                private JLabel messageLabel = null;
                private JLabel storageOrderIdLabel = null;

                private JPanel productsPanel = null;

                private JButton recieveDeliveryButton = null;
                // private JButton showPackageContentButton = null;

                public ProviderPackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(300, 230));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
                
                    providerNameLabel = new JLabel("Chingiz");
                    providerNameLabel.setFont(anotherFont);
                    messageLabel = new JLabel(" delivered order# ");
                    
                    storageOrderIdLabel = new JLabel("z000");
                    storageOrderIdLabel.setFont(anotherFont);

                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                    for(int i = 0; i < 10; i++){
                        panel1.add(new ProductInPackage().getPanel());
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

                    recieveDeliveryButton = new JButton("Recieve delivery");
                    recieveDeliveryButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            recieveDeliveryButtonFunction();
                        }
                    });
                    // showPackageContentButton = new JButton("Show content");
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
                            // .addComponent(showPackageContentButton)
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
                            // .addComponent(showPackageContentButton)                      
                        )
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }

                private void recieveDeliveryButtonFunction(){

                }
            }
        }

        private class FormingClientsPackagesPanel{

            private JPanel panel = null;

            private JLabel titleOfPanelLabel = null;
            private JPanel formedPackagesPanel = null;

            private JPanel panel1 = null;
            private int amountOfFormedPackagesInFormingPackagesPanel = 0;
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
                for(int i = 0; i < amountOfFormedPackagesInFormingPackagesPanel; i++){
                    arrayOfFormedPackagesInFormingPackagePanel.add(new PackagePanel().getPanel());
                }

                panel1 = new JPanel();
                GridLayout gridLayout = new GridLayout(0,2,10,10);
                panel1.setLayout(gridLayout);
                for(JPanel formedPackage : arrayOfFormedPackagesInFormingPackagePanel){
                    panel1.add(formedPackage);
                }
                panel1.add(new AddPackagePanel().getPanel());

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
            public void fillPanelWithPackages(){
                panel1.removeAll();
                arrayOfFormedPackagesInFormingPackagePanel = new ArrayList<>();
                for(int i = 0; i < amountOfFormedPackagesInFormingPackagesPanel; i++){
                    arrayOfFormedPackagesInFormingPackagePanel.add(new PackagePanel().getPanel());
                }
                for(JPanel formedPackage : arrayOfFormedPackagesInFormingPackagePanel){
                    panel1.add(formedPackage);
                }
                panel1.add(new AddPackagePanel().getPanel());
                panel.updateUI();
            }

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

                public PackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(300, 230));
                    panel.setBorder(BorderFactory.createLineBorder(new Color(130,50,180), 2));
                
                    orderIdTextLabel = new JLabel("Order ID:");
                    orderIdTextLabel.setFont(anotherFont);

                    orderIdValueLabel = new JLabel("zzz0");

                    clientNameTextLabel = new JLabel("Client name:");
                    clientNameTextLabel.setFont(anotherFont);

                    clientNameValueLabel = new JLabel("Vano");

                    clientAddressTextLabel = new JLabel("Address:");
                    clientAddressTextLabel.setFont(anotherFont);

                    clientAddressValueLabel = new JLabel("Zybickaia 9");

                    deliveryDateTextLabel = new JLabel("Delivery date:");
                    deliveryDateTextLabel.setFont(anotherFont);

                    deliveryDateValueLabel = new JLabel("30.12.2023");

                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            
                    for(int i = 0; i < 8; i++){
                        panel1.add(new ProductInPackage().getPanel());
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

                    cancelFormingButton = new JButton("Cancel forming");
                    cancelFormingButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            cancelFormingButtonFunction();
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

                private void cancelFormingButtonFunction(){
                    if(amountOfFormedPackagesInFormingPackagesPanel > 0)
                        amountOfFormedPackagesInFormingPackagesPanel--;
                    fillPanelWithPackages();                    
                }
            }

            private class AddPackagePanel{
                private JPanel panel = null;

                private JLabel chooseOrderToPackLabel = null;
                private JComboBox<String> choosingOrderToPackBox = null;
                
                private JLabel orderIdTextLabel = null;
                private JLabel orderIdValueLabel = null;
                private JLabel clientNameTextLabel = null;
                private JLabel clientNameValueLabel = null;
                private JLabel clientAddressTextLabel = null;
                private JLabel clientAddressValueLabel = null;
                private JLabel deliveryDateTextLabel = null;
                private JLabel deliveryDateValueLabel = null;

                private JPanel productsInFormingPackagePanel = null;
                
                private JButton formNewPackageButton = null;

                public AddPackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(100, 100));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

                    chooseOrderToPackLabel = new JLabel("Order:");
                    chooseOrderToPackLabel.setFont(anotherFont);

                    String order1ID = "zzz0", order2ID = "yyy1";
                    String order1Client = "Vano", order2Client = "Billy";
                    String order1DelDate = "12.12.2012", order2DelDate = "01.01.2021";
                    String[] orders = {order1ID + " - " + order1Client + " - " + order1DelDate, order2ID + " - " + order2Client + " - " + order2DelDate};
                    choosingOrderToPackBox = new JComboBox<>();
                    for(int i = 0, n = orders.length; i < n; i++){
                        choosingOrderToPackBox.addItem(orders[i]);
                    }
                    choosingOrderToPackBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event){
                            String valueOfBox = (String) choosingOrderToPackBox.getSelectedItem();
                            switch(valueOfBox){
                                case("zzz0 - Vano - 12.12.2012"):
                                    orderIdValueLabel.setText(order1ID);
                                    clientNameValueLabel.setText(order1Client);
                                    clientAddressValueLabel.setText("Zybickaia 9");
                                    deliveryDateValueLabel.setText(order1DelDate);
                                    break;
                                case("yyy1 - Billy - 01.01.2021"):
                                    orderIdValueLabel.setText(order2ID);
                                    clientNameValueLabel.setText(order2Client);
                                    clientAddressValueLabel.setText("Grustnaya 1");
                                    deliveryDateValueLabel.setText(order2DelDate);
                                    break;
                            }
                        }
                    });

                    orderIdTextLabel = new JLabel("Order ID:");
                    orderIdTextLabel.setFont(anotherFont);

                    orderIdValueLabel = new JLabel("zzz0");

                    clientNameTextLabel = new JLabel("Client name:");
                    clientNameTextLabel.setFont(anotherFont);

                    clientNameValueLabel = new JLabel("Vano");

                    clientAddressTextLabel = new JLabel("Address:");
                    clientAddressTextLabel.setFont(anotherFont);

                    clientAddressValueLabel = new JLabel("Grustnaya 99");

                    deliveryDateTextLabel = new JLabel("Delivery date:");
                    deliveryDateTextLabel.setFont(anotherFont);

                    deliveryDateValueLabel = new JLabel("12.12.2012");

                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                    for(int i = 0; i < 10; i++){
                        panel1.add(new ProductInPackage().getPanel());
                        panel1.add(Box.createVerticalStrut(10));
                    }

                    JScrollPane scrollPane = new JScrollPane(panel1);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    productsInFormingPackagePanel = new JPanel();
                    productsInFormingPackagePanel.setPreferredSize(new Dimension(330, 100));
                    productsInFormingPackagePanel.setLayout(new BorderLayout());
                    productsInFormingPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                    productsInFormingPackagePanel.add(scrollPane);


                    formNewPackageButton = new JButton("Form package");
                    formNewPackageButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            formNewPackageButtonFunction();
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
                            .addComponent(chooseOrderToPackLabel)
                            .addGap(5)
                            .addComponent(choosingOrderToPackBox)
                        )
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
                        .addComponent(productsInFormingPackagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(l.createSequentialGroup()
                            .addGap(70)
                            .addComponent(formNewPackageButton)
                        )
                    );
    
                    l.setVerticalGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(chooseOrderToPackLabel)
                            .addComponent(choosingOrderToPackBox)
                        )
                        // .addGap(5)
                        .addGroup(l.createParallelGroup()
                            .addComponent(orderIdTextLabel)
                            .addComponent(orderIdValueLabel)
                        )
                        // .addGap(10)
                        .addGroup(l.createParallelGroup()
                            .addComponent(clientNameTextLabel)
                            .addComponent(clientNameValueLabel)
                        )
                        // .addGap(10)
                        .addGroup(l.createParallelGroup()
                            .addComponent(clientAddressTextLabel)
                            .addComponent(clientAddressValueLabel)
                        )
                        // .addGap(10)
                        .addGroup(l.createParallelGroup()
                            .addComponent(deliveryDateTextLabel)
                            .addComponent(deliveryDateValueLabel)
                        )
                        // .addGap(10)
                        .addComponent(productsInFormingPackagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(formNewPackageButton)
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }

                private void formNewPackageButtonFunction(){
                    amountOfFormedPackagesInFormingPackagesPanel++;
                    fillPanelWithPackages();
                }
            }
        }

        private class ProductInPackage {
            private JPanel panel = null;
    
            private JLabel productIdLabel = null;
            private JLabel productNameLabel = null;
            private JLabel productAmountLabel = null;
    
            public ProductInPackage(){
                createElements();
                compose();
            }
    
            private void createElements(){
                panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
    
                productIdLabel = new JLabel("product id");
    
                productNameLabel = new JLabel("product name");
    
                productAmountLabel = new JLabel("product amount");
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
