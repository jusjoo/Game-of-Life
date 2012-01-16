/*
 * Mallintaa yht‰ Game of Lifen el‰v‰‰ tai kuollutta solua
 */
public class CellGrid {
	private boolean alive;
	
	
	/**
	 * Luo uuden kuolleen solun
	 * 
	 * ae: true
	 */
	public CellGrid() {
		alive = false;
	}
	
// havainnoijat
	
	/*
	 * Palauttaa solun tilan.
	 * 
	 * ae: true
	 */
	public boolean getStatus() {
		return alive;
	}
	
// muuttajat
	
	/*
	 * Asettaa solun el‰v‰ksi.
	 * 
	 * ae: true
	 */
	public void setAlive() {
		alive = true;
	}
	
	/*
	 * Asettaa solun kuolleeksi.
	 * 
	 * ae: true
	 */
	public void setDead() {
		alive = false;
	}
}
