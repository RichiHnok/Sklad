package org.sklad.view.screen.client.frames;

import org.sklad.model.Client;
import org.sklad.model.ClientOrder;
import org.sklad.model.Package;
import org.sklad.repository.ClientRepo;
import org.sklad.repository.ManagerRepo;
import org.sklad.view.screen.ChoosingRoleFrame;
import org.sklad.view.screen.client.courier.ThereIsCourierScreenFrame;
import org.sklad.view.screen.client.courier.ThereIsNoCourierScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientScreenFrame {

    private JFrame frame = null;

    private Client client = null;

    private final int WIDTH = 450;
    private final int HEIGHT = 200;

    JButton useApplicationButton = null;
    JButton checkCourierButton = null;
    JButton exitButton = null;
    ManagerRepo managerRepo;
    ClientRepo clientRepo;
    Client currentClient;
    Package currentPackage;


    public ClientScreenFrame() {
        managerRepo = new ManagerRepo();
        clientRepo = new ClientRepo();
        currentClient = clientRepo.getCurrentClient();
        currentPackage = managerRepo.getCurrentPackage();

        // Создание окна
        frame = new JFrame("Client");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Создание элементов окна
        useApplicationButton = new JButton("Use Application");
        checkCourierButton = new JButton("Check Courier");
        exitButton = new JButton("Exit");

        useApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useApplicationButtonFunction();
            }
        });

        checkCourierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCourierButtonFunction();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonFunction();
            }
        });

        // Компоновка элементов
        JPanel panel = new JPanel();

        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(useApplicationButton)
                        .addComponent(checkCourierButton)
                        .addComponent(exitButton)
                )
        );
        layout.linkSize(SwingConstants.HORIZONTAL, useApplicationButton, checkCourierButton, exitButton);

        layout.setVerticalGroup(
                layout.createParallelGroup(CENTER)
                        .addComponent(useApplicationButton)
                        .addComponent(checkCourierButton)
                        .addComponent(exitButton)
        );
    }

    private void useApplicationButtonFunction() {
        frame.dispose();
        new ClientCatalogScreenFrame();
    }

    private void checkCourierButtonFunction() {
        if (currentPackage != null){
            for (ClientOrder clientOrder : currentClient.clientOrders) {
                if (Objects.equals(clientOrder.deliveryAddress, currentPackage.getCurrentAddress())){
                    frame.dispose();
                    new ThereIsCourierScreenFrame();
                    return;
                }
            }
        }
        frame.dispose();
        new ThereIsNoCourierScreenFrame();
    }

    private void exitButtonFunction() {
        ClientRepo repo = new ClientRepo();
        repo.setOrderInfo(null);
        repo.setCurrentClient(null);
        frame.dispose();
        new ChoosingRoleFrame();
    }
}
