
/**
 * Kontrolliluokka, hallitsee pelin etenemisen ja s��nn�t.
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
	 * Pelaa peli� yhden kierroksen eteenp�in, ja tallentaa muutokset
	 * grid-luokkamuuttujaan.
	 */
	public void nextStep(){
		// Luodaan uusi v�liaikainen grid-olio
		CellGrid temp = new CellGrid(grid.getSize());

		for(int j = 0; j < grid.getSize(); j++){ // pystyrivit
			for(int i = 0; i < grid.getSize(); i++){ // vaakarivit
				
				// Lasketaan jokaisen solun el�vien naapurien m��r�
				int counter = 0;
				for(int m = j-1; m <= j+1; m++){ // Tarkasteltavan alkion ymp�rist�n pystyrivit
					int n = i-1;
					
					while (n <= i+1) { // Tarkasteltavan alkion ymp�rist�n vaakarivit
						// jos elossa, lis�t��n counteriin yksi
						if (grid.getCellStatus(m,n) == true) counter++; 
						
						n++;
					}
				}
				
				// Edet��n pelin s��nt�jen mukaisesti
				if (grid.getCellStatus(j, i) == true){
					if (counter <= 1 || counter >= 7)
						temp.setCellStatus(j,i, false);
				}
				else if (counter >= 3 && counter <= 7)
					temp.setCellStatus(j, i, true);
			}
		}
		
		// p�ivitet��n grid-olio
		grid = temp;

	}

	/**
	 * Aloittaa simulaation uudessa Threadiss�.
	 */
	public void startSimulation(){
		simulate = true;
		(new Thread(this)).start();
	}
	

	/**
	 * Pys�ytt�� simulaation
	 */
	public void stopSimulation() {
		simulate = false;
		
	}
	
	/**
	 * Palauttaa t�m�n pelin CellGrid-olion
	 */
	public CellGrid getGrid() {
		return this.grid;
	}

	/**
	 * T�m� ajetaan, kun uusi Thread on luotu ja halutaan aloittaa simulaatio
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