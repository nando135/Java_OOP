package javanexuspots.models;

public class Item {
    private String itemId;
    private String itemCode;
    private String itemName;
    private String supplierId;
    private double price;
    private int reorderLevel;
    private int stockLevel;

    public Item(String itemId, String itemCode, String itemName, String supplierId, double price, int reorderLevel, int stockLevel) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierId = supplierId;
        this.price = price;
        this.reorderLevel = reorderLevel;
        this.stockLevel = stockLevel;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", price=" + price +
                ", reorderLevel=" + reorderLevel +
                ", stockLevel=" + stockLevel +
                '}';
    }
}
