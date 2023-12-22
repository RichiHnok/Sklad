package org.sklad.view.screen.manager;

import org.sklad.model.Product;
import org.sklad.model.SerialImage;
import org.sklad.repository.ProductRepo;
import org.sklad.util.Utils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerCatalogEditorScreenFrame {
    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 700;

    private JPanel productManagerToolBarPanel = null;
    private JPanel catalogEditorPanel = null;
    private JPanel productsPanel = null;
    private ProductRepo productRepo;
    private ArrayList<Product> products;

    public ProductManagerCatalogEditorScreenFrame() {
        productRepo = new ProductRepo();
        products = productRepo.getProductList();
        createElements();
        compose();
    }

    private void createElements() {
        frame = new JFrame("Catalog Editor");
        frame.setSize(WIDTH, HEIGHT);
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

        // DEPRECATED
//        for(int i = 0; i < 6; i++){
//            panel1.add(new ProductInCatalogPanel().getPanel());
//            panel1.add(Box.createVerticalStrut(15));
//        }

        for (Product product : products) {
            panel1.add(new ProductInCatalogPanel(product).getPanel());
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
    }

    private void compose() {
        GroupLayout l2 = new GroupLayout(catalogEditorPanel);
        catalogEditorPanel.setLayout(l2);

        l2.setAutoCreateGaps(true);
        l2.setAutoCreateContainerGaps(true);

        l2.setHorizontalGroup(l2.createParallelGroup(CENTER)
                .addComponent(productsPanel)
        );

        l2.setVerticalGroup(l2.createSequentialGroup()
                .addComponent(productsPanel)
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

    private class ProductInCatalogPanel {
        private JPanel panel = null;

        private JLabel productIdTextLabel = null;
        private JLabel productIdValueLabel = null;
        private JLabel productNameTextLabel = null;
        private JTextField productNameTextField = null;
        private JLabel productProviderTextLabel = null;
        private JLabel productProviderValueLabel = null;

        private JLabel productImageLabel = null;
        private JLabel productDescriptionTextLabel = null;
        private JTextArea productDescriptionTextArea = null;

        private JLabel productAvailableAmountLabel = null;
        private JTextField productAvailabelAmountTextField = null;
        private JLabel productPricePerPieceTextLabel = null;
        private JTextField productPricePerPieceTextField = null;

        private JLabel showProductToClientsLabel = null;
        private JCheckBox showProductToClientBox = null;

        private JButton setImageButton = null;

        private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        // DEPRECATED
//        public ProductInCatalogPanel(){
//            createElements();
//            compose();
//        }

        public ProductInCatalogPanel(Product product) {
            createElements(product);
            compose();
        }

        // DEPRECATED
//        private void createElements(){
//            panel = new JPanel();
//            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
//
//            productIdTextLabel = new JLabel("Product ID:");
//            productIdTextLabel.setFont(anotherFont);
//
//            productIdValueLabel = new JLabel("p000");
//            productIdValueLabel.setPreferredSize(new Dimension(70, productIdValueLabel.getHeight()));
//
//            productNameTextLabel = new JLabel("Product Name:");
//            productNameTextLabel.setFont(anotherFont);
//
//            productNameTextField = new JTextField(15);
//
//            productProviderTextLabel = new JLabel("Provider:");
//            productProviderTextLabel.setFont(anotherFont);
//
//            productProviderValueLabel = new JLabel("Chingiz");
//            productProviderValueLabel.setPreferredSize(new Dimension(100, productProviderValueLabel.getHeight()));
//
//            productImageLabel = new JLabel();
//            Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
//            productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
//            productImageLabel.setPreferredSize(new Dimension(75, 75));
//
//            productDescriptionTextLabel = new JLabel("Description:");
//            productDescriptionTextLabel.setFont(anotherFont);
//
//            productDescriptionTextArea = new JTextArea();
//            productDescriptionTextArea.setLineWrap(true);
//            productDescriptionTextArea.setPreferredSize(new Dimension(300, 75));
//            productDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//            productAvailableAmountLabel = new JLabel("Available amount:");
//            productAvailableAmountLabel.setFont(anotherFont);
//            productAvailabelAmountTextField = new JTextField(12);
//
//            productPricePerPieceTextLabel = new JLabel("Price per piece:");
//            productPricePerPieceTextLabel.setFont(anotherFont);
//            productPricePerPieceTextField = new JTextField(12);
//
//            showProductToClientsLabel = new JLabel("Show product:");
//            showProductToClientsLabel.setFont(anotherFont);
//
//            showProductToClientBox = new JCheckBox();
//            showProductToClientBox.setSelected(true);
//
//            setImageButton = new JButton("Set Image");
//
//            setImageButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e){
//                    setImageButtonFunction();
//                }
//            });
//        }

        private void createElements(Product product) {
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            //TODO--------------------------------------------Add Listeners//
            productIdTextLabel = new JLabel("Product ID:");
            productIdTextLabel.setFont(anotherFont);
            productIdValueLabel = new JLabel("" + product.id);
            productIdValueLabel.setPreferredSize(new Dimension(70, productIdValueLabel.getHeight()));

            productNameTextLabel = new JLabel("Product Name:");
            productNameTextLabel.setFont(anotherFont);
            productNameTextField = new JTextField(15);
            productNameTextField.setText(product.name);
            productNameTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    product.name = productNameTextField.getText();
                }
            });

            productProviderTextLabel = new JLabel("");
            productProviderTextLabel.setFont(anotherFont);
            productProviderValueLabel = new JLabel("");
            productProviderValueLabel.setPreferredSize(new Dimension(100, productProviderValueLabel.getHeight()));

            //TODO------------------------------------------------//
            productImageLabel = new JLabel();                                   // Image Url
            if (product.image == null) {
                try {
                    URL imageURL = new URL(product.imageUrl);
                    ImageIcon icon = new ImageIcon(Utils.resizeImage(imageURL));
                    productImageLabel.setIcon(icon);
                } catch (Exception e) {
                    Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
                    productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                    System.out.println("Проблема с картинкой");
                }
            } else {
                productImageLabel.setIcon(new ImageIcon(product.image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            }
            productImageLabel.setPreferredSize(new Dimension(75, 75));
            //TODO------------------------------------------------//

            productDescriptionTextLabel = new JLabel("Description:");
            productDescriptionTextLabel.setFont(anotherFont);
            productDescriptionTextArea = new JTextArea();
            productDescriptionTextArea.setLineWrap(true);
            productDescriptionTextArea.setPreferredSize(new Dimension(300, 75));
            productDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            productDescriptionTextArea.setText(product.description);
            productDescriptionTextArea.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    product.description = productDescriptionTextArea.getText();
                }
            });

            productAvailableAmountLabel = new JLabel("Available amount:");
            productAvailableAmountLabel.setFont(anotherFont);
            productAvailabelAmountTextField = new JTextField(12);
            productAvailabelAmountTextField.setText("" + product.availableAmount);
            productAvailabelAmountTextField.setEnabled(false);

            productPricePerPieceTextLabel = new JLabel("Price per piece:");
            productPricePerPieceTextLabel.setFont(anotherFont);
            productPricePerPieceTextField = new JTextField(12);
            productPricePerPieceTextField.setText("" + product.pricePerPiece);
            productPricePerPieceTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        if (productPricePerPieceTextField.getText().isBlank())
                            productPricePerPieceTextField.setText("0");
                        product.pricePerPiece = Double.parseDouble(productPricePerPieceTextField.getText());
                        if (product.pricePerPiece < 0){
                            productPricePerPieceTextField.setText("0");
                            product.pricePerPiece = 0;
                        }
                    } catch (NumberFormatException exception) {
                        productPricePerPieceTextField.setText("0");
                    }
                }
            });

            showProductToClientsLabel = new JLabel("Show product:");
            showProductToClientsLabel.setFont(anotherFont);
            showProductToClientBox = new JCheckBox();
            if (product.visible)
                showProductToClientBox.setSelected(true);
            else
                showProductToClientBox.setSelected(false);
            showProductToClientBox.addChangeListener(e -> product.visible = showProductToClientBox.isSelected());

            setImageButton = new JButton("Set Image");

            setImageButton.addActionListener(e -> setImageButtonFunction(product));
        }

        private void compose() {
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                    .addGroup(l.createParallelGroup(CENTER)
                            .addGroup(l.createSequentialGroup()
                                    .addGap(10)
                                    .addComponent(productIdTextLabel)
                                    .addGap(5)
                                    .addComponent(productIdValueLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(productNameTextLabel)
                                    .addGap(5)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(productProviderTextLabel)
                                    .addGap(5)
                                    .addComponent(productProviderValueLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addGroup(l.createParallelGroup(CENTER)
                                            .addComponent(productImageLabel)
                                            .addComponent(setImageButton)
                                    )
                                    .addGap(20)
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
                                            .addGroup(l.createSequentialGroup()
                                                    .addComponent(showProductToClientsLabel)
                                                    .addComponent(showProductToClientBox)
                                            )
                                    )
                            )
                    )
            );

            l.setVerticalGroup(l.createParallelGroup(CENTER)
                    .addGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addComponent(productIdTextLabel)
                                    .addComponent(productIdValueLabel)
                                    .addComponent(productNameTextLabel)
                                    .addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productProviderTextLabel)
                                    .addComponent(productProviderValueLabel)
                            )
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(productImageLabel)
                                            .addComponent(setImageButton)
                                    )
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
                                            .addGroup(l.createParallelGroup(CENTER)
                                                    .addComponent(showProductToClientsLabel)
                                                    .addComponent(showProductToClientBox)
                                            )
                                    )
                            )
                    )
            );
        }

        public JPanel getPanel() {
            return panel;
        }

        private void setImageButtonFunction(Product product) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("Images"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            if (fileChooser.showDialog(frame, "Choose") == JFileChooser.APPROVE_OPTION) {
                String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                Image image = Toolkit.getDefaultToolkit().createImage(imagePath);
                product.image = image;
                productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            }
        }
    }
}