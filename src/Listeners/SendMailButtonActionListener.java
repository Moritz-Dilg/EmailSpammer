package Listeners;

import UI.SendMailFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMailButtonActionListener implements ActionListener {
    private final SendMailFrame frame;

    public SendMailButtonActionListener(SendMailFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Send mail");
        System.out.println("Recipient: " + frame.getRecipient());
        System.out.println("Subject: " + frame.getSubject());
        System.out.println("Message: " + frame.getMessage());
    }
}
