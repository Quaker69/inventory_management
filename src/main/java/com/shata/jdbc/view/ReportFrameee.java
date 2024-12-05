package com.shata.jdbc.view;

import java.awt.Container;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.shata.jdbc.controller.CategoryController;
import com.shata.jdbc.controller.ProductController;
import com.shata.jdbc.model.Product;

public class ReportFrameee extends JFrame {

    private static final long serialVersionUID = 1L;

    

    public ReportFrameee(StockFrameMain stockFrameMain) {
    	super("Stock Product Report");

        // Get the products from the ProductController
        ProductController productController = new ProductController();
        List<Product> products = productController.listar(); // Assuming 'listar()' returns a list of products

        // Create the dataset for the bar chart
        DefaultCategoryDataset dataset = createDataset(products);

        // Create the bar chart
        JFreeChart barChart = createChart(dataset);

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
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Stock Product Report",   // Chart title
                "Product",                // X-axis label
                "Stock Quantity",         // Y-axis label
                dataset,                  // Dataset
                org.jfree.chart.plot.PlotOrientation.VERTICAL, 
                true,                     // Show legend
                true,                     // Show tooltips
                false                     // No URLs
        );
    }
    
  

    

}
