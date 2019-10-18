package racingSim;

import javafx.scene.text.Text;

import static racingSim.Constants.*;

import java.util.ArrayList;

public class PlayerCar extends Car implements TileObserver {

    PlayerCar(double x, double y, double rad) {
        super(x, y, rad);
    }

    void setSteeringDirection(double mouseX, double mouseY) {
        carX = getCenterX();
        if (Math.abs(mouseX - carX) < STEERING_TOLERANCE) {
            directionX = DirectionX.STILL;
        } else if (mouseX > carX) {
            directionX = DirectionX.POSITIVE;
        } else {  // (mouseX < carX)
            directionX = DirectionX.NEGATIVE;
        }

        carY = getCenterY();
        if (Math.abs(mouseY - carY) < STEERING_TOLERANCE) {
            directionY = DirectionY.STILL;
        } else if (mouseY > carY) {
            directionY = DirectionY.POSITIVE;
        } else {  // mouseY < carY
            directionY = DirectionY.NEGATIVE;
        }
    }

    void checkBounds(double carX, double carY, double minY, double maxY, Text goal) {
        if (carX + MAX_MOVEMENT_X >= WIDTH || carX + MAX_MOVEMENT_X <= ZERO_X) {
            flipDirectionX();
        }
        if (carY <= minY - ROAD_MARGIN || carY >= maxY + ROAD_MARGIN) {
            flipDirectionY();
        }
    }

    void executeMovement(ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal, Car other) {
        double goalX = goal.getBoundsInParent().getCenterX();
        if (carX < SCREEN_CENTER || directionX == DirectionX.NEGATIVE  || carX > goalX) {
            setCenterX(carX + getMovementX());
            setIsAtCenterX(false);
        } else {
            simulateMovement(getMovementX(), street1, street2, goal);
            setIsAtCenterX(true);
        }
        setCenterY(carY + getMovementY());
        System.out.println("Player Speed " + getMovementX());
    }

    // Moves street to simulate car movement
    void simulateMovement(double simMove, ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal) {
        for (Terrain tile : street1) {
            tile.setX(tile.getX() - simMove);
        }
        for (Terrain tile : street2) {
            tile.setX(tile.getX() - simMove);
        }
        goal.setX(goal.getX() - simMove);
    }

    public String toString() {
        return "PlayerCar";
    }
}
