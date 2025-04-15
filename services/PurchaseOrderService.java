package javanexuspots.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javanexuspots.models.PurchaseOrder;

public class PurchaseOrderService extends AbstractService<PurchaseOrder> {
    private static final String FILE_PATH = "src/javanexuspots/databases/purchase_order.txt";

    public PurchaseOrderService() {
        super(FILE_PATH);
    }

    @Override
    public List<PurchaseOrder> loadData() {
    List<String> lines = readFile();
    List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            try {
                int purchaseOrderID = Integer.parseInt(parts[0].trim());
                String purchaseRequisitionID = parts[1].trim();
                String itemID = parts[2].trim();
                int quantity = Integer.parseInt(parts[3].trim());
                int reorderLevel = Integer.parseInt(parts[4].trim());
                String status = parts[5].trim();
                String date = parts[6].trim();

                purchaseOrders.add(new PurchaseOrder(purchaseOrderID, purchaseRequisitionID, itemID, quantity, reorderLevel, status, date));
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid entry: " + line);
            }
        }
    }
    return purchaseOrders;
}


    @Override
    public void saveData(List<PurchaseOrder> purchaseOrders) {
        List<String> lines = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String formattedDate = dateFormat.format(purchaseOrder.getDate());

            lines.add(String.join(",",
                String.valueOf(purchaseOrder.getPurchaseOrderID()),
                purchaseOrder.getPurchaseRequisitionID(),
                purchaseOrder.getItemID(),
                String.valueOf(purchaseOrder.getQuantity()),
                String.valueOf(purchaseOrder.getReorderLevel()),
                purchaseOrder.getStatus(),
                formattedDate
            ));
        }
        writeFile(lines);
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        List<PurchaseOrder> purchaseOrders = loadData();

        for (PurchaseOrder existingOrder : purchaseOrders) {
            if (existingOrder.getPurchaseOrderID() == purchaseOrder.getPurchaseOrderID()) {
                throw new IllegalArgumentException("Purchase Order ID already exists: " + purchaseOrder.getPurchaseOrderID());
            }
        }

        purchaseOrders.add(purchaseOrder);
        saveData(purchaseOrders);
    }

    public void updatePurchaseOrder(PurchaseOrder updatedOrder) {
        List<PurchaseOrder> purchaseOrders = loadData();
        boolean orderFound = false;

        for (int i = 0; i < purchaseOrders.size(); i++) {
            if (purchaseOrders.get(i).getPurchaseOrderID() == updatedOrder.getPurchaseOrderID()) {
                purchaseOrders.set(i, updatedOrder);
                orderFound = true;
                break;
            }
        }

        if (!orderFound) {
            throw new IllegalArgumentException("Purchase Order with ID " + updatedOrder.getPurchaseOrderID() + " does not exist.");
        }

        saveData(purchaseOrders);
    }

    public void deletePurchaseOrder(int purchaseOrderID) {
        List<PurchaseOrder> purchaseOrders = loadData();
        boolean removed = purchaseOrders.removeIf(order -> order.getPurchaseOrderID() == purchaseOrderID);

        if (!removed) {
            throw new IllegalArgumentException("Purchase Order with ID " + purchaseOrderID + " does not exist.");
        }

        saveData(purchaseOrders);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return loadData();
    }

    public PurchaseOrder getPurchaseOrderById(int purchaseOrderID) {
        List<PurchaseOrder> purchaseOrders = loadData();
        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPurchaseOrderID() == purchaseOrderID) {
                return order;
            }
        }
        return null;
    }

    public int generateNextPurchaseOrderId() {
        List<PurchaseOrder> purchaseOrders = loadData();
        int maxId = 0;

        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPurchaseOrderID() > maxId) {
                maxId = order.getPurchaseOrderID();
            }
        }

        return maxId + 1;
    }

    public List<Object[]> getAllPurchaseOrdersForTable() {
        List<PurchaseOrder> purchaseOrders = loadData();
        List<Object[]> purchaseOrderRows = new ArrayList<>();

        for (PurchaseOrder order : purchaseOrders) {
            purchaseOrderRows.add(new Object[]{
                order.getPurchaseOrderID(),
                order.getPurchaseRequisitionID(),
                order.getItemID(),
                order.getQuantity(),
                order.getReorderLevel(),
                order.getStatus(),
                order.getDate()
            });
        }

        return purchaseOrderRows;
    }

    public List<PurchaseOrder> getPendingPurchaseOrders() {
        List<PurchaseOrder> allOrders = getAllPurchaseOrders();
        List<PurchaseOrder> pendingOrders = new ArrayList<>();

        for (PurchaseOrder order : allOrders) {
            if ("Pending".equalsIgnoreCase(order.getStatus())) {
                pendingOrders.add(order);
            }
        }
        return pendingOrders;
    }

    public void updateOrderStatus(int purchaseOrderID, String newStatus) {
        List<PurchaseOrder> purchaseOrders = loadData();
        
        boolean updated = false;
        for (PurchaseOrder order : purchaseOrders) {
            if (order.getPurchaseOrderID() == purchaseOrderID) {
                order.setStatus(newStatus);
                updated = true;
                break;
            }
        }
        
        if (!updated) {
            throw new IllegalArgumentException("Purchase Order with ID " + purchaseOrderID + " not found.");
        }
        
        saveData(purchaseOrders);
    }
}