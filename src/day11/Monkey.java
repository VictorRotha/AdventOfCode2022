package day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {

    int id;
    ArrayList<Long> items;
    Operation operation;

    int targetTrue;
    int targetFalse;

    long inspections;

    int mod;

    public interface Operation {
        long operate(long value);
    }

    public Monkey(int id, List<Long> items, Operation operation, int targetTrue, int targetFalse, int mod) {
        this.id = id;
        this.items = new ArrayList<>(items);
        this.operation = operation;
        this.targetTrue = targetTrue;
        this.targetFalse = targetFalse;
        this.mod = mod;

        inspections = 0;
    }

}
