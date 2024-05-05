package main;

public class Game implements Runnable{
    private Game_window gameWindow;//object instanciation of
    // Game_window class.
    private GamePanel gamePanel;// object instanciation of GamePanel class.
    private Thread gameThread;
    private final int FPS_SET=120;
    private void startGameloop(){
        gameThread=new Thread(this);
        gameThread.start();

    }
    public Game(){//constructor
        gamePanel =new GamePanel();
        gameWindow =new Game_window(gamePanel);
        gamePanel.requestFocus();
        startGameloop();


    }

    @Override
    public void run() {

        double timePerFrame=1000000000.0/FPS_SET;
        long lastFrame=System.nanoTime();
        long now=System.nanoTime();
        int frames=0;
        long lastCheck=System.currentTimeMillis();

        while (true){
            now=System.nanoTime();
            if(now-lastFrame>=timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
                frames++;
            }

            if(System.currentTimeMillis()-lastCheck>=1000){
                lastCheck=System.currentTimeMillis();
                System.out.println("FPS: "+frames);
                frames=0;
            }

        }

    }
}
