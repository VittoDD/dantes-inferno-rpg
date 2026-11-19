package it.unicam.cs.mpgc.rpg132193.model;

/**
 * Represents a Circle of Hell in the Dante's Inferno RPG.
 * Encapsulates narrative data, asset paths and interactive hitbox boundaries.
 *
 * @author Vittoria Di Domizio
 * @version 1.5
 */

public class Circle {
    private final int number;
    private final String name;
    private final String description;
    private final int dangerLevel;
    private final String virgilioQuote;
    private final String targetObject;
    private final String imagePath;

    //hitbox boundaries (Rectangle)
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    /**
     * Constructs a new Circle of Hell.
     *
     * @param number      The circle number (e.g., 1 for Limbo, 9 for Cocytus)
     * @param name        The English name of the circle
     * @param description A brief description of the environment or punished sin
     * @param dangerLevel The scaling difficulty level for encounters in this circle
     * @param virgilioQuote Virgilio's hint for this level
     * @param targetObject The mysterious object the player must find
     * @param imagePath     The resource path for the background image
     * @param minX          Minimum X boundary for the target hitbox
     * @param maxX          Maximum X boundary for the target hitbox
     * @param minY          Minimum Y boundary for the target hitbox
     * @param maxY          Maximum Y boundary for the target hitbox
     */

    public Circle(int number, String name, String description, int dangerLevel, String virgilioQuote,
                  String targetObject, String imagePath, double minX, double maxX, double minY, double maxY) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.virgilioQuote = virgilioQuote;
        this.targetObject = targetObject;
        this.imagePath = imagePath;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    /**
     * Gets the circle number.
     *
     * @return The circle number (1 to 9).
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the name of the circle.
     *
     * @return The circle name.
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the description of the circle and its punishment.
     *
     * @return The circle description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the danger/difficulty level of the circle.
     *
     * @return The danger level integer.
     */
    public int getDangerLevel() {
        return dangerLevel;
    }

    /**
     * Gets Virgilio's quote or hint for the level.
     *
     * @return Virgilio's quote string.
     */
    public String getVirgilioQuote(){
        return virgilioQuote;
    }

    /**
     * Gets the target object that the player must find to complete the level.
     *
     * @return The target object name.
     */
    public String getTargetObject(){
        return targetObject;
    }

    /**
     * Gets the resource path for the circle's background image.
     *
     * @return The image path string.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Gets the minimum X boundary of the target hitbox.
     *
     * @return Minimum X coordinate.
     */

    public double getMinX() {
        return minX;
    }

    /**
     * Gets the maximum X boundary of the target hitbox.
     *
     * @return Maximum X coordinate.
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * Gets the minimum Y boundary of the target hitbox.
     *
     * @return Minimum Y coordinate.
     */
    public double getMinY() {
        return minY;
    }

    /**
     * Gets the maximum Y boundary of the target hitbox.
     *
     * @return Maximum Y coordinate.
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * Checks if given click coordinates fall inside this circle's active hitbox.
     *
     * @param x Click X coordinate relative to the image
     * @param y Click Y coordinate relative to the image
     * @return true if the click is within boundaries, false otherwise
     */
    public boolean contains(double x, double y) {
        return (x >= minX && x <= maxX && y >= minY && y <= maxY);
    }

    @Override
    public String toString() {
        return "Circle " + number + ": " + name + " | Target: " + targetObject +
                " | Hitbox X:[" + minX + "-" + maxX + "] Y:[" + minY + "-" + maxY + "]";
    }
}
