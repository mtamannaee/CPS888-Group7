package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList<String> Users;
   ArrayList ClientOutputStreams;
   
   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket Socket;
       PrintWriter Client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            Client = user;
            try 
            {
                Socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(Socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                c_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    c_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        c_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ": disconnected" + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                        c_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                c_chat.append("Connection is lost \n");
                ex.printStackTrace();
                ClientOutputStreams.remove(Client);
             }}}

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        Chat_Name = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        c_chat = new javax.swing.JTextArea();
        Start_Button = new javax.swing.JButton();
        End_Button = new javax.swing.JButton();
        Online_Users_Button = new javax.swing.JButton();
        Clear_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        background.setBackground(new java.awt.Color(102, 102, 102));

        Chat_Name.setBackground(new java.awt.Color(255, 204, 51));
        Chat_Name.setFont(new java.awt.Font("Sitka Text", 3, 72)); // NOI18N
        Chat_Name.setForeground(new java.awt.Color(255, 216, 101));
        Chat_Name.setText("C_Chat");

        c_chat.setColumns(20);
        c_chat.setRows(5);
        jScrollPane1.setViewportView(c_chat);

        Start_Button.setBackground(new java.awt.Color(204, 204, 204));
        Start_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Start_Button.setText("START");
        Start_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_ButtonActionPerformed(evt);
            }
        });

        End_Button.setBackground(new java.awt.Color(204, 204, 204));
        End_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        End_Button.setText("END");
        End_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                End_ButtonActionPerformed(evt);
            }
        });

        Online_Users_Button.setBackground(new java.awt.Color(204, 204, 204));
        Online_Users_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Online_Users_Button.setText("Online Users");
        Online_Users_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_Users_ButtonActionPerformed(evt);
            }
        });

        Clear_Button.setBackground(new java.awt.Color(204, 204, 204));
        Clear_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Clear_Button.setText("Clear");
        Clear_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(Chat_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Start_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Clear_Button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Online_Users_Button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(End_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(Chat_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Start_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(End_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Online_Users_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Clear_Button)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void End_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_End_ButtonActionPerformed
        try 
        {
            Thread.sleep(4500);                 
        } 
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();}
        
        tellEveryone("Server: Server is shutting down \n:Chat");
        c_chat.append("Server stopping... \n");
        c_chat.setText("");
    }//GEN-LAST:event_End_ButtonActionPerformed

    private void Start_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start_ButtonActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        c_chat.append("Server started...\n");
    }//GEN-LAST:event_Start_ButtonActionPerformed

    private void Online_Users_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_Users_ButtonActionPerformed
        c_chat.append("\n Online users : \n");
        for (String current_user : Users)
        {
            c_chat.append(current_user);
            c_chat.append("\n");
        }    
        
    }//GEN-LAST:event_Online_Users_ButtonActionPerformed

    private void Clear_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_ButtonActionPerformed
        c_chat.setText(""); //reset
    }//GEN-LAST:event_Clear_ButtonActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            ClientOutputStreams = new ArrayList();
            Users = new ArrayList();  
            try 
            {
                ServerSocket serverSock = new ServerSocket(1000);
                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				ClientOutputStreams.add(writer);
				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				c_chat.append("Connection is made \n");
                }
            }
            catch (Exception ex)
            {
                c_chat.append("ERROR: no connection \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Connected", name = data;
        c_chat.append("Before " + name + " added. \n");
        Users.add(name);
        c_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(Users.size())];
        Users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Connected", name = data;
        Users.remove(name);
        String[] tempList = new String[(Users.size())];
        Users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = ClientOutputStreams.iterator();
        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		c_chat.append("Sending: " + message + "\n");
                writer.flush();
                c_chat.setCaretPosition(c_chat.getDocument().getLength());
            } 
            catch (Exception ex) 
            {
		c_chat.append("Error to tell \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Chat_Name;
    private javax.swing.JButton Clear_Button;
    private javax.swing.JButton End_Button;
    private javax.swing.JButton Online_Users_Button;
    private javax.swing.JButton Start_Button;
    private javax.swing.JPanel background;
    private javax.swing.JTextArea c_chat;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
