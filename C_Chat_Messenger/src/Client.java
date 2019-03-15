/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Client extends JFrame {
	
        private String clientName;
        
        private ObjectOutputStream output;//output from  client to server
	private ObjectInputStream input;//input from server to server
    
	private JTextField TextUser;//type message here
	private JTextArea WindowChat;// show the conversation
	
        private Socket socketConnection;// a connection between computers 
        
        private String message = "";
	private String IP_Server;	//server IP
	

	//constructor and GUI
	public Client(String host) {	
		super("C_Chat Client");
		
		IP_Server = host;	// same computer for client and server "host", or other IP instead

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // chat window that dispays the messenges that were sent
		WindowChat = new JTextArea();
		add(new JScrollPane(WindowChat), BorderLayout.NORTH);
		
		//user field
		TextUser = new JTextField();
		TextUser.setEditable(false);	// disable typing until connected
		TextUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
                                                //pass the text we typed into sendMessage method
						MessageSend(event.getActionCommand()); 
                                                //reseting the text field, shows nothing
						TextUser.setText("");		
					}
                });
		add(TextUser, BorderLayout.CENTER);
        	setSize(450, 450);
		Login login = new Login(this);
		login.setVisible(true);
                add(TextUser, BorderLayout.NORTH);
                }
	
	//connect to server
	public void startToRun() {
		try {
			//server starts and waits for clients to connect
			ServerConnect();			
			setupStreams();
			whileChatting();
		}catch(EOFException eof) {	
                        //message is shown when connection is over
			MessageShow("\nClient ended connection");
		}catch(IOException ioe) {	
			ioe.printStackTrace();
		}finally {	
                        //close streams
			CloseAll();
		}
	}
	//connect to server
	private void ServerConnect() throws IOException {
		MessageShow("Trying to connect... \n");
		//passing IP address
		socketConnection = new Socket(InetAddress.getByName(IP_Server), 5000);
		//display connection info
		MessageShow("Connected to: " + socketConnection.getInetAddress().getHostName()); //get server IP
        }
 
	//setup streams to send and receive messages
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(socketConnection.getOutputStream());  //get output stream - client to server
		output.flush();	//flush all the crap through the pipes
		input = new ObjectInputStream(socketConnection.getInputStream());
		MessageShow("\n Connected \n");
	}	
	//chatting with server
	private void whileChatting() throws IOException {
		AllowToType(true);
		do {			
			try{
				message = (String) input.readObject();
				MessageShow("\n" + message );
			}catch(ClassNotFoundException classNotFound) {
                         //when the object that is not string is sent
				MessageShow("\n The message of this format can't be displayed");
			}
                }while(!message.equals("SERVER - END"));
	}
	//close streams and sockets after chatting
	private void CloseAll() {
                //show message
		MessageShow("\n C_Chat is closing...");
		AllowToType(false);
		try {
                        //close stream from client
			output.close();
			// close stream to client
                        input.close();
			socketConnection.close();
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}}
	//send message to server
	private void MessageSend(String msg) {
		try {
			output.writeObject(clientName + " : " + msg);
			output.flush();
			MessageShow("\n" + clientName + " : " + msg);
		}
		catch(IOException ioe) {
			WindowChat.append("\n The message can't be sent");
		}
	}
//update WindowChat
//thread that updates part of the GUI
	private void MessageShow(final String i) {
		SwingUtilities.invokeLater(new Runnable() {		
					public void run() {
                                                //add string to the end
						WindowChat.append(i);  
					}
				});
	
}
        //let the user type stuff into their box
	private void AllowToType(final boolean tof) {
		
		SwingUtilities.invokeLater(new Runnable() {		// create thread
					public void run() {
						TextUser.setEditable(tof);
					}
				});
	}
	public void clientNameSet(String n) {
		clientName = n;
	}
	public String clientNameGet() {
		return clientName;
	}
}





















