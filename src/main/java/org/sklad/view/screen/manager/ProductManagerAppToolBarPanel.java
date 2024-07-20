package org.sklad.view.screen.manager;

import org.sklad.model.Provider;
import org.sklad.repository.ManagerRepo;
import org.sklad.view.screen.ChoosingRoleFrame;
import org.sklad.view.screen.manager.addproduct.ProductManagerStorageOrdersScreenFrame;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
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
    ManagerRepo repo = new ManagerRepo();

    public ProductManagerAppToolBarPanel(JFrame frame){
        this.frame = frame;


        // Создание элементов панели
        toolBarPanel = new JPanel();
        toolBarPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        toolBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        clientsOrdersScreenButton = new JButton("Clients orders");
        recieveAndFormingScreenButton = new JButton("rec&forming");
        storageOrdersScreenButton = new JButton("storage orders");
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
                receiveAndFormingScreenButtonFunction();
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
            repo.setCurrentProvider(null);
            frame.dispose();
            new ProductManagerOrdersScreenFrame();
        }
    }

    private void receiveAndFormingScreenButtonFunction(){
        if(!(frame.getTitle().equals("Recieving and Forming Packages"))){
            repo.setCurrentProvider(null);
            frame.dispose();
            new ProductManagerRecAndFormingScreenFrame();
        }
    }

    private void storageOrdersScreenButtonFunction(){
        if(!(frame.getTitle().equals("Storage ordering"))){
            frame.dispose();
            new ProductManagerStorageOrdersScreenFrame();
        }
    }

    private void providersScreeButtonFunction(){
        repo.setCurrentProvider(null);
        if(!(frame.getTitle().equals("Providers"))){
            frame.dispose();
            new ProductManagerProvidersScreenFrame();
        }
    }

    private void catalogEditorButtonFunction(){
        repo.setCurrentProvider(null);
        if(!(frame.getTitle().equals("Catalog Editor"))){
            frame.dispose();
            new ProductManagerCatalogEditorScreenFrame();
        }
    }

    private void exiButtonFunction(){
        repo.setCurrentProvider(null);
        frame.dispose();
        new ChoosingRoleFrame();
    }

    public JPanel getPanel(){
        return toolBarPanel;
    }
}