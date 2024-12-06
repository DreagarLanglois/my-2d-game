package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import handlers.*;
import tile.TileManager;
import entity.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int origionalTileSize = 16; // 16X16 tile is default size.
    final int scale = 3;
    public final int tileSize = origionalTileSize * scale; // 48X48 Actual tile size.

    public final int mapScreenCol = 16; // map size is 16 columns.
    public final int mapScreenRow = 12; // map size is 12 rows.
    public final int screenWidth = tileSize * mapScreenCol; // 768 pixels across
    public final int screenHeight = tileSize * mapScreenRow; // 576 pixels up and down.

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    // sleep method
    // @Override
    // public void run() {
    //     double drawInterval = 1000000000/FPS; // 0.01666 seconds.
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    //     while(gameThread != null){// This is the game loop that runs while the game thread exists.
            
            
    //         // get current time
    //         long currentTime = System.nanoTime(); // most precise time 
    //         // Update information such as character position.
    //         update();
    //         // draw the screen.
    //         repaint();

    //         // see how much time has passed
    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;
    //             if(remainingTime < 0){
    //                 remainingTime = 0;
    //             }
    //             Thread.sleep((long)remainingTime);
    //             nextDrawTime += drawInterval;
    //         } catch (Exception e) {
    //             System.out.println(e.getMessage());
    //         }
            
    //     }
    // }


    // delta method
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
