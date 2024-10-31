package com.sebhcrookes.sorting;

import com.sebhcrookes.sorting.algorithms.*;

import javax.swing.*;
import java.awt.*;

public class ArrayPanel extends JPanel {

    private static boolean RUNNING = false;

    private SortArray sortArray;

    private JButton startButton;
    private JButton stopButton;
    private JComboBox sortingAlgorithms;

    private ISortingAlgorithm algorithm;

    private Thread thr;

    public void Do() {
        thr = Thread.currentThread();
        ArrayPanel.RUNNING = true;

        sortArray.shuffle();

        // Sleep for 1000ms
        try {
            Thread.sleep(1000);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Run the algorithm
        start(algorithm);

        // Sleep for another 100ms
        try {
            Thread.sleep(1000);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        ArrayPanel.RUNNING = false;

        // The new version of the visualiser repeats the sorting algorithm
        new Thread(() -> {
            Do();
        }).start();
    }

    public ArrayPanel() {
        // SortArray is a class which stores the bars/values and handles swapping and shuffling of the array
        sortArray = new SortArray(this, Settings.NUM_BARS);

        this.setBackground(new Color(24,25,26));

        /* Create the dropdown box to allow for algorithm selection */
        sortingAlgorithms = new JComboBox(new String[] {"QuickSort", "BubbleSort", "InsertionSort", "SelectionSort"});
        sortingAlgorithms.setBackground(Color.GRAY);
        sortingAlgorithms.setForeground(Color.WHITE);
        sortingAlgorithms.addActionListener(e -> {
            switch(String.valueOf(sortingAlgorithms.getSelectedItem())) {
                case "QuickSort":
                    algorithm = new QuickSort();
                    break;
                case "BubbleSort":
                    algorithm = new BubbleSort();
                    break;
                case "InsertionSort":
                    algorithm = new InsertionSort();
                    break;
                case "SelectionSort":
                    algorithm = new SelectionSort();
                    break;
            }
            repaint();
        });

        sortingAlgorithms.setSelectedIndex(0);
        this.add(sortingAlgorithms);

        /* Create the buttons to start/stop the algorithm */

        startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setBackground(Color.GRAY);
        startButton.setForeground(Color.WHITE);

        startButton.addActionListener(e -> new Thread(() -> {
            if(!ArrayPanel.RUNNING) {
                // Disable the start button and the dropdown
                startButton.setEnabled(false);
                sortingAlgorithms.setEnabled(false);
                Do();
            }
        }).start());

        this.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setFocusPainted(false);
        stopButton.setBorderPainted(false);
        stopButton.setBackground(Color.GRAY);
        stopButton.setForeground(Color.WHITE);

        stopButton.addActionListener(e -> new Thread(() -> {
            // Kill any running threads
            while(!ArrayPanel.RUNNING) {}
            if(thr != null) thr.stop();

            // Re-enable the start button and the dropdown
            startButton.setEnabled(true);
            sortingAlgorithms.setEnabled(true);
            ArrayPanel.RUNNING = false;
        }).start());

        this.add(stopButton);
    }

    public void start(ISortingAlgorithm sort) {
        sort.runSort(sortArray);
        sortArray.check();
        sortArray.resetColours();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paintComponent(graphics);

        graphics.setColor(Settings.BAR_COLOR);

        for(int i = 0; i < Settings.NUM_BARS; i++) {
            // Calculating the size and position of the bar
            int height = sortArray.getValue(i) * 2;
            int xBegin = i + (Settings.BAR_WIDTH - 1) * i;
            int yBegin = Settings.WINDOW_HEIGHT - height;

            // Drawing the bar
            int val = sortArray.getColour(i) * 2;
            graphics.setColor(new Color(230, 230 - val, 230 - val));
            graphics.fillRect(xBegin, yBegin, Settings.BAR_WIDTH - 1, height);

            // Fade out the recently coloured bars
            if(sortArray.getColour(i) > 0) {
                sortArray.setColour(i, sortArray.getColour(i) - 5);
            }
        }

        graphics.setColor(Color.RED);

        // Display the string telling the user which algorithm is selected and whether or not it is running
        FontMetrics metric = g.getFontMetrics(g.getFont());
        g.drawString(algorithm.getName() + " - " + (RUNNING ? "Running" : "Not Running"), 1, 1 + metric.getAscent() - metric.getDescent() - metric.getLeading());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }
}