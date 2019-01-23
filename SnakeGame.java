package snakegame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

class LocXY {

    public int x;
    public int y;

    LocXY(int xx, int yy) {
        x = xx;
        y = yy;
    }
}


public class SnakeGame extends JFrame implements KeyListener {

    private int x = 100;         // Ball's X coordinate
    private int y = 200;               // Ball's Y coordinate
    private final int WIDTH = 50;      // Ball's width
    private final int HEIGHT = 50;     // Ball's height
    private final int TIME_DELAY = 300; // Time delay
    private final int MAXIMUM_Y = 1000; // Min height of ball
    private final int MAXIMUM_X = 1200;
    private javax.swing.Timer timer;               // Timer object
    private boolean goingUp = false;    // Direction indicator
    private boolean goingLeft = false;
    private boolean goingRight = true;
    private boolean goingDown = false;
    private Random generator;
    private Queue<LocXY> snakePositions;
    private Queue<LocXY> bugsLocations;

    public SnakeGame() {
        timer = new javax.swing.Timer(TIME_DELAY, new TimerListener());
        timer.start();
        generator = new Random();
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAXIMUM_X + 2, MAXIMUM_Y + 2);
        setVisible(true);

        bugsLocations = new LinkedList<LocXY>();
        for (int i = 0; i < 4; i++) {
            bugsLocations.add(new LocXY ( generator.nextInt(MAXIMUM_X / WIDTH) * WIDTH,
                generator.nextInt(MAXIMUM_Y / HEIGHT) * HEIGHT));
        }
        
        snakePositions = new LinkedList<LocXY>();
        snakePositions.add(new LocXY(100, 200));
        snakePositions.add(new LocXY(150, 200));
        snakePositions.add(new LocXY(200, 200));

    }

    public void drawSnake(Graphics g)
    {
        Iterator it=snakePositions.iterator();

        g.setColor(Color.red);
        while(it.hasNext())
        {
            LocXY point=(LocXY)it.next();
            g.fillOval(point.x, point.y, WIDTH, HEIGHT);
        }
    }

    public void drawBugs(Graphics g)
    {


          
        Iterator it=bugsLocations.iterator();
    	
    	  int x = (int) (Math.random() );
          int y = (int) (Math.random()
        		  );
        

        g.setColor(Color.blue);
        while(it.hasNext())
        {
        	
        	
            LocXY point=(LocXY)it.next();
            g.fillRect(x, y*2, WIDTH, HEIGHT);
        }
    }
    
    public void paint(Graphics g) {
        // Call the superclass paint method.
        super.paint(g);

        g.setColor(Color.black);
        g.drawRect(2, 2, MAXIMUM_X -2, MAXIMUM_Y - 2);
        
        drawSnake(g);

        drawBugs(g);
    }

    /**
     * Private inner class that handles the Timer object's action events.
     *
     *
     *
     */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Update the ball's Y coordinate.
            if (goingUp) {
                y -= HEIGHT;
            } else if (goingLeft) {
                x -= WIDTH;
            } else if (goingDown) {
                y += HEIGHT;
            } else if (goingRight) {
                x += WIDTH;
            }

            if ((x < 0) || (x >= MAXIMUM_X)
                    || (y < 0) || (y >= MAXIMUM_Y)) {
                System.out.println("Game over.");
                timer.stop();
            }
            
            snakePositions.add(new LocXY(x, y));
            snakePositions.remove();
            // Force a call to the paint method.
            repaint();
        }
    }

    /**
     * Handle the key typed event from the text field.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handle the key-pressed event from the text field.
     */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
        int keyCode = e.getKeyCode();
        // System.out.println(keyCode);
        if (keyCode == KeyEvent.VK_LEFT) // Left arrow key
        {
            goingLeft = true;
            if(!goingRight)
            {
            	goingLeft=true;
            }
            else
            {
            	goingLeft=false;
            	goingRight=true;
            }
            goingUp = goingDown = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) // Left arrow key
        {
            goingRight = true;
            if(!goingLeft)
            {
            	goingRight=true;
            }
            else
            {
            	goingRight=false;
            	goingLeft=true;
            }
            goingUp = goingDown = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) // Left arrow key
        {
            goingDown = true;
            if(!goingUp)
            {
            	goingDown=true;
            }
            else
            {
            	goingDown=false;
            	goingUp=true;
            }
            goingRight =goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_UP) // Left arrow key
        {
            goingUp = true;
            if(!goingUp)
            {
            	goingDown=true;
            }
            else
            {
            	goingLeft=false;
            	goingRight=false;
            }
            goingRight = goingLeft =false;
        }
        repaint();
    }

    /**
     * Handle the key-released event from the text field.
     */
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}