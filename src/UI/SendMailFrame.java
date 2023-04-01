package UI;

import javax.swing.*;
import java.awt.*;

public class SendMailFrame extends Frame {
    public SendMailFrame() {
        super();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        this.add(mainPanel, BorderLayout.NORTH);

        JLabel recipientMailLabel = new JLabel("To: ");
        JTextField recipientMailField = new JTextField();
        mainPanel.add(recipientMailLabel);
        mainPanel.add(recipientMailField);

        JLabel subjectLabel = new JLabel("Subject: ");
        JTextField subjectField = new JTextField();
        mainPanel.add(subjectLabel);
        mainPanel.add(subjectField);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.add(messagePanel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("Message: ");
        JTextArea messageArea = new JTextArea();
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messagePanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);


        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new BorderLayout());
        sendPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.add(sendPanel, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendPanel.add(sendButton, BorderLayout.SOUTH);
    }
}
