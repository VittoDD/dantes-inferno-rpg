package com.dantesinferno.rpg.model;

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

    //getter methods
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public String getVirgilioQuote(){
        return virgilioQuote;
    }

    public String getTargetObject(){
        return targetObject;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    /**
     * Checks if given click coordinates fall inside this circle's active hitbox.
     *
     * @param x Click X coordinate relative to the image
     * @param y Click Y coordinate relative to the image
     * @return true if the click is within boundaries
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
