package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day05 {


    public static void main(String[] args)  {

        String filename = "src/day05/input.txt";

        ArrayList<int[]> rules = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> stacks = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))){

            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("move")) {

                    String[] s = line.split("\\D+");
                    int[] rule = Arrays.stream(Arrays.copyOfRange(s, 1, s.length)).mapToInt(Integer::parseInt).toArray();
                    rules.add(rule);

                } else {

                    for (int i = 0; i < line.length(); i += 4) {

                        if (String.valueOf(line.charAt(i)).equals("[")) {
                            int pos = i / 4 + 1;
                            if (!stacks.containsKey(pos))
                                stacks.put(pos, new ArrayList<>());
                            stacks.get(pos).add(0, String.valueOf(line.charAt(i + 1)));
                        }

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Part 01: " + partOne(rules, deepCopyStacks(stacks)));
        System.out.println("Part 02: " + partTwo(rules, stacks));

    }

    public static String partOne(ArrayList<int[]> rules, HashMap<Integer, ArrayList<String>> stacks) {

        for (int[] rule: rules)
            moveCratesPartOne(stacks, rule[0], rule[1], rule[2]);

        return getTopCrates(stacks);
    }

    public static String partTwo(ArrayList<int[]> rules, HashMap<Integer, ArrayList<String>> stacks) {

        for (int[] rule: rules)
            moveCratesPartTwo(stacks, rule[0], rule[1], rule[2]);

        return getTopCrates(stacks);
    }

    public static String getTopCrates(HashMap<Integer, ArrayList<String>> stacks) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < stacks.size() + 1; i++) {
            sb.append(stacks.get(i).get(stacks.get(i).size()-1));
        }

        return sb.toString();
    }



    public static void moveCratesPartOne(HashMap<Integer, ArrayList<String>> stacks, int times, int from, int to) {

        for (int i = 0; i < times; i++) {
            ArrayList<String> stack = stacks.get(from);
            String crate = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            stacks.get(to).add(crate);
        }
    }

    public static void moveCratesPartTwo(HashMap<Integer, ArrayList<String>> stacks, int times, int from, int to) {

        ArrayList<String> stack = stacks.get(from);
        List<String> moveStack = stack.subList(stack.size() - times, stack.size());

        stacks.get(to).addAll(moveStack);

        for (int i = 0; i < times; i++) {
            stack.remove(stack.size() - 1);
        }
    }

    public static HashMap<Integer, ArrayList<String>> deepCopyStacks(HashMap<Integer, ArrayList<String>> stacks) {

        HashMap<Integer, ArrayList<String>> result = new HashMap<>();
        for (Integer i : stacks.keySet()) {

            ArrayList<String> stack = stacks.get(i);
            ArrayList<String> copyOfStack = new ArrayList<>(stack);

            result.put(i, copyOfStack);

        }

        return result;
    }
}
