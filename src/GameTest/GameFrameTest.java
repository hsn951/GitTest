package GameTest;

import org.junit.Test;

import static org.junit.Assert.*;
import lifegame.*;
public class GameFrameTest {
GameFrame gameFrame = new GameFrame();
    @Test
    public void updateCellBtnUI() {
        gameFrame.updateCellBtnUI(new Cell(4,6));
    }

    @Test
    public void startGame() {
        gameFrame.startGame();
    }

    @Test
    public void pauseGame() {
        gameFrame.pauseGame();
    }

    @Test
    public void initUI() {
        gameFrame.initUI();
    }
}