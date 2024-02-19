package otherForms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CommunicationForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textSessionID;
    private JComboBox<String> comboBox; // Modified this line
    private JTextField textTime;
    private JTextField textIntractionDetails;

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
                    CommunicationForm frame = new CommunicationForm();
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
    public CommunicationForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 540, 394);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSessionID = new JLabel("Session ID");
        lblSessionID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblSessionID.setBounds(83, 98, 91, 24);
        contentPane.add(lblSessionID);

        textSessionID = new JTextField();
        textSessionID.setBounds(184, 97, 200, 30);
        contentPane.add(textSessionID);
        textSessionID.setColumns(10);

        JLabel lblParticipant = new JLabel("Participant");
        lblParticipant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblParticipant.setBounds(83, 139, 91, 24);
        contentPane.add(lblParticipant);

        comboBox = new JComboBox<>(); // Modified this line
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Client", "Consultant" }));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(184, 138, 200, 30);
        contentPane.add(comboBox);

        JLabel lblIntractionDetails = new JLabel("Intraction Details");
        lblIntractionDetails.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblIntractionDetails.setBounds(29, 180, 145, 24);
        contentPane.add(lblIntractionDetails);

        textIntractionDetails = new JTextField();
        textIntractionDetails.setBounds(184, 179, 200, 30);
        contentPane.add(textIntractionDetails);
        textIntractionDetails.setColumns(10);

        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTime.setBounds(118, 226, 47, 24);
        contentPane.add(lblTime);

        textTime = new JTextField();
        textTime.setBounds(184, 220, 200, 30);
        contentPane.add(textTime);
        textTime.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAdd.setBounds(10, 266, 110, 30);
        contentPane.add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnUpdate.setBounds(274, 266, 110, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnDelete.setBounds(406, 266, 110, 30);
        contentPane.add(btnDelete);

        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnView.setBounds(140, 266, 110, 30);
        contentPane.add(btnView);

        JLabel lblCommunicationLogs = new JLabel("Communication Logs");
        lblCommunicationLogs.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblCommunicationLogs.setBounds(140, 38, 242, 30);
        contentPane.add(lblCommunicationLogs);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(10, 0, 89, 23);
        contentPane.add(btnBack);
        // ActionListener for the Back button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect the user to the MainMenu
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);

                // Close or hide the current form
                setVisible(false);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCommunicationLog();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCommunicationLog();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCommunicationLog();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCommunicationLogs();
            }
        });
    }

    private void addCommunicationLog() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String participantType = comboBox.getSelectedItem().toString();

            String sql = "INSERT INTO CommunicationLogs (SessionID, Participant, InteractionDetails, Time) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textSessionID.getText()));
                statement.setString(2, participantType);
                statement.setString(3, textIntractionDetails.getText());
                statement.setString(4, textTime.getText());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Communication log added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add communication log");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding communication log");
        }
    }

    private void viewCommunicationLogs() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM CommunicationLogs";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("LogID");
                model.addColumn("SessionID");
                model.addColumn("Participant");
                model.addColumn("InteractionDetails");
                model.addColumn("Time");

                while (resultSet.next()) {
                    int logID = resultSet.getInt("LogID");
                    int sessionID = resultSet.getInt("SessionID");
                    String participant = resultSet.getString("Participant");
                    String interactionDetails = resultSet.getString("InteractionDetails");
                    String time = resultSet.getString("Time");

                    // Add data to the table model
                    model.addRow(new Object[] { logID, sessionID, participant, interactionDetails, time });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(555, 70, 600, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing communication logs");
        }
    }

    private void updateCommunicationLog() {
        String logIDString = JOptionPane.showInputDialog("Enter LogID to update:");
        if (logIDString == null || logIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid LogID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE CommunicationLogs SET SessionID=?, Participant=?, InteractionDetails=?, Time=? WHERE LogID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textSessionID.getText()));
                statement.setString(2, comboBox.getSelectedItem().toString());
                statement.setString(3, textIntractionDetails.getText());
                statement.setString(4, textTime.getText());
                statement.setInt(5, Integer.parseInt(logIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Communication Log updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update Communication Log. LogID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Communication Log");
        }
    }

    private void deleteCommunicationLog() {
        String logIDString = JOptionPane.showInputDialog("Enter LogID to delete:");
        if (logIDString == null || logIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid LogID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM CommunicationLogs WHERE LogID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(logIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Communication Log deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete Communication Log. LogID not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Communication Log");
        }
    }
}
