public class GameOfLife{
	
	public static void runko(int tableSize){
		int[][] t = new int[tableSize][tableSize];
		int[][] tNext = new int[tableSize][tableSize];

		for(int j = 0; j <= tableSize; j++){ // Koko taulun pystyrivit
			for(int i = 0; i <= tableSize; i++){ // Koko taulun vaakarivit
				int counter = 0;
				
				for(int m = j-1; m <= j+1; m++){ // Tarkasteltavan alkion ympäristön pystyrivit
					int n = i-1;
					
					while (n <= i+1) { // Tarkasteltavan alkion ympäristön vaakarivit
						if (t[m][n] == 1); // 1 == true
							counter++;
						n++;
					}
				}
				if (t[m][n] == 1){
					if (counter <= 1 || counter >= 7)
						tNext[m][n] = 0;
				}
				else if (counter >= 3 && counter <= 7)
					tNext[m][n] = 1;
			}

		}


	}


}