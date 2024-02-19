package otherForms;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import myUsers.MainMenu;

public class FeedbackForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textSessionID;
    private JComboBox<String> comboBox;
    private JTextField textRating;
    private JTextField textComments;
    private JTextField textTime;
    private JTable dataTable; // Move JTable declaration to class level

    // JDBC Database URL
    private static final String DB_URL = "jdbc:mysql://localhost/umuhozakampirenoelline222013336hbcsdb";
    private static final String DB_USER = "umuhozakampirenoelline2220133362";
    private static final String DB_PASSWORD = "222013336";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FeedbackForm frame = new FeedbackForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FeedbackForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 622, 540);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFeedback = new JLabel("Feedback");
        lblFeedback.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblFeedback.setBounds(234, 49, 115, 45);
        contentPane.add(lblFeedback);

        JLabel lblSessionID = new JLabel("Session ID");
        lblSessionID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblSessionID.setBounds(136, 140, 93, 34);
        contentPane.add(lblSessionID);

        textSessionID = new JTextField();
        textSessionID.setBounds(239, 140, 164, 34);
        contentPane.add(textSessionID);
        textSessionID.setColumns(10);

        JLabel lblParticipant = new JLabel("Participant");
        lblParticipant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblParticipant.setBounds(136, 191, 93, 34);
        contentPane.add(lblParticipant);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Client", "Consultant"}));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(237, 193, 164, 34);
        contentPane.add(comboBox);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblRating.setBounds(167, 243, 62, 34);
        contentPane.add(lblRating);

        textRating = new JTextField();
        textRating.setBounds(239, 245, 164, 34);
        contentPane.add(textRating);
        textRating.setColumns(10);

        JLabel lblComments = new JLabel("Comments");
        lblComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblComments.setBounds(136, 299, 93, 30);
        contentPane.add(lblComments);

        textComments = new JTextField();
        textComments.setBounds(239, 299, 164, 34);
        contentPane.add(textComments);
        textComments.setColumns(10);

        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTime.setBounds(179, 353, 50, 31);
        contentPane.add(lblTime);

        textTime = new JTextField();
        textTime.setBounds(239, 353, 164, 34);
        contentPane.add(textTime);
        textTime.setColumns(10);

        // Initialize the dataTable
        dataTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(555, 70, 600, 150);
        contentPane.add(scrollPane);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAdd.setBounds(54, 412, 115, 34);
        contentPane.add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnUpdate.setBounds(326, 412, 115, 34);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnDelete.setBounds(457, 412, 115, 34);
        contentPane.add(btnDelete);

        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnView.setBounds(189, 412, 115, 34);
        contentPane.add(btnView);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(10, 11, 89, 23);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            setVisible(false);
        });

        btnAdd.addActionListener(e -> addFeedbackAndReview());

        btnUpdate.addActionListener(e -> updateFeedbackAndReview());

        btnDelete.addActionListener(e -> deleteFeedbackAndReview());

        btnView.addActionListener(e -> viewFeedbackAndReviews());
    }

    private void addFeedbackAndReview() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sessionIDText = textSessionID.getText().trim();
            String ratingText = textRating.getText().trim();

            // Validate input
            if (!isNumeric(sessionIDText) || !isNumeric(ratingText)) {
                JOptionPane.showMessageDialog(null, "Invalid input. Session ID and Rating must be numeric.");
                return;
            }

            int sessionID = Integer.parseInt(sessionIDText);
            int rating = Integer.parseInt(ratingText);
            String participantType = comboBox.getSelectedItem().toString();

            String sql = "INSERT INTO FeedbackAndReviews (SessionID, Participant, Rating, Comments, Time) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, sessionID);
                statement.setString(2, participantType);
                statement.setInt(3, rating);
                statement.setString(4, textComments.getText());
                statement.setString(5, textTime.getText());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Feedback and Review added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add Feedback and Review");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding Feedback and Review");
        }
    }

    // Helper method to check if a string is numeric
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void viewFeedbackAndReviews() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM FeedbackAndReviews";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("FeedbackID");
                model.addColumn("SessionID");
                model.addColumn("Participant");
                model.addColumn("Rating");
                model.addColumn("Comments");
                model.addColumn("Time");

                while (resultSet.next()) {
                    int feedbackID = resultSet.getInt("FeedbackID");
                    int sessionID = resultSet.getInt("SessionID");
                    String participant = resultSet.getString("Participant");
                    int rating = resultSet.getInt("Rating");
                    String comments = resultSet.getString("Comments");
                    String time = resultSet.getString("Time");

                    model.addRow(new Object[]{feedbackID, sessionID, participant, rating, comments, time});
                }

                dataTable.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing Feedback and Reviews");
        }
    }

    private void updateFeedbackAndReview() {
        String feedbackIDString = JOptionPane.showInputDialog("Enter FeedbackID to update:");
        if (feedbackIDString == null || feedbackIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid FeedbackID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE FeedbackAndReviews SET SessionID=?, Participant=?, Rating=?, Comments=?, Time=? WHERE FeedbackID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textSessionID.getText()));
                statement.setString(2, comboBox.getSelectedItem().toString());
                statement.setInt(3, Integer.parseInt(textRating.getText()));
                statement.setString(4, textComments.getText());
                statement.setString(5, textTime.getText());
                statement.setInt(6, Integer.parseInt(feedbackIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Feedback and Review updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update Feedback and Review. FeedbackID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Feedback and Review");
        }
    }

    private void deleteFeedbackAndReview() {
        String feedbackIDString = JOptionPane.showInputDialog("Enter FeedbackID to delete:");
        if (feedbackIDString == null || feedbackIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid FeedbackID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM FeedbackAndReviews WHERE FeedbackID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(feedbackIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Feedback and Review deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete Feedback and Review. FeedbackID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Feedback and Review");
        }
    }
}
