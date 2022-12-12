package day12;

public class Node {

    int level;
    int row;
    int col;
    int distance;
    int idx;

    public Node(int level, int idx) {
        this.level = level;
        this.idx = idx;


        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return String.format("Node(idx=%s, level=%s, distance=%s)", idx, level, distance);
    }
}
