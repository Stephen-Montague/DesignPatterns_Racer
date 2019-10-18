package racingSim.terrain;

import javafx.scene.paint.Color;
import racingSim.Terrain;
import racingSim.Tile;

public class Grass extends Terrain {
    public Tile tile;
    private double movementModifier;

    public Grass (Tile tile, Color color) {
        this.tile = tile;
        this.setFill(color);
        movementModifier = 1;
    }

    public double getMovementModifier() {
        return movementModifier;
    }

    public Terrain getTerrain() {
        return this;
    }
    
    public String toString() {
        return "Grass";
    }
}
