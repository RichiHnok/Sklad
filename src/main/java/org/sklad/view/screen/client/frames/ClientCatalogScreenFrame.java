package org.sklad.view.screen.client.frames;

import org.sklad.model.Client;
import org.sklad.model.Product;
import org.sklad.repository.ClientRepo;
import org.sklad.repository.ProductRepo;
import org.sklad.util.Toast;
import org.sklad.view.screen.client.toolbar.ClientAppToolBar;

import static javax.swing.GroupLayout.Alignment.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ClientCatalogScreenFrame {
    private JFrame frame;
    private final int WIDTH = 800;
    private final int HEIGHT = 490;

    private JPanel toolBarPanel = null;

    private JPanel catalogProductsPanel = null;
    // Инициализация репозиториев
    private ProductRepo productRepository = new ProductRepo();
    private ClientRepo clientRepository = new ClientRepo();
    private Client currentClient;


    public ClientCatalogScreenFrame() {
        // Создание окна
        frame = new JFrame("Client catalog");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Создание элементов окна
        toolBarPanel = (new ClientAppToolBar(frame)).getPanel();

        catalogProductsPanel = new JPanel();
        catalogProductsPanel.setPreferredSize(new Dimension(750, 370));
        catalogProductsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

        // Компоновка элементов
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//        Default Loop
//		for(int i = 0; i < 6; i++){
//			panel1.add(new ProductInCatalogProductsPanel().getPanel());
//			panel1.add(Box.createVerticalStrut(5));
//		}

        for (Product product : productRepository.getProductList()) {
            panel1.add(new ProductInCatalogProductsPanel(product).getPanel());
            panel1.add(Box.createVerticalStrut(5));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, catalogProductsPanel.getWidth(), catalogProductsPanel.getHeight());

        catalogProductsPanel.setLayout(new BorderLayout());
        catalogProductsPanel.add(scrollPane);
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
                .addComponent(catalogProductsPanel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(toolBarPanel)
                .addComponent(catalogProductsPanel)
        );
    }

    private class ProductInCatalogProductsPanel {
        JPanel panel = null;

        private JLabel productNameLabel = null;
        private JLabel productDescriptionLabel = null;
        private JLabel productImageLabel = null;
        private JLabel availableAmountTextLabel = null;
        private JLabel availableAmountValueLabel = null;
        private JLabel pricePerPieceTextLabel = null;
        private JLabel pricePerPieceValueLabel = null;
        private JTextField chosenAmountTextField = null;
        private JLabel priceOfChosenValueLabel = null;
        private JButton plusPieceButton = null;
        private JButton minusPieceButton = null;
        private JButton addToCartButton = null;

        // Placeholder
        public ProductInCatalogProductsPanel() {    // Default constructor
            createElements();
            compose();
        }

        // TODO()
        public ProductInCatalogProductsPanel(Product product) {    // Default constructor
            createElements(product);        // With Info
            compose();
        }

        private void createElements() {
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productNameLabel = new JLabel("name placeholder");

            productDescriptionLabel = new JLabel("description placeholder");
            productDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            productDescriptionLabel.setPreferredSize(new Dimension(370, 75));
            productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            productImageLabel = new JLabel();
            Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
            productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            productImageLabel.setPreferredSize(new Dimension(75, 75));

            availableAmountTextLabel = new JLabel("Available amount: ");
            availableAmountValueLabel = new JLabel("10");

            pricePerPieceTextLabel = new JLabel("Price per piece: ");
            pricePerPieceValueLabel = new JLabel("1");

            chosenAmountTextField = new JTextField("0");
            chosenAmountTextField.setColumns(4);
            chosenAmountTextField.setHorizontalAlignment(JTextField.CENTER);

            priceOfChosenValueLabel = new JLabel("0");
            priceOfChosenValueLabel.setPreferredSize(new Dimension(30, 10));

            // moneyUnitLabel = new JLabel(" $");

            plusPieceButton = new JButton("+");
            plusPieceButton.setPreferredSize(new Dimension(30, 50));
            plusPieceButton.setMaximumSize(new Dimension(30, 70));
            minusPieceButton = new JButton("-");
            minusPieceButton.setPreferredSize(new Dimension(30, 50));
            minusPieceButton.setMaximumSize(new Dimension(30, 70));

            addToCartButton = new JButton("Add to cart");
        }

        private void createElements(Product product) {
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productNameLabel = new JLabel(product.name);                        // Name

            productDescriptionLabel = new JLabel(product.description);          // Description
            productDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            productDescriptionLabel.setPreferredSize(new Dimension(370, 75));
            productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            productImageLabel = new JLabel();                                   // Image Url
            try {        // Image
                URL imageURL = new URL(product.imageUrl);
                ImageIcon icon = new ImageIcon(resizeImage(imageURL));
                productImageLabel.setIcon(icon);
            } catch (Exception e) {
                Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
                productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                System.out.println("Проблема с картинкой");
            }
            productImageLabel.setPreferredSize(new Dimension(75, 75));

            availableAmountTextLabel = new JLabel("Available amount: ");
            availableAmountValueLabel = new JLabel("" + product.availableAmount);        // Available Amount

            pricePerPieceTextLabel = new JLabel("Price per piece: ");
            pricePerPieceValueLabel = new JLabel("" + product.pricePerPiece);            // Price per Piece

            chosenAmountTextField = new JTextField("0");
            chosenAmountTextField.setColumns(4);
            chosenAmountTextField.setHorizontalAlignment(JTextField.CENTER);
            chosenAmountTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        if (chosenAmountTextField.getText().isBlank()) {
                            chosenAmountTextField.setText("0");
                        }
                        doCalculation(product);
                    } catch (NumberFormatException exception) {
                        chosenAmountTextField.setText("0");
                    }
                }
            });

            priceOfChosenValueLabel = new JLabel("0");
            priceOfChosenValueLabel.setPreferredSize(new Dimension(30, 10));

            // TODO(Кнопки организуй по братски)
            plusPieceButton = new JButton("+");
            plusPieceButton.setPreferredSize(new Dimension(30, 50));
            plusPieceButton.setMaximumSize(new Dimension(30, 70));
            plusPieceButton.addActionListener(e -> {
                if (Integer.parseInt(chosenAmountTextField.getText())
                        < Integer.parseInt(availableAmountValueLabel.getText())) {
                    chosenAmountTextField.setText(
                            "" + (Integer.parseInt(chosenAmountTextField.getText()) + 1)
                    );
                    doCalculation(product);
                } else {
                    chosenAmountTextField.setText(availableAmountValueLabel.getText());
                }
            });
            minusPieceButton = new JButton("-");
            minusPieceButton.setPreferredSize(new Dimension(30, 50));
            minusPieceButton.setMaximumSize(new Dimension(30, 70));
            minusPieceButton.addActionListener(event -> {
                if (Integer.parseInt(chosenAmountTextField.getText()) > 0) {
                    chosenAmountTextField.setText(
                            "" + (Integer.parseInt(chosenAmountTextField.getText()) - 1)
                    );
                    doCalculation(product);
                } else {
                    chosenAmountTextField.setText("0");
                }

            });

            addToCartButton = new JButton("Add to cart");
            addToCartButton.addActionListener(event -> {
                currentClient = clientRepository.getCurrentClient();
                int amount = Integer.parseInt(chosenAmountTextField.getText());
                if (amount > 0) {
                    Product productIntoCart = new Product(product);
                    productIntoCart.availableAmount = productRepository.takeSomeAmountOfProduct(product, amount);
                    currentClient.addToCart(productIntoCart);
                    Toast.show("Added to Cart");
                }
                chosenAmountTextField.setText("0");
                priceOfChosenValueLabel.setText("0.0");
                availableAmountValueLabel.setText("" + product.availableAmount);
            });
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

        private void doCalculation(Product product) {
            int num = Integer.parseInt(chosenAmountTextField.getText());
            if (num > Integer.parseInt(availableAmountValueLabel.getText()))
                num = Integer.parseInt(availableAmountValueLabel.getText());
            priceOfChosenValueLabel.setText(
                    "" + num * product.pricePerPiece
            );
            chosenAmountTextField.setText(
                    "" + num
            );
        }

        private void compose() {
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(productNameLabel)
                    .addGroup(l.createSequentialGroup()
                            .addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(10)
                            .addGroup(l.createParallelGroup()
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(availableAmountTextLabel)
                                            .addComponent(availableAmountValueLabel)
                                    )
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(pricePerPieceTextLabel)
                                            .addComponent(pricePerPieceValueLabel)
                                    )
                            )
                            .addGap(10)
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(minusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(l.createParallelGroup(CENTER)
                                                    .addComponent(chosenAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(l.createSequentialGroup()
                                                                    .addComponent(priceOfChosenValueLabel)
                                                            // .addComponent(moneyUnitLabel)
                                                    )
                                            )
                                            .addComponent(plusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                                    .addComponent(addToCartButton)
                            )
                    )
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(productNameLabel)
                    .addGroup(l.createParallelGroup(CENTER)
                            .addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(l.createSequentialGroup()
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(availableAmountTextLabel)
                                            .addComponent(availableAmountValueLabel)
                                    )
                                    .addGap(15)
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(pricePerPieceTextLabel)
                                            .addComponent(pricePerPieceValueLabel)
                                    )
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(minusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(l.createSequentialGroup()
                                                    .addComponent(chosenAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(l.createParallelGroup()
                                                                    .addComponent(priceOfChosenValueLabel)
                                                            // .addComponent(moneyUnitLabel)
                                                    )
                                            )
                                            .addComponent(plusPieceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    )
                                    .addComponent(addToCartButton)
                            )
                    )
            );
        }

        public JPanel getPanel() {
            return panel;
        }
    }
}
