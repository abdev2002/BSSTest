import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDetail extends JPanel {
	private JTextField nameField, emailField, phoneField, addressField, cityField, zipcodeField;
	private JComboBox<String> accountTypeComboBox;
	private JButton saveChangesButton, closeAccountButton;
	private BankAccount bankAccount;

    
	public AccountDetail(BankAccount bankAccount) {
		this.bankAccount = bankAccount;

		setLayout(null);

		JLabel titleLabel = new JLabel("Edit Information");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(150, 70, 200, 30);
		add(titleLabel);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(80, 120, 80, 25);
		add(nameLabel);

		nameField = new JTextField(bankAccount.getName());
		nameField.setBounds(230, 120, 200, 25);
		add(nameField);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(80, 150, 80, 25);
		add(emailLabel);

		emailField = new JTextField(bankAccount.getEmail());
		emailField.setBounds(230, 150, 200, 25);
		add(emailField);

		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setBounds(80, 180, 80, 25);
		add(phoneLabel);

		phoneField = new JTextField(bankAccount.getPhone());
		phoneField.setBounds(230, 180, 200, 25);
		add(phoneField);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBounds(80, 210, 80, 25);
		add(addressLabel);

		addressField = new JTextField(bankAccount.getAddress());
		addressField.setBounds(230, 210, 200, 25);
		add(addressField);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setBounds(80, 240, 80, 25);
		add(cityLabel);

		cityField = new JTextField(bankAccount.getCity());
		cityField.setBounds(230, 240, 200, 25);
		add(cityField);

		JLabel zipcodeLabel = new JLabel("Zipcode:");
		zipcodeLabel.setBounds(80, 270, 80, 25);
		add(zipcodeLabel);

		zipcodeField = new JTextField(bankAccount.getZipcode());
		zipcodeField.setBounds(230, 270, 200, 25);
		add(zipcodeField);

		JLabel accountTypeLabel = new JLabel("Account Type:");
		accountTypeLabel.setBounds(80, 300, 80, 25);
		add(accountTypeLabel);

		String[] accountTypes = { "Personal", "Business" };
		accountTypeComboBox = new JComboBox<>(accountTypes);
		accountTypeComboBox.setSelectedItem(bankAccount.getAccountType());
		accountTypeComboBox.setBounds(230, 300, 200, 25);
		add(accountTypeComboBox);

		saveChangesButton = new JButton("Save Changes");
		saveChangesButton.setForeground(new Color(255, 255, 255));
		saveChangesButton.setBackground(new Color(50, 205, 50));
		saveChangesButton.setBounds(80, 365, 140, 35);
		add(saveChangesButton);

		closeAccountButton = new JButton("Close Account");
		closeAccountButton.setForeground(new Color(255, 255, 255));
		closeAccountButton.setBackground(new Color(255, 0, 0));
		closeAccountButton.setBounds(230, 365, 140, 35);
		add(closeAccountButton);

		saveChangesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bankAccount.setName(nameField.getText());
				bankAccount.setEmail(emailField.getText());
				bankAccount.setPhone(phoneField.getText());
				bankAccount.setAddress(addressField.getText());
				bankAccount.setCity(cityField.getText());
				bankAccount.setZipcode(zipcodeField.getText());
				bankAccount.setAccountType((String) accountTypeComboBox.getSelectedItem());
				JOptionPane.showMessageDialog(null, "Changes saved successfully!");
				Database.updateAccountDetailsInDatabase(bankAccount);
			}
		});

		closeAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the account?",
						"Confirm Close Account", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					SwingUtilities.getWindowAncestor(AccountDetail.this).dispose();
					Database.deleteAccountFromDatabase(bankAccount);
				}
			}
		});
	}


	}

