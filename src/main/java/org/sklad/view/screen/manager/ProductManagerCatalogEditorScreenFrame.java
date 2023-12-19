package org.sklad.view.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerCatalogEditorScreenFrame {
    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 700;

    private JPanel productManagerToolBarPanel = null;
    private JPanel catalogEditorPanel = null;
    private JPanel productsPanel = null;
    private JPanel addProductToCatalogPanel = null;

    public ProductManagerCatalogEditorScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Catalog Editor");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        catalogEditorPanel = new JPanel();
        catalogEditorPanel.setPreferredSize(new Dimension(750, 600));
        catalogEditorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        catalogEditorPanel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        for(int i = 0; i < 6; i++){
            panel1.add(new ProductInCatalogPanel().getPanel());
            panel1.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        productsPanel = new JPanel();
        productsPanel.setPreferredSize(new Dimension(750, 300));
        productsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        productsPanel.setLayout(new BorderLayout());
        productsPanel.add(scrollPane);

        addProductToCatalogPanel = new AddProductToCatalogPanel().getPanel();
        addProductToCatalogPanel.setPreferredSize(new Dimension(750, 150));
        addProductToCatalogPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
    }

    private void compose(){
        GroupLayout l2 = new GroupLayout(catalogEditorPanel);
        catalogEditorPanel.setLayout(l2);

        l2.setAutoCreateGaps(true);
        l2.setAutoCreateContainerGaps(true);

        l2.setHorizontalGroup(l2.createParallelGroup(CENTER)
                .addComponent(productsPanel)
                .addComponent(addProductToCatalogPanel)
        );

        l2.setVerticalGroup(l2.createSequentialGroup()
                .addComponent(productsPanel)
                .addComponent(addProductToCatalogPanel)
        );

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
                .addComponent(catalogEditorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(catalogEditorPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private class ProductInCatalogPanel{
        private JPanel panel = null;

        private JLabel productIdTextLabel = null;
        private JLabel productIdValueLabel = null;
        private JLabel productNameTextLabel = null;
        private JTextField productNameTextField = null;

        private JLabel productImageLabel = null;
        private JLabel productDescriptionTextLabel = null;
        private JTextArea productDescriptionTextArea = null;

        private JLabel productAvailableAmountLabel = null;
        private JTextField productAvailabelAmountTextField = null;
        private JLabel productPricePerPieceTextLabel = null;
        private JTextField productPricePerPieceTextField = null;

        private JButton setImageButton = null;
        private JButton removeProductButton = null;

        private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        public ProductInCatalogPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productIdTextLabel = new JLabel("Product ID:");
            productIdTextLabel.setFont(anotherFont);

            productIdValueLabel = new JLabel("p000");

            productNameTextLabel = new JLabel("Product Name:");
            productNameTextLabel.setFont(anotherFont);

            productNameTextField = new JTextField(15);

            productImageLabel = new JLabel();
            Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
            productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            productImageLabel.setPreferredSize(new Dimension(75, 75));

            productDescriptionTextLabel = new JLabel("Description:");
            productDescriptionTextLabel.setFont(anotherFont);

            productDescriptionTextArea = new JTextArea();
            productDescriptionTextArea.setLineWrap(true);
            productDescriptionTextArea.setPreferredSize(new Dimension(300, 75));
            productDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            productAvailableAmountLabel = new JLabel("Available amount:");
            productAvailableAmountLabel.setFont(anotherFont);

            productAvailabelAmountTextField = new JTextField(12);

            productPricePerPieceTextLabel = new JLabel("Price per piece:");
            productPricePerPieceTextLabel.setFont(anotherFont);

            productPricePerPieceTextField = new JTextField(12);

            setImageButton = new JButton("Set Image");
            removeProductButton = new JButton("Remove product");

            setImageButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    setImageButtonFunction();
                }
            });

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
                    .addGroup(l.createParallelGroup(CENTER)
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(productIdTextLabel)
                                    .addGap(5)
                                    .addComponent(productIdValueLabel)
                                    .addGap(40)
                                    .addComponent(productNameTextLabel)
                                    .addGap(5)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(productImageLabel)
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(productDescriptionTextLabel)
                                            .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                                    .addGap(10)
                                    .addGroup(l.createParallelGroup(LEADING, false)
                                            .addComponent(productAvailableAmountLabel)
                                            .addComponent(productAvailabelAmountTextField)
                                            .addComponent(productPricePerPieceTextLabel)
                                            .addComponent(productPricePerPieceTextField)
                                    )
                            )
                    )
                    .addGroup(l.createParallelGroup()
                            .addComponent(setImageButton)
                            .addComponent(removeProductButton)
                    )
            );
            l.linkSize(SwingConstants.HORIZONTAL, setImageButton, removeProductButton);

            l.setVerticalGroup(l.createParallelGroup(CENTER)
                    .addGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addComponent(productIdTextLabel)
                                    .addComponent(productIdValueLabel)
                                    .addComponent(productNameTextLabel)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(l.createParallelGroup(TRAILING)
                                    .addComponent(productImageLabel)
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(productDescriptionTextLabel)
                                            .addGap(5)
                                            .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                    )
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(productAvailableAmountLabel)
                                            .addComponent(productAvailabelAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(productPricePerPieceTextLabel)
                                            .addComponent(productPricePerPieceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                            )
                    )
                    .addGroup(l.createSequentialGroup()
                            .addComponent(setImageButton)
                            .addComponent(removeProductButton)
                    )
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private void setImageButtonFunction(){

        }

        private void removeProductButtonFunction(){

        }
    }

    private class AddProductToCatalogPanel{
        private JPanel panel = null;

        private JLabel productIdTextLabel = null;
        private JTextField productIdTextField = null;
        private JLabel productNameTextLabel = null;
        private JTextField productNameTextField = null;

        private JLabel productImageLabel = null;
        private JLabel productDescriptionTextLabel = null;
        private JTextArea productDescriptionTextArea = null;

        private JLabel productAvailableAmountLabel = null;
        private JTextField productAvailabelAmountTextField = null;
        private JLabel productPricePerPieceTextLabel = null;
        private JTextField productPricePerPieceTextField = null;

        private JButton setImageButton = null;
        private JButton resetButton = null;
        private JButton addProductButton = null;

        private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        public AddProductToCatalogPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            productIdTextLabel = new JLabel("Product ID:");
            productIdTextLabel.setFont(anotherFont);

            productIdTextField = new JTextField(15);

            productNameTextLabel = new JLabel("Product Name:");
            productNameTextLabel.setFont(anotherFont);

            productNameTextField = new JTextField(15);

            productImageLabel = new JLabel();
            Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
            productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            productImageLabel.setPreferredSize(new Dimension(75, 75));

            productDescriptionTextLabel = new JLabel("Description:");
            productDescriptionTextLabel.setFont(anotherFont);

            productDescriptionTextArea = new JTextArea();
            productDescriptionTextArea.setLineWrap(true);
            productDescriptionTextArea.setPreferredSize(new Dimension(300, 75));
            productDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            productAvailableAmountLabel = new JLabel("Available amount:");
            productAvailableAmountLabel.setFont(anotherFont);

            productAvailabelAmountTextField = new JTextField(12);

            productPricePerPieceTextLabel = new JLabel("Price per piece:");
            productPricePerPieceTextLabel.setFont(anotherFont);

            productPricePerPieceTextField = new JTextField(12);

            setImageButton = new JButton("Set Image");
            resetButton = new JButton("Reset");
            addProductButton = new JButton("Add product");

            setImageButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    setImageButtonFunction();
                }
            });

            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    resetButtonFunction();
                }
            });

            addProductButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    addProductButtonFunction();
                }
            });
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                    .addGroup(l.createParallelGroup(CENTER)
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(productIdTextLabel)
                                    .addGap(5)
                                    .addComponent(productIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20)
                                    .addComponent(productNameTextLabel)
                                    .addGap(5)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(productImageLabel)
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(productDescriptionTextLabel)
                                            .addGap(5)
                                            .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addGap(5)
                                    )
                                    .addGap(10)
                                    .addGroup(l.createParallelGroup(LEADING, false)
                                            .addComponent(productAvailableAmountLabel)
                                            .addComponent(productAvailabelAmountTextField)
                                            .addComponent(productPricePerPieceTextLabel)
                                            .addComponent(productPricePerPieceTextField)
                                    )
                            )
                    )
                    .addGroup(l.createParallelGroup()
                            .addComponent(setImageButton)
                            .addComponent(resetButton)
                            .addComponent(addProductButton)
                    )
            );
            l.linkSize(SwingConstants.HORIZONTAL, setImageButton, resetButton, addProductButton);

            l.setVerticalGroup(l.createParallelGroup(CENTER)
                    .addGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addComponent(productIdTextLabel)
                                    .addComponent(productIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productNameTextLabel)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(l.createParallelGroup(TRAILING)
                                    .addComponent(productImageLabel)
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(productDescriptionTextLabel)
                                            .addComponent(productDescriptionTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(productAvailableAmountLabel)
                                            .addComponent(productAvailabelAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(productPricePerPieceTextLabel)
                                            .addComponent(productPricePerPieceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                            )
                    )
                    .addGroup(l.createSequentialGroup()
                            .addComponent(setImageButton)
                            .addComponent(resetButton)
                            .addComponent(addProductButton)
                    )
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private void setImageButtonFunction(){

        }

        private void resetButtonFunction(){

        }

        private void addProductButtonFunction(){

        }
    }
}