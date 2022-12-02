package day02;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day02 {

    public static void main(String[] args) {

        part01();
        part02();

    }

    public static void part02() {

        //A Rock
        //B Paper
        //C Scissors

        //X loose
        //Y draw
        //Z win

        HashMap<String, Integer> resultScore = new HashMap<>();
        resultScore.put("X", 0);
        resultScore.put("Y", 3);
        resultScore.put("Z", 6);

        HashMap<String, Integer> shapes = new HashMap<>();
        shapes.put("A", 0);
        shapes.put("B", 1);
        shapes.put("C", 2);
        shapes.put("X", 0);
        shapes.put("Y", 1);
        shapes.put("Z", 2);

        int totalScore = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/day02/input.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {

                int score = 0;

                String result = line.substring(2);
                score += resultScore.get(result);

                int s = shapes.get(line.substring(0, 1));
                int s2;

                if (result.equals("X")) {
                    s2 = ((s - 1) + 3) % 3;
                } else if (result.equals("Y")) {
                    s2 = s;
                } else {
                    s2 = (s + 1) % 3;
                }

                score += s2 + 1;

                totalScore += score;

            }

            System.out.println("Part 02 total score: " + totalScore);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void part01() {

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

            System.out.println("Part 01 total score: " + totalScore);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
