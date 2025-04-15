/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javanexuspots.models;

/**
 *
 * @author fernandosalim
 */
public class salesreport {
    // Attributes
    private String entryId;
    private String itemId;
    private int salesDateMonth;
    private int salesDateYear;
    private int quantitySold;
    private double price;
    private double totalSales;

    // Constructor
    public salesreport(String entryId, String itemId, int salesDateMonth, int salesDateYear, int quantitySold, double price) {
        this.entryId = entryId;
        this.itemId = itemId;
        this.salesDateMonth = salesDateMonth;
        this.salesDateYear = salesDateYear;
        this.quantitySold = quantitySold;
        this.price = price;
        this.totalSales = calculateTotalSales();
        
    }

    // Method to calculate total sales
    private double calculateTotalSales() {
        return this.quantitySold * this.price;
    }

    // Getters and Setters
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getSalesDateMonth() {
        return salesDateMonth;
    }

    public void setSalesDateMonth(int salesDateMonth) {
        this.salesDateMonth = salesDateMonth;
    }

    public int getSalesDateYear() {
        return salesDateYear;
    }

    public void setSalesDateYear(int salesDateYear) {
        this.salesDateYear = salesDateYear;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
        this.totalSales = calculateTotalSales(); // Recalculate total sales
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalSales = calculateTotalSales(); // Recalculate total sales
    }

    public double getTotalSales() {
        return totalSales;
    }

    // Optional toString method for debugging or displaying information
    @Override
    public String toString() {
        return "SalesReport{" +
                "entryId='" + entryId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", salesDateMonth=" + salesDateMonth +
                ", salesDateYear=" + salesDateYear +
                ", quantitySold=" + quantitySold +
                ", price=" + price +
                ", totalSales=" + totalSales +
                '}';
    }
}

