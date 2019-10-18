package racingSim;

interface TileSubject {
    void registerObserver(Car car);
    void removeObserver(Car car);
    void updateObserver(Car car);
}
