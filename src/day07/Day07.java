package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day07 {


    public static void main(String[] args) {

        String filename = "src/day07/testinput.txt";

        ArrayList<String> currentDir = new ArrayList<>();
        HashMap<String, Integer> dirSize = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("$ cd")) {

                    String dest = line.split(" ")[2];
                    if (Objects.equals(dest, "/")) {
                        currentDir.clear();
                        currentDir.add("root");
                    } else if (Objects.equals(dest, "..")) {
                        currentDir.remove(currentDir.size() - 1);
                    } else {
                        currentDir.add(dest);
                    }

                } else if (!line.startsWith("$") && !line.startsWith("dir") ) {

                    int fileSize = Integer.parseInt(line.split(" ")[0].trim());
                    for (int i = 0; i < currentDir.size(); i++) {
                        String key = generateDirKey(currentDir, i);
                        dirSize.put(key, (dirSize.containsKey(key)) ? dirSize.get(key) + fileSize : fileSize);

                    }

                }
            }

            int fileSysSize = 70000000;
            int minFree = 30000000;
            int maxUsed = fileSysSize - minFree;

            int currentUsedSize = dirSize.get("root/");
            int minToDelete = currentUsedSize - maxUsed;

            int deleteCandidate = currentUsedSize;

            int resultPartOne = 0;
            for (String s : dirSize.keySet()) {
                int size = dirSize.get(s);
                if (size < 100000)
                    resultPartOne += size;
                if (size >= minToDelete) {
                    deleteCandidate = Math.min(deleteCandidate, size);
                }
            }

            System.out.println("Part 01 result: " + resultPartOne);
            System.out.println("Part 02 result: " + deleteCandidate);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String generateDirKey(ArrayList<String> dir, int idx) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx + 1; i++) {
            sb.append(dir.get(i)).append("/");
        }

        return sb.toString();
    }
}
