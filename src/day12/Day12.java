package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day12 {

    public static int GRID_WIDTH;
    public static int GRID_HEIGHT;
    public static int START_IDX;
    public static int END_IDX;
    public static final int PART_ONE = 1;
    public static final int PART_TWO = 2;

    public static void main(String[] args) {

        String filename = "src/day12/input.txt";

        HashMap<Integer, Node> nodes = getInput(filename);

        System.out.println("start idx " + START_IDX +" end idx " + END_IDX);
        System.out.println("grid w " + GRID_WIDTH + " h " + GRID_HEIGHT );
        System.out.println("total nodes " + GRID_WIDTH * GRID_HEIGHT);
        System.out.println();

        System.out.println("Part 01 shortest way: "  + shortestWayFromStart(nodes, PART_ONE));

        nodes = getInput(filename);

        System.out.println("Part 02 shortest way: " + shortestWayFromStart(nodes, PART_TWO));

    }

    public static int shortestWayFromStart(HashMap<Integer, Node> nodes, int part) {

        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();

        queue.add((part == PART_ONE) ? START_IDX : END_IDX);

        nodes.get(queue.get(0)).distance = 0;

        while (queue.size() > 0) {

            Node node = nodes.get(queue.get(0));

            if ((part == PART_ONE && node.idx == END_IDX) || (part == PART_TWO && node.level == 0)) {
                return node.distance;
            }

            queue.remove(0);
            visited.add(node.idx);

            List<Node> nbs = getNeighbours(node, visited, nodes, part);

            int distance = node.distance + 1;
            for (Node nb: nbs) {
                if (distance < nb.distance)
                    nb.distance = distance;
                if (!queue.contains(nb.idx))
                    queue.add(nb.idx);
            }

            queue.sort((o1, o2) -> {
                int d1 = nodes.get(o1).distance;
                int d2 = nodes.get(o2).distance;
                return Integer.compare(d1, d2);

            });

        }

        return -1;

    }


    public static List<Node> getNeighbours(Node node, List<Integer> visited, HashMap<Integer, Node> nodes, int part) {

        ArrayList<Node> result = new ArrayList<>();

        int row = node.idx / GRID_WIDTH;
        int col = node.idx % GRID_WIDTH;

        if (row < GRID_HEIGHT - 1)
            result.add(nodes.get(node.idx + GRID_WIDTH));

        if (row > 0)
            result.add(nodes.get(node.idx - GRID_WIDTH));

        if (col < GRID_WIDTH - 1)
            result.add(nodes.get(node.idx + 1));

        if (col > 0)
            result.add(nodes.get(node.idx - 1));

        result.removeIf(nb -> visited.contains(nb.idx));

        result.removeIf((part == PART_ONE) ? nb -> nb.level - node.level > 1 : nb -> node.level - nb.level > 1);

        return result;

    }

    public static HashMap<Integer, Node> getInput(String filename) {

        HashMap<Integer, Node> nodes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {

                GRID_WIDTH = line.length();

                for (int i = 0; i < line.length(); i++) {
                    int idx = row * line.length() + i;
                    int level;
                    if (String.valueOf(line.charAt(i)).equals("S")) {
                        level = 0;
                        START_IDX = idx;
                    } else if (String.valueOf(line.charAt(i)).equals("E")) {
                        level = 25;
                        END_IDX = idx;
                    } else {
                        level = line.charAt(i) - 97;
                    }
                    nodes.put(idx, new Node(level, idx));
                }
                row++;

            }

            GRID_HEIGHT = row;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nodes;

    }

}

