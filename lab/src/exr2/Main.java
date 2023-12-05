package exr2;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();

        Person person2 = new Person("kiro", 20);

        System.out.println(person2);

        Person person3 = new Person(null, 20);

        System.out.println(person3.getName());
    }
}
