    import java.util.Random;

    public class upr1 {
        public static void main(String[] args) {
            printTriangele();
        }

        private void printAvg() {
            Random random = new Random();
            double sum = 0.0;

            int minValue = 1;
            int maxValue = 100;

            for (int i = 0; i < 11; i++) {
                sum += random.nextInt(maxValue);
            }

            System.out.println("Avg: " + sum / 11);
        }

        private static void printTriangele() {
            int size = 5;
            // Upward-pointing triangle
            for (int i = 0; i < size; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        }
    }
