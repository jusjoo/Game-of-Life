import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;



/**
 * Piirtoalusta, johon piirret‰‰n Game of Lifen solujen tilat.
 */
public class LifeCanvas extends Component implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	
	private int cellSize;
	private int cellsPerRow;
	private Random rnd;
	private CellGrid grid;
	
	/**
	 * Luo uuden LifeCanvaksen
	 * @param cellSize
	 * @param cellsPerRow
	 */
	public LifeCanvas(int cellSize, CellGrid grid) {
		this.cellSize = cellSize;
		this.cellsPerRow = grid.getCellsPerRow();
		this.grid = grid;
		
		rnd = new Random(System.currentTimeMillis());
		
		addMouseListener(this);
		addMouseMotionListener(this);
        
    }

    public Dimension getPreferredSize() {
    	int canvasSize = cellSize * cellsPerRow;
        return new Dimension(canvasSize, canvasSize);
    }

   /*
    * Piirt‰‰ pelialueen
    */
    public void paint(Graphics g) {
    	
    	// TODO: tarvitaanko?
        // Dynamically calculate size information
        // (the canvas may have been resized externally...)
        Dimension size = getSize();
        
        // K‰yd‰‰n l‰pi rivit
        for (int i=0; i < cellsPerRow; i++) {
        	// rivin solut
        	for (int j=0; j < cellsPerRow; j++) {
        		
        		// Tarkistetaan, onko solu elossa
        		if (grid.getCellStatus(i, j)) {
        			// Asetetaan satunnainen v‰ri ja piirret‰‰n solu.
        	        g.setColor(new Color(rnd.nextFloat(), rnd.nextFloat(),rnd.nextFloat()));
        	        g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
        		}
        		
        	}
        }
	       
    }

@Override
public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseClicked(MouseEvent e) {
	grid.toggle(e.getX(), e.getY());
	repaint();
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
		repaint();
	}
}

