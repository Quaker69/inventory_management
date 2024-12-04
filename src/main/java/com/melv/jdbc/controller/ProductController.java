package com.melv.jdbc.controller;

import java.sql.SQLException;
import java.util.List;

import com.melv.jdbc.dao.Product2something;
import com.melv.jdbc.databases_stuff_do_here.ConnectionFactory;
import com.melv.jdbc.model.Product;

public class ProductController {

	private Product2something product2something;
	public ProductController() {
		var factory = new ConnectionFactory();
		this.product2something = new Product2something(factory.recoverConnection());
	}

	public int modifcationss(String nombre, String descripcion, Integer cantidad, Integer id) {
		return product2something.modificationsssss(nombre, descripcion, cantidad, id);
	}

	public int elinate_record(Integer id) throws SQLException {
		return product2something.elemniateeee(id);
		
	}

	public List<Product> listar() {
		return product2something.listar();
	}


    public void save(Product product) {
		
		product2something.keep_it(product);
    }

	

}
