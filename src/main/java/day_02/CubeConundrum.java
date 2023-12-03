package day_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {

    public List<String> getInputText() throws IOException {
        return Files.readAllLines(Paths.get("./src/test/resources/day_02_part_01.txt"));
    }

    public int partOne(List<String> inputText) {

        int total = 0;
        for (String line : inputText) {

            String gameNumber = parseGameNumber(line);

            int biggestBlue = parseBiggestOfColour(line, "blue");
            if (biggestBlue > 14) continue;

            int biggestRed = parseBiggestOfColour(line, "red");
            if (biggestRed > 12) continue;

            int biggestGreen = parseBiggestOfColour(line, "green");
            if (biggestGreen > 13) continue;

            total += Integer.parseInt(gameNumber);
        }
        return total;
    }

    public int partTwo(List<String> inputText) {

        int total = 0;
        for (String line : inputText) {

            int biggestBlue = parseBiggestOfColour(line, "blue");
            int biggestRed = parseBiggestOfColour(line, "red");
            int biggestGreen = parseBiggestOfColour(line, "green");

            total += (biggestBlue * biggestRed * biggestGreen);
        }
        return total;
    }

    public String parseGameNumber(String input) {
        Pattern gameNumberPattern = Pattern.compile("Game (\\d+):");
        Matcher gameNumberMatch = gameNumberPattern.matcher(input);
        String gameNumber = "";
        while (gameNumberMatch.find()) {
            gameNumber = gameNumberMatch.group(1);
        }
        return gameNumber;
    }

    public int parseBiggestOfColour(String input, String colour) {
        Pattern colourNumberPattern = Pattern.compile("(\\d+) " + colour);
        Matcher colourNumberMatch = colourNumberPattern.matcher(input);
        int biggestColour = 0;
        while (colourNumberMatch.find()) {
            int colourIndex = 1;
            int currentColour = Integer.parseInt(colourNumberMatch.group(colourIndex));
            if (currentColour > biggestColour) biggestColour = currentColour;
        }
        return biggestColour;
    }

}
