import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Voter> voters = new ArrayList<>();
//
//        voters.add(new Voter("Stiliyna", "Sofia", "Kestenova gora", 21));
//        voters.add(new Voter("Kiril", "Buras", "Theater 199v", 18));
//        voters.add(new Voter("Stiliyna", "Sofia", "The Bulgarian Army Theater", 10));
//        voters.add(new Voter("Vladi", "Sofia", "Kestenova gora", 16));
//
//
//        voters = voters.stream()
//                .filter(v -> v.city.equals("Sofia"))
//                .collect(Collectors.toList());
//
//        voters.sort(Comparator.comparing(Voter::getStreet)
//                .thenComparing(Voter::getAddress));
//
//        for (Voter voter : voters) {
//            System.out.println(voter.print());
//        }

//        String[] arr = { "kir", "stiliyan", "bobi", "dimitar" };
//
//        System.out.println(Arrays.stream(arr).max(Comparator.comparing(String::length)).get());
//        System.out.println(Arrays.stream(arr).min(Comparator.comparing(String::length)).get());
        List<String> arr = Arrays.asList("AS", "DS", "FD", "AS");

        System.out.println("Is only upper case: " + arr.stream().allMatch(s -> s.chars().allMatch(Character::isUpperCase)));

        System.out.println("Is only lower case: " + arr.stream().allMatch(s -> s.chars().allMatch(Character::isLowerCase)));

        System.out.println("Is only mixed case: " + arr.stream()
                .allMatch(s -> mixedCase(s)));
    }

    public static boolean mixedCase(String s) {
        boolean isLowerCase = s.chars().anyMatch(Character::isUpperCase);
        boolean isUpperCase = s.chars().anyMatch(Character::isLowerCase);

        return  isLowerCase && isUpperCase;
    }
}
