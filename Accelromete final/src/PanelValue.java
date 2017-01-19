import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class PanelValue extends JPanel implements ActionListener {
	
	 JLabel  gyro_x, gyro_y, gyro_z;
     JTextField  gyro_xvalue, gyro_yvalue, gyro_zvalue;
	 Timer timer;
	 String xString,yString,zString;
	 String[] line;
	 SerialTest serialTest;
	 double x,y,z;
	 XYSeriesCollection datasetCollection;
	 private int n = 1;
	 int counter=1;
	 final XYSeries seriesX = new XYSeries("Gyro_x");
	 final XYSeries seriesY = new XYSeries("Gyro_y");
	 final XYSeries seriesZ = new XYSeries("Gyro_y");
	
	public PanelValue()
	{
		this.setBounds(5, 390, 320, 278);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.setBackground(Color.lightGray);
		this.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		final JTabbedPane jtp = new JTabbedPane();
		jtp.add(String.valueOf(n), createPane());
		this.add(jtp);
	    
	   
	    this.add(new JButton(new AbstractAction("Next") {   //buttonadd
            @Override
            public void actionPerformed(ActionEvent e) {
                jtp.add(String.valueOf(++n), createPane());
                jtp.setSelectedIndex(n - 1);
            }
	    }));
	    
	  
	
	    }
	
	public ChartPanel createPane() {
	      
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(seriesX);
	        dataset.addSeries(seriesY);
	        dataset.addSeries(seriesZ);
	      
	        JFreeChart chart = ChartFactory.createXYLineChart("Gyroscope values vs Time", "Gyroscope values",
	            "Time", dataset, PlotOrientation.VERTICAL, false, false, false);
	        XYPlot plot= chart.getXYPlot();
	        XYLineAndShapeRenderer renderer= new XYLineAndShapeRenderer();
	        renderer.setSeriesPaint(0, Color.RED);
	        renderer.setSeriesPaint(1, Color.GREEN);
	        renderer.setSeriesPaint(2, Color.YELLOW);
	         
	        // sets thickness for series (using strokes)
	        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
	        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
	        renderer.setSeriesStroke(2, new BasicStroke(1.0f));
	        plot.setBackgroundPaint(Color.darkGray);
	        plot.setOutlinePaint(Color.white);
	        plot.setOutlineStroke(new BasicStroke(2.0f));
	        plot.setRangeGridlinesVisible(true);
	        plot.setRangeGridlinePaint(Color.white);
	         
	        plot.setDomainGridlinesVisible(true);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRenderer(renderer);
	        
	        return new ChartPanel(chart) {
	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(300, 200);
	            }
	        };
	    }
	
	public void addChart()
	{
		
	}
	
	public void startTimer()
	{
		timer= new javax.swing.Timer(1000, this);
		timer.start();
	
	}
	public void stopTimer()
	{
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 createPane();
		seriesX.add(counter++,x);
		seriesY.add(counter++,y);
		seriesZ.add(counter++,z);
	
		
	}
	
	public void setGyroX(String gyro_x)
	{
		xString=gyro_x;
	    x= Double.parseDouble(xString);
	}
	
	
	public void setGyroY(String gyro_y)
	{
		yString=gyro_y;
	    y= Double.parseDouble(xString);
	}	
	
	public void setGyroZ(String gyro_z)
	{
		zString=gyro_z;
	    z= Double.parseDouble(zString);
	}
	
	public void setText(String data)
	{
		//System.out.println("v 11111111: "+data);
		line=data.split("g:");
		//System.out.println("v 22222222: "+data);
		
		String gyroLine=line[1];
		
		
		String[] array=gyroLine.split(" ");
		
		
		
		setGyroX(array[1]);
		setGyroY(array[2]);
		setGyroZ(array[3]);
	}
}
	
	     	      
		     
		      
		      
		    
	


