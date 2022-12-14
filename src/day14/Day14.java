package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day14 {

    public static int lowest_row;

    public static void main(String[] args) {

        String filename = "src/day14/input.txt";

        HashSet<Point> points = getInput(filename);

        int resultPartOne = part01(points);
        System.out.println("part 01 result: " + resultPartOne);

        points = getInput(filename);

        int resultPartTwo = part02(points);
        System.out.println("part 02 result: " + resultPartTwo);

    }

    public static int part02(HashSet<Point> points) {

        int rest = 0;
        boolean stop = false;
        while (!stop) {

            Point startPoint = new Point(500, 0);
            Point sand = startPoint;
            boolean falling = true;
            while (falling) {

                Point pDown = new Point(sand.x, sand.y + 1);
                Point pLeft = new Point(sand.x - 1, sand.y + 1);
                Point pRight = new Point(sand.x + 1, sand.y + 1);

                if (sand.y == lowest_row + 1) {
                    falling = false;
                } else  if (!points.contains(pDown)) {
                    sand = pDown;
                } else if (!points.contains(pLeft)) {
                    sand = pLeft;
                } else if (!points.contains(pRight)) {
                    sand = pRight;
                } else {
                    falling = false;

                }

                if (!falling) {
                    points.add(sand);
                    rest++;
                    if (sand.equals(startPoint)) {
                        stop = true;
                    }
                }

            }
        }

        return rest;

    }

    public static int part01(HashSet<Point> points) {

        int rest = 0;
        boolean stop = false;
        while (!stop) {

            Point sand = new Point(500, 0);
            while (true) {

                Point pDown = new Point(sand.x, sand.y + 1);
                Point pLeft = new Point(sand.x - 1, sand.y + 1);
                Point pRight = new Point(sand.x + 1, sand.y + 1);

                if (!points.contains(pDown)) {
                    sand = pDown;
                } else if (!points.contains(pLeft)) {
                    sand = pLeft;
                } else if (!points.contains(pRight)) {
                    sand = pRight;
                } else {
                    points.add(sand);
                    rest++;
                    break;
                }

                if (sand.y == lowest_row) {
                    stop = true;
                    break;
                }

            }
        }

        return rest;

    }

    public static HashSet<Point> getInput(String filename) {

        lowest_row = 0;
        HashSet<Point> points = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = br.readLine()) != null) {

                ArrayList<Point> path = new ArrayList<>();

                for (String s: line.split(" -> ")) {

                    int x = Integer.parseInt(s.split(",")[0]);
                    int y = Integer.parseInt(s.split(",")[1]);
                    path.add(new Point(x, y));
                    lowest_row = Math.max(lowest_row, y);

                }

                for (int i = 0; i < path.size() - 1; i++) {

                    Point p1 = path.get(i);
                    Point p2 = path.get(i + 1);

                    for (int x = Math.min(p1.x, p2.x); x < Math.max(p1.x, p2.x) + 1; x++) {
                        for (int y = Math.min(p1.y, p2.y); y < Math.max(p1.y, p2.y) + 1; y++) {
                            points.add(new Point(x, y));
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }


}

