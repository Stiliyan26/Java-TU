package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Object mutex = new Object();
    private static int a = 0;

    public static void main(String[] args) {
        Main mainInstance = new Main();
        Main.InnerClass ic = mainInstance.new InnerClass();

        ic.startThreads();
    }

    public class InnerClass {
        private List<Integer> list = new ArrayList<>();
        private boolean produced = false;
        public void startThreads() {
            Runnable producer = () -> {
                Scanner sc = new Scanner(System.in);

                synchronized (mutex) {
                    for (int i = 0; i < 10; i++) {
                        int input = sc.nextInt();
                        list.add(input);
                        produced = true;
                        mutex.notify();

                        try {
                            mutex.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Runnable consumer = () -> {
                synchronized (mutex) {
                    for (int i = 0; i < 10; i++) {
                        while (!produced) {
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(list);
                        produced = false;
                        mutex.notify();
                    }
                }
            };

            Thread t1 = new Thread(producer);
            Thread t2 = new Thread(consumer);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            t1.start();
            t2.start();
        }
    }
}
