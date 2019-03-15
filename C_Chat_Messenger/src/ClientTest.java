/**
 *
 * @author irina
 */
import javax.swing.JFrame;

public class ClientTest {
	public static void main(String[] args) {
		Client ClientCChat;
                //local host
		ClientCChat = new Client("127.0.0.1");
		ClientCChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClientCChat.startToRun();
	}
}