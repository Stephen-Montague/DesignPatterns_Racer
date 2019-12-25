# lewis-ood-final_project
A simple car-racing simulator that demo's 4 design patterns.

Add the JavaFX library to run App.java 

In this program (App.java), I intended to create a fun demo of design patterns 
studied in Object Oriented Design at Lewis University.  I used the Observer, Decorator, 
Factory, and Template patterns to display two sprite cars (one controlled by 
the user, the other, automated) racing along a tiled street of randomly-varied terrain 
toward an (initially) off-screen goal.  

Each street tile (class Tile) uses the Observer pattern to register observers (of class Car).  
A Terrain class has been implemented with four children (Asphalt, Grass, Sand, Mud– located in 
the “terrain” package), and each Tile gains new properties when wrapped by Terrain, 
using the Decorator pattern.  A Factory pattern (designated with “Factory” in the class name), 
is used to create an ArrayList collection of pseudo-randomly decorated Tile, and this ArrayList 
of Terrain-Tile forms a “street” for sprites.  For sprites, I implemented a Template pattern 
to control movement, with shared functions in an abstract Car class and variations held 
in each concrete child (class PlayerCar and ComputerCar). 

I manually tested the program repeatedly and used a J-unit test on the main method, which 
showed successful coverage of nearly all functions.  Those not covered were mostly of the class 
instance “toString()” variety, which are not used yet but could be useful in the future.  

On future potential, I could expand the simulated Terrain to cover the entire screen, enabling 
the creation of an interactive sky and other features.  I could also add new classes of sprites, 
although, for further development, a new game engine should be considered, or at least, I would 
need to improve my use of JavaFX.  Currently, not all actions are centralized in a main loop or 
state machine.  Given more resources, I would also add UI / menu options, before, during, 
and after each game.  

It was a special challenge to implement patterns with JavaFX, since this was my first experience 
with this GUI framework.  Some patterns, like Observer, are already heavily implemented 
within JavaFX, so rather than creating this pattern from scratch (for demonstration purposes), 
I may have done better to rely on the code already in place.  For a student, though, it was good 
to learn by experience, and now I’m eager for more in this field.
