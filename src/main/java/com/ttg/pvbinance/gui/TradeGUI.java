package com.ttg.pvbinance.gui;

import com.binance.api.client.domain.general.SymbolInfo;
import com.ttg.pvbinance.BinanceAPI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TradeGUI {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private String crypto;

    public TradeGUI(String crypto) {
        this.crypto = crypto;

        JFrame frame = new JFrame(crypto);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.getContentPane().add(createContent(), BorderLayout.CENTER);

        frame.pack();
        frame.setSize(256, 80);
        frame.setVisible(true);
    }

    private Component createContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(new JLabel(crypto));
        panel.add(new JLabel(BinanceAPI.getInstance().getPrice(crypto)));

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.LINE_AXIS));

        JLabel countFieldLabel = new JLabel("Amount");
        JTextField countField = new JTextField();
        panel.add(countFieldLabel);
        panel.add(countField);

        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        actionsPanel.add(buyButton);
        actionsPanel.add(sellButton);
        panel.add(actionsPanel);

        return panel;
    }
}

