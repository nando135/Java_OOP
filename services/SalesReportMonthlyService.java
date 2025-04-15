/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javanexuspots.services;
import javanexuspots.models.Item;

import java.util.*;
import java.io.*;
import javanexuspots.models.SalesEntry;
import javanexuspots.models.salesreport;

public class SalesReportMonthlyService extends AbstractService<salesreport> {

    private static final String REPORT_MONTHLY_PATH = "src/javanexuspots/databases/reportmonthly.txt";
    private SalesEntryService salesEntryService;

    public SalesReportMonthlyService() {
        super(REPORT_MONTHLY_PATH);
        this.salesEntryService = new SalesEntryService();
    }

    @Override
    public List<salesreport> loadData() {
        List<String> lines = readFile();
        List<salesreport> reports = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) { // Ensure correct format
                try {
                    salesreport report = new salesreport(
                            parts[0], // entryId
                            parts[1], // itemId
                            Integer.parseInt(parts[2]), // salesDateMonth
                            Integer.parseInt(parts[3]), // salesDateYear
                            Integer.parseInt(parts[4]), // quantitySold
                            Double.parseDouble(parts[5]) // totalSales
                    );
                    reports.add(report);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid report entry: " + line);
                }
            }
        }
        return reports;
    }

    @Override
    public void saveData(List<salesreport> reports) {
        List<String> lines = new ArrayList<>();
        for (salesreport report : reports) {
            lines.add(String.join(",",
                    report.getEntryId(),
                    report.getItemId(),
                    String.valueOf(report.getSalesDateMonth()),
                    String.valueOf(report.getSalesDateYear()),
                    String.valueOf(report.getQuantitySold()),
                    String.format("%.2f", report.getTotalSales())
            ));
        }
        writeFile(lines);
    }

    // Generate Monthly Report
    public double generateMonthlyReport(int month, int year) {
        List<SalesEntry> salesEntries = salesEntryService.getAllSalesEntries();
        List<salesreport> reports = new ArrayList<>();
        double totalMonthlySales = 0.0;

        for (SalesEntry entry : salesEntries) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(entry.getSalesDate());

            if (cal.get(Calendar.MONTH) + 1 == month && cal.get(Calendar.YEAR) == year) {
                double price = getItemPrice(entry.getItemId());
                System.out.println(price);
                double totalEntrySales = price * entry.getQuantitySold();
                System.out.println(totalEntrySales);
                totalMonthlySales += totalEntrySales;
                
                salesreport report = new salesreport(
                        entry.getEntryId(),
                        entry.getItemId(),
                        month,
                        year,
                        entry.getQuantitySold(),
                        price
                );
                reports.add(report);
            }
        }

        // Save the report to file
        saveData(reports);
        System.out.println("Monthly report generated successfully. Total Sales: " + totalMonthlySales);
        return totalMonthlySales;
    }

private double getItemPrice(String itemId) {
    ItemService inventoryService = new ItemService();
    List<Item> items = inventoryService.loadData();

    for (Item item : items) {
        if (item.getItemId().equals(itemId)) {
            return item.getPrice();
        }
    }

    System.err.println("Warning: Item ID " + itemId + " not found in inventory.");
    return 0.0; // Default price if not found
    }
}