package racingSim;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import static racingSim.Constants.*;

import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    private static ArrayList<Terrain> mainStreetNorthTiles =  new ArrayList<>();
    private static ArrayList<Terrain> mainStreetSouthTiles =  new ArrayList<>();

    public void start(Stage stage) {
        Pane root = new Pane();

        Rectangle sky = new Rectangle(WIDTH,HORIZON,Color.LIGHTBLUE);
        root.getChildren().add(sky);

        Text directions = new Text();
        directions.setText("Move mouse to maneuver down the road");
        directions.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 18));
        directions.setFill(Color.DARKBLUE);
        directions.setX((SCREEN_CENTER) - directions.getBoundsInParent().getCenterX());
        directions.setY(90);
        root.getChildren().add(directions);

        Text goal = new Text();
        goal.setText("GOAL!");
        goal.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 36));
        goal.setFill(Color.DARKBLUE);
        goal.setX(2750);  // 2500 = 25 Road tiles * 100 tile width
        goal.setY(90);
        root.getChildren().add(goal);

        // Add streets (ArrayLists of Terrain-decorated Tile)
        ArrListTerrainFactory tf = new ArrListTerrainFactory();
        mainStreetNorthTiles = tf.makeStreet(mainStreetNorthTiles,180, 25);
        mainStreetSouthTiles = tf.makeStreet(mainStreetSouthTiles,220,25);
        for (Terrain tile : mainStreetNorthTiles) {
            root.getChildren().add(tile);
        }
        for (Terrain tile : mainStreetSouthTiles) {
            root.getChildren().add(tile);
        }

        // Add computerCar
        ComputerCar computerCar = new ComputerCar(0,180,30);
        computerCar.setFill(Color.DARKRED);
        root.getChildren().add(computerCar);

        // Add playerCar
        PlayerCar playerCar = new PlayerCar(0,220,30);
        playerCar.setFill(Color.DARKBLUE);
        root.getChildren().add(playerCar);

        // Set cars to observe street
        for (Terrain tile : mainStreetNorthTiles) {
            tile.registerObserver(playerCar);
            tile.registerObserver(computerCar);
        }
        for (Terrain tile : mainStreetSouthTiles) {
            tile.registerObserver(playerCar);
            tile.registerObserver(computerCar);
        }

        // Add scene & stage
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.LIGHTGREEN);
        stage.setScene(scene);
        stage.setTitle("Racing Simulator");
        stage.setResizable(false);
        stage.show();

        // Get user input to steer playerCar
        root.setOnMouseMoved((mouseEvt) -> {
                    playerCar.setSteeringDirection(mouseEvt.getX(),mouseEvt.getY());  // See program notes
                    }
        );

        // Main Loop
        Timeline timeline = new
                Timeline(new KeyFrame(
                Duration.millis(30),
                (evt) -> {
                    Tile.checkCollision(mainStreetNorthTiles, mainStreetSouthTiles, playerCar);
                    Tile.checkCollision(mainStreetNorthTiles, mainStreetSouthTiles, computerCar);
                    playerCar.move(mainStreetNorthTiles, mainStreetSouthTiles, goal, computerCar);
                    computerCar.move(mainStreetNorthTiles, mainStreetSouthTiles, goal, playerCar);
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // On Race Start
        Timeline directionsVisibleTimer = new
                Timeline(new KeyFrame(
                Duration.seconds(4),
                (evt) ->
                        directions.setVisible(false)
        ));
        directionsVisibleTimer.setCycleCount(1);
        directionsVisibleTimer.play();

        // Rescue player car lost out-of-bounds (hack for rare bug)
        Timeline rescueTimer = new
                Timeline(new KeyFrame(
                Duration.seconds(2),
                (evt) -> {
                    if (playerCar.getCenterX() < ZERO_X || playerCar.getCenterX() > WIDTH ||
                            playerCar.getCenterY() < HORIZON || playerCar.getCenterY() > HEIGHT) {
                        playerCar.setCenterX(50);
                        playerCar.setCenterY(220);
                    }
                }
        ));
        rescueTimer.setCycleCount(Animation.INDEFINITE);
        rescueTimer.play();
    }
    public static void main(String[] args) {
        launch();
    }
}
