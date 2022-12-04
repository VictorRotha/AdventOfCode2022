package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day03 {


    public static void main(String[] args) {

        part01();
        part02();

    }

    public static void part02() {

        try (BufferedReader br = new BufferedReader(new FileReader("src/day03/input.txt"))) {

            String line;
            int priority = 0;
            ArrayList<String> group = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                group.add(line);
                if (group.size() == 3) {
                    String common1 = findCommonChars(group.get(0), group.get(1));
                    String common2 = findCommonChars(common1, group.get(2));
                    priority += getPriority(common2.charAt(0));
                    group.clear();
                }

            }

            System.out.println("Part 2: priority sum is " + priority);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String findCommonChars(String s1, String s2) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (s2.indexOf(s1.charAt(i)) > -1) {
                sb.append(s1.charAt(i));
            }
        }
        return sb.toString();

    }


    public static void part01() {

        try (BufferedReader br = new BufferedReader(new FileReader("src/day03/input.txt"))) {

            String line;
            int priority = 0;

            while ((line = br.readLine()) != null) {

                String compartment1 = line.substring(0, line.length()/2);
                String compartment2 = line.substring(line.length()/2);

                for (int i = 0; i < compartment1.length(); i++) {
                    if (compartment2.indexOf(compartment1.charAt(i)) != -1) {
                        priority += getPriority(compartment1.charAt(i));
                        break;
                    }
                }
            }

            System.out.println("Part 1: priority sum is " + priority);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getPriority(char c) {

        return (c < 97) ? c - 38 : c - 96;

    }

}
