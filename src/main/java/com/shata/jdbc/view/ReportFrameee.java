package com.shata.jdbc.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.shata.jdbc.controller.CategoryController;

public class ReportFrameee extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaReporte;
    private DefaultTableModel modelo;

    private CategoryController categoryyController;

    public ReportFrameee(StockFrameMain stockFrameMain) {
        super("Stock Product Report");

        this.categoryyController = new CategoryController();

        Container container = getContentPane();
        setLayout(null);

        tablaReporte = new JTable();
        tablaReporte.setBounds(0, 0, 600, 400);
        container.add(tablaReporte);

        modelo = (DefaultTableModel) tablaReporte.getModel();
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");

        CreateReport();

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(stockFrameMain);
    }

    private void CreateReport() {
        var contenttt = categoryyController.createReportss();
        
        // TODO
        contenttt.forEach(fila -> modelo
                .addRow(new Object[] {}));
    }

}
