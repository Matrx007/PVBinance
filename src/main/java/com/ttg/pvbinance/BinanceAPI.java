package com.ttg.pvbinance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI { // https://github.com/binance-exchange/binance-java-api

    private BinanceApiRestClient client;
    private static BinanceAPI instance;

    public BinanceAPI() {
        BinanceAPI.instance = this;
        logIn(PrivateConf.API_KEY, PrivateConf.SECRET_KEY, "");
        System.out.println(this.getTime());
        this.getCurrencies();
    }

    public boolean logIn(String apiKey, String secretKey, String baseURL) {
        PrivateConf.API_KEY = apiKey;
        PrivateConf.SECRET_KEY = secretKey;
        PrivateConf.baseUrl = baseURL;

        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(PrivateConf.API_KEY, PrivateConf.SECRET_KEY);
        BinanceApiRestClient client = factory.newRestClient();
        this.client = client;

        return true;
    }

    public long getTime() {
        return client.getServerTime();
    }

    public List<SymbolInfo> getCurrencies() {
        return client.getExchangeInfo().getSymbols();
    }

    public String getPrice(String symbol) {
        return client.get24HrPriceStatistics(symbol).getLastPrice();
    }

    public List<Asset> getAssets() {
        return client.getAllAssets();
    }

    public List<OrderBookEntry> order(String symbol, int quantity) {
        OrderBook ob = client.getOrderBook(symbol, quantity);
        return ob.getAsks();
    }


    public static BinanceAPI getInstance() {
        return BinanceAPI.instance;
    }

    public boolean isUserLoggedIn() {
        return true;
    }

    public static class OwnedCryptoPriceInfo {

    }

    /**
     * Used when displaying crypto prices by name.
     */
    public static class CryptoPriceInfo {
        public String name;
        public float price;

        public CryptoPriceInfo(String name, float price) {
            this.name = name;
            this.price = price;
        }
    }

    /**
     * Used when displaying crypto price history.
     */
    public static class CryptoPriceHistoryInfo {
        public int timestamp;
        public float price;
        public float change;

        public CryptoPriceHistoryInfo(int timestamp, float price, float change) {
            this.timestamp = timestamp;
            this.price = price;
            this.change = change;
        }
    }
}
