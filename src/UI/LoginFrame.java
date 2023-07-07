package UI;

import Filters.IntegerFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame {
    private JTextField smtpServerField;
    private JTextField smtpPortField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        super("Login");
    }

    @Override
    public void draw() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(mainPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.SOUTH);

        JPanel hostPanel = new JPanel();
        hostPanel.setLayout(new GridLayout(2, 2));
        hostPanel.setBorder(BorderFactory.createTitledBorder("Host"));
        mainPanel.add(hostPanel, BorderLayout.NORTH);

        JLabel smtpServerLabel = new JLabel("SMTP Server: ");
        smtpServerField = new JTextField();
        hostPanel.add(smtpServerLabel);
        hostPanel.add(smtpServerField);

        JLabel smtpPortLabel = new JLabel("SMTP Port: ");
        smtpPortField = new JTextField();
        hostPanel.add(smtpPortLabel);
        hostPanel.add(smtpPortField);
        PlainDocument smtpPortFieldDoc = (PlainDocument) smtpPortField.getDocument();
        smtpPortFieldDoc.setDocumentFilter(new IntegerFilter());


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        mainPanel.add(loginButton, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(loginButton);
        pack();
    }

    public void populateFields(String smtpServer, String smtpPort, String username, String password) {
        // Ensure that all fields are existing
        if (smtpServerField == null || smtpPortField == null || usernameField == null || passwordField == null)
            draw();

        smtpServerField.setText(smtpServer);
        smtpPortField.setText(smtpPort);
        usernameField.setText(username);
        passwordField.setText(password);
    }

    public String getSmtpServer() {
        return smtpServerField.getText();
    }

    public String getSmtpPort() {
        return smtpPortField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

}
