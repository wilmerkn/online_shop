package view;

import javax.swing.*;

public class RegisterForm extends JFrame {
    private JPanel registerPanel;
    private JTextField textField1;
    private JButton registerButton;

    public RegisterForm() {
        super("Register Account");
        this.setSize(400,350);
        //this.setResizable(false);
        this.setContentPane(registerPanel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(false);
    }
}
