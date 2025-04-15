
package javanexuspots.financeManager;

import javanexuspots.models.Item;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import javanexuspots.services.ItemService;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class ViewStock extends javax.swing.JFrame {
    
    private final ItemService itemService;
    private final String filePath;

    
     public ViewStock() {
        initComponents();
        this.itemService = new ItemService();
        this.filePath = "src/javanexuspots/databases/items.txt"; // Adjust this path if needed
        loadInventoryData(); // Load initial data into the table
        startFileWatcher(); // Start monitoring the file for changes
    }


     private void loadInventoryData() {
        List<Item> items = itemService.getAllItems(); // Fetch items from ItemService

        // Access the JTable's model and clear existing data
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Add rows to the JTable model
        for (Item item : items) {
            Object[] rowData = {
                item.getItemId(),
                item.getItemCode(),
                item.getItemName(),
                item.getSupplierId(),
                item.getPrice(),
                item.getReorderLevel(),
                item.getStockLevel()
            };
            model.addRow(rowData);
        }
    }
  
    
    private void filterDataByItemName(String itemName) {
        List<Item> items = itemService.getAllItems(); // Fetch items from ItemService

        // Access the JTable's model and clear existing data
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Add rows that match the filter condition
        for (Item item : items) {
            if (item.getItemName().toLowerCase().contains(itemName.toLowerCase())) {
                Object[] rowData = {
                    item.getItemId(),
                    item.getItemCode(),
                    item.getItemName(),
                    item.getSupplierId(),
                    item.getPrice(),
                    item.getReorderLevel(),
                    item.getStockLevel()
                };
                model.addRow(rowData);
            }
        }
    }
    
    
    private void startFileWatcher() {
        new Thread(() -> {
            try {
                Path path = Paths.get(filePath).getParent();
                WatchService watchService = FileSystems.getDefault().newWatchService();
                path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY &&
                                event.context().toString().equals(Paths.get(filePath).getFileName().toString())) {
                            // Reload the table data when the file is modified
                            SwingUtilities.invokeLater(this::loadInventoryData);
                        }
                    }
                    key.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Filter_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Item Code", "Item Name ", "Supplier ID", "Price", "Reorder Level", "Stock Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("VIEW STOCK");

        Filter_btn.setText("Filter");
        Filter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Filter_btnActionPerformed(evt);
            }
        });

        jLabel2.setText("FILTER DATA");

        TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(Filter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Filter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        FinanceMainPage financeMainpage = new FinanceMainPage();
        financeMainpage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void Filter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filter_btnActionPerformed
        // TODO add your handling code here:
        String itemName = TextField.getText();
        filterDataByItemName(itemName);

    }//GEN-LAST:event_Filter_btnActionPerformed

    private void TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton Filter_btn;
    private javax.swing.JTextField TextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
