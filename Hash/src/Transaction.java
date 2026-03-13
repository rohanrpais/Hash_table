import java.util.*;

public class Transaction {

        int id, amount;
        String merchant;
        String time;
        Transaction(int id, int amount, String merchant, String time) {
            this.id = id; this.amount = amount; this.merchant = merchant; this.time = time;
        }


    static class FraudDetector {
        public List<int[]> findTwoSum(List<Transaction> txns, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            List<int[]> result = new ArrayList<>();
            for (Transaction t : txns) {
                if (map.containsKey(target - t.amount)) {
                    result.add(new int[]{map.get(target - t.amount), t.id});
                }
                map.put(t.amount, t.id);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 500, "StoreA", "10:00"),
                new Transaction(2, 300, "StoreB", "10:15"),
                new Transaction(3, 200, "StoreC", "10:30")
        );
        FraudDetector fd = new FraudDetector();
        List<int[]> pairs = fd.findTwoSum(txns, 500);
        for (int[] p : pairs) {
            System.out.println("TwoSum pair: " + p[0] + ", " + p[1]);
        }
    }
}
