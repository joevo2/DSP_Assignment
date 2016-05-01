import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class LogoAnimator {
	public static void main( String args[]) {
		LogoAnimatorJPanel animation = new LogoAnimatorJPanel();
		
		JFrame window = new JFrame("Super duper car racing game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(animation);
		
		window.pack();
		window.setVisible(true);
		
		// Add key listener
		//window.addKeyListener(animation);

		animation.startAnimation();
	}
}
