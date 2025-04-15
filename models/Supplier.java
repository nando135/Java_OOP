package javanexuspots.models;

public class Supplier {
    private String supplierId;
    private String supplierCode;
    private String supplierName;
    private String contactInfo;
    private String address;
    private String itemId;

    public Supplier(String supplierId, String supplierCode, String supplierName, String contactInfo, String address, String itemId) {
        this.supplierId = supplierId; 
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.address = address;
        this.itemId = itemId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
