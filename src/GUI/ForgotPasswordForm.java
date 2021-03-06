/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.Helper.CreateCapcha;
import Controller.Helper.EmailSupport;
import Controller.Helper.Mgsbox;
import Controller.ModelDAO.EmployeeDAO;
import Model.Employee;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author Laxus
 */
public class ForgotPasswordForm extends javax.swing.JFrame {

    /**
     * Creates new form ForgotPassword
     */
    public ForgotPasswordForm() {
        initComponents();
        pnlStep2.setVisible(false);
        pnlStep3.setVisible(false);
        lblSendAgain.setVisible(false);

    }

    private List<Employee> list = new EmployeeDAO().selectAll();
    Employee staff = null;
    private String code = "";
    Thread alertStatus;
    Thread send;

    private void sendMail() throws MessagingException {
        code = createCapcha();
        String message = "Hello " + staff.getEpeName() + "\n This is your capcha to reset your password: ";

        send = new Thread() {
            @Override
            public void run() {
                try {
                    EmailSupport.send(staff.getEpeEmail(), "CAPCHA FOR YOU", message + code, null);
                } catch (MessagingException | IOException ex) {
                    Logger.getLogger(ForgotPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        send.start();
        System.out.println(code);
        alertStatus = new Thread() {
            int j = 60;
            @Override
            public void run() {
                while (true) {
                    try {
                        j--;
                        lblStatusCode.setText("Email has been sent! Captcha is valid for " + j + "s");
                        Thread.sleep(1000);
                        if (j == 0) {
                            code = createCapcha();
                            System.out.println("Code end:"+code);
                            lblStatusCode.setText("Are you recived my email?");
                            lblSendAgain.setVisible(true);
                            break;
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        alertStatus.start();
    }

    private String createCapcha() {
        CreateCapcha cc = new CreateCapcha();
        BufferedImage bi = cc.getCaptchaImage();
        return cc.getCaptchaString();
    }

    private Employee findAccount(String user) {
        for (Employee e : list) {
            if (e.getEpeUserName().equals(user) || e.getEpeEmail().equals(user)) {
                return e;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblStep1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        pnlStep3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblStep3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new GUI.PasswordField();
        txtPasswordConfirm = new GUI.PasswordField();
        lblStatus = new javax.swing.JLabel();
        txtUser = new GUI.TextField();
        pnlStep2 = new javax.swing.JPanel();
        lblCodeIcon = new javax.swing.JLabel();
        txtEmailCode = new GUI.TextField();
        btnSumitCode = new javax.swing.JButton();
        lblStatusCode = new javax.swing.JLabel();
        lblStep2 = new javax.swing.JLabel();
        lblSendAgain = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Forgot Password");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1090, 528));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(457, 490));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/forgotPassword.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Forgot Password");

        lblStep1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblStep1.setText("Step 1: Provide username or email");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user.png"))); // NOI18N

        btnSubmit.setBackground(new java.awt.Color(0, 204, 51));
        btnSubmit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        pnlStep3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/email.png"))); // NOI18N

        lblStep3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblStep3.setText("Step 3: Provide new password");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("New password:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Confirm:");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStep3Layout = new javax.swing.GroupLayout(pnlStep3);
        pnlStep3.setLayout(pnlStep3Layout);
        pnlStep3Layout.setHorizontalGroup(
            pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStep3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStep3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(pnlStep3Layout.createSequentialGroup()
                        .addComponent(lblStep3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(130, Short.MAX_VALUE))))
        );
        pnlStep3Layout.setVerticalGroup(
            pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStep3Layout.createSequentialGroup()
                .addComponent(lblStep3)
                .addGap(9, 9, 9)
                .addGroup(pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlStep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblStatus.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(0, 153, 102));

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        pnlStep2.setBackground(new java.awt.Color(255, 255, 255));

        lblCodeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/code.png"))); // NOI18N

        btnSumitCode.setBackground(new java.awt.Color(102, 51, 255));
        btnSumitCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSumitCode.setForeground(new java.awt.Color(255, 255, 255));
        btnSumitCode.setText("Confirm");
        btnSumitCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumitCodeActionPerformed(evt);
            }
        });

        lblStep2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblStep2.setText("Step 2: Provide your code in email");

        lblSendAgain.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        lblSendAgain.setText("Click me to send again!");
        lblSendAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSendAgainMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlStep2Layout = new javax.swing.GroupLayout(pnlStep2);
        pnlStep2.setLayout(pnlStep2Layout);
        pnlStep2Layout.setHorizontalGroup(
            pnlStep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStep2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStatusCode, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStep2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSendAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(pnlStep2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlStep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStep2Layout.createSequentialGroup()
                        .addComponent(lblStep2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlStep2Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(lblCodeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailCode, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSumitCode, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        pnlStep2Layout.setVerticalGroup(
            pnlStep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStep2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStep2)
                .addGap(18, 18, 18)
                .addGroup(pnlStep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmailCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSumitCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCodeIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatusCode, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(lblSendAgain))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblStep1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlStep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlStep3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(lblStep1)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(pnlStep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(pnlStep3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String inputPassword = txtPassword.getText().trim(),
                inputPasswordConfirm = txtPasswordConfirm.getText().trim();
        if (inputPassword== null || inputPasswordConfirm == null) {
            Mgsbox.error(this, "Please enter your new password!");
        }else if (!inputPassword.equals(inputPasswordConfirm)) {
            Mgsbox.error(this, "Two passwords must be the same!!!");
        }else if ( inputPassword.length()<10) {
            Mgsbox.alert(this, "Password must contain 10 characters!");
        }else{
            new EmployeeDAO().updatePassword(inputPassword, staff.getEpeID());
            Mgsbox.alert(this, "Changed password successfully! Don't forget again....");
            this.dispose();
            index.main(null);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnSumitCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumitCodeActionPerformed
        String inputCaptcha = txtEmailCode.getText().trim();
        if (inputCaptcha.length()==0) {
            Mgsbox.error(this, "Your captcha is null!");
            pnlStep3.setVisible(false);
        }else{
            if (inputCaptcha.equals(code)) {
                pnlStep3.setVisible(true);
                lblStatusCode.setText("Your code is correct! Let's do Step 3...");
                lblStatusCode.setForeground(Color.GREEN);
                lblSendAgain.setVisible(false);
            }else{
                Mgsbox.error(this, "Your captcha is invalid!");
                txtEmailCode.setText("");
            }
        }
    }//GEN-LAST:event_btnSumitCodeActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtUserKeyPressed

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUserKeyTyped

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUserActionPerformed

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        // TODO add your handling code here:
        staff = null;
        String input = txtUser.getText().trim();
        staff = findAccount(input);
        if (input.length() == 0) {
            lblStatus.setText("");
        } else if (staff != null && evt.getKeyCode()!= KeyEvent.VK_SHIFT) {
            pnlStep2.setVisible(true);
            lblStatus.setText("Are you is " + staff.getEpeName() + " ? Let's do Step 2!");
            lblStatus.setForeground(Color.GREEN);
            try {
                sendMail();
            } catch (MessagingException ex) {
                Logger.getLogger(ForgotPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblStatus.setText("Account does not exist!");
            lblStatus.setForeground(Color.red);
            pnlStep2.setVisible(false);            
        }
    }//GEN-LAST:event_txtUserKeyReleased

    private void lblSendAgainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSendAgainMouseClicked
        lblSendAgain.setVisible(false);
        try {
            sendMail();
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPasswordForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lblSendAgainMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ForgotPasswordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ForgotPasswordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ForgotPasswordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ForgotPasswordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ForgotPasswordForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSumitCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCodeIcon;
    protected javax.swing.JLabel lblSendAgain;
    private javax.swing.JLabel lblStatus;
    protected javax.swing.JLabel lblStatusCode;
    private javax.swing.JLabel lblStep1;
    private javax.swing.JLabel lblStep2;
    private javax.swing.JLabel lblStep3;
    private javax.swing.JPanel pnlStep2;
    private javax.swing.JPanel pnlStep3;
    private GUI.TextField txtEmailCode;
    private GUI.PasswordField txtPassword;
    private GUI.PasswordField txtPasswordConfirm;
    private GUI.TextField txtUser;
    // End of variables declaration//GEN-END:variables

}
