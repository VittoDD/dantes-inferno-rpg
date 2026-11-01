package com.dantesinferno.rpg;

import com.dantesinferno.rpg.model.Circle;

public class Main {
    public static void main(String[] args) {
        Circle limbo = new Circle(1, "Limbo", "The circle of unbaptized and virtuous pagans.", 1);
        Circle lust = new Circle(2, "Lust", "Trapped in a violent storm for eternity.", 2);

        System.out.println("--- Dante's Inferno RPG ---");
        System.out.println(limbo);
        System.out.println(lust);
    }
}
