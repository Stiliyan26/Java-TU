package SAA;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
            { 1, 2, 3, 4 },
            { 5, 6, 6, 12 },
            { 42, 65, 63, 41 },
            { 5, 7, 9, 4 },
        };

        int oddCount = 0;
        int sumDiagonal = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] % 2 == 1) {
                    oddCount++;
                }
            }
        }

        System.out.println("Odd: " + oddCount);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i + j == matrix[0].length - 1) {
                    sumDiagonal += matrix[i][j];
                }
            }
        }
        System.out.println("Sum diagonal: " + sumDiagonal);

        int[] rowArr = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rowArr[i] += matrix[i][j];
            }
        }

        System.out.println("Sum rows - " + Arrays.toString(rowArr));

        int[] choosedArray = new int[2 + rowArr.length];
        choosedArray[0] = oddCount;
        choosedArray[1] = sumDiagonal;

        int coutner = 2;
        for (int i = 0; i < rowArr.length; i++) {
            choosedArray[coutner] = rowArr[i];
            coutner++;
        }

        System.out.println("Last array: " + Arrays.toString(choosedArray));
    }
}
