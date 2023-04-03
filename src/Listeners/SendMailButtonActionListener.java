package Listeners;

import Email.EmailSpammer;
import UI.SendMailFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMailButtonActionListener implements ActionListener {
    private final SendMailFrame frame;
    private final EmailSpammer spammer;

    public SendMailButtonActionListener(SendMailFrame frame, EmailSpammer spammer) {
        this.frame = frame;
        this.spammer = spammer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Send mail");
        System.out.println("Recipient: " + frame.getRecipient());
        System.out.println("Subject: " + frame.getSubject());
        System.out.println("Message: " + frame.getMessage());

        spammer.sendMail(frame.getRecipient(), frame.getSubject(), frame.getMessage());
    }
}
