package com.ttg.pvbinance;

import com.binance.connector.client.impl.SpotClientImpl;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI {

    private SpotClientImpl spotClient;
    private static BinanceAPI instance;

    public BinanceAPI() {
        BinanceAPI.instance = this;
        this.spotClient = new SpotClientImpl(PrivateConf.API_KEY, PrivateConf.SECRET_KEY, PrivateConf.baseUrl);
        System.out.println(this.spotClient.createMarket().time());
    }


    public static BinanceAPI getInstance() {
        return BinanceAPI.instance;
    }
}
