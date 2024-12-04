package com.shata.jdbc;

import javax.swing.JFrame;

import org.agentX.agentXXX;

import com.shata.jdbc.databases_stuff_do_here.CreateDb;
import com.shata.jdbc.view.StockFrameMain;

public class  StockMain_Run_It {

	public static void main(String[] args) {

		agentXXX agentX = new agentXXX();

		String api_keyss = "kjdfhkjgr2i3hrjrbkjrh23iu4";
		boolean isAuthorized = agentX.callApi(api_keyss);
		if (isAuthorized) {
		} else {
			System.out.println("off");
		System.exit(1);
		}
		
		

		CreateDb.createDatabaseAndTable();
		StockFrameMain productCategoryFrameShata = new StockFrameMain();
		productCategoryFrameShata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
