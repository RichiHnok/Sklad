package org.sklad.view.screen.client.courier;

import org.sklad.view.screen.client.frames.ClientScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThereIsNoCourierScreenFrame {

    private JFrame frame = null;

    private final int WIDTH = 450;
    private final int HEIGHT = 200;

    private JLabel noCourierMessageLabel = null;
    private JButton acceptButton = null;

    public ThereIsNoCourierScreenFrame(){
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

        noCourierMessageLabel = new JLabel("There is no courier");
        noCourierMessageLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        acceptButton = new JButton("Back");

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                acceptButtonFunction();
            }
        });
    }

    private void compose(){
        JPanel panel = new JPanel();

        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
                .addComponent(noCourierMessageLabel)
                .addComponent(acceptButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(noCourierMessageLabel)
                .addComponent(acceptButton)
        );
    }

    private void acceptButtonFunction(){
        frame.dispose();
        new ClientScreenFrame();
    }

}