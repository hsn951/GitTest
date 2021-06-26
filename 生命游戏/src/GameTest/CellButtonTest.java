package GameTest;

import lifegame.CellButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellButtonTest {
    private static CellButton cellBtn = new CellButton(10,10);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getxInCellPanel() {
        cellBtn.setxInCellPanel(20);
        assertEquals("getxInCellPanel函数出错",20,cellBtn.getxInCellPanel());
    }

    @Test
    public void setxInCellPanel() {
        cellBtn.setxInCellPanel(20);
        System.out.println(cellBtn.getxInCellPanel()+"");
    }

    @Test
    public void getyInCellPanel() {
        cellBtn.setyInCellPanel(20);
        assertEquals("getyInCellPanel函数出错",20,cellBtn.getyInCellPanel());
    }

    @Test
    public void setyInCellPanel() {
        cellBtn.setyInCellPanel(20);
        System.out.println(cellBtn.getyInCellPanel()+"");
    }
}