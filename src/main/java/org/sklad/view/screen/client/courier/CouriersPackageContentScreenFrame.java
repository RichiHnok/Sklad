package org.sklad.view.screen.client.courier;

import org.sklad.model.Client;
import org.sklad.model.Package;
import org.sklad.model.Product;
import org.sklad.repository.ClientRepo;
import org.sklad.repository.ManagerRepo;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ещё не доделан
public class CouriersPackageContentScreenFrame {
    private JFrame frame = null;

    private final int WIDTH = 450;
    private final int HEIGHT = 450;

    private JLabel orderIdLabel = null;
    private JPanel productsInPackagePanel = null;

    private JButton closeButton = null;
    private ManagerRepo managerRepo;
    private ClientRepo clientRepo;
    private Client currentClient;
    private Package currentPackage;

    public CouriersPackageContentScreenFrame(){
        managerRepo = new ManagerRepo();
        clientRepo = new ClientRepo();
        currentClient = clientRepo.getCurrentClient();
        currentPackage = managerRepo.getCurrentPackage();

        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Couriers package content");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        orderIdLabel = new JLabel("Order ID: " + currentPackage.getOderId());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

//        for(int i = 0; i < 12; i++){
//            panel1.add(new ProductInPackage().getPanel());
//            panel1.add(Box.createVerticalStrut(10));
//            // я остановился здесь
//        }
        // todo---------------------------------------------------------------------------
        for (Product product: currentPackage.getPackageOrder().deliveryProducts) {
            panel1.add(new ProductInPackage(product).getPanel());
            panel1.add(Box.createVerticalStrut(10));
        }
        // todo---------------------------------------------------------------------------

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scrollPane.setBounds(0, 0, productsInPackagePanel.getWidth(), productsInPackagePanel.getHeight());

        productsInPackagePanel = new JPanel();
        productsInPackagePanel.setPreferredSize(new Dimension(400, 200));
        productsInPackagePanel.setLayout(new BorderLayout());
        productsInPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        productsInPackagePanel.add(scrollPane);

        closeButton = new JButton("Back");

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                closeButtonFunction();
            }
        });
    }

    private void compose(){
        JPanel panel1 = new JPanel();

        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel1);

        GroupLayout l = new GroupLayout(panel1);
        panel1.setLayout(l);
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(orderIdLabel)
                .addComponent(productsInPackagePanel)
                .addComponent(closeButton)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(orderIdLabel)
                .addComponent(productsInPackagePanel)
                .addComponent(closeButton)
        );
    }

    private void closeButtonFunction(){
        frame.dispose();
        new ThereIsCourierScreenFrame();
    }

    private class ProductInPackage {

        private JPanel panel = null;

        private JLabel productNameLabel = null;
        private JLabel productAmountLabel = null;

        public ProductInPackage(){
            createElements();
            compose();
        }
        public ProductInPackage(Product product){
            createElements(product);
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productNameLabel = new JLabel("name placeholder");

            productAmountLabel = new JLabel("amount placeholder");
        }

        private void createElements(Product product){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productNameLabel = new JLabel("product: " + product.name);

            productAmountLabel = new JLabel("amount : " + product.availableAmount);
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                    .addComponent(productNameLabel)
                    .addGap(15)
                    .addComponent(productAmountLabel)
            );

            l.setVerticalGroup(l.createParallelGroup()
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