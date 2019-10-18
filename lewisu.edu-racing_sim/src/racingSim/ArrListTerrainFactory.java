package racingSim;

import javafx.scene.paint.Color;
import racingSim.terrain.*;

import static racingSim.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;

class ArrListTerrainFactory {
    // Returns ArrayList of Terrain-decorated Tile
    ArrayList<Terrain> makeStreet(ArrayList<Terrain> streetName, double latitude, int numTiles) {
        Terrain terrain;
        // Every other tile has 60 percent chance of non-asphalt terrain
        for (int i = 0; i < numTiles; i++) {
            if (i % 2 == 0) {
                terrain = new Asphalt(new Tile(), Color.DARKGREY);
            } else {
                terrain = getRandomTerrain();
            }
            terrain.setX(i * TILE_WIDTH);
            terrain.setY(latitude);
            streetName.add(terrain);
        }
        return streetName;
    }

    private Terrain getRandomTerrain() {
        Asphalt asphalt = new Asphalt(new Tile(), Color.DARKGREY);
        Grass grass = new Grass(new Tile(), Color.LIGHTGREEN);
        Sand sand = new Sand(new Tile(), Color.BURLYWOOD);
        Mud mud = new Mud(new Tile(), Color.MAROON);
        ArrayList<Terrain> terrainOptions = new ArrayList<>(Arrays.asList(
                asphalt,
                asphalt,
                grass,
                sand,
                mud));
        return terrainOptions.get((int) (Math.random() * terrainOptions.size()));
    }
}
