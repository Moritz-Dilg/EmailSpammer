import UI.Frame;
import UI.LoginFrame;
import UI.SendMailFrame;

public class Main {
    public static void main(String[] args) {
        Frame frame = new LoginFrame();
        frame.draw();

        Frame frame2 = new SendMailFrame();
        frame2.draw();
    }
}
