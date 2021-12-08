/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.Helper.CreateExcel;
import Controller.Helper.Image_Auth;
import Controller.Helper.Mgsbox;
import Controller.Helper.ValidateSupport;
import Controller.ModelDAO.IdDAO;
import Controller.ModelDAO.TypeOfMedicineDAO;
import Model.TypeOfMedicine;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Laxus
 */
public class MedicineTypeForm extends javax.swing.JPanel {

    /**
     * Creates new form MedicineTypeForm
     */
    public MedicineTypeForm() {
        initComponents();
        LoadDataToTable(new TypeOfMedicineDAO().selectAll());
        EditTable(tblMeType);
        txtDrugNameID.setBackground(white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMeType = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        rdoYes = new javax.swing.JRadioButton();
        rdoNo = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        txtDrugTypeID = new GUI.TextField();
        txtDrugNameID = new GUI.TextField();
        txtUserObject = new GUI.TextField();
        btnAdd = new javax.swing.JButton();
        cboTOM = new javax.swing.JComboBox<>();
        txtSearch = new GUI.TextField();
        jLabel1 = new javax.swing.JLabel();
        btnCreateExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Drug type ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Drug name ID:");

        tblMeType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblMeType.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Drug type ID", "Drug name ID", "Prescription drugs", "User objec", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMeType.setFocusable(false);
        tblMeType.setGridColor(new java.awt.Color(15, 106, 205));
        tblMeType.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblMeType.setRowHeight(25);
        tblMeType.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblMeType.setShowVerticalLines(false);
        tblMeType.getTableHeader().setReorderingAllowed(false);
        tblMeType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMeTypeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMeType);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Prescription drugs:");

        rdoYes.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoYes);
        rdoYes.setText("Yes");

        rdoNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNo);
        rdoNo.setText("No");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("User object:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Note:");

        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNote.setRows(5);
        jScrollPane2.setViewportView(txtNote);

        btnEdit.setBackground(new java.awt.Color(92, 84, 179));
        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edit_32px.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(92, 84, 179));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(92, 84, 179));
        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add_32px_1.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(92, 84, 179));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update_32px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        txtDrugTypeID.setEditable(false);
        txtDrugTypeID.setBackground(new java.awt.Color(255, 255, 255));
        txtDrugTypeID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDrugNameID.setEditable(false);
        txtDrugNameID.setBackground(new java.awt.Color(255, 255, 255));
        txtDrugNameID.setText(" ");
        txtDrugNameID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtUserObject.setText(" ");
        txtUserObject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(92, 84, 179));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/New_user_32px.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cboTOM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None Sort", " " }));

        txtSearch.setText(" ");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search_24px.png"))); // NOI18N
        jLabel1.setText(" ");

        btnCreateExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnCreateExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/excel.png"))); // NOI18N
        btnCreateExcel.setText("Export to Excel");
        btnCreateExcel.setBorder(null);
        btnCreateExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDrugTypeID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDrugNameID, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboTOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUserObject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoYes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNo)
                                .addGap(218, 218, 218))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(rdoYes)
                    .addComponent(rdoNo)
                    .addComponent(txtDrugTypeID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserObject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(txtDrugNameID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateExcel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblMeTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMeTypeMouseClicked
        findAndLoadRowToControl(tblMeType.getValueAt(tblMeType.getSelectedRow(), 0).toString());
        editBtn(false);
    }//GEN-LAST:event_tblMeTypeMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnEditStatus(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (Image_Auth.USER.isEpeIsRole()) {
            delete();
            clearForm();
        } else {
            Mgsbox.alert(this, "Chỉ trưởng phòng mới được phép xóa");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        BtnNew(true);
        createID();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
        editBtn(false);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            btnChange.setToolTipText("Cick 2 for change Background");
            setBackground(Color.decode("#9badf2"));
            Controller.Helper.BackgroundC1.ChangeTxt(txtDrugNameID);
            Controller.Helper.BackgroundC1.ChangeTxt(txtDrugTypeID);
            Controller.Helper.BackgroundC1.ChangeTxt(txtUserObject);
            Controller.Helper.BackgroundC1.ChangeTxtArea(txtNote);
            Controller.Helper.BackgroundC1.ChangeBtn(btnChange);
            Controller.Helper.BackgroundC1.ChangeRdo(rdoNo);
            Controller.Helper.BackgroundC1.ChangeRdo(rdoYes);
            Controller.Helper.BackgroundC1.ChangeTxt(txtSearch);
        } else if (evt.getClickCount() == 2) {
            btnChange.setToolTipText("Cick 1 for change Background");
            setBackground(Color.white);
            Controller.Helper.BackgroundC2.ChangeRdo(rdoNo);
            Controller.Helper.BackgroundC2.ChangeRdo(rdoYes);
            Controller.Helper.BackgroundC2.ChangeTxt(txtDrugNameID);
            Controller.Helper.BackgroundC2.ChangeTxt(txtDrugTypeID);
            Controller.Helper.BackgroundC2.ChangeTxtArea(txtNote);
            Controller.Helper.BackgroundC2.ChangeTxt(txtUserObject);
            Controller.Helper.BackgroundC2.ChangeTxt(txtSearch);
            Controller.Helper.BackgroundC2.ChangeTxt(txtSearch);
        }
    }//GEN-LAST:event_btnChangeMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (ValidateSupport.isNull(txtDrugNameID)
                || ValidateSupport.isNull(txtDrugTypeID)
                || ValidateSupport.isNull(txtUserObject)) {
            Mgsbox.error(this, "Please fill out the form...");
        } else if (!isDuplicateID(txtDrugTypeID)) {
            insert();
            editBtn(false);
            clearForm();
        } else {
            Mgsbox.error(this, "Drug Type ID already exists");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChangeActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        search(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnCreateExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateExcelActionPerformed
        // TODO add your handling code here:
        CreateExcel.ExportToExcel(tblMeType, "Type Medicine");
    }//GEN-LAST:event_btnCreateExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnCreateExcel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTOM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNo;
    private javax.swing.JRadioButton rdoYes;
    private javax.swing.JTable tblMeType;
    private GUI.TextField txtDrugNameID;
    private GUI.TextField txtDrugTypeID;
    private javax.swing.JTextArea txtNote;
    private GUI.TextField txtSearch;
    private GUI.TextField txtUserObject;
    // End of variables declaration//GEN-END:variables
    TypeOfMedicineDAO TM = new TypeOfMedicineDAO();
    JFileChooser fileChooser = new JFileChooser();
    int index = 0;

    private void LoadDataToTable(List<TypeOfMedicine> listTypeMe) {
        DefaultTableModel model = (DefaultTableModel) tblMeType.getModel();
        model.setRowCount(0);
        try {
            listTypeMe.forEach(e -> {
                model.addRow(new Object[]{e.getTomID(),
                    e.getTomName(),
                    e.isTomIsPrecription() ? "Yes" : "No",
                    e.getTomUserObject(),
                    e.getTomNote()});
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataToSort() {
        cboTOM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && cboTOM.getSelectedIndex() == 1) {
                    LoadDataToTable(SortByNameDrug());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboTOM.getSelectedIndex() > 1) {
                    LoadDataToTable(SortByUserObject());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboTOM.getSelectedIndex() < 1) {
                    LoadDataToTable(new TypeOfMedicineDAO().selectAll());
                }
            }
        });
    }

    public List<TypeOfMedicine> SortByNameDrug() {
        List<TypeOfMedicine> listSort = new TypeOfMedicineDAO().selectAll();
        Collections.sort(listSort, (TypeOfMedicine o1, TypeOfMedicine o2)
                -> o1.getTomName().compareTo(o2.getTomName()));
        return listSort;
    }

    public List<TypeOfMedicine> SortByUserObject() {
        List<TypeOfMedicine> listSort = new TypeOfMedicineDAO().selectAll();
        Collections.sort(listSort, (TypeOfMedicine o1, TypeOfMedicine o2)
                -> o1.getTomUserObject().compareTo(o2.getTomUserObject()));
        return listSort;
    }

    private void setModel(TypeOfMedicine model) {
        txtDrugTypeID.setText(model.getTomID().trim());
        txtDrugNameID.setText(model.getTomName().trim());
        txtUserObject.setText(model.getTomUserObject().trim());
        System.out.println(model.getTomUserObject().length());
        System.out.println(txtUserObject.getText().length());
        rdoYes.setSelected(model.isTomIsPrecription());
        rdoNo.setSelected(!model.isTomIsPrecription());
        txtNote.setText(model.getTomNote());
    }

    TypeOfMedicine getModel() {
        TypeOfMedicine model = new TypeOfMedicine();
        model.setTomID(txtDrugTypeID.getText());
        model.setTomName(txtDrugNameID.getText());
        model.setTomIsPrecription(rdoYes.isSelected());
        model.setTomUserObject(txtUserObject.getText().trim());
        model.setTomNote(txtNote.getText());
        return model;
    }

    private void clearForm() {
        final String a = "";
        txtDrugNameID.setText(a);
        txtDrugTypeID.setText(a);
        txtUserObject.setText(a);
        txtNote.setText(a);
        buttonGroup1.clearSelection();
    }

    public void search(String text) {
        DefaultTableModel model = (DefaultTableModel) tblMeType.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblMeType.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(text));
    }

    //insert
    private void insert() {
        TypeOfMedicine model = getModel();
        try {
            TM.insert(model);
            LoadDataToTable(new TypeOfMedicineDAO().selectAll());
            Mgsbox.alert(this, "Insert successful!");
        } catch (Exception e) {
            Mgsbox.alert(this, "Insert failed!");
        }
    }

    //Update
    private void update() {
        TypeOfMedicine model = getModel();
        try {
            TM.update(model);
            LoadDataToTable(new TypeOfMedicineDAO().selectAll());
            Mgsbox.alert(this, "Update successful!");
        } catch (Exception e) {
            Mgsbox.alert(this, "Update failed!");
        }
    }

    //Delete
    private void delete() {
        if (Mgsbox.comfirm(this, "Do you really want to delete this Medicine Type?")) {
            String TMID = txtDrugTypeID.getText();
            try {
                TM.delete(TMID);
                LoadDataToTable(new TypeOfMedicineDAO().selectAll());
                Mgsbox.alert(this, "Delete successfully!");
            } catch (Exception e) {
                Mgsbox.alert(this, "Delete failed!");
            }
        }
    }

    private void edit() {
        // setTrang();
        try {
            String manv = (String) tblMeType.getValueAt(this.index, 0);
            TypeOfMedicine model = TM.selectByID(manv);
            if (model != null) {
                this.setModel(model);
                //this.setStatus(false);
            }
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }

    private void editBtn(boolean b) {
        txtDrugNameID.setEditable(b);
        txtDrugTypeID.setEditable(b);
        txtNote.setEditable(b);
        rdoNo.setEnabled(b);
        rdoYes.setEnabled(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnUpdate.setEnabled(b);
        btnEdit.setEnabled(!b);
    }

    private void BtnNew(boolean b) {
        txtDrugNameID.setEditable(b);
        txtDrugTypeID.setEditable(b);
        txtNote.setEditable(b);
        rdoNo.setEnabled(b);
        rdoYes.setEnabled(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnDelete.setEnabled(b);
        btnUpdate.setEnabled(!b);
        btnEdit.setEnabled(!b);
    }

    private void btnEditStatus(boolean b) {
        txtDrugNameID.setEditable(b);
        txtDrugTypeID.setEditable(!b);
        txtNote.setEditable(b);
        rdoNo.setEnabled(b);
        rdoYes.setEnabled(b);
        btnUpdate.setEnabled(b);
    }

    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    private void findAndLoadRowToControl(String TypeOfMedicine) {
        try {
            setModel(TM.selectByID(TypeOfMedicine));
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }

    private boolean isDuplicateID(JTextField txt) {
        if (TM.selectByID(txt.getText()) != null) {
            return true;
        }
        return false;

    }
    
    IdDAO iddao = new IdDAO();
    private void createID(){
        List<Object> data = iddao.medicineTypeID();
        List<Object> data1 = iddao.medicineID();
        int id = Integer.valueOf(data.get(0).toString()) + 1;
        int id1 = Integer.valueOf(data1.get(0).toString()) + 1;
        txtDrugTypeID.setText("MDO"+id);
        txtDrugNameID.setText("MED"+id1);
    }
}
