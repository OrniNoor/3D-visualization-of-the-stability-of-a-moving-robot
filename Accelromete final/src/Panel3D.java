import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Random;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import sun.security.krb5.internal.ccache.CCacheInputStream;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class Panel3D extends JPanel implements ActionListener{
	
	Canvas3D canvas3d= new Canvas3D(SimpleUniverse.getPreferredConfiguration());
	TransformGroup rotationTGroup;
	Timer timer;
	String xString,yString,zString,xxString,yyString,zzString;
	String[] line;
	double x,y,z,xx,yy,zz;
	
	
	public Panel3D()
	{
		this.setBounds(330,5,510,500);
		this.setLayout(new BorderLayout());
		add(BorderLayout.CENTER, canvas3d);
		this.setBackground(Color.black);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
		
	    newLocale();
	
		setVisible(true);
		
	}
	
	 public void startTimer()
		{
			timer= new javax.swing.Timer(100, this);
			timer.start();
			
		}
		public void stopTimer()
		{
			timer.stop();
		}
		
		public void newLocale()
		{
			VirtualUniverse virtualUniverse = new VirtualUniverse();
			javax.media.j3d.Locale locale= new javax.media.j3d.Locale(virtualUniverse);
			
			locale.addBranchGraph(addViewBranch(canvas3d));
			locale.addBranchGraph(addContentBranch(buildShape()));
			
		}
	    
	
	protected BranchGroup addViewBranch(Canvas3D c) {  //setting view
	    
		BranchGroup viewBranchGroup = new BranchGroup();
	    Transform3D viewTransform3d = new Transform3D();
	    viewTransform3d.set(new Vector3f(0.0f, 0.0f, 10.0f));
	    TransformGroup viewTransformGroup = new TransformGroup(viewTransform3d);
	    ViewPlatform viewPlatform = new ViewPlatform();
	    
	    PhysicalBody Body = new PhysicalBody();
	    PhysicalEnvironment myEnvironment = new PhysicalEnvironment();
	    viewTransformGroup.addChild(viewPlatform);
	    viewBranchGroup.addChild(viewTransformGroup);
	    
	    View myView = new View();
	    myView.addCanvas3D(c);
	    myView.attachViewPlatform(viewPlatform);
	    myView.setPhysicalBody(Body);
	    myView.setPhysicalEnvironment(myEnvironment);
	    return viewBranchGroup;
	  }
	
	protected void addLights(BranchGroup b) {   //adding lights in branchGroup called from addcontentBranch() 
	    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
	   
	    Color3f ambLightColour = new Color3f(0.5f, 0.5f, 0.5f);
	    AmbientLight ambLight = new AmbientLight(ambLightColour);
	    ambLight.setInfluencingBounds(bounds);
	    Color3f dirLightColour = new Color3f(1.0f, 1.0f, 1.0f);
	    Vector3f dirLightDir = new Vector3f(-1.0f, -1.0f, -1.0f);
	    DirectionalLight dirLight = new DirectionalLight(dirLightColour,
	        dirLightDir);
	    dirLight.setInfluencingBounds(bounds);
	    b.addChild(ambLight);
	    b.addChild(dirLight);
	  }

	protected BranchGroup addContentBranch(Node shape) {  //adding transformation
	    
		BranchGroup contentBranchGroup = new BranchGroup();
	    Transform3D rotateCube = new Transform3D();
	    
	    rotateCube.set(new AxisAngle4d(2.0, 1.0, 1.0,0));
	    rotationTGroup = new TransformGroup(rotateCube);
	  
	    rotationTGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    rotationTGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    contentBranchGroup.addChild(rotationTGroup);
	    rotationTGroup.addChild(shape);
	    addLights(contentBranchGroup);
	    return contentBranchGroup;
	  }
	
	protected Node buildShape() {   //adding shape
	    Appearance app = new Appearance();
	    Color3f ambientColour = new Color3f(0.2f, 0.8f, 0.4f);
	    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.f);
	    Color3f specularColour = new Color3f(0.0f, 0.0f, 0.f);
	    Color3f diffuseColour = new Color3f(0.2f, 0.8f, 0.4f);
	    float shininess = 0.0f;
	    app.setMaterial(new Material(ambientColour, emissiveColour,diffuseColour, specularColour, shininess));
	    return new Box(2.0f, 0.5f, 1.0f, app);
	  }


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Transform3D tempTransform3d = new Transform3D();
	    rotationTGroup.getTransform(tempTransform3d);
	    float alpha=(float) 0.5;
	    double fXg=0.0,fYg=0.0,fZg=0.0;
	    Transform3D tempDelta= new Transform3D();
	   
	    fXg = xx * alpha + (fXg * (1.0 - alpha));
	    fYg = yy * alpha + (fYg * (1.0 - alpha));
	    fZg = zz * alpha + (fZg * (1.0 - alpha));
	 
	    //Roll & Pitch Equations
	   double roll  = (Math.atan2(-fYg, fZg)*180.0)/3.14;
	   double pitch =( Math.atan2(fXg, Math.sqrt(fYg*fYg + fZg*fZg))*180.0)/3.14;
	   
	    tempDelta.rotX((float)pitch*(Math.PI/180.0)); 
	   
		tempTransform3d.rotZ((float)roll*(Math.PI/180.0));
	    tempDelta.mul(tempTransform3d);
	  
       
      
        rotationTGroup.setTransform(tempDelta);
      
		
	
		
	      
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
	
	public void setAccX(String acc_x)
	{
		xxString=acc_x;
	    xx= Double.parseDouble(xxString);
	}
	
	
	public void setAccY(String acc_y)
	{
		yyString=acc_y;
	    yy= Double.parseDouble(yyString);
	}	
	
	public void setAccZ(String acc_z)
	{
		zzString=acc_z;
		zz= Double.parseDouble(zzString);
	}
	
	public void setText(String data)
	{
		line=data.split("g:");
		String gyroLine=line[1];
		String accLine= line[0];
		
		String[] arrayG=gyroLine.split(" ");
		String[] arrayA=accLine.split(" ");
		
	
		
		setAccX(arrayA[1]);
		setAccY(arrayA[2]);
		setAccZ(arrayA[3]);
		
		setGyroX(arrayG[1]);
		setGyroY(arrayG[2]);
		setGyroZ(arrayG[3]);
	}
}	
	
	
	


