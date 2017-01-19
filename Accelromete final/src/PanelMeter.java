import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelMeter extends JPanel implements ActionListener{
    JLabel max,meter1,meter2,meter3,meter4,meter5,meter6;
	javax.swing.Timer timer;
	int lineX,lineY,temp;
	BufferedImage img;
	double max_acc,x,y,z;

	String xString,yString,zString;
	String[] line;
	
	
	public PanelMeter()
	{
		this.setBounds(5,5,320,205);
	    this.setBackground(Color.lightGray);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		addLabels();
		lineX=100;
		lineY=180;
		temp=0;
		addImage();
             
	}
	
	public void addLabels()
	{
		max = new JLabel("Max acceleration: ");
		max.setBounds(50, 10, 150, 50);
		max.setFont(new Font("Arial black", Font.PLAIN, 14));
		this.add(max);
		
		meter1=new JLabel("0.0");
		meter1.setBounds(10, 140, 50, 50);
		meter1.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter1);
		
		meter2=new JLabel("0.5");
		meter2.setBounds(35, 80, 50, 50);
		meter2.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter2);
		
		meter3=new JLabel("1.0");
		meter3.setBounds(100, 40, 50, 50);
		meter3.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter3);
		
		meter4=new JLabel("1.5");
		meter4.setBounds(190, 40, 50, 50);
		meter4.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter4);
		
		meter5=new JLabel("2.0");
		meter5.setBounds(260, 80, 50, 50);
		meter5.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter5);
		
		meter6=new JLabel("2.5");
		meter6.setBounds(290, 140, 50, 50);
		meter6.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(meter6);
		
	}
	
	@Override
	public  void paint(Graphics g)
	{
	super.paint(g);
	
	
    Graphics2D graphics2d= (Graphics2D) g.create();
    graphics2d.drawImage(img,35,70,this);
   
    g.setColor(Color.WHITE);   
	g.fillOval(151, 180, 20,20);
	
	Color oColor= Color.decode("#CC0000");
	g.setColor(oColor);   
	g.fillOval(154, 184, 13, 13);
	
	g.setColor(oColor);
	Graphics2D line2d= (Graphics2D) g.create();
	line2d.setStroke(new BasicStroke(4));
    
	line2d.drawLine(161, 191, lineX,lineY);
	
	line2d.setColor(Color.black);
	Font f = new Font("Arial black", Font.PLAIN, 16);
    line2d.setFont(f);
    DecimalFormat df = new DecimalFormat("#.##");
	
	line2d.drawString(df.format(calculateMaxACC())+" g",190, 40);

	
          
        
        
	}

	
	public void addImage()
	{
		try {
			File imageFile= new File("E:\\java\\Accelromete final\\meter2.png");
			img= ImageIO.read(imageFile);
		} catch (IOException e) {
			
			System.out.println("Error in loading image!");
			
		}
		
	}
	
	
	
	
	public void startTimer()
	{
		timer= new javax.swing.Timer(500, this);
		timer.start();
	}
	public void stopTimer()
	{
		timer.stop();
	}
	
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(calculateMaxACC()>0.0 && calculateMaxACC()<=0.50)
    	{
    		lineX=100;
    		lineY=180;
    		lineX+=0.5;
    		lineY-=0.5;
    	}
    	
    	else if(calculateMaxACC()<=1.00 && calculateMaxACC()>0.50)
    	{
    		lineX=130;
    		lineY=140;
    		lineX+=0.5;
    		lineY-=0.5;
    	}
    	
    	else if(calculateMaxACC()<=1.50 && calculateMaxACC()>1.00)
    	{
    		lineX=161;
    		lineY=130;
    		lineX+=0.5;
    		lineY-=0.5;
    	
    	}
    	
    	else if(calculateMaxACC()> 1.50 && calculateMaxACC()<=2.0)
    	{
    		lineX=190;
    		lineY=140;
    		lineX+=0.5;
    		lineY+=0.5;
    	}
    	
    	else if(calculateMaxACC()>2.00)
    	{
    		lineX=220;
    		lineY=170;
    		lineX+=0.5;
    		lineY+=0.5;
    	}
    	
   
   
   repaint(); 
      
    }
    
    public void setAccX(String acc_x)
	{
		xString=acc_x;
	    x= Double.parseDouble(xString);
	}
	
	
	public void setAccY(String acc_y)
	{
		yString=acc_y;
	    y= Double.parseDouble(yString);
	}	
	
	public void setAccZ(String acc_z)
	{
		zString=acc_z;
		z= Double.parseDouble(zString);
	}
	
	public void setText(String data)
	{
		line=data.split("a:");
		String accLine=line[1];
		
		
		String[] array=accLine.split(" ");
		
		//System.out.println(array[3]);
		
		setAccX(array[1]);
		setAccY(array[2]);
		setAccZ(array[3]);
	}
    
    public double calculateMaxACC()
    {
    	
    	max_acc=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
    	return max_acc;
    }
			

}