package day_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trebuchet {

    private Map<String, String> lookupTable;

    public List<String> getInputText() throws IOException {
        return Files.readAllLines(Paths.get("./src/test/resources/day_01_part_01.txt"));
    }

    public String parseFirstDigit(String input) {
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                return String.valueOf(c);
            }
        }
        return null;
    }

    public int partOne(List<String> inputText) {

        int total = 0;
        for (String line : inputText) {

            String firstDigit = parseFirstDigit(line);
            String lastDigit = parseFirstDigit(new StringBuilder(line).reverse().toString());

            total += Integer.parseInt(firstDigit+lastDigit);
        }

        return total;

    }

    public int partTwo() throws IOException {

        List<String> inputText = getInputText();
        List<String> outputText = new ArrayList<>();
        Map<String, String> lookup = getLookupTable();

        for (String line : inputText) {
            for (Map.Entry<String, String> entry : lookup.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                line = line.replace(key, value);
            }
            outputText.add(line);
        }
        return partOne(outputText);
    }

    public Map<String, String> getLookupTable() {
        if (lookupTable == null) {
            lookupTable = new HashMap<>();
            lookupTable.put("zero", "zero0zero");
            lookupTable.put("one", "one1one");
            lookupTable.put("two", "two2two");
            lookupTable.put("three", "three3three");
            lookupTable.put("four", "four4four");
            lookupTable.put("five", "five5five");
            lookupTable.put("six", "six6six");
            lookupTable.put("seven", "seven7seven");
            lookupTable.put("eight", "eight8eight");
            lookupTable.put("nine", "nine9nine");
        }
        return  lookupTable;
    }

}
