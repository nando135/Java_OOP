package javanexuspots.services;

import javanexuspots.models.Payment;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javanexuspots.models.PurchaseOrder;

public class PaymentService extends AbstractService<Payment> {
    private static final String FILE_PATH = "src/javanexuspots/databases/payment.txt";
    
    public PaymentService() {
        super(FILE_PATH);
    }
    
    @Override
    public List<Payment> loadData() {
        List<String> lines = readFile();
        List<Payment> paymentList = new ArrayList<>();
        
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length == 6) {
                try {
                    Payment payment = new Payment(
                            Integer.parseInt(fields[0].trim()), // Payment ID
                            Integer.parseInt(fields[1].trim()), // Purchase Order ID
                            fields[2].trim(),                  // Supplier ID (String)
                            Double.parseDouble(fields[3].trim()), // Amount
                            fields[4].trim(),                  // Status
                            fields[5].trim()                   // Payment Date
                    );
                    paymentList.add(payment);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid payment entry: " + line);
                }
            }
        }
        return paymentList;
    }
    
    @Override
       public void saveData(List<Payment> payments) {
        List<String> lines = new ArrayList<>();
        for (Payment payment : payments) {
            lines.add(String.join(",",
                    String.valueOf(payment.getPaymentID()),
                    String.valueOf(payment.getPurchaseOrderID()),
                    payment.getSupplierID(),
                    String.valueOf(payment.getAmount()),
                    payment.getStatus(),
                    payment.getPaymentDate()
            ));
        }
        writeFile(lines); // Overwrite the file with updated content
    }

    
    public List<Payment> getPaymentHistory() {
        return loadData();
    }
    
    public List<Payment> getUnpaidPayments() {
        List<Payment> unpaidPayments = new ArrayList<>();
        for (Payment payment : loadData()) {
            if ("unpaid".equalsIgnoreCase(payment.getStatus())) {
                unpaidPayments.add(payment);
            }
        }
        return unpaidPayments;
    }
    
    public void updatePaymentStatus(int paymentID, String newStatus) {
        List<Payment> payments = loadData();
        boolean paymentFound = false;
        
        for (Payment payment : payments) {
            if (payment.getPaymentID() == paymentID) {
                payment.setStatus(newStatus);
                saveData(payments);
                paymentFound = true;
                break;
            }
        }
        
        if (!paymentFound) {
            JOptionPane.showMessageDialog(null, "Payment ID not found: " + paymentID, "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addPayment(Payment payment) {
        List<Payment> payments = loadData();
        
        for (Payment existingPayment : payments) {
            if (existingPayment.getPaymentID() == payment.getPaymentID()) {
                throw new IllegalArgumentException("Payment ID already exists: " + payment.getPaymentID());
            }
        }
        payments.add(payment);
        saveData(payments);
    }
    
    public void deletePayment(int paymentId) {
        List<Payment> payments = loadData();
        payments.removeIf(payment -> payment.getPaymentID() == paymentId);
        saveData(payments);
    }
    
    public Payment getPaymentById(int paymentID) {
        for (Payment payment : loadData()) {
            if (payment.getPaymentID() == paymentID) {
                return payment;
            }
        }
        return null;
    }
    
    public String generateNextPaymentId() {
        List<Payment> payments = loadData();
        int maxId = 0;
        
        for (Payment payment : payments) {
            if (payment.getPaymentID() > maxId) {
                maxId = payment.getPaymentID();
            }
        }
        return String.valueOf(maxId + 1);
    }
    
    public List<Payment> getAllPayments() {
        return loadData();
    }

    public List<Object[]> getAllPaymentsForTable() {
        List<Payment> payments = loadData();
        List<Object[]> paymentRows = new ArrayList<>();
        
        for (Payment payment : payments) {
            paymentRows.add(new Object[]{
                payment.getPaymentID(),
                payment.getPurchaseOrderID(),
                payment.getSupplierID(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentDate()
            });
        }
        return paymentRows;
    }
    
    public void updatePayment(Payment updatedPayment) {
        List<Payment> payments = loadData();
        boolean paymentFound = false;
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentID() == updatedPayment.getPaymentID()) {
                payments.set(i, updatedPayment);
                saveData(payments);
                paymentFound = true;
                break;
            }
        }

        if (!paymentFound) {
            JOptionPane.showMessageDialog(null, "Payment not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void moveApprovedOrdersToPayments(List<PurchaseOrder> approvedOrders) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
