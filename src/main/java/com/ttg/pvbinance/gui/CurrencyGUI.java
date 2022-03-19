package com.ttg.pvbinance.gui;

import com.binance.api.client.domain.general.SymbolInfo;
import com.ttg.pvbinance.BinanceAPI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CurrencyGUI {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private String crypto;

    public CurrencyGUI(String crypto) {
        this.crypto = crypto;

        JFrame frame = new JFrame(crypto);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.getContentPane().add(createContent(), BorderLayout.CENTER);

        frame.pack();
        frame.setSize(480, 600);
        frame.setVisible(true);
    }

    private Component createContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(new JLabel(crypto));
        panel.add(new JLabel(BinanceAPI.getInstance().getPrice(crypto)));

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.LINE_AXIS));

        JButton tradeButton = new JButton("Trade");
        tradeButton.addActionListener(e -> new TradeGUI(crypto));
        actionsPanel.add(tradeButton);
        panel.add(actionsPanel);

        JLabel historyLabel = new JLabel("History");
        historyLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(historyLabel);

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
