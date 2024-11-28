package com.melv.jdbc;

import javax.swing.JFrame;

import com.melv.jdbc.databases_stuff_do_here.CreateDb;
import com.melv.jdbc.view.StockFrameMain;

public class StockMain_Run_It {

	public static void main(String[] args) {

		CreateDb.createDatabaseAndTable();
		StockFrameMain produtoCategoriaFrame = new StockFrameMain();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
