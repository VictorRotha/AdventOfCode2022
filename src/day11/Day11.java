package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) {

        String filename = "src/day11/input.txt";

        HashMap<Integer, Monkey> monkeys = getInput(filename);

        int mod = 1;
        for (Monkey monkey: monkeys.values())
            mod *= monkey.mod;

        System.out.println("Part 01: " + play(monkeys, 20, 3, mod));

        monkeys = getInput(filename);

        System.out.println("Part 02: " + play(monkeys, 10000, 1, mod));

    }

    private static long play(HashMap<Integer, Monkey> monkeys, int numberOfRounds, int div, int mod) {

        for (int round = 0; round < numberOfRounds; round++) {

            for (int i = 0; i < monkeys.size(); i++) {

                Monkey monkey = monkeys.get(i);

                for (long item : monkey.items) {
                    monkey.inspections++;
                    long v = (monkey.operation.operate(item) / div) % mod;

                    int target = ( v % monkey.mod == 0) ? monkey.targetTrue : monkey.targetFalse;
                    monkeys.get(target).items.add(v);

                }

                monkey.items.clear();

            }

        }

        ArrayList<Long> inspections = new ArrayList<>();
        for (int i = 0; i < monkeys.size(); i++)
            inspections.add(monkeys.get(i).inspections);
        Collections.sort(inspections);
        return inspections.get(inspections.size() - 1) * inspections.get(inspections.size()-2);

    }

    private static HashMap<Integer, Monkey> getInput(String filename) {

        HashMap<Integer, Monkey> monkeys = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            List<Long> items = null;
            int id = -1,  targetTrue = -1, targetFalse, mod = -1;
            Monkey.Operation operation = null;

            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("Monkey")) {
                    id = Integer.parseInt(line.split(" ")[1].split(":")[0]);
                }

                if (line.trim().startsWith("Starting items:")) {
                    String[] itemsAsString = line.split("Starting items:")[1].trim().split(", ");
                    items = Arrays.stream(itemsAsString).map(Long::parseLong).collect(Collectors.toList());

                } else if (line.trim().startsWith("Operation:")) {

                    String op = line.trim().split(" ")[4];
                    String vs = line.trim().split(" ")[5];

                    switch (op) {
                        case "*" -> operation = old -> old * ((vs.equals("old")) ? old : Integer.parseInt(vs));
                        case "+" -> operation = old -> old + ((vs.equals("old")) ? old : Integer.parseInt(vs));
                    }

                } else if (line.trim().startsWith("Test:")) {

                    mod = Integer.parseInt(line.trim().split(" ")[3]);

                } else if (line.trim().startsWith("If true: ")) {

                    targetTrue = Integer.parseInt(line.split("monkey")[1].trim());

                } else if (line.trim().startsWith("If false: ")) {

                    targetFalse = Integer.parseInt(line.split("monkey")[1].trim());
                    monkeys.put(id, new Monkey(id, items, operation, targetTrue, targetFalse, mod));

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return monkeys;

    }

}

