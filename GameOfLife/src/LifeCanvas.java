import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



/**
 * Piirtoalusta, johon piirret‰‰n Game of Lifen solujen tilat.
 */
public class LifeCanvas extends Component implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	
	private int cellSize;
	private int cellsPerRow;
	private GameOfLife game;
	
	/**
	 * Luo uuden LifeCanvaksen
	 * 
	 * @.pre 	cellSize > 0 &&
	 * 			cellsPerRow > 0 &&
	 * 			gameinstance != null
	 */
	public LifeCanvas(int cellSize, GameOfLife gameinstance) {
		this.cellSize = cellSize;
		this.game = gameinstance;
		this.cellsPerRow = game.getGrid().getSize();
		
		// Lis‰t‰‰n kuuntelupalikat, jotta voidaan k‰sitell‰ hiiren painallukset.
		addMouseListener(this);
		addMouseMotionListener(this);
        
    }

	/**
	 * Asettaa pelialustan koon.
	 */
    public Dimension getPreferredSize() {
    	int canvasSize = cellSize * cellsPerRow +1 ;
        return new Dimension(canvasSize, canvasSize);
    }

   /**
    * Piirt‰‰ pelialueen
    */
    public void paint(Graphics g) {
       
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


	/**
	 * Klikkaamalla pelialuetta voidaan muuttaa solun tilaa
	 * 
	 * L‰hett‰‰ grid-oliolle oikeat CellGrid-koordinaatit.
	 */
	public void mouseClicked(MouseEvent e) {
		// Jaetaan hiiren koordinaatit solun koolla, josta saadaan selville solun koordinaatit.
		int gridX = (int)e.getX()/cellSize;
		int gridY = (int)e.getY()/cellSize;
		
		// Muutetaan klikatun solun tilaa.
		game.getGrid().toggle(gridX, gridY);
		repaint();
	}
	
	
	/*
	 * N‰m‰ eiv‰t ole k‰ytˆss‰.
	 */
	public void mouseDragged(MouseEvent arg0) { }
    public void mouseMoved(MouseEvent arg0) { }
	public void mouseEntered(MouseEvent arg0) {	}
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
}

