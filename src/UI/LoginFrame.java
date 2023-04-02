package UI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends Frame {

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
        JTextField smtpServerField = new JTextField();
        hostPanel.add(smtpServerLabel);
        hostPanel.add(smtpServerField);

        JLabel smtpPortLabel = new JLabel("SMTP Port: ");
        JTextField smtpPortField = new JTextField();
        hostPanel.add(smtpPortLabel);
        hostPanel.add(smtpPortField);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 2));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        mainPanel.add(loginButton, BorderLayout.SOUTH);

        pack();
    }

}
