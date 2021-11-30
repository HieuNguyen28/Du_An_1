package GUI;

import Controller.Helper.Image_Auth;
import Controller.Helper.Mgsbox;
import Event.EventMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        lblMainFrame.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblMainFrame.setBackground(Color.decode("#deddf8"));
        pnlMain.setBackground(Color.decode("#deddf8"));
        init();
    }

    private void init() {
        new Timer(1000, new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            @Override
            public void actionPerformed(ActionEvent e) {
                lblTime.setText(sdf.format(new Date()));
            }
        }).start();
        setBackground(new Color(0, 0, 0, 0));
        menu2.initMoving(this);
        menu2.addEventMenu(new EventMenu() {
            @Override
            public void menuIndexChange(int index) {
                if (index == 0) {
                    showForm(new ReceiptForm());
                }else if (index == 1) {
                    showForm(new EmployeeGUI());
                }else if (index == 2) {
                    showForm(new WarehouseForm());
                }else if (index == 3) {
                    showForm(new MedicineForm());
                }else if (index == 4) {
                    showForm(new MedicineTypeForm());
                }else if (index == 5) {
                    showForm(new StatisForm());
                }else if (index == 6) {
                    showForm(new CustomerForm());
                }else if (index == 7) {
                    showForm(new VoucherForm());
                }else if (index == 8) {
                    showForm(new ProducerForm());
                }else if (index == 9) {
                    if (Mgsbox.comfirm(null, "Do you really want to sign out ?")) {
                        Image_Auth.logOff();
                        dispose();
                        new LoginForm().setVisible(true);
                    }
                }else if (index == 10) {
                    if (Mgsbox.comfirm(null, "Do you really want to exit ?")) {
                        System.exit(0);
                    }
                }
            }
        });
    }
    
    private void showForm(Component com){
        pnlMain.removeAll();
        pnlMain.add(com);
        pnlMain.revalidate();
        pnlMain.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new GUI.PanelBorder();
        lblTime = new javax.swing.JLabel();
        menu2 = new GUI.Menu();
        pnlMain = new GUI.PanelBorder();
        lblMainFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setPreferredSize(new java.awt.Dimension(909, 608));

        lblTime.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 153));
        lblTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/clock.png"))); // NOI18N

        pnlMain.setPreferredSize(new java.awt.Dimension(909, 313));
        pnlMain.setLayout(new java.awt.BorderLayout());

        lblMainFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MainFrame.png"))); // NOI18N
        pnlMain.add(lblMainFrame, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(539, 539, 539)
                                .addComponent(lblTime))
                            .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Main().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMainFrame;
    private javax.swing.JLabel lblTime;
    private GUI.Menu menu2;
    private GUI.PanelBorder panelBorder1;
    private GUI.PanelBorder pnlMain;
    // End of variables declaration//GEN-END:variables
}
