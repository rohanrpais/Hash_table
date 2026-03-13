import java.util.*;

public class MultiLevelCache {
        private LinkedHashMap<String, String> L1 = new LinkedHashMap<>(16, 0.75f, true);
        private Map<String, String> L2 = new HashMap<>();
        private Map<String, String> L3 = new HashMap<>();

        public MultiLevelCache() {
            // preload demo data
            L3.put("video_123", "DB: Video 123");
            L3.put("video_999", "DB: Video 999");
        }

        public String getVideo(String id) {
            if (L1.containsKey(id)) {
                return "L1 HIT: " + L1.get(id);
            } else if (L2.containsKey(id)) {
                String val = L2.get(id);
                L1.put(id, val); // promote
                return "L2 HIT → promoted to L1: " + val;
            } else if (L3.containsKey(id)) {
                String val = L3.get(id);
                L2.put(id, val);
                return "L3 HIT → added to L2: " + val;
            }
            return "MISS: Not found";
        }


    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();
        System.out.println(cache.getVideo("video_123"));
        System.out.println(cache.getVideo("video_123"));
        System.out.println(cache.getVideo("video_999"));
        System.out.println(cache.getVideo("video_000"));
    }
}
