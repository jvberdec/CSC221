package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

import java.util.Map;

public class PieChart extends sample.HistogramAlphaBet {
    int charNumber;

    public void setCharNumber(int i) {
        charNumber = i;
    }

    Map<Character, Double> sortedAngles = getSortedProbabilities();

    public Map<Character, Double> getAngles() {
        double a;
        for (Map.Entry<Character, Double> entry : getSortedProbabilities().entrySet()) {
            a = entry.getValue() * 360;
            Math.toDegrees(a);
            sortedAngles.put(entry.getKey(), a);
        }
        return sortedAngles;
    }

    public String toString() {
        String s = "";
        for (Map.Entry<Character, Double> entry : getAngles().entrySet()) {
            s += ("Character = " + entry.getKey() +
                    ", Angle = " + entry.getValue());
            s += "\n";
        }

        return s;
    }

    public void draw(GraphicsContext gc) {
        gc.setFont(Font.font("Times", 20));
        gc.strokeText("Emma by Jane Austen: A Pie Chart Showcasing Character Probabilities", 20, 25);
        gc.setFont(Font.font("Times", 12));

        int n = 0;
        double startingAngle = 0;
        double rectPosition = 40;
        double height = 0;
        double usedProb = 0;

        for (Map.Entry<Character, Double> entry : sortedAngles.entrySet()) {
            if (n >= charNumber) {
                break;
            }
//            Colors are generated through randomization
            int r = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            gc.setFill(Color.rgb(r, b, g));
//            Outputting arcs that make the pie
            gc.fillArc(80, 90, 500, 500, startingAngle, entry.getValue(), ArcType.ROUND);
//            Outputting small square that make the colors of the legend
            gc.fillRect(650, rectPosition + 1.3 * (height), 20, 20);
//            Outputting the labels for the legend
            gc.strokeText("Character: " + entry.getKey() + "  Probability: " + map.get(entry.getKey()), 675, rectPosition + 1.3 * (height + 10));
            startingAngle += entry.getValue();
            usedProb += map.get(entry.getKey());
            n += 1;
            height = 20 * n;
        }

//        Output for the remainder of the pie...
//        That is, if the user did not enter 26 into the console
        if (usedProb != 1) {
            double remainProb = 1 - usedProb;
            gc.setFill(Color.BLACK);
            gc.fillArc(80, 90, 500, 500, startingAngle, 360 - startingAngle, ArcType.ROUND);
            gc.fillRect(650, rectPosition + 1.3 * (height), 20, 20);
            gc.strokeText("Rest of Characters, " + remainProb, 675, rectPosition + 1.3 * (height + 10));

        }

    }
}

