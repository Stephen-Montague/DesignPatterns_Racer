package racingSim;

import javafx.scene.paint.Color;
import racingSim.terrain.Grass;

public interface Constants {
    // Screen
    double WIDTH = 500;
    double HEIGHT = 300;
    double SCREEN_CENTER = WIDTH / 2;
    double HALF_SCREEN = WIDTH / 2;
    double ZERO_X = 0;
    double HORIZON = 140;

    // Movement
    double BASE_MOVEMENT_X = 2;
    double BASE_MOVEMENT_Y = 1;
    double MAX_MOVEMENT_X = 5; // Base + Asphalt modifier
    double STEERING_TOLERANCE = 20;
    double ROAD_MARGIN = 40; // Passable area near road

    // World Objects
    int TILE_WIDTH = 100;
    int TILE_HEIGHT = 40;

    // Terrain
    Terrain DEFAULT_TERRAIN = new Grass(new Tile(), Color.LIGHTGREEN);
}
