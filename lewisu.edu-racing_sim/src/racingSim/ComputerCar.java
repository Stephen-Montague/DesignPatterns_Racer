package racingSim;

import javafx.scene.text.Text;

import static racingSim.Constants.*;

import java.util.ArrayList;

class ComputerCar extends Car implements TileObserver{
    ComputerCar(double x, double y, double rad) {
        super(x, y, rad);
        directionX = DirectionX.POSITIVE;
        directionY = DirectionY.STILL;
        setLightingEffect();
    }

    void checkBounds(double carX, double carY, double minY, double maxY, Text goal) {
        double goalX = goal.getBoundsInParent().getCenterX();
        if (carX + MAX_MOVEMENT_X >= goalX + HALF_SCREEN ||
                (carX + MAX_MOVEMENT_X <= ZERO_X) && (directionX == DirectionX.NEGATIVE)) {
            flipDirectionX();
        }
        if (carY <= minY - ROAD_MARGIN || carY >= maxY + ROAD_MARGIN) {
            flipDirectionY();
        }
    }

    void executeMovement(ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal, Car other) {
        if (other.isAtCenterX()) {
            this.setCenterX(this.carX + (getMovementX() - other.getMovementX()));
        } else {
            setCenterX(carX + getMovementX());
        }
        setCenterY(carY + getMovementY());
        System.out.println("Computer Speed " + getMovementX());
    }

    void setSteeringDirection(double mouseX, double mouseY) {
    	// Empty - currently not required
    }

    void simulateMovement(double simMove, ArrayList<Terrain> street1, ArrayList<Terrain> street2, Text goal) {
    	// Empty
    }
}
