package Lists;

public class Main {
    public static void main(String[] args) {
        SynonymDictionary dict = new SynonymDictionary();

        System.out.println("milk".compareTo("bndi"));

        dict.addSynonym("Egg", "bndi");
        dict.addSynonym("Egg", "Milk");
        dict.addSynonym("Egg", "andi");

        dict.addSynonym("Arr", "zdrasti");
        dict.addSynonym("Arr", "zdr");
        dict.addSynonym("Arr", "asgdggdda");

        System.out.println(dict);
    }
}
