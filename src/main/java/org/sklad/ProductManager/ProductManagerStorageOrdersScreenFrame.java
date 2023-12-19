package org.sklad.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerStorageOrdersScreenFrame {
    
    private JFrame frame = new JFrame();

	private final int WIDTH = 800;
	private final int HEIGHT = 700;
    
    private JPanel productManagerToolBarPanel = null;

    private JPanel orderingPanel = null;

    private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private Font titleFont = new Font("Verdana", Font.BOLD, 18);

    public ProductManagerStorageOrdersScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Storage ordering");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        orderingPanel = new OrderingPanel().getPanel();
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
            .addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
		);

		l.setVerticalGroup(l.createSequentialGroup()
			.addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        );
    }

    private class OrderingPanel{

        private JPanel panel = null;

        private JPanel storageOrdersPanel = null;
        private JPanel makeStorageOrderPanel = null;

        public OrderingPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(750, 400));
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            panel.setBackground(new Color(70, 70, 70));

            storageOrdersPanel = new StorageOrdersPanel().getPanel();

            makeStorageOrderPanel = new MakeStorageOrderPanel().getPanel();
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(storageOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(makeStorageOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

            l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(storageOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(makeStorageOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

        }

        public JPanel getPanel(){
            return panel;
        }

        private class StorageOrdersPanel{

            private JPanel panel = null;

            private JLabel titleStorageOrdersLabel = null;
            private JPanel storageOrdersPanel = null;
            // private JLabel testLabel = new JLabel("Storage orders test label");

            public StorageOrdersPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(750, 350));
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                titleStorageOrdersLabel = new JLabel("STORAGE ORDERS");
                titleStorageOrdersLabel.setFont(titleFont);
                titleStorageOrdersLabel.setVerticalAlignment(JLabel.CENTER);
                // titleStorageOrdersLabel.setPreferredSize(new Dimension(titleStorageOrdersLabel.getWidth(), titleStorageOrdersLabel.getHeight() + 30));

                JPanel panel1 = new JPanel();
                for(int i = 0; i < 2; i++){
                    panel1.add(new StorageOrderPanel().getPanel());
                    panel1.add(Box.createVerticalStrut(15));
                }
                // GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                // panel1.setLayout(gridLayout);
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

            private void compose(){
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

            public JPanel getPanel(){
                return panel;
            }

            private class StorageOrderPanel{

                private JPanel panel = null;

                private JLabel storageOrderIdLabel = null;

                private JPanel productsInOrderPanel = null;
                private JPanel orderInfoPanel = null;

                public StorageOrderPanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(100, 220));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                    String orderId = "z000";
                    storageOrderIdLabel = new JLabel("Order " + orderId);
                    storageOrderIdLabel.setFont(titleFont);

                    JPanel panel1 = new JPanel();
                    GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                    panel1.setLayout(gridLayout);
                    for(int i = 0; i < 3; i++){
                        panel1.add(new ProductPanel().getPanel());
                    }

                    JScrollPane scrollPane = new JScrollPane(panel1);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    productsInOrderPanel = new JPanel();
                    productsInOrderPanel.setLayout(new BorderLayout());
                    productsInOrderPanel.setPreferredSize(new Dimension(472, 195));
                    productsInOrderPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 200, 78), 2));
                    productsInOrderPanel.add(scrollPane);
                    
                    orderInfoPanel = new OrderInfoPanel().getPanel();
                }

                private void compose(){
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

                public JPanel getPanel(){
                    return panel;
                }

                private class ProductPanel{

                    private JPanel panel = null;

                    // private JLabel testLabel = new JLabel("storage order test label");

                    private JLabel productIdTextLabel = null;
                    private JLabel productIdValueLabel = null;
                    private JLabel productNameTextLabel =  null;
                    private JLabel productNameValueLabel = null;
                    private JLabel productsTotalPriceTextLabel = null;
                    private JLabel productsTotalPriceValueLabel = null;
                    private JLabel productAmountTextLabel = null;
                    private JLabel productAmountValueLabel = null;

                    public ProductPanel(){
                        createElements();
                        compose();
                    }

                    private void createElements(){
                        panel = new JPanel();
                        panel.setPreferredSize(new Dimension(210, 150));
                        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                        productIdTextLabel = new JLabel("Product ID:");
                        productIdTextLabel.setFont(anotherFont);

                        productIdValueLabel = new JLabel("z000");

                        productNameTextLabel = new JLabel("Product Name:");
                        productNameTextLabel.setFont(anotherFont);

                        productNameValueLabel = new JLabel("Hurma");

                        productsTotalPriceTextLabel = new JLabel("Price:");
                        productsTotalPriceTextLabel.setFont(anotherFont);

                        productsTotalPriceValueLabel = new JLabel("100");

                        productAmountTextLabel = new JLabel("Amount:");
                        productAmountTextLabel.setFont(anotherFont);

                        productAmountValueLabel = new JLabel("10");
                    }

                    private void compose(){
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
                            // .addComponent(testLabel)
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
                            // .addComponent(testLabel)
                        );
                    }

                    public JPanel getPanel(){
                        return panel;
                    }                

                }

                private class OrderInfoPanel{

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

                    public OrderInfoPanel(){
                        createElements();
                        compose();
                    }

                    private void createElements(){
                        panel = new JPanel();
                        panel.setPreferredSize(new Dimension(250, 195));
                        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                        providerIdTextLabel = new JLabel("Provider ID:");
                        providerIdTextLabel.setFont(anotherFont);

                        providerIdValueLabel = new JLabel("z000");

                        providerNameTextLabel = new JLabel("Provider Name:");
                        providerNameTextLabel.setFont(anotherFont);

                        providerNameValueLabel = new JLabel("Chingiz");

                        providerPhoneTextLabel = new JLabel("Provider phone:");
                        providerPhoneTextLabel.setFont(anotherFont);

                        providerPhoneValueLabel = new JLabel("+3751234567");

                        deliveryDateTextLabel = new JLabel("Delivering date:");
                        deliveryDateTextLabel.setFont(anotherFont);

                        deliveryDateValueLabel = new JLabel("30.12.2023");

                        orderStatusTextLabel = new JLabel("Status:");
                        orderStatusTextLabel.setFont(anotherFont);

                        orderStatusValueLabel = new JLabel("delivering"); // "delivered" , "canceled"

                        cancelOrderButton = new JButton("Cancel order");

                        cancelOrderButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                cancelOrderButtonFunction();
                            }
                        });
                    }

                    private void compose(){
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

                    public JPanel getPanel(){
                        return panel;
                    }                
                    
                    private void cancelOrderButtonFunction(){

                    }
                }
            }            
        }

        private class MakeStorageOrderPanel{
            private JPanel panel = null;

            private JLabel testLabel = new JLabel("making order test label");

            private JLabel formingNewOrderLabel = null;
            private JPanel productsInNewOrderPanel = null;
            private JPanel orderInfoPanel = null;

            private JPanel panel1 = null;

            private int amountOfProductsInFormingOrderPanel = 0;
            private ArrayList<JPanel> arrayOfProductsInFormingOrderPanel = null;

            public MakeStorageOrderPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(750, 230));
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                formingNewOrderLabel = new JLabel("FORMING NEW ORDER");
                formingNewOrderLabel.setFont(titleFont);

                arrayOfProductsInFormingOrderPanel = new ArrayList<>();
                for(int i = 0; i < amountOfProductsInFormingOrderPanel; i++){
                    arrayOfProductsInFormingOrderPanel.add(new ProductInFormingOrderPanel().getPanel());
                }

                panel1 = new JPanel();
                GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                panel1.setLayout(gridLayout);
                for(JPanel product : arrayOfProductsInFormingOrderPanel){
                    panel1.add(product);
                }
                panel1.add(new AddProductToNewOrderPanel().getPanel());

                JScrollPane scrollPane = new JScrollPane(panel1);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                productsInNewOrderPanel = new JPanel();
                productsInNewOrderPanel.setLayout(new BorderLayout());
                productsInNewOrderPanel.setPreferredSize(new Dimension(472, 195));
                productsInNewOrderPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 70, 255), 2));
                productsInNewOrderPanel.add(scrollPane);

                orderInfoPanel = new NewOrderInfoPanel().getPanel();
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(formingNewOrderLabel)
                    .addGroup(l.createSequentialGroup()
                        .addComponent(productsInNewOrderPanel)
                        .addComponent(orderInfoPanel)
                    )
                    // .addComponent(testLabel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(formingNewOrderLabel)
                    .addGroup(l.createParallelGroup()
                        .addComponent(productsInNewOrderPanel)
                        .addComponent(orderInfoPanel)
                    )
                    // .addComponent(testLabel)
                );

            }

            public JPanel getPanel(){
                return panel;
            }
            public void fillOrderWithProducts(){
                panel1.removeAll();
                arrayOfProductsInFormingOrderPanel = new ArrayList<>();
                for(int i = 0; i < amountOfProductsInFormingOrderPanel; i++){
                    arrayOfProductsInFormingOrderPanel.add(new ProductInFormingOrderPanel().getPanel());
                }
                for(JPanel product : arrayOfProductsInFormingOrderPanel){
                    panel1.add(product);
                }
                panel1.add(new AddProductToNewOrderPanel().getPanel());                
                panel1.updateUI();
            }
            // public int getAmountOfproducts(){
            //     return amountOfProductsInFormingOrderPanel;
            // }
            // public void setAmountOfProducts(int value){
            //     amountOfProductsInFormingOrderPanel = value;
            // }

            private class ProductInFormingOrderPanel{
                private JPanel panel = null;

                // private JLabel testLabel = new JLabel("storage order test label");

                private JLabel productIdTextLabel = null;
                private JLabel productIdValueLabel = null;
                private JLabel productNameTextLabel =  null;
                private JLabel productNameValueLabel = null;
                private JLabel productsTotalPriceTextLabel = null;
                private JLabel productsTotalPriceValueLabel = null;
                private JLabel productAmountTextLabel = null;
                private JLabel productAmountValueLabel = null;
                private JButton removeProductFromOrderButton = null;

                public ProductInFormingOrderPanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(210, 195));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                    productIdTextLabel = new JLabel("Product ID:");
                    productIdTextLabel.setFont(anotherFont);

                    productIdValueLabel = new JLabel("z000");

                    productNameTextLabel = new JLabel("Product Name:");
                    productNameTextLabel.setFont(anotherFont);

                    productNameValueLabel = new JLabel("Hurma");

                    productsTotalPriceTextLabel = new JLabel("Price:");
                    productsTotalPriceTextLabel.setFont(anotherFont);

                    productsTotalPriceValueLabel = new JLabel("100");

                    productAmountTextLabel = new JLabel("Amount:");
                    productAmountTextLabel.setFont(anotherFont);

                    productAmountValueLabel = new JLabel("10");

                    removeProductFromOrderButton = new JButton("Remove");
                    removeProductFromOrderButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            removeProductFromOrderButtonFunction();
                        }
                    });
                }

                private void compose(){
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
                        .addGroup(l.createSequentialGroup()
                            .addGap(35)
                            .addComponent(removeProductFromOrderButton)
                        )
                        // .addComponent(testLabel)
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
                        .addGroup(l.createParallelGroup()
                            .addComponent(removeProductFromOrderButton)
                        )
                        // .addComponent(testLabel)
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }                

                private void removeProductFromOrderButtonFunction(){
                    if(amountOfProductsInFormingOrderPanel > 0)
                        amountOfProductsInFormingOrderPanel--;
                    fillOrderWithProducts();
                }
            }

            private class AddProductToNewOrderPanel{

                private JPanel panel = null;

                // private JLabel testLabel = new JLabel("storage order test label");

                private JLabel chooseProvidersProductLabel = null;
                private JComboBox<String> choosingProvidersProductBox = null;

                private JLabel productIdTextLabel = null;
                private JLabel productIdValueLabel = null;
                private JLabel productNameTextLabel =  null;
                private JLabel productNameValueLabel = null;
                private JLabel productPricePerPieceTextLabel = null;
                private JLabel productPricePerPieceValueLabel = null;
                private JLabel productAmountTextLabel = null;
                private JTextField productAmountTextField = null;
                private JButton addProductToOrderButton = null;

                public AddProductToNewOrderPanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(210, 195));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                    chooseProvidersProductLabel = new JLabel("Product:");
                    chooseProvidersProductLabel.setFont(anotherFont);

                    // здесь создаём строки-идентифкаторы, которые являются комбинацией айдишника и названия продукта 
                    String prod1ID = "z0", prod2ID = "z1";
                    String prod1Name = "Apple", prod2Name = "Cheese";
                    String[] products = {"-", prod1ID + " - " + prod1Name, prod2ID + " - " + prod2Name,};  
                    choosingProvidersProductBox = new JComboBox<>();
                    for(int i = 0, n = products.length; i < n; i++){
                        choosingProvidersProductBox.addItem(products[i]);
                    }
                    choosingProvidersProductBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event){
                            String valueOfBox = (String) choosingProvidersProductBox.getSelectedItem();
                            productAmountTextField.setText("");
                            switch(valueOfBox){
                                case("-"):
                                    productIdValueLabel.setText("-");
                                    productNameValueLabel.setText("-");
                                    productPricePerPieceValueLabel.setText("-");
                                    productAmountTextField.setEditable(false);
                                    break;
                                case("z0 - Apple"):
                                    productIdValueLabel.setText(prod1ID);
                                    productNameValueLabel.setText(prod1Name);
                                    productPricePerPieceValueLabel.setText("2");
                                    productAmountTextField.setEditable(true);
                                    break;
                                case("z1 - Cheese"):
                                    productIdValueLabel.setText(prod2ID);
                                    productNameValueLabel.setText(prod2Name);
                                    productPricePerPieceValueLabel.setText("10");
                                    productAmountTextField.setEditable(true);
                                    break;
                            }
                        }
                    });
                    
                    
                    productIdTextLabel = new JLabel("Product ID:");
                    productIdTextLabel.setFont(anotherFont);

                    productIdValueLabel = new JLabel("-");

                    productNameTextLabel = new JLabel("Product Name:");
                    productNameTextLabel.setFont(anotherFont);

                    productNameValueLabel = new JLabel("-");

                    productPricePerPieceTextLabel = new JLabel("Price:");
                    productPricePerPieceTextLabel.setFont(anotherFont);

                    productPricePerPieceValueLabel = new JLabel("-");

                    productAmountTextLabel = new JLabel("Amount:");
                    productAmountTextLabel.setFont(anotherFont);

                    productAmountTextField = new JTextField(5);
                    productAmountTextField.setEditable(false);

                    addProductToOrderButton = new JButton("Add");
                    addProductToOrderButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            addProductToOrderButtonFunction();
                        }
                    });                    
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup(LEADING)
                        .addGroup(l.createSequentialGroup()
                            .addComponent(chooseProvidersProductLabel)
                            .addGap(10)
                            .addComponent(choosingProvidersProductBox)
                        )
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
                            .addComponent(productPricePerPieceTextLabel)
                            .addGap(10)
                            .addComponent(productPricePerPieceValueLabel)
                        )
                        .addGroup(l.createSequentialGroup()
                            .addComponent(productAmountTextLabel)
                            .addGap(10)
                            .addComponent(productAmountTextField)
                        )
                        .addGroup(l.createSequentialGroup()
                            .addGap(45)
                            .addComponent(addProductToOrderButton)
                        )
                        // .addComponent(testLabel)
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                        .addGap(10)
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(chooseProvidersProductLabel)
                            .addComponent(choosingProvidersProductBox)
                        )
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
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(productPricePerPieceTextLabel)
                            .addComponent(productPricePerPieceValueLabel)
                        )
                        .addGap(10)
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(productAmountTextLabel)
                            .addComponent(productAmountTextField)
                        )
                        .addGroup(l.createParallelGroup()
                            .addComponent(addProductToOrderButton)
                        )
                        // .addComponent(testLabel)
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }                

                private void addProductToOrderButtonFunction(){
                    amountOfProductsInFormingOrderPanel++;
                    fillOrderWithProducts();
                }                
            }

            private class NewOrderInfoPanel{

                private JPanel panel = null;

                // private JLabel testLabel = new JLabel("storage order test label");
                private JLabel chooseProviderLabel = null;
                private JComboBox choosingProviderBox = null;
                private String choosedProviderId = "z000";

                private JLabel providerIdTextLabel = null;
                private JLabel providerIdValueLabel = null;
                private JLabel providerNameTextLabel = null;
                private JLabel providerNameValueLabel = null;
                private JLabel providerPhoneTextLabel = null;
                private JLabel providerPhoneValueLabel = null;
                private JLabel deliveryDateTextLabel = null;
                private JTextField deliveryDateTextField = null;
                private JButton formNewOrderButton = null;

                public NewOrderInfoPanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(250, 195));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    
                    chooseProviderLabel = new JLabel("Provider:");
                    chooseProviderLabel.setFont(anotherFont);

                    // здесь создаём строки-индентификаторы, которые являются комбинацией айдишника и имени провайдера
                    String prov1ID = "z000", prov2ID = "y111", prov3ID = "x222";
                    String prov1Name = "Chingiz", prov2Name = "Fred", prov3Name = "Egor";
                    String[] providers = {prov1ID + " - " + prov1Name, prov2ID + " - " + prov2Name, prov3ID + " - " + prov3Name};
                    choosingProviderBox = new JComboBox();
                    for(int i = 0, n = providers.length; i < n; i++){
                        choosingProviderBox.addItem(providers[i]);
                    }
                    choosingProviderBox.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event){
                            String valueOfBox = (String) ((JComboBox)event.getSource()).getSelectedItem();
                            switch(valueOfBox){
                                case ("z000 - Chingiz"):
                                    providerIdValueLabel.setText(prov1ID);
                                    providerNameValueLabel.setText(prov1Name);
                                    providerPhoneValueLabel.setText("+375291234567");
                                    break;
                                case ("y111 - Fred"):
                                    providerIdValueLabel.setText(prov2ID);
                                    providerNameValueLabel.setText(prov2Name);
                                    providerPhoneValueLabel.setText("+375112223344");
                                    break;
                                case ("x222 - Egor"):
                                    providerIdValueLabel.setText(prov3ID);
                                    providerNameValueLabel.setText(prov3Name);
                                    providerPhoneValueLabel.setText("+375997654321");
                                    break;
                            }
                        }
                    });

                    providerIdTextLabel = new JLabel("Provider ID:");
                    providerIdTextLabel.setFont(anotherFont);

                    providerIdValueLabel = new JLabel("z000");

                    providerNameTextLabel = new JLabel("Provider Name:");
                    providerNameTextLabel.setFont(anotherFont);

                    providerNameValueLabel = new JLabel("Chingiz");

                    providerPhoneTextLabel = new JLabel("Provider phone:");
                    providerPhoneTextLabel.setFont(anotherFont);

                    providerPhoneValueLabel = new JLabel("+375291234567");

                    deliveryDateTextLabel = new JLabel("Delivering date:");
                    deliveryDateTextLabel.setFont(anotherFont);

                    deliveryDateTextField = new JTextField(10);

                    formNewOrderButton = new JButton("Make order");

                    formNewOrderButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            formNewOrderButtonFunction();
                        }
                    });
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup(LEADING)
                        .addGroup(l.createSequentialGroup()
                            .addComponent(chooseProviderLabel)
                            .addGap(5)
                            .addComponent(choosingProviderBox)
                        )
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
                            .addComponent(deliveryDateTextField)
                        )
                        .addGroup(l.createSequentialGroup()
                            .addGap(60)
                            .addComponent(formNewOrderButton)
                        )
                        // .addComponent(testLabel)
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(chooseProviderLabel)
                            .addComponent(choosingProviderBox)
                        )
                        .addGap(10)
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
                        .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(deliveryDateTextLabel)
                            .addComponent(deliveryDateTextField)
                        )
                        .addGap(10)
                        .addGroup(l.createParallelGroup()
                            .addComponent(formNewOrderButton)
                        )
                        // .addComponent(testLabel)
                    );

                }

                public String getChoosedProviderId(){
                    return choosedProviderId;
                }

                public JPanel getPanel(){
                    return panel;
                }                

                private void formNewOrderButtonFunction(){

                }
            }

        }
    }
}
