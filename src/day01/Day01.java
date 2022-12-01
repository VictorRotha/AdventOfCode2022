package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day01 {


    public static void main(String[] args) {


        int calories = 0;
        int maxCalories = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/day01/input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {

                    maxCalories = Math.max(maxCalories, calories);
                    calories = 0;

                } else {
                    calories += Integer.parseInt(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        maxCalories = Math.max(maxCalories, calories);

        System.out.println("MaxCalories: " + maxCalories);

    }



}
