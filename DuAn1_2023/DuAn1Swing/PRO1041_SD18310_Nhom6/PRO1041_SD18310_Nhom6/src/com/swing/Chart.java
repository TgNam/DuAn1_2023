package com.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {

    public static DefaultCategoryDataset createDataset(Double value1, Double value2, Double value3, Double value4, Double value5, Double value6,
            Double value7, Double value8, Double value9, Double value10, Double value11, Double value12) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(value1, "GDP", "1");
        dataset.addValue(value2, "GDP", "2");
        dataset.addValue(value3, "GDP", "3");
        dataset.addValue(value4, "GDP", "4");
        dataset.addValue(value5, "GDP", "5");
        dataset.addValue(value6, "GDP", "6");
        dataset.addValue(value7, "GDP", "7");
        dataset.addValue(value8, "GDP", "8");
        dataset.addValue(value9, "GDP", "9");
        dataset.addValue(value10, "GDP", "10");
        dataset.addValue(value11, "GDP", "11");
        dataset.addValue(value12, "GDP", "12");
        return dataset;

    }

    public static JFreeChart createLineChart(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu".toUpperCase(),
                "Tháng", "VND", dataset,
                PlotOrientation.VERTICAL, false, false, false);
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        // Thiết lập màu nền của Plot
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setOutlineVisible(false);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.red); // Màu của đường line (0 là index của dòng)
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesFilled(0, false);

        return lineChart;
    }

}
