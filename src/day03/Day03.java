package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day03 {


    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/day03/input.txt"))) {

            String line;
            int priority = 0;

            while ((line = br.readLine()) != null) {

                String compartment1 = line.substring(0, line.length()/2);
                String compartment2 = line.substring(line.length()/2);

                for (int i = 0; i < compartment1.length(); i++) {
                    if (compartment2.indexOf(compartment1.charAt(i)) != -1) {
                        priority += getPriority(compartment1.charAt(i));
                        break;
                    }
                }
            }

            System.out.println("priority sum is " + priority);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getPriority(char c) {

        return (c < 97) ? c - 38 : c - 96;

    }

}
