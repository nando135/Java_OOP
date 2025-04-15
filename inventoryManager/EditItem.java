/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javanexuspots.inventoryManager;

import javanexuspots.models.Item;
import javanexuspots.services.ItemService;
import javanexuspots.services.SupplierService;
import java.util.List;
import javanexuspots.models.InventoryUpdates;
import javanexuspots.services.InventoryUpdateService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus ROG
 */
public class EditItem extends javax.swing.JFrame {
    
    private final ItemService itemService;
    private final SupplierService supplierService;

    /**
     * Creates new form AdminAddUser
     */
    public EditItem() {
        initComponents();
        itemService = new ItemService();
        supplierService = new SupplierService();
        loadItemIDComboBox();
    }
        private void loadItemIDComboBox() {
            List<Item> items = itemService.getAllItems();
            DefaultComboBoxModel<String> itemIdModel = new DefaultComboBoxModel<>();

            // Populate the combo box with item IDs
            for (Item item : items) {
                itemIdModel.addElement(item.getItemId());
            }
            itemIDCmbBox.setModel(itemIdModel);

            // Add listener to load details when an item ID is selected
            itemIDCmbBox.addActionListener(evt -> {
                String selectedItemId = (String) itemIDCmbBox.getSelectedItem();
                if (selectedItemId != null) {
                loadItemDetails(selectedItemId);
                }
            });

            // Load details for the first item by default if the list is not empty
            if (!items.isEmpty()) {
            loadItemDetails(items.get(0).getItemId());
            }
        }
            private void loadItemDetails(String itemId) {
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                itemCodeTxtField.setText(item.getItemCode());
                itemNameTxtField.setText(item.getItemName());
                supplierIDTxtField.setText(item.getSupplierId());
                priceTxtField.setText(String.valueOf(item.getPrice()));
                reorderLevelTxtField.setText(String.valueOf(item.getReorderLevel()));
                stockLevelTxtField.setText(String.valueOf(item.getStockLevel()));
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

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        itemCodeTxtField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        itemNameTxtField = new javax.swing.JTextField();
        reorderLevelTxtField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        supplierIDTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        editItemBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        itemIDCmbBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        priceTxtField = new javax.swing.JTextField();
        stockLevelTxtField = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("ItemID:");

        jLabel3.setText("Item Code:");

        itemCodeTxtField.setToolTipText("");
        itemCodeTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodeTxtFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Stock Level:");

        jLabel5.setText("Item Name");

        itemNameTxtField.setText("-");
        itemNameTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameTxtFieldActionPerformed(evt);
            }
        });

        reorderLevelTxtField.setText("-");
        reorderLevelTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reorderLevelTxtFieldActionPerformed(evt);
            }
        });

        jLabel7.setText("Reorder Level:");

        jLabel6.setText("Supplier ID:");

        supplierIDTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierIDTxtFieldActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Edit Existing Item");

        editItemBtn.setBackground(new java.awt.Color(51, 102, 255));
        editItemBtn.setForeground(new java.awt.Color(255, 255, 255));
        editItemBtn.setText("Edit User");
        editItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemBtnActionPerformed(evt);
            }
        });

        CancelBtn.setBackground(new java.awt.Color(153, 153, 153));
        CancelBtn.setText("Cancel");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });

        itemIDCmbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDCmbBoxActionPerformed(evt);
            }
        });

        jLabel8.setText("Price:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(itemIDCmbBox, 0, 133, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemCodeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stockLevelTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reorderLevelTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplierIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(CancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addContainerGap(584, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(itemIDCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(itemCodeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(reorderLevelTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(stockLevelTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelBtn)
                    .addComponent(editItemBtn))
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jLabel2)
                    .addContainerGap(193, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCodeTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodeTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCodeTxtFieldActionPerformed

    private void itemNameTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameTxtFieldActionPerformed

    private void reorderLevelTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reorderLevelTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reorderLevelTxtFieldActionPerformed

    private void supplierIDTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierIDTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIDTxtFieldActionPerformed

    private void editItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemBtnActionPerformed
        String itemId = (String) itemIDCmbBox.getSelectedItem();
        String itemCode = itemCodeTxtField.getText().trim();
        String itemName = itemNameTxtField.getText().trim();
        String supplierId = supplierIDTxtField.getText().trim();
        String priceText = priceTxtField.getText().trim();
        String reorderLevelText = reorderLevelTxtField.getText().trim();
        String stockLevelText = stockLevelTxtField.getText().trim();

        try {
            // Validation
            if (itemId == null || itemId.isEmpty()) {
                throw new IllegalArgumentException("No Item ID selected.");
            }
            if (itemCode.isEmpty() || itemName.isEmpty()) {
                throw new IllegalArgumentException("Item Code and Item Name cannot be empty.");
            }
            if (supplierId.isEmpty() || supplierService.getSupplierById(supplierId) == null) {
                throw new IllegalArgumentException("Invalid Supplier ID. Please ensure the supplier exists.");
            }

            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
            }

            int reorderLevel = Integer.parseInt(reorderLevelText);
            if (reorderLevel < 0) {
                throw new IllegalArgumentException("Reorder Level cannot be negative.");
            }

            int stockLevel = Integer.parseInt(stockLevelText);
            if (stockLevel < 0) {
                throw new IllegalArgumentException("Stock Level cannot be negative.");
            }
            
            InventoryUpdateService inventoryUpdateService = new InventoryUpdateService();
            List<Item> items = itemService.getAllItems();
            
            Item originalItem = items.stream()
                .filter(i -> i.getItemId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

            // Update item in storage/database
            Item updatedItem = new Item(itemId, itemCode, itemName, supplierId, price, reorderLevel, stockLevel);
            itemService.updateItem(updatedItem);

            
            // logging inventory updates
            int changeInStock = stockLevel - originalItem.getStockLevel(); // calculate stock change
            InventoryUpdates editUpdate = inventoryUpdateService.createEditUpdate(itemId, changeInStock);
            inventoryUpdateService.writeInventoryUpdate(editUpdate);

            JOptionPane.showMessageDialog(this, "Item updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            itemCodeTxtField.setText("");
            itemNameTxtField.setText("");
            supplierIDTxtField.setText("");
            priceTxtField.setText("");
            reorderLevelTxtField.setText("");
            stockLevelTxtField.setText("");

            // Return to previous menu or close
            this.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid numeric value. Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                }

    }//GEN-LAST:event_editItemBtnActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        new MainMenu().setVisible(true);;
        this.dispose();
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void itemIDCmbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDCmbBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDCmbBoxActionPerformed


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
            java.util.logging.Logger.getLogger(EditItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton editItemBtn;
    private javax.swing.JTextField itemCodeTxtField;
    private javax.swing.JComboBox<String> itemIDCmbBox;
    private javax.swing.JTextField itemNameTxtField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JTextField reorderLevelTxtField;
    private javax.swing.JTextField stockLevelTxtField;
    private javax.swing.JTextField supplierIDTxtField;
    // End of variables declaration//GEN-END:variables
}
