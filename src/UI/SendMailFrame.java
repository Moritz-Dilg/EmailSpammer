package UI;

import Filters.IntegerFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class SendMailFrame extends Frame {

    private JTextField numberOfMailsField;
    private JTextField recipientMailField;
    private JTextField subjectField;
    private JTextArea messageArea;
    private JButton sendButton;


    public SendMailFrame() {
        super();
    }

    @Override
    public void draw() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        this.add(mainPanel, BorderLayout.NORTH);

        JLabel numberOfMailsLabel = new JLabel("Number of mails: ");
        numberOfMailsField = new JTextField();
        mainPanel.add(numberOfMailsLabel);
        mainPanel.add(numberOfMailsField);
        PlainDocument doc = (PlainDocument) numberOfMailsField.getDocument();
        doc.setDocumentFilter(new IntegerFilter());

        JLabel recipientMailLabel = new JLabel("To: ");
        recipientMailField = new JTextField();
        mainPanel.add(recipientMailLabel);
        mainPanel.add(recipientMailField);

        JLabel subjectLabel = new JLabel("Subject: ");
        subjectField = new JTextField();
        mainPanel.add(subjectLabel);
        mainPanel.add(subjectField);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        this.add(messagePanel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("Message: ");
        messageArea = new JTextArea();
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messagePanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);


        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new BorderLayout());
        sendPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.add(sendPanel, BorderLayout.SOUTH);

        sendButton = new JButton("Send");
        sendPanel.add(sendButton, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(sendButton);
        pack();
    }

    public Integer getNumberOfMails() {
        return Integer.parseInt(numberOfMailsField.getText());
    }

    public String getRecipient() {
        return recipientMailField.getText();
    }

    public String getSubject() {
        return subjectField.getText();
    }

    public String getMessage() {
        return messageArea.getText();
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        sendButton.addActionListener(listener);
    }
}
