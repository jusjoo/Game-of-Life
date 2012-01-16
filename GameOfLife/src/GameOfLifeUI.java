import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Game of Lifen graafinen k�ytt�liittym�
 */
public class GameOfLifeUI implements ActionListener {

	private JFrame frame;
	private JButton clearButton;
	private JButton startButton;
	private JButton stopButton;
	private CellGrid grid;
	private LifeCanvas canv;
	private GameOfLife game;
	
    public static void main(String[] args) {
    	    	
    	GameOfLifeUI ui = new GameOfLifeUI(8, 20, grid);
    }

    /**
     * Luo uuden Game of Life -k�ytt�liittym�n.
     * Ottaa parametreina vastaan jokaisen solun pikselikoon 
     * ja m��ritellyn pelialustan (grid)
     * 
     * ae: 	grid != null && 
     * 		cellSize > 0
     */
	public GameOfLifeUI(int cellSize, CellGrid grid) {
		
		
		frame = new JFrame("Game of Life");
    	Container contentPane = frame.getContentPane();
    	contentPane.setLayout(new FlowLayout());
    	
    	
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Luodaan osat
        canv = new LifeCanvas(cellSize, grid);
        frame.add(canv);
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        
        // Lis�t��n osat ikkunaan
        frame.add(clearButton);
        frame.add(startButton);
        frame.add(stopButton);
        
        frame.pack();
        frame.setVisible(true);
	}


	/*
	 * K�sittelee k�ytt�liittym�tapahtumat, jotka ovat LifeCanvaksen ulkopuolella
	 */
	public void actionPerformed(ActionEvent e) {
		
		// tyhjent�� pelialustan
		if(e.getSource() == clearButton) {
			grid.clear();
			canv.repaint();
		}
		
		// aloittaa pelin simuloinnin
		if(e.getSource() == startButton) {
			game.start();
		}
		
		// pys�ytt�� pelin simuloinnin
		if(e.getSource() == stopButton) {
			game.stop();
		}
	}

}
