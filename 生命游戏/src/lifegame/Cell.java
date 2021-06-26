
package lifegame;

/**
 * @数据结构-细胞类。
 * @author hsn and lyh
 * @value x 细胞横坐标
 *
 */
public class Cell {
    //细胞状态
    private LifeStatus status;
    //细胞下个状态
    private LifeStatus nextStatus;
    private int x;
    private int y;

    /**
     * @param x 细胞横坐标
     * @param y 细胞纵坐标
     */
    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @使细胞进化到下一个状态
     * @return 状态是否改变过
     */
    public boolean evolve() {
        if (status == nextStatus) {
            return false;
        }
        status = nextStatus;
        return true;
    }

    /**
     * @获取细胞的下一个状态
     * @return nextStatus 细胞的下个状态
     */
    public LifeStatus getNextStatus() {
        return nextStatus;
    }

    /**
     * @设置细胞的下个状态
     * @param nextStatus 细胞的下个状态
     */
    public void setNextStatus(final LifeStatus nextStatus) {
        this.nextStatus = nextStatus;
    }

    /**
     * @设置细胞当前状态
     * @param status 细胞当前状态
     */
    public void setStatus(final LifeStatus status) {
        this.status = status;
    }

    /**
     * @获取细胞当前状态
     * @return status 当前状态
     */
    public LifeStatus getStatus() {
        return status;
    }

    /**
     * @获取当前细胞的横坐标
     * @return 无
     */
    public int getX() {
        return x;
    }

    /**
     * @设置当前细胞的横坐标
     * @param x 横坐标
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @获取当前细胞的纵坐标
     * @return 无
     */
    public int getY() {
        return y;
    }

    /**
     * @设置当前细胞的纵坐标
     * @param y 纵坐标
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * @细胞状态枚举类型
     * @see java.lang.Enum 枚举类
     */
    public enum LifeStatus {
        //存活
        SURVIVAL,
        //死亡
        DEATH,
    }
}
