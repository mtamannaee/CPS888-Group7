
import javax.swing.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
public class Login extends JFrame {
	
	static String IP_serv = "localhost";
        private JPasswordField Password;
	private String Username;
        private JTextField TextUser;
	

	public Login(Client c) {
		super("Login");
		setSize(350,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JPanel p = new JPanel();
		 p.setLayout(null);
		 JLabel userLabel = new JLabel("User");
			userLabel.setBounds(15, 15, 70, 20);
			p.add(userLabel);

			TextUser = new JTextField(20);
			TextUser.setBounds(150, 15, 150, 20);
			p.add(TextUser);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(5, 35, 76, 20);
			p.add(passwordLabel);

			Password = new JPasswordField(20);
			Password.setBounds(100, 40, 160, 25);
			p.add(Password);

			JButton loginButton = new JButton("Login");
			loginButton.setBounds(15, 85, 85, 20);
			
			loginButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							if (verify())
							{	c.clientNameSet(Username);
								c.setVisible(true);
								close();
							}}});
			
			p.add(loginButton);
			
			JButton exitButton = new JButton("Exit");
			exitButton.setBounds(170, 70, 70, 20);
			p.add(exitButton);	
			add(p);}

	public boolean verify() {
	       try{
	       
	           Class.forName("com.mysql.jdbc.Driver");

	           java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://" + IP_serv  + "/chat", "cps", "888");
 
	           //java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://" + servIP + "/chat?user=lenny&password=abc123&useUnicode=true&characterEncoding=UTF-8");

	           Statement  st  = con.createStatement(); 
	           
	           //SQL query
	           String query = "SELECT * FROM users "
	                   + "WHERE username = '" + TextUser.getText().trim()  + "'" + 
	                   " AND password = '" + String.valueOf(Password.getPassword())  + "';";
	           
	           //password.getPassword() returns a char[]
	           
	             //System.out.println(query);
	           
	           //process result
	           ResultSet rs = st.executeQuery(query);
	           if (!rs.next()) {
	               System.out.println("No results");
	               JOptionPane.showMessageDialog(null, "Account doesn't exist, try again");
	               
	               
	           }
	           else
	           {
	               JOptionPane.showMessageDialog(null, "Welcome to C_Chat " + rs.getString("username"));
	               Username = rs.getString("username");
	               con.close();
	               return true;
	             
	           }

	           con.close();
	           }
	           catch(Exception e){
	               e.printStackTrace();
	           }
	           //*************************************
	       
	       	return false;		
	}
	
	public void close() {
		this.dispose();
	}
	
	
}