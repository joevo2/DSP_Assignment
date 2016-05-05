import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;


public class carGame {
	public static void main( String args[]) throws Exception {
        // Create the JPanel window and the assets inside.
		carAndArena carGame = new carAndArena();
		JFrame window = new JFrame("Super duper car racing game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(carGame);
		window.pack();
		window.setVisible(true);
        carGame.startAnimation();

		// Connection to server with pop up
		carGame.connectToServer();
	}
}
