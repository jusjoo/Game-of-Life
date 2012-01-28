import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Game of Lifen graafinen k‰yttˆliittym‰
 */
public class GameOfLifeUI implements ActionListener {

	private JFrame frame;
	private JButton clearButton;
	private JButton startButton;
	private JButton stopButton;
	private LifeCanvas canv;
	private GameOfLife game;
	

    /**
     * Luo uuden Game of Life -k‰yttˆliittym‰n.
     * Ottaa parametreina vastaan jokaisen solun pikselikoon 
     * ja m‰‰ritellyn pelialustan (grid)
     * 
     * ae: 	grid != null && 
     * 		cellSize > 0
     */
	public GameOfLifeUI(int cellSize, GameOfLife gameinstance) {
				
		this.game = gameinstance;
		
		frame = new JFrame("Game of Life");
    	Container contentPane = frame.getContentPane();
    	contentPane.setLayout(new FlowLayout());
    	
    	
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Luodaan osat
        canv = new LifeCanvas(cellSize, gameinstance);
        frame.add(canv);
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(this);
        
        // Lis‰t‰‰n osat ikkunaan
        frame.add(clearButton);
        frame.add(startButton);
        frame.add(stopButton);
        
        frame.pack();
        frame.setSize(cellSize*gameinstance.getGrid().getSize() + 15, cellSize*gameinstance.getGrid().getSize() + 75);
        frame.setResizable(false);
        
        
        frame.setVisible(true);
	}


	/**
	 * K‰sittelee k‰yttˆliittym‰tapahtumat, jotka ovat LifeCanvaksen ulkopuolella
	 */
	public void actionPerformed(ActionEvent e) {
		
		// tyhjent‰‰ pelialustan ja lopettaa simuloinnin
		if(e.getSource() == clearButton) {
			game.getGrid().clear();
			game.stopSimulation();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			canv.repaint();
		}
		
		// aloittaa pelin simuloinnin
		if(e.getSource() == startButton) {
			game.startSimulation();
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}
		
		// pys‰ytt‰‰ pelin simuloinnin
		if(e.getSource() == stopButton) {
			game.stopSimulation();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
		}
		
	}


	/**
	 * P‰ivitt‰‰ pelialustan.
	 */
	public void update() {
		canv.repaint();
	}

}

