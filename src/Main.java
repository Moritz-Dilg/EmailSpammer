import Listeners.LoginButtonActionListener;
import Listeners.SendMailButtonActionListener;
import UI.LoginFrame;
import UI.SendMailFrame;

public class Main {
    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.draw();
        frame.addButtonListener(new LoginButtonActionListener(frame));

        SendMailFrame frame2 = new SendMailFrame();
        frame2.draw();
        frame2.addButtonListener(new SendMailButtonActionListener(frame2));
    }
}
