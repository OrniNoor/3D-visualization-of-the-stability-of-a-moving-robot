
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.sun.prism.shader.FillCircle_Color_AlphaTest_Loader;
/**
 *
 * @author Orni
 */
public class PanelCompass extends JPanel implements ActionListener{
   
	JLabel compassImage;
    JLabel acc_x, acc_y, acc_z;
    Timer timer;
    int x_fillX1, y_fillX1, z_fillX1;
    double x,y,z,xx=0;
    String colorStringX,colorStringY,colorStringZ;
    double ax,ay,az;
    String xString,yString,zString;
	String[] line;
   
	 public PanelCompass()
    {
        this.setBounds(5, 210, 320, 180);                                                           
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		acc_x = new JLabel("Acc_X: ");
		acc_x.setBounds(50, 20, 50, 30);
		acc_x.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(acc_x);
		
		
		
		acc_y  = new JLabel("Acc_Y: ");
		acc_y.setBounds(50, 70, 50,30);
		acc_y.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(acc_y);
		
		
		
		acc_z  = new JLabel("Acc_Z: ");
		acc_z.setBounds(50, 120,50,30);
        acc_z.setFont(new Font("Arial black", Font.PLAIN, 12));
		this.add(acc_z);
		
		x_fillX1=101;
		y_fillX1=101;
		z_fillX1=101;
		
		colorStringX="#cc0000";
		colorStringY="#cc0000";
		colorStringZ="#cc0000";
		
		
		
		
	  }
	 
	
    
  
    
    public void startTimer()
	{
    	timer= new javax.swing.Timer(1000,this);
	    timer.start();
	}
	public void stopTimer()
	{
		timer.stop();
	}
    
  
    @Override
	public void paint(Graphics g)
	{
	super.paint(g);
	
	Graphics2D line2d= (Graphics2D) g.create();
	line2d.setStroke(new BasicStroke(3));
	line2d.drawRect(100,20,150, 30);
	line2d.drawRect(100,70,150, 30);
	line2d.drawRect(100,120,150, 30);
	
	Color xColor= Color.decode(colorStringX);
	Color yColor= Color.decode(colorStringY);
	Color zColor= Color.decode(colorStringZ);
	g.setColor(xColor);   
	
	
	g.fillRect(102, 21, x_fillX1, 29);
	g.setColor(yColor);
	g.fillRect(102, 71, y_fillX1, 29);
	g.setColor(zColor);
	g.fillRect(102, 121, z_fillX1, 29);
	
	g.setColor(Color.black);
	Font f = new Font("Arial black", Font.PLAIN, 16);
    g.setFont(f);
	g.drawString(""+x,180 ,40);
	g.drawString(""+y,180 ,90);
	g.drawString(""+z,180 ,140);
	

	
	
	}
    
    public void updateValues(double x, double y, double z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		Random random1= new Random();
		Random random2= new Random();
		Random random3= new Random();
		
	
   // x portion
		
		if(x<=0.00 || x>2.00)
		{
			colorStringX= "#CC0000";
	        x_fillX1= 20;
					
		}
		
		else if(x>0.00 && x<0.15)
		{
			colorStringX= "#FFA500";
	        x_fillX1= 50+random1.nextInt(20);
		}
		
		else{
			colorStringX= "#33FF33";
	        x_fillX1= 90+random1.nextInt(50);
			
		}
		
		//y portion
		
		if(y<=0.00 || y>2.00)
		{
			colorStringY= "#CC0000";
	        y_fillX1= 20;
					
		}
		
		else if(y>0.00 && y<0.15)
		{
			colorStringY= "#FFA500";
	        y_fillX1= 50+random2.nextInt(20);
		}
		
		else{
			colorStringY= "#33FF33";
	        y_fillX1= 100+random2.nextInt(50);
			
		}
		
		//z portion
		
		if(z<=0.00 || z>2.50)
		{
			colorStringZ= "#CC0000";
	        z_fillX1= 20;
					
		}
		
		else
		{
			colorStringZ= "#33FF33";
	        z_fillX1= 50+random3.nextInt(100);
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
	
		        
		
		
			  
		   
		        

	
	}



