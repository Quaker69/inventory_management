package com.melv.jdbc.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.melv.jdbc.controller.CategoryController;
import com.melv.jdbc.controller.ProductController;
import com.melv.jdbc.model.Product;

public class StockFrameMain extends JFrame {

  private static final long serialVersionUID = 1L;

  private JLabel labelName, labelDescription, labelQuantity, labelCategory;
  private JTextField textName, textDescription, textQuantity;
  private JComboBox<Object> comboCategory;
  private JButton buttonSave, buttonEdit, buttonClear, botonEliminatee, botonReporteee;
  private JTable tabla;
  private DefaultTableModel modelo;
  private ProductController productController;
  private CategoryController categoryController;

  public StockFrameMain() {
    super("Product");

    this.categoryController = new CategoryController();
    this.productController = new ProductController();

    Container container = getContentPane();
    setLayout(null);

    configurarCamposDelFormulario(container);
    configureTableContentss(container);
    configureFormActions();
  }

  private void configureTableContentss(Container container) {
    tabla = new JTable();

    modelo = (DefaultTableModel) tabla.getModel();
    modelo.addColumn("Product Identifier");
    modelo.addColumn("Product Name");
    modelo.addColumn("Product Description");
    modelo.addColumn("Product Quantity");

    loadTable();

    tabla.setBounds(10, 205, 760, 280);

    botonEliminatee = new JButton("Eliminate");
    buttonEdit = new JButton("Modify");
    botonReporteee = new JButton("Report");
    botonEliminatee.setBounds(10, 500, 80, 20);
    buttonEdit.setBounds(100, 500, 80, 20);
    botonReporteee.setBounds(190, 500, 80, 20);

    container.add(tabla);
    container.add(botonEliminatee);
    container.add(buttonEdit);
    container.add(botonReporteee);

    setSize(800, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  private void configurarCamposDelFormulario(Container container) {
    labelName = new JLabel("Product Name");
    labelDescription = new JLabel("Product Description");
    labelQuantity = new JLabel("Quantity");
    labelCategory = new JLabel("Product Category");

    labelName.setBounds(10, 10, 240, 15);
    labelDescription.setBounds(10, 50, 240, 15);
    labelQuantity.setBounds(10, 90, 240, 15);
    labelCategory.setBounds(10, 130, 240, 15);

    labelName.setForeground(Color.BLACK);
    labelDescription.setForeground(Color.BLACK);
    labelCategory.setForeground(Color.BLACK);

    textName = new JTextField();
    textDescription = new JTextField();
    textQuantity = new JTextField();
    comboCategory = new JComboBox<>();

    // Add predefined categories to the ComboBox
    comboCategory.addItem("Choose a Category");
    comboCategory.addItem("Large-cap stocks");
    comboCategory.addItem("Mid-cap stocks");
    comboCategory.addItem("Small-cap stocks");
    comboCategory.addItem("Sector stocks");
    comboCategory.addItem("Domestic stocks");
    comboCategory.addItem("International stocks");

    textName.setBounds(10, 25, 265, 20);
    textDescription.setBounds(10, 65, 265, 20);
    textQuantity.setBounds(10, 105, 265, 20);
    comboCategory.setBounds(10, 145, 265, 20);

    buttonSave = new JButton("Insert");
    buttonClear = new JButton("Reset");
    buttonSave.setBounds(10, 175, 80, 20);
    buttonClear.setBounds(100, 175, 80, 20);

    container.add(labelName);
    container.add(labelDescription);
    container.add(labelQuantity);
    container.add(labelCategory);
    container.add(textName);
    container.add(textDescription);
    container.add(textQuantity);
    container.add(comboCategory);
    container.add(buttonSave);
    container.add(buttonClear);
  }

  private void configureFormActions() {
    buttonSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        guardar();
        cleanTable();
        loadTable();
      }
    });

    buttonClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cleanForm();
      }
    });

    botonEliminatee.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        eliminar();
        cleanTable();
        loadTable();
      }
    });

    buttonEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modificar();
        cleanTable();
        loadTable();
      }
    });

    botonReporteee.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        abrirReporte();
      }
    });
  }

  private void abrirReporte() {
    new ReportFrameee(this);
  }

  private void cleanTable() {
    modelo.getDataVector().clear();
  }

  private boolean tieneFilaElegida() {
    return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
  }

  private void modificar() {
    if (tieneFilaElegida()) {
      JOptionPane.showMessageDialog(this, "Please choose an item");
      return;
    }

    Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
              Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
              String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 1);
              String descripcion = (String) modelo.getValueAt(tabla.getSelectedRow(), 2);
              Integer cantidad = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());

              int filasModificadas;
              filasModificadas = this.productController.modifcationss(nombre, descripcion, cantidad, id);
              JOptionPane.showMessageDialog(this, String.format("%d item successfully modified!", filasModificadas));
            }, () -> JOptionPane.showMessageDialog(this, "Please choose an item"));
  }

  private void eliminar() {
    if (tieneFilaElegida()) {
      JOptionPane.showMessageDialog(this, "Please choose an item");
      return;
    }

    Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
              Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
              int quantityRemoved;
              try {
                quantityRemoved = this.productController.elinate_record(id);
              } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
              }

              modelo.removeRow(tabla.getSelectedRow());

              JOptionPane.showMessageDialog(this, quantityRemoved + " Item successfully deleted!");
            }, () -> JOptionPane.showMessageDialog(this, "Please choose an item"));
  }

  private void loadTable() {
    var productos = this.productController.listar();
    productos.forEach(product -> modelo.addRow(
            new Object[] { product.getId(), product.getName(), product.getDescription(), product.getQuatityy() }));
  }

  private void guardar() {
    if (textName.getText().isBlank() || textDescription.getText().isBlank()) {
      JOptionPane.showMessageDialog(this, "The Name and Description fields are required.");
      return;
    }

    Integer cantidadInt;

    try {
      cantidadInt = Integer.parseInt(textQuantity.getText());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, String
              .format("The quantity field must be numeric within the range %d y %d.", 0, Integer.MAX_VALUE));
      return;
    }

    var producto = new Product(textName.getText(), textDescription.getText(), cantidadInt);

    // Handle selected category
    var categoria = comboCategory.getSelectedItem();
    if (categoria == null || categoria.equals("Choose a Category")) {
      JOptionPane.showMessageDialog(this, "Please select a valid category.");
      return;
    }

    // Assuming you will assign the category to the product (or handle it accordingly)
    producto.setCategory(categoria.toString());

    this.productController.guardar(producto);

    JOptionPane.showMessageDialog(this, "Successfully registered!");

    this.cleanForm();
  }

  private void cleanForm() {
    this.textName.setText("");
    this.textDescription.setText("");
    this.textQuantity.setText("");
    this.comboCategory.setSelectedIndex(0);
  }
}
