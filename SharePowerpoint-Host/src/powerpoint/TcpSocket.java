/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerpoint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author
 */
public class TcpSocket {

    private final Socket socket;
    private final KeySender keySender;

    public TcpSocket(Socket socket, KeySender keySender) {
        this.socket = socket;
        this.keySender = keySender;
    }

    public void listen() throws IOException {
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        Byte b = 0;
        dout.write(b);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        while (true) {
            if (din.available() > 0) {
                byte buffer[] = new byte[din.available()];
                din.readFully(buffer);
                parseBuffer(buffer);
            }
        }
    }

    private void parseBuffer(byte[] buffer) {
        String output = "";
        for (int i = 0; i < buffer.length; i++) {
            output += (char)buffer[i];
        }
        keySender.sendKey(output);
    }

}
