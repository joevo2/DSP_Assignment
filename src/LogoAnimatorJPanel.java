import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LogoAnimatorJPanel extends JPanel implements KeyListener{
	//protected ImageIcon images[]; // array of images
	protected ImageIcon images[];
	private int currentImage = 0; // current image index
	private final int ANIMATION_DELAY = 0; // millisecond delay
	private int width = 850; // image width
	private int height = 650; // image height
	private Timer animationTimer; // Timer drives animation 23
	// Initial car position
	private int x = 425;
	private int y = 500;
    private int velX = 0;
    private int velY = 0;
	
	public LogoAnimatorJPanel() {
	 try {
		 images = new ImageIcon[16];
		 // Load all the images
		 for ( int count = 0; count < 16; count++ ) {
			 // Load images according to the image path
			 images[count] = new ImageIcon("img/BLUE-"+(count+1)+".jpg");
		 }
         } catch( Exception e ) {
             e.printStackTrace();
         }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	} // end LogoAnimatorJPanel constructor
	
	 // display current image
	 public void paintComponent( Graphics g ) {
		 super.paintComponent( g ); // call superclass paintComponent
		 
		 // Race track
	     Color c2 = Color.white;
	     g.setColor( c2 );
	     g.fillRect(50, 100, 750, 500); 
	     g.drawRect(50, 100, 750, 500); // outer edge 
	     g.drawRect(150, 200, 550, 300); // inner edge
	     
		 Color c1 = Color.GREEN;
		 g.setColor( c1 );
	     g.fillRect( 150, 200, 550, 300 ); 

	     Color c3 = Color.yellow;
	     g.setColor( c3 );
	     g.drawRect( 100, 150, 650, 400 ); // mid-lane marker
	     Color c4 = Color.white;
	     g.setColor( c4 );
	     g.drawLine( 425, 500, 425, 600 ); // start line

	
		 images[currentImage].paintIcon( this, g, x, y );
		 // set next image to be drawn only if Timer is running
		 //if ( animationTimer.isRunning() )
			 //currentImage = ( currentImage + 1 ) % images.length;
	 } // end method paintComponent
	 
	 // start animation, or restart if window is redisplayed
	 public void startAnimation() {
		 if ( animationTimer == null ) {
			 currentImage = 12; // display first image
			 
			 animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());

			 animationTimer.start(); // start Timer
		 } else { // animationTimer already exists, restart animation
			 if (! animationTimer.isRunning())
				 animationTimer.restart();
		 }
	 }
	 
	 public void stopAnimation() {
		 animationTimer.stop();
	 }
	 
	 public Dimension getMinimumSize() {
		 return getPreferredSize();
	 }
	 
	 public Dimension getPreferredSize() {
		 return new Dimension(width, height);
	 }

    private class TimerHandler implements ActionListener {
		 public void actionPerformed( ActionEvent actionEvent) { 
			 repaint();
		 }
	 }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
//		System.out.println("x: " + x + ", y: " + y + ", currentImage: " + currentImage);

		switch(e.getKeyCode()) {
			// Left key
	    	case 37:
	    		// Change car turning image
	    		if (currentImage > 0) {
                    currentImage -= 1;
                }
	    		else {
                    currentImage=15;
                }
	    		break;
	    	// Up key
	    	case 38:
                x-=5;
	    		break;
	    	// Right key
	    	case 39:
	    		// Change car turning image
	    		if (currentImage != 15) {
                    currentImage += 1;
                }
	    		else {
                    currentImage=0;
                }
                break;
	    	// Down key
	    	case 40:
	    		x+=5;
	    		break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
