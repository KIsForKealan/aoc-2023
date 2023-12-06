package day_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScratchCards {

    public List<String> getInputText() throws IOException {
        return Files.readAllLines(Paths.get("./src/test/resources/day_04_part_01.txt"));
    }

    public int partOne(List<String> input) {

        int totalScore = 0;
        for (String line : input) {
            String[] splitCard = line.split(":");
            String[] splitPipe = splitCard[1].split("\\|");
            Set<Integer> winningNums = getNumbers(splitPipe[0]);
            Set<Integer> chosenNums = getNumbers(splitPipe[1]);
            totalScore = getTotalScore(totalScore, winningNums, chosenNums);
        }
        return totalScore;
    }

    public int partTwo(List<String> input) {

        int[] cardAmounts = new int[input.size()];
        int index = 0;
        int totalScore = 0;

        for (String line : input) {
            cardAmounts[index]++;
            System.out.println("Card " + (index+1));

            for (int i = 1; i <= cardAmounts[index]; i++) {
                String[] splitCard = line.split(":");
                String[] splitPipe = splitCard[1].split("\\|");
                Set<Integer> winningNums = getNumbers(splitPipe[0]);
                Set<Integer> chosenNums = getNumbers(splitPipe[1]);
                winningNums.retainAll(chosenNums);
                int numWins = winningNums.size();
                totalScore++;
                for (int j = 1; j <= numWins; j++) {
                    cardAmounts[index+j]++;
                }
            }
            index++;
        }
        return totalScore;
    }

    private Set<Integer> getNumbers(String numbers) {
        int[] chosenNumbers = Arrays.stream(numbers.trim()
                .replace("\s", ",").split(","))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt).toArray();
        Set<Integer> chosenNums = new HashSet<>();
        for (Integer chosenNumber : chosenNumbers) {
            chosenNums.add(chosenNumber);
        }
        return chosenNums;
    }

    private int getTotalScore(int totalScore, Set<Integer> winningNums, Set<Integer> chosenNums) {
        winningNums.retainAll(chosenNums);
        if (winningNums.size() > 0) {
            int currentScore = 0;
            for (int i = 0; i < winningNums.size(); i++) {
                if (i == 0) {
                    currentScore++;
                } else {
                    currentScore *= 2;
                }
            }
            totalScore += currentScore;
        }
        return totalScore;
    }

    private String parseCardNumber(String input) {
        Pattern cardNumberPattern = Pattern.compile("Card (\\d+)");
        Matcher cardNumberMatch = cardNumberPattern.matcher(input);
        String cardNumber = "";
        while (cardNumberMatch.find()) {
            cardNumber = cardNumberMatch.group(1);
        }
        return cardNumber;
    }
}
