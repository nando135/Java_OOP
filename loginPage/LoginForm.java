package javanexuspots.loginPage;

import javanexuspots.services.LoginAuthenticationService;
import javanexuspots.services.UserService;
import javanexuspots.admin.*;
import javanexuspots.financeManager.FinanceMainPage;
import javanexuspots.purchaseManager.Main_menu_PM;
import javanexuspots.salesManager.salesmanager;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPageForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginPageForm.setBackground(new java.awt.Color(255, 255, 255));
        loginPageForm.setName("loginPageForm"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nexus POTS Login");

        passwordField.setName("passwordField"); // NOI18N

        usernameField.setName("usernameField"); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        loginBtn.setBackground(new java.awt.Color(51, 102, 255));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Username:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password:");

        javax.swing.GroupLayout loginPageFormLayout = new javax.swing.GroupLayout(loginPageForm);
        loginPageForm.setLayout(loginPageFormLayout);
        loginPageFormLayout.setHorizontalGroup(
            loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPageFormLayout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPageFormLayout.createSequentialGroup()
                        .addGroup(loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginBtn)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPageFormLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66))))
        );
        loginPageFormLayout.setVerticalGroup(
            loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPageFormLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginPageFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        passwordField.getAccessibleContext().setAccessibleName("passwordField");
        usernameField.getAccessibleContext().setAccessibleName("usernameField");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPageForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPageForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        loginPageForm.getAccessibleContext().setAccessibleName("loginPageForm");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // debug: Print input values
        System.out.println("Login attempt - Username: " + username);
        System.out.println("Login attempt - Password length: " + password.length());

        try {
            UserService userManagement = new UserService();
            LoginAuthenticationService LoginAuthenticationService = new LoginAuthenticationService(userManagement);


            String role = LoginAuthenticationService.validateUser(username, password);

            System.out.println("Session Role: " + role);

            if (role != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                LoginSession.setCurrentUserSession(username); // Save user session
                System.out.println("Login session created - Username: " + LoginSession.getCurrentUserSession());

                switch (role) {
                    case "ADM":
                        new AdminMainPage().setVisible(true);
                        break;
                    case "SM":
                        new salesmanager().setVisible(true);
                        break;
                    case "IM":
//                        new InventoryManagerPage().setVisible(true);
                        break;
                    case "FM":
                        new FinanceMainPage().setVisible(true);
                        break;
                    case "PM":
                        new Main_menu_PM().setVisible(true);
                        break;
                    default:
                        System.out.println("Invalid role detected: " + role);
                        JOptionPane.showMessageDialog(this, "Invalid role detected. Please contact the administrator.");
                        break;
                }
                this.dispose(); // Close the login form
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_loginBtnActionPerformed



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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPanel loginPageForm;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
