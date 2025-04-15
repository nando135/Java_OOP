package javanexuspots.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javanexuspots.models.Supplier;

public class SupplierService {
    private static final String FILE_PATH = "src/javanexuspots/databases/suppliers.txt";
    private List<Supplier> suppliers;

    public SupplierService() {
        this.suppliers = loadSuppliers();
    }

    private List<Supplier> loadSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Supplier supplier = new Supplier(
                        parts[0].trim(), // Supplier ID
                        parts[1].trim(), // Supplier Code
                        parts[2].trim(), // Supplier Name
                        parts[3].trim(), // Contact Info
                        parts[4].trim(), // Address
                        parts[5].trim()  // Item ID
                    );
                    supplierList.add(supplier);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    public void saveSuppliers() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Supplier supplier : suppliers) {
                fw.write(String.join(",",
                    supplier.getSupplierId(),
                    supplier.getSupplierCode(),
                    supplier.getSupplierName(),
                    supplier.getContactInfo(),
                    supplier.getAddress(),
                    supplier.getItemId()
                ));
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }
    
    public void addSupplier(Supplier newSupplier) {
        suppliers.add(newSupplier);
        saveSuppliers(); // Make sure to persist the new supplier
    }

    public String generateNextSupplierId() {
        int maxIdNumber = 0;
        for (Supplier supplier : suppliers) {
            String currentId = supplier.getSupplierId();
            String numberPart = currentId.substring(1); 
            int idNum = Integer.parseInt(numberPart);
            if (idNum > maxIdNumber) {
                maxIdNumber = idNum;
            }
        }
        int nextId = maxIdNumber + 1;
        return String.format("S%03d", nextId);
    }

    public boolean deleteSupplier(String supplierId) {
        boolean removed = suppliers.removeIf(s -> s.getSupplierId().equals(supplierId));
        if (removed) {
            saveSuppliers();
        }
        return removed;
    }
    
    public Optional<Supplier> getSupplierById(String supplierId) {
        return getAllSuppliers().stream()
                .filter(supplier -> supplier.getSupplierId().equals(supplierId))
                .findFirst();
    }
    
    public boolean updateSupplier(Supplier updatedSupplier) {
        for (int i = 0; i < suppliers.size(); i++) {
            if (suppliers.get(i).getSupplierId().equals(updatedSupplier.getSupplierId())) {
                suppliers.set(i, updatedSupplier);
                saveSuppliers();
                return true;
            }
        }
        return false;
    }

    public List<Object[]> getAllSuppliersForTable() {
        List<Object[]> supplierRows = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierRows.add(new Object[]{
                supplier.getSupplierId(),
                supplier.getSupplierCode(),
                supplier.getSupplierName(),
                supplier.getContactInfo(),
                supplier.getAddress(),
                supplier.getItemId()
            });
        }

        return supplierRows;
    }

}
