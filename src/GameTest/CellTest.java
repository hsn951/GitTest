package GameTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import lifegame.*;
public class CellTest {

    private static Cell cellObj = new Cell(10,10);
    private static int count = 0;
    @Before
    public void setUp() throws Exception {
        ++count;
    System.out.println("测试结果#"+count);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void evolve() {

        cellObj.setStatus(Cell.LifeStatus.SURVIVAL);
        cellObj.setNextStatus(Cell.LifeStatus.DEATH);
        assertFalse("结果错误",cellObj.evolve());
        cellObj.setStatus(Cell.LifeStatus.DEATH);
        assertTrue("结果错误",cellObj.evolve());

    }

    @Test
    public void getNextStatus() {
        cellObj.setNextStatus(Cell.LifeStatus.SURVIVAL);
        assertEquals("getNextStatus函数出错",Cell.LifeStatus.SURVIVAL,cellObj.getNextStatus());
    }

    @Test
    public void setNextStatus() {
        cellObj.setNextStatus(Cell.LifeStatus.DEATH);
        assertEquals("setNextStatus函数出错",cellObj.getNextStatus(),Cell.LifeStatus.DEATH);
        cellObj.setNextStatus(Cell.LifeStatus.SURVIVAL);
        assertEquals("setNextStatus函数出错",cellObj.getNextStatus(),Cell.LifeStatus.SURVIVAL);
    }

    @Test
    public void setStatus() {
        cellObj.setStatus(Cell.LifeStatus.DEATH);
        assertEquals("setStatus函数出错",Cell.LifeStatus.DEATH,cellObj.getStatus());
        cellObj.setStatus(Cell.LifeStatus.SURVIVAL);
    }

    @Test
    public void getStatus() {
        System.out.println(cellObj.getStatus()+"");
    }

    @Test
    public void getX() {
        cellObj.setX(15);
        System.out.println(cellObj.getX()+"");
    }

    @Test
    public void setX() {
        cellObj.setX(20);
        assertEquals("setX函数错误",20,cellObj.getX());
    }

    @Test
    public void getY() {
        cellObj.setY(15);
        System.out.println(cellObj.getY()+"");
    }

    @Test
    public void setY() {
        cellObj.setY(20);
        assertEquals("setY函数错误",20,cellObj.getY());
    }
}