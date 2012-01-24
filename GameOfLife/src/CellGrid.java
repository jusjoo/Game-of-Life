
/**
 * Tietomalliluokka, tallentaa pelin tilanteen.
 */
public class CellGrid{

	private int size;
	private boolean[][] t;

	public CellGrid(int size){
		
		t = new boolean[size][size];
		this.size = size;
	}
	


	/**
	 * Palauttaa solun tilan pisteessä (x,y)
	 * 
	 * @.post: 	jos (x,y) taulukon ulkopuolella, return false.
	 */
	public boolean getCellStatus(int x, int y){
		
		// Tarkistetaan, että ollaan taulukon sisällä.
		if ( (x >= 0 && x < size) && (y >= 0 && y < size) ){
			return t[x][y];
		}
		
		// Jos taulukon ulkopuolella, palautetaan aina false
		return false;
	}

	//Clears the grid (sets every cell status to 0).
	public void clear(){ 

		for(int j = 0; j < size; j++){
			for(int i = 0; i < size; i++){
				t[j][i] = false;
			}
		}

	}

	/**
	 * Muuttaa solun (x,y) tilan päinvastaiseksi.
	 * 
	 * @.pre	x >= 0 &&
	 * 			x < size &&
	 * 			y >= 0 &&
	 * 			y < size
	 */
	public void toggle(int x, int y){
		
		if(t[x][y] == true){
			t[x][y] = false;
		}
		else
			t[x][y] = true;
	}

	/*
	 * Palauttaa taulukon koon.
	 */
	public int getSize(){
		return size;
	}


	/*
	 * Asettaa (x,y) -pisteessä sijaitsevan solun eläväksi
	 * tai kuolleeksi.
	 */
	public void setCellStatus(int x, int y, boolean alive) {
		t[x][y] = alive;
		
	}

}