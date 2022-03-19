package com.ttg.pvbinance.gui;

import com.ttg.pvbinance.BinanceAPI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CurrencyGUI {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private BinanceAPI.CryptoPriceInfo crypto;

    public CurrencyGUI(BinanceAPI.CryptoPriceInfo crypto) {
        this.crypto = crypto;

        JFrame frame = new JFrame(crypto.name);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.getContentPane().add(createContent(), BorderLayout.CENTER);

        frame.pack();
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

    private Component createContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(new JLabel(crypto.name));
        panel.add(new JLabel(Float.toString(crypto.price)));

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.LINE_AXIS));

        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        actionsPanel.add(buyButton);
        actionsPanel.add(sellButton);
        panel.add(actionsPanel);

        panel.add(new JLabel("History"));
        JPanel historyContainer = new JPanel();
        historyContainer.setLayout(new BoxLayout(historyContainer, BoxLayout.PAGE_AXIS));

        ArrayList<Component> historyElement = createHistory();
        historyElement.forEach(historyContainer::add);

        panel.add(historyContainer);

        return panel;
    }

    private ArrayList<Component> createHistory() {
        ArrayList<BinanceAPI.CryptoPriceHistoryInfo> data = new ArrayList<BinanceAPI.CryptoPriceHistoryInfo>();

        for(int i = 0; i < 100; i++) {
            data.add(new BinanceAPI.CryptoPriceHistoryInfo(1647684406 - (int)(Math.random()*10000), (float)Math.random()*1000, (float)Math.random()*20-10));
        }

        ArrayList<Component> components = new ArrayList<>();
        data.forEach(cryptoPriceHistoryInfo -> {
            JPanel panel = new JPanel(new BorderLayout());

            Date date = new Date();
            date.setTime((long)cryptoPriceHistoryInfo.timestamp*1000);

            panel.add(new JLabel(DATE_FORMAT.format(date)), BorderLayout.LINE_START);

            JPanel pricePanel = new JPanel(new BorderLayout());
            pricePanel.add(new JLabel(Float.toString(cryptoPriceHistoryInfo.price)), BorderLayout.PAGE_START);
            pricePanel.add(new JLabel(Float.toString(cryptoPriceHistoryInfo.change)), BorderLayout.PAGE_END);

            panel.add(pricePanel, BorderLayout.LINE_END);

            components.add(panel);
        });

        return components;
    }
}
