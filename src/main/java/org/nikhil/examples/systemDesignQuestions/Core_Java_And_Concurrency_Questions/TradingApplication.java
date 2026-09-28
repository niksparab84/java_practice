package org.nikhil.examples.systemDesignQuestions.Core_Java_And_Concurrency_Questions;

import java.util.concurrent.ConcurrentHashMap;

public class TradingApplication {

    //Problem: You're developing a high-throughput trading application.
    // How would you design a thread-safe cache to store frequently accessed stock prices, minimizing lock contention?

    //Hint: Consider using ConcurrentHashMap for its high concurrency or an advanced data structure like a Concurrent Linked HashMap.
    // Discuss the trade-offs of different locking mechanisms.

    //Solution:
    // For a high-throughput trading application, use ConcurrentHashMap for a thread-safe cache.
    // It allows concurrent reads and writes with minimal lock contention by segmenting the map internally.
    // For eviction (e.g., LRU), consider a third-party ConcurrentLinkedHashMap or implement a custom solution with atomic operations.
    //
    //Trade-offs:
    //ConcurrentHashMap: High concurrency, no built-in eviction, low lock contention.
    //Synchronized map: Simple, but high lock contention.
    //Advanced structures (e.g., ConcurrentLinkedHashMap): Support eviction, but may have slightly higher overhead.
    //Example using ConcurrentHashMap:
    //For LRU eviction, use libraries like Caffeine or implement a custom solution with atomic operations
    // and minimal locking.

    public class StockPriceCache {
        private final ConcurrentHashMap<String, Double> priceCache = new ConcurrentHashMap<>();

        public void putPrice(String symbol, double price) {
            priceCache.put(symbol, price);
        }

        public Double getPrice(String symbol) {
            return priceCache.get(symbol);
        }
    }

    // Data structure to hold trades with latest version
    public class Trade {
        private String tradeId;
        private int version;
        private String details;

        public Trade(String tradeId, int version, String details) {
            this.tradeId = tradeId;
            this.version = version;
            this.details = details;
        }

        public String getTradeId() {
            return tradeId;
        }

        public int getVersion() {
            return version;
        }

        public String getDetails() {
            return details;
        }

        @Override
        public int hashCode() {
            return tradeId.hashCode();
        }
    }

    public class TradeCache {
        private final ConcurrentHashMap<String, Trade> tradeMap = new ConcurrentHashMap<>();

        public void putTrade(Trade trade) {
            tradeMap.merge(trade.getTradeId(), trade, (existingTrade, newTrade) ->
                    newTrade.getVersion() > existingTrade.getVersion() ? newTrade : existingTrade);
        }

        public Trade getTrade(String tradeId) {
            return tradeMap.get(tradeId);
        }
    }
}
