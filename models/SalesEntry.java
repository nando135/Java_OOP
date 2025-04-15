package javanexuspots.models;

import java.util.Date;

/**
 * SalesEntry represents a single sales transaction.
 */
public class SalesEntry {
    private String entryId;
    private String itemId;
    private Date salesDate;
    private int quantitySold;

    // Constructor
    public SalesEntry(String entryId, String itemId, Date salesDate, int quantitySold) {
        this.entryId = entryId;
        this.itemId = itemId;
        this.salesDate = salesDate;
        this.quantitySold = quantitySold;
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

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}