package lifegame;
import java.awt.Color;

import javax.swing.JButton;

/**
 * @界面按钮类
 * @author hsn and lyh
 *
 */
public class CellButton extends JButton {

    private static final long serialVersionUID = 1L;
    public static final Color SURVIVAL_COLOR = Color.GREEN;
    public static final Color DEATH_COLOR = Color.BLACK;

    private int xInCellPanel;    //细胞在面板中的x坐标
    private int yInCellPanel;    //细胞在面板中的y坐标

    public CellButton(final int xInCellPanel, final int yInCellPanel) {
        this.xInCellPanel = xInCellPanel;
        this.yInCellPanel = yInCellPanel;
        setBackground(DEATH_COLOR);
    }

    /**
     * @获取细胞按钮在面板中的横坐标
     * @return xInCellPanel 横坐标
     */
    public int getxInCellPanel() {
        return xInCellPanel;
    }

    /**
     * @设置细胞按钮在面板中的x坐标
     * @param xInCellPanel x坐标
     */
    public void setxInCellPanel(final int xInCellPanel) {
        this.xInCellPanel = xInCellPanel;
    }

    /**
     * @获取细胞按钮在面板中的y坐标
     * @return yInCellPanel y坐标
     */
    public int getyInCellPanel() {
        return yInCellPanel;
    }

    /**
     * @设置细胞按钮y坐标
     * @param yInCellPanel y坐标
     */
    public void setyInCellPanel(final int yInCellPanel) {
        this.yInCellPanel = yInCellPanel;
    }
}
