package IO;

import java.io.*;
import java.util.*;

public class FileMerger {
    public static void main(String[] args) {
        List<Integer> list1 = readFile("input1.txt");
        List<Integer> list2 = readFile("input2.txt");

        writeMergedFile(list1, list2, "merged.txt");
        writeCommonFile(list1, list2, "common.txt");
    }

    private static List<Integer> readFile(String fileName) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0; // To track the line number for better debugging

            while ((line = reader.readLine()) != null) {
                lineNumber++; // Increment line number on each read
                String trimmedLine = line.trim(); // Trim the line to remove leading/trailing whitespace
                try {
                    if (trimmedLine.isEmpty()) {
                        System.err.println("Skipping empty or whitespace-only line at line " + lineNumber + " in file: " + fileName);
                    } else {
                        int number = Integer.parseInt(trimmedLine);
                        numbers.add(number);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number format for line: '" + line + "' at line " + lineNumber + " in file: " + fileName);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + fileName);
        }
        return numbers;
    }



    private static void writeMergedFile(List<Integer> list1, List<Integer> list2, String outputFileName) {
        List<Integer> mergedList = new ArrayList<>(list1);
        mergedList.addAll(list2);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Integer number : mergedList) {
                writer.write(number + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFileName);
        }
    }

    private static void writeCommonFile(List<Integer> list1, List<Integer> list2, String outputFileName) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> common = new HashSet<>();
        for (Integer number : list2) {
            if (set1.contains(number)) {
                common.add(number);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Integer number : common) {
                writer.write(number + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFileName);
        }
    }
}
