package com.dantesinferno.rpg;

import com.dantesinferno.rpg.model.Circle;

public class Main {
    public static void main(String[] args) {
        Circle limbo = new Circle(
                1,
                "Limbo",
                "The circle of unbaptized and virtuous pagans.",
                1,
                "Non ti curar di lor, ma guarda e passa."
        );

        System.out.println("--- Dante's Inferno RPG - Level Test ---");
        System.out.println(limbo);
    }
}
