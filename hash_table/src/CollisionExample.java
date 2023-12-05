import java.util.HashMap;

public class CollisionExample {
    public static void main(String[] args) {
        // Създаване на HashMap с размер по подразбиране
        HashMap<Integer, String> hashMap = new HashMap<>();

        // Добавяне на две стойности с еднакъв хеш код
        Integer key1 = 1;
        Integer key2 = 11;

        hashMap.put(key1, "Стойност 1");
        hashMap.put(key2, "Стойност 2");

        // Опит за извличане на стойности
        String value1 = hashMap.get(key1);
        String value2 = hashMap.get(key2);

        System.out.println("Стойност 1: " + value1);
        System.out.println("Стойност 2: " + value2);
    }
}
