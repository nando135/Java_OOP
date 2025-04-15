package javanexuspots.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javanexuspots.models.InventoryUpdates;

public class InventoryUpdateService {
    private static final String FILE_PATH = "src/javanexuspots/databases/item_logs.txt";

    // method to generate the next update ID
    public String generateNextUpdateId() {
        List<InventoryUpdates> updates = loadUpdates(); // load existing updates
        int maxId = 0;

        for (InventoryUpdates update : updates) {
            try {
                String idPart = update.getUpdateId().replaceAll("[^\\d]", ""); // extract numeric part
                int numericId = Integer.parseInt(idPart);
                if (numericId > maxId) {
                    maxId = numericId;
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid update ID: " + update.getUpdateId());
            }
        }

        return String.format("%03d", maxId + 1); // generate next ID as a number
    }

    public void writeInventoryUpdate(InventoryUpdates update) {
        String logEntry = String.format("%s,%s,%d,%s%n",
            update.getUpdateId(),
            update.getItemId(),
            update.getChangeInStock(),
            update.getDateUpdated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(logEntry);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to inventory update log file: " + e.getMessage());
        }
    }

    public InventoryUpdates createAddUpdate(String itemId, int changeInStock) {
        String updateId = generateNextUpdateId();
        LocalDateTime dateUpdated = LocalDateTime.now();

        return new InventoryUpdates(updateId, itemId, changeInStock, dateUpdated);
    }

    public InventoryUpdates createEditUpdate(String itemId, int changeInStock) {
        String updateId = generateNextUpdateId();
        LocalDateTime dateUpdated = LocalDateTime.now();

        return new InventoryUpdates(updateId, itemId, changeInStock, dateUpdated);
    }

    public InventoryUpdates createDeleteUpdate(String itemId) {
        String updateId = generateNextUpdateId();
        LocalDateTime dateUpdated = LocalDateTime.now();

        return new InventoryUpdates(updateId, itemId, 0, dateUpdated);
    }

    public List<InventoryUpdates> loadUpdates() {
        List<InventoryUpdates> updates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    InventoryUpdates update = new InventoryUpdates(
                        parts[0].trim(), // updateId
                        parts[1].trim(), // itemId
                        Integer.parseInt(parts[2].trim()), // changeInStock
                        LocalDateTime.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME) // dateUpdated
                    );
                    updates.add(update);
                } else {
                    System.out.println("Skipping invalid entry: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load inventory updates: " + e.getMessage());
        }
        return updates;
    }
}
