package com.dantesinferno.rpg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the static data and initialization for the 9 Circles of Hell.
 *
 * @author Vittoria Di Domizio
 * @version 1.3
 */

public class LevelData {

    /**
     * Generates and returns the list of all 9 Circles of Hell with narrative data and
     * hitbox boundaries
     *
     * @return List of Circle objects representing the 9 levels of Hell.
     */

    public static List<Circle> getInfernoCircles() {
        List<Circle> circles = new ArrayList<>();

        //Circle 1: Limbo (tested and working hitbox coordinates and asset path)
        circles.add(new Circle(1, "Limbo",
                "The circle of unbaptized and virtuous pagans.", 1,
                "Tra ombre di saggi e poeti senza speme, cerca ciò che custodisce la vera sapienza.",
                "Ancient Scroll", "src/main/resources/images/limbo.jpg", 240.0,
                290.0, 170.0, 220.0));

        //Circles 2 to 9: Configured with placeholder boundaries, awaiting respective level design
        circles.add(new Circle(2, "Lust", "Trapped in a violent, unending storm.", 2,
                "Dove il vento soffia implacabile e i cuori si sfiorano in eterno, " +
                        "riposa il pegno di un amore perduto.",
                "Broken Medallion", "...", 0.0, 0.0, 0.0, 0.0));

        circles.add(new Circle(3, "Gluttony", "Wallowing in foul, freezing rain and mud.",
                3, "Sotto la pioggia fangosa e l'insaziabile pianto, " +
                "brilla ciò che non può mai essere colmato.",
                "Muddy Silver Chalice", "...", 0.0, 0.0, 0.0, 0.0));

        circles.add(new Circle(4, "Greed", "Pushing heavy weights in eternal opposition.",
                4, "Chi accumula e chi disperde si scontra senza sosta; " +
                "cerca il metallo che appesantisce l'anima.", "Heavy Gold Pouch", "...",
                0.0, 0.0, 0.0, 0.0));

        circles.add(new Circle(5, "Wrath", "Fighting eternally in the murky waters of the Styx.",
                5, "Nelle torbide acque dello Stige, dove il fango ribolle di rabbia cieca, " +
                "si cela un ferro conteso.", "Broken Sword Hilt", "...", 0.0,
                0.0, 0.0, 0.0));

        circles.add(new Circle(6, "Heresy", "Trapped inside burning tombs for eternity.",
                6, "Tra tombe scoperte e fuochi che non danno calore, " +
                "cerca la fiamma che custodisce la verità negata.", "Flaming Stone Urn",
                " ", 0.0, 50.0, 0.0, 0.0));

        circles.add(new Circle(7, "Violence", "Submerged in a river of boiling blood.",
                7, "Lungo il fiume di sangue bollente e tra i rami che gemono dolore, " +
                "si nasconde il segno della violenza.", "Bloody Thorn", "...", 0.0,
                0.0, 0.0, 0.0));

        circles.add(new Circle(8, "Fraud", "Divided into Malebolge with various torments.",
                8, "Nei fossi profondi delle Malebolge, dove l'inganno tesse la sua tela, " +
                "trova ciò che apre porte proibite.", "False Key", "...", 0.0,
                0.0, 0.0, 0.0));

        circles.add(new Circle(9, "Treachery", "Frozen in the icy expanse of Lake Cocytus.",
                9, "Nell'immensa distesa gelata di Cocito, dove ogni calore si spegne, " +
                "riposa l'ultimo frammento di gelido gelo.", "Eternal Ice Fragment", "...",
                0.0, 0.0, 0.0, 0.0));

        return circles;
    }
}
