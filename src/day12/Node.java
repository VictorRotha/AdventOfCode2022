package day12;

public class Node {

    int idx;
    int level;
    int distance;


    public Node(int level, int idx) {
        this.level = level;
        this.idx = idx;

        this.distance = Integer.MAX_VALUE;
    }

}
