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
import com.melv.jdbc.controller.ProductoController;
import com.melv.jdbc.model.Producto;

public class ControlDeStockFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private JLabel labelName, labelDescription, labelQuantity, labelCategory;
  private JTextField textName, textDescription, textQuantity;
  private JComboBox<Object> comboCategory;
  private JButton buttonSave, buttonEdit, buttonClear, botonEliminar, botonReporte;
  private JTable tabla;
  private DefaultTableModel modelo;
  private ProductoController productController;
  private CategoryController categoryController;

  public ControlDeStockFrame() {
    super("product");

    this.categoryController = new CategoryController();
    this.productController = new ProductoController();

    Container container = getContentPane();
    setLayout(null);

    configurarCamposDelFormulario(container);

    configurarTablaDeContenido(container);

    configurarAccionesDelFormulario();
  }

  private void configurarTablaDeContenido(Container container) {
    tabla = new JTable();

    modelo = (DefaultTableModel) tabla.getModel();
    modelo.addColumn("Product Identifier");
    modelo.addColumn("Product Name");
    modelo.addColumn("Product Description");
    modelo.addColumn("Product quantity");

    loadTable();

    tabla.setBounds(10, 205, 760, 280);

    botonEliminar = new JButton("Eliminate");
    buttonEdit = new JButton("Modify");
    botonReporte = new JButton("Report");
    botonEliminar.setBounds(10, 500, 80, 20);
    buttonEdit.setBounds(100, 500, 80, 20);
    botonReporte.setBounds(190, 500, 80, 20);

    container.add(tabla);
    container.add(botonEliminar);
    container.add(buttonEdit);
    container.add(botonReporte);

    setSize(800, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  private void configurarCamposDelFormulario(Container container) {
    labelName = new JLabel("Product Name");
    labelDescription = new JLabel("Product Description");
    labelQuantity = new JLabel("Quantity");
    labelCategory = new JLabel("Product category");

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
    comboCategory.addItem("Choose a Category");

    // TODO
    var categorias = this.categoryController.listar();
    // categorias.forEach(categoria -> comboCategoria.addItem(categoria));

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

  private void configurarAccionesDelFormulario() {
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

    botonEliminar.addActionListener(new ActionListener() {
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

    botonReporte.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        abrirReporte();
      }
    });
  }

  private void abrirReporte() {
    new ReporteFrame(this);
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
          filasModificadas = this.productController.modificar(nombre, descripcion, cantidad, id);
          JOptionPane.showMessageDialog(this, String.format("%d item successfully modified!", filasModificadas));
        }, () -> JOptionPane.showMessageDialog(this, "Please choose an item"));
  }

  private void eliminar() {
    if (tieneFilaElegida()) {
      JOptionPane.showMessageDialog(this, "Successfully registered!");
      return;
    }

    Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
          Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
          int quantityRemoved;
          try {
            quantityRemoved = this.productController.eliminar(id);
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
    productos.forEach(producto -> modelo.addRow(
        new Object[] { producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCantidad() }));
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

    var producto = new Producto(textName.getText(), textDescription.getText(), cantidadInt);

    var categoria = comboCategory.getSelectedItem();

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
