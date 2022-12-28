package day15;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {

        String filename = "src/day15/input.txt";
        List<Sensor> sensors = getInput(filename);

        part01(sensors);
        part02(sensors);

    }


    public static void part01(List<Sensor> sensors) {

        int targetLine = 2000000;

        HashSet<Integer> targetX = new HashSet<>();
        HashSet<Integer> beaconsInTarget = new HashSet<>();

        for (Sensor s : sensors) {

            if (s.by == targetLine)
                beaconsInTarget.add(s.bx);
            if (s.y == targetLine)
                beaconsInTarget.add(s.x);

            int distToTarget = Math.abs(targetLine - s.y);
            for (int i = 0; i <= s.distance - distToTarget; i++) {

                targetX.add(s.x + i);
                targetX.add(s.x - i);

            }


        }

        System.out.println("Part 01 result: " + (targetX.size() - beaconsInTarget.size()));

    }


    public static void part02(List<Sensor> sensors) {

        int range = 4000000;

        HashSet<Point> points = new HashSet<>();

        for (Sensor sensor : sensors) {
            points.addAll(getPointsOutside(sensor, range));
        }

        for (Point p : points) {

            boolean isInRange = false;
            for (Sensor sensor : sensors) {
                int manhattan = manhattanDistance(p.x, p.y, sensor.x, sensor.y);
                if (manhattan <= sensor.distance) {
                    isInRange = true;
                    break;
                }
            }

            if (!isInRange) {
                System.out.println("Part 02 result: " + (p.x * 4000000L + p.y));
                break;
            }

        }

    }

    public static HashSet<Point> getPointsOutside(Sensor sensor, int range) {

        HashSet<Point> result = new HashSet<>();

        int x1, x2, y1, y2;
        int distance = sensor.distance + 1;

        for (int i = 0; i < distance + 1; i++) {

            y1 = sensor.y + i;
            x1 = sensor.x + (distance - i);

            y2 = sensor.y - i;
            x2 = sensor.x - (distance - i);

            Point p;

            p = new Point(x1, y1);
            if (isInArea(p, range)) result.add(p);
            p = new Point(x1, y2);
            if (isInArea(p, range)) result.add(p);
            p = new Point(x2, y1);
            if (isInArea(p, range)) result.add(p);
            p = new Point(x2, y2);
            if (isInArea(p, range)) result.add(p);
       }

        return result;
    }

    public static boolean isInArea(Point p, int range) {

        return (p.x >= 0 && p.x <= range && p.y >= 0 && p.y <= range);

    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2) {

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);

    }

    public static List<Sensor> getInput(String filename) {

        ArrayList<Sensor> sensors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {

                int sx = Integer.parseInt(line.split(" ")[2].split("=")[1].split(",")[0]);
                int sy = Integer.parseInt(line.split(" ")[3].split("=")[1].split(":")[0]);
                int bx = Integer.parseInt(line.split(" ")[8].split("=")[1].split(",")[0]);
                int by = Integer.parseInt(line.split(" ")[9].split("=")[1]);

                int distance = manhattanDistance(sx, sy, bx, by);

                sensors.add(new Sensor(sx, sy, bx, by, distance));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sensors;

    }


}

