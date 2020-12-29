package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = "         ";

        printField(s);
        int counter = 1;
        char letter;
        boolean game = true;
        int row;
        int col;
        String enter;
        while (game) {
            if(counter % 2 == 0) {
                letter = 'O';
            } else {
                letter = 'X';
            }
            System.out.print("Enter the coordinates: ");
            enter = scanner.nextLine();
            String[] enterArray = enter.split(" ");
            try {
                row = Integer.parseInt(enterArray[0]);
                col = Integer.parseInt(enterArray[1]);
            } catch (NumberFormatException nfe) {
                System.out.println("You should enter number!");
                continue;
            }

            if (1 > row || row > 3 || 1 > col || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (!checkCell(s, row, col)) {
                System.out.println("This cell is occupied! Chose another one!");
                continue;
            }

            s = enterCell(s, row, col, letter);
            printField(s);
            counter++;
            game = checkState(s);
        }

    }

    public static boolean checkCell(String field, int row, int col) {
        char[][] array = fillArray(field);
        return array[row - 1][col - 1] == ' ';
    }

    public static String enterCell(String field, int row, int col, char letter) {
        char[][] array = fillArray(field);
        array[row - 1][col - 1] = letter;

        int index = 0;
        char[] newFieldAsArray = new char[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newFieldAsArray[index++] = array[i][j];
            }
        }
        return String.valueOf(newFieldAsArray);
    }

    public static boolean checkState(String s) {
        String[] sArray = s.split("");

        int countX = 0;
        int countO = 0;
        for (String ch : sArray) {
            if (ch.equals("O")) {
                countO++;
            }
            if (ch.equals("X")) {
                countX++;
            }
        }
        String row1 = sArray[0] + sArray[1] + sArray[2];
        String row2 = sArray[3] + sArray[4] + sArray[5];
        String row3 = sArray[6] + sArray[7] + sArray[8];
        String column1 = sArray[0] + sArray[3] + sArray[6];
        String column2 = sArray[1] + sArray[4] + sArray[7];
        String column3 = sArray[2] + sArray[5] + sArray[8];
        String diagonal1 = sArray[0] + sArray[4] + sArray[8];
        String diagonal2 = sArray[2] + sArray[4] + sArray[6];
        ArrayList<String> list = new ArrayList<>(Arrays.asList(row1, row2, row3, column1, column2, column3, diagonal1, diagonal2));

        if (list.contains("XXX") && !list.contains("OOO")) {
            System.out.println("X wins");
            return false;
        } else if (list.contains("OOO") && !list.contains("XXX")) {
            System.out.println("O wins");
            return false;
        } else if (!s.contains(" ")) {
            System.out.println("Draw");
            return false;
        } else if (list.contains("XXX") && list.contains("OOO") || ((countO - countX) > 1) || ((countX - countO) > 1)) {
            System.out.println("Impossible");
            return false;
        } else {
            return true; // Game not finished
        }
    }

    public static char[][] fillArray(String s) {
        int index = 0;
        char[][] array = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = s.charAt(index++);
            }
        }
        return array;
    }

    public static void printField(String s) {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 4) {
                    System.out.print("-");
                } else {
                    if (j == 0 || j == 8) {
                        System.out.print("|");
                    } else if (j % 2 != 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print(s.charAt(k));
                        k++;
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
