// Project: 	dinh_w_lab15
// Author:		Linh Dinh
// Date: 		December 5, 2020
// Purpose:		Prove the successful installation of the JavaFX library.
//				Create a GUI interface.
//				Learn how to create graphic objects.
//				Review lists and random commands

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {
	public ArrayList<Circle> Balls = new ArrayList<Circle>();		// Arraylist for balls
	int iArraySize;
	@Override
	public void start(Stage primaryStage) {
		try {
			// window size
			int xSize = 640;
			int ySize = 400;
			
			// set stage
			primaryStage.setTitle("Linh Dinh");						// set Title				
			Scene scene = new Scene(new Group(),xSize,ySize);		// set Window size
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // CSS
			scene.setFill(Color.WHITE);								// set color
			BorderPane canvas = new BorderPane();					// create border panel
		    
			// add to scene
			Balls.add(spawnCircle());			// spawn a circle
			((Group)scene.getRoot()).getChildren().addAll(Balls.get(iArraySize++)); // add circle to scene and array size


			// timeline for spawning circles every 5 seconds
	     	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
	     			@Override
	     			public void handle(ActionEvent event)
	     			{
	     				Balls.add(spawnCircle());
	     				((Group)scene.getRoot()).getChildren().addAll(Balls.get(iArraySize++));
	     			}
	     				}
	     					));
			
	     	
	     	primaryStage.setScene(scene); // set scene
			primaryStage.show();		  // show scene 
			

	        // timeline settings
			timeline.setCycleCount(Timeline.INDEFINITE); // does not end
			timeline.setAutoReverse(true);				 // reverse automatically (doesn't really do anything for this)
			timeline.play();							 // play the timeline
			
			// random number for different speeds
			Random rand = new Random();
			int speed = rand.nextInt(9);
			speed += 1;
			
			
			// check boundaries
			Timeline boundaryCheck = new Timeline(new KeyFrame(Duration.millis(speed*10), new EventHandler<ActionEvent>() {
     			@Override
     			public void handle(ActionEvent event) {
                	int j = 0;								// variable for the loop
     				while (j < Balls.size()) {
         				Balls.get(j).setLayoutX(Balls.get(j).getLayoutX() + Balls.get(j).getTranslateX());
         				Balls.get(j).setLayoutY(Balls.get(j).getLayoutY() + Balls.get(j).getTranslateY());
     					if(Balls.get(j).getLayoutX() > xSize)
     						Balls.get(j).setTranslateX(Balls.get(j).getTranslateX() * -1);
     					if(Balls.get(j).getLayoutY() > ySize)
     						Balls.get(j).setTranslateY(Balls.get(j).getTranslateY() * -1);
     					if(Balls.get(j).getLayoutX() < 0)
     						Balls.get(j).setTranslateX(Balls.get(j).getTranslateX() * -1);
     					if(Balls.get(j).getLayoutY() < 0)
     						Balls.get(j).setTranslateY(Balls.get(j).getTranslateY() * -1);
     					j++;
     					
     					}						
     				}
     				
					}));
			
				// timeline settings
				boundaryCheck.setCycleCount(Timeline.INDEFINITE); // does not end
				boundaryCheck.setAutoReverse(true);				  // auto reverse
				boundaryCheck.play();							  // plays timeline
		       
			

					
			  } catch(Exception e) {		// catching errors
			e.printStackTrace();
		}
		System.out.println("in start() method");	// check if start works
	}
	
	public static void main(String[] args) {
		launch(args);								// launches javaFX
	}
	@Override
	public void init() {
		System.out.println("in init() method");		// check if init works
	}
	@Override
	public void stop() {							
		System.out.println("in stop() method");		// check if stop works
	}
public Circle spawnCircle(){
		// random for color
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		Color randomColor = Color.rgb(r,g,b);
		

		// random number for diameter
		int n = rand.nextInt(20);
		n += 5;						// number from 5-25
		
		// random movement
		int iX = rand.nextInt(11);
		iX += 1;
		int iY = rand.nextInt(11);
		iY += 1;
		
		//set circle
		Circle circ = new Circle(n);
		circ.setRadius(n/2);
		circ.setCenterX(n/2);
		circ.setCenterY(n/2);
		circ.setFill(randomColor);
		circ.setTranslateX(iX);
		circ.setTranslateY(iY);
		

		return circ;	// return circle
}
}
	


