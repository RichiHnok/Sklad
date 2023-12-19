package org.sklad.view.screen.manager;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;

import org.sklad.view.screen.ChoosingRoleFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductManagerAppToolBarPanel {

    private JFrame frame = null;

    private JPanel toolBarPanel = null;
    private final int WIDTH = 750;
    private final int HEIGHT = 50;

    // private JButton catalogScreenButton = null;
    // private JButton cartScreenButton = null;
    // private JButton ordersScreenButton = null;
    // private JButton profileScreenButton = null;
    // private JButton exitButton = null;
    private JButton clientsOrdersScreenButton = null;
    private JButton recieveAndFormingScreenButton = null;
    private JButton storageOrdersScreenButton = null;
    private JButton providersScreeButton = null;
    private JButton catalogEditorButton = null;
    private JButton exiButton = null;

    public ProductManagerAppToolBarPanel(JFrame frame){
        this.frame = frame;

        // Создание элементов панели
        toolBarPanel = new JPanel();
        toolBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        toolBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        clientsOrdersScreenButton = new JButton("Cl-ts orders");
        recieveAndFormingScreenButton = new JButton("rec&forming");
        storageOrdersScreenButton = new JButton("st-ge orders");
        providersScreeButton = new JButton("Providers");
        catalogEditorButton = new JButton("Catalog editor");
        exiButton = new JButton("Exit");

        clientsOrdersScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientsOrdersScreenButtonFunction();
            }
        });

        recieveAndFormingScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recieveAndFormingScreenButtonFunction();
            }
        });

        storageOrdersScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storageOrdersScreenButtonFunction();
            }
        });

        providersScreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                providersScreeButtonFunction();
            }
        });

        catalogEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogEditorButtonFunction();
            }
        });

        exiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exiButtonFunction();
            }
        });


        JPanel panel0 = new JPanel();
        toolBarPanel.add(panel0);
        GroupLayout tbLayout = new GroupLayout(panel0);
        panel0.setLayout(tbLayout);
        panel0.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        // tbLayout.setAutoCreateGaps(true);
        // tbLayout.setAutoCreateContainerGaps(true);

        tbLayout.setHorizontalGroup(tbLayout.createSequentialGroup()
                .addGap(1)
                .addComponent(clientsOrdersScreenButton)
                .addComponent(recieveAndFormingScreenButton)
                .addComponent(storageOrdersScreenButton)
                .addComponent(providersScreeButton)
                .addComponent(catalogEditorButton)
                .addComponent(exiButton)
        );

        tbLayout.setVerticalGroup(tbLayout.createSequentialGroup()
                .addGroup(tbLayout.createParallelGroup(CENTER)
                        .addGap(1)
                        .addComponent(clientsOrdersScreenButton)
                        .addComponent(recieveAndFormingScreenButton)
                        .addComponent(storageOrdersScreenButton)
                        .addComponent(providersScreeButton)
                        .addComponent(catalogEditorButton)
                        .addComponent(exiButton)
                )
        );
    }

    private void clientsOrdersScreenButtonFunction(){
        if(!(frame.getTitle().equals("Clients orders"))){
            new ProductManaderOrdersScreenFrame();
            frame.dispose();
        }
    }

    private void recieveAndFormingScreenButtonFunction(){
        if(!(frame.getTitle().equals(""))){

        }
    }

    private void storageOrdersScreenButtonFunction(){
        if(!(frame.getTitle().equals(""))){

        }
    }

    private void providersScreeButtonFunction(){
        if(!(frame.getTitle().equals("Providers"))){
            new ProductManagerProvidersScreenFrame();
            frame.dispose();
        }
    }

    private void catalogEditorButtonFunction(){
        if(!(frame.getTitle().equals("Catalog Editor"))){
            new ProductManagerCatalogEditorScreenFrame();
            frame.dispose();
        }
    }

    private void exiButtonFunction(){
        new ChoosingRoleFrame();
        frame.dispose();
    }

    public JPanel getPanel(){
        return toolBarPanel;
    }
}
