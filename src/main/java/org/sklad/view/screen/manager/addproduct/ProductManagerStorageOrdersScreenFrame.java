package org.sklad.view.screen.manager.addproduct;

import org.sklad.view.screen.manager.ProductManagerAppToolBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerStorageOrdersScreenFrame {

    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 700;

    private JPanel productManagerToolBarPanel = null;

    private JPanel orderingPanel = null;

    private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private Font titleFont = new Font("Verdana", Font.BOLD, 18);

    public ProductManagerStorageOrdersScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Storage ordering");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();
        orderingPanel = new OrderingPanel().getPanel();
    }

    private void compose(){
        JPanel panel = new JPanel();
        Container cont = frame.getContentPane();
        cont.setLayout(new GridBagLayout());
        frame.add(panel);

        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);
        l.setAutoCreateGaps(true);
        l.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER, false)
                .addComponent(productManagerToolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(orderingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        );
    }

    private class OrderingPanel{

        private JPanel panel = null;

        private JPanel storageOrdersPanel = null;
        private JPanel makeStorageOrderPanel = null;

        public OrderingPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(750, 400));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            panel.setBackground(new Color(70, 70, 70));

            storageOrdersPanel = new StorageOrdersPanel(frame).getPanel();

            makeStorageOrderPanel = new MakeStorageOrderPanel(frame).getPanel();
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                    .addComponent(storageOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeStorageOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(storageOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(20)
                    .addComponent(makeStorageOrderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

        }

        public JPanel getPanel(){
            return panel;
        }


    }
}