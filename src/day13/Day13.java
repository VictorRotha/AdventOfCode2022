package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day13 {

    public static void main(String[] args) {

        String filename = "src/day13/testinput.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            ArrayList<String> pair = new ArrayList<>();
            ArrayList<String> packets = new ArrayList<>();

            int sum = 0;
            int n = 1;
            String line;
            while ((line = br.readLine()) != null) {

                if (line.isEmpty())
                    continue;

                pair.add(line.trim());
                packets.add(line.trim());

                if (pair.size() == 2 ) {

                    int result = compareLists(pair.get(0), pair.get(1));

                    if (result == -1)
                        sum += n;

                    pair.clear();
                    n++;

                }

            }

            System.out.println("Part 01 result: " + sum);

            packets.add("[[2]]");
            packets.add("[[6]]");
            packets.sort(Day13::compareLists);

            int prod = 1;
            for ( int i = 0; i < packets.size(); i++) {
                if (Objects.equals(packets.get(i), "[[2]]") || Objects.equals(packets.get(i), "[[6]]"))
                    prod *= (i+1);
            }

            System.out.println("Part 02 result: " + prod);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int compareLists(String s1, String s2) {

        List<String> list1 = listify(s1);
        List<String> list2 = listify(s2);

        for (int i = 0; i < list1.size(); i++) {

            if (i > list2.size() - 1)
                return 1;

            String ele1 = list1.get(i);
            String ele2 = list2.get(i);

            if (isNumber(ele1) && isNumber(ele2)) {

                int result = compareNumbers(ele1, ele2);
                if (result != 0)
                    return result;

            } else {

                if (isNumber(ele1))
                    ele1 = "[" + ele1  + "]";
                if (isNumber(ele2))
                    ele2 = "[" + ele2  + "]";

                int result = compareLists(ele1, ele2);
                if (result != 0)
                    return result;

            }

        }

        if (list2.size() > list1.size())
            return -1;

        return 0;
    }

    public static int compareNumbers(String s1, String s2) {

        int i1 = Integer.parseInt(s1);
        int i2 = Integer.parseInt(s2);

        return Integer.compare(i1, i2);

    }


    public static List<String> listify(String s) {

        ArrayList<String> result = new ArrayList<>();
        int openBrackets = 0;
        int startIdx = 1;

        for (int i = 0; i < s.length(); i++) {

            boolean isOpenBracket = "[".equals(String.valueOf(s.charAt(i)));
            boolean isClosedBracket = "]".equals(String.valueOf(s.charAt(i)));
            boolean isDivider = ",".equals(String.valueOf(s.charAt(i)));

            if (isOpenBracket) {
                openBrackets++;
            } else if (isClosedBracket) {
                openBrackets--;
            }

            if (openBrackets == 1 && isDivider || openBrackets == 0 && isClosedBracket) {
                result.add(s.substring(startIdx, i));
                startIdx = i + 1;
            }
        }

        return result;
    }

    public static boolean isNumber(String s) {

        return !s.startsWith("[") && !s.isEmpty();

    }

}

