package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {

        String filename = "src/day10/input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            int cycle = 1;
            int x = 1;
            int sum = 0;

            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("noop")) {
                    if ((cycle - 20) % 40 == 0) {
                        sum += cycle * x;
                    }

                    cycle++;
                }


                if (line.startsWith("addx")) {
                    int v = Integer.parseInt(line.split(" ")[1]);

                    if ((cycle + 1 - 20) % 40 == 0) {
                        sum += (cycle + 1) * x;
                    }

                    if ((cycle - 20) % 40 == 0) {
                        sum += cycle * x;
                    }

                    cycle += 2;
                    x += v;

                }


            }

            System.out.println(sum);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

