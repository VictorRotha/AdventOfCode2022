package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day09 {

    private int hx, hy, tx, ty;

    private final int[] ropeX = new int[10];
    private final int[] ropeY = new int[10];

    public Day09() {

        String filename = "src/day09/testinput.txt";

        HashSet<String> visited = new HashSet<>();
        HashSet<String> visited2 = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] command = line.split(" ");
                String direction = command[0];
                int steps = Integer.parseInt(command[1]);

                visited.add(stringify(0, 0));
                visited2.add(stringify(0, 0));

                for (int i = 0; i < steps; i++) {

                    //part 01
                    moveHead(direction);
                    follow(direction);
                    visited.add(stringify(tx, ty));

                    //part 02
                    moveRopeHead(direction);
                    for (int j = 1; j < ropeX.length; j++) {
                        followPrevious(j);
                    }
                    visited2.add(stringify(ropeX[9], ropeY[9]));
                }

            }

            System.out.println("Part 01: visited by tail: " + visited.size());
            System.out.println("Part 02: visited by tail: " + visited2.size());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void moveRopeHead(String direction) {

        switch (direction) {
            case "R" -> ropeX[0]++;
            case "L" -> ropeX[0]--;
            case "D" -> ropeY[0]++;
            case "U" -> ropeY[0]--;
        }

    }

    private void followPrevious(int idx) {

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

    private void moveHead(String direction) {

        switch (direction) {
            case "R" -> hx++;
            case "L" -> hx--;
            case "U" -> hy--;
            case "D" -> hy++;
        }

    }

    private void follow(String direction) {

        if (tx == hx && ty == hy)
            return;

       switch (direction) {

           case("R"):
               if (hx - tx > 1) {
                   tx++;
                   ty += (hy - ty);
               }
               break;
           case("L"):
               if (tx - hx > 1) {
                   tx--;
                   ty += (hy - ty);
               }
               break;
           case("D"):
               if (hy - ty > 1) {
                   ty++;
                   tx += (hx - tx);
               }
               break;
           case("U"):
               if (ty - hy > 1) {
                   ty--;
                   tx += (hx - tx);
               }
               break;

        }

    }


    private String stringify(int x, int y) {

        return x + " " + y;

    }


    public static void  main(String[] args) {

        new Day09();

    }


}
