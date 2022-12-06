package day06;

import java.util.HashSet;

public class Day06 {

    public static void main(String[] args) {

        String input ="mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        System.out.println("Part 01: first start-of-packet marker after character  " + findMarker(4, input));
        System.out.println("Part 02: first start-of-message marker after character " + findMarker(14, input));

    }

    public static int findMarker(int n, String input) {

        int result = -1;
        HashSet<Character> chars = new HashSet<>();
        for (int i = n-1; i < input.length(); i++) {
            chars.clear();
            for (int j = 0; j < n; j++)
                chars.add(input.charAt(i-j));
            if (chars.size() == n) {
                result = i + 1;
                break;
            }
        }

        return result;

    }


}
