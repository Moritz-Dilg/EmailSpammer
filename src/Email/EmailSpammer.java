package Email;

import Listeners.LoginButtonActionListener;
import Listeners.SendMailButtonActionListener;
import UI.LoginFrame;
import UI.SendMailFrame;

public class EmailSpammer {
    private EmailUtil emailUtil;
    private LoginFrame loginFrame;
    private SendMailFrame sendMailFrame;

    public void start() {
        loginFrame = new LoginFrame();
        loginFrame.draw();
        loginFrame.addButtonListener(new LoginButtonActionListener(loginFrame, this));

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
