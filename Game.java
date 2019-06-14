import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application{
	private Map map;
	//private Player player;
	//private Food food;

	public static void main(String[] args){
		launch(args);
	}

	public void start(Stage primaryStage){
			Map map = new Map("map.txt");
			Pane pane = new Pane();

			for (int i = 0; i < map.getSize(); i++) {
				for (int j = 0; j < map.getSize(); j++) {
					Rectangle rect = new Rectangle(j * map.getUnit(), i * map.getUnit(), map.getUnit(), map.getUnit());
					if(map.getMap()[i][j] == 0){
						rect.setFill(Color.WHITE);
					}
					
					else{
						rect.setFill(Color.BLACK);
						
					}
					rect.setStroke(Color.BLACK);
					pane.getChildren().add(rect);
				}
			}
			Scene scene = new Scene(pane, map.getSize() * map.getUnit() + 0.3 * (map.getSize() * map.getUnit()), map.getSize() * map.getUnit() + 0.3 * (map.getSize() * map.getUnit()));
			primaryStage.setScene(scene);
			primaryStage.show();
	}
}


