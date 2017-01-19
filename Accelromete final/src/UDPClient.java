/*
 * For AIUB Mars Rover Communication & Interface Team.
 * Author - Mahmud, Minar (minarmahmud@yahoo.com)
 * 
 */

/*	
 * You have to call 2 methods from your code
 * 1. public void makeRequest()
 * 2. public String getServerMessage()
 * 
 * Process -
 * 
 * Create an object of this class using 'UDPClient(InetAddress serverIP, int serverPort)'
 * Call 'void makeRequest()'
 * Call 'String getServerMessage()' immediately after that to get a String data from this class
 * Parse the String according to your need
 * Do the whole process in a 'while(true)' to get data continuously
 * 
 */


import java.net.*;

import javax.swing.*;

public class UDPClient {
	
	private InetAddress serverIP, clientIP; 
	private int serverPort, clientPort;
	
	private DatagramSocket udpSocket;
	private DatagramPacket outPacket, inPacket;
	
	private byte[] buffer;
	
	private String msgIn, msgOut = "1";
	
	public UDPClient() {
	}
	
	public UDPClient(InetAddress serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		createClient();
	}
	
	
	public void createClient() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			JOptionPane.showMessageDialog(null, "createClient() error!");
			e.printStackTrace();
		}
	}
	
	public void makeRequest() {			// connects to server, receive data, stores it in a member attribute named 'msgIn'
		
            outPacket = new DatagramPacket(msgOut.getBytes(), 0, msgOut.length(), serverIP, serverPort);
            try {
                    udpSocket.send(outPacket);
                    buffer = new byte[256];
                    inPacket = new DatagramPacket(buffer, buffer.length);
                    udpSocket.receive(inPacket);
                    msgIn = new String(inPacket.getData(), 0, inPacket.getLength());
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "makeRequest() error!");
                    e.printStackTrace();
            }
            
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//
//			}
//		});
//		
//		t.start();
	}
	
	public String getServerMessage() {		// Returns received and stored data from this class
		return this.msgIn;
	}
}
