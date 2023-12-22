package org.sklad.view.screen.client.frames;

import org.sklad.model.Client;
import org.sklad.model.ClientOrder;
import org.sklad.model.Product;
import org.sklad.repository.ClientRepo;
import org.sklad.repository.ProductRepo;
import org.sklad.model.OrderStatus;
import org.sklad.util.Toast;
import org.sklad.util.Utils;
import org.sklad.view.screen.client.toolbar.ClientAppToolBar;

import static javax.swing.GroupLayout.Alignment.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ClientCartScreenFrame {
    private JFrame frame;
    private final int WIDTH = 800;
    private final int HEIGHT = 490;

    private JPanel toolBarPanel = null;

    private JPanel cartProductsPanel = null;
    private ArrayList<ProductInCartPanel> cartProductsPanels = null;

    private JPanel orderingPanel = null;

    private ClientRepo clientRepository;
    private ProductRepo productRepository;
    private ClientOrder cartClientOrder;
    private Client currentClient;

    public ClientCartScreenFrame() {
        // Добавим то что нам нужно
        clientRepository = new ClientRepo();
        productRepository = new ProductRepo();
        currentClient = clientRepository.getCurrentClient();
        cartClientOrder = clientRepository.getOrderInfo();
        if (cartClientOrder == null){
            clientRepository.setOrderInfo(cartClientOrder = new ClientOrder(
                    currentClient.name,
                    currentClient.phone,
                    currentClient.address,
                    Utils.getCurrentDate(),
                    OrderStatus.IN_CART,
                    new ArrayList<>()
            ));

        }

        // Создание окна
        frame = new JFrame("Client cart");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Создание элементов окна
        toolBarPanel = (new ClientAppToolBar(frame)).getPanel();

        cartProductsPanel = new JPanel();
        cartProductsPanel.setSize(100, 100);
        cartProductsPanel.setPreferredSize(new Dimension(500, 370));
        cartProductsPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

        orderingPanel = new OrderingPanel().getPanel();

        // Компоновка элементов
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//        for (int i = 0; i < 20; i++) {
//            panel1.add(new ProductInCartPanel().getPanel());
//            panel1.add(Box.createVerticalStrut(5));
//        }

        for (Product product : currentClient.cart) {
            panel1.add(new ProductInCartPanel(product).getPanel());
            panel1.add(Box.createVerticalStrut(5));
        }


        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, cartProductsPanel.getWidth(), cartProductsPanel.getHeight());

        cartProductsPanel.setLayout(new BorderLayout());
        cartProductsPanel.add(scrollPane);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

//
        JPanel panelM = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panelM);

        GroupLayout layout = new GroupLayout(panelM);
        panelM.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(CENTER, false)
                .addComponent(toolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(cartProductsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(toolBarPanel)
                .addGroup(layout.createParallelGroup(CENTER, false)
                        .addComponent(cartProductsPanel)
                        .addComponent(orderingPanel)
                )
        );
    }

    private class OrderingPanel {
        private JPanel panel = null;

        private JLabel nameLabel = null;
        private JTextField nameTextField = null;
        private JLabel phoneNumberLabel = null;
        private JTextField phoneNumberTextField = null;
        private JLabel addressLabel = null;
        private JTextField addressTextField = null;
        private JLabel deliveringDateLabel = null;
        private JTextField deliveringDateTextField = null;
        private JButton makeOrderButton = null;

        public OrderingPanel() {
            panel = new JPanel();

            panel.setSize(100, 100);
            panel.setPreferredSize(new Dimension(250, 370));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

            // Delivery Name
            nameLabel = new JLabel("Name:");
            nameTextField = new JTextField(15);
            if (cartClientOrder.deliveryName != null)
                nameTextField.setText(cartClientOrder.deliveryName);
            nameTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    cartClientOrder.deliveryName = nameTextField.getText();
                }
            });

            // Delivery phone number
            phoneNumberLabel = new JLabel("Phone:");
            phoneNumberTextField = new JTextField(15);
            if (cartClientOrder.deliveryPhone != null) {
                phoneNumberTextField.setText(cartClientOrder.deliveryPhone);
            }
            phoneNumberTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    cartClientOrder.deliveryPhone = phoneNumberTextField.getText();
                }
            });

            // Delivery address
            addressLabel = new JLabel("Address:");
            addressTextField = new JTextField(15);
            addressTextField.setText(cartClientOrder.deliveryAddress);
            addressTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    cartClientOrder.deliveryAddress = addressTextField.getText();
                }
            });

            // Delivery date
            deliveringDateLabel = new JLabel("Date to deliver:");
            deliveringDateTextField = new JTextField(15);
            if (cartClientOrder.deliveryDate != null) {
                deliveringDateTextField.setText(cartClientOrder.deliveryDate);
            }
            deliveringDateTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO(ADD DATE CONVERTER)
                    cartClientOrder.deliveryDate = deliveringDateTextField.getText();
                }
            });

            makeOrderButton = new JButton("Make order");
            makeOrderButton.addActionListener(e -> makeOrderButtonFunction());

            JPanel panel2 = new JPanel();
            panel.add(panel2);
            GroupLayout oLayout = new GroupLayout(panel2);
            panel2.setLayout(oLayout);

            oLayout.setHorizontalGroup(oLayout.createParallelGroup(CENTER)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField)
                    .addComponent(phoneNumberLabel)
                    .addComponent(phoneNumberTextField)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField)
                    .addComponent(deliveringDateLabel)
                    .addComponent(deliveringDateTextField)
                    .addComponent(makeOrderButton)
            );

            oLayout.setVerticalGroup(oLayout.createParallelGroup()
                    .addGroup(oLayout.createSequentialGroup()
                            .addGap(50)
                            .addComponent(nameLabel)
                            .addComponent(nameTextField)
                            .addComponent(phoneNumberLabel)
                            .addComponent(phoneNumberTextField)
                            .addComponent(addressLabel)
                            .addComponent(addressTextField)
                            .addComponent(deliveringDateLabel)
                            .addComponent(deliveringDateTextField)
                            .addComponent(makeOrderButton)
                    )
            );
        }

        private void makeOrderButtonFunction() {
            if (!cartClientOrder.checkValidity()){
                Toast.show("Fill required fields");
            } else if (currentClient.cart.isEmpty()){
                Toast.show("Nothing to add");
            } else {
                if (Utils.isValidDateFormat(cartClientOrder.deliveryDate)){
                    cartClientOrder.deliveryProducts = new ArrayList<>(currentClient.cart);
                    //------------------------------------------------------------//
//                    cartOrder.deliveryStatus = OrderStatus.IN_PROCESS;
                    cartClientOrder.deliveryStatus = OrderStatus.IN_PROCESS;
                    //------------------------------------------------------------//
                    cartClientOrder.setId(ClientOrder.provideId());
                    currentClient.clientOrders.add(cartClientOrder);
                    currentClient.cart = new ArrayList<>();
                    clientRepository.setOrderInfo(null);
                    frame.dispose();
                    new ClientCartScreenFrame();
                    Toast.show("Order was made");
                } else {
                    deliveringDateTextField.setText("");
                    Toast.show("Wrong date format (dd/MM/yyyy)");
                }

            }
        }

        public JPanel getPanel() {
            return panel;
        }
    }

    private class ProductInCartPanel {

        private JPanel panel = null;

        private JLabel productNameLabel = null;
        private JLabel productDescriptionLabel = null;
        private JLabel productImageLabel = null;
        private JLabel amountTextLabel = null;
        private JLabel amountValueLabel = null;
        private JLabel priceTextLabel = null;
        private JLabel priceValueLabel = null;
        private JButton removeButton = null;

        // Placeholder
        public ProductInCartPanel() {
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productNameLabel = new JLabel("name placeholder");

            productDescriptionLabel = new JLabel("description placeholder");
            productDescriptionLabel.setMinimumSize(new Dimension(150, 75));
            productDescriptionLabel.setPreferredSize(new Dimension(300, 75));
            productDescriptionLabel.setMaximumSize(new Dimension(450, 75));
            productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            productImageLabel = new JLabel();
            Image image = Toolkit.getDefaultToolkit().createImage("Images/imagePlaceHolder.png");
            productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
            productImageLabel.setPreferredSize(new Dimension(75, 75));

            amountTextLabel = new JLabel("Amount:");
            amountValueLabel = new JLabel("1");

            priceTextLabel = new JLabel("Price:");
            priceValueLabel = new JLabel("10");

            removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    removeButtonFunction();
                }
            });

            compose();
        }

        public ProductInCartPanel(Product product) {                   // Вот тут сама карточка
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            // Name
            productNameLabel = new JLabel(product.name);

            // Description
            productDescriptionLabel = new JLabel("suita " + product.description);
            productDescriptionLabel.setMinimumSize(new Dimension(150, 75));
            productDescriptionLabel.setPreferredSize(new Dimension(300, 75));
            productDescriptionLabel.setMaximumSize(new Dimension(450, 75));
            productDescriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Image
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

            // Amount
            amountTextLabel = new JLabel("Amount:");
            amountValueLabel = new JLabel("" + product.availableAmount);

            // Price
            priceTextLabel = new JLabel("Price:");
            priceValueLabel = new JLabel("" + product.calculateTotalPrice());

            removeButton = new JButton("Remove");
            removeButton.addActionListener(event -> removeButtonFunction(product));

            compose();
        }

        private void compose() {
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(productNameLabel)
                    .addGroup(l.createSequentialGroup()
                            .addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(productDescriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(l.createParallelGroup(CENTER)
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(amountTextLabel)
                                            .addComponent(amountValueLabel)
                                    )
                                    .addGroup(l.createSequentialGroup()
                                            .addComponent(priceTextLabel)
                                            .addComponent(priceValueLabel)
                                    )
                                    .addComponent(removeButton)
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
                                            .addComponent(amountTextLabel)
                                            .addComponent(amountValueLabel)
                                    )
                                    .addGroup(l.createParallelGroup()
                                            .addComponent(priceTextLabel)
                                            .addComponent(priceValueLabel)
                                    )
                                    .addComponent(removeButton)
                            )
                    )
            );
        }

        private void removeButtonFunction(Product product) {
            // Убираем из корзины клиента
            // Если товара на складе нет, то добавляем обратно
            // Если товар есть, то просто прибавляем amount
            Toast.show("Product removed from cart");
            clientRepository.removeProductFromCart(product);
            frame.dispose();
            new ClientCartScreenFrame();
        }

        public JPanel getPanel() {
            return panel;
        }
    }
}
