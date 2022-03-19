package com.ttg.pvbinance;

import com.ttg.pvbinance.gui.CurrencyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private Component createAccount() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel apiFieldLabel = new JLabel("API key");
        JTextField apiField = new JTextField();
        JLabel secretFieldLabel = new JLabel("Secret key");
        JTextField secretField = new JTextField();

        JButton logInButton = new JButton("Log in");
        logInButton.addActionListener(e -> {
            System.out.println("'Log in' button pressed");
        });

        return panel;
    }

    private ArrayList<Component> createBrowseContents() {
        ArrayList<BinanceAPI.CryptoPriceInfo> data = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            data.add(new BinanceAPI.CryptoPriceInfo("AAA", (float)Math.random()*1000));
        }

        ArrayList<Component> components = new ArrayList<>();
        data.forEach(cryptoPriceInfo -> {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel(cryptoPriceInfo.name), BorderLayout.LINE_START);

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(e -> new CurrencyGUI(cryptoPriceInfo));

            JPanel rightSidePanel = new JPanel(new BorderLayout(8, 0));
            rightSidePanel.add(new JLabel(Float.toString(cryptoPriceInfo.price)), BorderLayout.LINE_START);
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
