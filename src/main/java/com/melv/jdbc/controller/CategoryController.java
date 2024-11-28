package com.melv.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {

	public List<?> listar() {
        List<String> categories = new ArrayList<>();
        categories.add("Large-cap stocks");
        categories.add("Mid-cap stocks");
        categories.add("Small-cap stocks");
        categories.add("Sector stocks");
        categories.add("Domestic stocks");
        categories.add("International stocks");
        return categories;

	}

    public List<?> createReportss() {
        // TODO
        List<String> report = new ArrayList<>();
        report.add("Category Report: ");
        report.add("1. Large-cap stocks: 20 products");
        report.add("2. Mid-cap stocks: 15 products");
        report.add("3. Small-cap stocks: 30 products");
        report.add("4. Sector stocks: 10 products");
        report.add("5. Domestic stocks: 40 products");
        report.add("6. International stocks: 25 products");
        return report;
    }

}
