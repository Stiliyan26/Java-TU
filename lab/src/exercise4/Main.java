package exercise4;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String filePath = "persons.bin";

        List<Person> personsToWrite = new ArrayList<>();
        personsToWrite.add(new Person("Alice", LocalDate.of(1990, 5, 15)));
        personsToWrite.add(new Person("Bob", LocalDate.of(1985, 8, 20)));

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(personsToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Person> personsFromFile = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            personsFromFile = (List<Person>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Person person : personsFromFile) {
            System.out.println("Name: " + person.getName());
            System.out.println("Birth: " + person.getBirth());
            System.out.println("Age: " + person.getAge());
            System.out.println();
        }
    }
}
