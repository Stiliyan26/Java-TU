import java.util.Arrays;
import java.util.Scanner;

public class ExercieOne {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exercise2();
    }

    private static void exercise1() {
        System.out.println("Type word: ");
        String input = scanner.nextLine();

        System.out.println(input);
    }

    private static void exercise2() {
        float[] array = new float[2];

        float number = scanner.nextFloat();
        array[0] = 1;
        array[1] = 2;

        float[] updtatedArray = multiply(array, number);

        float[] updatedArray2 = multiply(1, 2, 3);
        System.out.println(Arrays.toString(updatedArray2));
        float[] devidedArr = divide(10, 20, 2);
        System.out.println(Arrays.toString(devidedArr));

    }

    private static float[] multiply(float[] arr, float number) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= number;
        }

        return arr;
    }

    private static float[] multiply(float a, float b, float s) {
        float[] array = new float[2];
        array[0] = a;
        array[1] = b;

        return multiply(array, s);
    }

    private static float[] divide(float a, float b, float s) {
        float[] array = new float[2];
        array[0] = a;
        array[1] = b;

        System.out.println();
        return multiply(array, 1/s);
    }
}
