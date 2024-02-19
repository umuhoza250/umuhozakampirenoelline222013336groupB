package myUsers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import otherForms.BusinessExpertForm;
import otherForms.ClientForm;
import otherForms.CommunicationForm;
import otherForms.ConsultantForm;
import otherForms.ConsultationForm;
import otherForms.FeedbackForm;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MainMenu");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(286, 11, 185, 39);
		contentPane.add(lblNewLabel);
		
				
		JButton btnClient = new JButton("Client");
		btnClient.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnClient.setBounds(139, 96, 147, 30);
		contentPane.add(btnClient);
		
		JButton btnConsultant = new JButton("Consultant");
		btnConsultant.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnConsultant.setBounds(296, 96, 147, 30);
		contentPane.add(btnConsultant);
		
		JButton btnExparts = new JButton("Experts");
		btnExparts.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnExparts.setBounds(453, 96, 147, 30);
		contentPane.add(btnExparts);
		
		JButton btnConsultationSession = new JButton("Consultation Session");
		btnConsultationSession.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnConsultationSession.setBounds(38, 148, 217, 30);
		contentPane.add(btnConsultationSession);
		
		JButton btnCommunicationLogs = new JButton("Communication Logs");
		btnCommunicationLogs.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnCommunicationLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCommunicationLogs.setBounds(266, 148, 224, 30);
		contentPane.add(btnCommunicationLogs);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFeedback.setBounds(500, 148, 217, 30);
		contentPane.add(btnFeedback);
		
		// ActionListeners to each button to open the corresponding forms
				btnClient.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ClientForm clientForm = new ClientForm();
						clientForm.setVisible(true);
						// Close or hide the current registration form 
		                setVisible(false);
					}
				});

				btnConsultant.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConsultantForm consultantForm = new ConsultantForm();
						consultantForm.setVisible(true);
						// Close or hide the current registration form 
		                setVisible(false);
					}
				});

				btnExparts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BusinessExpertForm businessExpertForm = new BusinessExpertForm();
						businessExpertForm.setVisible(true);
						// Close or hide the current registration form 
		                setVisible(false);
					}
				});

				btnConsultationSession.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConsultationForm consultationForm = new ConsultationForm();
						consultationForm.setVisible(true);
						// Close or hide the current registration form 
		                setVisible(false);
					}
				});

				btnCommunicationLogs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CommunicationForm communicationForm = new CommunicationForm();
						communicationForm.setVisible(true);
						// Close or hide the current registration form 
		                setVisible(false);
					}
				});

				btnFeedback.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FeedbackForm feedbackForm = new FeedbackForm();
						feedbackForm.setVisible(true);
						// Close or hide the current registration form form 
		                setVisible(false);
					}
				});
			}
		

	}

