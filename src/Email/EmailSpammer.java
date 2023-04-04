package Email;

import Listeners.SendMailButtonActionListener;
import UI.LoginFrame;
import UI.SendMailFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailSpammer {
    private EmailUtil emailUtil;
    private LoginFrame loginFrame;
    private SendMailFrame sendMailFrame;

    public void login(ActionListener onLoggedIn) {
        // TODO: read saved login data from file
        loginFrame = new LoginFrame();
        loginFrame.draw();
        loginFrame.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEmailUtil(new EmailUtil(loginFrame.getSmtpServer(), loginFrame.getSmtpPort(), loginFrame.getUsername(), loginFrame.getPassword()));
                if (emailUtil.isLoggedIn()) {
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
