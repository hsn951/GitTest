package lifegame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @游戏控制类，实现Runnable类
 * @author hsn and lyh
 */
public class Game implements Runnable {
    /*
    * Cell[][]      细胞地图数组
    * width         地图长
    * height        地图宽
    * */
    private Cell[][] cells;
    private int width;
    private int height;
    private volatile boolean run;   //保证run变量的可见性
    private int interval;           //线程睡眠时间

    private CellChangeListener cellChangeListener;
    private CellListener cellListener;

    /**
     * @Game类初始化函数
     * @param width 地图长度
     * @param height 地图高度
     * @param interval 线程睡眠时间
     */
    public Game(final int width, final int height, final int interval) {
        this.width = width;
        this.height = height;
        this.run = true;
        this.interval = interval;
        cells = new Cell[width][height];
    }

    /**
     * @初始化全部cell为death
     * @return this
     */
    public Game init() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setStatus(Cell.LifeStatus.DEATH);
            }
        }
        return this;
    }

    /**
     * @获取指定位置的细胞对象
     * @param x     横坐标
     * @param y     纵坐标
     * @return 坐标中的cell
     */
    public Cell getCellByXY(final int x, final int y) {
        return cells[x][y];
    }
    /**
     * @改变细胞数组大小
     * @param width,height
     * @return
     */
    public void setCells(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
    }

    /**
     * @获取细胞数组
     * @return[][] cells
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * @随机初始化
     * @param probability 随机种子
     * @return 无
     */
    public Game randomInit(final int probability) {
        Random rand = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j] == null) {
                    cells[i][j] = new Cell(i, j);
                }
                if (probability >= rand.nextInt(100)) {
                    cells[i][j].setStatus(Cell.LifeStatus.SURVIVAL);
                } else {
                    cells[i][j].setStatus(Cell.LifeStatus.DEATH);
                }
            }
        }
        return this;
    }

    /**
     * @设置每隔多少毫秒运行一次
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * @获取指定细胞周围存活邻居数量
     * @param x     横坐标
     * @param y     纵坐标
     * @return 指定细胞周围生存的邻居数量
     */
    public int getNeighbor(final int x, final int y) {
        int sum = 0;
        int row = x - 1;
//        for (int i = x - 1; i <= x + 1; ++i) {
//            for (int j = y - 1; j <= y + 1; ++j) {
//                if (i >= 0 && i < width && j >= 0 && j < height
//                        && !(i == x && j == y)
//                        && cells[i][j].getStatus() == Cell.LifeStatus.SURVIVAL) {
//                    ++sum;
//                }
//            }
//        }
        for(int i = y-1;i<= y +1;i++) {
            if (row >= 0 && row < width && i >= 0 && i < height
                    && !(row == x && i == y)
                    && cells[row][i].getStatus() == Cell.LifeStatus.SURVIVAL) {
                ++sum;
            }
            }

        row = x;
        for(int i = y-1;i<= y +1;i++) {
            if (row >= 0 && row < width && i >= 0 && i < height
                    && !(row == x && i == y)
                    && cells[row][i].getStatus() == Cell.LifeStatus.SURVIVAL) {
                ++sum;
            }
        }
        row = x+1;
            for(int i = y-1;i<= y +1;i++) {
                if (row >= 0 && row < width && i >= 0 && i < height
                        && !(row == x && i == y)
                        && cells[row][i].getStatus() == Cell.LifeStatus.SURVIVAL) {
                    ++sum;
                }
            }
        return sum;
    }

    /**
     * @改变细胞状态监听
     * @param cellChangeListener
     */
    public void setCellChangeListener(final CellChangeListener cellChangeListener) {
        this.cellChangeListener = cellChangeListener;
    }

    /**
     * @细胞状态监听
     * @param cellListener
     */
    public void setCellListener(final CellListener cellListener) {
        this.cellListener = cellListener;
    }

    /**
     * @run方法，多线程刷新界面
     */
    @Override
    public void run() {
        while (true) {
            if (run) {
                List<Cell> changeCells = new ArrayList<Cell>();
                for (int j = 0; j < height; ++j) {
                    for (int i = 0; i < width; ++i) {
                        int num = getNeighbor(i, j);
                        if (num == 2) {
                            cells[i][j].setNextStatus(cells[i][j].getStatus());
                        } else if (num == 3) {
                            cells[i][j].setNextStatus(Cell.LifeStatus.SURVIVAL);
                        } else {
                            cells[i][j].setNextStatus(Cell.LifeStatus.DEATH);
                        }

                    }
                }
                for (int j = 0; j < height; ++j) {
                    for (int i = 0; i < width; ++i) {
                        if (cells[i][j].evolve()) {
                            changeCells.add(cells[i][j]);
                        }
                    }
                }
                if (cellListener != null) {
                    cellListener.cellArray(cells, run);
                }
                if (cellChangeListener != null) {
                    cellChangeListener.cellChange(changeCells, run);
                }
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @单步执行，演化下一代
     */
    public  void update() {
    if (!run) {
        List<Cell> changeCells = new ArrayList<Cell>();
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                int num = getNeighbor(i, j);
                if (num == 2) {
                    cells[i][j].setNextStatus(cells[i][j].getStatus());
                } else if (num == 3) {
                    cells[i][j].setNextStatus(Cell.LifeStatus.SURVIVAL);
                } else {
                    cells[i][j].setNextStatus(Cell.LifeStatus.DEATH);
                }

            }
        }
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                if (cells[i][j].evolve()) {
                    changeCells.add(cells[i][j]);
                }
            }
        }
        if (cellListener != null) {
            cellListener.cellArray(cells, run);
        }
        if (cellChangeListener != null) {
            cellChangeListener.cellChange(changeCells, run);
        }
    }
}

    /**
     * @设置游戏状态
     * @param run
     */
    public void setRun(boolean run) {
        this.run = run;
    }

    /**
     * @获取地图长度
     * @return 无
     */
    public int getWidth() {
        return width;
    }

    /**
     * @获取地图高度
     * @return 无
     */
    public int getHeight() {
        return height;
    }


    /**
     * @author franswoo
     * 监听所有的cells
     */
    @FunctionalInterface
    public interface CellListener {
        void cellArray(Cell[][] cells, boolean run);
    }

    /**
     * @author franswoo
     * 监听改变过的cells
     *
     */
    @FunctionalInterface
    public interface CellChangeListener {
        void cellChange(List<Cell> changeCells, boolean run);
    }

//    public static void main(String[] args) {
//        Game game = new Game(10, 10, 500);
//        game.randomInit(50);
//        game.setCellListener((cells, run) -> {
//            for (int i = 0; i < game.getWidth(); ++i) {
//                for (int j = 0; j < game.getHeight(); ++j) {
//                    System.out.print(cells[i][j].getStatus() == Cell.LifeStatus.SURVIVAL ? "@" : " ");
//                }
//                System.out.println();
//            }
//        });
//        new Thread(game).start();
//    }
}
