/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hawad
 */
public class ReaderThread extends Thread implements Runnable {

    private final Socket socket;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader reader;
    private final Frame frame;
    private int onlineClients = 0;

    public ReaderThread(Socket socket, Frame frame) {
        this.socket = socket;
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                reader = new BufferedReader(isr);
                //updates the client list
                setClientList();
                while (true) {
                    //receive for a message and store in receivedMessage
                    String receivedMessage = reader.readLine();
                    //if its null it means that its disconnected from the server
                    if (receivedMessage == null) {
                        frame.addText("Disconnected from server!");
                        break;
                    } else {
                        //if someone connected
                        if (receivedMessage.endsWith(" connected")) {
                            //gets store the name of the client who connected
                            String clientName = receivedMessage.substring(0, receivedMessage.indexOf(" connected"));
                            //adds that name to the client list
                            frame.model.addElement(clientName);
                            //increments the amount of people online by 1
                            onlineClients++;
                            //updates the label
                            frame.setClientCount(onlineClients);
                        } //if someone disconnected
                        else if (receivedMessage.endsWith(" disconnected")) {
                            //gets and store the name of the client who disconnected
                            String clientName = receivedMessage.substring(0, receivedMessage.indexOf(" disconnected"));
                            //removes that name from the client list
                            frame.model.removeElement(clientName);
                            //decrements the amount of people online by 1
                            onlineClients--;
                            //updates the label to show the correct ammount of users online
                            frame.setClientCount(onlineClients);
                        }
                        //shows the received message to the textpane
                        frame.addText(receivedMessage);
                        
                    }
                }
            } catch (IOException ex) {
                System.err.println("Error: closing application");
                break;
            }
        }
        closeEverything();
    }

    void closeEverything() {
        try {
            is.close();
            isr.close();
            reader.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ReaderThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    private void setClientList() {

        try {
            String name = reader.readLine();
            while (!name.equals("/")) {
                frame.model.addElement(name);
                name = reader.readLine();
                onlineClients++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ReaderThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setClientCount(onlineClients);
    }

}
