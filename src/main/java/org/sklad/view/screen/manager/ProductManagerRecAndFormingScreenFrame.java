package org.sklad.view.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class ProductManagerRecAndFormingScreenFrame {

    private JFrame frame = new JFrame();

    private final int WIDTH = 800;
    private final int HEIGHT = 700;

    private JPanel productManagerToolBarPanel = null;
    private JPanel mainPanel = null;

    private Font anotherFont = new Font("Verdana", Font.BOLD, 12);
    private Font titleFont = new Font("Verdana", Font.BOLD, 16);

    public ProductManagerRecAndFormingScreenFrame(){
        createElements();
        compose();
    }

    private void createElements(){
        frame = new JFrame("Providers");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        productManagerToolBarPanel = new ProductManagerAppToolBarPanel(frame).getPanel();

        mainPanel = new MainPanel().getPanel();
        // mainPanel.add(scrollPane);
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
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(productManagerToolBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private class MainPanel{

        private JPanel panel = null;

        private JPanel recievingProvidersOrdersPanel = null;
        private JPanel formingClientsPackagesPanel = null;

        public MainPanel(){
            createElements();
            compose();
        }

        private void createElements(){
            panel = new JPanel();
            panel.setPreferredSize(new Dimension(750, 600));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            recievingProvidersOrdersPanel = new RecievingProvidersOrdersPanel().getPanel();

            formingClientsPackagesPanel = new FormingClientsPackagesPanel().getPanel();
        }

        private void compose(){
            GroupLayout l = new GroupLayout(panel);
            panel.setLayout(l);

            l.setAutoCreateGaps(true);
            l.setAutoCreateContainerGaps(true);

            l.setHorizontalGroup(l.createParallelGroup()
                    .addComponent(recievingProvidersOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(formingClientsPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );

            l.setVerticalGroup(l.createSequentialGroup()
                    .addComponent(recievingProvidersOrdersPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(formingClientsPackagesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            );
        }

        public JPanel getPanel(){
            return panel;
        }

        private class RecievingProvidersOrdersPanel{

            private JPanel panel = null;

            private JLabel titleOfPanelLabel = null;
            private JButton updatePanelButton = null;
            private JPanel recievedPackagesPanel = null;

            public RecievingProvidersOrdersPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(732, 285));
                panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));

                titleOfPanelLabel = new JLabel("Recieving packages from providers");
                titleOfPanelLabel.setFont(titleFont);

                updatePanelButton = new JButton("Update");
                updatePanelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        updatePanelButtonFunction();
                    }
                });

                JPanel panel1 = new JPanel();
                for(int i = 0; i < 3; i++){
                    panel1.add(new ProviderPackagePanel().getPanel());
                }
                GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
                // gridLayout.setColumns(2);
                panel1.setLayout(gridLayout);

                JScrollPane scrollPane = new JScrollPane(panel1);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                recievedPackagesPanel = new JPanel();
                recievedPackagesPanel.setLayout(new BorderLayout());
                recievedPackagesPanel.setPreferredSize(new Dimension(750, 200));
                recievedPackagesPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                recievedPackagesPanel.add(scrollPane);
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createParallelGroup(TRAILING)
                        .addGroup(l.createSequentialGroup()
                                .addComponent(titleOfPanelLabel)
                                .addGap(135)
                                .addComponent(updatePanelButton)
                        )
                        .addComponent(recievedPackagesPanel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(CENTER)
                                .addComponent(titleOfPanelLabel)
                                .addComponent(updatePanelButton)
                        )
                        .addComponent(recievedPackagesPanel)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

            private void updatePanelButtonFunction(){

            }

            private class ProviderPackagePanel{
                private JPanel panel = null;

                private JLabel providerNameLabel = null;
                private JLabel messageLabel = null;
                private JLabel storageOrderIdLabel = null;

                private JButton acceptButton = null;
                private JButton showPackageContentButton = null;

                public ProviderPackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(100, 150));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

                    providerNameLabel = new JLabel("Chingiz");
                    providerNameLabel.setFont(anotherFont);
                    messageLabel = new JLabel(" delivered order# ");

                    storageOrderIdLabel = new JLabel("z000");
                    storageOrderIdLabel.setFont(anotherFont);

                    acceptButton = new JButton("Accept");

                    showPackageContentButton = new JButton("Show content");
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup(CENTER)
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(providerNameLabel)
                                    .addComponent(messageLabel)
                                    .addComponent(storageOrderIdLabel)
                            )
                            .addGroup(l.createSequentialGroup()
                                    .addComponent(acceptButton)
                                    .addComponent(showPackageContentButton)
                            )
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                            .addGroup(l.createParallelGroup()
                                    .addComponent(providerNameLabel)
                                    .addComponent(messageLabel)
                                    .addComponent(storageOrderIdLabel)
                            )
                            .addGroup(l.createParallelGroup()
                                    .addComponent(acceptButton)
                                    .addComponent(showPackageContentButton)
                            )
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }
            }
        }

        private class FormingClientsPackagesPanel{

            private JPanel panel = null;

            private JLabel titleOfPanelLabel = null;
            private JPanel formedPackagesPanel = null;

            public FormingClientsPackagesPanel(){
                createElements();
                compose();
            }

            private void createElements(){
                panel = new JPanel();
                panel.setPreferredSize(new Dimension(732, 285));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

                titleOfPanelLabel = new JLabel("Forming packages for clients");
                titleOfPanelLabel.setFont(titleFont);

                JPanel panel1 = new JPanel();
                for(int i = 0; i < 5; i++){
                    panel1.add(new PackagePanel().getPanel());
                }
                GridLayout gridLayout = new GridLayout(0,2,10,10);
                panel1.setLayout(gridLayout);

                JScrollPane scrollPane = new JScrollPane(panel1);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                formedPackagesPanel = new JPanel();
                formedPackagesPanel.setLayout(new BorderLayout());
                formedPackagesPanel.setPreferredSize(new Dimension(730, 200));
                formedPackagesPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
                formedPackagesPanel.add(scrollPane);
            }

            private void compose(){
                GroupLayout l = new GroupLayout(panel);
                panel.setLayout(l);

                l.setHorizontalGroup(l.createParallelGroup()
                        .addComponent(titleOfPanelLabel)
                        .addComponent(formedPackagesPanel)
                );

                l.setVerticalGroup(l.createSequentialGroup()
                        .addComponent(titleOfPanelLabel)
                        .addComponent(formedPackagesPanel)
                );
            }

            public JPanel getPanel(){
                return panel;
            }

            private class PackagePanel{
                private JPanel panel = null;

                private JButton cancelFormingButton = null;

                public PackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(100, 200));
                    panel.setBorder(BorderFactory.createLineBorder(new Color(130,50,180), 2));

                    cancelFormingButton = new JButton("Cancel forming");
                }

                private void compose(){
                    panel.setLayout(new GridBagLayout());

                    JPanel panel1 = new JPanel();
                    GroupLayout l = new GroupLayout(panel1);
                    panel1.setLayout(l);

                    panel.add(panel1);

                    l.setHorizontalGroup(l.createParallelGroup()
                            .addComponent(cancelFormingButton)
                    );

                    l.setVerticalGroup(l.createSequentialGroup()
                            .addComponent(cancelFormingButton)
                    );
                }

                public JPanel getPanel(){
                    return panel;
                }
            }

            private class AddPackagePanel{
                private JPanel panel = null;

                public AddPackagePanel(){
                    createElements();
                    compose();
                }

                private void createElements(){
                    panel = new JPanel();
                    panel.setPreferredSize(new Dimension(100, 100));
                    panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                }

                private void compose(){
                    GroupLayout l = new GroupLayout(panel);
                    panel.setLayout(l);

                    l.setHorizontalGroup(l.createSequentialGroup()

                    );

                    l.setVerticalGroup(l.createParallelGroup(CENTER)

                    );
                }

                public JPanel getPanel(){
                    return panel;
                }
            }
        }
    }
}