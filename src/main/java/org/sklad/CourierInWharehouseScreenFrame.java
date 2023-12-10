package org.sklad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class CourierInWharehouseScreenFrame {
    JFrame frame = new JFrame();

	private final int WIDTH = 400;
	private final int HEIGHT = 300;

    private JPanel preparedPackagesPanel = null;
    private JLabel preparedPackagesLabel = null;
    private JLabel orderTextLabel = null;
    private JLabel addressTextLabel = null;
    private JLabel deliveryDateTextLabel = null;
    private JLabel choosedPackageLabel = null;

    private JButton exitButton = null;

    public CourierInWharehouseScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Courier in wharehouse");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

        preparedPackagesPanel = new JPanel();
        preparedPackagesPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        preparedPackagesPanel.setPreferredSize(new Dimension(400, 200)); 
        preparedPackagesPanel.setLayout(new BorderLayout());
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        for(int i = 0; i < 8; i++){
            panel1.add(new PreparedPackagePanel().getPanel());
			panel1.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, preparedPackagesPanel.getWidth(), preparedPackagesPanel.getHeight());
		
		preparedPackagesPanel.setLayout(new BorderLayout());
		preparedPackagesPanel.add(scrollPane);
		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        preparedPackagesLabel = new JLabel("Prepared packages");

        orderTextLabel = new JLabel("OrderId:");

        addressTextLabel = new JLabel("Address:");

        deliveryDateTextLabel = new JLabel("Delivery date:");

        exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                exitButtonFunction();
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

        l.setHorizontalGroup(l.createParallelGroup(CENTER)
            .addComponent(preparedPackagesLabel)
            // .addGroup(l.createSequentialGroup()
            //     .addComponent(orderTextLabel)
            //     .addGap(20)
            //     .addComponent(addressTextLabel)
            //     .addGap(20)
            //     .addComponent(deliveryDateTextLabel)
            //     .addGap(20)
            // )
            .addComponent(preparedPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(exitButton)
        );

        l.setVerticalGroup(l.createSequentialGroup()
            .addComponent(preparedPackagesLabel)
            .addGap(15)
            // .addGroup(l.createParallelGroup()
            //     .addComponent(orderTextLabel)
            //     .addComponent(addressTextLabel)
            //     .addComponent(deliveryDateTextLabel)
            // )
            .addComponent(preparedPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(exitButton)
        );
    }

    private void exitButtonFunction(){
        frame.dispose();
        new CourierScreenFrame();
    }

    private class PreparedPackagePanel{
        
        private JPanel panel = null;

        private JLabel packageOrderLabel = null;
        private JLabel clientAddressLabel = null;
        private JLabel deliveryDateLabel = null;
        private JButton choosePackageButton = null;

        public PreparedPackagePanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));

            packageOrderLabel = new JLabel("z000");

            clientAddressLabel = new JLabel("city");

            deliveryDateLabel = new JLabel("29.12.2000");

            choosePackageButton = new JButton("Choose");

            choosePackageButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    choosePackageButtonFunction();
                }
            });
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setHorizontalGroup(l.createSequentialGroup()
                .addGap(30)
                .addComponent(packageOrderLabel)
                .addGap(30)
                .addComponent(clientAddressLabel)
                .addGap(30)
                .addComponent(deliveryDateLabel)
                .addGap(30)
                .addComponent(choosePackageButton)
            );

            l.setVerticalGroup(l.createParallelGroup()
                .addComponent(packageOrderLabel)
                .addComponent(clientAddressLabel)
                .addComponent(deliveryDateLabel)
                .addComponent(choosePackageButton)          
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private void choosePackageButtonFunction(){

        }
    }
}
