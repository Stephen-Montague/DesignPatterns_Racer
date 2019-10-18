package racingSim;

import javafx.geometry.Point2D;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

import static racingSim.Constants.*;

import java.util.ArrayList;

public class Tile extends javafx.scene.shape.Rectangle implements TileSubject {

    private ArrayList<Car> observers;

    Tile() {
        this.observers = new ArrayList<>();
        this.setWidth(TILE_WIDTH);
        this.setHeight(TILE_HEIGHT);
        setShadowEffect();
    }

    public void registerObserver(Car car) {
        observers.add(car);
    }

    public void removeObserver(Car car) {
        observers.remove(car);
    }

    public void updateObserver(Car car) {
        for (Car observer: observers){
            if (observer.equals(car)) {
                observer.update(this);
            }
        }
    }

    void receiveCollision(Car car) {
        updateObserver(car);
    }

    private void setShadowEffect() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(1);
        innerShadow.setOffsetY(1);
        innerShadow.setRadius(4);
        innerShadow.setColor(Color.GRAY);
        this.setEffect(innerShadow);
    }

    public String toString() {
        return "Tile";
    }

    static void checkCollision (ArrayList<Terrain> street1, ArrayList<Terrain> street2, Car car) {
        boolean collisionDetected = false;
        Point2D carPosition = new Point2D(car.getCenterX(), car.getCenterY() + car.getRadius());
        Terrain terrain = null;
        for (Terrain tile : street1) {
            if (tile.getBoundsInParent().contains(carPosition)) {
                terrain = tile;
                collisionDetected = true;
            }
        }
        for (Terrain tile : street2) {
            if (tile.getBoundsInParent().contains(carPosition)) {
                terrain = tile;
                collisionDetected = true;
            }
        }
        if (collisionDetected) {
            terrain.receiveCollision(car);
        } else { // Car is off-road, if tile map is upgraded to cover all areas, below can be removed
            car.update(DEFAULT_TERRAIN);
        }
    }
}
