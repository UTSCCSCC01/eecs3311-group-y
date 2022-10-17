package statsVisualiser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class LoginUI implements ActionListener {
    public static JFrame f = new JFrame();
    public static JPanel p = new JPanel();
    public static JButton login = new JButton();
    public static JButton signup = new JButton();
    public static JLabel username = new JLabel("Username");
    public static JLabel password = new JLabel("Password");
    public static JTextField usernameField = new JTextField("", 20);
    public static JPasswordField passwordField = new JPasswordField();

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login) { 
            loginUser(); 
        }else if(e.getSource()==signup) { 
            signupUser(); 
        }
    }

    public void loginUser(){
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());
        Pattern userPattern = Pattern.compile("^[a-zA-Z0-9_]{3,}$", Pattern.CASE_INSENSITIVE);
        Matcher userMatcher = userPattern.matcher(user);
        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
        Matcher passMatcher = passPattern.matcher(pass);
        if (userMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid username. Username msut be at least 3 characters long and can only contain letters, numbers and underscores.");

        } else if (passMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid password. Password must be between 8 and 20 characters long and contain at least one uppercase letter, one lowercase letter and one number.");
        } else {
            try {
                checkPassword(user, pass);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void signupUser(){ 
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());
        Pattern userPattern = Pattern.compile("^[a-zA-Z0-9_]{3,}$", Pattern.CASE_INSENSITIVE);
        Matcher userMatcher = userPattern.matcher(user);
        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
        Matcher passMatcher = passPattern.matcher(pass);
        if (userMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid username. Username msut be at least 3 characters long and can only contain letters, numbers and underscores.");

        } else if (passMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid password. Password must be between 8 and 20 characters long and contain at least one uppercase letter, one lowercase letter and one number.");
        } else {
            try {
                createUser(user, pass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void createUser(String user, String pass) throws IOException {
        HashMap<String, String> users = readUsers(); // read users from csv file 
        if (users.containsKey(user)) { // check if user already exists
            JOptionPane.showMessageDialog(f, "User already exists.");
        } else {
            users.put(user, pass); // add user to hashmap
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/src/assets/userList.csv"), true));
            out.append(","+user+";"+pass);
            out.close();
            JOptionPane.showMessageDialog(f, "User created successfully.");
        }
    }
    public HashMap<String,String> readUsers() throws FileNotFoundException {
        HashMap<String,String> users = new HashMap<String,String>();
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/src/assets/userList.csv"));
        sc.useDelimiter(","); // sets the delimiter pattern
        while (sc.hasNext()) // returns a boolean value
        {
            String[] userData = sc.next().split(";");
            String u = userData[0];
            String p = userData[1];
            users.put(u, p);
        }
        sc.close(); // closes the scanner

        return users;
    }
    public boolean checkPassword(String user, String pass) throws FileNotFoundException {
        HashMap<String, String> users = readUsers(); // read users from csv file 
        if (users.containsKey(user)) {
            if (users.get(user).equals(pass)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(f, "Incorrect password");
            }
        } else {
            JOptionPane.showMessageDialog(f, "User not found, please create an account.");
        }

        return false;
    }

    public LoginUI() {
        GridLayout layout = new GridLayout(3, 2);
        p.setLayout(layout);
        login.setText("Login");
        signup.setText("Sign Up");
        login.addActionListener(this);
        signup.addActionListener(this);
        p.add(username);
        p.add(usernameField);
        p.add(password);
        p.add(passwordField);
        p.add(login);
        p.add(signup); 
        f.setTitle("Login Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setContentPane(p);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // setLookandFeel for modern Windows 10
                                                                             // look on buttons and other JComponents
        new LoginUI();
    }
}