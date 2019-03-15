package chat_client;

import java.net.*;
import java.io.*;
import java.util.*;

public class client_frame extends javax.swing.JFrame 
{
    String nameUser, Addr = "localhost";
    ArrayList<String> Users = new ArrayList();
    int Port = 1000;
    Boolean isConnected = false;
    
    Socket socket;
    BufferedReader Reader;
    PrintWriter Writer;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         Users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         C_Chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(Users.size())];
         Users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (nameUser + ": :Disconnect");
        try
        {
            Writer.println(bye); 
            Writer.flush(); 
        } catch (Exception e) 
        {
            C_Chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            C_Chat.append("Disconnected.\n");
            socket.close();
        } catch(Exception ex) {
            C_Chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        type_username.setEditable(true);

    }
    
    public client_frame() 
    {
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Login", disconnect = "LogOff", chat = "Chat";

            try 
            {
                while ((stream = Reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        C_Chat.append(data[0] + ": " + data[1] + "\n");
                        C_Chat.setCaretPosition(C_Chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        C_Chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        Users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        background = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        C_Chat = new javax.swing.JTextArea();
        Chat_Name = new javax.swing.JLabel();
        type_username = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        lb_password = new javax.swing.JLabel();
        type_passw = new javax.swing.JTextField();
        Anonym_Button = new javax.swing.JButton();
        Login_Button = new javax.swing.JButton();
        LogOff_Button = new javax.swing.JButton();
        type_window = new javax.swing.JTextField();
        Send_button = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 223, 96));

        C_Chat.setColumns(20);
        C_Chat.setRows(5);
        jScrollPane1.setViewportView(C_Chat);

        Chat_Name.setFont(new java.awt.Font("Sitka Text", 3, 72)); // NOI18N
        Chat_Name.setForeground(new java.awt.Color(102, 102, 102));
        Chat_Name.setText("C_Chat");

        type_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_usernameActionPerformed(evt);
            }
        });

        lb_username.setText("Username :");

        lb_password.setText("Password : ");

        Anonym_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Anonym_Button.setText("Anonymous Login");
        Anonym_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Anonym_ButtonActionPerformed(evt);
            }
        });

        Login_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Login_Button.setText("Login");
        Login_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_ButtonActionPerformed(evt);
            }
        });

        LogOff_Button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        LogOff_Button.setText("Logoff");
        LogOff_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOff_ButtonActionPerformed(evt);
            }
        });

        Send_button.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Send_button.setText("SEND");
        Send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Chat_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_username)
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addComponent(type_username, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Anonym_Button))
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addComponent(type_passw, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Login_Button)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LogOff_Button)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(type_window, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Send_button, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(type_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Anonym_Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_password)
                        .addGap(2, 2, 2)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(type_passw)
                            .addComponent(Login_Button)
                            .addComponent(LogOff_Button)))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(Chat_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_window, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Send_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        background.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void type_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_usernameActionPerformed
    
    }//GEN-LAST:event_type_usernameActionPerformed

    private void Login_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_ButtonActionPerformed
        if (isConnected == false) 
        {
            nameUser = type_username.getText();
            type_username.setEditable(false);

            try 
            {
                socket = new Socket(Addr, Port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                Reader = new BufferedReader(streamreader);
                Writer = new PrintWriter(socket.getOutputStream());
                Writer.println(nameUser + ":has connected.:Connect");
                Writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                C_Chat.append("Cannot Connect! Try Again. \n");
                type_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            C_Chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_Login_ButtonActionPerformed

    private void LogOff_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOff_ButtonActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_LogOff_ButtonActionPerformed

    private void Anonym_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Anonym_ButtonActionPerformed
        type_username.setText("");
        if (isConnected == false) 
        {
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            nameUser=anon;
            
            type_username.setText(anon);
            type_username.setEditable(false);

            try 
            {
                socket = new Socket(Addr, Port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                Reader = new BufferedReader(streamreader);
                Writer = new PrintWriter(socket.getOutputStream());
                Writer.println(anon + ":has connected.:Connect");
                Writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                C_Chat.append("Cannot Connect! Try Again. \n");
                type_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            C_Chat.append("You are already connected. \n");
        }        
    }//GEN-LAST:event_Anonym_ButtonActionPerformed

    private void Send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_buttonActionPerformed
        String nothing = "";
        if ((type_window.getText()).equals(nothing)) {
            type_window.setText("");
            type_window.requestFocus();
        } else {
            try {
               Writer.println(nameUser + ":" + type_window.getText() + ":" + "Chat");
               Writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                C_Chat.append("Message was not sent. \n");
            }
            type_window.setText("");
            type_window.requestFocus();
        }

        type_window.setText("");
        type_window.requestFocus();
    }//GEN-LAST:event_Send_buttonActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Anonym_Button;
    private javax.swing.JTextArea C_Chat;
    private javax.swing.JLabel Chat_Name;
    private javax.swing.JButton LogOff_Button;
    private javax.swing.JButton Login_Button;
    private javax.swing.JButton Send_button;
    private javax.swing.JPanel background;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextField type_passw;
    private javax.swing.JTextField type_username;
    private javax.swing.JTextField type_window;
    // End of variables declaration//GEN-END:variables
}
