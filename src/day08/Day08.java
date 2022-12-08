package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Day08 {

    public static void main(String[] args) {

        String filename = "src/day08/input.txt";

        ArrayList<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                int[] row = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    row[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] grid = rows.toArray(new int[0][]);

        int visible = 0;

        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                if (!isBlocked(grid, j, i))
                    visible ++;
            }
        }

        System.out.println("visible trees: " + visible);


    }

    public static boolean isBlocked(int[][] grid, int row, int col) {
        return  isBlockedLeft(grid, row, col) &&
                isBlockedRight(grid, row, col) &&
                isBlockedFromTop(grid, row, col) &&
                isBlockedFromBottom(grid, row, col);
    }

    public static boolean isBlockedLeft(int[][] grid, int row, int col) {

        for (int i = 0; i < col; i++) {
            if (grid[row][i] >= grid[row][col]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockedRight(int[][] grid, int row, int col) {

        for (int i = col + 1; i < grid[row].length; i++) {
            if (grid[row][i] >= grid[row][col]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockedFromTop(int[][] grid, int row, int col) {

        for (int i = 0; i < row; i++) {
            if (grid[i][col] >= grid[row][col]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlockedFromBottom(int[][] grid, int row, int col) {

        for (int i = row + 1; i < grid.length; i++) {
            if (grid[i][col] >= grid[row][col]) {
                return true;
            }
        }
        return false;
    }
}
