import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LogoAnimatorJPanel extends JPanel implements ActionListener, KeyListener{
	//protected ImageIcon car1[]; // array of car1
	protected ImageIcon car1[];
    protected ImageIcon car2[];
	private int currentImage = 0; // current image index
	private final int ANIMATION_DELAY = 0; // millisecond delay
	private int width = 850; // image width
	private int height = 650; // image height
	private Timer animationTimer; // Timer drives animation 23
	// Initial car position
	private int x = 425;
	private int y = 500;
    private int x1 = 420;
    private int y1 = 550;
    private int velX = 0;
    private int velY = 0;
    private int velX1 = 0;
    private int velY1 = 0;
	
	public LogoAnimatorJPanel() {
	 try {
		 car1 = new ImageIcon[16];
         car2 = new ImageIcon[16];
		 // Load all the car1
		 for ( int count = 0; count < 16; count++ ) {
			 // Load car1 according to the image path
			 car1[count] = new ImageIcon("img/BLUE-"+(count+1)+".jpg");
             car2[count] = new ImageIcon("img/red-"+(count+1)+".jpg");
         }
         } catch( Exception e ) {
             e.printStackTrace();
         }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	} // end LogoAnimatorJPanel constructor
	
	 // display current image
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g); // call superclass paintComponent
		 
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

	
		 car1[currentImage].paintIcon( this, g, x, y );
         car2[currentImage].paintIcon( this, g, x1, y1 );
	 } // end method paintComponent
	 
	 // start animation, or restart if window is redisplayed
	 public void startAnimation() {
		 if ( animationTimer == null ) {
			 currentImage = 12; // display first image
			 
			 animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());

			 animationTimer.start(); // start Timer
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private class TimerHandler implements ActionListener {
		 public void actionPerformed( ActionEvent actionEvent) {
             // collison for car1

             // outer boundary
             if (x < 50) {
                 velX = 0;
                 x = 50;
             }

             if (x > 750) {
                 velX = 0;
                 x = 750;
             }

             if (y < 100) {
                 velY = 0;
                 y = 100;
             }

             if (y > 550) {
                 velY = 0;
                 y = 550;
             }

             // inner grass left boundary
             if (y > 150 && y < 500) {
                 if (x > 50 && x < 100) {
                     if (x > 95) {  // if x become 100 then it will go to y=500
                         x = 95;
                         velX = 0;
                     }
                 }
             }

//             if (x < 700) {
//                 x = 700;
//                 velX = 0;
//             }

             // inner grass bottom boundary
             if (x < 700 && x > 100) {
                 if (y < 500) {
                     velY = 0;
                     y = 500;
                 }
             }

             x = x + velX;
             y = y + velY;

             x1 = x1 + velX1;
             y1 = y1 + velY1;
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
        System.out.println("x: " + x + ", y: " + y);
		int c = e.getKeyCode();
//		if (c == KeyEvent.VK_UP) {
//            velX = -1;
//            velY = 0;
//		}
//        if (c == KeyEvent.VK_DOWN) {
//            velX = 1;
//            velY = 0;
//        }
//        if (c == KeyEvent.VK_LEFT) {
//            if (currentImage > 0)
//                currentImage-=1;
//            else
//                currentImage=15;
//            velX = 0;
//            velY = 1;
//        }
//        if (c == KeyEvent.VK_RIGHT) {
//            if (currentImage != 15) {
//                currentImage += 1;
//            }
//            else {
//                currentImage=0;
//            }
//            velX = 0;
//            velY = -1;
//        }

        if (c == KeyEvent.VK_LEFT) {
            velX = -1;
            velY = 0;
        }
        if (c == KeyEvent.VK_UP) {
            velX = 0;
            velY = -1;
        }
        if (c == KeyEvent.VK_RIGHT) {
            velX = 1;
            velY = 0;
        }
        if (c == KeyEvent.VK_DOWN) {
            velX = 0;
            velY = 1;
        }

        // Car 2
        if (c == KeyEvent.VK_W) {
            velX1 = -1;
            velY1 = 0;
        }
        if (c == KeyEvent.VK_S) {
            velX1 = 1;
            velY1 = 0;
        }
        if (c == KeyEvent.VK_A) {
            if (currentImage > 0)
                currentImage-=1;
            else
                currentImage=15;
        }
        if (c == KeyEvent.VK_D) {
            if (currentImage != 15) {
                currentImage += 1;
            }
            else {
                currentImage=0;
            }
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
        velX = 0;
        velY = 0;

        velX1 = 0;
        velY1 = 0;
    }
}
