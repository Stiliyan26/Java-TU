public class Zad4 {
    public static void main(String[] args) {
        int[] sequence = { 1, 3, 10, 5, 4, 7, 6 };

        if (checkSequence(sequence)) {
            System.out.println("The row satisfies the condition a0 < a1 > a2 < a3 and so on.");
        } else {
            System.out.println("The row does not satisfy the condition a0 < a1 > a2 < a3 and so on.");
        }
    }

    public static boolean checkSequence(int[] sequence) {
        boolean fufillsCondition = true;

        for (int i = 0; i < sequence.length - 1; i++) {
            if (i % 2 == 0) {
                if (sequence[i] > sequence[i + 1]) {
                    fufillsCondition = false;
                    break;
                }
            } else if (i % 2 == 1) {
                if (sequence[i] < sequence[i + 1]) {
                    fufillsCondition = false;
                    break;
                }
            }
        }

        return fufillsCondition;
    }
}
