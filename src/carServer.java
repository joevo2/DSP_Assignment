import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Joel on 05/05/2016.
 */
public class carServer {
    public static void main(String[] args) throws Exception{
        System.out.println("Car game server is running.");
        int clientNumber = 1;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new carComm(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class carComm extends Thread {
        private Socket socket;
        private int clientNumber;

        public carComm(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with players# " + clientNumber + " at " + socket);
        }

        /**
         * Services this thread's client by first sending the
         * client a welcome message then repeatedly reading strings
         * and sending back the capitalized version of the string.
         */
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Hello, you are client #" + clientNumber + ".");

            } catch (Exception e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }
        private void log(String message) {
            System.out.println(message);
        }
    }
}
