package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day01 {


    public static void main(String[] args) {

        int calories = 0;
        ArrayList<Integer> elves = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/day01/input.txt"));
            String line ;

            do {
                line = br.readLine();
                if (line == null || line.isEmpty()) {
                    elves.add(calories);
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }

            } while (line != null);


        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(elves);
        int s = elves.size();
        int topSum = elves.get(s-1) + elves.get(s-2) + elves.get(s-3);
        int maxCalories = elves.get(s-1);

        System.out.println("MaxCalories:    " + maxCalories);
        System.out.println("Top 3 Calories: " + topSum);

    }


}
