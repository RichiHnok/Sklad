package org.sklad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.*;
 
public class ProfileScreenFrame {
	JFrame frame = new JFrame();

	private final int WIDTH = 250;
	private final int HEIGHT = 400;

	private JLabel loginLabel = null;
	private JLabel loginTextLabel= null;

	private JLabel defaultValuesLabel = null;
    private JLabel nameLabel = null;
    private JTextField nameTextField = null;
    private JLabel phoneLabel = null;
    private JTextField phonTextField = null;
    private JLabel addressLabel = null;
    private JTextField addressTextField = null;

    private JButton saveButton = null;
    private JButton cancelButton = null;

	public ProfileScreenFrame() {
		createElements();
		compose();
	}

	private void createElements() {
		frame = new JFrame("Profile");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

        loginLabel = new JLabel("Login:");

        loginTextLabel = new JLabel("Placeholder");
        // loginTextLabel.setColumns(16);

        defaultValuesLabel = new JLabel("Default values:");
        defaultValuesLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        nameLabel = new JLabel("Name:");

        nameTextField = new JTextField();
        nameTextField.setColumns(16);

        phoneLabel = new JLabel("Phone number:");

        phonTextField = new JTextField("");
        phonTextField.setColumns(16);

        addressLabel = new JLabel("Address:");

        addressTextField = new JTextField();
        addressTextField.setColumns(16);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                saveButtonFunction();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cancelButtonFunction();
            }
        });
	}

	private void compose() {
		JPanel panel = new JPanel();

		Container cont = frame.getContentPane();
		cont.setLayout(new GridBagLayout());
		frame.add(panel);

		GroupLayout l = new GroupLayout(panel);
		panel.setLayout(l);

		// layout.setAutoCreateGaps(true);
		// layout.setAutoCreateContainerGaps(true);

		l.setHorizontalGroup(l.createParallelGroup(CENTER)
            .addGroup(l.createSequentialGroup()
                .addComponent(loginLabel)
                .addGap(5)
                .addComponent(loginTextLabel)            
            )
            .addGroup(l.createSequentialGroup()
                .addComponent(defaultValuesLabel)
            )
            .addComponent(nameLabel)
            .addComponent(nameTextField)
            .addComponent(phoneLabel)
            .addComponent(phonTextField)
            .addComponent(addressLabel)
            .addComponent(addressTextField)
            .addGroup(l.createSequentialGroup()
                .addComponent(saveButton)
                .addComponent(cancelButton)
            )
		);

		l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(loginLabel)
                .addComponent(loginTextLabel)
            )
            .addGap(15)
            .addGroup(l.createParallelGroup()
                .addComponent(defaultValuesLabel)
            )
            .addGap(10)
            .addComponent(nameLabel)
            .addComponent(nameTextField)
            .addComponent(phoneLabel)
            .addComponent(phonTextField)
            .addComponent(addressLabel)
            .addComponent(addressTextField)
            .addGroup(l.createParallelGroup()
                .addComponent(saveButton)
                .addComponent(cancelButton)
            )
		);
	}

    private void saveButtonFunction(){
        frame.dispose();
    }

    private void cancelButtonFunction(){
        frame.dispose();
    }
}
