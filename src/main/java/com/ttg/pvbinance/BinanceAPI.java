package com.ttg.pvbinance;

import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.impl.spot.Market;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI {

    private SpotClientImpl spotClient;
    private static BinanceAPI instance;

    public BinanceAPI() {
        BinanceAPI.instance = this;
        this.spotClient = new SpotClientImpl(PrivateConf.API_KEY, PrivateConf.SECRET_KEY, PrivateConf.baseUrl);
        Market market = spotClient.createMarket();
    }


    public static BinanceAPI getInstance() {
        return BinanceAPI.instance;
    }

    public boolean isUserLoggedIn() {
        return true;
    };
}