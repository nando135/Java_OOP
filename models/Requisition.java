package javanexuspots.models;

import java.util.Date;

public class Requisition {
    private String requisitionId;
    private String itemId;
    private int quantity;
    private Date requiredByDate;
    private String managerId;
    private String supplierId;
    private String status;

    public Requisition(String requisitionId, String itemId, int quantity, Date requiredByDate, String managerId, String supplierId, String status) {
        this.requisitionId = requisitionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.requiredByDate = requiredByDate;
        this.managerId = managerId;
        this.supplierId = supplierId;
        this.status = status;
    }

    public String getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getRequiredByDate() {
        return requiredByDate;
    }

    public void setRequiredByDate(Date requiredByDate) {
        this.requiredByDate = requiredByDate;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
