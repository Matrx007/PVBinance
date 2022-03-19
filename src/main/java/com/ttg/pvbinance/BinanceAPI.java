package com.ttg.pvbinance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.SymbolInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI { // https://github.com/binance-exchange/binance-java-api

    private BinanceApiRestClient client;
    private static BinanceAPI instance;

    public BinanceAPI() {
        BinanceAPI.instance = this;
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
        BinanceApiRestClient client = factory.newRestClient();
        this.client = client;
        System.out.println(this.getTime());
        this.getCurrencies();
        this.getCurrencyInOther("BTCBUSD", "ETHBUSD");
    }

    public long getTime() {
        return client.getServerTime();
    }

    public List<SymbolInfo> getCurrencies() {
        return client.getExchangeInfo().getSymbols();
    }

    public void getCurrencyInOther(String first, String second) {
        
    }



    public static BinanceAPI getInstance() {
        return BinanceAPI.instance;
    }

    public boolean isUserLoggedIn() {
        return true;
    };

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
