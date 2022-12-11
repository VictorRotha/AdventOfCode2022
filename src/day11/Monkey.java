package day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {

    int id;
    ArrayList<Integer> items;
    Operation operation;
    Test test;

    int targetTrue;
    int targetFalse;

    int inspections;


    public interface Operation {
        int operate(int value);
    }

    public interface Test {
        boolean test(int value);
    }

    public Monkey(int id, List<Integer> items, Operation operation, Test test, int targetTrue, int targetFalse) {
        this.id = id;
        this.items = new ArrayList<>(items);
        this.operation = operation;
        this.test = test;
        this.targetTrue = targetTrue;
        this.targetFalse = targetFalse;

        inspections = 0;
    }

    @Override
    public String toString() {
        return String.format("Monkey (%s, %s, %s, %s, %s, %s", id, items, operation, test, targetTrue, targetFalse);
    }
}
