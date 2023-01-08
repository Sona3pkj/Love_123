import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class Paddel extends Rectangle{
    int id;
    int speed = 10;
    int yVelocity;
    Paddel(int x,int y,int PADDLE_WIDTH,int PADDLE_HEIGHT,int id){
     super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);

    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x,y,width,height);
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            setYDirection(-speed);
            System.out.println(y);
            move();
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            setYDirection(speed);
            System.out.println(y);
            move();
        }
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
        y = y + yVelocity;
    }

}
class JavaPanel extends JPanel implements Runnable{
    static final int PANEL_WIDTH =1000;
    static final int PANEL_HEIGHT = (int)(PANEL_WIDTH*0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH,PANEL_HEIGHT);
    static final int PADEL_HEIGHT = 100;
    static final int PADEL_WIDTH = 25;
    Image image;
    Graphics graphics;
    Random random;
    Paddel paddel1;
    Thread gameThread;
    JavaPanel(){
         newPaddel();
         this.setFocusable(true);
         this.setBackground(Color.black);
        this.addKeyListener(new Al());
         this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newPaddel(){
        paddel1 = new Paddel(0,(PANEL_HEIGHT/2)-(PADEL_HEIGHT/2),PADEL_WIDTH,PADEL_HEIGHT,1);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddel1.draw(g);
    }
    public void move(){

    }
    public void run(){

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                //checkCollision();
                repaint();
                delta--;
    }
}
    }
    class Al extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            paddel1.keyPressed(e);
        }
    }
}
 class JavaFrame extends JFrame{
     JavaPanel panel;
    JavaFrame(){
        panel = new JavaPanel();
        this.add(panel);
        this.setResizable(false);//fixed the size of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//place the screen on middle of the window
    }
}
public class Sona {
    public static void main(String[] args) {
        JavaFrame f = new JavaFrame();//creating instance of JFrame

      /*  JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers

        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        b.setBackground(Color.blue);*/
    }
}
