package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day04 {


    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/day04/input.txt")))  {

            int sum = 0;
            String line;
            while ((line = br.readLine()) != null) {

                int[] i = Arrays.stream(line.split("[-,]")).mapToInt(Integer::parseInt).toArray();

                if (isIncluded(i))
                    sum++;

            }

            System.out.println("Part 01 result: " + sum);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static boolean isIncluded(int[] i) {

        return (i[0] >= i[2] && i[1] <= i[3]) || (i[2] >= i[0] && i[3] <= i[1]);
    }


}
