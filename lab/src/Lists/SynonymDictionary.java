package Lists;

import java.util.*;

public class SynonymDictionary {
    private Map<String, List<String>> map;

    public SynonymDictionary() {
        this.map = new HashMap<>();
    }
    public void addSynonym(String word, String synonym) {
        if (map.get(word) == null) {
            map.put(word, new ArrayList<>());
        }

        if (!isSynonym(word, synonym)) {
            List<String> synonyms = map.get(word);
            int index = 0;

            if (synonyms != null) {
                for (int i = 0; i < synonyms.size(); i++) {
                    String currentSyn = synonyms.get(i);

                    if (synonym.toLowerCase().compareTo(currentSyn.toLowerCase()) < 0) {
                        index = i;
                        break;
                    }

                    if (index == 0 && i == synonyms.size() - 1) {
                        index = i + 1;
                        break;
                    }
                }

                synonyms.add(index, synonym);
            }
        }
    }

    public boolean isSynonym(String word, String synonym) {
        return map.get(word).contains(synonym);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
