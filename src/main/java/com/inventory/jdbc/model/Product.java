package com.inventory.jdbc.model;

public class Product {

	

		private Integer id;
		
		private String descripcion;
		
		private String nombre;

		private Integer cantidad;
		
		public Product(String nombre, String descripcion, Integer cantidad) {
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.cantidad = cantidad;
	}
		
		public Product(int id, String nombre, String descripcion, int cantidad) {
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.cantidad = cantidad;
		}

		public String getDescription() {
			return descripcion;
		}

		public String getName() {
			return nombre;
		}

		public Integer getQuatityy() {
			return cantidad;
		}

		public void setId(int id) {
			this.id = id;
			
		}
		public Object getId() {
			return this.id;
		}
		
		
		@Override
		public String toString() {
		return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %d}", this.id, this.nombre, this.descripcion, this.cantidad);
		}


	public void setCategory(String string) {
		
	}
}
