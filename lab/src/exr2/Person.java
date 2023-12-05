package exr2;
public class Person {
     private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        if (name == null)  {
            System.out.println("Name canot be null");
        }
        this.name = name;
        this.age = age;
    }

    void sayInfo() {
        System.out.println("My name is " + name + "I'am " + age + " years old.");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
