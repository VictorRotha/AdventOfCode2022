package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day05 {

    public static void main(String[] args)  {

        String filename = "src/day05/input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))){

            HashMap<Integer, ArrayList<String>> stacks = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("move")) {

                    String[] s = line.split("\\D+");
                    int[] rules = Arrays.stream(Arrays.copyOfRange(s, 1, s.length)).mapToInt(Integer::parseInt).toArray();

                    moveCrates(stacks, rules[0], rules[1], rules[2]);


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

            String result = "";
            for (int i = 1; i < stacks.size() + 1; i++) {
                result += stacks.get(i).get(stacks.get(i).size()-1);
            }

            System.out.println("Part 01 result is " + result);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void moveCrates(HashMap<Integer, ArrayList<String>> stacks, int times, int from, int to) {

        for (int i = 0; i < times; i++) {
            ArrayList<String> stack = stacks.get(from);
            String crate = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            stacks.get(to).add(crate);
        }
    }
}
