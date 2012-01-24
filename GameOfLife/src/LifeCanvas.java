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
	private GameOfLife game;
	
	/**
	 * Luo uuden LifeCanvaksen
	 * @param cellSize
	 * @param cellsPerRow
	 */
	public LifeCanvas(int cellSize, GameOfLife gameinstance) {
		this.cellSize = cellSize;
		this.game = gameinstance;
		this.cellsPerRow = game.getGrid().getSize();
		
		
		rnd = new Random(System.currentTimeMillis());
		
		addMouseListener(this);
		addMouseMotionListener(this);
        
    }

    public Dimension getPreferredSize() {
    	int canvasSize = cellSize * cellsPerRow +1 ;
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
        		
        		// piirret‰‰n tyhj‰n solun reunat
        		g.setColor(Color.gray);
        		for (int x=0; x < cellSize; x++) g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
        		
        		// Tarkistetaan, onko solu elossa
        		if (game.getGrid().getCellStatus(i, j)) {
        			// Asetetaan satunnainen v‰ri ja piirret‰‰n solu.
        	        g.setColor(Color.BLACK);
        	        g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
        		}
        		
        	}
        }
	       
    }

@Override
public void mouseDragged(MouseEvent arg0) {
	
}

@Override
public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

/*
 * Klikkaamalla pelialuetta voidaan muuttaa solun tilaa
 * 
 * L‰hett‰‰ grid-oliolle oikeat CellGrid-koordinaatit.
 */
public void mouseClicked(MouseEvent e) {
	
	// Jaetaan hiiren koordinaatit solun koolla, josta saadaan selville solun koordinaatit.
	game.getGrid().toggle((int)Math.floor(e.getX()/cellSize), (int)Math.floor(e.getY()/cellSize));
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

