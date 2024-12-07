
import javax.swing.JFrame;
import view.GamePanel;

public class Main {

    private static GamePanel gp;

    public static void main(String[] args) {
        gp = new GamePanel();
        setupWindow();
        runGame();
        
    }

    private static void setupWindow(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static void runGame(){
        GameRunner gameRun = new GameRunner(gp);
        gameRun.startGameThread();
    }
}
