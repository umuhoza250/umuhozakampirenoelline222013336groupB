package otherForms;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import myUsers.MainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultationForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textClientID;
	private JTextField textConsultantID;
	private JTextField textDateAndTime;
	private JTextField textDuration;
	private JTextField textAgender;
	private JTextField textOutcomes;
	

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
					ConsultationForm frame = new ConsultationForm();
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
	public ConsultationForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultationSession = new JLabel("Consultation Session");
		lblConsultationSession.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblConsultationSession.setBounds(159, 25, 237, 27);
		contentPane.add(lblConsultationSession);
		
		JLabel lblClientID = new JLabel("Client ID");
		lblClientID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblClientID.setBounds(121, 78, 79, 27);
		contentPane.add(lblClientID);
		
		textClientID = new JTextField();
		textClientID.setBounds(210, 78, 200, 30);
		contentPane.add(textClientID);
		textClientID.setColumns(10);
		
		JLabel lblConsultantID = new JLabel("Consultant ID");
		lblConsultantID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblConsultantID.setBounds(88, 122, 112, 27);
		contentPane.add(lblConsultantID);
		
		textConsultantID = new JTextField();
		textConsultantID.setBounds(210, 122, 200, 30);
		contentPane.add(textConsultantID);
		textConsultantID.setColumns(10);
		
		JLabel lblDateAndTime = new JLabel("Date And Time");
		lblDateAndTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDateAndTime.setBounds(70, 167, 130, 27);
		contentPane.add(lblDateAndTime);
		
		textDateAndTime = new JTextField();
		textDateAndTime.setBounds(210, 167, 200, 30);
		contentPane.add(textDateAndTime);
		textDateAndTime.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDuration.setBounds(121, 206, 79, 30);
		contentPane.add(lblDuration);
		
		textDuration = new JTextField();
		textDuration.setBounds(210, 208, 200, 30);
		contentPane.add(textDuration);
		textDuration.setColumns(10);
		
		JLabel lblAgenda = new JLabel("Agenda");
		lblAgenda.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgenda.setBounds(130, 253, 70, 27);
		contentPane.add(lblAgenda);
		
		textAgender = new JTextField();
		textAgender.setBounds(210, 253, 200, 30);
		contentPane.add(textAgender);
		textAgender.setColumns(10);
		
		JLabel lblOutcomes = new JLabel("Outcomes");
		lblOutcomes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblOutcomes.setBounds(113, 297, 87, 27);
		contentPane.add(lblOutcomes);
		
		textOutcomes = new JTextField();
		textOutcomes.setBounds(210, 294, 200, 30);
		contentPane.add(textOutcomes);
		textOutcomes.setColumns(10);
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(37, 335, 110, 30);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(300, 335, 110, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(431, 335, 110, 30);
		contentPane.add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.setBounds(171, 335, 110, 30);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(10, 11, 89, 23);
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
		        addConsultationSession();
		    }
		});

		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        updateConsultationSession();
		    }
		});

		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        deleteConsultationSession();
		    }
		});

		btnView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        viewConsultationSessions();
		    }
		});
	}

		private void addConsultationSession() {
		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "INSERT INTO ConsultationSession (ClientID, ConsultantID, DateAndTime, Duration, Agenda, Outcome) VALUES (?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setInt(1, Integer.parseInt(textClientID.getText()));
		            statement.setInt(2, Integer.parseInt(textConsultantID.getText()));
		            statement.setString(3, textDateAndTime.getText());
		            statement.setInt(4, Integer.parseInt(textDuration.getText()));
		            statement.setString(5, textAgender.getText());
		            statement.setString(6, textOutcomes.getText());

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Consultation Session added successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to add Consultation Session");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error adding Consultation Session");
		    }
		}

		private void viewConsultationSessions() {
		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "SELECT * FROM ConsultationSession";
		        try (PreparedStatement statement = connection.prepareStatement(sql);
		             ResultSet resultSet = statement.executeQuery()) {

		            DefaultTableModel model = new DefaultTableModel();
		            model.addColumn("SessionID");
		            model.addColumn("ClientID");
		            model.addColumn("ConsultantID");
		            model.addColumn("DateAndTime");
		            model.addColumn("Duration");
		            model.addColumn("Agenda");
		            model.addColumn("Outcome");

		            while (resultSet.next()) {
		                int sessionID = resultSet.getInt("SessionID");
		                int clientID = resultSet.getInt("ClientID");
		                int consultantID = resultSet.getInt("ConsultantID");
		                String dateAndTime = resultSet.getString("DateAndTime");
		                int duration = resultSet.getInt("Duration");
		                String agenda = resultSet.getString("Agenda");
		                String outcome = resultSet.getString("Outcome");

		                // Add data to the table model
		                model.addRow(new Object[]{sessionID, clientID, consultantID, dateAndTime, duration, agenda, outcome});
		            }

		            JTable dataTable = new JTable();
		            JScrollPane scrollPane = new JScrollPane(dataTable);
		            scrollPane.setBounds(555, 70, 600, 150);
		            contentPane.add(scrollPane);

		            dataTable.setModel(model);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error viewing Consultation Sessions");
		    }
		}

		private void updateConsultationSession() {
		    String sessionIDString = JOptionPane.showInputDialog("Enter SessionID to update:");
		    if (sessionIDString == null || sessionIDString.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Invalid SessionID");
		        return;
		    }

		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "UPDATE ConsultationSession SET ClientID=?, ConsultantID=?, DateAndTime=?, Duration=?, Agenda=?, Outcome=? WHERE SessionID=?";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setInt(1, Integer.parseInt(textClientID.getText()));
		            statement.setInt(2, Integer.parseInt(textConsultantID.getText()));
		            statement.setString(3, textDateAndTime.getText());
		            statement.setInt(4, Integer.parseInt(textDuration.getText()));
		            statement.setString(5, textAgender.getText());
		            statement.setString(6, textOutcomes.getText());
		            statement.setInt(7, Integer.parseInt(sessionIDString));

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Consultation Session updated successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to update Consultation Session. SessionID not found.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error updating Consultation Session");
		    }
		}

		private void deleteConsultationSession() {
		    String sessionIDString = JOptionPane.showInputDialog("Enter SessionID to delete:");
		    if (sessionIDString == null || sessionIDString.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Invalid SessionID");
		        return;
		    }

		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "DELETE FROM ConsultationSession WHERE SessionID=?";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setInt(1, Integer.parseInt(sessionIDString));

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Consultation Session deleted successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to delete Consultation Session. SessionID not found.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error deleting Consultation Session");
		    }
		}

}
