package com.ttg.pvbinance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
        client.ge
        return Long.parseLong(spotClient.createMarket().time().replace("{\"serverTime\":", "").replace("}", ""));
    }

    public ArrayList<CryptoPriceInfo> getCurrencies() {
        /* // https://binance-docs.github.io/apidocs/spot/en/#all-coins-39-information-user_data
        LinkedHashMap<String, Object> prop = new LinkedHashMap<>();
        prop.put("timestamp", this.getTime());
        Wallet wallet = this.spotClient.createWallet();
        String data = wallet.coinInfo(prop);
        System.out.println(data);
        return new HashMap<String, String>();
        */
        String data = this.spotClient.createMarket().tickerSymbol(new LinkedHashMap<>());
        ArrayList<CryptoPriceInfo> priceInfos = new ArrayList<>();
        for (String s : data.substring(1, data.length() - 2).split("}(,)*")) {
            s = s + "}";
            priceInfos.add(new CryptoPriceInfo(JSONParser.getJSONStringValue(s, "symbol"),
                    Float.parseFloat(JSONParser.getJSONStringValue(s, "price"))));
        }
        return priceInfos;
    }

    public void getCurrencyInOther(String first, String second) {
        LinkedHashMap<String, Object> prop = new LinkedHashMap<>();
        prop.put("symbol", first);
        System.out.println(this.spotClient.createMarket().tickerSymbol(prop));
        prop.replace("symbol", second);
        System.out.println(this.spotClient.createMarket().tickerSymbol(prop));
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
