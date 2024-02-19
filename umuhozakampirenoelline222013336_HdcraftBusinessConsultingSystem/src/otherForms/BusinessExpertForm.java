package otherForms;

import java.awt.EventQueue;

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
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BusinessExpertForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNames;
	private JTextField textContactInfo;
	private JTextField textAreaOfExpertise;
	private JTextField textConsultingHistory;

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
					BusinessExpertForm frame = new BusinessExpertForm();
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
	public BusinessExpertForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Busines Experts");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(232, 49, 131, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNames = new JLabel("Names");
		lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNames.setBounds(157, 113, 61, 30);
		contentPane.add(lblNames);
		
		textNames = new JTextField();
		textNames.setBounds(249, 115, 218, 30);
		contentPane.add(textNames);
		textNames.setColumns(10);
		
		JLabel lblContactInfo = new JLabel("Contact Info");
		lblContactInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblContactInfo.setBounds(114, 157, 104, 36);
		contentPane.add(lblContactInfo);
		
		textContactInfo = new JTextField();
		textContactInfo.setBounds(249, 162, 241, 30);
		contentPane.add(textContactInfo);
		textContactInfo.setColumns(10);
		
		JLabel lblAreaOfExpertise = new JLabel("Area Of Expertise");
		lblAreaOfExpertise.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAreaOfExpertise.setBounds(63, 204, 155, 35);
		contentPane.add(lblAreaOfExpertise);
		
		textAreaOfExpertise = new JTextField();
		textAreaOfExpertise.setBounds(249, 208, 269, 30);
		contentPane.add(textAreaOfExpertise);
		textAreaOfExpertise.setColumns(10);
		
		JLabel lblConsultingHistory = new JLabel("Consulting History");
		lblConsultingHistory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblConsultingHistory.setBounds(63, 249, 155, 33);
		contentPane.add(lblConsultingHistory);
		
		textConsultingHistory = new JTextField();
		textConsultingHistory.setBounds(249, 252, 289, 30);
		contentPane.add(textConsultingHistory);
		textConsultingHistory.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(77, 306, 110, 30);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(327, 306, 110, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(449, 306, 110, 30);
		contentPane.add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.setBounds(203, 306, 110, 30);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack );
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
		        addBusinessExpert();
		    }
		});

		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        updateBusinessExpert();
		    }
		});

		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        deleteBusinessExpert();
		    }
		});

		btnView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        viewBusinessExperts();
		    }
		});
		}

		private void addBusinessExpert() {
		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "INSERT INTO BusinessExperts (Name, ContactInformation, AreaOfExpertise, ConsultingHistory) VALUES (?, ?, ?, ?)";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setString(1, textNames.getText());
		            statement.setString(2, textContactInfo.getText());
		            statement.setString(3, textAreaOfExpertise.getText());
		            statement.setString(4, textConsultingHistory.getText());

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Business Expert added successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to add business expert");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error adding business expert");
		    }
		}

		private void viewBusinessExperts() {
		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "SELECT * FROM BusinessExperts";
		        try (PreparedStatement statement = connection.prepareStatement(sql);
		             ResultSet resultSet = statement.executeQuery()) {

		            DefaultTableModel model = new DefaultTableModel();
		            model.addColumn("ExpertID");
		            model.addColumn("Name");
		            model.addColumn("ContactInformation");
		            model.addColumn("AreaOfExpertise");
		            model.addColumn("ConsultingHistory");

		            while (resultSet.next()) {
		                int expertID = resultSet.getInt("ExpertID");
		                String name = resultSet.getString("Name");
		                String contactInfo = resultSet.getString("ContactInformation");
		                String areaOfExpertise = resultSet.getString("AreaOfExpertise");
		                String consultingHistory = resultSet.getString("ConsultingHistory");

		                // Add data to the table model
		                model.addRow(new Object[]{expertID, name, contactInfo, areaOfExpertise, consultingHistory});
		            }

		            JTable dataTable = new JTable();
		            JScrollPane scrollPane = new JScrollPane(dataTable);
		            scrollPane.setBounds(555, 70, 600, 150);
		            contentPane.add(scrollPane);

		            dataTable.setModel(model);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error viewing business experts");
		    }
		}

		private void updateBusinessExpert() {
		    String expertIDString = JOptionPane.showInputDialog("Enter ExpertID to update:");
		    if (expertIDString == null || expertIDString.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Invalid ExpertID");
		        return;
		    }

		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "UPDATE BusinessExperts SET Name=?, ContactInformation=?, AreaOfExpertise=?, ConsultingHistory=? WHERE ExpertID=?";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setString(1, textNames.getText());
		            statement.setString(2, textContactInfo.getText());
		            statement.setString(3, textAreaOfExpertise.getText());
		            statement.setString(4, textConsultingHistory.getText());
		            statement.setInt(5, Integer.parseInt(expertIDString));

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Business Expert updated successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to update business expert. ExpertID not found.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error updating business expert");
		    }
		}

		private void deleteBusinessExpert() {
		    String expertIDString = JOptionPane.showInputDialog("Enter ExpertID to delete:");
		    if (expertIDString == null || expertIDString.trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Invalid ExpertID");
		        return;
		    }

		    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		        String sql = "DELETE FROM BusinessExperts WHERE ExpertID=?";
		        try (PreparedStatement statement = connection.prepareStatement(sql)) {
		            statement.setInt(1, Integer.parseInt(expertIDString));

		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Business Expert deleted successfully");
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to delete business expert. ExpertID not found.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error deleting business expert");
		    }
		}}
