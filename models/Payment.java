package javanexuspots.models;

import java.util.Date;

public class Payment {
    private int paymentID;
    private int purchaseOrderID;
    private String supplierID;
    private double amount;
    private String status;
    private String paymentDate;

    // Constructor
    public Payment(int paymentID, int purchaseOrderID, String supplierID, double amount, String status, String paymentDate) {
        this.paymentID = paymentID;
        this.purchaseOrderID = purchaseOrderID;
        this.supplierID = supplierID;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public Payment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters
    public int getPaymentID() {
        return paymentID;
    }

    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    // Setters
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
    if (status.equalsIgnoreCase("Approved") || 
        status.equalsIgnoreCase("Rejected") || 
        status.equalsIgnoreCase("Pending") || 
        status.equalsIgnoreCase("Paid") || 
        status.equalsIgnoreCase("Unpaid")) {
        this.status = status;
    } else {
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}


    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return paymentID + "," + purchaseOrderID + "," + supplierID + "," + amount + "," + status + "," + paymentDate;
    }

    public void setId(int generatePaymentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
