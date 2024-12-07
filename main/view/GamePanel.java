package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import handlers.*;
import tile.TileManager;
import entity.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    // Screen Settings
    private final int origionalTileSize = 16; // 16X16 tile is default size.
    private final int scale = 3;
    public final int tileSize = origionalTileSize * scale; // 48X48 Actual tile size.

    public final int mapScreenCol = 16; // map size is 16 columns.
    public final int mapScreenRow = 12; // map size is 12 rows.
    public final int screenWidth = tileSize * mapScreenCol; // 768 pixels across
    public final int screenHeight = tileSize * mapScreenRow; // 576 pixels up and down.

    // worldmap parameters
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    private TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler();
    private Player player = new Player(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

    public Player getPlayer(){ return this.player; }
    public TileManager getTileManager(){ return this.tileM; }
    public KeyHandler getKeyHandler(){ return this.keyH; }
}
