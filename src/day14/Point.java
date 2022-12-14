package day14;

public class Point {

    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Point other)) return false;
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {

        return x + 100000 * y;
    }

    @Override
    public String toString() {
        return String.format("Point (x=%s, y=%s)", this.x, this.y);
    }
}
