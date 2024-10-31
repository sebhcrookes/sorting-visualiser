package com.sebhcrookes.sorting;

import javax.swing.*;

public class SortVisualiser {

    private JFrame window;
    private ArrayPanel arrayPanel;

    public SortVisualiser() {
        this.window = new JFrame("Sorting Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);

        /* Set up main object (array panel) */
        arrayPanel = new ArrayPanel();
        window.add(arrayPanel);
        arrayPanel.repaint();

        window.pack();
        window.setVisible(true);
    }
}
