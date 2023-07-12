package Email;

import UI.LoginFrame;
import UI.SendMailFrame;
import jakarta.mail.MessagingException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

                try {
                    if (emailUtil.isLoggedIn()) {
                        Preferences preferences = Preferences.userNodeForPackage(EmailSpammer.class);
                        preferences.put("host", host);
                        preferences.put("port", port);
                        preferences.put("username", username);
                        preferences.put("password", password);

                        loginFrame.dispose();
                        onLoggedIn.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "logged in"));
                    } else {
                        JOptionPane.showMessageDialog(loginFrame, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (MessagingException ex) {
                    JOptionPane optionPane = new JOptionPane("""
                            Failed to authenticate at the SMTP Server

                            Please check your network connection""", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error logging in");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
    }

    public void start() {
        sendMailFrame = new SendMailFrame();
        sendMailFrame.draw();
        sendMailFrame.addButtonListener(sendMailButtonActionListener);
    }

    public void setEmailUtil(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    public void sendMail(String to, String subject, String message) throws MessagingException {
        emailUtil.sendMail(to, loginFrame.getUsername(), subject, message);
    }

    private final ActionListener sendMailButtonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                for (int i = 0; i < sendMailFrame.getNumberOfMails(); i++) {
                    sendMail(sendMailFrame.getRecipient(), sendMailFrame.getSubject(), sendMailFrame.getMessage());

                    try {
                        Thread.sleep(new Random().nextInt(50, 2000));
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                sendMail(loginFrame.getUsername(), "Successfully sent " + sendMailFrame.getNumberOfMails() + " Mail(s) to " + sendMailFrame.getRecipient(),
                        """
                                Successfully sent %d Mail(s) to %s:
                                
                                Subject: %s
                                    
                                Message:
                                %s
                                """.formatted(sendMailFrame.getNumberOfMails(), sendMailFrame.getRecipient(), sendMailFrame.getSubject(), sendMailFrame.getMessage()));;
            } catch (NumberFormatException e) {
                JOptionPane optionPane = new JOptionPane("Please enter a valid number of EMails to send", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Invalid number of EMails");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                return;
            } catch (MessagingException e) {
                JOptionPane optionPane = new JOptionPane("Failed to send Mail(s): " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error sending Mail(s)");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                return;
            }

            JOptionPane optionPane = new JOptionPane(sendMailFrame.getNumberOfMails() + " Mail(s) sent", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Success");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    };
}
