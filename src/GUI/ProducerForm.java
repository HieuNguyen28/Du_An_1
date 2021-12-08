package GUI;

import Controller.Helper.CreateExcel;
import static Controller.Helper.DateSupport.toDate;
import Controller.Helper.Mgsbox;
import Controller.ModelDAO.IdDAO;
import Controller.ModelDAO.ProducerDAO;
import Model.Producer;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProducerForm extends javax.swing.JPanel {

    public ProducerForm() {
        initComponents();
        EditTable(tblProducer);
        loadDataToTabel();
        defaultButton(false);
        defaultText(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new CustomizeGUI.Componets.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new GUI.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtCompanyName = new GUI.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new GUI.TextField();
        dcFounding = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtHotline = new GUI.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducer = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnCreateExcel = new javax.swing.JButton();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 51, 255));
        jLabel6.setText("Producer");

        jLabel1.setText("Producer ID:");

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Company name:");

        txtCompanyName.setEditable(false);
        txtCompanyName.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Founding:");

        jLabel4.setText("Email:");

        txtEmail.setEditable(false);
        txtEmail.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Hotline:");

        txtHotline.setEditable(false);
        txtHotline.setBackground(new java.awt.Color(255, 255, 255));

        tblProducer.setBackground(new java.awt.Color(222, 221, 248));
        tblProducer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producer ID", "Company name", "Pounding", "Hotline", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducer.setFocusable(false);
        tblProducer.setGridColor(new java.awt.Color(15, 106, 205));
        tblProducer.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProducer.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblProducer.setShowVerticalLines(false);
        tblProducer.getTableHeader().setReorderingAllowed(false);
        tblProducer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProducerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducer);

        btnNew.setBackground(new java.awt.Color(92, 84, 179));
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add_32px_1.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(92, 84, 179));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update_32px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(92, 84, 179));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(92, 84, 179));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add_32px_1.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnChange.setBackground(new java.awt.Color(255, 255, 255));
        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/night-mode.png"))); // NOI18N
        btnChange.setBorder(null);
        btnChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChangeMouseClicked(evt);
            }
        });

        btnCreateExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnCreateExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/excel.png"))); // NOI18N
        btnCreateExcel.setText("Export to Excel");
        btnCreateExcel.setBorder(null);
        btnCreateExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtHotline, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(24, 24, 24)
                                        .addComponent(dcFounding, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGap(285, 285, 285)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(53, 53, 53)))
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 47, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11))))))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnCreateExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(dcFounding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHotline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCreateExcel))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        defaultText(true);
        defaultButton(false);
        btnAdd.setEnabled(true);
        createID();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (check()) {
            if (pdao.selectByID(txtID.getText()) != null) {
                Mgsbox.alert(this, "Duplicate producer ID");
            } else {
                insert();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblProducerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProducerMouseClicked
        // TODO add your handling code here:
        this.index = tblProducer.rowAtPoint(evt.getPoint());
        if (this.index >= 0) {
            this.edit();
            defaultText(true);
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
            btnAdd.setEnabled(false);
            txtID.setEditable(false);
        }
    }//GEN-LAST:event_tblProducerMouseClicked

    private void btnChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            btnChange.setToolTipText("Cick 2 for change Background");
            pnlMain.setBackground(Color.decode("#9badf2"));
            Controller.Helper.BackgroundC1.ChangeTxt(txtCompanyName);
            Controller.Helper.BackgroundC1.ChangeTxt(txtEmail);
            Controller.Helper.BackgroundC1.ChangeTxt(txtHotline);
            Controller.Helper.BackgroundC1.ChangeTxt(txtID);
            Controller.Helper.BackgroundC1.ChangeBtn(btnChange);
        } else if (evt.getClickCount() == 2) {
            btnChange.setToolTipText("Cick 1 for change Background");
            pnlMain.setBackground(Color.white);
            Controller.Helper.BackgroundC2.ChangeTxt(txtCompanyName);
            Controller.Helper.BackgroundC2.ChangeTxt(txtEmail);
            Controller.Helper.BackgroundC2.ChangeTxt(txtHotline);
            Controller.Helper.BackgroundC2.ChangeTxt(txtID);
            Controller.Helper.BackgroundC2.ChangeBtn(btnChange);
        }
    }//GEN-LAST:event_btnChangeMouseClicked

    private void btnCreateExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateExcelActionPerformed
        // TODO add your handling code here:
        CreateExcel.ExportToExcel(tblProducer, "Producer");
    }//GEN-LAST:event_btnCreateExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnCreateExcel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser dcFounding;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private CustomizeGUI.Componets.PanelBorder pnlMain;
    private javax.swing.JTable tblProducer;
    private GUI.TextField txtCompanyName;
    private GUI.TextField txtEmail;
    private GUI.TextField txtHotline;
    private GUI.TextField txtID;
    // End of variables declaration//GEN-END:variables
    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    private void clearForm() {
        txtID.setText("");
        txtCompanyName.setText("");
        txtEmail.setText("");
        txtHotline.setText("");
    }

    ProducerDAO pdao = new ProducerDAO();

    private void loadDataToTabel() {
        DefaultTableModel model = (DefaultTableModel) tblProducer.getModel();
        model.setRowCount(0);
        try {
            List<Producer> list = pdao.selectAll();
            for (Producer producer : list) {
                model.addRow(new Object[]{
                    producer.getPdcID(),
                    producer.getPdcCompanyName(),
                    producer.getPdcFoundingDate(),
                    producer.getPdcHotline(),
                    producer.getPdcEmail()
                });
            }
        } catch (Exception e) {
        }
    }

    private void setModel(Producer model) {
        txtCompanyName.setText(model.getPdcCompanyName());
        txtEmail.setText(model.getPdcEmail());
        txtHotline.setText(model.getPdcHotline());
        txtID.setText(model.getPdcID());
        dcFounding.setDate(model.getPdcFoundingDate());
    }

    private Producer getModel() {
        Producer model = new Producer();
        model.setPdcCompanyName(txtCompanyName.getText());
        model.setPdcEmail(txtEmail.getText());
        model.setPdcFoundingDate(toDate(new SimpleDateFormat("yyyy-MM-dd").format(dcFounding.getDate())));
        model.setPdcHotline(txtHotline.getText());
        model.setPdcID(txtID.getText());
        return model;
    }

    private void insert() {
        Producer model = getModel();
        try {
            pdao.insert(model);
            loadDataToTabel();
            clearForm();
            Mgsbox.alert(this, "Insert successfull");
            defaultButton(false);
            defaultText(false);
        } catch (Exception e) {
            Mgsbox.alert(this, "Insert fail");
        }
    }

    private void update() {
        if (Mgsbox.comfirm(this, "Do you really want to update ?")) {
            Producer model = getModel();
            try {
                pdao.update(model);
                loadDataToTabel();
                clearForm();
                Mgsbox.alert(this, "Update successfull");
                defaultButton(false);
                defaultText(true);
            } catch (Exception e) {
                Mgsbox.alert(this, "Update fail");
            }
        }
    }

    private void delete() {
        if (Mgsbox.comfirm(this, "Do you really want to delete ?")) {
            String id = txtID.getText();
            try {
                pdao.delete(id);
                loadDataToTabel();
                clearForm();
                Mgsbox.alert(this, "Delete successful !");
                defaultButton(false);
                defaultText(false);
            } catch (Exception e) {
                Mgsbox.alert(this, "Delete failed !");
            }
        }
    }

    int index = 0;

    private void edit() {
        try {
            String manv = (String) tblProducer.getValueAt(this.index, 0);
            Producer model = pdao.selectByID(manv);
            if (model != null) {
                this.setModel(model);
            }
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }

    private boolean check() {
        if (txtID.getText().equals("")) {
            Mgsbox.alert(this, "Flease fill out producer ID");
            return false;
        } else if (!txtID.getText().matches("^(NS)[0-9]{1,4}$")) {
            Mgsbox.alert(this, "Invalid producer ID. Ex:NS0001");
            return false;
        } else if (txtCompanyName.getText().equals("")) {
            Mgsbox.alert(this, "Flease fill out company name");
            return false;
        } else if (txtHotline.getText().equals("")) {
            Mgsbox.alert(this, "Flease fill out hotline");
            return false;
        } else if (txtEmail.getText().equals("")) {
            Mgsbox.alert(this, "Flease fill out email");
            return false;
        } else if (dcFounding.getDate().equals("")) {
            Mgsbox.alert(this, "Flease choose founding");
            return false;
        }
        return true;
    }

    private void defaultButton(boolean a) {
        btnDelete.setEnabled(a);
        btnAdd.setEnabled(a);
        btnUpdate.setEnabled(a);
    }

    private void defaultText(boolean a) {
        txtCompanyName.setEditable(a);
        txtID.setEditable(a);
        txtEmail.setEditable(a);
        txtHotline.setEditable(a);
    }
    
    IdDAO iddao = new IdDAO();
    private void createID(){
        List<Object> data = iddao.producerID();
        int id = Integer.valueOf(data.get(0).toString()) + 1;
        txtID.setText("NS"+id);
    }
}
