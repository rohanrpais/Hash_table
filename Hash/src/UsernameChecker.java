import java.util.*;

public class UsernameChecker {
        private Map<String, Integer> userMap = new HashMap<>();
        private Map<String, Integer> attemptFrequency = new HashMap<>();
        public boolean checkAvailability(String username) {
            attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
            return !userMap.containsKey(username);
        }
        public void registerUser(String username, int userId) {
            userMap.put(username, userId);
        }
        public List<String> suggestAlternatives(String username) {
            List<String> suggestions = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                String alt = username + i;
                if (!userMap.containsKey(alt)) suggestions.add(alt);
            }
            suggestions.add(username.replace("_", "."));
            return suggestions;
        }
        public String getMostAttempted() {
            return attemptFrequency.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).orElse(null);
        }

    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();
        checker.registerUser("john_doe", 1);
        System.out.println("Availability of john_doe: " + checker.checkAvailability("john_doe"));
        System.out.println("Availability of jane_smith: " + checker.checkAvailability("jane_smith"));
        System.out.println("Suggestions: " + checker.suggestAlternatives("john_doe"));
        System.out.println("Most attempted: " + checker.getMostAttempted());
    }
}
