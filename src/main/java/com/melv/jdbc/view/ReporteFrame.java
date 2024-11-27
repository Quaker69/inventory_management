package com.melv.jdbc.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.melv.jdbc.controller.CategoryController;

public class ReporteFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaReporte;
    private DefaultTableModel modelo;

    private CategoryController categoriaController;

    public ReporteFrame(ControlDeStockFrame controlDeStockFrame) {
        super("Stock Product Report");

        this.categoriaController = new CategoryController();

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

        cargaReporte();

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(controlDeStockFrame);
    }

    private void cargaReporte() {
        var contenido = categoriaController.cargaReporte();
        
        // TODO
        contenido.forEach(fila -> modelo
                .addRow(new Object[] {}));
    }

}
