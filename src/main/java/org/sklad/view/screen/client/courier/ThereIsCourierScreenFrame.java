package org.sklad.view.screen.client.courier;

import org.sklad.model.Client;
import org.sklad.model.OrderStatus;
import org.sklad.model.Package;
import org.sklad.repository.ClientRepo;
import org.sklad.repository.ManagerRepo;
import org.sklad.util.Toast;
import org.sklad.view.screen.client.frames.ClientScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThereIsCourierScreenFrame {

    private JFrame frame = null;

    private final int WIDTH = 500;
    private final int HEIGHT = 200;

    private JLabel thereIsCourierLabel = null;
    private JButton acceptOrderButton = null;
    private JButton viewPackageContentButton = null;
    private JButton declineOrderButton = null;

    private ManagerRepo managerRepo;
    private ClientRepo clientRepo;
    private Client currentClient;
    private Package currentPackage;

    public ThereIsCourierScreenFrame(){
        managerRepo = new ManagerRepo();
        clientRepo = new ClientRepo();
        currentClient = clientRepo.getCurrentClient();
        currentPackage = managerRepo.getCurrentPackage();

        createElemnts();
        compose();
    }

    private void createElemnts(){
        frame=new JFrame("There is no courier");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        thereIsCourierLabel = new JLabel("There is a courier");
        thereIsCourierLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        acceptOrderButton = new JButton("Accept package");

        viewPackageContentButton = new JButton("View package\ncontent");
        viewPackageContentButton.setHorizontalAlignment(JLabel.CENTER);

        declineOrderButton = new JButton("Decline package");

        acceptOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                acceptButtonFunction();
            }
        });

        viewPackageContentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                viewPackageContentButtonFunction();
            }
        });

        declineOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                declineOrderButtonFunction();
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
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(thereIsCourierLabel)
                .addGroup(l.createSequentialGroup()
                        .addComponent(acceptOrderButton)
                        .addComponent(viewPackageContentButton)
                        .addComponent(declineOrderButton)
                )
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(thereIsCourierLabel)
                .addGap(20)
                .addGroup(l.createParallelGroup()
                        .addComponent(acceptOrderButton)
                        .addComponent(viewPackageContentButton)
                        .addComponent(declineOrderButton)
                )
        );
    }

    private void acceptButtonFunction(){
        managerRepo.updateOrderStatus(currentPackage, OrderStatus.DELIVERED);
        managerRepo.setCurrentPackage(null);
        Toast.show("Package Accepted");
        frame.dispose();
        new ClientScreenFrame();
    }

    private void viewPackageContentButtonFunction(){
        frame.dispose();
        new CouriersPackageContentScreenFrame();
    }

    private void declineOrderButtonFunction(){
        clientRepo.removeOrder(currentPackage.getPackageOrder());
        managerRepo.setCurrentPackage(null);
        Toast.show("Package Declined");
        frame.dispose();
        new ClientScreenFrame();
    }
}