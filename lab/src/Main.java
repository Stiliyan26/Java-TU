public class Main {
    public static void main(String[] args) {
       int[] arr = new int[] { 2, 3, 6, 12 };

        int biggestSeparator = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int a = arr[i];
                int b = arr[j];

                int max = Math.min(a, b);

                if (biggestSeparator < max) {
                    biggestSeparator = max;
                }
            }
        }

        System.out.println(biggestSeparator);
    }
}

//private static zad1() {
//    int x = 3;
//    int n = 0;
//
//    while (x <= 4200) {
//        x = 4 * x + 17;
//        n++;
//    }
//
//    System.out.println(x);
//    System.out.println("Indx");
//}
