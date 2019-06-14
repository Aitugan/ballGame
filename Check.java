import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Check extends Application{
	private Map map;
	private MyPlayer player;
	private Food food;
	static String name;
	public static void main(String[] args){
		Check game = new Check();
		name = args[0];
		game.launch(args);
	}
	public void start(Stage primaryStage){
			map = new Map(name);
			for (int i = 0; i < map.getSize(); i++) {
				for (int j = 0; j < map.getSize(); j++) {
					Rectangle rect = new Rectangle(j * map.getUnit(), i * map.getUnit(), map.getUnit(), map.getUnit());
					if(map.getMap()[i][j] == 0){
						rect.setFill(Color.WHITE);
						rect.setStroke(Color.BLACK);
					}
					else if (map.getMap()[i][j] == 2){
						rect.setFill(Color.WHITE);
						rect.setStroke(Color.BLACK);
					}
					else{
						rect.setFill(Color.BLACK);
						rect.setStroke(Color.WHITE);
					}
					map.getChildren().add(rect);
				}
			}
			this.player = new MyPlayer(map);
			map.getChildren().add(player.getBall());
			food = new Food(map, player);

			Scene scene = new Scene(map, map.getSize() * map.getUnit() + 0.3 * (map.getSize() * map.getUnit()), map.getSize() * map.getUnit() + 0.3 * (map.getSize() * map.getUnit()));
			
			Thread thread = new Thread(()->{
				try{
					while(true){
						int[] start = {
							(int)(player.getBall().getCenterY() - map.getUnit() / 2) / map.getUnit(),
							(int)(player.getBall().getCenterX() - map.getUnit() / 2) / map.getUnit()
						};
						int[] end = {food.foodPosition.getY(),food.foodPosition.getX()};
						int[][] arr = ShortestPathBetweenCellsBFS.shortestPath(map.getMap(), start, end);
						for (int i = 0; i < arr.length; i++) {
							player.getBall().setCenterX(arr[i][0] * map.getUnit() + map.getUnit()/2);
							player.getPosition().setX((int)((player.getBall().getCenterX()-map.getUnit()/2)/map.getUnit()));
							Thread.sleep(70);
							player.getBall().setCenterY(arr[i][1] * map.getUnit()+map.getUnit()/2);
							player.getPosition().setY((int)((player.getBall().getCenterY()-map.getUnit()/2)/map.getUnit()));
							Thread.sleep(70);
						}
						Thread.sleep(800);
					}
				}catch(InterruptedException e){
					System.out.println(e);
				}
			});
			thread.start();
			
			primaryStage.setScene(scene);
			primaryStage.show();
	}
}