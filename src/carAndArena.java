import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class carAndArena extends JPanel implements KeyListener {
    //protected ImageIcon car1[]; // array of car1
    protected ImageIcon car1[];
    protected ImageIcon car2[];
    private int car1Image = 0; // current image index
    private int car2Image = 0; // current image index
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

    // Choose server
    public JFrame frame = new JFrame("Car Game Client");

    public carAndArena() {
        try {
            car1 = new ImageIcon[16];
            car2 = new ImageIcon[16];
            // Load all the car1
            for (int count = 0; count < 16; count++) {
                // Load car1 according to the image path
                car1[count] = new ImageIcon("img/Red/red-" + (count + 1) + ".png");
                car2[count] = new ImageIcon("img/Green/green-" + (count + 1) + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    } // end carAndArena constructor

    // display current image
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call superclass paintComponent

        // Race track
        Color c2 = Color.white;
        g.setColor(c2);
        g.fillRect(50, 100, 750, 500);
        g.drawRect(50, 100, 750, 500); // outer edge
        g.drawRect(150, 200, 550, 300); // inner edge

        Color c1 = Color.GREEN;
        g.setColor(c1);
        g.fillRect(150, 200, 550, 300);

        Color c3 = Color.yellow;
        g.setColor(c3);
        g.drawRect(100, 150, 650, 400); // mid-lane marker
        Color c4 = Color.white;
        g.setColor(c4);
        g.drawLine(425, 500, 425, 600); // start line


        car1[car1Image].paintIcon(this, g, x, y);
        car2[car2Image].paintIcon(this, g, x1, y1);
    } // end method paintComponent

    // start animation, or restart if window is redisplayed
    public void startAnimation() {
        if (animationTimer == null) {
            car1Image = 12; // display first image
            car2Image = 12;

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
        public void actionPerformed(ActionEvent actionEvent) {
            // collison for car1
            // outer boundary

            //car 1
            // left outer border
            if (x < 50) {
                velX = 0;
                x = 50;
            }

            // right outer border
            if (x > 750) {
                velX = 0;
                x = 750;
            }

            // top outer border
            if (y < 100) {
                velY = 0;
                y = 100;
            }

            // bottom outer border
            if (y > 550) {
                velY = 0;
                y = 550;
            }

            // car 2
            // left outer border
            if (x1 < 50) {
                velX1 = 0;
                x1 = 50;
            }

            // right outer border
            if (x1 > 750) {
                velX1 = 0;
                x1 = 750;
            }

            // top outer border
            if (y1 < 100) {
                velY1 = 0;
                y1 = 100;
            }

            // bottom outer border
            if (y1 > 550) {
                velY1 = 0;
                y1 = 550;
            }

            // Inner boundary

            // car 1
            // inner grass left boundary
            if (y > 150 && y < 500) {
                if (x > 50 && x < 110) {
                    if (x >= 100) {  // if x become 100 then it will go to y=500
                        x = 99;
                        velX = 0;
                    }
                }
            }

            // inner grass right boundary
            if (y > 150 && y < 500) {
                if (x < 750 && x > 650) {
                    if (x < 705) {
                        x = 705;
                        velX = 0;
                    }
                }
            }

            // inner grass bottom boundary
            if (x < 700 && x > 100) {
                if (y > 400 && y < 700) {
                    if (y < 500) {
                        velY = 0;
                        y = 500;
                    }
                }
            }

            // inner grass top boundary
            if (x < 700 && x > 100) {
                if (y > 100 && y < 200) {
                    if (y > 145) {
                        velY = 0;
                        y = 145;
                    }
                }
            }

            // car 2
            // inner grass left boundary
            if (y1 > 150 && y1 < 500) {
                if (x1 > 50 && x1 < 110) {
                    if (x1 >= 100) {  // if x become 100 then it will go to y=500
                        x1 = 99;
                        velX1 = 0;
                    }
                }
            }

            // inner grass right boundary
            if (y1 > 150 && y1 < 500) {
                if (x1 < 750 && x1 > 650) {
                    if (x1 < 705) {
                        x1 = 705;
                        velX1 = 0;
                    }
                }
            }

            // inner grass bottom boundary
            if (x1 < 700 && x1 > 100) {
                if (y1 > 400 && y1 < 700) {
                    if (y1 < 500) {
                        velY1 = 0;
                        y1 = 500;
                    }
                }
            }

            // inner grass top boundary
            if (x1 < 700 && x1 > 100) {
                if (y1 > 100 && y1 < 200) {
                    if (y1 > 145) {
                        velY1 = 0;
                        y1 = 145;
                    }
                }
            }

            // car collison
//            if (x == x1 && y == y1) {
//                x = x - 20;
//                y = y - 20;
//            }

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
            carDirection(car1Image, "up", 1);
        }
        if (c == KeyEvent.VK_DOWN) {
            carDirection(car1Image, "down", 1);
        }
        if (c == KeyEvent.VK_LEFT) {
            if (car1Image > 0)
                car1Image -= 1;
            else
                car1Image = 15;
            carDirection(car1Image, "", 1);
        }
        if (c == KeyEvent.VK_RIGHT) {
            if (car1Image != 15) {
                car1Image += 1;
            } else {
                car1Image = 0;
            }
            carDirection(car1Image, "", 1);
        }

        // Car 2
        if (c == KeyEvent.VK_W) {
            carDirection(car2Image, "up", 2);
        }
        if (c == KeyEvent.VK_S) {
            carDirection(car2Image, "down", 2);
        }
        if (c == KeyEvent.VK_A) {
            if (car2Image > 0)
                car2Image -= 1;
            else
                car2Image = 15;
            carDirection(car2Image, "", 2);
        }
        if (c == KeyEvent.VK_D) {
            if (car2Image != 15) {
                car2Image += 1;
            } else {
                car2Image = 0;
            }
            carDirection(car2Image, "", 2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
//        velX = 0;
//        velY = 0;

//        velX1 = 0;
//        velY1 = 0;
    }

    public void carDirection(int carImage, String type, int car) {
        int x = 0;
        int y = 0;
        switch (carImage) {
            case 0:
                x = 0;
                y = -10;
                break;
            case 1:
                x = 3;
                y = -7;
                break;
            case 2:
                x = 5;
                y = -5;
                break;
            case 3:
                x = 7;
                y = -3;
                break;
            case 4:
                x = 10;
                y = 0;
                break;
            case 5:
                x = 7;
                y = 3;
                break;
            case 6:
                x = 5;
                y = 5;
                break;
            case 7:
                x = 3;
                y = 7;
                break;
            case 8:
                x = 0;
                y = 10;
                break;
            case 9:
                x = -3;
                y = 7;
                break;
            case 10:
                x = -5;
                y = 5;
                break;
            case 11:
                x = -7;
                y = 3;
                break;
            case 12:
                x = -10;
                y = 0;
                break;
            case 13:
                x = -7;
                y = -3;
                break;
            case 14:
                x = -5;
                y = -5;
                break;
            case 15:
                x = -3;
                y = -7;
                break;
        }

        if (type == "up") {
            x += x;
            y += y;
        }
        if (type == "down") {
            x -= x;
            y -= y;
        }

        if (car == 1) {
            velX = x;
            velY = y;
        }
        if (car == 2) {
            velX1 = x;
            velY1 = y;
        }
    }

    public void connectToServer() throws IOException {
        BufferedReader in;
        PrintWriter out;

        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Capitalization Program",
                JOptionPane.QUESTION_MESSAGE);

        // Make connection and initialize streams
        try {
            Socket socket = new Socket(serverAddress, 9898);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Consume the initial welcoming messages from the server
            for (int i = 0; i < 3; i++) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to game server: " + e);
            System.exit(0);
        }
    }
}
