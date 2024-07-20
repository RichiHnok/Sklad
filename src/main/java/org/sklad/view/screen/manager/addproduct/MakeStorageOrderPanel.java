package org.sklad.view.screen.manager.addproduct;

import org.sklad.model.Product;
import org.sklad.model.Provider;
import org.sklad.model.StorageOrder;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;
import org.sklad.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

class MakeStorageOrderPanel {
    private final Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private final Font titleFont = new Font("Verdana", Font.BOLD, 18);
    private ArrayList<JPanel> arrayOfProductsInFormingOrderPanel = null;
    private JPanel panel = null;
    private JLabel formingNewOrderLabel = null;
    private JPanel productsInNewOrderPanel = null;
    private JPanel orderInfoPanel = null;
    private JPanel panel1 = null;
    private JFrame frame;
    private ManagerRepo repository;
    private Provider currentProvider;
    private Product currentProduct = new Product();
    private ArrayList<Provider> providers;

    public MakeStorageOrderPanel(JFrame frame) {
        repository = new ManagerRepo();
        providers = repository.getProviders();
        currentProvider = repository.getCurrentProvider();
        this.frame = frame;

        createElements();
        compose();
    }

    private void createElements() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 230));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        formingNewOrderLabel = new JLabel("FORMING NEW ORDER");
        formingNewOrderLabel.setFont(titleFont);

        // (--*--) Список того что уже выбрали (--*--)                                              (--*--)
        arrayOfProductsInFormingOrderPanel = new ArrayList<>();
        if (currentProvider != null)
            for (Product product : currentProvider.getCart())
                arrayOfProductsInFormingOrderPanel.add(new ProductInFormingOrderPanel(product).getPanel());

        panel1 = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
        panel1.setLayout(gridLayout);
        for (JPanel product : arrayOfProductsInFormingOrderPanel)
            panel1.add(product);

        // (--*--) Добавить продукт (--*--)                                                         (--*--)
        if (currentProvider != null)
            panel1.add(new AddProductToNewOrderPanel().getPanel());

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        productsInNewOrderPanel = new JPanel();
        productsInNewOrderPanel.setLayout(new BorderLayout());
        productsInNewOrderPanel.setPreferredSize(new Dimension(472, 195));
        productsInNewOrderPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 70, 255), 2));
        productsInNewOrderPanel.add(scrollPane);

        // (--*--) Выбор поставщика (--*--)                                                         (--*--)
        orderInfoPanel = new NewOrderInfoPanel().getPanel();
    }

    private void compose() {
        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(formingNewOrderLabel)
                .addGroup(l.createSequentialGroup()
                        .addComponent(productsInNewOrderPanel)
                        .addComponent(orderInfoPanel)
                )
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(formingNewOrderLabel)
                .addGroup(l.createParallelGroup()
                        .addComponent(productsInNewOrderPanel)
                        .addComponent(orderInfoPanel)
                )
        );

    }

    // TODO (Выбор поставщика) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private class NewOrderInfoPanel {
        private JPanel panel = null;
        private JLabel chooseProviderLabel = null;
        private JComboBox<Provider> choosingProviderBox = null;
        private JLabel providerIdTextLabel = null;
        private JLabel providerIdValueLabel = null;
        private JLabel providerNameTextLabel = null;
        private JLabel providerNameValueLabel = null;
        private JLabel providerPhoneTextLabel = null;
        private JLabel providerPhoneValueLabel = null;
        private JLabel deliveryDateTextLabel = null;
        private JTextField deliveryDateTextField = null;
        private JButton formNewOrderButton = null;

        public NewOrderInfoPanel() {
            createElements();
            compose();
        }

        private void createElements() {
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(250, 195));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            chooseProviderLabel = new JLabel("Provider:");
            chooseProviderLabel.setFont(anotherFont);

            // todo(Вот это говно переработать) -------//-------//-------//-------//-------//-------//-------//-------//
            choosingProviderBox = new JComboBox<>();
            for (Provider provider : providers)
                choosingProviderBox.addItem(provider);

            if (currentProvider != null)
                choosingProviderBox.setSelectedItem(currentProvider);

            choosingProviderBox.addActionListener(event -> {
                repository.setCurrentProvider((Provider) choosingProviderBox.getSelectedItem());
                currentProvider = repository.getCurrentProvider();

                frame.dispose();
                new ProductManagerStorageOrdersScreenFrame();
            });
            // todo(Вот это говно переработать) -------//-------//-------//-------//-------//-------//-------//-------//

            providerIdTextLabel = new JLabel("Provider ID:");
            providerIdTextLabel.setFont(anotherFont);

            providerNameTextLabel = new JLabel("Provider Name:");
            providerNameTextLabel.setFont(anotherFont);

            providerPhoneTextLabel = new JLabel("Provider phone:");
            providerPhoneTextLabel.setFont(anotherFont);

            deliveryDateTextLabel = new JLabel("Delivering date:");
            deliveryDateTextLabel.setFont(anotherFont);
            deliveryDateTextField = new JTextField(10);
            deliveryDateTextField.setText(Utils.getCurrentDate());

            if (currentProvider == null) {
                providerIdValueLabel = new JLabel("");
                providerNameValueLabel = new JLabel("");
                providerPhoneValueLabel = new JLabel("");
            } else {
                providerIdValueLabel = new JLabel("" + currentProvider.getId());
                providerNameValueLabel = new JLabel(currentProvider.getName());
                providerPhoneValueLabel = new JLabel(currentProvider.getPhone());
            }

            formNewOrderButton = new JButton("Make order");
            formNewOrderButton.addActionListener(e -> formNewOrderButtonFunction());
        }

        private void compose() {
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
            );

        }

        private void formNewOrderButtonFunction() {
            // Собираем все продукты
            if (currentProvider == null || currentProvider.getCart().isEmpty()){
                Toast.show("U tebya problemy bratishka!");
            } else {
                StorageOrder storageOrder =
                        new StorageOrder(currentProvider, deliveryDateTextField.getText(), currentProvider.getCart());
                repository.addToStorageOrders(storageOrder);
                Toast.show("Storage Order Was Made");
                currentProvider.clearCart();
                frame.dispose();
                new ProductManagerStorageOrdersScreenFrame();
            }
        }

        public JPanel getPanel() {
            return panel;
        }
    }

    // TODO (Добавление товара в список сука) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private class AddProductToNewOrderPanel {

        private JPanel panel = null;

        private JLabel chooseProvidersProductLabel = null;
        private JComboBox<Product> choosingProvidersProductBox = null;

        private JLabel productIdTextLabel = null;
        private JLabel productIdValueLabel = null;
        private JLabel productNameTextLabel = null;
        private JLabel productNameValueLabel = null;
        private JLabel productPricePerPieceTextLabel = null;
        private JLabel productPricePerPieceValueLabel = null;
        private JLabel productAmountTextLabel = null;
        private JTextField productAmountTextField = null;
        private JButton addProductToOrderButton = null;

        public AddProductToNewOrderPanel() {
            createElements();
            compose();
        }

        private void createElements() {
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(210, 195));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            chooseProvidersProductLabel = new JLabel("Product:");
            chooseProvidersProductLabel.setFont(anotherFont);

            // todo(Вот это говно переработать) -------//-------//-------//-------//-------//-------//-------//-------//
            ArrayList<Product> products = currentProvider.getProducts();
            choosingProvidersProductBox = new JComboBox<>();
            for (Product product : products) {
                choosingProvidersProductBox.addItem(product);
            }
            choosingProvidersProductBox.addActionListener(event -> {
                Product product = (Product) choosingProvidersProductBox.getSelectedItem();
                productIdValueLabel.setText("" + product.id);
                productNameValueLabel.setText(product.name);
                productPricePerPieceValueLabel.setText("" + product.pricePerPiece);
                productAmountTextField.setText("1");
                addProductToOrderButton.setEnabled(true);
                // Initialize product
                currentProduct.id = Integer.parseInt(productIdValueLabel.getText());
                currentProduct.name = productNameValueLabel.getText();
                currentProduct.pricePerPiece = Double.parseDouble(productPricePerPieceValueLabel.getText());
                currentProduct.description = repository.findDescriptionById(currentProduct.id);
            });
            // todo(Вот это говно переработать) -------//-------//-------//-------//-------//-------//-------//-------//

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
            productAmountTextField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        if (productAmountTextField.getText().isBlank())
                            productAmountTextField.setText("1");
                        if (Double.parseDouble(productAmountTextField.getText()) < 0) {
                            productAmountTextField.setText("1");
                        }
                    } catch (NumberFormatException exception) {
                        productAmountTextField.setText("1");
                    }
                }
            });

            addProductToOrderButton = new JButton("Add");
            addProductToOrderButton.setEnabled(false);
            addProductToOrderButton.addActionListener(e -> addProductToOrderButtonFunction());
        }

        private void compose() {
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
            );
        }

        private void addProductToOrderButtonFunction() {
            Product temp = new Product(currentProduct);
            temp.availableAmount = Integer.parseInt(productAmountTextField.getText());
            currentProvider.addToCart(temp);
            Toast.show("Product added to cart");
            frame.dispose();
            new ProductManagerStorageOrdersScreenFrame();
        }

        public JPanel getPanel() {
            return panel;
        }
    }

    // TODO (То что уже выбрали - хотим заказать) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    private class ProductInFormingOrderPanel {
        private JPanel panel = null;

        private JLabel productIdTextLabel = null;
        private JLabel productIdValueLabel = null;
        private JLabel productNameTextLabel = null;
        private JLabel productNameValueLabel = null;
        private JLabel productsTotalPriceTextLabel = null;
        private JLabel productsTotalPriceValueLabel = null;
        private JLabel productAmountTextLabel = null;
        private JLabel productAmountValueLabel = null;
        private JButton removeProductFromOrderButton = null;

        public ProductInFormingOrderPanel(Product product) {
            createElements(product);
            compose();
        }

        private void createElements(Product product) {
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(210, 195));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            productIdTextLabel = new JLabel("Product ID:");
            productIdTextLabel.setFont(anotherFont);

            productIdValueLabel = new JLabel("" + product.id);

            productNameTextLabel = new JLabel("Product Name:");
            productNameTextLabel.setFont(anotherFont);

            productNameValueLabel = new JLabel(product.name);

            productsTotalPriceTextLabel = new JLabel("Price:");
            productsTotalPriceTextLabel.setFont(anotherFont);

            productsTotalPriceValueLabel = new JLabel("" + product.pricePerPiece);

            productAmountTextLabel = new JLabel("Amount:");
            productAmountTextLabel.setFont(anotherFont);

            productAmountValueLabel = new JLabel("" + product.availableAmount);

            removeProductFromOrderButton = new JButton("Remove");
            removeProductFromOrderButton.addActionListener(e -> removeProductFromOrderButtonFunction(product));
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
                    .addGroup(l.createSequentialGroup()
                            .addGap(35)
                            .addComponent(removeProductFromOrderButton)
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
                    .addGroup(l.createParallelGroup()
                            .addComponent(removeProductFromOrderButton)
                    )
            );
        }

        private void removeProductFromOrderButtonFunction(Product product) {
            currentProvider.removeFromCart(product);
            frame.dispose();
            new ProductManagerStorageOrdersScreenFrame();
        }

        public JPanel getPanel() {
            return panel;
        }

    }

    public JPanel getPanel() {
        return panel;
    }
}