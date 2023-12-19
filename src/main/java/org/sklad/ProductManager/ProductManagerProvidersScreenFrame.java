package org.sklad.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerProvidersScreenFrame {
    private JFrame frame = new JFrame();

	private final int WIDTH = 800;
	private final int HEIGHT = 500;
    
    private JPanel productManagerToolBarPanel = null;

    private JPanel providersPanel = null;

    private JPanel addProviderPanel = null;

	private Font anotherFont = new Font("Verdana", Font.BOLD, 12);    

    public ProductManagerProvidersScreenFrame(){
        createElements();
        compose();
    }
    
    private void createElements(){
        frame = new JFrame("Providers");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        // providersPanel = new JPanel();
        // providersPanel.setPreferredSize(new Dimension(750, 170));
        // providersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        for(int i = 0; i < 3; i++){
            panel1.add(new ProviderPanel().getPanel());
            panel1.add(Box.createVerticalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        providersPanel = new JPanel();
        providersPanel.setLayout(new BorderLayout());
        providersPanel.setPreferredSize(new Dimension(750, 350));
        providersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        providersPanel.add(scrollPane);
        
        addProviderPanel = new AddProviderPanel().getPanel();
        addProviderPanel.setPreferredSize(new Dimension(750, 40));
        // addProviderPanel.setLayout(new GridBagLayout());
        addProviderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
			.addComponent(providersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addComponent(addProviderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		);

		l.setVerticalGroup(l.createSequentialGroup()
			.addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addComponent(providersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addComponent(addProviderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		);

    }

    private class ProviderPanel{
        private JPanel panel = null;

        private JLabel providerIdTextLabel = null;
        private JLabel providerIdValueLabel = null;
        private JLabel providerNameTextLabel = null;
        private JTextField providerNameTextField = null;
        private JLabel providerPhoneTextLabel = null;
        private JTextField providerPhoneTextField = null;
        private JButton removeProviderButton = null;

        private JPanel productsPanel = null;

        private JPanel addProductPanel = null;

        public ProviderPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            providerIdTextLabel = new JLabel("Provider ID:");
            providerIdTextLabel.setFont(anotherFont);
            providerIdTextLabel.setVerticalAlignment(JLabel.CENTER);            
            
            providerIdValueLabel = new JLabel("p123");
            providerIdValueLabel.setPreferredSize(new Dimension(50, providerIdValueLabel.getHeight()));
            // providerIdValueLabel.setVerticalAlignment(JLabel.CENTER);

            providerNameTextLabel = new JLabel("Provider Name:");
            providerNameTextLabel.setFont(anotherFont);
            providerNameTextLabel.setVerticalAlignment(JLabel.CENTER);

            providerNameTextField = new JTextField("Chingiz", 9);
            // providerNameTextField.setVerticalAlignment(JLabel.CENTER);

            providerPhoneTextLabel = new JLabel("Phone:");
            providerPhoneTextLabel.setFont(anotherFont);
            providerPhoneTextLabel.setVerticalAlignment(JLabel.CENTER);

            providerPhoneTextField = new JTextField("+375291234567", 9);

            removeProviderButton = new JButton("Remove provider");

            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            for(int i = 0; i < 4; i++){
                panel1.add(new ProvidersProductPanel().getPanel());
                panel1.add(Box.createVerticalStrut(5));
            }

			JScrollPane scrollPane = new JScrollPane(panel1);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            productsPanel = new JPanel();
            productsPanel.setPreferredSize(new Dimension(600, 300));
			productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			productsPanel.setLayout(new BorderLayout());
            productsPanel.add(scrollPane);
            addProductPanel = new AddProductToProviderPanel().getPanel();
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addGroup(l.createSequentialGroup()
                    .addGap(5)
                    .addComponent(providerIdTextLabel)
                    .addGap(5)
                    .addComponent(providerIdValueLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(providerNameTextLabel)
                    .addGap(5)
                    .addComponent(providerNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(providerPhoneTextLabel)
                    .addGap(5)
                    .addComponent(providerPhoneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeProviderButton)
                )
                .addGroup(l.createSequentialGroup()
                    .addComponent(productsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(addProductPanel)
            );

            l.setVerticalGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup(CENTER, false)
                    .addComponent(providerIdTextLabel)
                    .addComponent(providerIdValueLabel)
                    .addComponent(providerNameTextLabel)
                    .addComponent(providerNameTextField)
                    .addComponent(providerPhoneTextLabel)
                    .addComponent(providerPhoneTextField)
                    .addComponent(removeProviderButton)                    
                )
                .addGroup(l.createParallelGroup()
                    .addComponent(productsPanel)
                )
                .addComponent(addProductPanel)
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private class ProvidersProductPanel{
            private JPanel panel = null;

            private JLabel productIdTextLabel = null;
            private JLabel productIdValueLabel = null;
            private JLabel nameOfProductTextLabel = null;
            private JTextField nameOfProductTextField = null;
            private JLabel pricePerPieceTextLabel = null;
            private JTextField pricePerPieceTextField = null;
            private JLabel productDescriptionTextLabel = null;
            private JTextArea productDescriptionTextArea = null;
            private JButton removeProductButton = null;

            public ProvidersProductPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                productIdTextLabel = new JLabel("Product ID:");
                productIdTextLabel.setFont(anotherFont);

                productIdValueLabel = new JLabel("z000");
                productIdValueLabel.setPreferredSize(new Dimension(40, productIdValueLabel.getHeight()));

                nameOfProductTextLabel = new JLabel("Product name:");
                nameOfProductTextLabel.setFont(anotherFont);
                nameOfProductTextField = new JTextField("Hurma", 10);

                pricePerPieceTextLabel = new JLabel("Price per piece:");
                pricePerPieceTextLabel.setFont(anotherFont);
                pricePerPieceTextField = new JTextField("10", 10);

                productDescriptionTextLabel = new JLabel("Description:");
                productDescriptionTextLabel.setFont(anotherFont);
                productDescriptionTextArea = new JTextArea("description");
                productDescriptionTextArea.setLineWrap(true);
                productDescriptionTextArea.setPreferredSize(new Dimension(300, 100));
                productDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                removeProductButton = new JButton("Remove");
                removeProductButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        removeProductButtonFunction();
                    }
                });
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createSequentialGroup()
                    .addGap(10)
                    .addGroup(l.createParallelGroup()
                        .addGroup(l.createSequentialGroup()
                            .addComponent(productIdTextLabel)
                            .addGap(5)
                            .addComponent(productIdValueLabel)
                        )
                        .addComponent(nameOfProductTextLabel)
                        .addComponent(nameOfProductTextField)
                        .addComponent(pricePerPieceTextLabel)
                        .addComponent(pricePerPieceTextField)
                    )
                    .addGap(15)
                    .addGroup(l.createParallelGroup()
                        .addComponent(productDescriptionTextLabel)
                        .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(removeProductButton)
                );

                l.setVerticalGroup(l.createParallelGroup(CENTER)
                    .addGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup()
                            .addComponent(productIdTextLabel)
                            .addComponent(productIdValueLabel)
                        )
                        .addGap(5)
                        .addComponent(nameOfProductTextLabel)
                        .addComponent(nameOfProductTextField)
                        .addComponent(pricePerPieceTextLabel)
                        .addComponent(pricePerPieceTextField)
                    )
                    .addGroup(l.createSequentialGroup()
                        .addComponent(productDescriptionTextLabel)
                        .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(removeProductButton)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

            private void removeProductButtonFunction(){

            }
        }

        private class AddProductToProviderPanel{
            private JPanel panel = null;

            private JLabel nameOfProductTextLabel = null;
            private JTextField nameOfProductTextField = null;
            private JLabel pricePerPieceTextLabel = null;
            private JTextField pricePerPieceTextField = null;
            private JLabel productDescriptionTextLabel = null;
            private JTextArea productDescriptionTextArea = null;
            private JButton addProductToProviderButton = null;

            public AddProductToProviderPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                nameOfProductTextLabel = new JLabel("Product name:");
                nameOfProductTextLabel.setFont(anotherFont);

                nameOfProductTextField = new JTextField(14);

                pricePerPieceTextLabel = new JLabel("Price per piece:");
                pricePerPieceTextLabel.setFont(anotherFont);

                pricePerPieceTextField = new JTextField(14);

                productDescriptionTextLabel = new JLabel("Description:");
                productDescriptionTextLabel.setFont(anotherFont);
                
                productDescriptionTextArea = new JTextArea();
                productDescriptionTextArea.setLineWrap(true);
                productDescriptionTextArea.setPreferredSize(new Dimension(300, 75));

                addProductToProviderButton = new JButton("Add");
                addProductToProviderButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        addProductToProviderButtonFunction();
                    }
                });
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createSequentialGroup()
                    .addGroup(l.createParallelGroup(LEADING, false)
                        .addComponent(nameOfProductTextLabel)
                        .addComponent(nameOfProductTextField)
                        .addComponent(pricePerPieceTextLabel)
                        .addComponent(pricePerPieceTextField)
                    )
                    .addGap(15)
                    .addGroup(l.createParallelGroup()
                        .addComponent(productDescriptionTextLabel)
                        .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(addProductToProviderButton)
                );

                l.setVerticalGroup(l.createParallelGroup(CENTER, false)
                    .addGroup(l.createSequentialGroup()
                        .addComponent(nameOfProductTextLabel)
                        .addComponent(nameOfProductTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(15)
                        .addComponent(pricePerPieceTextLabel)
                        .addComponent(pricePerPieceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGroup(l.createSequentialGroup()
                        .addComponent(productDescriptionTextLabel)
                        .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(addProductToProviderButton)
                );
                 
            }

            public JPanel getPanel(){
                return panel;
            }

            private void addProductToProviderButtonFunction(){

            }
        }
    }

    private class AddProviderPanel{
        private JPanel panel = null;

        private JLabel providerIdTextLabel = null;
        private JTextField providerIdTextField = null;
        private JLabel providerPhoneTextLabel = null;
        private JTextField providerPhoneTextField = null;
        private JButton addProviderButton = null;

		private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        public AddProviderPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            providerIdTextLabel = new JLabel("Provider Name:");
            providerIdTextLabel.setFont(anotherFont);
            providerIdTextLabel.setVerticalAlignment(JLabel.CENTER);

            providerIdTextField = new JTextField(30);

            providerPhoneTextLabel = new JLabel("Phone:");
            providerPhoneTextLabel.setFont(anotherFont);
            providerPhoneTextLabel.setVerticalAlignment(JLabel.CENTER);

            providerPhoneTextField = new JTextField(30);

            addProviderButton = new JButton("Add provider");

            addProviderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    addProviderButtonFunction();
                }
            });
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                .addGap(5)
                .addComponent(providerIdTextLabel)
                .addGap(5)
                .addComponent(providerIdTextField)
                .addGap(30)
                .addComponent(providerPhoneTextLabel)
                .addGap(5)
                .addComponent(providerPhoneTextField)
                .addGap(5)
                .addComponent(addProviderButton)
            );

            l.setVerticalGroup(l.createParallelGroup(CENTER, false)
                .addComponent(providerIdTextLabel)
                .addComponent(providerIdTextField)
                .addComponent(providerPhoneTextLabel)
                .addComponent(providerPhoneTextField)
                .addComponent(addProviderButton)
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private void addProviderButtonFunction(){

        }
    }
}
