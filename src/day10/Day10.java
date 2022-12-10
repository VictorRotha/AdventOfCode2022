package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day10 {

    public static void main(String[] args) {

        String filename = "src/day10/input.txt";
        String[] screen = new String[6 * 40];

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

                    int pos = cycle - 1;
                    screen[pos] = getPixel(pos, x);

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

                    int pos = cycle - 1;
                    screen[pos] = getPixel(pos, x);

                    int nextPos = cycle;
                    screen[nextPos] = getPixel(nextPos, x);

                    cycle += 2;
                    x += v;

                }

            }

            System.out.println("Part01: " + sum);
            System.out.println("Part02:");
            for (int i = 0; i < screen.length; i++) {
                if ((i % 40) == 0)
                    System.out.println();
                System.out.print(screen[i] + " ");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getPixel(int pos, int x) {

        int rowPos = pos % 40;
        return (rowPos >= x - 1 && rowPos <= x + 1) ? "#" : ".";

    }

}

