package com.ttg.pvbinance;

import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.impl.spot.Market;
import com.binance.connector.client.utils.JSONParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI {

    private SpotClientImpl spotClient;
    private static BinanceAPI instance;

    public BinanceAPI() {
        BinanceAPI.instance = this;
        this.spotClient = new SpotClientImpl(PrivateConf.API_KEY, PrivateConf.SECRET_KEY, PrivateConf.baseUrl);
        System.out.println(this.getTime());
        this.getCurrencies();
    }

    public long getTime() {
        return Long.parseLong(JSONParser.getJSONStringValue(spotClient.createMarket().time(), "serverTime"));
    }

    public HashMap<String, String> getCurrencies() {
        String data = this.spotClient.createWallet().coinInfo(new LinkedHashMap<String, Object>());
        return new HashMap<String, String>();
    }

    public void getCurrencyInOther(String first, String second) {
        System.out.println(this.spotClient.createMarket().tickerSymbol(new LinkedHashMap<String, Object>()));
    }



    public static BinanceAPI getInstance() {
        return BinanceAPI.instance;
    }

    public boolean isUserLoggedIn() {
        return true;
    };
}
