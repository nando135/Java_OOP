package javanexuspots.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javanexuspots.models.SalesEntry;

public class SalesEntryService extends AbstractService<SalesEntry> {

    private static final String FILE_PATH = "src/javanexuspots/databases/sales_entry.txt";

    public SalesEntryService() {
        super(FILE_PATH);
    }

    @Override
    public List<SalesEntry> loadData() {
        List<String> lines =readFile();
        List<SalesEntry> salesEntries = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4) { // Ensure there are 4 fields
                try {
                    SalesEntry salesEntry = new SalesEntry(
                            parts[0].trim(), // entryId
                            parts[1].trim(), // itemId
                            new Date(Long.parseLong(parts[2].trim())), // salesDate
                            Integer.parseInt(parts[3].trim()) // quantitySold
                    );
                    salesEntries.add(salesEntry);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid entry: " + line);
                }
            } else {
                System.out.println("Skipping invalid entry: " + line);
            }
        }
        return salesEntries;
    }

    @Override
    public void saveData(List<SalesEntry> salesEntries) {
        List<String> lines = new ArrayList<>();
        for (SalesEntry salesEntry : salesEntries) {
            lines.add(String.join(",",
                    salesEntry.getEntryId(),
                    salesEntry.getItemId(),
                    String.valueOf(salesEntry.getSalesDate().getTime()), // Save date as timestamp
                    String.valueOf(salesEntry.getQuantitySold())
            ));
        }
        writeFile(lines);
    }

    public void addSalesEntry(SalesEntry salesEntry) {
        List<SalesEntry> salesEntries = loadData();

        for (SalesEntry existingEntry : salesEntries) {
            if (existingEntry.getEntryId().equals(salesEntry.getEntryId())) {
                throw new IllegalArgumentException("Sales entry ID already exists: " + salesEntry.getEntryId());
            }
        }

        salesEntries.add(salesEntry);
        saveData(salesEntries);
    }

    public void updateSalesEntry(SalesEntry updatedSalesEntry) {
        List<SalesEntry> salesEntries = loadData();
        boolean entryFound = false;

        for (int i = 0; i < salesEntries.size(); i++) {
            if (salesEntries.get(i).getEntryId().equals(updatedSalesEntry.getEntryId())) {
                salesEntries.set(i, updatedSalesEntry);
                entryFound = true;
                break;
            }
        }

        if (!entryFound) {
            throw new IllegalArgumentException("Sales entry with ID " + updatedSalesEntry.getEntryId() + " does not exist.");
        }

        saveData(salesEntries);
    }

    public void deleteSalesEntry(String entryId) {
        List<SalesEntry> salesEntries = loadData();
        boolean removed = salesEntries.removeIf(entry -> entry.getEntryId().equals(entryId));

        if (!removed) {
            throw new IllegalArgumentException("Sales entry with ID " + entryId + " does not exist.");
        }

        saveData(salesEntries);
    }

    public List<SalesEntry> getAllSalesEntries() {
        return loadData();
    }

    public SalesEntry getSalesEntryById(String entryId) {
        List<SalesEntry> salesEntries = loadData();
        for (SalesEntry salesEntry : salesEntries) {
            if (salesEntry.getEntryId().equals(entryId)) {
                return salesEntry;
            }
        }
        return null;
    }

    public String generateNextEntryId() {
        List<SalesEntry> salesEntries = loadData();
        int maxId = 0;

        for (SalesEntry salesEntry : salesEntries) {
            try {
                String idPart = salesEntry.getEntryId().replaceAll("[^\\d]", ""); // Extract numeric part
                int numericId = Integer.parseInt(idPart);
                if (numericId > maxId) {
                    maxId = numericId;
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid entry ID: " + salesEntry.getEntryId());
            }
        }

        return "SA" + String.format("%03d", maxId + 1); // Generate next ID in "SAXXX" format
    }
    
    public List<Object[]> getAllSalesForTable() {
        List<SalesEntry> salesEntries = loadData(); // Load sales entries from the file
        List<Object[]> salesRows = new ArrayList<>();

        for (SalesEntry salesEntry : salesEntries) {
            salesRows.add(new Object[]{
                    salesEntry.getEntryId(), // Entry ID
                    salesEntry.getItemId(), // Item ID
                    salesEntry.getSalesDate().toString(), // Sales Date as String
                    salesEntry.getQuantitySold() // Quantity Sold
            });
        }

        return salesRows;
    }
}