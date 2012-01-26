
/**
 * Kontrolliluokka, hallitsee pelin etenemisen ja säännöt.
 */
public class GameOfLife implements Runnable{
	
	private GameOfLifeUI ui;
	private CellGrid grid;
	private boolean simulate;
	
	public static void main(String[] args){
		GameOfLife game = new GameOfLife();

	}
	
	/**
	 * Luo uuden Game of Life -instanssin vakioasetuksilla.
	 */
	public GameOfLife() {
		
		grid = new CellGrid(20);
		simulate = false;
		ui = new GameOfLifeUI(15, this);
	}
	

	/**
	 * Pelaa peliä yhden kierroksen eteenpäin, ja tallentaa muutokset
	 * grid-luokkamuuttujaan.
	 */
	public void nextStep(){
		// Luodaan uusi väliaikainen grid-olio
		CellGrid temp = new CellGrid(grid.getSize());

		for(int j = 0; j < grid.getSize(); j++){ // pystyrivit
			for(int i = 0; i < grid.getSize(); i++){ // vaakarivit
				
				// Lasketaan jokaisen solun elävien naapurien määrä
				int counter = 0;
				for(int m = j-1; m <= j+1; m++){ // Tarkasteltavan alkion ympäristön pystyrivit
					int n = i-1;
					
					while (n <= i+1) { // Tarkasteltavan alkion ympäristön vaakarivit
						// jos elossa, lisätään counteriin yksi
						if (grid.getCellStatus(m,n) == true) counter++; 
						
						n++;
					}
				}
				
				// Edetään pelin sääntöjen mukaisesti
				if (grid.getCellStatus(j, i) == true){
					if (counter <= 1 || counter >= 7)
						temp.setCellStatus(j,i, false);
				}
				else if (counter >= 3 && counter <= 7)
					temp.setCellStatus(j, i, true);
			}
		}
		
		// päivitetään grid-olio
		grid = temp;

	}

	/**
	 * Aloittaa simulaation uudessa Threadissä.
	 */
	public void startSimulation(){
		simulate = true;
		(new Thread(this)).start();
	}
	

	/**
	 * Pysäyttää simulaation
	 */
	public void stopSimulation() {
		simulate = false;
		
	}
	
	/**
	 * Palauttaa tämän pelin CellGrid-olion
	 */
	public CellGrid getGrid() {
		return this.grid;
	}

	/**
	 * Tämä ajetaan, kun uusi Thread on luotu ja halutaan aloittaa simulaatio
	 */
	public void run() {
		while (simulate) {
			try {
				this.nextStep();
				ui.update();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}