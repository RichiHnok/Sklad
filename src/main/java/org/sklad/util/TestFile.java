package org.sklad.util;

import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;
import java.awt.*;

public class TestFile {
	private JFrame frame;
	private final int WIDTH = 600;
	private final int HEIGHT = 500;
	
	public TestFile(){
		frame=new JFrame("Test");

		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        for (int i = 0; i < 20; i++) {
            panel1.add(new JButton("Hello-" + i));
        }
		// panel.add(panel1);
		JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 600, 500);
		// scrollPane.add(panel);
        // panel.setPreferredSize(new Dimension(500, 400));
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane);
		frame.getContentPane().add(panel);
		frame.pack();
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


		 

		// JButton b1 = new JButton("aaa");
		// JButton b2 = new JButton("b---2");

		// // Компоновка элементов
		// JPanel panel = new JPanel();
		// panel.setSize(new Dimension(300, 50));
		// panel.setPreferredSize(new Dimension(300, 50));
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// JPanel panel2 = new JPanel();
		// panel2.setPreferredSize(new Dimension(750, 50));
		// panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Container cont = frame.getContentPane();
		// GroupLayout gl = new GroupLayout(cont);
		// cont.setLayout(gl);

		// gl.setAutoCreateGaps(true);
		// gl.setAutoCreateContainerGaps(true);

		// gl.setHorizontalGroup(gl.createParallelGroup(TRAILING)
		// 	// .addGroup(gl.createSequentialGroup()
		// 		.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		// 		.addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		// 	// )
		// );
		
		// gl.setVerticalGroup(gl.createParallelGroup(CENTER)
		// 	.addGroup(gl.createSequentialGroup()
		// 		// .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		// 		.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		// 		.addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		// 	)
		// );

		// GroupLayout layout = new GroupLayout(panel);
		// panel.setLayout(layout);
		// // layout.setAutoCreateGaps(true);
		// // layout.setAutoCreateContainerGaps(true);

		// layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
		// 	.addGroup(layout.createSequentialGroup()
		// 		.addComponent(b1)
		// 	)
		// );

		// layout.setVerticalGroup(layout.createParallelGroup(CENTER)
		// 	.addGroup(layout.createSequentialGroup()
		// 		.addComponent(b1)
		// 	)
		// );

	}
}

// private class ProductPanel{
		
// 		private JPanel mainPanel = null;

// 		private String productName = null;
// 		private JLabel productImageLabel = null;
// 		private JLabel productDescriptionLabel = null;
// 		private JLabel productAvailableAmountLabel = null;
// 		private JLabel productPricePerPieceLabel = null;
// 		private JLabel choosedAmountLabel = null;
// 		private JLabel priceTotalLabel = null;
// 		private JButton plusPieceButton = null;
// 		private JButton minusPieceButton = null;
// 		private JButton addToCartButton = null;

// 		private static Product testProduct1 = null;
// 		private static Product testProduct2 = null;
// 		private static Product testProduct3 = null;

// 		// Placeholder
// 		public ProductPanel(){

// 			mainPanel.setBorder(BorderFactory.createLineBorder(Color.PINK));

// 			productName = new String("name placeHolder");
			
// 			productImageLabel = new JLabel();
// 			Image image = Toolkit.getDefaultToolkit().createImage("imagePlaceHolder");
// 			productImageLabel.setIcon(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
// 			productImageLabel.setPreferredSize(new Dimension(50, 50));

// 			productDescriptionLabel = new JLabel("descriptionPlaceholder");
// 			productAvailableAmountLabel = new JLabel("10");
// 			productPricePerPieceLabel = new JLabel("1");
// 			choosedAmountLabel = new JLabel("0");
// 			priceTotalLabel = new JLabel("0");

// 			minusPieceButton = new JButton("-");
// 			plusPieceButton = new JButton("+");
// 			addToCartButton = new JButton("Add to cart");

// 			minusPieceButton.addActionListener(new ActionListener() {
// 				@Override
// 				public void actionPerformed(ActionEvent e){
// 					minusPieceButtonFunction();
// 				}
// 			});

// 			plusPieceButton.addActionListener(new ActionListener() {
// 				@Override
// 				public void actionPerformed(ActionEvent e){
// 					plusPieceButtonFunction();
// 				}
// 			});

// 			addToCartButton.addActionListener(new ActionListener() {
// 				@Override
// 				public void actionPerformed(ActionEvent e){
// 					addToCartButtonFunction();
// 				}
// 			});

// 			JPanel panel = new JPanel();
// 			GroupLayout layout = new GroupLayout(mainPanel);
// 			mainPanel.setLayout(new GridBagLayout());
// 			mainPanel.add(panel);

// 			layout.setHorizontalGroup(layout.createSequentialGroup()
// 				.addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
// 			);

// 			layout.setVerticalGroup(layout.createParallelGroup()
// 				.addComponent(productImageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)				
// 			);
// 		}

// 		// Actual Constructor
// 		public ProductPanel(Product panel){

// 		}

// 		public JPanel getPanel(){
// 			return mainPanel;
// 		} 

// 		private void minusPieceButtonFunction(){

// 		}

// 		private void plusPieceButtonFunction(){

// 		}

// 		private void addToCartButtonFunction(){

// 		}
// 	}
