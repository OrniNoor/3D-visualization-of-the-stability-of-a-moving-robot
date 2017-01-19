/* Accelerometer and Gyroscope GUI for MARS ROVER of AIUB
   developed by Sefat Noor Orni */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class MainFrame extends JFrame implements ActionListener {
	
	JLabel ipLabel, portLabel;
	JTextField ipField, portField;
	JButton connectButton;
	String xString,yString, zString;
    static PanelMeter panelMeter;
    static Panel3D panel3d;
    static PanelCompass panelCompass;
    static PanelValue panelValue;
    Thread thread;
	Timer timer;
	UDPClient udpClient;
	
    double x,y,z,xx=0;
	 
	double ax,ay,az;
	String line;
	
	
	public MainFrame()
	{
		this.setSize(850,700);
        this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Accelerometer & Gyroscope GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(100, this);
		
	    addComponents();
		addPanels();
	    
		this.setVisible(true);
	}
	
	public void addComponents()
	{
	 Font f = new Font("Arial black", Font.PLAIN, 16);
	 ipLabel= new JLabel("IP address: ");
	 ipLabel.setBounds(380, 530, 100, 30);
	 ipLabel.setFont(new Font("Arial", Font.BOLD, 16));
	 this.add(ipLabel);
	 
	 ipField= new JTextField();
	 ipField.setBounds(480, 530, 150, 30);
	 ipField.setFont(f);
	 this.add(ipField);
	 
	 portLabel= new JLabel("Port: ");
	 portLabel.setBounds(430, 570, 100, 30);
	 portLabel.setFont(new Font("Arial", Font.BOLD, 16));
	 this.add(portLabel);
	 
	 
	 portField= new JTextField();
	 portField.setBounds(480, 570, 150, 30);
	 portField.setFont(f);
	 this.add(portField);
	 
	 connectButton= new JButton("Connect");
	 connectButton.setBounds(650, 535, 150, 60);
	 this.add(connectButton);
	 Font font=new Font("Arial", Font.BOLD, 16);
	 connectButton.setFont(font);
	 connectButton.setBackground(Color.green);
	 connectButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				if(connectButton.getText().equals("Connect"))
				{
				try {                   // connecting to server
				udpClient= new UDPClient(InetAddress.getByName(ipField.getText()), Integer.parseInt(portField.getText()));
				}
				 catch(Exception exc) {
                        JOptionPane.showMessageDialog(null, "Connection error!");
                        return;
                    }
					
					connectButton.setText("Disconnect");
				    connectButton.setBackground(Color.red);
				
				  thread= new Thread(new Runnable() {
					  
					@Override
					public synchronized void run() {
						
					    panelMeter.startTimer();
						panel3d.startTimer();
						panelCompass.startTimer();
						panelValue.startTimer();
					
						startReading();	
						}
				});
				  
				  thread.start();
				
							}
				else if(connectButton.getText().equals("Disconnect"))	{
						
					   
						connectButton.setText("Connect");
						connectButton.setBackground(Color.green);
						panel3d.stopTimer();
						panelCompass.stopTimer();
					
						panelMeter.stopTimer();
						panelValue.stopTimer();
						
						System.out.print("timer stopped!"); 
						thread.interrupt();
					}
	}
	
		
	public void addPanels()
	{
	    panelValue= new PanelValue();
	    panelCompass= new PanelCompass();
		panelMeter= new PanelMeter();
		panel3d= new Panel3D();
              
		add(panel3d);
		add(panelCompass);
		add(panelMeter);
		add(panelValue);
	}
	


public void startReading()
{
	int count = 0;
	
	while(true)
	{
		if(thread.currentThread().isInterrupted())
		{
			break;
		}
		count++;
		udpClient.makeRequest();
		String data = udpClient.getServerMessage();
		
		if(count<10) {
			continue;
		}
		else{
		System.out.println("main: " + data);
        panelValue.setText(data);
		panelCompass.setText(data);
		panel3d.setText(data);
		panelMeter.setText(data);	
		}
	}
}

	

}
