package day02;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day02 {

    public static void main(String[] args) {

        //A  X  Rock
        //B  Y  Paper
        //C  Z  Scissors

        HashMap<String, Integer> shapeScore = new HashMap<>();
        shapeScore.put("X", 1);
        shapeScore.put("Y", 2);
        shapeScore.put("Z", 3);

        List<String> winners = Arrays.asList("A Y", "B Z", "C X");
        List<String> draws = Arrays.asList("A X", "B Y", "C Z");
        int totalScore = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/day02/input.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                int score = 0;

                String shape = line.substring(2);
                score += shapeScore.get(shape);

                if (winners.contains(line.strip())) {
                    score += 6;
                } else if (draws.contains(line.strip())) {
                    score += 3;
                }

                totalScore += score;

            }

            System.out.println("TotalScore: " + totalScore);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
