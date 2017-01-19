/* Accelerometer and Gyroscope GUI for MARS ROVER of AIUB
   developed by Sefat Noor Orni */


import java.awt.EventQueue;
public class Main {
	
	public static void main(String[] args) {
           
		   EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                MainFrame frame= new MainFrame();
                frame.setVisible(true);
	        }
        });
    }

}
