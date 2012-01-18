public class CellGrid{

	private int size;
	private int[][] t = new int[size][size];

	public CellGrid(int size){
		this.size = size;
	}
	
	//Checks the whole grid and saves the future grid status (alive and dead cells)
	//to a temporary table.
	public void checkCells(){

		int[][] tNext = new int[size][size];
		int counter = 0;
		int m = 0;
		int n = 0;

		for(int j = 0; j <= size; j++){ // Koko taulun pystyrivit
			for(int i = 0; i <= size; i++){ // Koko taulun vaakarivit

				for(m = j-1; m <= j+1; m++){ // Tarkasteltavan alkion ympäristön pystyrivit
					n = i-1;
					counter = 0;
					while (n <= i+1){ // Tarkasteltavan alkion ympäristön vaakarivit
						if(t[m][n] == 1) // 1 == alive
							counter++;
						n++;
					}
				}
				if(t[m][n] == 1){
					if (counter <= 1 || counter >= 7)
						tNext[m][n] = 0;
				}
				else if (counter >= 3 && counter <= 7)
					tNext[m][n] = 1;
			}

		}

		t = tNext;

	}

	//Returns cell status.
	//True if it's alive, false if it's dead.
	public boolean getCellStatus(int x, int y){
		
		if(t[x][y] == 1)
			return true;

		return false;
	}

	//Clears the grid (sets every cell status to 0).
	public void clear(){ 

		for(int j = 0; j <= size; j++){
			for(int i = 0; i <= size; i++){
				t[j][i] = 0;
			}
		}

	}

	//Sets alive cell dead and vice versa
	public void toggle(int x, int y){
		
		if(t[x][y] == 1){
			t[x][y] = 0;
		}
		else
			t[x][y] = 1;
	}

	public int getSize(){
		return size;
	}

}