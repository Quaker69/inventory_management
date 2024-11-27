package com.melv.jdbc.model;

public class Producto {

	

		private Integer id;
		
		private String descripcion;
		
		private String nombre;

		private Integer cantidad;
		
		public Producto(String nombre, String descripcion, Integer cantidad) {
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.cantidad = cantidad;
	}
		
		public Producto(int id, String nombre, String descripcion, int cantidad) {
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.cantidad = cantidad;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public String getNombre() {
			return nombre;
		}

		public Integer getCantidad() {
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

	
		
}
