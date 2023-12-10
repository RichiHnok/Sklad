package org.sklad;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ещё не доделан
public class CouriersPackageContentScreenFrame {
    private JFrame frame = null;

    private final int WIDTH = 450;
    private final int HEIGHT = 450;

    private JLabel orderIdLabel = null;
    private JPanel productsInPackagePanel = null;
    private JTable productsInPackagTable = null;
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

        for(int i = 0; i < 5; i++){
            JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
            JLabel productNameLabel = new JLabel();
            // я остановился здесь
        }

        productsInPackagePanel = new JPanel();
        productsInPackagePanel.setPreferredSize(new Dimension(400, 200));
        productsInPackagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

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
}
