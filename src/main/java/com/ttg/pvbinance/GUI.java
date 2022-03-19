package com.ttg.pvbinance;

import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.SymbolInfo;
import com.ttg.pvbinance.gui.CurrencyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class connects all GUI components. GUI will be started from here.
 */

public class GUI {
    private JMenuBar createMenuBar() {
        JMenuItem i1, i2, i3;

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuFile.add(new JMenuItem("Exit"));

        menuBar.add(menuFile);

        return menuBar;
    }

    private Component createMainPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Home", createHome());
        tabbedPane.addTab("Browse", createBrowse());
        tabbedPane.addTab("Account", createAccount());

        return tabbedPane;
    }

    private void createOverview(JPanel logInPanel, JPanel overviewPanel) {

        List<Asset> data = BinanceAPI.getInstance().getAssets();

        ArrayList<Component> components = new ArrayList<>();
        data.forEach(asset -> {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel(asset.getAssetName()), BorderLayout.LINE_START);

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(e -> new CurrencyGUI(asset.getAssetName()));

            panel.add(viewButton, BorderLayout.LINE_END);

            components.add(panel);
        });

        JLabel ownedLabel = new JLabel("Owned");
        ownedLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel ownedCryptos = new JPanel();
        components.forEach(ownedCryptos::add);

        overviewPanel.add(ownedLabel);
        overviewPanel.add(ownedCryptos);
    }

    private void createLogIn(JPanel logInPanel, JPanel overviewPanel) {
        logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.PAGE_AXIS));

        JLabel apiFieldLabel = new JLabel("API key");
        JTextField apiField = new JTextField();
        JLabel secretFieldLabel = new JLabel("Secret key");
        JTextField secretField = new JTextField();
        JLabel baseURLFieldLabel = new JLabel("Base URL");
        JTextField baseURLField = new JTextField();

        JButton logInButton = new JButton("Log in");
        logInButton.addActionListener(e -> {
            boolean success = BinanceAPI.getInstance().logIn(
                    apiField.getText(),
                    secretField.getText(),
                    baseURLField.getText()
            );

            if(success) {
                logInPanel.setVisible(false);
                overviewPanel.setVisible(true);
            } else {
                logInPanel.setVisible(true);
                overviewPanel.setVisible(false);
            }
        });

        logInPanel.add(apiFieldLabel);
        logInPanel.add(apiField);
        logInPanel.add(secretFieldLabel);
        logInPanel.add(secretField);
        logInPanel.add(baseURLFieldLabel);
        logInPanel.add(baseURLField);
        logInPanel.add(logInButton);
    }

    private Component createAccount() {
        JPanel panel = new JPanel();

        JPanel logInPanel = new JPanel();
        JPanel overviewPanel = new JPanel();

        createLogIn(logInPanel, overviewPanel);
        createOverview(logInPanel, overviewPanel);

        overviewPanel.setVisible(false);

        panel.add(logInPanel);
        panel.add(overviewPanel);

        return panel;
    }

    private ArrayList<Component> createBrowseContents() {
        List<SymbolInfo> data = BinanceAPI.getInstance().getCurrencies();

        ArrayList<Component> components = new ArrayList<>();
        data.forEach(cryptoPriceInfo -> {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel(cryptoPriceInfo.getSymbol()), BorderLayout.LINE_START);

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(e -> new CurrencyGUI(cryptoPriceInfo.getSymbol()));

            JPanel rightSidePanel = new JPanel(new BorderLayout(8, 0));
            rightSidePanel.add(new JLabel(BinanceAPI.getInstance().getPrice(cryptoPriceInfo.getSymbol())), BorderLayout.LINE_START);
            rightSidePanel.add(viewButton, BorderLayout.LINE_END);

            panel.add(rightSidePanel, BorderLayout.LINE_END);
            components.add(panel);
        });

        return components;
    }

    private Component createBrowse() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            System.out.println("'Refresh' button pressed");
        });

        ArrayList<Component> components = createBrowseContents();
        components.forEach(panel::add);

        return panel;
    }

    private Component createHome() {
        return new JLabel("Home");
    }

    public GUI() {
        System.out.println(1);
        JFrame frame = new JFrame("Binance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(2);
        frame.setJMenuBar(createMenuBar());
        frame.getContentPane().add(createMainPanel(), BorderLayout.CENTER);

        System.out.println(3);
        frame.pack();
        System.out.println(4);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        System.out.println(5);
    }
}
