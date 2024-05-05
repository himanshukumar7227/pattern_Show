package main;
import inputs.MouseInputs;
import inputs.keyboardInputs;
import javax.swing.JPanel;//Jpannel class importing important for drawing stufs
//into the window.
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private int frames=0;
    private long lastCheck=0;
    private float xDelta=100,yDelta=100;
    private float xDir=0.9f,yDir=0.8f;
    private Color color=new Color(24, 150, 20);
    private Random random;
    private ArrayList<MyRect> rects=new ArrayList<>();
    public GamePanel(){//Gamepanel constructor
        random= new Random();
        mouseInputs= new MouseInputs(this);
        addKeyListener(new keyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    public void changexDelta(int value){
        this.xDelta+=value;
    }
    public void changeyDelta(int value){
        this.yDelta+=value;
    }
    public void setRectPos(int x,int y){
        this.xDelta=x;
        this.yDelta=y;
    }

    public void spawnRect(int x,int y){
        rects.add(new MyRect(x,y));
    }
    // paintComponent is method defined in Jpannel class which is
    // overwrited here again.
    //it is a game loop.
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Temp Rects
        for (MyRect rect : rects){
            rect.updateRect();
            rect.draw(g);
        }

        updateRectangle();

        g.setColor(color);
        g.fillRect((int)xDelta,(int)yDelta,200,30);//this will draw a rectangele.

    }

    private void updateRectangle() {
        xDelta+=xDir;
        if(xDelta>400||xDelta<0){
            xDir*=-1;
            color = getRndColor();
        }
        yDelta+=yDir;
        if(yDelta>400||yDelta<0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r=random.nextInt(255);
        int b=random.nextInt(255);
        int g=random.nextInt(255);
        return new Color(r,b,g);
    }
    public class MyRect {
        int x,y,w,h;
        int xDir=1,yDir=1;
        Color color;
        public MyRect(int x,int y){
            this.x=x;
            this.y=y;
            w=random.nextInt(50);
            h=w;
            color=newColor();
        }
        public void updateRect(){
            this.x+=xDir;
            this.y+=yDir;
            if((x+w)>400||x<0){
                xDir*=-1;
                color=newColor();
            }
            if((y+h)>400||x<0){
                yDir*=-1;
                color=newColor();
            }
        }
        private Color newColor(){
            return new Color(random.nextInt(255),
                    random.nextInt(255),random.nextInt(255));
        }
        public void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x,y,w,h);
        }
    }
}