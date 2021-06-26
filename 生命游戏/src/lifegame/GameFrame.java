package lifegame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @界面类
 * @author hsn and lyh
 */
public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Game game;
    private CellsPanel leftPanel;
    private JPanel rightPanel;
    private JButton ok;
    private JButton nextgeneration;
    private JButton startBtn;
    private JButton pauseBtn;
    private JButton randomBtn;
    private JButton clearBtn;
    private JComboBox widthList;
    private JComboBox heightList;
    private JLabel jwidth;
    private Thread gameThread;
    private int width = 16;   //地图的横向格数
    private int height = 16;  //地图的纵向格数
    private int randomProbability = 30;
    private boolean run = false;   //游戏状态

    /**
     * @GameFrame类初始化函数
     */
    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        game = new Game(width, height, 100).init();
        game.setCellChangeListener((cells, run) -> {
            cells.forEach((cell) -> {
                updateCellBtnUI(cell);
            });
            leftPanel.updateUI();
        });
        gameThread = new Thread(game);

        initUI();
    }

    /**
     * @根据cell更新cellButton的ui
     * @param cell
     */
    public void updateCellBtnUI(final Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        Component c = leftPanel.getComponentAtXY(x, y);
        if (cell.getStatus() == Cell.LifeStatus.SURVIVAL) {
            c.setBackground(CellButton.SURVIVAL_COLOR);
        } else {
            c.setBackground(CellButton.DEATH_COLOR);
        }
    }
    /**
     * @开始游戏
     */
    public void startGame() {
        run = true;
        if (!gameThread.isAlive()) {
            gameThread.start();
        } else {
            game.setRun(run);
        }
    }
    /**
     * @暂停游戏
     */
    public void pauseGame() {
        run = false;
        game.setRun(run);
    }
    /**
     * @初始化界面
     */
    public void initUI() {
        leftPanel = new CellsPanel(width, height);
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        leftPanel.setPreferredSize(new Dimension(800, 800));
        ok = new JButton("确定");

        rightPanel.add(ok);
        //更改地图布局响应函数
        ok.addActionListener((e) -> {
            leftPanel.setVisible(false);
            rightPanel.setVisible(false);
            width = widthList.getSelectedIndex() + 3;     //因为该控件元素最小下标是三，故+3补上
            height = widthList.getSelectedIndex() + 3;

            game.setCells(width, height);

            initUI();


        });
        nextgeneration = new JButton("下一代");
        rightPanel.add(nextgeneration);
        widthList = new JComboBox();
        heightList = new JComboBox();
        for (int i = 3; i < 100; i++) {
            widthList.addItem(String.valueOf(i));
        }
        jwidth = new JLabel("地图规模" +
                "（长x宽）");
        rightPanel.add(jwidth);
        rightPanel.add(widthList);


        nextgeneration.addActionListener((e) -> {
            game.update();
            run = false;
        });
        startBtn = new JButton("开始");
        startBtn.addActionListener((e) -> {
            startGame();
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            clearBtn.setEnabled(false);
            randomBtn.setEnabled(false);
        });
        rightPanel.add(startBtn);
        pauseBtn = new JButton("停止");
        pauseBtn.addActionListener((e) -> {
            pauseGame();
            pauseBtn.setEnabled(false);
            startBtn.setEnabled(true);
            clearBtn.setEnabled(true);
            randomBtn.setEnabled(true);
        });
        pauseBtn.setEnabled(false);
        rightPanel.add(pauseBtn);
        randomBtn = new JButton("随机");
        randomBtn.addActionListener((e) -> {
            if (!run) {
                game.randomInit(randomProbability);
                for (int i = 0; i < game.getWidth(); ++i) {
                    for (int j = 0; j < game.getHeight(); ++j) {
                        updateCellBtnUI(game.getCellByXY(i, j));
                    }
                }
            }
        });
        rightPanel.add(randomBtn);
        clearBtn = new JButton("清除");
        clearBtn.addActionListener((e) -> {
            if (!run) {
                game.init();
                for (int i = 0; i < game.getWidth(); ++i) {
                    for (int j = 0; j < game.getHeight(); ++j) {
                        updateCellBtnUI(game.getCellByXY(i, j));
                    }
                }
            }
        });
        rightPanel.add(clearBtn);

        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                CellButton btn = new CellButton(i, j);
                //为每个细胞按钮添加事件响应，当有事件响应时，若细胞状态为存活，则设为死亡，反之
                btn.addActionListener((e) -> {
                    if (!run) {
                        if (btn.getBackground() == CellButton.DEATH_COLOR) {
                            btn.setBackground(CellButton.SURVIVAL_COLOR);
                            game.getCellByXY(btn.getxInCellPanel(), btn.getyInCellPanel()).setStatus(Cell.LifeStatus.SURVIVAL);
                        } else {
                            btn.setBackground(CellButton.DEATH_COLOR);
                            game.getCellByXY(btn.getxInCellPanel(), btn.getyInCellPanel()).setStatus(Cell.LifeStatus.DEATH);
                        }
                    }
                });
                leftPanel.add(btn);
            }
        }
        pack();
    }
}
