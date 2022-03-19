package com.ttg.pvbinance;

import com.binance.connector.client.impl.SpotClientImpl;

/**
 * This class will contain high-level functions which will call the Binance API.
 */

public class BinanceAPI {
    SpotClientImpl spotClient = new SpotClientImpl(PrivateConf.API_KEY, PrivateConf.SECRET_KEY, PrivateConf.baseUrl);
}
