package org.sklad.view.screen.courier;

import org.sklad.model.OrderStatus;
import org.sklad.model.Package;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class CourierInWarehouseScreenFrame {
    JFrame frame = new JFrame();

    private JPanel preparedPackagesPanel = null;
    private JLabel preparedPackagesLabel = null;
    private JLabel orderTextLabel = null;
    private JLabel addressTextLabel = null;
    private JLabel deliveryDateTextLabel = null;
    private JLabel chosenPackageLabel = null;

    private JButton exitButton = null;
    private ManagerRepo repository;

    public CourierInWarehouseScreenFrame(){
        repository = new ManagerRepo();

        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Courier in warehouse");
        int WIDTH = 400;
        int HEIGHT = 300;
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        preparedPackagesPanel = new JPanel();
        preparedPackagesPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        preparedPackagesPanel.setPreferredSize(new Dimension(400, 200));
        preparedPackagesPanel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
//        for(int i = 0; i < 8; i++){
//            panel1.add(new PreparedPackagePanel().getPanel());
//            panel1.add(Box.createVerticalStrut(10));
//        }
        // Todo(----------------------------Adding packages----------------------------)
        // Todo(Добавить проверку на взятие)
        for (Package pkg: repository.getAvailablePackages()) {
            panel1.add(new PreparedPackagePanel(pkg).getPanel());
            panel1.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, preparedPackagesPanel.getWidth(), preparedPackagesPanel.getHeight());

        preparedPackagesPanel.setLayout(new BorderLayout());
        preparedPackagesPanel.add(scrollPane);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        preparedPackagesLabel = new JLabel("Prepared packages");

        orderTextLabel = new JLabel("OrderId:");

        addressTextLabel = new JLabel("Address:");

        deliveryDateTextLabel = new JLabel("Delivery date:");

        exitButton = new JButton("Back");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                exitButtonFunction();
            }
        });
    }

    private void compose(){
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(preparedPackagesLabel)
                // .addGroup(l.createSequentialGroup()
                //     .addComponent(orderTextLabel)
                //     .addGap(20)
                //     .addComponent(addressTextLabel)
                //     .addGap(20)
                //     .addComponent(deliveryDateTextLabel)
                //     .addGap(20)
                // )
                .addComponent(preparedPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(preparedPackagesLabel)
                .addGap(15)
                // .addGroup(l.createParallelGroup()
                //     .addComponent(orderTextLabel)
                //     .addComponent(addressTextLabel)
                //     .addComponent(deliveryDateTextLabel)
                // )
                .addComponent(preparedPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton)
        );
    }

    private void exitButtonFunction(){
        frame.dispose();
        new CourierScreenFrame();
    }

    private class PreparedPackagePanel{

        private JPanel panel = null;

        private JLabel packageOrderLabel = null;
        private JLabel clientAddressLabel = null;
        private JLabel deliveryDateLabel = null;
        private JButton choosePackageButton = null;

        public PreparedPackagePanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            packageOrderLabel = new JLabel("z000");

            clientAddressLabel = new JLabel("city");

            deliveryDateLabel = new JLabel("29.12.2000");

            choosePackageButton = new JButton("Choose");

            choosePackageButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
//                    choosePackageButtonFunction();
                }
            });
        }

        public PreparedPackagePanel(Package pkg){
            createElements(pkg);
            compose();
        }

        private void createElements(Package pkg){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            packageOrderLabel = new JLabel("id: " + pkg.getOderId());

            clientAddressLabel = new JLabel(pkg.getClientAddress());

            deliveryDateLabel = new JLabel(pkg.getDeliveryDate());

            choosePackageButton = new JButton("Choose");
            choosePackageButton.addActionListener(e -> choosePackageButtonFunction(pkg));
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                    .addGap(30)
                    .addComponent(packageOrderLabel)
                    .addGap(30)
                    .addComponent(clientAddressLabel)
                    .addGap(30)
                    .addComponent(deliveryDateLabel)
                    .addGap(30)
                    .addComponent(choosePackageButton)
            );

            l.setVerticalGroup(l.createParallelGroup()
                    .addComponent(packageOrderLabel)
                    .addComponent(clientAddressLabel)
                    .addComponent(deliveryDateLabel)
                    .addComponent(choosePackageButton)
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private void choosePackageButtonFunction(Package pkg){
//            pkg.setPackageOrderStatus(OrderStatus.BEING_DELIVERED);
            repository.updateOrderStatus(pkg, OrderStatus.BEING_DELIVERED);
            repository.setCurrentPackage(pkg);
            Toast.show("Package chosen");
            frame.dispose();
            new CourierScreenFrame();
        }
    }
}