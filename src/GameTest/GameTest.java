package GameTest;

import org.junit.Test;
import lifegame.*;
import static org.junit.Assert.*;

public class GameTest {
private static Game game = new Game(10,10,100);
    @Test
    public void getCellByXY() {
        Cell cell = game.getCellByXY(5,6);
        assertNotNull(cell);
    }

    @Test
    public void setCells() {
        game.setCells(5,5);
    }

    @Test
    public void getNeighbor() {
        game.randomInit(30);
        Cell[][] cells = game.getCells();
        int sum = 0;
        int x = 5,y = 6;
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10
                        && !(i == x && j == y)
                        && cells[i][j].getStatus() == Cell.LifeStatus.SURVIVAL) {
                    ++sum;
                }
            }
        }
        assertEquals("获取邻居数出错", sum, game.getNeighbor(x,y));
    }
}