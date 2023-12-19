package org.sklad;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CouriersPackageContentScreenFrame {
    private JFrame frame = null;

    private final int WIDTH = 450;
    private final int HEIGHT = 450;

    private JLabel orderIdLabel = null;
    private JPanel productsInPackagePanel = null;
    private JButton closeButton = null;

    public CouriersPackageContentScreenFrame(){
        createElements();
        compose();
    }
    
    private void createElements(){
        frame = new JFrame("Couriers package content");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        orderIdLabel = new JLabel("Order z000");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        for(int i = 0; i < 12; i++){
            panel1.add(new ProductInPackage().getPanel());
            panel1.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        productsInPackagePanel = new JPanel();
        productsInPackagePanel.setPreferredSize(new Dimension(400, 200));
        productsInPackagePanel.setLayout(new BorderLayout());
        productsInPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        productsInPackagePanel.add(scrollPane);

        closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                closeButtonFunction();
            }
        });
    }

    private void compose(){
        JPanel panel1 = new JPanel();

		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		frame.add(panel1);

		GroupLayout l = new GroupLayout(panel1);
		panel1.setLayout(l);
		// layout.setAutoCreateGaps(true);
		// layout.setAutoCreateContainerGaps(true);

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
            .addComponent(orderIdLabel)
            .addComponent(productsInPackagePanel)
            .addComponent(closeButton)
        );

        l.setVerticalGroup(l.createSequentialGroup()
            .addComponent(orderIdLabel)
            .addComponent(productsInPackagePanel)
            .addComponent(closeButton)
        );
    }

    private void closeButtonFunction(){
        frame.dispose();
    }

    private class ProductInPackage {
        
        private JPanel panel = null;

        private JLabel productIdLabel = null;
        private JLabel productNameLabel = null;
        private JLabel productAmountLabel = null;

        public ProductInPackage(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            productIdLabel = new JLabel("product id");

            productNameLabel = new JLabel("product name");

            productAmountLabel = new JLabel("product amount");
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                .addComponent(productIdLabel)
                .addGap(15)
                .addComponent(productNameLabel)
                .addGap(15)
                .addComponent(productAmountLabel)
            );

            l.setVerticalGroup(l.createParallelGroup()
                .addComponent(productIdLabel)
                .addGap(15)
                .addComponent(productNameLabel)
                .addGap(15)
                .addComponent(productAmountLabel)
            );
        }

        public JPanel getPanel(){
            return panel;
        }
    }
}
