/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.awt.Cursor;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hawad
 */
public class UsernameDialog extends javax.swing.JDialog {

    /**
     * Creates new form UsernameDialog
     */
    private String username;
    private String serverAddr;

    public UsernameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        usernameTextField = new javax.swing.JTextField();
        serverAddrTxtfield = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        warningLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Username");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        usernameTextField.setToolTipText("username");
        usernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyTyped(evt);
            }
        });

        serverAddrTxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                serverAddrTxtfieldKeyPressed(evt);
            }
        });

        jButton1.setText("Connect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Username:");

        warningLabel.setForeground(new java.awt.Color(204, 0, 0));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Server address:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(warningLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField)
                            .addComponent(serverAddrTxtfield)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 147, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverAddrTxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usernameTextField.getAccessibleContext().setAccessibleName("");
        usernameTextField.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        username = usernameTextField.getText();
        if (hasSpecialChars(username)) {
            warningLabel.setText("Username cannot contain special characters, spaces or numbers");
        } else {
            connect();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usernameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyPressed

    }//GEN-LAST:event_usernameTextFieldKeyPressed

    private void usernameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyTyped
        //if pressed enter key
        if ((int) evt.getKeyChar() == 10) {
            username = usernameTextField.getText();
            if (hasSpecialChars(username)) {
                warningLabel.setText("Username cannot contain special characters, spaces or numbers");
            } else {
                if (!serverAddrTxtfield.getText().equals("")) {
                    connect();
                } else {
                    serverAddrTxtfield.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_usernameTextFieldKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //if users closes the dialog, exit
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void serverAddrTxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverAddrTxtfieldKeyPressed
        if ((int) evt.getKeyChar() == 10) {
            serverAddr = serverAddrTxtfield.getText();
            username = usernameTextField.getText();
            if (hasSpecialChars(username)) {
                warningLabel.setText("Username cannot contain special characters, spaces or numbers");
            } else {
                connect();
            }
        }
    }//GEN-LAST:event_serverAddrTxtfieldKeyPressed
    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField serverAddrTxtfield;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    //checks if every character is a letter
    private boolean hasSpecialChars(String word) {
        if (word.equals("")) {
            return true;
        }
        for (char s : word.toCharArray()) {
            if (!(s >= 'A' && s <= 'Z') && !(s >= 'a' && s <= 'z')) {
                return true;
            }
        }
        return false;
    }

    private void connect() {
        Cursor loadingCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        Cursor textCursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);

        setCursor(loadingCursor);
        usernameTextField.setCursor(loadingCursor);
        serverAddrTxtfield.setCursor(loadingCursor);
        try {
            socket = new Socket(serverAddr, 25000);
            dispose();
        } catch (IOException ex) {
            Logger.getLogger(UsernameDialog.class.getName()).log(Level.SEVERE, null, ex);
            showError();
        } finally {
            setCursor(Cursor.getDefaultCursor());
            usernameTextField.setCursor(textCursor);
            serverAddrTxtfield.setCursor(textCursor);
        }
    }
    private Socket socket;

    private void showError() {
        JOptionPane.showMessageDialog(this,
                "Couldn't connect to server!\nCheck your server address",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
