package day06;

import java.util.HashSet;

public class Day06 {

    public static void main(String[] args) {

        String input ="mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        int result = -1;
        HashSet<Character> lastFour = new HashSet<>();
        for (int i = 3; i < input.length(); i++) {
            lastFour.clear();
            for (int j = 0; j < 4; j++)
                 lastFour.add(input.charAt(i-j));
            if (lastFour.size() == 4) {
                result = i + 1;
                break;
            }
        }

        System.out.println("Part 01: first marker after character " + result);

    }


}
