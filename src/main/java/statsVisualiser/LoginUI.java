package statsVisualiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    public actionListener 
    public static void main(String[] a) {
        // Creating object of LoginFrame class and setting some of its properties
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        GridLayout layout = new GridLayout(3,2);

        p.setLayout(layout);
        JButton login = new JButton();
        login.setText("Login");
        JLabel username = new JLabel("Username");
        JLabel password = new JLabel("Password");
        JTextField usernameField = new JTextField("", 20);
        JPasswordField passwordField = new JPasswordField();
        p.add(username);
        p.add(usernameField);
        p.add(password);
        p.add(passwordField);
        p.add(login);

        f.setTitle("Login Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setContentPane(p);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

}