package org.sklad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

public class CourierScreenFrame {
    JFrame frame = new JFrame();

	private final int WIDTH = 400;
	private final int HEIGHT = 250;

    private JButton goToWarehouseButton = null;
    private JButton deliverPackageButton = null;
    private JButton exiButton = null;

    private boolean isPackageChoosed = true;

    private JPanel packagePanel = null;

    public CourierScreenFrame(){
        createElements();
        compose();
    }

    public void createElements(){
        frame = new JFrame("Courier");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

        goToWarehouseButton = new JButton("Go to warehouse");
        deliverPackageButton = new JButton("Deliver package");
        exiButton = new JButton("Exit");

        goToWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                goToWarehouseButtonFunction();
            }
        });

        deliverPackageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                deliverPackageButtonFunction();
            }
        });

        exiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                exitButtonFunction();
            }
        });

        packagePanel = new JPanel();
        packagePanel.setLayout(new BorderLayout());
        if(isPackageChoosed){
            packagePanel.add(new PackagePanel().getPanel());        
        }else{
            JLabel packageNotChoosedLabel = new JLabel("Package not choosed");
            packageNotChoosedLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            packageNotChoosedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));            
            packagePanel.add(packageNotChoosedLabel);
        }
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

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(goToWarehouseButton)
                .addComponent(deliverPackageButton)
                .addComponent(exiButton)
            )
            .addComponent(packagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.linkSize(SwingConstants.HORIZONTAL, goToWarehouseButton, deliverPackageButton, exiButton);

        layout.setVerticalGroup(layout.createParallelGroup(CENTER)
            .addGroup(layout.createSequentialGroup()
                .addComponent(goToWarehouseButton)
                .addComponent(deliverPackageButton)
                .addComponent(exiButton)
            )
            .addComponent(packagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private class PackagePanel{
        private JPanel panel = null;

        private JLabel choosedPackageLabel = null;
        private Package choosedPackage = new Package();

        private JLabel packageOrderTextLabel = null;
        private JLabel packageOrderValueLabel = null;
        private JLabel deliveryAddressTextLabel = null;
        private JLabel deliveryAddressValueLabel = null;
        private JLabel deliveryDateTextLabel = null;
        private JLabel deliveryDateValueLabel = null;

        private Font anotherFont = new Font("Verdana", Font.BOLD, 12);

        public PackagePanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(200, 200));
            // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            choosedPackageLabel = new JLabel("Choosed package");
            choosedPackageLabel.setFont(new Font("Verdana", Font.BOLD, 16));
            choosedPackageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            
            packageOrderTextLabel = new JLabel("Order ID:");
            packageOrderTextLabel.setFont(anotherFont);
            packageOrderValueLabel = new JLabel(choosedPackage.getPackageOrder());

            deliveryAddressTextLabel = new JLabel("Address:");
            deliveryAddressTextLabel.setFont(anotherFont);
            deliveryAddressValueLabel = new JLabel(choosedPackage.getClientAddress());

            deliveryDateTextLabel = new JLabel("Deliver date:");
            deliveryDateTextLabel.setFont(anotherFont);
            deliveryDateValueLabel = new JLabel(choosedPackage.getDeliveryDate());
        }

        private void compose(){
            JPanel panel1 = new JPanel();

            panel.setLayout(new GridBagLayout());
            panel.add(panel1);

            GroupLayout l = new GroupLayout(panel1);
            panel1.setLayout(l);

            l.setHorizontalGroup(l.createParallelGroup(CENTER)
                .addComponent(choosedPackageLabel)
                .addGroup(l.createSequentialGroup()
                    .addComponent(packageOrderTextLabel)
                    .addGap(10)
                    .addComponent(packageOrderValueLabel)
                )
                .addGroup(l.createSequentialGroup()
                    .addComponent(deliveryAddressTextLabel)
                    .addGap(10)
                    .addComponent(deliveryAddressValueLabel)
                )
                .addGroup(l.createSequentialGroup()
                    .addComponent(deliveryDateTextLabel)
                    .addGap(10)
                    .addComponent(deliveryDateValueLabel)
                )
            );

            l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(choosedPackageLabel)
                .addGap(15)
                .addGroup(l.createParallelGroup()
                    .addComponent(packageOrderTextLabel)
                    .addComponent(packageOrderValueLabel)
                )
                .addGap(10)
                .addGroup(l.createParallelGroup()
                    .addComponent(deliveryAddressTextLabel)
                    .addComponent(deliveryAddressValueLabel)
                )
                .addGap(10)
                .addGroup(l.createParallelGroup()
                    .addComponent(deliveryDateTextLabel)        
                    .addComponent(deliveryDateValueLabel)                
                )
                .addGap(20)
            );
        }

        public JPanel getPanel(){
            return panel;
        }
    }

    public void goToWarehouseButtonFunction(){
        frame.dispose();
        new CourierInWharehouseScreenFrame();
    }

    public void deliverPackageButtonFunction(){
        new CourierDeliveringPackagesScreenFrame();
        frame.dispose();
    }

    public void exitButtonFunction(){
        new ChoosingRoleFrame();
        frame.dispose();
    }
}
