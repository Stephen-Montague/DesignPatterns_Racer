package racingSim.terrain;

import javafx.scene.paint.Paint;
import racingSim.Terrain;
import racingSim.Tile;

public class Asphalt extends Terrain {
    public Tile tile;
    private double movementModifier;

    public Asphalt (Tile tile, Paint color) {
        this.tile = tile;
        this.setFill(color);
        movementModifier = 3;
    }

    public double getMovementModifier() {
        return movementModifier;
    }

    public Terrain getTerrain() {
        return this;
    }

    public String toString() {
        return "Asphalt";
    }
}
