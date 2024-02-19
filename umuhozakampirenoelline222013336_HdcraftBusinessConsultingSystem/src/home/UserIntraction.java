package home;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import myUsers.LoginForm;

public class UserIntraction extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNames;
    private JTextField textEmail;
    private JTextField textPassword;
    private JComboBox<String> comboBox;


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
                    UserIntraction frame = new UserIntraction();
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
    public UserIntraction() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 561);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Please Interact with Us.");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(278, 34, 206, 36);
        contentPane.add(lblNewLabel_7);

        JLabel lblSignUp = new JLabel("Sign Up");
        lblSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblSignUp.setBounds(316, 77, 168, 40);
        contentPane.add(lblSignUp);

        JLabel lblNames = new JLabel("Names");
        lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNames.setBounds(220, 137, 63, 33);
        contentPane.add(lblNames);

        textNames = new JTextField();
        textNames.setBounds(293, 141, 263, 28);
        contentPane.add(textNames);
        textNames.setColumns(10);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(220, 176, 63, 33);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(293, 180, 263, 28);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        JLabel lblSpecialization = new JLabel("Specialization");
        lblSpecialization.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblSpecialization.setBounds(157, 212, 117, 33);
        contentPane.add(lblSpecialization);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPassword.setBounds(195, 256, 88, 33);
        contentPane.add(lblPassword);

        textPassword = new JTextField();
        textPassword.setBounds(291, 258, 265, 28);
        contentPane.add(textPassword);
        textPassword.setColumns(10);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnSignUp.setBounds(211, 324, 110, 30);
        contentPane.add(btnSignUp);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnCancel.setBounds(480, 324, 110, 30);
        contentPane.add(btnCancel);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Client", "Consultant", "Business Expert" }));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(293, 219, 263, 26);
        contentPane.add(comboBox);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnReset.setBounds(342, 324, 110, 30);
        contentPane.add(btnReset);

        JLabel lblNewLabel_5 = new JLabel("Already Signed Up?");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(322, 380, 179, 28);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Then Login Here.");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(332, 415, 148, 28);
        contentPane.add(lblNewLabel_6);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLogin.setBounds(352, 454, 110, 30);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);

                setVisible(false);
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpUser();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelRegistration();
            }
        });
    }

   //implementation for signup button 
    private void signUpUser() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //querry to be registered
            String sql = "INSERT INTO User (Name, Email, Specialization, Password) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textNames.getText());
            preparedStatement.setString(2, textEmail.getText());
            preparedStatement.setString(3, comboBox.getSelectedItem().toString());
            preparedStatement.setString(4, textPassword.getText());
          
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(contentPane, "User registered successfully!", "Registration Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);

                    setVisible(false);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error: Unable to register user. Please try again.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println("enable to register");
        } 
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void resetForm() {
        textNames.setText("");
        textEmail.setText("");
        textPassword.setText("");
        comboBox.setSelectedIndex(0);
        JOptionPane.showMessageDialog(contentPane, "Form reset successful!", "Reset Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancelRegistration() {
        int choice = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to cancel registration?",
                "Cancel Registration", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            setVisible(false);
        }
        
    }
}
