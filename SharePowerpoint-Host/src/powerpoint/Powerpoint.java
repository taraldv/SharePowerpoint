/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerpoint;

import java.awt.Robot;
import java.net.Socket;

/**
 *
 * @author Tarald
 */
public class Powerpoint {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        KeySender keySender = new KeySender(robot);
        Socket socket = new Socket("www.tarves.no", 3322); 
        TcpSocket tcpSocket = new TcpSocket(socket,keySender);
        tcpSocket.listen();
    }
}
