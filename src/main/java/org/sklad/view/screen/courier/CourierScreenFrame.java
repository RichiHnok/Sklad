package org.sklad.view.screen.courier;

import org.sklad.model.OrderStatus;
import org.sklad.model.Package;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;
import org.sklad.view.screen.ChoosingRoleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class CourierScreenFrame {
    JFrame frame = new JFrame();

    private JButton goToWarehouseButton = null;
    private JButton deliverPackageButton = null;
    private JButton cancelButton = null;
    private JButton exitButton = null;

    private boolean isPackageChosen = true;

    private JPanel packagePanel = null;
    private ManagerRepo repository;
    private Package currentPackage;

    public CourierScreenFrame() {
        repository = new ManagerRepo();
        currentPackage = repository.getCurrentPackage();

        createElements();
        compose();
    }

    public void createElements() {
        frame = new JFrame("Courier");
        int WIDTH = 400;
        int HEIGHT = 250;
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        goToWarehouseButton = new JButton("Go to warehouse");
        goToWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToWarehouseButtonFunction();
            }
        });
        if (currentPackage != null){
            goToWarehouseButton.setEnabled(false);
        }

        deliverPackageButton = new JButton("Deliver package");
        if (currentPackage == null){
            deliverPackageButton.setEnabled(false);
        }
        deliverPackageButton.addActionListener(e -> deliverPackageButtonFunction());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancelButtonFunction());
        if (currentPackage == null){
            cancelButton.setEnabled(false);
        }

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exitButtonFunction());


        packagePanel = new JPanel();
        packagePanel.setLayout(new BorderLayout());
        if (isPackageChosen) {
            packagePanel.add(new PackagePanel().getPanel());
        } else {
            JLabel packageNotChosenLabel = new JLabel("Package not choosed");
            packageNotChosenLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            packageNotChosenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            packagePanel.add(packageNotChosenLabel);
        }
    }

    private void compose() {
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(CENTER)
                        .addComponent(goToWarehouseButton)
                        .addComponent(deliverPackageButton)
                        .addComponent(cancelButton)
                        .addComponent(exitButton)
                )
                .addComponent(packagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.linkSize(SwingConstants.HORIZONTAL, goToWarehouseButton, deliverPackageButton, cancelButton, exitButton);

        layout.setVerticalGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(goToWarehouseButton)
                        .addComponent(deliverPackageButton)
                        .addComponent(cancelButton)
                        .addComponent(exitButton)
                )
                .addComponent(packagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private class PackagePanel {
        private JPanel panel = null;

        private JLabel chosenPackageLabel = null;

        private JLabel packageOrderTextLabel = null;
        private JLabel packageOrderValueLabel = null;
        private JLabel deliveryAddressTextLabel = null;
        private JLabel deliveryAddressValueLabel = null;
        private JLabel deliveryDateTextLabel = null;
        private JLabel deliveryDateValueLabel = null;

        private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        public PackagePanel() {
            createElements();
            compose();
        }

        private void createElements() {
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(200, 200));
            // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            chosenPackageLabel = new JLabel("Chosen package");
            chosenPackageLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            chosenPackageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            packageOrderTextLabel = new JLabel("Order ID:");
            packageOrderTextLabel.setFont(anotherFont);
            if (currentPackage != null) {
                packageOrderValueLabel = new JLabel("" + currentPackage.getOderId());
            } else {
                packageOrderValueLabel = new JLabel("n/a");
            }


            deliveryAddressTextLabel = new JLabel("Address:");
            deliveryAddressTextLabel.setFont(anotherFont);
            if (currentPackage != null) {
                deliveryAddressValueLabel = new JLabel(currentPackage.getClientAddress());
            } else {
                deliveryAddressValueLabel = new JLabel("n/a");
            }

            deliveryDateTextLabel = new JLabel("Deliver date:");
            deliveryDateTextLabel.setFont(anotherFont);
            if (currentPackage != null) {
                deliveryDateValueLabel = new JLabel(currentPackage.getDeliveryDate());
            } else {
                deliveryDateValueLabel = new JLabel("n/a");
            }
        }

        private void compose() {
            JPanel panel1 = new JPanel();

            panel.setLayout(new GridBagLayout());
            panel.add(panel1);

            GroupLayout l = new GroupLayout(panel1);
            panel1.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(chosenPackageLabel)
                    .addGroup(l.createSequentialGroup()
                            .addComponent(packageOrderTextLabel)
                            .addGap(10)
                            .addComponent(packageOrderValueLabel)
                    )
                    .addGroup(l.createSequentialGroup()
                            .addComponent(deliveryAddressTextLabel)
                            .addGap(10)
                            .addComponent(deliveryAddressValueLabel)
                    )
                    .addGroup(l.createSequentialGroup()
                            .addComponent(deliveryDateTextLabel)
                            .addGap(10)
                            .addComponent(deliveryDateValueLabel)
                    )
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(chosenPackageLabel)
                    .addGap(15)
                    .addGroup(l.createParallelGroup()
                            .addComponent(packageOrderTextLabel)
                            .addComponent(packageOrderValueLabel)
                    )
                    .addGap(10)
                    .addGroup(l.createParallelGroup()
                            .addComponent(deliveryAddressTextLabel)
                            .addComponent(deliveryAddressValueLabel)
                    )
                    .addGap(10)
                    .addGroup(l.createParallelGroup()
                            .addComponent(deliveryDateTextLabel)
                            .addComponent(deliveryDateValueLabel)
                    )
                    .addGap(20)
            );
        }

        public JPanel getPanel() {
            return panel;
        }
    }

    public void goToWarehouseButtonFunction() {
        frame.dispose();
        new CourierInWarehouseScreenFrame();
    }

    public void deliverPackageButtonFunction() {
        frame.dispose();
        new CourierDeliveringPackagesScreenFrame();
    }

    public void exitButtonFunction() {
        frame.dispose();
        new ChoosingRoleFrame();
    }

    public void cancelButtonFunction() {
        repository.updateOrderStatus(currentPackage, OrderStatus.IN_PROCESS);
        repository.setCurrentPackage(null);
        Toast.show("Delivering canceled");
        frame.dispose();
        new CourierScreenFrame();
    }
}