package com.dantesinferno.rpg;

import com.dantesinferno.rpg.model.Circle;
import com.dantesinferno.rpg.model.LevelData;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Circle> inferno = LevelData.getInfernoCircles();

        System.out.println("--- Dante's Inferno RPG: All 9 Circles & Targets ---");
        for (Circle circle : inferno) {
            System.out.println(circle);
        }
    }
}
