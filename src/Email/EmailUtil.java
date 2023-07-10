package Email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {

    private final Session session;

    public EmailUtil(String host, String port, String username, String password) {
        session = createSMPTSession(host, port, username, password);
    }

    public static Session createSMPTSession(String host, String port, String username, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        return Session.getInstance(props, authenticator);
    }

    public void sendMail(String to, String from, String subject, String message) throws MessagingException {
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
        Transport.send(mimeMessage);
        System.out.println("Email.Email Message Sent Successfully");
    }

    public boolean isLoggedIn() {
        try {
            Transport transport = session.getTransport();
            transport.connect();
            transport.close();

            return true;
        } catch (AuthenticationFailedException e) {
            return false;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
