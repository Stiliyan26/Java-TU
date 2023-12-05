import java.util.Arrays;

public class Zad6 {
    public static void main(String[] args) {
        //3, 2, 1
        //10, 9, 8, 7, 2 //Longest
        //4, 3
        int[] arr = { 3, 2, 1, 4, 3, 10, 9, 8, 7, 2  };

        int countDescendingSequences = 0;
        int currentCountOfSequence = 1;
        int longestReducingSequenceCount = 0;
        int longestStartIndex = 0;
        int longestEndIndex = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                currentCountOfSequence++;
            } else {
                if (longestReducingSequenceCount < currentCountOfSequence) {
                    longestStartIndex = i + 1 - currentCountOfSequence;
                    longestEndIndex = i;
                    longestReducingSequenceCount = currentCountOfSequence;
                }

                countDescendingSequences++;
                currentCountOfSequence = 1;
            }
        }

        if (currentCountOfSequence > 1) {
            countDescendingSequences++;

            if (longestReducingSequenceCount < currentCountOfSequence) {
                longestStartIndex = arr.length - currentCountOfSequence;
                longestEndIndex = arr.length - 1;
                longestReducingSequenceCount = currentCountOfSequence;
            }
        }

        System.out.println("Count of reducing sequences is: " + countDescendingSequences);
        System.out.println("Longest descending sequence count is: " + longestReducingSequenceCount);
        int[] longestSequence = Arrays.copyOfRange(arr, longestStartIndex, longestEndIndex + 1);
        System.out.println(Arrays.toString(longestSequence));
    }
}
