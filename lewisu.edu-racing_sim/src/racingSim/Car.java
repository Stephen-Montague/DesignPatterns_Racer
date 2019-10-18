package racingSim;

import javafx.scene.shape.Circle;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static racingSim.Constants.*;

import java.util.ArrayList;

public abstract class Car extends Circle implements TileObserver {
    double carX;
    double carY;
    private double movementX;
    private double movementY;
    private boolean isAtCenterX;
    private Terrain currentTerrain;
    enum DirectionX {
        POSITIVE(1),
        NEGATIVE(-1),
        STILL(0);
        public int value;
        DirectionX(int value) {
            this.value = value;
        }
    }
    enum DirectionY {
        POSITIVE(1),
        NEGATIVE(-1),
        STILL(0);
        public int value;
        DirectionY(int value) {
            this.value = value;
        }
    }
    DirectionX directionX;
    DirectionY directionY;

    Car(double x, double y, double rad) {
        super(x, y, rad);
        directionX = DirectionX.POSITIVE;
        directionY = DirectionY.STILL;
        setLightingEffect();
    }

    final void move(ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal, Car otherCar) {
        carX = getCenterX();
        carY = getCenterY();
        double minY = street1.get(0).getBoundsInParent().getMinY();
        double maxY = street2.get(0).getBoundsInParent().getMaxY();

        checkBounds(carX, carY, minY, maxY, goal);
        setMovementX(currentTerrain);
        setMovementY();
        executeMovement(street1, street2, goal, otherCar);
    }

    abstract void setSteeringDirection(double mouseX, double mouseY);

    abstract void checkBounds(double carX, double carY, double minY, double maxY, Text goal);

    abstract void executeMovement(ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal, Car other);

    abstract void simulateMovement(double simMove, ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal);

    public void update(Tile tile){
        currentTerrain = (Terrain) tile;
    }

    private void setMovementX(Terrain currentTerrain) {
        double movementModifier = currentTerrain.getMovementModifier();
        movementX = directionX.value * (BASE_MOVEMENT_X + movementModifier);
    }

    private void setMovementY() {
        movementY = directionY.value * (BASE_MOVEMENT_Y);
    }

    void flipDirectionX() {
        if (directionX == DirectionX.POSITIVE) {
            directionX = DirectionX.NEGATIVE;
        } else if (directionX == DirectionX.NEGATIVE) {
            directionX = DirectionX.POSITIVE;
        } else {
            directionX = DirectionX.STILL;
        }
    }

    void flipDirectionY() {
        if (directionY == DirectionY.POSITIVE) {
            directionY = DirectionY.NEGATIVE;
        } else if (directionY == DirectionY.NEGATIVE) {
            directionY = DirectionY.POSITIVE;
        } else {
            directionY = DirectionY.STILL;
        }
    }

    double getMovementX() {
        return movementX;
    }

    double getMovementY() {
        return movementY;
    }

    boolean isAtCenterX() {
        return isAtCenterX;
    }

    void setIsAtCenterX(boolean b) {
        isAtCenterX = b;
    }

    void setLightingEffect() {
        Light.Spot spotlight = new Light.Spot();
        spotlight.setColor(Color.IVORY);
        spotlight.setX(70);
        spotlight.setY(25);
        spotlight.setZ(90);
        Lighting lighting = new Lighting();
        lighting.setLight(spotlight);
        this.setEffect(lighting);
    }
}
