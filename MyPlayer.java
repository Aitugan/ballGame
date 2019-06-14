import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.*;

public class MyPlayer implements Player{
	private Circle ball;
	private Map map;
	private Position position;


	public MyPlayer(Map map){
		this.map = map;
		position = map.getStartPosition();
		ball = new Circle(map.getStartPosition().getX() + map.getUnit()/2, map.getStartPosition().getY() + map.getUnit()/2, map.getUnit()/2);
		ball.setFill(Color.RED);
		//map.getChildren().add(ball);
	}
	public Circle getBall(){
		return ball;
	}
	public void moveUp(){
		if (ball.getCenterY() - map.getUnit() < 0){
			System.out.println("Invalid position!");
			return;
		} 
		if (map.getMap()[(int)((ball.getCenterY()-map.getUnit())/map.getUnit())][(int)(ball.getCenterX())/map.getUnit()] == 1){
			System.out.println("Invalid position!");
			return;
		}
		ball.setCenterY(ball.getCenterY() - map.getUnit());
		position.setX((int)(ball.getCenterX()/map.getUnit()));
		position.setY((int)(ball.getCenterY()/map.getUnit()));
	}
	public void moveDown(){
		if (ball.getCenterY() + map.getUnit() > map.getSize() * map.getUnit()){
			System.out.println("Invalid position!");
			return;
		} 
		if (map.getMap()[(int)(ball.getCenterY()+map.getUnit())/map.getUnit()][(int)(ball.getCenterX())/map.getUnit()] == 1){
			System.out.println("Invalid position!");
			return;
		}
		ball.setCenterY(ball.getCenterY() + map.getUnit());
		position.setX((int)(ball.getCenterX()/map.getUnit()));
		position.setY((int)(ball.getCenterY()/map.getUnit()));
		}
	public void moveLeft(){
		if (ball.getCenterX() - map.getUnit() < 0){
			System.out.println("Invalid position!");
			return;
		} 
		if (map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX() - map.getUnit())/map.getUnit()] == 1){
			System.out.println("Invalid position!");
			return;
		}

		ball.setCenterX(ball.getCenterX() - map.getUnit());
		position.setX((int)(ball.getCenterX()/map.getUnit()));
		position.setY((int)(ball.getCenterY()/map.getUnit()));
	}
	public void moveRight(){
		if (ball.getCenterX() + map.getUnit() > map.getSize() * map.getUnit()){
			System.out.println("Invalid position!");
			return;
		} 
		if (map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX()/map.getUnit()) + 1] == 1){
			System.out.println("Invalid position!");
			return;
		}
		ball.setCenterX(ball.getCenterX() + map.getUnit());
		position.setX((int)(ball.getCenterX()/map.getUnit()));
		position.setY((int)(ball.getCenterY()/map.getUnit()));
	}	
	public Position getPosition(){
		return position;
	}
}


