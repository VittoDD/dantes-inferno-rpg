package com.dantesinferno.rpg.model;

/**
 * Represents a Circle of Hell in the Dante's Inferno RPG.
 * Each circle contains environmental details, Virgilio's hint, the target object,
 * and its 2D coordinates with hit tolerance.
 *
 * @author Vittoria Di Domizio
 * @version 1.3
 */

public class Circle {
    private final int number;
    private final String name;
    private final String description;
    private final int dangerLevel;
    private final String virgilioQuote;
    private final String targetObject;
    private final double targetX;
    private final double targetY;
    private final double targetRadius;

    /**
     * Constructs a new Circle of Hell.
     *
     * @param number      The circle number (e.g., 1 for Limbo, 9 for Cocytus)
     * @param name        The English name of the circle
     * @param description A brief description of the environment or punished sin
     * @param dangerLevel The scaling difficulty level for encounters in this circle
     * @param virgilioQuote Virgilio's hint for this level
     * @param targetObject The mysterious object the player must find
     * @param targetX       The X-coordinate of the target object center
     * @param targetY       The Y-coordinate of the target object center
     * @param targetRadius  The click tolerance radius in pixels
     */

    public Circle(int number, String name, String description, int dangerLevel, String virgilioQuote,
                  String targetObject, double targetX, double targetY, double targetRadius) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.virgilioQuote = virgilioQuote;
        this.targetObject = targetObject;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetRadius = targetRadius;
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

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public double getTargetRadius() {
        return targetRadius;
    }

    @Override
    public String toString() {
        return "Circle " + number + ": " + name + " | Target: " + targetObject +
                " at (" + targetX + ", " + targetY + ") radius: " + targetRadius;
    }
}
