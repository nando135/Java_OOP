package javanexuspots.financeManager;

import javanexuspots.models.PurchaseOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javanexuspots.services.PaymentService;
import javanexuspots.services.PurchaseOrderService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Approve extends javax.swing.JFrame {

   
    private final PurchaseOrderService purchaseOrderManager;
    private final PaymentService paymentService;

    public Approve() {
        initComponents();
        purchaseOrderManager = new PurchaseOrderService();
        paymentService = new PaymentService();
        populateTable();
        populateComboBox();
    }


    private void populateTable() {
    DefaultTableModel model = (DefaultTableModel) approvalTable.getModel();
    model.setRowCount(0);

    List<PurchaseOrder> purchaseOrders = purchaseOrderManager.getAllPurchaseOrders();
    for (PurchaseOrder order : purchaseOrders) {
        
        model.addRow(new Object[]{
            order.getPurchaseOrderID(),
            order.getPurchaseRequisitionID(),
            order.getItemID(),
            order.getReorderLevel(),
            order.getQuantity(),
            order.getStatus(),
            new SimpleDateFormat("yyyy-MM-dd").format(order.getDate())
        });
    }
}


    private void populateComboBox() {
        ComboBox.removeAllItems();
        List<PurchaseOrder> pendingOrders = purchaseOrderManager.getPendingPurchaseOrders();

        for (PurchaseOrder order : pendingOrders) {
            ComboBox.addItem("PurchaseOrder: " + order.getPurchaseOrderID());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        approvalTable = new javax.swing.JTable();
        ComboBox = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        POIDlabel = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        PRIDlabel = new javax.swing.JLabel();
        ITEMlabel = new javax.swing.JLabel();
        qtyLabel = new javax.swing.JLabel();
        lavellabel = new javax.swing.JLabel();
        statuslabel = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        Approve = new javax.swing.JButton();
        Reject = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Backbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        approvalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "PO ID", "PR ID", "Item ID", "REODER LEVEL", "QTY", "Status", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(approvalTable);

        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        POIDlabel.setText("PO ID");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        PRIDlabel.setText("PR ID");

        ITEMlabel.setText("ITEM ID");

        qtyLabel.setText("QTY");

        lavellabel.setText("REORDER LEVEL");

        statuslabel.setText("STATUS");

        datelabel.setText("DATE");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        Approve.setBackground(new java.awt.Color(153, 255, 153));
        Approve.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Approve.setText("Approve");
        Approve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveActionPerformed(evt);
            }
        });

        Reject.setBackground(new java.awt.Color(255, 51, 51));
        Reject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Reject.setText("Reject");
        Reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("APPROVAL PAGE");

        Backbtn.setText("Back");
        Backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Backbtn)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(PRIDlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(POIDlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(ITEMlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datelabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lavellabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(qtyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statuslabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Approve, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Reject, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(316, 316, 316))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(POIDlabel)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PRIDlabel)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ITEMlabel)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(Backbtn)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Reject, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(qtyLabel))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lavellabel)
                                    .addComponent(Approve, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datelabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statuslabel))))
                        .addGap(42, 42, 42))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxActionPerformed
         String selectedItem = (String) ComboBox.getSelectedItem();

        if (selectedItem != null && selectedItem.startsWith("PurchaseOrder: ")) {
            int selectedPurchaseOrderID = Integer.parseInt(selectedItem.replace("PurchaseOrder: ", "").trim());

            PurchaseOrder selectedOrder = purchaseOrderManager.getPurchaseOrderById(selectedPurchaseOrderID);

            if (selectedOrder != null) {
                // Populate text fields with selected purchase order details
                   jTextField1.setText(String.valueOf(selectedOrder.getPurchaseOrderID()));  // PO ID
                    jTextField2.setText(selectedOrder.getPurchaseRequisitionID());            // PR ID
                    jTextField3.setText(selectedOrder.getItemID());                           // Item ID
                    jTextField4.setText(String.valueOf(selectedOrder.getQuantity()));         // Quantity
                    jTextField7.setText(String.valueOf(selectedOrder.getReorderLevel()));     // Reorder Level (fixed)
                    jTextField6.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedOrder.getDate())); // Date
                    jTextField5.setText(selectedOrder.getStatus());                             // Status
            } else {
                JOptionPane.showMessageDialog(this, "Purchase order not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    

    }//GEN-LAST:event_ComboBoxActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void BackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackbtnActionPerformed
        // TODO add your handling code here:
        FinanceMainPage finance_Main = new FinanceMainPage();
        finance_Main.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_BackbtnActionPerformed

    private void ApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveActionPerformed
        // TODO add your handling code here:
        updateOrderStatus("approved"); 
    }//GEN-LAST:event_ApproveActionPerformed

    private void RejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejectActionPerformed
        // TODO add your handling code here:
        updateOrderStatus("rejected");       
    }//GEN-LAST:event_RejectActionPerformed

    
   private void moveApprovedToPayments() {
    List<PurchaseOrder> approvedOrders = new ArrayList<>();
    DefaultTableModel model = (DefaultTableModel) approvalTable.getModel(); // Use the correct JTable name

    for (int i = 0; i < model.getRowCount(); i++) {
        if ("Approved".equalsIgnoreCase(model.getValueAt(i, 5).toString())) { 
            int poID = Integer.parseInt(model.getValueAt(i, 0).toString());
            String prID = model.getValueAt(i, 1).toString();
            String itemID = model.getValueAt(i, 2).toString();
            int quantity = Integer.parseInt(model.getValueAt(i, 4).toString());
            int reorderLevel = Integer.parseInt(model.getValueAt(i, 3).toString());
            String date = model.getValueAt(i, 6).toString();

            // Add approved order to list
            approvedOrders.add(new PurchaseOrder(poID, prID, itemID, quantity, reorderLevel, "approved", date));
        }
    }
    
    // Call method to save the approved orders to payments
    paymentService.moveApprovedOrdersToPayments(approvedOrders);



    paymentService.moveApprovedOrdersToPayments(approvedOrders);


    purchaseOrderManager.saveData(purchaseOrderManager.getAllPurchaseOrders());
}
    
    private void updateOrderStatus(String newStatus) {
    String selectedItem = (String) ComboBox.getSelectedItem();
    int selectedRow = approvalTable.getSelectedRow(); // Use the correct JTable name

    if (selectedRow == -1 || selectedItem == null || !selectedItem.startsWith("PurchaseOrder: ")) {
        JOptionPane.showMessageDialog(this, "Please select a row in the table and an item in the ComboBox.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int selectedPoID = Integer.parseInt(selectedItem.replace("PurchaseOrder: ", "").trim());
        int tablePoID = Integer.parseInt(approvalTable.getValueAt(selectedRow, 0).toString());

        if (tablePoID != selectedPoID) {
            JOptionPane.showMessageDialog(this, "The selected row and ComboBox item do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update status in the PurchaseOrder manager
        purchaseOrderManager.updateOrderStatus(selectedPoID, newStatus);

        JOptionPane.showMessageDialog(this, "Order status updated to " + newStatus);
        populateTable();
        populateComboBox();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Purchase Order ID format.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    private void saveDataToFile() {
        try {
            purchaseOrderManager.saveData(purchaseOrderManager.getAllPurchaseOrders());
            System.out.println("Data successfully saved to file.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Approve().setVisible(true);
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Approve;
    private javax.swing.JButton Backbtn;
    private javax.swing.JComboBox<String> ComboBox;
    private javax.swing.JLabel ITEMlabel;
    private javax.swing.JLabel POIDlabel;
    private javax.swing.JLabel PRIDlabel;
    private javax.swing.JButton Reject;
    private javax.swing.JTable approvalTable;
    private javax.swing.JLabel datelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lavellabel;
    private javax.swing.JLabel qtyLabel;
    private javax.swing.JLabel statuslabel;
    // End of variables declaration//GEN-END:variables

    private static class Table {

        private static int getSelectedRow() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public Table() {
        }
    }
}
