package GUI;


import Controller.Helper.DateSupport;
import Controller.Helper.Image_Auth;
import Controller.Helper.QRCodeSupport;
import Controller.ModelDAO.MedicineDAO;
import Controller.ModelDAO.VoucherDAO;
import Controller.Helper.Mgsbox;
import Controller.Helper.ValidateSupport;
import Model.Voucher;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VoucherForm extends javax.swing.JPanel {

    /**
     * Creates new form VoucherForm
     *
     * @throws com.google.zxing.WriterException
     */
    public VoucherForm() {
        initComponents();
        EditTable(tblVoucher);
        loadDataToTable();
        loadComboboxMedicine();
        setStatus(false);

        dcDayStart.setMinSelectableDate(DateSupport.now());
        dcDayEnd.setMinSelectableDate(DateSupport.now());
        btnAdd.setVisible(false);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
    }

    private boolean isDuplicateID(String ID) {
        return new VoucherDAO().selectByID(ID) != null;
    }

    private void setStatus(boolean flag) {
        if (flag) {
            txtVoucherID.setEditable(!flag);
        } else {
            txtVoucherID.setEditable(flag);
        }
        txtDiscount.setEditable(flag);
        txtReceiptApply.setEditable(flag);
        cbbMedicineID.setEnabled(flag);
        dcDayStart.setEnabled(flag);
        dcDayEnd.setEnabled(flag);
    }

    private void loadComboboxMedicine() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbbMedicineID.getModel();
        boxModel.removeAllElements();
        new MedicineDAO().selectAll().forEach(o -> {
            boxModel.addElement(o.getMdcID());
        });
    }

    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        try {
            for (Voucher voucher : new VoucherDAO().selectAll()) {
                model.addRow(new Object[]{
                    voucher.getVcID(),
                    voucher.getVcStartDate(),
                    voucher.getVcEndDate(),
                    voucher.getVcTotalBillCanAdd(),
                    voucher.getVcPriceDiscount()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setModel(Voucher model) throws WriterException, IOException, NotFoundException {
        txtVoucherID.setText(model.getVcID());
        cbbMedicineID.setSelectedItem(model.getVcMedicineID());
        txtReceiptApply.setText(String.valueOf(model.getVcTotalBillCanAdd()));
        txtDiscount.setText(String.valueOf(model.getVcPriceDiscount()));
        dcDayStart.setDate(model.getVcStartDate());
        dcDayEnd.setDate(model.getVcEndDate());
        lblStartDate.setText("Start from: " + model.getVcStartDate());
        lblEndDate.setText("To: " + model.getVcEndDate());
        lblDiscount.setText("Discount up " + model.getVcPriceDiscount() + " VNĐ");
        lblQRCode.setIcon(new ImageIcon(QRCodeSupport.createQRCode(model.getVcID(), lblQRCode.getWidth(), lblQRCode.getHeight())));
    }

    private Voucher getModel() {
        Voucher model = new Voucher();
        model.setVcID(txtVoucherID.getText());
        model.setVcEmployeeAddVoucher(Image_Auth.USER.getEpeID());
        model.setVcMedicineID(cbbMedicineID.getSelectedItem().toString());
        model.setVcTotalBillCanAdd(Double.valueOf(txtReceiptApply.getText()));
        model.setVcPriceDiscount(Double.valueOf(txtDiscount.getText()));
        model.setVcStartDate(DateSupport.toDate(new SimpleDateFormat("yyyy-MM-dd").format(dcDayStart.getDate())));
        model.setVcEndDate(DateSupport.toDate(new SimpleDateFormat("yyyy-MM-dd").format(dcDayEnd.getDate())));
        return model;
    }

    private void clearForm() {
        txtDiscount.setText("");
        cbbMedicineID.setSelectedIndex(0);
        txtReceiptApply.setText("");
        txtVoucherID.setText("");
        dcDayEnd.cleanup();
        dcDayStart.cleanup();
        lblQRCode.setIcon(null);
        lblStartDate.setText("");
        lblEndDate.setText("");
        lblDiscount.setText("");
    }

    private void insert(Voucher model) {
        try {
            new VoucherDAO().insert(model);
            loadDataToTable();
            clearForm();
            btnAdd.setVisible(false);
            setStatus(false);
            Mgsbox.alert(this, "Insert successfully");
        } catch (Exception e) {
            Mgsbox.error(this, "Insert failded");
        }
    }

    private void delete(String voucherID) {
        if (Mgsbox.comfirm(this, "Do you really want to delete ?")) {
            try {
                new VoucherDAO().delete(voucherID);
                loadDataToTable();
                clearForm();
                Mgsbox.alert(this, "Delete successfully !!!");
            } catch (Exception e) {
                Mgsbox.error(this, "Delete failed !");
                e.printStackTrace();
            }
        }
    }

    private void update(Voucher model) {
        try {
            new VoucherDAO().update(model);
            loadDataToTable();
            clearForm();
            Mgsbox.alert(this, "Update successful!");
            btnUpdate.setVisible(false);
            btnEdit.setVisible(true);
            setStatus(false);
        } catch (Exception e) {
            Mgsbox.alert(this, "Update failed!");
            e.printStackTrace();

        }
    }

    private void loadRowToControl(String VoucherID) {
        try {
            Voucher model = new VoucherDAO().selectByID(VoucherID);
            if (model != null) {
                this.setModel(model);
            }
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }

    public boolean isValidDate(JDateChooser dateStart, JDateChooser dateEnd) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(dateStart.getDate());
        c2.setTime(dateEnd.getDate());
        long a = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        if (a >= 2) {
            return true;
        } else {
            return false;
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
 jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtVoucherID = new GUI.TextField();
        jLabel3 = new javax.swing.JLabel();
jLabel4 = new javax.swing.JLabel();
        txtReceiptApply = new GUI.TextField();
        jLabel5 = new javax.swing.JLabel();
        dcDayStart = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtDiscount = new GUI.TextField();
        jLabel7 = new javax.swing.JLabel();
        dcDayEnd = new com.toedter.calendar.JDateChooser();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        cbbMedicineID = new javax.swing.JComboBox<>();
        btnEdit = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        pnlVoucher = new javax.swing.JPanel();
        lblEndDate = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblQRCode = new javax.swing.JLabel();
        lblVoucher = new javax.swing.JLabel();
        btnCapture = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
 tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Voucher ID", "Day start", "Day end", "Receipt apply", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setFocusable(false);
        tblVoucher.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblVoucher.setRowHeight(25);
        tblVoucher.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblVoucher.setShowVerticalLines(false);
        tblVoucher.getTableHeader().setReorderingAllowed(false);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Voucher ID:");

        txtVoucherID.setBackground(new java.awt.Color(255, 255, 255));
        txtVoucherID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVoucherIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVoucherIDKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Medicine ID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Receipt apply:");

        txtReceiptApply.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Discount:");

        dcDayStart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Day start:");

        txtDiscount.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Day end:");

        dcDayEnd.setBackground(new java.awt.Color(255, 255, 255));

        btnNew.setBackground(new java.awt.Color(0, 153, 255));
        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 153, 255));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 153, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 153, 255));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cbbMedicineID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEdit.setBackground(new java.awt.Color(0, 153, 255));
        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/new.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        pnlVoucher.setPreferredSize(new java.awt.Dimension(490, 175));
        pnlVoucher.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEndDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEndDate.setForeground(new java.awt.Color(102, 102, 255));
        lblEndDate.setText("End ");
        pnlVoucher.add(lblEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, -1));

        lblStartDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(102, 102, 255));
        lblStartDate.setText("Start");
        pnlVoucher.add(lblStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 180, -1));

        lblDiscount.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(255, 102, 255));
        lblDiscount.setText("Discount");
        pnlVoucher.add(lblDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 180, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gift Voucher");
        pnlVoucher.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));
        pnlVoucher.add(lblQRCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 70, 70));

        lblVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Voucher.png"))); // NOI18N
        lblVoucher.setText("Gift Voucher");
        pnlVoucher.add(lblVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnCapture.setBackground(new java.awt.Color(255, 255, 255));
        btnCapture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/camera.png"))); // NOI18N
        btnCapture.setBorder(null);
        btnCapture.setBorderPainted(false);
        btnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtVoucherID, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
   .addComponent(cbbMedicineID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
  .addGap(19, 19, 19)
                                .addComponent(dcDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReceiptApply, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(pnlVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCapture)
                                .addGap(157, 157, 157)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
.addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtVoucherID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtReceiptApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))    
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel3)
                        .addComponent(cbbMedicineID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(dcDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(dcDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(pnlVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCapture)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
 .addComponent(btnNew)
                    .addComponent(btnEdit))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        setStatus(true);
        txtVoucherID.setEditable(true);
        btnNew.setVisible(false);
        btnAdd.setVisible(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update(getModel());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    delete(tblVoucher.getValueAt(tblVoucher.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
 if (ValidateSupport.isNull(txtVoucherID)) {
            Mgsbox.error(this, "Please enter Voucher ID...");
        } else if (!ValidateSupport.isNumber(txtDiscount) || !ValidateSupport.isNumber(txtReceiptApply)) {
            Mgsbox.error(txtDiscount.getRootPane(), "Please enter a number....");
        } else if (!isValidDate(dcDayStart, dcDayEnd)) {
            Mgsbox.error(this, "Promotion period must be more than 2 days");
        } else if (isDuplicateID(txtVoucherID.getText())) {
            Mgsbox.error(this, "Voucher ID is duplicated...!");
        } else if (txtVoucherID.getText().length() > 8) {
            Mgsbox.error(this, "Voucher ID have to less than 9 characters!");
        } else {
            lblStatus.setText("");
            insert(getModel());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        // TODO add your handling code here:
        loadRowToControl(tblVoucher.getValueAt(tblVoucher.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        setStatus(true);
        btnEdit.setVisible(false);
        btnUpdate.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtVoucherIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVoucherIDKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtVoucherIDKeyPressed

    private void txtVoucherIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVoucherIDKeyReleased
        // TODO add your handling code here:
        if (isDuplicateID(txtVoucherID.getText())) {
            lblStatus.setText("Voucher ID is duplicated!");
            lblStatus.setForeground(Color.red);
        } else if (txtVoucherID.getText().length() > 8) {
            lblStatus.setText("Voucher ID have to less than 9 characters!");
            lblStatus.setForeground(Color.red);
        } else if (txtVoucherID.getText().length() == 0) {
            lblStatus.setText("");
        } else {
            lblStatus.setText("Voucher ID is valid!");
            lblStatus.setForeground(Color.GREEN);
        }
    }//GEN-LAST:event_txtVoucherIDKeyReleased

    private void btnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaptureActionPerformed
        String fileName = Mgsbox.prompt(this, "Enter name your cupture!");
        if (fileName == null || !fileName.isBlank()) {
            Image_Auth.saveVoucher(Image_Auth.getScreenShot(pnlVoucher), fileName);
        }
    }//GEN-LAST:event_btnCaptureActionPerformed


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCapture;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbMedicineID;
    private com.toedter.calendar.JDateChooser dcDayEnd;
    private com.toedter.calendar.JDateChooser dcDayStart;
 private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblQRCode;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblVoucher;
    public javax.swing.JPanel pnlVoucher;
    private javax.swing.JTable tblVoucher;
    private GUI.TextField txtDiscount;
 private GUI.TextField txtReceiptApply;
    private GUI.TextField txtVoucherID;
}
