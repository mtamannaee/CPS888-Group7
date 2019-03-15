/*
 * @author irina
 */


import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {

	private JTextField TextUser;	//type message here
	private JTextArea WindowChat;	// show the conversation
	private JLabel Lbl;
	
        //streams
	private ObjectOutputStream output;//output from server to computer
	private ObjectInputStream input;//input from client to server	
	private ServerSocket server; //at server wait for clients to connect
	private Socket socketConnection; //connection between computers
	
	//constr - GUI
	public Server() {
		super("C_Chat Server");
		
		WindowChat = new JTextArea();
		WindowChat.setEditable(false);
		add(new JScrollPane(WindowChat));		

		JPanel t = new JPanel();
		t.setLayout(new FlowLayout());
		TextUser = (new JTextField("",15));
		TextUser.setSize(350, 20);
		TextUser.setText("    waiting for other users to connect   ");
		TextUser.setEditable(false); 	
		TextUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
                                                //pass the text we typed into sendMessage method
						MessageSend(event.getActionCommand()); 
                                                //reseting the text field, shows nothing
						TextUser.setText(""); 
					}});
		
		Lbl = new JLabel(" Message: ");
		Lbl.setSize(100, 15);
		t.add(Lbl);
		t.add(TextUser);
		add(t, BorderLayout.NORTH);		
		setSize(300,150);
                //visible on the screen
		setVisible(true);
	}
	//set up and run the server	
	public void startToRun() {
		try {
                        //the first num is port (location of the program we are looking for
                        //on server)
                        //the second num is backlog(how many people can wait until 
                        //connection to messenger)
			server = new ServerSocket(5000, 100);	// port number 5000, 
													
			while (true) {
				try {
					//start the program and wait for connection
					waitToConnect();
					//setup connection between user and server
					StreamsSetup();
					//while connected send messages to each other
					Chatting();	
				}
				catch(EOFException eofException) {		
                                        //message is shown when connection is over
					MessageShow("\n Connection is over");
				}
				finally {
					closeAll();
				}}}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}}	
       //wait for connection, then display connection information	
	private void waitToConnect() throws IOException {
                //wait other people to connect
		MessageShow("Waiting for connection\n");
		// when there is connection the socket is created between server and 
                //client
		socketConnection = server.accept();	      // returns IP address	
		MessageShow("Connected to " + socketConnection.getInetAddress().getCanonicalHostName());			
		TextUser.setText("");
		
}      
	// get stream to send and receive data
	private void StreamsSetup() throws IOException {
                //the path to another computer
		output = new ObjectOutputStream(socketConnection.getOutputStream());
                //flash leftover data
		output.flush();	
                //the path into computer
		input = new ObjectInputStream(socketConnection.getInputStream());	
		//only the other one can flush to you, no flush for input
		MessageShow("\nStreams are setup\n");
        }

	//during the chat conversation
	private void Chatting() throws IOException {
		String message = "You are connected" ; 
		MessageSend(message);
                //allow user to type
		AllowToType(true);	
                //loops till user types END
		do {
			// have a conversation
			try {
				message = (String) input.readObject();		
				MessageShow("\n" + message);
			}
			catch(ClassNotFoundException cnfe) {	
                                //when the object that is not string is sent
				MessageShow("\n The message of this format can't be displayed");
			}
                //have a conversation
                //typing "END" stop the conversation
		}while(!message.equals("CLIENT - END"));	
	}
	
	//close connection
	private void closeAll() throws IOException {
		MessageShow("\nConnection is closing...\n");
		AllowToType(false);	
		try {
			//close output stream
			output.close();
                        //close input stream
			input.close();
                        //close socket
			socketConnection.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}}
	//send message to client
	private void MessageSend(String msg) {
		try{
			output.writeObject("SERVER :  " + msg);	 
			output.flush();		
			MessageShow("\nSERVER : " + msg); 	
		}
		catch(IOException ioe) {
			WindowChat.append("\n The message can't be sent");
		}}
	//update WindowChat
        //thread that updates part of the GUI
	private void MessageShow(final String text) {
		//update GUI component /dynamic
		
		// allows to create a thread that updates the GUI
		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						WindowChat.append(text);	
					}
				});}	
	//allows users to tyoe
	private void AllowToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						TextUser.setEditable(tof);	
					}
				});}}



