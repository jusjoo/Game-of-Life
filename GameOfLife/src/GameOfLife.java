
/**
 * Kontrolliluokka, hallitsee pelin etenemisen ja s‰‰nnˆt.
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
	 * Pelaa peli‰ yhden kierroksen eteenp‰in, ja p‰ivitt‰‰ muutokset
	 * grid-luokkamuuttujaan.
	 */
	public void nextStep(){
		// Luodaan uusi v‰liaikainen grid-olio
		CellGrid temp = new CellGrid(grid.getSize());

		for(int j = 0; j < grid.getSize(); j++){ // pystyrivit
			for(int i = 0; i < grid.getSize(); i++){ // vaakarivit
				
				// Lasketaan jokaisen solun el‰vien naapurien m‰‰r‰
				int counter = 0;
				for(int m = j-1; m <= j+1; m++){ // Tarkasteltavan alkion ymp‰ristˆn pystyrivit
					int n = i-1;
					
					while (n <= i+1) { // Tarkasteltavan alkion ymp‰ristˆn vaakarivit
						// jos elossa, eik‰ ole itse tarkasteltava alkio
						if (grid.getCellStatus(m,n) == true && (i != 0 || j != 0)) {
							// jos elossa, lis‰t‰‰n counteriin yksi
							counter++; 
						}
						n++;
					}
				}
				
				// Edet‰‰n pelin s‰‰ntˆjen mukaisesti
				if (counter <= 1) {
					temp.setCellStatus(j, i, false);
				}
				if (counter == 3) {
					temp.setCellStatus(j, i, true);
				}
				if (counter >= 4) {
					temp.setCellStatus(j, i, false);
				}
			}
		}
		
		// p‰ivitet‰‰n grid-olio
		grid = temp;

	}

	/**
	 * Aloittaa simulaation uudessa Threadiss‰.
	 */
	public void startSimulation(){
		simulate = true;
		(new Thread(this)).start();
	}
	

	/**
	 * Pys‰ytt‰‰ simulaation
	 */
	public void stopSimulation() {
		simulate = false;
		
	}
	
	/**
	 * Palauttaa t‰m‰n pelin CellGrid-olion
	 */
	public CellGrid getGrid() {
		return this.grid;
	}

	/**
	 * T‰m‰ ajetaan, kun uusi Thread on luotu ja halutaan aloittaa simulaatio
	 */
	public void run() {
		while (simulate) {
			try {
				this.nextStep();
				ui.update();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}