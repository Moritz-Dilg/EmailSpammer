package Listeners;

import UI.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonActionListener implements ActionListener {
    private final LoginFrame frame;

    public LoginButtonActionListener(LoginFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Login button clicked");
        System.out.println("SMTP Server: " + frame.getSmtpServer());
        System.out.println("SMTP Port: " + frame.getSmtpPort());
        System.out.println("Username: " + frame.getUsername());
        System.out.println("Password: " + frame.getPassword());
    }
}
