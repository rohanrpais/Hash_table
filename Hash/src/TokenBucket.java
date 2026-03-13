import java.util.*;

public class TokenBucket {

        int tokens, maxTokens;
        long lastRefill;
        int refillRate;

        TokenBucket(int maxTokens, int refillRate) {
            this.tokens = maxTokens;
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.lastRefill = System.currentTimeMillis();
        }

        synchronized boolean allowRequest() {
            refill();
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            long elapsed = (now - lastRefill) / 1000;
            int refillTokens = (int) (elapsed * refillRate);
            tokens = Math.min(maxTokens, tokens + refillTokens);
            if (refillTokens > 0) lastRefill = now;
        }


    static class RateLimiter {
        private Map<String, TokenBucket> clients = new HashMap<>();

        public boolean checkRateLimit(String clientId) {
            // Correct usage: pass arguments to constructor
            clients.putIfAbsent(clientId, new TokenBucket(5, 1));
            return clients.get(clientId).allowRequest();
        }
    }

    public static void main(String[] args) {
        RateLimiter rl = new RateLimiter();
        for (int i = 0; i < 7; i++) {
            System.out.println("Request " + i + ": " + rl.checkRateLimit("clientA"));
        }
    }
}
