import UI.Frame;
import UI.LoginFrame;
import UI.SendMailFrame;

public class Main {
    public static void main(String[] args) {
        Frame frame = new LoginFrame();
        frame.pack();

        Frame frame2 = new SendMailFrame();
        frame2.pack();
    }
}
