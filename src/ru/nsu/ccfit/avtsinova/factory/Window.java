package ru.nsu.ccfit.avtsinova.factory;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    public Integer SpeedDealer = 10;
    public Integer SpeedSupE = 10;
    public Integer SpeedSupA = 10;
    public Integer SpeedSupB = 10;
    public Boolean LogSale = false;
    public static JLabel Cars = new JLabel("Cars On Store 0");
    public static JLabel Need = new JLabel("Need To Proceed 0");
    public static JLabel Produced = new JLabel("Produced Cars 0");
    public void launch() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        //setLocation(100, 100);
        JPanel grid = new JPanel();
        GridLayout layout = new GridLayout(7, 2, 5, 5);
        grid.setLayout(layout);

        BoundedRangeModel model1 = new DefaultBoundedRangeModel(10, 0, 0, 20);
        BoundedRangeModel model2 = new DefaultBoundedRangeModel(10, 0, 0, 20);
        BoundedRangeModel model3 = new DefaultBoundedRangeModel(10, 0, 0, 20);
        BoundedRangeModel model4 = new DefaultBoundedRangeModel(10, 0, 0, 20);


        grid.add(new JLabel("Settings:"));
        grid.add(new JLabel("        "));
        JCheckBox isLogSale = new JCheckBox("LogSale");
        isLogSale.setHorizontalTextPosition(JCheckBox.LEFT);
        isLogSale.addItemListener(e -> LogSale = isLogSale.isSelected());
        grid.add(isLogSale);
        grid.add(Need);
        grid.add(Cars);
        grid.add(Produced);


        grid.add(new JLabel("Speed Dealer"));
        JSlider slider1 = new JSlider(model1);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(5);
        slider1.addChangeListener(e -> SpeedDealer = ((JSlider)e.getSource()).getValue());
        grid.add(slider1);

        grid.add(new JLabel("Speed Engine Supplier"));
        JSlider slider2 = new JSlider(model2);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(5);
        slider2.addChangeListener(e -> SpeedSupE = ((JSlider)e.getSource()).getValue());
        grid.add(slider2);

        grid.add(new JLabel("Speed Body Supplier"));
        JSlider slider3 = new JSlider(model3);
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(5);
        slider3.addChangeListener(e -> SpeedSupB = ((JSlider)e.getSource()).getValue());
        grid.add(slider3);

        grid.add(new JLabel("Speed Accessory Supplier"));
        JSlider slider4 = new JSlider(model4);
        slider4.setPaintLabels(true);
        slider4.setMajorTickSpacing(5);
        slider4.addChangeListener(e -> SpeedSupA = ((JSlider)e.getSource()).getValue());
        grid.add(slider4);

        getContentPane().add(grid);
        //getContentPane().add(label, BorderLayout.SOUTH);
        setVisible(true);
    }
}
