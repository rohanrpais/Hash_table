import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryManager {

        private Map<String, AtomicInteger> stock = new HashMap<>();
        private Map<String, Queue<Integer>> waitingList = new HashMap<>();

        public void addProduct(String productId, int count) {
            stock.put(productId, new AtomicInteger(count));
            waitingList.put(productId, new LinkedList<>());
        }

        public String purchaseItem(String productId, int userId) {
            AtomicInteger count = stock.get(productId);
            if (count.get() > 0) {
                count.decrementAndGet();
                return "Success, " + count.get() + " units remaining";
            } else {
                waitingList.get(productId).add(userId);
                return "Added to waiting list, position #" + waitingList.get(productId).size();
            }
        }

        public int checkStock(String productId) {
            return stock.get(productId).get();
        }


    public static void main(String[] args) {
        InventoryManager inv = new InventoryManager();
        inv.addProduct("IPHONE15", 2);

        System.out.println(inv.purchaseItem("IPHONE15", 101));
        System.out.println(inv.purchaseItem("IPHONE15", 102));
        System.out.println(inv.purchaseItem("IPHONE15", 103));
        System.out.println("Stock left: " + inv.checkStock("IPHONE15"));
    }
}
