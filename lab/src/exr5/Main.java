package exr5;

public class Main {
    public static void main(String[] args) {
        try {
            Student student = new Student("stiliyan@tu-sofia.bg", "1234567891");

            System.out.println(student.Info());
            String message = student.Info();
            System.out.println(student.EmailToUpperCase());
        } catch (StudentException e) {
            e.printStackTrace();
        }
    }
}
