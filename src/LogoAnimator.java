import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;


public class LogoAnimator {
	public static void main( String args[]) throws Exception {

        // Server and client,
        // if client cannot connect then it will become a server
        int port = 9090;
        try {
            Socket s = new Socket("localhost", port);
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String answer = input.readLine();
            System.out.println(answer);
        } catch (Exception e) {
            ServerSocket listener = new ServerSocket(port);
            try {
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Hello from the server");
                    } finally {
                        socket.close();
                    }
                }
            }
            finally {
                listener.close();
            }
        }

		LogoAnimatorJPanel animation = new LogoAnimatorJPanel();
		
		JFrame window = new JFrame("Super duper car racing game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(animation);
		window.pack();
		window.setVisible(true);
		animation.startAnimation();
	}
}
