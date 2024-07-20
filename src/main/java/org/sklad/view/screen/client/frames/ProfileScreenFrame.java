package org.sklad.view.screen.client.frames;
import org.sklad.db.DB;
import org.sklad.model.Client;
import org.sklad.repository.ClientRepo;
import org.sklad.util.Toast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProfileScreenFrame {
    JFrame frame = new JFrame();

    private final int WIDTH = 250;
    private final int HEIGHT = 400;

    private JLabel loginLabel = null;
    private JLabel loginTextLabel= null;

    private JLabel defaultValuesLabel = null;
    private JLabel nameLabel = null;
    private JTextField nameTextField = null;
    private JLabel phoneLabel = null;
    private JTextField phoneTextField = null;
    private JLabel addressLabel = null;
    private JTextField addressTextField = null;

    private JButton saveButton = null;
    private JButton cancelButton = null;

    private ClientRepo clientRepository;
    private Client currentClient;
    private ArrayList<String> clientData;
    private String input;

    public ProfileScreenFrame() {
        clientRepository = new ClientRepo();
        currentClient = clientRepository.getCurrentClient();
        clientData = new ArrayList<>(3);
        clientData.add("");
        clientData.add("");
        clientData.add("");
        createElements();
        compose();
    }

    private void createElements() {
        frame = new JFrame("Profile");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        loginLabel = new JLabel("");

//        loginTextLabel = new JLabel("Placeholder");
        // loginTextLabel.setColumns(16);

        defaultValuesLabel = new JLabel("User Profile : ");
        defaultValuesLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        nameLabel = new JLabel("Name:");

        nameTextField = new JTextField();
        nameTextField.setColumns(16);
        nameTextField.setText(currentClient.name);
        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                input = nameTextField.getText();
                if (!input.isBlank()){
                    clientData.set(0, input);
                }
            }
        });

        phoneLabel = new JLabel("Phone number:");
        phoneTextField = new JTextField("");
        phoneTextField.setColumns(16);
        if (currentClient.phone != null && !currentClient.phone.isBlank()){
            phoneTextField.setText(currentClient.phone);
        }
        phoneTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                input = phoneTextField.getText();
                if (!input.isBlank() && input.length() > 6){
                    clientData.set(1, input);
                }
            }
        });


        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        addressTextField.setColumns(16);
        if (currentClient.address != null && !currentClient.address.isBlank()){
            addressTextField.setText(currentClient.address);
        }
        addressTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                input = addressTextField.getText();
                if (!input.isBlank()){
                    clientData.set(2, input);
                }
            }
        });


        saveButton = new JButton("Save");
        cancelButton = new JButton("Exit");

        saveButton.addActionListener(e -> saveButtonFunction());

        cancelButton.addActionListener(e -> cancelButtonFunction());
    }

    private void compose() {
        JPanel panel = new JPanel();

        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);

        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addGroup(l.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addGap(5)
//                        .addComponent(loginTextLabel)
                )
                .addGroup(l.createSequentialGroup()
                        .addComponent(defaultValuesLabel)
                )
                .addComponent(nameLabel)
                .addComponent(nameTextField)
                .addComponent(phoneLabel)
                .addComponent(phoneTextField)
                .addComponent(addressLabel)
                .addComponent(addressTextField)
                .addGroup(l.createSequentialGroup()
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                        .addComponent(loginLabel)
//                        .addComponent(loginTextLabel)
                )
                .addGap(15)
                .addGroup(l.createParallelGroup()
                        .addComponent(defaultValuesLabel)
                )
                .addGap(10)
                .addComponent(nameLabel)
                .addComponent(nameTextField)
                .addComponent(phoneLabel)
                .addComponent(phoneTextField)
                .addComponent(addressLabel)
                .addComponent(addressTextField)
                .addGroup(l.createParallelGroup()
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );
    }

    private void saveButtonFunction(){
        if (clientData.get(0) == null || clientData.get(0).isBlank()){
            Toast.show("Client name cannot be NULL");
        }

        clientData.set(0, nameTextField.getText());
        clientData.set(1, phoneTextField.getText());
        clientData.set(2, addressTextField.getText());
        currentClient.updateData(clientData);
        clientRepository.setOrderInfo(null);
        Toast.show("Data saved");
        frame.dispose();
        new ProfileScreenFrame();
    }

    private void cancelButtonFunction(){
        frame.dispose();
        new ClientCartScreenFrame();
    }
}