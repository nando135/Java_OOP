package javanexuspots.financeManager;

import javanexuspots.loginPage.LoginForm;
import javanexuspots.loginPage.LoginSession;

public class FinanceMainPage extends javax.swing.JFrame {

    public FinanceMainPage() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        View_btn = new javax.swing.JButton();
        Approval_btn = new javax.swing.JButton();
        Payment_brn = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("FINANCE");

        View_btn.setText("VIEW STOCK");
        View_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_btnActionPerformed(evt);
            }
        });

        Approval_btn.setText("Approval");
        Approval_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Approval_btnActionPerformed(evt);
            }
        });

        Payment_brn.setText("Payment");
        Payment_brn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Payment_brnActionPerformed(evt);
            }
        });

        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(View_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Approval_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Payment_brn, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(View_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Approval_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Payment_brn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LogoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void View_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_btnActionPerformed
        // TODO add your handling code here:
        ViewStock viewStockPage = new ViewStock();
        viewStockPage.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_View_btnActionPerformed

    private void Approval_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Approval_btnActionPerformed
        Approve approve = new Approve(); 
        approve.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Approval_btnActionPerformed

    private void Payment_brnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Payment_brnActionPerformed
        // TODO add your handling code here:
        PaymentForm payment = new PaymentForm(); 
        payment.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Payment_brnActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        LoginSession.clearSession(); // clear login session

        System.out.println("Login session: "+LoginSession.getCurrentUserSession());

        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }//GEN-LAST:event_LogoutBtnActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinanceMainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Approval_btn;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JButton Payment_brn;
    private javax.swing.JButton View_btn;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
