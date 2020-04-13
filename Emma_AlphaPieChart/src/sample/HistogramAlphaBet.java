package sample;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class HistogramAlphaBet {

    Map<Character, Double> map = new HashMap<>();
    double totalChar = 0;

    public void add(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            c = Character.toLowerCase(c);
            if (Character.isAlphabetic(c)) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, (double) 1);
                }
            }
        }
    }

    public Map<Character, Double> getMap() {
        return map;
    }

    public double getTotalChar() {
        for (double i : map.values()) {
            totalChar += i;
        }
        return totalChar;
    }

    /*TEST BELOW:: Checking that characters and frequencies have been properly added to the map.*/

//    public String toString(){
//        String s = "";
//        for (Map.Entry<Character, Double> entry : map.entrySet()) {
//            s += ("Character = " + entry.getKey() +
//                    ", Frequency = " + entry.getValue());
//            s += "\n";
//        }
//
//        s += ("The total number of characters are: " + getTotalChar());
//        return s;
//    }

    Map<Character, Double> probabilities = getMap();

    public Map<Character, Double> calcProbabilities() {
        double p;
        for (Map.Entry<Character, Double> entry : probabilities.entrySet()) {
            p = entry.getValue() / 684358;
            probabilities.put(entry.getKey(), p);
        }
        return probabilities;
    }

    double totalProb = 0;

    public double getTotalProb() {
        for (double i : probabilities.values()) {
            totalProb += i;
        }
        return totalProb;
    }

    /*TEST BELOW:: Checking that characters and probabilities have been properly added to the map.*/


//    public String toString() {
//        String s = "";
//        for (Map.Entry<Character, Double> entry : calcProbabilities().entrySet()) {
//            s += ("Character = " + entry.getKey() +
//                    ", Probability = " + entry.getValue());
//            s += "\n";
//        }
//        s += ("Total Probabilities: " + getTotalProb());
//        return s;
//    }

    public Map<Character, Double> getSortedProbabilities() {
        Map<Character, Double> sortedProbabilities = calcProbabilities()
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return sortedProbabilities;
    }

    /*TEST BELOW:: Checking that characters and frequencies have been properly sorted to the map.*/

//        public String toString() {
//        String s = "";
//        for (Map.Entry<Character, Double> entry : getSortedProbabilities().entrySet()) {
//            s += ("Character = " + entry.getKey() +
//                    ", Probability = " + entry.getValue());
//            s += "\n";
//        }
//
//        return s;
//    }

}
