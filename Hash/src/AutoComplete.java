import java.util.*;

public class AutoComplete {

        private Map<String, Integer> frequency = new HashMap<>();

        public void addQuery(String query) {
            frequency.put(query, frequency.getOrDefault(query, 0) + 1);
        }

        public List<String> search(String prefix) {
            return frequency.entrySet().stream()
                    .filter(e -> e.getKey().startsWith(prefix))
                    .sorted((a, b) -> b.getValue() - a.getValue())
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .toList();
        }

    public static void main(String[] args) {
        AutoComplete  ac = new AutoComplete ();
        ac.addQuery("java tutorial");
        ac.addQuery("javascript basics");
        ac.addQuery("java download");
        ac.addQuery("java tutorial"); // increase frequency
        System.out.println("Autocomplete for 'jav': " + ac.search("jav"));
    }
}
