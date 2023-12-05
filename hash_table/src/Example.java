import java.util.HashMap;

public class Example {
    public static void main(String[] args) {
        HashMap<String, Integer> studentsScores = new HashMap<>();

        //Adds elements to the hashmap
        studentsScores.put("Alice", 95);
        studentsScores.put("Bob", 85);
        studentsScores.put("Charlie", 92);
        studentsScores.put("David", 78);

        //Removes element with the key
        studentsScores.remove("Bob");
        for (String name : studentsScores.keySet()) {
            //Retrieves the value by the given key
            int score = studentsScores.get(name);
            System.out.format("%s has %d points%n", name, score);
        }
    }
}
