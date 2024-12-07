
import view.GamePanel;

public class GameRunner implements Runnable{

    private Thread gameThread;
    private int FPS = 60;
    private GamePanel gp;

    public GameRunner(GamePanel gp){
        this.gp = gp;
    }

    public Thread getGameThread(){ return this.gameThread; }
    public int getFPS(){ return this.FPS; }
    public GamePanel getGamePanel(){ return this.gp; }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
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
                gp.repaint();
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
        gp.getPlayer().update();
    }
    
}
