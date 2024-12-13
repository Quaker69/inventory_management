package com.inventory.jdbc.view;

import java.awt.Color;
import java.awt.Container;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.inventory.jdbc.controller.ProductController;
import com.inventory.jdbc.model.Product;

public class ReportFrameee extends JFrame {

    private static final long serialVersionUID = 1L;

    // Constructor to accept the list of products
    public ReportFrameee(StockFrameMain stockFrameMain) {
        super("Stock Product Report");

        // Get the products from the ProductController
        ProductController productController = new ProductController();
        List<Product> products = productController.listar(); // Assuming 'listar()' returns a list of products

        // Create the dataset for the bar chart
        DefaultCategoryDataset dataset = createDataset(products);

        // Create the bar chart
        JFreeChart barChart = createChart(dataset);

        // Customize bar colors
        customizeBarColors(barChart, products.size());

        // Create a chart panel and add it to the content pane
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        Container contentPane = getContentPane();
        contentPane.add(chartPanel);

        // Frame settings
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(stockFrameMain);
    }

    // Method to create the dataset for the chart
    private DefaultCategoryDataset createDataset(List<Product> products) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Loop through each product and add it to the dataset
        for (Product product : products) {
            String productName = product.getName(); // Get the product name
            Integer productQuantity = product.getQuatityy(); // Get the product quantity

            // Add each product to the dataset (y-axis = quantity, x-axis = product name)
            dataset.addValue(productQuantity, "Stocks", productName);
        }

        return dataset;
    }

    // Method to create the bar chart
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Stock Product Report",   // Chart title
                "Product",                // X-axis label
                "Stock Quantity",         // Y-axis label
                dataset,                  // Dataset
                PlotOrientation.VERTICAL, 
                true,                     // Show legend
                true,                     // Show tooltips
                false                     // No URLs
        );
    }

    // Method to customize the bar colors
    private void customizeBarColors(JFreeChart barChart, int numberOfBars) {
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Define a set of colors for the bars
        Color[] barColors = new Color[] {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.CYAN,
            Color.MAGENTA, Color.PINK, Color.YELLOW, Color.GRAY, Color.LIGHT_GRAY
        };

        // Loop through each bar and set a different color
        for (int i = 0; i < numberOfBars; i++) {
            renderer.setSeriesPaint(i, barColors[i % barColors.length]);
        }
    }
}
