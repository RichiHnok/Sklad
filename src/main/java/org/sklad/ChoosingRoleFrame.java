package org.sklad;
import javax.swing.*;

import org.sklad.ProductManager.ProductManagerCatalogEditorScreenFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;

// import java.util.Date;

public class ChoosingRoleFrame {
    JFrame frame;

    private final int WIDTH = 450;
    private final int HEIGHT = 200;

    private JButton prodMangerButt = null;
    private JButton courierButt = null;
    private JButton clientButt = null;
    public ChoosingRoleFrame(){
        // System.out.println((new Date()).toString());

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
                courierButtonFunction();
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

    //    GroupLayout layout = new GroupLayout(frame.getContentPane());
    //    frame.getContentPane().setLayout(layout);
    //    layout.setAutoCreateGaps(true);
    //    layout.setAutoCreateContainerGaps(true);

    //    JLabel amountOfImagesLabel = new JLabel("label1");
    //    JTextField amountOfImagesTextField = new JTextField();
    //    JLabel timeLabel = new JLabel("label2");
    //    JTextField timeTextField = new JTextField();
    //    JLabel directoryLabel = new JLabel();
    //    JScrollPane scroller = new JScrollPane();
    //    JButton addDirectoryButton = new JButton("b1");
    //    JButton removeDirectoryButton = new JButton("b2");
    //    JButton acceptChangesButton = new JButton("b3");
    //    JButton cancelChangesButton = new JButton("b4");

    //    layout.setHorizontalGroup(layout.createParallelGroup(TRAILING)
    //            .addGroup(layout.createSequentialGroup()
    //                    .addGroup(layout.createParallelGroup(CENTER)
    //                            .addComponent(amountOfImagesLabel)
    //                         //    .addComponent(amountOfImagesTextField)
    //                            .addComponent(timeLabel)
    //                            .addGroup(layout.createSequentialGroup()
    //                                         //    .addComponent(timeTextField)
    //                                    // .addComponent(secondLabel)
    //                            )
    //                         //    .addComponent(directoryLabel)
    //                         //    .addGroup(layout.createSequentialGroup()
    //                         //            .addComponent(scroller)
    //                         //            .addGroup(layout.createParallelGroup()
    //                         //                    .addComponent(addDirectoryButton)
    //                         //                    .addComponent(removeDirectoryButton)
    //                         //            )
    //                         //    )
    //                    )
    //            )
    //         //    .addGroup(layout.createSequentialGroup()
    //         //            .addGroup(layout.createParallelGroup()
    //         //                    .addGroup(layout.createSequentialGroup()
    //         //                            .addComponent(acceptChangesButton)
    //         //                            .addComponent(cancelChangesButton)
    //         //                    )
    //         //            )
    //         //    )
    //    );

    // //    layout.linkSize(SwingConstants.HORIZONTAL, addDirectoryButton, removeDirectoryButton);
    // //    layout.linkSize(SwingConstants.HORIZONTAL, acceptChangesButton, cancelChangesButton);

    //    layout.setVerticalGroup(layout.createSequentialGroup()
    //            .addComponent(amountOfImagesLabel)
    //            .addGroup(layout.createParallelGroup(BASELINE)
    //                 //    .addComponent(amountOfImagesTextField)
    //            )
    //            .addComponent(timeLabel)
    //            .addGroup(layout.createParallelGroup(BASELINE)
    //                         //    .addComponent(timeTextField)
    //                    // .addComponent(secondLabel)
    //            )
    //         //    .addGroup(layout.createSequentialGroup()
    //         //            .addComponent(directoryLabel)
    //         //            .addGroup(layout.createParallelGroup(CENTER)
    //         //                    .addComponent(scroller)
    //         //                    .addGroup(layout.createSequentialGroup()
    //         //                            .addComponent(addDirectoryButton)
    //         //                            .addComponent(removeDirectoryButton)
    //         //                    )
    //         //            )
    //         //    )
    //         //    .addGroup(layout.createParallelGroup()
    //         //            .addComponent(acceptChangesButton)
    //         //            .addComponent(cancelChangesButton)
    //         //    )
    //    );
    }

    private void prodManagerButtFunction(){
        new ProductManagerCatalogEditorScreenFrame();
        frame.dispose();
    }

    private void courierButtonFunction(){
        new CourierScreenFrame();
        frame.dispose();
    }

    private void clientButtFunction(){
        frame.dispose();
        new LoginScreenFrame();
    }
}
