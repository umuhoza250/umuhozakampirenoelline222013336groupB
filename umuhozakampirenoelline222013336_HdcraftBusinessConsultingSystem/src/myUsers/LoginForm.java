package myUsers;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import home.UserIntraction;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class LoginForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textEmail;
    private JPasswordField passwordField;

	// JDBC Database URL
	    private static final String DB_URL = "jdbc:mysql://localhost/umuhozakampirenoelline222013336hbcsdb";
	    private static final String DB_USER = "umuhozakampirenoelline2220133362";
	    private static final String DB_PASSWORD = "222013336";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginForm frame = new LoginForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 769, 397);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbllgn = new JLabel("Login");
        lbllgn.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbllgn.setBounds(287, 50, 166, 49);
        contentPane.add(lbllgn);

        JLabel lblLogin = new JLabel("E-mail");
        lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblLogin.setBounds(220, 137, 57, 35);
        contentPane.add(lblLogin);

        textEmail = new JTextField();
        textEmail.setBounds(306, 137, 225, 34);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPassword.setBounds(196, 183, 81, 35);
        contentPane.add(lblPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLogin.setBounds(173, 249, 112, 30);
        contentPane.add(btnLogin);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnExit.setBounds(444, 249, 118, 30);
        contentPane.add(btnExit);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnReset.setBounds(309, 249, 112, 30);
        contentPane.add(btnReset);

        passwordField = new JPasswordField();
        passwordField.setBounds(306, 183, 225, 34);
        contentPane.add(passwordField);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(10, 11, 89, 23);
        contentPane.add(btnBack);
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserIntraction userIntraction = new UserIntraction();
                userIntraction.setVisible(true);
                dispose();
            }
        });
        // ActionListener to the Login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String enteredEmail = textEmail.getText();
                String enteredPassword = new String(passwordField.getPassword());

                // Checking if the entered email and password match
                if (isValidLogin(enteredEmail, enteredPassword)) {
                    // For Successful login
                    JOptionPane.showMessageDialog(contentPane, "Login successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Open my main menu 
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setVisible(true);

                    // Close the login form
                    setVisible(false);
                } else {
                    // For Invalid login
                    JOptionPane.showMessageDialog(contentPane, "Invalid email or password. Please try again.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener to the Reset button
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear email and password fields
                textEmail.setText("");
                passwordField.setText("");

                // Display a reset message using JOptionPane
                JOptionPane.showMessageDialog(contentPane, "Form reset successful!", "Reset Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener to the Exit button
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display an exit confirmation message
                int choice = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    // If the user chooses yes, close the form
                    System.exit(0);
                }
                // If the user chooses no, do nothing
            }
        });
    }

    public static boolean isValidLogin(String enteredEmail, String enteredPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to check if the email and password match in the User table
            String sql = "SELECT * FROM User WHERE Email = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredEmail);
            preparedStatement.setString(2, enteredPassword);

            // Execute the SQL query
            resultSet = preparedStatement.executeQuery();

            // Check if any result is returned
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related exceptions

        } finally {
            // Closing resources in the reverse order of their creation to avoid potential resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
