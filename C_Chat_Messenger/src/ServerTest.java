import javax.swing.JFrame;

/**
 *
 * @author irina
 */
public class ServerTest {
    public static void main(String[] args){
        Server ServerCChat= new Server();
        ServerCChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ServerCChat.startToRun();
        
    }
    
    
    
}
