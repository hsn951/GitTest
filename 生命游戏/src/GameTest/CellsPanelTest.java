package GameTest;

import lifegame.CellsPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellsPanelTest {
    private static CellsPanel cellPnl = new CellsPanel(10,10);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getRows() {
        assertEquals("getRows函数出错",10,cellPnl.getRows());
    }

    @Test
    public void getCols() {
        assertEquals("getCols函数出错",10,cellPnl.getCols());
    }

    @Test
    public void getComponentAtXY() {
assertNull(cellPnl.getComponentAtXY(2,4));
    }
}