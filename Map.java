import java.io.File;
import java.util.*;
import javafx.scene.layout.Pane;

public class Map extends Pane{

	private int unit = 50;
	private int size = 1;
	private int[][] map;
	private Position start;

	public Map(String name){

		File file = new File(name);
		try(
			Scanner input = new Scanner(file);
		){
			this.size = input.nextInt();
			map = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					this.map[i][j] = input.nextInt();
					// if (this.map[i][j] == 2) {
					// 	this.start = new Position(i+1, j+1);
					// }
					Random rand = new Random();
					this.start = new Position(rand.nextInt(size), rand.nextInt(size));
				}
			}
			
		}catch(Exception e){
		}
	}

	public int getUnit(){
		return this.unit;
	}
	public int getSize(){
		return size;
	}
	public int[][] getMap(){
		return map;
	}
	public Position getStartPosition(){
		return start;
	}
}