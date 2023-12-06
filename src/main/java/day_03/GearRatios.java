package day_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GearRatios {

    public List<String> getInputText() throws IOException {
        return Files.readAllLines(Paths.get("./src/test/resources/day_03_part_01.txt"));
    }

    public int partOne(List<String> input) {
        Character[][] matrix = getMatrix(input);
        List<ValidNumber> partNumbers = getAllPartNumbers(matrix);
        List<ValidNumber> validPartNumbers = getValidPartNumbers(matrix, partNumbers);
        return validPartNumbers.stream().map(ValidNumber::getValue).mapToInt(Integer::parseInt).sum();
    }

    public int partTwo(List<String> input) {
        int total = 0;
        Character[][] matrix = getMatrix(input);
        List<ValidNumber> partNumbers = getAllPartNumsBesideGear(matrix, getAllPartNumbers(matrix));
        List<GearSymbol> allGearSymbols = getAllGearSymbols(matrix);

        for (GearSymbol gearSymbol : allGearSymbols) {
            Set<ValidNumber> gearsTwoValidNums = checkSurroundingForNums(gearSymbol, matrix, partNumbers);
            if (gearsTwoValidNums != null) {
                int product = 1;
                for (ValidNumber gearsTwoValidNum : gearsTwoValidNums) {
                    product *= Integer.parseInt(gearsTwoValidNum.getValue());
                }
                total += product;
            }
        }
        return total;
    }

    private Set<ValidNumber> checkSurroundingForNums(GearSymbol gear, Character[][] matrix, List<ValidNumber> partNumbers) {
        int row = gear.getRow();
        int column = gear.getColumn();
        int indexMod = 0;
        Set<ValidNumber> surroundingNums = new HashSet<>();

        int[][] directions = {
                { -1 , 0 },
                { -1 , 1 },
                { 0 , 1 },
                { 1 , 1 },
                { 1 , 0 },
                { 1 , -1 },
                { 0 , -1 },
                { -1 , -1 }
        };

        for (int[] direction : directions) {
            int tempRow = direction[0];
            int tempColumn = direction[1];
            if (isNumber(matrix[(row+tempRow+indexMod)][(column+tempColumn+indexMod)])) {
                for (ValidNumber partNumber : partNumbers) {
                    if ((row+tempRow) >= partNumber.getRow() && (row+tempRow) <= partNumber.getRow()) {
                        if ((column + tempColumn) >= partNumber.getColumn() && (column + tempColumn) <= (partNumber.getColumn() + partNumber.getValue().length() - 1)) {
                            surroundingNums.add(partNumber);
                        }
                    }
                }
            }
        }
        if (surroundingNums.size() == 2) return surroundingNums;
        return null;
    }

    private List<GearSymbol> getAllGearSymbols(Character[][] matrix) {
        List<GearSymbol> gearSymbols = new ArrayList<>();
        for (int i = 1; i < matrix.length-1; i++) {
            String gearSymbol = "*";
            for (int j = 1; j < matrix[1].length-1; j++) {
                if (isGearSymbol(matrix[i][j])) {
                    gearSymbols.add(new GearSymbol(i, j, gearSymbol));
                }
            }
        }
        return gearSymbols;
    }

    private List<ValidNumber> getAllPartNumsBesideGear(Character[][] matrix, List<ValidNumber> partNumbers) {
        List<ValidNumber> valid = new ArrayList<>();
        for (ValidNumber validNum : partNumbers) {
            char[] value = validNum.getValue().toCharArray();
            if (checkSurroundingGear(validNum, matrix)) {
                valid.add(validNum);
            }
        }
        return valid;
    }

    private List<ValidNumber> getValidPartNumbers(Character[][] matrix, List<ValidNumber> partNumbers) {
        List<ValidNumber> valid = new ArrayList<>();
        for (ValidNumber validNum : partNumbers) {
            char[] value = validNum.getValue().toCharArray();
            if (checkSurrounding(validNum.getRow(), validNum.getColumn(), value.length, matrix)) {
                valid.add(validNum);
            }
        }
        return valid;
    }

    private static List<ValidNumber> getAllPartNumbers(Character[][] matrix) {
        List<ValidNumber> validNums = new ArrayList<>();
        for (int i = 1; i < matrix.length-1; i++) {
            StringBuilder validNum = new StringBuilder();
            for (int j = 1; j < matrix[1].length-1; j++) {
                if (Character.isDigit(matrix[i][j])) {
                    validNum.append(matrix[i][j]);
                    if (Character.isDigit(matrix[i][j+1])) { continue; }
                    validNums.add(new ValidNumber(i, j-validNum.length()+1, validNum.toString()));
                    validNum = new StringBuilder();
                }
            }
        }
        return validNums;
    }

    private boolean isGearSymbol(Character c) {
        return "*".equals(String.valueOf(c));
    }

    private boolean isSymbol(Character c) {
        return ((!('.' == c)) && !(Character.isDigit(c)));
    }
    private boolean isNumber(Character c) {
        return Character.isDigit(c);
    }

    private boolean checkSurroundingGear(ValidNumber num, Character[][] matrix) {

        int row = num.getRow();
        int column = num.getColumn();
        int length = num.getValue().length();

        for (int l = 0; l < length; l++) {
            int indexMod = l;
            boolean top = isGearSymbol(matrix[row-1][column+indexMod]);
            boolean topRight = isGearSymbol(matrix[row-1][column+1+indexMod]);
            boolean right = isGearSymbol(matrix[row][column+1+indexMod]);
            boolean bottomRight = isGearSymbol(matrix[row+1][column+1+indexMod]);
            boolean bottom = isGearSymbol(matrix[row+1][column+indexMod]);
            boolean bottomLeft = isGearSymbol(matrix[row+1][column-1+indexMod]);
            boolean left = isGearSymbol(matrix[row][column-1+indexMod]);
            boolean topLeft = isGearSymbol(matrix[row-1][column-1+indexMod]);
            Boolean[] surrounding = { top, topRight, right, bottomRight, bottom, bottomLeft, left, topLeft };

            for (Boolean b : surrounding) {
                if (b) return true;
            }
        }
        return false;
    }

    private boolean checkSurrounding(int row, int column, int length, Character[][] matrix) {

        for (int l = 0; l < length; l++) {
            int indexMod = l+1;
            boolean top = isSymbol(matrix[row-1][column+indexMod]);
            boolean topRight = isSymbol(matrix[row-1][column+1+indexMod]);
            boolean right = isSymbol(matrix[row][column+1+indexMod]);
            boolean bottomRight = isSymbol(matrix[row+1][column+1+indexMod]);
            boolean bottom = isSymbol(matrix[row+1][column+indexMod]);
            boolean bottomLeft = isSymbol(matrix[row+1][column-1+indexMod]);
            boolean left = isSymbol(matrix[row][column-1+indexMod]);
            boolean topLeft = isSymbol(matrix[row-1][column-1+indexMod]);
            Boolean[] surrounding = { top, topRight, right, bottomRight, bottom, bottomLeft, left, topLeft };

            for (Boolean b : surrounding) {
                if (b) return true;
            }
        }
        return false;
    }

    private Character[][] getMatrix(List<String> input) {
        int width = input.get(0).length()+2;
        int height = input.size()+2;
        Character[][] matrix = new Character[height][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || i == matrix.length-1 || j == 0 || j == matrix[0].length-1) {
                    matrix[i][j] = ".".charAt(0);
                } else {
                    matrix[i][j] = input.get(i-1).toCharArray()[j-1];
                }
            }
        }
        return matrix;
    }

    public static class GearSymbol {

        public GearSymbol(int row, int column, String value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        private List<ValidNumber> adjacentNums;

        private final int row;
        private final int column;
        private final String value;

        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }
        public String getValue() {
            return value;
        }

        public List<ValidNumber> getAdjacentNums() {
            return adjacentNums;
        }

        public void setAdjacentNums(List<ValidNumber> adjacentNums) {
            this.adjacentNums = adjacentNums;
        }
    }

    public static class ValidNumber {

        public ValidNumber(int row, int column, String value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        private List<GearSymbol> gearSymbols = new ArrayList<>();

        private final int row;
        private final int column;
        private final String value;

        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }
        public String getValue() {
            return value;
        }

        public List<GearSymbol> getGearSymbols() {
            return gearSymbols;
        }

        public void setGearSymbols(List<GearSymbol> gearSymbols) {
            this.gearSymbols = gearSymbols;
        }

        public void addGearSymbol(GearSymbol gearSymbol) {
            this.gearSymbols.add(gearSymbol);
        }

    }

}
