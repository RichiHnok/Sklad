package org.sklad.view.screen.client.frames;
import javax.swing.*;

public class ClientProfileScreenFrame {
	JFrame frame = new JFrame();

	private final int WIDTH = 450;
	private final int HEIGHT = 200;

	public ClientProfileScreenFrame() {
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
