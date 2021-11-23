package GUI;

import Controller.Helper.Image_Auth;
import Controller.Helper.Mgsbox;
import Event.EventMenu;
import Model.Employee;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends javax.swing.JFrame {

    public Main(Employee a) {
        initComponents();
        lblMainFrame.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblMainFrame.setBackground(Color.decode("#deddf8"));
        pnlMain.setBackground(Color.decode("#deddf8"));
        prepareUI(pnlMain, pnlEmployee, pnlWarehouse, pnlMedicine, pnlMedicineType, pnlReceipt, pnlCustomer);
        init();
    }

    private void prepareUI(JPanel... jPanels) {
        jPanels[0].setVisible(true);
        for (int i = 1; i < jPanels.length; i++) {
            jPanels[i].setVisible(false);
        }
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        menu2.initMoving(this);
        menu2.addEventMenu(new EventMenu() {
            @Override
            public void menuIndexChange(int index) {
                if (index == 8) {
                    if (Mgsbox.comfirm(null, "Do you really want to exit?")) {
                        JOptionPane.showMessageDialog(null, "See you again");
                        System.exit(0);
                    }
                } else if (index == 0) {
                    prepareUI(pnlEmployee, pnlMain, pnlWarehouse, pnlMedicine, pnlMedicineType, pnlReceipt, pnlCustomer);
                } else if (index == 1) {
                    prepareUI(pnlWarehouse, pnlMain, pnlMedicine, pnlMedicineType, pnlEmployee, pnlReceipt, pnlCustomer);
                } else if (index == 2) {
                    prepareUI(pnlMedicine, pnlMain, pnlWarehouse, pnlMedicineType, pnlEmployee, pnlReceipt, pnlCustomer);
                } else if (index == 3) {
                    prepareUI(pnlMedicineType, pnlMain, pnlWarehouse, pnlMedicine, pnlEmployee, pnlReceipt, pnlCustomer);
                } else if (index == 7) {
                    if (Mgsbox.comfirm(null, "Do you really want to sign out?")) {
                        dispose();
                        new LoginForm().setVisible(true);
                    }
                } else if (index == 5) {
                    prepareUI(pnlReceipt, pnlMedicineType, pnlMain, pnlWarehouse, pnlMedicine, pnlEmployee, pnlCustomer);
                } else if (index == 4) {
                    System.out.println("The function is developing");
                } else if (index == 6) {
                    prepareUI(pnlCustomer, pnlReceipt, pnlMedicineType, pnlMain, pnlWarehouse, pnlMedicine, pnlEmployee);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new GUI.PanelBorder();
        pnlMain = new javax.swing.JPanel();
        lblMainFrame = new javax.swing.JLabel();
        pnlEmployee = new GUI.EmployeeGUI();
        pnlWarehouse = new GUI.WarehouseForm();
        pnlMedicine = new GUI.MedicineForm();
        pnlMedicineType = new GUI.MedicineTypeForm();
        menu2 = new GUI.Menu();
        pnlReceipt = new GUI.ReceiptForm();
        pnlCustomer = new GUI.CustomerForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMain.setBackground(new java.awt.Color(242, 242, 242));

        lblMainFrame.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMainFrame.setForeground(new java.awt.Color(255, 0, 0));
        lblMainFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MainFrame.png"))); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelBorder1.add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 0, 930, 599));
        panelBorder1.add(pnlEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 0, 930, 600));
        panelBorder1.add(pnlWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, -1));
        panelBorder1.add(pnlMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, -1));
        panelBorder1.add(pnlMedicineType, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, -1));
        panelBorder1.add(menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 256, 590));
        panelBorder1.add(pnlReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, -1));
        panelBorder1.add(pnlCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private GUI.Menu menu2;
    private GUI.PanelBorder panelBorder1;
    private GUI.CustomerForm pnlCustomer;
    private GUI.EmployeeGUI pnlEmployee;
    private javax.swing.JPanel pnlMain;
    private GUI.MedicineForm pnlMedicine;
    private GUI.MedicineTypeForm pnlMedicineType;
    private GUI.ReceiptForm pnlReceipt;
    private GUI.WarehouseForm pnlWarehouse;
    // End of variables declaration//GEN-END:variables
}
