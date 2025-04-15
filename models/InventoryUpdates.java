package javanexuspots.models;

import java.time.LocalDateTime;
import java.util.Date;

public class InventoryUpdates {
    private String updateId;
    private String itemId;
    private int changeInStock;
    private LocalDateTime dateUpdated;

    public InventoryUpdates(String updateId, String itemId, int changeInStock, LocalDateTime dateUpdated) {
        this.updateId = updateId;
        this.itemId = itemId;
        this.changeInStock = changeInStock;
        this.dateUpdated = dateUpdated;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getChangeInStock() {
        return changeInStock;
    }

    public void setChangeInStock(int changeInStock) {
        this.changeInStock = changeInStock;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "InventoryUpdates{" +
                "updateId='" + updateId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", changeInStock=" + changeInStock +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
