package javanexuspots.financeManager;

import javanexuspots.models.Payment;
import javanexuspots.services.PaymentService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PaymentForm extends javax.swing.JFrame {
    
    private final PaymentService paymentService;
    private DefaultTableModel tableModel;

    public PaymentForm() {
        paymentService = new PaymentService(); // No file path needed here
        initComponents();
        loadTableData();
        populateUnpaidComboBox();
    }
    
    public void loadTableData() {
        tableModel = (DefaultTableModel) PaymentTable.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        List<Payment> payments = paymentService.getPaymentHistory();
        for (Payment payment : payments) {
            tableModel.addRow(new Object[]{
                payment.getPaymentID(),
                payment.getPurchaseOrderID(),
                payment.getSupplierID(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentDate()
            });
        }
    }

     
    // Populate ComboBox with Unpaid Payments
    private void populateUnpaidComboBox() {
        ComboxPayment.removeAllItems();

        List<Payment> unpaidPayments = paymentService.getUnpaidPayments();
        for (Payment payment : unpaidPayments) {
                    String comboItem = "PO ID: " + payment.getPurchaseOrderID() + " (Payment ID: " + payment.getPaymentID() + ")";

            ComboxPayment.addItem(String.valueOf(payment.getPurchaseOrderID()));
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PaymentTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ComboxPayment = new javax.swing.JComboBox<>();
        ButtonPay = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PaymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PAYMENT ID", "PO ID", "SUPPLIER ID", "QTY", "STATUS", "DATE"
            }
        ));
        jScrollPane1.setViewportView(PaymentTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("PAYMENT APPROVAL");

        ComboxPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboxPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboxPaymentActionPerformed(evt);
            }
        });

        ButtonPay.setText("Pay");
        ButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPayActionPerformed(evt);
            }
        });

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ComboxPayment, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BackButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboxPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPayActionPerformed
     String selectedPOID = (String) ComboxPayment.getSelectedItem();

    if (selectedPOID == null || selectedPOID.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a PO ID from the ComboBox.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Parse selected PO ID
        int poID = Integer.parseInt(selectedPOID.trim());

        // Find and update the payment status
        List<Payment> payments = paymentService.getPaymentHistory();
        boolean updated = false;

        for (Payment payment : payments) {
            if (payment.getPurchaseOrderID() == poID && "unpaid".equalsIgnoreCase(payment.getStatus())) {
                payment.setStatus("Paid"); // Update status
                updated = true;
                break;
            }
        }

        if (!updated) {
            JOptionPane.showMessageDialog(this, "No unpaid payment found for PO ID: " + poID, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save updated data back to the file
        paymentService.saveData(payments);

        // Notify user of success
        JOptionPane.showMessageDialog(this, "Payment successfully updated to Paid.", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Refresh table and ComboBox
        loadTableData();
        populateUnpaidComboBox();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid PO ID format.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_ButtonPayActionPerformed

    private void ComboxPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboxPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboxPaymentActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        FinanceMainPage financeMainpage = new FinanceMainPage();
        financeMainpage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton ButtonPay;
    private javax.swing.JComboBox<String> ComboxPayment;
    private javax.swing.JTable PaymentTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}