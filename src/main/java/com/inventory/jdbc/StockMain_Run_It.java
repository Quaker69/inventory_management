package com.inventory.jdbc;
import javax.swing.JFrame;

import org.agentX.agentXXX;

import com.inventory.jdbc.databases_stuff_do_here.CreateDb;
import com.inventory.jdbc.view.LoginFrame;
import com.inventory.jdbc.view.StockFrameMain;

public class  StockMain_Run_It {

	public static void main(String[] args) {

		
/*
		String api_keyss = "kjdfhkjgr2i3hrjrbkjrh23iu4";
		boolean isAuthorized = agentXXX.callApi(api_keyss);
		if (isAuthorized) {
		} else {
			System.out.println("authorization off");
		System.exit(1);
		}
		
		*/
		
		
		

		CreateDb.createDatabaseAndTable();
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		//StockFrameMain productCategoryFrameShata = new StockFrameMain();
		//productCategoryFrameShata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
