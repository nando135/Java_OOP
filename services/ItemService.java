package javanexuspots.services;

import javanexuspots.models.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemService extends AbstractService<Item> {
    private static final String FILE_PATH = "src/javanexuspots/databases/items.txt";
    private final InventoryUpdateService inventoryUpdateService;

    public ItemService() {
        super(FILE_PATH);
        this.inventoryUpdateService = new InventoryUpdateService();
    }

    @Override
    public List<Item> loadData() {
        List<String> lines = readFile();
        List<Item> items = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                try {
                    Item item = new Item(
                        parts[0].trim(),    
                        parts[1].trim(),   
                        parts[2].trim(),    
                        parts[3].trim(),   
                        Double.parseDouble(parts[4].trim()),  
                        Integer.parseInt(parts[5].trim()),  
                        Integer.parseInt(parts[6].trim())    
                    );
                    items.add(item);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            } else {
                System.err.println("Invalid number of fields in line: " + line);
            }
        }
        return items;
    }

    @Override
    public void saveData(List<Item> items) {
        List<String> lines = new ArrayList<>();
        for (Item item : items) {
            String line = String.format("%s,%s,%s,%s,%.2f,%d,%d",
                item.getItemId(),
                item.getItemCode(),
                item.getItemName(),
                item.getSupplierId(),
                item.getPrice(),
                item.getReorderLevel(),
                item.getStockLevel()
            );
            lines.add(line);
        }
        writeFile(lines);
    }

    public void addItem(Item item) {
        validateItem(item);
        List<Item> items = loadData();

        // check for duplicate IDs and codes
        for (Item existingItem : items) {
            if (existingItem.getItemId().equals(item.getItemId())) {
                throw new IllegalArgumentException("Item ID already exists: " + item.getItemId());
            }
            if (existingItem.getItemCode().equals(item.getItemCode())) {
                throw new IllegalArgumentException("Item code already exists: " + item.getItemCode());
            }
        }

        items.add(item);
        saveData(items);
    }

    public void updateItem(Item updatedItem) {
        validateItem(updatedItem);
        List<Item> items = loadData();
        boolean itemFound = false;

        // original item for logging
        Item originalItem = getItemById(updatedItem.getItemId());
        if (originalItem == null) {
            throw new IllegalArgumentException("Item with ID " + updatedItem.getItemId() + " does not exist");
        }

        // check for duplicate item code
        for (Item existingItem : items) {
            if (!existingItem.getItemId().equals(updatedItem.getItemId()) && 
                existingItem.getItemCode().equals(updatedItem.getItemCode())) {
                throw new IllegalArgumentException("Item code already exists: " + updatedItem.getItemCode());
            }
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId().equals(updatedItem.getItemId())) {
                items.set(i, updatedItem);
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            throw new IllegalArgumentException("Item with ID " + updatedItem.getItemId() + " does not exist");
        }

        saveData(items);
    }

    public void deleteItem(String itemId) {
        List<Item> items = loadData();
        boolean removed = items.removeIf(item -> item.getItemId().equals(itemId));

        if (!removed) {
            throw new IllegalArgumentException("Item with ID " + itemId + " does not exist");
        }

        saveData(items);
    }

    public void updateStock(String itemId, int changeAmount, String managerId) {
        Item item = getItemById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found: " + itemId);
        }

        int newStockLevel = item.getStockLevel() + changeAmount;
        if (newStockLevel < 0) {
            throw new IllegalArgumentException("Insufficient stock level");
        }

        Item updatedItem = new Item(
            item.getItemId(),
            item.getItemCode(),
            item.getItemName(),
            item.getSupplierId(),
            item.getPrice(),
            item.getReorderLevel(),
            newStockLevel
        );

        updateItem(updatedItem);
    }

    public List<Item> getAllItems() {
        return loadData();
    }

    public String generateNextItemId() {
        List<Item> items = loadData();
        int maxId = 0;

        for (Item item : items) {
            try {
                String idNumber = item.getItemId().substring(1); // Remove 'I' prefix
                int currentId = Integer.parseInt(idNumber);
                maxId = Math.max(maxId, currentId);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println("Invalid item ID format: " + item.getItemId());
            }
        }

        return "I" + String.format("%03d", maxId + 1);
    }

    private void validateItem(Item item) {
        if (item.getItemId() == null || item.getItemId().trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }
        if (item.getItemCode() == null || item.getItemCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Item code cannot be empty");
        }
        if (item.getItemName() == null || item.getItemName().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (item.getSupplierId() == null || item.getSupplierId().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID cannot be empty");
        }
        if (item.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (item.getReorderLevel() < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative");
        }
        if (item.getStockLevel() < 0) {
            throw new IllegalArgumentException("Stock level cannot be negative");
        }
    }

    public List<Object[]> getAllItemsForTable() {
        List<Item> items = loadData();
        List<Object[]> itemRows = new ArrayList<>();

        for (Item item : items) {
            itemRows.add(new Object[]{
                item.getItemId(),
                item.getItemCode(),
                item.getItemName(), 
                item.getSupplierId(),
                item.getPrice(),
                item.getReorderLevel(),
                item.getStockLevel()
            });
        }

        return itemRows;
    }
    
    private Item getItem(String key, boolean isId) {
        return loadData().stream()
            .filter(item -> isId ? item.getItemId().equals(key) : item.getItemCode().equals(key))
            .findFirst()
            .orElse(null);
    }
    
    public Item getItemById(String itemId) {
        return getItem(itemId, true);
    }
    
    public Item getItemByCode(String itemCode) {
        return getItem(itemCode, false);
    }

}