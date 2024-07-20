package org.sklad.view.screen;
import org.sklad.view.screen.client.login.LoginScreenFrame;
import org.sklad.view.screen.courier.CourierScreenFrame;
import org.sklad.view.screen.manager.ProductManagerCatalogEditorScreenFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class ChoosingRoleFrame {
    JFrame frame;

    private final int WIDTH = 450;
    private final int HEIGHT = 200;

    private JButton prodMangerButt = null;
    private JButton courierButt = null;
    private JButton clientButt = null;
    public ChoosingRoleFrame(){
        // Создание окна
        frame=new JFrame("Choosing Role");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

		// Создание элементов окна
        JLabel message = new JLabel("Choose role");

        prodMangerButt = new JButton("Prod-Manager");
        courierButt = new JButton("Courier");
        clientButt = new JButton("Client");

        prodMangerButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prodManagerButtFunction();
            }
        });

        courierButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courierButtFunction();
            }
        });

        clientButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientButtFunction();
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
                .addComponent(message)
            )
            .addGroup(layout.createSequentialGroup()
            .addComponent(prodMangerButt)
            .addComponent(courierButt)
            .addComponent(clientButt)
            )
        );
        layout.linkSize(SwingConstants.HORIZONTAL, prodMangerButt, courierButt, clientButt);
        
        layout.setVerticalGroup(layout.createParallelGroup(CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(50)
                .addComponent(message)
                .addGap(50)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(prodMangerButt)
                    .addComponent(courierButt)
                    .addComponent(clientButt)
                )
            )
        );
    }

    private void prodManagerButtFunction(){
        frame.dispose();
        new ProductManagerCatalogEditorScreenFrame();
    }

    private void courierButtFunction(){
        frame.dispose();
        new CourierScreenFrame();
    }

    private void clientButtFunction(){
        frame.dispose();
        new LoginScreenFrame();
    }
}
