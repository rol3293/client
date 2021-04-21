/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Hawad
 */
public class Frame extends javax.swing.JFrame {

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        chat = new com.mycompany.client.textpane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Client");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Connected Users (0)");

        list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(list);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        message.setColumns(20);
        message.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        message.setLineWrap(true);
        message.setRows(5);
        message.setWrapStyleWord(true);
        jScrollPane2.setViewportView(message);

        jScrollPane3.setViewportView(chat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //gets the image
        java.net.URL url = Frame.class.getResource("/images/scale.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);

        Frame frame = new Frame();
        frame.setLocationRelativeTo(null);
        frame.setIconImage(img);
        frame.createTray();
        frame.show();
        frame.connectToServer();

    }

    private void connectToServer() {
        //initialize the dialog for the username
        UsernameDialog dialog = new UsernameDialog(this, true);
        dialog.setLocationRelativeTo(null);
        dialog.setIconImage(getIconImage());
        list.setModel(model);
        //try to connect to the server
        try {
            //open the username dialog 
            dialog.show();

            socket = dialog.getSocket();
            //get and store the username that the user wrote in the dialog
            String username = dialog.getUsername();
            //change the title to show the user his own username displayed
            setTitle("Justice Messenger (" + username + ")");
            //start to read with another thread
            readerThread = new ReaderThread(socket, this);
            readerThread.start();
            //sends this username
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(username);
        } catch (IOException ex) {
            //close application if failed to connect to the server
            System.err.println("Connection error");
            System.exit(0);
        }
        //focus the message textarea
        message.requestFocus();
        //sets some attributes to the Frame
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new windowAdapter(writer, readerThread));

        //set a keybind to the jtextarea(message JtextArea)
        int condition = WHEN_FOCUSED;
        // get our maps for binding from the message JTextArea
        InputMap inputMap = message.getInputMap(condition);
        ActionMap actionMap = message.getActionMap();

        // the key stroke we want to capture
        KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

        // tell input map that we are handling the enter key
        inputMap.put(enterStroke, enterStroke.toString());

        // tell action map just how we want to handle the enter key
        actionMap.put(enterStroke.toString(), new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String text = message.getText();
                if (text.length() > 500) {
                    addText("Message length connot be bigger than 500 characters!");
                    message.setText("");
                } else {
                    writer.println(text);
                    message.setText("");
                }
            }
        });
    }

    //appends the message to the chat
    public void addText(String text) {

        StyledDocument doc = chat.getStyledDocument();
        try {

            doc.insertString(doc.getLength(), "\n" + text, new SimpleAttributeSet());
            chat.setCaretPosition(doc.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setClientCount(int count) {
        jLabel1.setText("Connected Users (" + count + ")");
    }
    private Socket socket;
    private PrintWriter writer;
    private ReaderThread readerThread;
    public final DefaultListModel<String> model = new DefaultListModel<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.client.textpane chat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> list;
    private javax.swing.JTextArea message;
    // End of variables declaration//GEN-END:variables

    private void createTray() {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        java.net.URL url = Frame.class.getResource("/images/scale.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(img, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Justice Messenger");
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.NONE);
    }

}

class windowAdapter extends WindowAdapter {

    private final PrintWriter w;
    private final ReaderThread rt;

    public windowAdapter(PrintWriter w, ReaderThread rt) {
        this.w = w;
        this.rt = rt;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        w.close();
        rt.closeEverything();
    }
}
