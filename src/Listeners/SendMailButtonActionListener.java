package Listeners;

import Email.EmailSpammer;
import UI.SendMailFrame;

import javax.swing.*;
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

        Thread thread = new Thread(() -> {
            for (int i = 0; i < frame.getNumberOfMails(); i++) {
                spammer.sendMail(frame.getRecipient(), frame.getSubject(), frame.getMessage());
            }
        });
        thread.start();

        try {
            thread.join();
            JOptionPane optionPane = new JOptionPane(frame.getNumberOfMails() + " Mail(s) sent", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Success");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
