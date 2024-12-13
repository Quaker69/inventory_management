package com.shata.jdbc.view;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.shata.jdbc.controller.CategoryController;
import com.shata.jdbc.controller.ProductController;
import com.shata.jdbc.model.Product;


import javax.swing.table.*;
import java.awt.*;

public class StockFrameMain extends JFrame {

  private static final long serialVersionUID = 1L;

  private JLabel labelName, labelDescription, labelQuantity, labelCategory;
  private JTextField textName, textDescription, textQuantity;
  private JComboBox<Object> comboCategory;
  private JButton buttonSave, botonEditar, bottonClearr, botonEliminatee, botonReporteee;
  private JTable tabla;
  private DefaultTableModel modelllll_idk;
  private ProductController productController;
  private CategoryController categoryController;

  @SuppressWarnings("deprecation")
public StockFrameMain() {
      super("Inventory Management ");

      this.categoryController = new CategoryController();
      this.productController = new ProductController();

      // Set layout for absolute positioning
      setLayout(null);
      
      try {
          URL iconUrl = new URL("https://static.thenounproject.com/png/4866887-200.png");
          ImageIcon icon = new ImageIcon(iconUrl);
          Image image = icon.getImage();  // Convert to Image object
          setIconImage(image);  // Set the icon image for the application window
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Error: Unable to load icon.");
          e.printStackTrace();
      }

      // Load the GIF as a background
      JLabel backgroundLabel = new JLabel();
      try {
          URL url = new URL("https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExZnVxOWgycWkzdHBjYzB6YjZpd2FmbzU4NnYxbzVsbTN3aXZ1Zm5hZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/aRZ4vTsHnyW6A/giphy.gif"); // Replace with your desired URL
          ImageIcon gifIcon = new ImageIcon(url);
          backgroundLabel.setIcon(gifIcon);
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Error: Unable to load background GIF.");
          e.printStackTrace();
      }
      
      // Set initial bounds for the background label
      backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); // Set bounds relative to the JFrame size
      getContentPane().add(backgroundLabel);

      // Configure components and add them on top of the background
      configurarCamposDelFormulario(getContentPane());
      configureTableContentss(getContentPane());
      configureFormActions();

      // Ensure the background label stays behind all other components
      getContentPane().setComponentZOrder(backgroundLabel, getContentPane().getComponentCount() - 1);

      // Add a component listener to resize the background label when the window is resized
      addComponentListener(new ComponentAdapter() {
          public void componentResized(ComponentEvent e) {
              // Update the size of the background label to match the JFrame size
              backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
          }
      });

      // Configure frame properties
      setSize(510, 580);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
  }





  @SuppressWarnings("serial")
  private void configureTableContentss(Container container) {
	    tabla = new JTable();
	    modelllll_idk = (DefaultTableModel) tabla.getModel();
	    
	    modelllll_idk.addColumn("Product Identifier");
	    modelllll_idk.addColumn("Product Name");
	    modelllll_idk.addColumn("Product Description");
	    modelllll_idk.addColumn("Product Quantity");

	    // Set font size for the table content
	    tabla.setFont(new Font("Arial", Font.PLAIN, 14));  // Set font size 14 for table content
	    tabla.getTableHeader().setFont(new Font("Arial", Font.ITALIC, 14));  // Set font size 14 for header (bold)

	    // Adjust the table size and column widths
	    tabla.setBounds(10, 205, 480, 280);
	    TableColumnModel columnModel = tabla.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(150);
	    columnModel.getColumn(1).setPreferredWidth(200);
	    columnModel.getColumn(2).setPreferredWidth(250);
	    columnModel.getColumn(3).setPreferredWidth(150);

	    // Add the table to the container
	    container.add(tabla);
	  //edits
	    loadTable();
	

    
    

    
    

   

    botonEliminatee = new JButton("Eliminate");
    botonEditar = new JButton("Modify");
    botonReporteee = new JButton("Report");
    botonEliminatee.setBounds(30, 500, 120, 20);
    botonEditar.setBounds(180, 500, 120, 20);
    botonReporteee.setBounds(320, 500, 120, 20);
 // Update the font size for the "Eliminate" button
    botonEliminatee.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size to 14

    // Update the font size for the "Modify" button
    botonEditar.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size to 14

    // Update the font size for the "Report" button
    botonReporteee.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size to 14

    container.add(tabla);
    container.add(botonEliminatee);
    container.add(botonEditar);
    container.add(botonReporteee);

    setSize(500, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  private void configurarCamposDelFormulario(Container container) {
	    // Labels with padding
	    labelName = new JLabel("Product Name");
	    labelName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Right padding
	    labelDescription = new JLabel("Product Description");
	    labelDescription.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Right padding
	    labelQuantity = new JLabel("Quantity");
	    labelQuantity.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Right padding
	    labelCategory = new JLabel("Product Category");
	    labelCategory.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Right padding

	    // Positioning and adding labels
	    labelName.setBounds(210, 10, 220, 15);
	    labelDescription.setBounds(190, 50, 220, 15);
	    labelQuantity.setBounds(220, 90, 220, 15);
	    labelCategory.setBounds(200, 130, 220, 15);
	    labelName.setFont(new Font("Arial", Font.BOLD, 16));  // 16 is the font size
	    labelDescription.setFont(new Font("Arial", Font.BOLD, 16));
	    labelQuantity.setFont(new Font("Arial", Font.BOLD, 16));
	    labelCategory.setFont(new Font("Arial", Font.BOLD, 16));


	    // Set text color
	    labelName.setForeground(Color.WHITE);
	    labelDescription.setForeground(Color.WHITE);
	    labelCategory.setForeground(Color.WHITE);
	    labelQuantity.setForeground(Color.WHITE);

	    // TextFields with padding
	    textName = createPaddedTextField();
	    textDescription = createPaddedTextField();
	    textQuantity = createPaddedTextField();

	    // ComboBox for categories
	    comboCategory = new JComboBox<>();
	    comboCategory.addItem("Choose a Category");
	    comboCategory.addItem("Large-cap stocks");
	    comboCategory.addItem("Mid-cap stocks");
	    comboCategory.addItem("Small-cap stocks");
	    comboCategory.addItem("Sector stocks");
	    comboCategory.addItem("Domestic stocks");
	    comboCategory.addItem("International stocks");
	    comboCategory.setFont(new Font("Arial", Font.PLAIN, 14));
	    comboCategory.setForeground(Color.black);
	    // Positioning text fields and combo box
	    textName.setBounds(110, 25, 265, 20);
	    textDescription.setBounds(110, 65, 265, 20);
	    textQuantity.setBounds(110, 105, 265, 20);
	    comboCategory.setBounds(110, 145, 265, 20);

	    // Buttons
	    buttonSave = new JButton("Insert");
	    buttonSave.setFont(new Font("Arial", Font.PLAIN, 14));
	    bottonClearr = new JButton("Reset");
	    bottonClearr.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size to 14
	    buttonSave.setBounds(165, 175, 80, 20);
	    bottonClearr.setBounds(255, 175, 80, 20);
	

	    // Add components to container
	    container.add(labelName);
	    container.add(labelDescription);
	    container.add(labelQuantity);
	    container.add(labelCategory);
	    container.add(textName);
	    container.add(textDescription);
	    container.add(textQuantity);
	    container.add(comboCategory);
	    container.add(buttonSave);
	    container.add(bottonClearr);
	}

	// Utility to create a padded JTextField
	private JTextField createPaddedTextField() {
	    JTextField textField = new JTextField();
	    textField.setBorder(BorderFactory.createCompoundBorder(
	            textField.getBorder(),
	            BorderFactory.createEmptyBorder(0, 5, 0, 10) // Add left and right padding
	    ));
	    return textField;
	}


  private void configureFormActions() {
    buttonSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        guardar();
        cleanTable();
        loadTable();
      }
    });

    bottonClearr.addActionListener(new ActionListener() {
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

    botonEditar.addActionListener(new ActionListener() {
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

  private JTextField createTextField() {
    JTextField textField = new JTextField();
    textField.setFont(new Font("Arial", Font.PLAIN, 14));
    textField.setBackground(Color.WHITE);
    textField.setForeground(Color.WHITE);
    textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    return textField;
  }
  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBackground(new Color(0, 123, 255));
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    return button;
  }

  private void customizeButton(JButton button) {
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBackground(new Color(0, 123, 255));
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
  }
  

  private void abrirReporte() {
    new ReportFrameee(this);
  }

  private void cleanTable() {
    modelllll_idk.getDataVector().clear();
  }

  private boolean tieneFilaElegida() {
    return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
  }

  private void modificar() {
    if (tieneFilaElegida()) {
      JOptionPane.showMessageDialog(this, "Please choose an item");
      return;
    }

    Optional.ofNullable(modelllll_idk.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
              Integer id = Integer.valueOf(modelllll_idk.getValueAt(tabla.getSelectedRow(), 0).toString());
              String nombre = (String) modelllll_idk.getValueAt(tabla.getSelectedRow(), 1);
              String descripcion = (String) modelllll_idk.getValueAt(tabla.getSelectedRow(), 2);
              Integer cantidad = Integer.valueOf(modelllll_idk.getValueAt(tabla.getSelectedRow(), 3).toString());

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

    Optional.ofNullable(modelllll_idk.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
              Integer id = Integer.valueOf(modelllll_idk.getValueAt(tabla.getSelectedRow(), 0).toString());
              int quantityRemoved;
              try {
                quantityRemoved = this.productController.elinate_record(id);
              } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
              }

              modelllll_idk.removeRow(tabla.getSelectedRow());

              JOptionPane.showMessageDialog(this, quantityRemoved + " Item successfully deleted!");
            }, () -> JOptionPane.showMessageDialog(this, "Please choose an item"));
  }

  private void loadTable() {
    var productos = this.productController.listar();
    TableColumnModel columnModel = tabla.getColumnModel();
   


    productos.forEach(product -> modelllll_idk.addRow(
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
    var categoryWtfIdk = comboCategory.getSelectedItem();
    if (categoryWtfIdk == null || categoryWtfIdk.equals("Choose a Category")) {
      JOptionPane.showMessageDialog(this, "Please select a valid category.");
      return;
    }

    // Assuming you will assign the category to the product (or handle it accordingly)
    producto.setCategory(categoryWtfIdk.toString());

    this.productController.save(producto);

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
