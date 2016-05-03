import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LogoAnimatorJPanel extends JPanel implements KeyListener{
	//protected ImageIcon car1[]; // array of car1
	protected ImageIcon car1[];
    protected ImageIcon car2[];
	private int car1Image = 0; // current image index
	private final int ANIMATION_DELAY = 20; // millisecond delay
	private int width = 850; // image width
	private int height = 650; // image height
	private Timer animationTimer; // Timer drives animation 23
	// Initial car position
	private int x = 425;
	private int y = 505;
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

	
		 car1[car1Image].paintIcon( this, g, x, y );
         car2[car1Image].paintIcon( this, g, x1, y1 );
	 } // end method paintComponent
	 
	 // start animation, or restart if window is redisplayed
	 public void startAnimation() {
		 if ( animationTimer == null ) {
			 car1Image = 12; // display first image
			 
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
             if (y > 200 && y < 450) {
                 if (x > 50 && x < 110) {
                     if (x >= 100) {  // if x become 100 then it will go to y=500
                         x = 99;
                         velX = 0;
                     }
                 }
             }

             // inner grass right boundary
             if (y > 150 && y < 500) {
                 if (x < 750 && x > 700) {
                     if (x < 705) {
                         x = 705;
                         velX = 0;
                     }
                 }
             }

             // inner grass bottom boundary
             if (x < 700 && x > 100) {
                 if (y > 500 && y <700) {
                     if (y < 505) {
                         velY = 0;
                         y = 505;
                     }
                 }
             }

             // inner grass top boundary
             if (x < 700 && x > 100) {
                 if (y > 100 && y < 150) {
                     if (y > 145) {
                         velY = 0;
                         y = 145;
                     }
                 }
             }

             // car collison, not working
             if (x == x1 && y == y1) {
                 x = x-20;
                 y = y-20;
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
		System.out.println("x: " + x + ", y: " + y + ", car1Image: " + car1Image);
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_UP) {
            velX = -10;
            velY = 0;
		}
        if (c == KeyEvent.VK_DOWN) {
            velX = 10;
            velY = 0;
        }
        if (c == KeyEvent.VK_LEFT) {
            if (car1Image > 0)
                car1Image -=1;
            else
                car1Image =15;
            carDirection(car1Image);
        }
        if (c == KeyEvent.VK_RIGHT) {
            if (car1Image != 15) {
                car1Image += 1;
            }
            else {
                car1Image =0;
            }
            carDirection(car1Image);
        }

//        if (c == KeyEvent.VK_LEFT) {
//            velX = -1;
//            velY = 0;
//        }
//        if (c == KeyEvent.VK_UP) {
//            velX = 0;
//            velY = -1;
//        }
//        if (c == KeyEvent.VK_RIGHT) {
//            velX = 1;
//            velY = 0;
//        }
//        if (c == KeyEvent.VK_DOWN) {
//            velX = 0;
//            velY = 1;
//        }

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
            if (car1Image > 0)
                car1Image -=1;
            else
                car1Image =15;
        }
        if (c == KeyEvent.VK_D) {
            if (car1Image != 15) {
                car1Image += 1;
            }
            else {
                car1Image =0;
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

    public void carDirection(int carD) {
        switch (carD) {
            case 0:
                velX = 0;
                velY = -10;
                break;
            case 1:
                velX = 3;
                velY = -7;
                break;
            case 2:
                velX = 5;
                velY = -5;
                break;
            case 3:
                velX = 7;
                velY = -3;
                break;
            case 4:
                velX = 10;
                velY = 0;
                break;
            case 5:
                velX = 7;
                velY = 3;
                break;
            case 6:
                velX = 5;
                velY = 5;
                break;
            case 7:
                velX = 3;
                velY = 7;
                break;
            case 8:
                velX = 0;
                velY = 10;
                break;
            case 9:
                velX = -3;
                velY = 7;
                break;
            case 10:
                velX = -5;
                velY = 5;
                break;
            case 11:
                velX = -7;
                velY = 3;
                break;
            case 12:
                velX = -10;
                velY = 0;
                break;
            case 13:
                velX = -7;
                velY = -3;
                break;
            case 14:
                velX = -5;
                velY = -5;
                break;
            case 15:
                velX = -3;
                velY = -7;
                break;
        }
    }
}
