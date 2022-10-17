package statsVisualiser;

import javax.swing.*;

import statsVisualiser.gui.MainUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginUI implements ActionListener {
    Gson gson = new Gson(); 
    public static JFrame f = new JFrame();
    public static JPanel p = new JPanel();
    public static JButton login = new JButton();
    public static JButton signup = new JButton();
    public static JLabel username = new JLabel("Username");
    public static JLabel password = new JLabel("Password");
    public static JTextField usernameField = new JTextField("", 20);
    public static JPasswordField passwordField = new JPasswordField(20);
    Pattern userPattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$", Pattern.CASE_INSENSITIVE);
    Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            loginUser();
        } else if (e.getSource() == signup) {
            signupUser();
        }
    }

    public void loginUser() {
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());
        Matcher userMatcher = userPattern.matcher(user);
        Matcher passMatcher = passPattern.matcher(pass);
        if (userMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid username. Username msut be at least 3 characters long and can only contain letters, numbers and underscores.");

        } else if (passMatcher.find() == false) {
            JOptionPane.showMessageDialog(f,
                    "Invalid password. Password must be between 8 and 20 characters long and contain at least one uppercase letter, one lowercase letter and one number.");
        } else {
            try {
                if (checkPassword(user, pass) == true) {
                    f.dispose();
                    JFrame frame = MainUI.getInstance();
                    frame.setSize(900, 600);
                    frame.pack();
                    frame.setVisible(true);
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void signupUser() {
        String user = usernameField.getText();
        String pass = String.valueOf(passwordField.getPassword());
        Matcher userMatcher = userPattern.matcher(user);
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
        Map<String, String> users = readUsers(); // read users from csv file
        if (users.containsKey(user)) { // check if user already exists
            JOptionPane.showMessageDialog(f, "User already exists.");
        } else {
            users.put(user, pass); // add user to hashmap
            String jsonObj = gson.toJson(users); // convert hashmap to json
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(new File(System.getProperty("user.dir") + "/src/assets/userList.json")));
            out.append(jsonObj);
            out.close();
            JOptionPane.showMessageDialog(f, "User created successfully.");
        }
    }

    public HashMap<String, String> readUsers() throws FileNotFoundException {
        HashMap<String, String> users = new HashMap<String, String>();
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/src/assets/userList.json"));
        String jsonString = ""; 
        while (sc.hasNext()) // returns a boolean value
        {
         jsonString += sc.next();
        }
        sc.close(); // closes the scanner
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        users = gson.fromJson(jsonString,type);
        System.out.println(users.keySet()); 
        System.out.println(users.containsKey("zuhair"));
        System.out.println(users.get("zuhair"));
        return users;
    }

    public boolean checkPassword(String user, String pass) throws FileNotFoundException {
        HashMap<String, String> users = readUsers(); // read users from csv file
        System.out.println(users.get(user));
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
        login.setText("Login");
        signup.setText("Sign Up");
        login.addActionListener(this);
        signup.addActionListener(this);
        p.setPreferredSize(new Dimension(800, 500));
        p.setLayout(null);
        p.add(username);
        username.setBounds(370, 120, 60, 20);
        p.add(usernameField);
        usernameField.setBounds(125, 149, 550, 28);
        p.add(password);
        password.setBounds(370, 196, 60, 20);
        p.add(passwordField);
        passwordField.setBounds(125, 225, 550, 28);
        p.add(login);
        login.setBounds(300, 270, 90, 25);
        p.add(signup);
        signup.setBounds(410, 270, 90, 25);
        f.setTitle("Login Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
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