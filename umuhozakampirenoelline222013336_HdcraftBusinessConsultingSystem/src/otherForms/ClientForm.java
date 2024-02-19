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

public class ClientForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textContactInfo;
	private JTextField textBusinessName;
	private JTextField textLocation;
	private JTextField textBusinessSize;
	private JTextField textGoals;
	private JTextField textChallenges;
	  private JTable dataTable;
	
	
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
					ClientForm frame = new ClientForm();
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
	public ClientForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(211, 27, 115, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNames = new JLabel("Names");
		lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNames.setBounds(117, 97, 62, 27);
		contentPane.add(lblNames);
		
		textName = new JTextField();
		textName.setBounds(200, 97, 200, 30);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblContactInfo = new JLabel("Contact Info");
		lblContactInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblContactInfo.setBounds(72, 144, 107, 22);
		contentPane.add(lblContactInfo);
		
		textContactInfo = new JTextField();
		textContactInfo.setBounds(200, 143, 200, 29);
		contentPane.add(textContactInfo);
		textContactInfo.setColumns(10);
		
		JLabel lblBusinessName = new JLabel("Business name");
		lblBusinessName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBusinessName.setBounds(55, 191, 124, 27);
		contentPane.add(lblBusinessName);
		
		textBusinessName = new JTextField();
		textBusinessName.setBounds(200, 191, 200, 30);
		contentPane.add(textBusinessName);
		textBusinessName.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblLocation.setBounds(101, 232, 78, 27);
		contentPane.add(lblLocation);
		
		textLocation = new JTextField();
		textLocation.setBounds(200, 234, 200, 30);
		contentPane.add(textLocation);
		textLocation.setColumns(10);
		
		JLabel lblBusinessSize = new JLabel("Business Size");
		lblBusinessSize.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBusinessSize.setBounds(64, 278, 115, 27);
		contentPane.add(lblBusinessSize);
		
		textBusinessSize = new JTextField();
		textBusinessSize.setBounds(200, 278, 200, 30);
		contentPane.add(textBusinessSize);
		textBusinessSize.setColumns(10);
		
		JLabel lblGoals = new JLabel("Goals");
		lblGoals.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGoals.setBounds(116, 326, 55, 27);
		contentPane.add(lblGoals);
		
		textGoals = new JTextField();
		textGoals.setBounds(200, 326, 200, 30);
		contentPane.add(textGoals);
		textGoals.setColumns(10);
		
		JLabel lblChallenges = new JLabel("Challenges");
		lblChallenges.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChallenges.setBounds(86, 369, 93, 37);
		contentPane.add(lblChallenges);
		
		textChallenges = new JTextField();
		textChallenges.setBounds(200, 374, 200, 30);
		contentPane.add(textChallenges);
		textChallenges.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(30, 445, 110, 30);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(290, 445, 110, 30);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(423, 445, 110, 30);
		contentPane.add(btnDelete);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.setBounds(158, 445, 110, 30);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
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
	                addClient();
	            }
	        });

	        btnUpdate.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                updateClient();
	            }
	        });

	        btnDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                deleteClient();
	            }
	        });

	        btnView.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                viewClients();
	            }
	        });
	    }

	    private void addClient() {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "INSERT INTO Client (Name, ContactInformation, BusinessName, Location, BusinessSize, Goals, Challenges) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, textName.getText());
	                statement.setString(2, textContactInfo.getText());
	                statement.setString(3, textBusinessName.getText());
	                statement.setString(4, textLocation.getText());
	                statement.setString(5, textBusinessSize.getText());
	                statement.setString(6, textGoals.getText());
	                statement.setString(7, textChallenges.getText());

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Client added successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to add client");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error adding client");
	        }
	    }
	    
	    private void viewClients() {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "SELECT * FROM Client";
	            try (PreparedStatement statement = connection.prepareStatement(sql);
	                 ResultSet resultSet = statement.executeQuery()) {

	                DefaultTableModel model = new DefaultTableModel();
	                model.addColumn("ClientID");
	                model.addColumn("Name");
	                model.addColumn("ContactInformation");
	                model.addColumn("BusinessName");
	                model.addColumn("Location");
	                model.addColumn("BusinessSize");
	                model.addColumn("Goals");
	                model.addColumn("Challenges");

	                while (resultSet.next()) {
	                    int clientID = resultSet.getInt("ClientID");
	                    String name = resultSet.getString("Name");
	                    String contactInfo = resultSet.getString("ContactInformation");
	                    String businessName = resultSet.getString("BusinessName");
	                    String location = resultSet.getString("Location");
	                    String businessSize = resultSet.getString("BusinessSize");
	                    String goals = resultSet.getString("Goals");
	                    String challenges = resultSet.getString("Challenges");

	                    // Add data to the table model
	                    model.addRow(new Object[]{clientID, name, contactInfo, businessName, location, businessSize, goals, challenges});
	                }
	                
	                dataTable = new JTable();
	                JScrollPane scrollPane = new JScrollPane(dataTable);
	                scrollPane.setBounds(500, 70, 600, 150);
	                contentPane.add(scrollPane);

	         
	                dataTable.setModel(model);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error viewing clients");
	        }
	   

	    }
	
		        private void updateClient() {
		            String clientIDString = JOptionPane.showInputDialog("Enter ClientID to update:");
		            if (clientIDString == null || clientIDString.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Invalid ClientID");
		                return;
		            }

		            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		                String sql = "UPDATE Client SET Name=?, ContactInformation=?, BusinessName=?, Location=?, BusinessSize=?, Goals=?, Challenges=? WHERE ClientID=?";
		                try (PreparedStatement statement = connection.prepareStatement(sql)) {
		                    statement.setString(1, textName.getText());
		                    statement.setString(2, textContactInfo.getText());
		                    statement.setString(3, textBusinessName.getText());
		                    statement.setString(4, textLocation.getText());
		                    statement.setString(5, textBusinessSize.getText());
		                    statement.setString(6, textGoals.getText());
		                    statement.setString(7, textChallenges.getText());
		                    statement.setInt(8, Integer.parseInt(clientIDString));

		                    int rowsAffected = statement.executeUpdate();

		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(null, "Client updated successfully");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to update client. ClientID not found.");
		                    }
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error updating client");
		            }
		        }

		        private void deleteClient() {
		            String clientIDString = JOptionPane.showInputDialog("Enter ClientID to delete:");
		            if (clientIDString == null || clientIDString.trim().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Invalid ClientID");
		                return;
		            }

		            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		                String sql = "DELETE FROM Client WHERE ClientID=?";
		                try (PreparedStatement statement = connection.prepareStatement(sql)) {
		                    statement.setInt(1, Integer.parseInt(clientIDString));

		                    int rowsAffected = statement.executeUpdate();

		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(null, "Client deleted successfully");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to delete client. ClientID not found.");
		                    }
		                }
		            } catch (SQLException e) {
		                e.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error deleting client");
		            }
		        }}

