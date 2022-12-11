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

        int numberOfRounds = 20;
        for (int round = 0; round < numberOfRounds; round++) {

            for (int i = 0; i < monkeys.size(); i++) {

                Monkey monkey = monkeys.get(i);

                for (int item : monkey.items) {
                    monkey.inspections++;
                    int v = monkey.operation.operate(item) / 3;
                    int target = (monkey.test.test(v)) ? monkey.targetTrue : monkey.targetFalse;

                    monkeys.get(target).items.add(v);

                }

                monkey.items.clear();

            }

        }


        ArrayList<Integer> inspections = new ArrayList<>();
        for (int i = 0; i < monkeys.size(); i++)
            inspections.add(monkeys.get(i).inspections);
        Collections.sort(inspections);
        int result = inspections.get(inspections.size()-1) * inspections.get(inspections.size()-2);
        System.out.printf("Result after %s rounds: %s\n", numberOfRounds, result);


    }

    private static HashMap<Integer, Monkey> getInput(String filename) {

        HashMap<Integer, Monkey> monkeys = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            List<Integer> items = null;
            int id = -1,  targetTrue = -1, targetFalse;
            Monkey.Operation operation = null;
            Monkey.Test test = null;

            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("Monkey")) {
                    id = Integer.parseInt(line.split(" ")[1].split(":")[0]);
                }

                if (line.trim().startsWith("Starting items:")) {
                    String[] itemsAsString = line.split("Starting items:")[1].trim().split(", ");
                    items = Arrays.stream(itemsAsString).map(Integer::parseInt).collect(Collectors.toList());

                } else if (line.trim().startsWith("Operation:")) {

                    String op = line.trim().split(" ")[4];
                    String vs = line.trim().split(" ")[5];

                    switch (op) {
                        case "*" -> operation = old -> old * ((vs.equals("old")) ? old : Integer.parseInt(vs));
                        case "+" -> operation = old -> old + ((vs.equals("old")) ? old : Integer.parseInt(vs));
                    }


                } else if (line.trim().startsWith("Test:")) {
                    int div = Integer.parseInt(line.trim().split(" ")[3]);
                    test = n -> (n % div) == 0;


                } else if (line.trim().startsWith("If true: ")) {

                    targetTrue = Integer.parseInt(line.split("monkey")[1].trim());

                } else if (line.trim().startsWith("If false: ")) {

                    targetFalse = Integer.parseInt(line.split("monkey")[1].trim());

                    monkeys.put(id, new Monkey(id, items, operation, test, targetTrue, targetFalse));

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return monkeys;

    }

}

