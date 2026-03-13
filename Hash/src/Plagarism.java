import java.util.*;

public class Plagarism {
        private Map<String, Set<String>> nGramIndex = new HashMap<>();
        public void indexDocument(String docId, String text, int n) {
            String[] words = text.split(" ");
            for (int i = 0; i <= words.length - n; i++) {
                String nGram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
                nGramIndex.computeIfAbsent(nGram, k -> new HashSet<>()).add(docId);
            }
        }
        public double analyze(String docId, String text, int n) {
            int matches = 0, total = 0;
            String[] words = text.split(" ");
            for (int i = 0; i <= words.length - n; i++) {
                total++;
                String nGram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
                if (nGramIndex.containsKey(nGram)) matches++;
            }
            return (matches * 100.0) / total;
        }


    public static void main(String[] args) {
        Plagarism pd = new Plagarism();
        pd.indexDocument("doc1", "this is a test document", 2);
        double sim = pd.analyze("doc2", "this is another test", 2);
        System.out.println("Similarity: " + sim + "%");
    }
}
