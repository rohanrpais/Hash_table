import java.util.*;
public class DNSEntry {
        String domain, ip;
        long expiryTime;
        DNSEntry(String domain, String ip, int ttl) {
            this.domain = domain;
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + ttl * 1000;
        }
        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }


    static class DNSCache {
        private Map<String, DNSEntry> cache = new HashMap<>();
        private int hits = 0, misses = 0;

        public String resolve(String domain) {
            DNSEntry entry = cache.get(domain);
            if (entry == null || entry.isExpired()) {
                misses++;
                String ip = queryUpstream(domain);
                cache.put(domain, new DNSEntry(domain, ip, 5)); // TTL 5s for demo
                return ip + " (MISS)";
            } else {
                hits++;
                return entry.ip + " (HIT)";
            }
        }

        private String queryUpstream(String domain) {
            return "172.217." + new Random().nextInt(255) + "." + new Random().nextInt(255);
        }

        public String getStats() {
            return "Hit Rate: " + (100.0 * hits / (hits + misses)) + "%";
        }

    }
    public static void main(String[] args) throws InterruptedException {
        DNSCache dns = new DNSCache();
        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
        Thread.sleep(6000); // wait for TTL expiry
        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.getStats());
    }
}
