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

/**
 * Login component for the StatsVisualiser application.
 * 
 * @author Zuhair, Abdul
 * @version 1.0
 * @since 2022-10-23
 * 
 */
public class LoginUI implements ActionListener {
    Gson gson = new Gson(); // gson object for interfacing with json files
    /*
     * All java swing components used in the login UI
     */
    public static JFrame f = new JFrame();
    public static JPanel p = new JPanel();
    public static JButton login = new JButton();

    /**
     * The user name and password fields
     */
    public static String user;
    public static String pass;
    /**
     * matchers that will check the pattern below when given a username and password
     */
    Matcher userMatcher;
    Matcher passMatcher;
    public static JButton signup = new JButton();
    public static JLabel username = new JLabel("Username");
    public static JLabel password = new JLabel("Password");
    public static JTextField usernameField = new JTextField("", 20);
    public static JPasswordField passwordField = new JPasswordField(20);

    /**
     * Regex patterns for the username and password fields
     */
    Pattern userPattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$", Pattern.CASE_INSENSITIVE);
    Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,100}$");

    private static LoginUI instance; // singleton instance of the loginUI

    public static LoginUI getInstance() throws Exception { // singleton pattern
        if (instance == null)
            instance = new LoginUI();

        return instance;
    }

    /**
     * Action performed method for the login UI
     * Checks if user clicked login or signup button and performs the appropriate
     * action
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            try {
                loginUser();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == signup) {
            signupUser();
        }
    }

    /**
     * Method to login a user
     * Checks if the username and password match the regex patterns
     * Checks if the username and password match the username and password in the
     * json file
     * If the username and password match, the main UI is opened
     * If the username and password do not match, an error message is displayed
     * 
     * @return none
     * @param none
     * @throws Exception
     */
    public void loginUser() throws Exception {
        user = usernameField.getText(); // gets the username from the username field
        pass = String.valueOf(passwordField.getPassword()); // gets the password from the password field
        userMatcher = userPattern.matcher(user); // checks if the username matches the regex pattern
        passMatcher = passPattern.matcher(pass); // checks if the password matches the regex pattern
        if (userMatcher.find() == false) { // if the username does not match the regex pattern
            JOptionPane.showMessageDialog(f,
                    "Invalid username. Username must be at least 3 characters long and can only contain letters, numbers and underscores."); // error
                                                                                                                                             // message

        } else if (passMatcher.find() == false) { // if the password does not match the regex pattern
            JOptionPane.showMessageDialog(f,
                    "Invalid password. Password must be between 8 and 20 characters long and contain at least one uppercase letter, one lowercase letter and one number."); // error
                                                                                                                                                                            // message
        } else { // if the username and password match the regex patterns
            try {
                if (checkPassword(user, pass) == true) { // if the username and password match the username and password
                                                         // in the json file
                    f.dispose(); // closes the login UI
                    JFrame frame = MainUI.getInstance(); // opens the main UI
                    frame.setSize(900, 600); // sets the size of the main UI
                    frame.pack(); // packs the main UI
                    frame.setVisible(true); // makes the main UI visible
                }
            } catch (FileNotFoundException e1) { // if the json file is not found
                e1.printStackTrace(); // prints the stack trace
            }
        }
    }

    /**
     * Method to signup a user
     * Checks if the username and password match the regex patterns
     * Checks if the username and password match the username and password in the
     * json file using createUser()
     * If the username and password match, an error message is displayed
     * If the username and password do not match, the user is added to the json file
     * and the main UI is opened
     * 
     * @return none
     * @param none
     */
    public void signupUser() {
        user = usernameField.getText(); // gets the username from the username field
        pass = String.valueOf(passwordField.getPassword()); // gets the password from the password field
        userMatcher = userPattern.matcher(user); // checks if the username matches the regex pattern
        passMatcher = passPattern.matcher(pass); // checks if the password matches the regex pattern
        if (userMatcher.find() == false) { // if the username does not match the regex pattern
            JOptionPane.showMessageDialog(f,
                    "Invalid username. Username msut be at least 3 characters long and can only contain letters, numbers and underscores."); // error
                                                                                                                                             // message

        } else if (passMatcher.find() == false) { // if the password does not match the regex pattern
            JOptionPane.showMessageDialog(f,
                    "Invalid password. Password must be between 8 and 20 characters long and contain at least one uppercase letter, one lowercase letter and one number."); // error
                                                                                                                                                                            // message
        } else { // if the username and password match the regex patterns
            try {
                removeUser(user, pass); // checks if the username and password match the username and password in the
                                        // json file, if they do, creates user
            } catch (IOException e) { // if the json file is not found
                e.printStackTrace(); // prints the stack trace
            }
        }
    }

    /**
     * Method to create a user
     * Checks if the username given is in the json file already,
     * if it is, an error message is displayed
     * if it is not, the user is added to the json file
     * 
     * @return none
     * @param String user
     * @param String pass
     * @throws IOException
     */
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

    public void removeUser(String user, String pass) throws IOException {
        Map<String, String> users = readUsers(); // read users from csv file
        if (users.containsKey(user) == false) { // check if user already exists
            JOptionPane.showMessageDialog(f, "User does not exist.");
        } else {
            users.remove(user);
            String jsonObj = gson.toJson(users); // convert hashmap to json
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(new File(System.getProperty("user.dir") + "/src/assets/userList.json")));
            out.append(jsonObj);
            out.close();
            JOptionPane.showMessageDialog(f, "User removed successfully.");
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
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        users = gson.fromJson(jsonString, type);
        return users;
    }

    /**
     * Method to check if the username and password match the username and password
     * in the json file
     * 
     * @return boolean
     * @param String user
     * @param String pass
     * @throws FileNotFoundException
     */
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

    /**
     * Constructor for the login UI
     */
    public LoginUI() {
        login.setText("Login"); // set the text of the login button
        signup.setText("Sign Up"); // set the text of the signup button
        login.addActionListener(this); // add action listener to login button
        signup.addActionListener(this); // add action listener to signup button
        p.setPreferredSize(new Dimension(800, 500)); // set the size of the panel
        p.setLayout(null); // set the layout of the panel to null
        p.add(username); // add the username label to the panel
        username.setBounds(370, 120, 60, 20); // set the bounds of the username label
        p.add(usernameField); // add the username field to the panel
        usernameField.setBounds(125, 149, 550, 28); // set the bounds of the username field
        p.add(password); // add the password label to the panel
        password.setBounds(370, 196, 60, 20); // set the bounds of the password label
        p.add(passwordField); // add the password field to the panel
        passwordField.setBounds(125, 225, 550, 28); // set the bounds of the password field
        p.add(login); // add the login button to the panel
        login.setBounds(300, 270, 90, 25); // set the bounds of the login button
        p.add(signup); // add the signup button to the panel
        signup.setBounds(410, 270, 90, 25); // set the bounds of the signup button
        f.setTitle("Login Form"); // set the title of the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation of the frame
        f.setResizable(false); // set the frame to be not resizable
        f.setContentPane(p); // set the content pane of the frame to the panel
        f.pack(); // pack the frame
        f.setLocationRelativeTo(null); // set the location of the frame to the center of the screen
        f.setVisible(true); // set the frame to be visible

    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // setLookandFeel for modern Windows 10
                                                                             // look on buttons and other JComponents
        LoginUI.getInstance(); // create a new instance of the login UI
    }
}