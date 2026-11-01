package com.dantesinferno.rpg.model;

/**
 * Represents a Circle of Hell in the Dante's Inferno RPG.
 * Each circle has a number, a name, a description, a difficulty level,
 * a Virgilio's quote acting as a hint for the player and the secret target
 * object to find.
 *
 * @author Vittoria Di Domizio
 * @version 1.2
 */

public class Circle {
    private final int number;
    private final String name;
    private final String description;
    private final int dangerLevel;
    private final String virgilioQuote;
    private final String targetObject;

    /**
     * Constructs a new Circle of Hell.
     *
     * @param number      The circle number (e.g., 1 for Limbo, 9 for Cocytus)
     * @param name        The English name of the circle
     * @param description A brief description of the environment or punished sin
     * @param dangerLevel The scaling difficulty level for encounters in this circle
     * @param virgilioQuote Virgilio's hint for this level
     * @param targetObject The mysterious object the player must find
     */

    public Circle(int number, String name, String description, int dangerLevel, String virgilioQuote,
                  String targetObject) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.virgilioQuote = virgilioQuote;
        this.targetObject = targetObject;
    }

    //getter method
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

    @Override
    public String toString() {
        return "Circle " + number + ": " + name +
                " | Hint: \"" + virgilioQuote + "\" | Target: " + targetObject;
    }
}
