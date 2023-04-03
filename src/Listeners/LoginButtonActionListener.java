package Listeners;

import Email.EmailSpammer;
import Email.EmailUtil;
import UI.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonActionListener implements ActionListener {
    private final LoginFrame frame;
    private final EmailSpammer spammer;

    public LoginButtonActionListener(LoginFrame frame, EmailSpammer spammer) {
        this.frame = frame;
        this.spammer = spammer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        spammer.setEmailUtil(new EmailUtil(frame.getSmtpServer(), frame.getSmtpPort(), frame.getUsername(), frame.getPassword()));
    }
}
