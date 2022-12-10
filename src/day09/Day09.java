package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day09 {


    public static void  main(String[] args) {

        String filename = "src/day09/input.txt";
        List<String[]> input = getInput(filename);

        System.out.println("rope length  2: " + move(input, 2));
        System.out.println("rope length 10: " + move(input, 10));
    }


    private static int  move(List<String[]> input, int ropeLength) {

        int[] ropeX = new int[ropeLength];
        int[] ropeY = new int[ropeLength];

        HashSet<String> visited = new HashSet<>();
        visited.add(stringify(0, 0));

        for(String[] command : input) {
            String direction = command[0];
            int steps = Integer.parseInt(command[1]);

            for (int i = 0; i < steps; i++) {

                switch (direction) {
                    case "R" -> ropeX[0]++;
                    case "L" -> ropeX[0]--;
                    case "D" -> ropeY[0]++;
                    case "U" -> ropeY[0]--;
                }

                for (int j = 1; j < ropeX.length; j++) {
                    followPrevious(j, ropeX, ropeY);
                }

                visited.add(stringify(ropeX[ropeLength - 1], ropeY[ropeLength - 1]));
            }

        }

        return visited.size();

    }

    private static List<String[]> getInput(String filename) {

        ArrayList<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.split(" "));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }


    private static void followPrevious(int idx, int[] ropeX, int[] ropeY) {

        int x = ropeX[idx];
        int y = ropeY[idx];

        int px = ropeX[idx - 1];
        int py = ropeY[idx - 1];

        if (px == x && py == y)
            return;

        if (px - x > 1 || (px - x > 0 && Math.abs(py - y) > 1))
            x++;
        else if (x - px > 1 || (x - px > 0 && Math.abs(py - y) > 1) )
            x--;

        if (py - y > 1 || (py - y > 0 && Math.abs(px - ropeX[idx]) > 1))
            y++;
        else if (y - py > 1 || (y - py > 0 && Math.abs(px - ropeX[idx]) > 1))
            y--;

        ropeX[idx] = x;
        ropeY[idx] = y;

    }


    private static String stringify(int x, int y) {

        return x + " " + y;

    }


}
