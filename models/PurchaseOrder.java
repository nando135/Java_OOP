package javanexuspots.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseOrder {

    private int purchaseOrderID;
    private String purchaseRequisitionID;
    private String itemID;
    private int quantity;
    private int reorderLevel; 
    private String status;
    private Date date;


    public PurchaseOrder(int purchaseOrderID, String purchaseRequisitionID, String itemID, int quantity, int reorderLevel, String status, Date date) {
        this.purchaseOrderID = purchaseOrderID;
        this.purchaseRequisitionID = purchaseRequisitionID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.status = status;
        this.date = date;
    }

    public PurchaseOrder(int purchaseOrderID, String purchaseRequisitionID, String itemID, int quantity, int reorderLevel, String status, String date) {
        this.purchaseOrderID = purchaseOrderID;
        this.purchaseRequisitionID = purchaseRequisitionID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.status = status;
        try {
            // Parse the String date into a Date object
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }
    }

    // Getters
    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public String getPurchaseRequisitionID() {
        return purchaseRequisitionID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setStatus(String status) {
        if (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected") || status.equalsIgnoreCase("Pending")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public void setPurchaseRequisitionID(String purchaseRequisitionID) {
        this.purchaseRequisitionID = purchaseRequisitionID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return purchaseOrderID + "," + purchaseRequisitionID + "," + itemID + "," + quantity + "," + reorderLevel + "," + status + "," + dateFormat.format(date);
    }
}
