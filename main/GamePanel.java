import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    // Screen Settings
    final int origionalTileSize = 16; // 16X16 tile is default size.
    final int scale = 3;
    final int tileSize = origionalTileSize * scale; // 48X48 Actual tile size.

    final int mapScreenCol = 16; // map size is 16 columns.
    final int mapScreenRow = 12; // map size is 12 rows.
    final int screenWidth = tileSize * mapScreenCol; // 768 pixels across
    final int screenHeight = tileSize * mapScreenRow; // 576 pixels up and down.

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
