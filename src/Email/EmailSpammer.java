package Email;

import Listeners.SendMailButtonActionListener;
import UI.LoginFrame;
import UI.SendMailFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class EmailSpammer {
    private EmailUtil emailUtil;
    private LoginFrame loginFrame;
    private SendMailFrame sendMailFrame;

    public void login(ActionListener onLoggedIn) {
        loginFrame = new LoginFrame();
        loginFrame.draw();

        Preferences preferences = Preferences.userNodeForPackage(EmailSpammer.class);
        final String savedHost = preferences.get("host", "");
        final String savedPort = preferences.get("port", "");
        final String savedUsername = preferences.get("username", "");
        final String savedPassword = preferences.get("password", "");
        loginFrame.populateFields(savedHost, savedPort, savedUsername, savedPassword);
        loginFrame.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String host = loginFrame.getSmtpServer();
                final String port = loginFrame.getSmtpPort();
                final String username = loginFrame.getUsername();
                final String password = loginFrame.getPassword();
                setEmailUtil(new EmailUtil(host, port, username, password));

                if (emailUtil.isLoggedIn()) {
                    Preferences preferences = Preferences.userNodeForPackage(EmailSpammer.class);
                    preferences.put("host", host);
                    preferences.put("port", port);
                    preferences.put("username", username);
                    preferences.put("password", password);

                    loginFrame.dispose();
                    onLoggedIn.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "logged in"));
                } else {
                    System.out.println("Login failed");
                    JOptionPane.showMessageDialog(loginFrame, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void start() {
        sendMailFrame = new SendMailFrame();
        sendMailFrame.draw();
        sendMailFrame.addButtonListener(new SendMailButtonActionListener(sendMailFrame, this));
    }

    public void setEmailUtil(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    public void sendMail(String to, String subject, String message) {
        emailUtil.sendMail(to, loginFrame.getUsername(), subject, message);
    }
}
