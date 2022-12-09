package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day09 {

    private int hx, hy, tx, ty;
    private final HashSet<String> visited;

    public Day09() {

        String filename = "src/day09/testinput.txt";

        visited = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] command = line.split(" ");
                String direction = command[0];
                int steps = Integer.parseInt(command[1]);

                visited.add(stringify(0, 0));

                for (int i = 0; i < steps; i++) {

                    moveHead(direction);
                    follow(direction);
                    visited.add(stringify(tx, ty));

                }

            }

            System.out.println("visited by tail: " + visited.size());


        } catch (IOException e) {
            e.printStackTrace();
        }


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
