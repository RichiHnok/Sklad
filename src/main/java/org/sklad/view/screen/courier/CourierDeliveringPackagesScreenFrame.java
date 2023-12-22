package org.sklad.view.screen.courier;

import org.sklad.model.Package;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class CourierDeliveringPackagesScreenFrame {
    JFrame frame = new JFrame();

    private final int WIDTH = 400;
    private final int HEIGHT = 250;

    private JLabel enterAddressLabel = null;
    private JTextField goingAddressTextField = null;
    private JButton goToAddressButton = null;
    private JLabel currentLocationTextLabel = null;
    private JLabel currentLocationValueLabel = null;
    private JButton stopDeliveringButton = null;
    ManagerRepo repository;
    Package currentPackage;

    public CourierDeliveringPackagesScreenFrame() {
        repository = new ManagerRepo();
        currentPackage = repository.getCurrentPackage();
        createElements();
        compose();
    }

    private void createElements() {
        frame = new JFrame("Courier delivering packages");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        enterAddressLabel = new JLabel();

        goingAddressTextField = new JTextField(20);

        goToAddressButton = new JButton("Deliver");

        goToAddressButton.addActionListener(e -> goToAddressButtonFunction());

        currentLocationTextLabel = new JLabel("Current Location:");

        currentLocationValueLabel = new JLabel(currentPackage.getCurrentAddress());

        stopDeliveringButton = new JButton("Back");

        stopDeliveringButton.addActionListener(e -> stopDeliveringButtonFunction());
    }

    private void compose() {
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(enterAddressLabel)
                .addComponent(goingAddressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(goToAddressButton)
                .addGroup(l.createSequentialGroup()
                        .addComponent(currentLocationTextLabel)
                        .addGap(10)
                        .addComponent(currentLocationValueLabel)
                )
                .addComponent(stopDeliveringButton)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(enterAddressLabel)
                .addComponent(goingAddressTextField)
                .addComponent(goToAddressButton)
                .addGroup(l.createParallelGroup()
                        .addComponent(currentLocationTextLabel)
                        .addComponent(currentLocationValueLabel)
                )
                .addGap(25)
                .addComponent(stopDeliveringButton)
        );
    }

    private void goToAddressButtonFunction() {
        String address = goingAddressTextField.getText();
        if (address == null || address.isBlank()) {
            Toast.show("Please provide address");
        } else {

            repository.getCurrentPackage().setCurrentAddress(address);
            currentLocationValueLabel.setText(address);
            Toast.show("Courier at " + address);
        }
    }

    private void stopDeliveringButtonFunction() {
        new CourierScreenFrame();
        frame.dispose();
    }
}