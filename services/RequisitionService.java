package javanexuspots.services;

import javanexuspots.models.Requisition;
import javanexuspots.services.AbstractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequisitionService extends AbstractService<Requisition> {
    private static final String FILE_PATH = "src/javanexuspots/databases/requisitions.txt";

    public RequisitionService() {
        super(FILE_PATH);
    }

    @Override
    public List<Requisition> loadData() {
        List<String> lines = readFile();
        List<Requisition> requisitions = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                try {
                    Requisition requisition = new Requisition(
                            parts[0].trim(), // requisitionId
                            parts[1].trim(), // itemId
                            Integer.parseInt(parts[2].trim()), // quantity
                            new Date(Long.parseLong(parts[3].trim())), // requiredByDate
                            parts[4].trim(), // managerId
                            parts[5].trim(), // supplierId
                            parts[6].trim()  // status
                    );
                    requisitions.add(requisition);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid entry: " + line);
                }
            } else {
                System.out.println("Skipping invalid entry: " + line);
            }
        }
        return requisitions;
    }

    @Override
    public void saveData(List<Requisition> requisitions) {
        List<String> lines = new ArrayList<>();
        for (Requisition requisition : requisitions) {
            lines.add(String.join(",",
                    requisition.getRequisitionId(),
                    requisition.getItemId(),
                    String.valueOf(requisition.getQuantity()),
                    String.valueOf(requisition.getRequiredByDate().getTime()),
                    requisition.getManagerId(),
                    requisition.getSupplierId(),
                    requisition.getStatus()
            ));
        }
        writeFile(lines);
    }

    public void addRequisition(Requisition requisition) {
        List<Requisition> requisitions = loadData();

        for (Requisition existingRequisition : requisitions) {
            if (existingRequisition.getRequisitionId().equals(requisition.getRequisitionId())) {
                throw new IllegalArgumentException("Requisition ID already exists: " + requisition.getRequisitionId());
            }
        }

        requisitions.add(requisition);
        saveData(requisitions);
    }

    public void updateRequisition(Requisition updatedRequisition) {
        List<Requisition> requisitions = loadData();
        boolean requisitionFound = false;

        for (int i = 0; i < requisitions.size(); i++) {
            if (requisitions.get(i).getRequisitionId().equals(updatedRequisition.getRequisitionId())) {
                requisitions.set(i, updatedRequisition);
                requisitionFound = true;
                break;
            }
        }

        if (!requisitionFound) {
            throw new IllegalArgumentException("Requisition with ID " + updatedRequisition.getRequisitionId() + " does not exist.");
        }

        saveData(requisitions);
    }

    public void deleteRequisition(String requisitionId) {
        List<Requisition> requisitions = loadData();
        boolean removed = requisitions.removeIf(req -> req.getRequisitionId().equals(requisitionId));

        if (!removed) {
            throw new IllegalArgumentException("Requisition with ID " + requisitionId + " does not exist.");
        }

        saveData(requisitions);
    }

    public List<Requisition> getAllRequisitions() {
        return loadData();
    }

    public Requisition getRequisitionById(String requisitionId) {
        List<Requisition> requisitions = loadData();
        for (Requisition requisition : requisitions) {
            if (requisition.getRequisitionId().equals(requisitionId)) {
                return requisition;
            }
        }
        return null;
    }

    public String generateNextRequisitionId() {
        List<Requisition> requisitions = loadData();
        int maxId = 0;

        for (Requisition requisition : requisitions) {
            try {
                String idPart = requisition.getRequisitionId().replaceAll("[^\\d]", "");
                int numericId = Integer.parseInt(idPart);
                if (numericId > maxId) {
                    maxId = numericId;
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid requisition ID: " + requisition.getRequisitionId());
            }
        }

        return "R" + String.format("%03d", maxId + 1); // Generate next ID in "RXXX" format
    }

    public List<Object[]> getAllRequisitionsForTable() {
        List<Requisition> requisitions = loadData();
        List<Object[]> requisitionRows = new ArrayList<>();

        for (Requisition requisition : requisitions) {
            requisitionRows.add(new Object[]{
                requisition.getRequisitionId(),
                requisition.getItemId(),
                requisition.getQuantity(),
                requisition.getRequiredByDate(),
                requisition.getManagerId(),
                requisition.getSupplierId(),
                requisition.getStatus()
            });
        }

        return requisitionRows;
    }
}
