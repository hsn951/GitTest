package lifegame;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * @author hsn and lyh
 * @细胞地图面板生成类
 */
public class CellsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int rows;   //细胞地图横向个数
    private int cols;   //细胞地图纵向格数

    /**
     * @获取横向格数
     * @return 无
     */
    public int getRows() {
        return rows;
    }

    /**
     * @获取纵向格数
     * @return 无
     */
    public int getCols() {
        return cols;
    }

    /**
     * @初始化函数
     * @param rows 横向格数
     * @param cols 纵向格数
     */
    public CellsPanel(final int rows, final int cols) {
        setLayout(new GridLayout(rows, cols, 0, 0));  //设置指定行数和指定列数，并且间距为0,0的网格布局
        this.rows = rows;
        this.cols = cols;
    }
    /**
     * @获得该位置的组件
     * @param x 按钮横坐标
     * @param y 按钮纵坐标
     * @return 该位置的按钮对象
     */
    public Component getComponentAtXY(final int x, final int y) {
        return getComponents()[x + y * cols];
    }
}
