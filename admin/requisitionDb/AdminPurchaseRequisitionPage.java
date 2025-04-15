/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javanexuspots.purchaseManager;
import javanexuspots.loginPage.LoginSession;
import javanexuspots.services.RequisitionService;
import javanexuspots.loginPage.LoginSession;
import javanexuspots.models.Item;
import javanexuspots.models.Requisition;
import javanexuspots.services.ItemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;
import javanexuspots.admin.AdminMainPage;
/**
 *
 * @author chris
 */
public class AdminPurchaseRequisitionPage extends javax.swing.JFrame {
    private final DefaultTableModel tableModel;
    private final RequisitionService requisitionService;
    private final DefaultTableModel reqTableModel;
    private LoginSession loginSession;
    /**
     * Creates new form PurchaseRequisitionPage
     */
    public AdminPurchaseRequisitionPage(LoginSession loginSession) {
         this.loginSession = loginSession;
        initComponents(); // Initialize GUI components

        // Initialize requisition service
        requisitionService = new RequisitionService();

        // Initialize table model
        String[] columnNames = {"Requisition ID", "Item ID", "Quantity", "Required By Date", "Manager ID", "Supplier ID", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ApprovalTable.setModel(tableModel); // Link table model to ApprovalTable
        
        // Initialize table model for req_table
        reqTableModel = new DefaultTableModel(columnNames, 0);
        req_table.setModel(reqTableModel);
        
        // Load pending requisitions
        loadPendingRequisitions();
        loadRequisitionData(); 
        
        req_table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_tableMouseClicked(evt);
            }
        });
        // Load all requisitions into req_table
    }

    private void approveSelectedRequisition() {
        // Verify the table is not null
        if (ApprovalTable == null) {
            JOptionPane.showMessageDialog(this, "Error: Approval table is not initialized.");
            return;
        }

        // Check if a row is selected
        int selectedRow = ApprovalTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a requisition to approve or reject.");
            return;
        }

        // Get requisition ID
        String requisitionId = (String) ApprovalTable.getModel().getValueAt(selectedRow, 0);

        // Retrieve requisition from service
        Requisition requisition = requisitionService.getRequisitionById(requisitionId);
        if (requisition != null) {
            requisition.setStatus("approved");
            requisitionService.updateRequisition(requisition);

            // Show success message and refresh table
            JOptionPane.showMessageDialog(this, "Requisition " + requisitionId + " approved successfully!");
            loadPendingRequisitions(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "Error: Requisition not found for ID: " + requisitionId);
        }
    }

    private void RejectSelectedRequisition() {
        // Verify the table is not null
        if (ApprovalTable == null) {
            JOptionPane.showMessageDialog(this, "Error: Approval table is not initialized.");
            return;
        }

        // Check if a row is selected
        int selectedRow = ApprovalTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a requisition to approve or reject.");
            return;
        }

        // Get requisition ID
        String requisitionId = (String) ApprovalTable.getModel().getValueAt(selectedRow, 0);

        // Retrieve requisition from service
        Requisition requisition = requisitionService.getRequisitionById(requisitionId);
        if (requisition != null) {
            requisition.setStatus("rejected");
            requisitionService.updateRequisition(requisition);

            // Show success message and refresh table
            JOptionPane.showMessageDialog(this, "Requisition " + requisitionId + " rejected successfully!");
            loadPendingRequisitions(); // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "Error: Requisition not found for ID: " + requisitionId);
        }
    }

    private void loadPendingRequisitions() {
        tableModel.setRowCount(0); // Clear current table data
        List<Requisition> requisitions = requisitionService.getAllRequisitions();

        System.out.println("Total requisitions: " + requisitions.size());
        for (Requisition req : requisitions) {
            if ("pending".equalsIgnoreCase(req.getStatus())) {
                tableModel.addRow(new Object[]{
                    req.getRequisitionId(),
                    req.getItemId(),
                    req.getQuantity(),
                    req.getRequiredByDate(),
                    req.getManagerId(),
                    req.getSupplierId(),
                    req.getStatus()
                });
            }
        }
    }
    
    private void loadRequisitionData() {
        try {
            // Clear existing rows
            DefaultTableModel reqTableModels = (DefaultTableModel) req_table.getModel();
            reqTableModels.setRowCount(0);

            // Initialize ItemService
            ItemService itemService = new ItemService();

            // Retrieve all items for comboBox
            List<Item> items = itemService.getAllItems();
            cmbItem.removeAllItems();
            for (Item item : items) {
                cmbItem.addItem(item.getItemId()); // Populate ComboBox with item IDs
            }

            // Retrieve all requisitions
            List<Requisition> requisitions = requisitionService.getAllRequisitions();
            for (Requisition req : requisitions) {
                reqTableModel.addRow(new Object[]{
                    req.getRequisitionId(),
                    req.getItemId(),
                    req.getQuantity(),
                    req.getRequiredByDate(),
                    req.getManagerId(),
                    req.getSupplierId(),
                    req.getStatus()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading requisitions: " + ex.getMessage());
        }
    }
    
    private void req_tableMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = req_table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                String itemId = req_table.getValueAt(selectedRow, 1).toString();
                String quantity = req_table.getValueAt(selectedRow, 2).toString();
                Date requiredByDate = (Date) req_table.getValueAt(selectedRow, 3);

                // Set values to input fields
                cmbItem.setSelectedItem(itemId);
                txtQuan.setText(quantity);
                jDateChooser1.setDate(requiredByDate);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error setting selected row data: " + ex.getMessage());
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ApprovalTable = new javax.swing.JTable();
        ApprovedButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        RejectButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtQuan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        req_table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBack2 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        cmbItem = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ApprovalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "requisitionID", "itemID", "Quantity", "RequiredByDate", "ManagerID", "SupplierID", "Status"
            }
        ));
        jScrollPane1.setViewportView(ApprovalTable);

        ApprovedButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ApprovedButton.setText("Approve");
        ApprovedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApprovedButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setText("Purchase Requisition");

        btnBack1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        RejectButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RejectButton.setText("Reject");
        RejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejectButtonActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*Note: Please select the desired purchase requisition by clicking on the row inside the table to approve/reject the requisition");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RejectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ApprovedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ApprovedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RejectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Approval Purchase Requisition", jPanel2);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setText("Purchase Requisition");

        txtQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Quantity     ");

        req_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Requisition ID", "Item ID", "Quantity", "Required By Date", "Manager ID", "Supplier ID", "Status"
            }
        ));
        jScrollPane2.setViewportView(req_table);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Date");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        btnBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        cmbItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbItemActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Item ID");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Please fill in the fields above to update the database.");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*Note: Please select the desired purchase requisition by clicking on the row inside the table before choosing edit/delete.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(560, 560, 560)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create Purchase Requisition", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuanActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        new AdminMainPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = req_table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }
        try {
            String requisitionId = (String) req_table.getValueAt(selectedRow, 0);
            requisitionService.deleteRequisition(requisitionId);
            JOptionPane.showMessageDialog(this, "Requisition deleted successfully!");

            // Refresh table data
            loadRequisitionData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            // Initialize ItemService
            ItemService itemService = new ItemService();

            String requisitionId = requisitionService.generateNextRequisitionId();
            String itemId = cmbItem.getSelectedItem().toString();
            int quantity = Integer.parseInt(txtQuan.getText());
            Date requiredByDate = jDateChooser1.getDate();
            String managerId = loginSession.getCurrentUserSession();

            // Get supplierId dynamically from ItemService
            Item selectedItem = itemService.getItemById(itemId);
            String supplierId = selectedItem.getSupplierId();
            String status = "approved"; // Automatically set to approved

            // Create requisition object
            Requisition requisition = new Requisition(requisitionId, itemId, quantity, requiredByDate, managerId, supplierId, status);

            // Add to service
            requisitionService.addRequisition(requisition);
            JOptionPane.showMessageDialog(this, "Requisition added successfully with status: Approved!");

            // Refresh table data
            loadRequisitionData();

            // Clear input fields
            txtQuan.setText("");
            jDateChooser1.setDate(null);
            cmbItem.setSelectedIndex(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = req_table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        try {
            // Initialize ItemService
            ItemService itemService = new ItemService();

            // Get updated values from input fields
            String requisitionId = (String) req_table.getValueAt(selectedRow, 0);
            String itemId = cmbItem.getSelectedItem().toString();
            int quantity = Integer.parseInt(txtQuan.getText());
            Date requiredByDate = jDateChooser1.getDate();
            String managerId = loginSession.getCurrentUserSession();

            // Get supplierId dynamically from ItemService
            Item selectedItem = itemService.getItemById(itemId);
            String supplierId = selectedItem.getSupplierId();
            String status = "approved";

            Requisition updatedRequisition = new Requisition(requisitionId, itemId, quantity, requiredByDate, managerId, supplierId, status);
            requisitionService.updateRequisition(updatedRequisition);
            JOptionPane.showMessageDialog(this, "Requisition updated successfully!");

            // Refresh table data
            loadRequisitionData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void ApprovedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApprovedButtonActionPerformed
        // TODO add your handling code here:
        approveSelectedRequisition();
    }//GEN-LAST:event_ApprovedButtonActionPerformed

    private void cmbItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbItemActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadRequisitionData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        new AdminMainPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void RejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejectButtonActionPerformed
        // TODO add your handling code here:
        RejectSelectedRequisition();
    }//GEN-LAST:event_RejectButtonActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPurchaseRequisitionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPurchaseRequisitionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPurchaseRequisitionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPurchaseRequisitionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
LoginSession loginSession = new LoginSession();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPurchaseRequisitionPage(loginSession).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ApprovalTable;
    private javax.swing.JButton ApprovedButton;
    private javax.swing.JButton RejectButton;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbItem;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable req_table;
    private javax.swing.JTextField txtQuan;
    // End of variables declaration//GEN-END:variables
}
