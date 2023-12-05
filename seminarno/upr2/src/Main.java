import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = { 2, 1, 100, 20, 330, 30, 3, 7, 4 ,5 };

        int[] filteredCopy = sortAndFilter(arr, 150);
        System.out.println(Arrays.toString(filteredCopy));
    }

    public static int [] sortAndFilter(int[] array, int key) {
        int[] newArr = Arrays.copyOfRange(array, 0, array.length);


        int[] filteredArray = Arrays.stream(newArr)
                .filter(x -> x < key)
                .toArray();

        Arrays.sort(filteredArray);

        return filteredArray;
    }
}
