package org.sklad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;
 
public class ProfileScreenFrame {
	JFrame frame = new JFrame();

	private final int WIDTH = 450;
	private final int HEIGHT = 200;

	public ProfileScreenFrame() {
		// Создание окна
		frame = new JFrame("Choosing Role");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
