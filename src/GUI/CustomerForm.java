/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static Controller.Helper.DateSupport.now;
import Controller.Helper.Mgsbox;
import Controller.Helper.ValidateSupport;
import Controller.ModelDAO.CustomerDAO;
import Model.Customer;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Laxus
 */
public class CustomerForm extends javax.swing.JPanel {

    /**
     * Creates new form CustomerForm
     */
    public CustomerForm() {
        initComponents();
        loadDataToTable(new CustomerDAO().selectAll());
        EditTable(tblCustomer);
        loadDataToSort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        txtID = new GUI.TextField();
        txtName = new GUI.TextField();
        txtPN = new GUI.TextField();
        btnChange = new javax.swing.JButton();
        txtSearch = new GUI.TextField();
        jLabel6 = new javax.swing.JLabel();
        cboSort = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 255));
        jLabel1.setText("Customer");

        jLabel2.setText("Customer ID:");

        jLabel3.setText("Name:");

        jLabel4.setText("Phone number:");

        tblCustomer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Name", "Phone number", "Start", "Last", "Times", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.setFocusable(false);
        tblCustomer.setGridColor(new java.awt.Color(15, 106, 205));
        tblCustomer.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblCustomer.setRowHeight(25);
        tblCustomer.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblCustomer.setShowVerticalLines(false);
        tblCustomer.getTableHeader().setReorderingAllowed(false);
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        btnNew.setBackground(new java.awt.Color(92, 84, 179));
        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/New_user_32px.png"))); // NOI18N
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

        btnAdd.setBackground(new java.awt.Color(92, 84, 179));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add_32px_1.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

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

        txtID.setText(" ");
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPN.setText(" ");
        txtPN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnChange.setBackground(new java.awt.Color(255, 255, 255));
        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/night-mode.png"))); // NOI18N
        btnChange.setBorder(null);
        btnChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChangeMouseClicked(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search_24px.png"))); // NOI18N
        jLabel6.setText(" ");

        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None sort", "Sort by name", "Sort by total", " " }));
        cboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        BtnNew(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
        editBtn(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed
    String maKH = "", name = "", numberPhone = "";
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (ValidateSupport.isNull(txtID) || ValidateSupport.isNull(txtName) || ValidateSupport.isNull(txtPN)) {
            Mgsbox.error(this, "Please fill out the form...");
        } else if (!ValidateSupport.checkSDT(txtPN)) {
            Mgsbox.error(this, "Please fill in the correct format as required !!!");
        } else if (!isDuplicateCustomerID(txtID)) {
            insert();
            editBtn(false);
        } else {
            Mgsbox.error(this, "This customer code already exists");
        }

        }
    }//GEN-LAST:event_btnAddActionPerformed

    int index = 0;
    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:

        this.index = tblCustomer.rowAtPoint(evt.getPoint());
        this.edit();
        editBtn(false);

    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnEditStatus(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            btnChange.setToolTipText("Cick 2 for change Background");
            setBackground(Color.decode("#9badf2"));
            Controller.Helper.BackgroundC1.ChangeTxt(txtID);
            Controller.Helper.BackgroundC1.ChangeTxt(txtName);
            Controller.Helper.BackgroundC1.ChangeTxt(txtPN);
            Controller.Helper.BackgroundC1.ChangeTxt(txtSearch);
            Controller.Helper.BackgroundC1.ChangeBtn(btnChange);
        } else if (evt.getClickCount() == 2) {
            btnChange.setToolTipText("Cick 1 for change Background");
            setBackground(Color.white);
            Controller.Helper.BackgroundC2.ChangeTxt(txtID);
            Controller.Helper.BackgroundC2.ChangeTxt(txtName);
            Controller.Helper.BackgroundC2.ChangeTxt(txtPN);
            Controller.Helper.BackgroundC2.ChangeTxt(txtSearch);
            btnChange.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btnChangeMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        search(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSortActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboSortActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCustomer;
    private GUI.TextField txtID;
    private GUI.TextField txtName;
    private GUI.TextField txtPN;
    private GUI.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
    CustomerDAO ctm = new CustomerDAO();

    private void loadDataToTable(List<Customer> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblCustomer.getModel();
        dtm.setRowCount(0);
        try {
            for (Customer customer : list) {
                dtm.addRow(new Object[]{
                    customer.getCtmID(),
                    customer.getCtmName(),
                    customer.getCtmNumberPhone(),
                    customer.getCtmStartDateBuy(),
                    customer.getCtmLastDateBuy(),
                    customer.getCtmTimesOfBuy(),
                    customer.getCtmTotalMoneyBought()
                });
            }
        } catch (Exception e) {
        }
    }

    private void loadDataToSort() {
        cboSort.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() == 1) {
                    loadDataToTable(SortByName());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() > 1) {
                    loadDataToTable(SortByTotal());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() < 1) {
                    loadDataToTable(new CustomerDAO().selectAll());
                }
            }
        });
    }

    public List<Customer> SortByName() {
        List<Customer> listSort = new CustomerDAO().selectAll();
        Collections.sort(listSort, (Customer o1, Customer o2)
                -> o1.getCtmName().compareTo(o2.getCtmName()));
        loadDataToTable(listSort);
        return listSort;
    }

    public List<Customer> SortByTotal() {
        List<Customer> listSort = new CustomerDAO().selectAll();
        Collections.sort(listSort, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return Double.compare(c1.getCtmTotalMoneyBought(), c2.getCtmTotalMoneyBought());
            }
        });
        return listSort;
    }

    private void setModel(Customer model) {
        txtID.setText(model.getCtmID());
        txtName.setText(model.getCtmName());
        txtPN.setText(model.getCtmNumberPhone());
    }

    private Customer getModel() {
        Customer c = new Customer();
        c.setCtmID(txtID.getText());
        c.setCtmName(txtName.getText());
        c.setCtmNumberPhone(txtPN.getText());
        c.setCtmStartDateBuy(now());
        c.setCtmLastDateBuy(now());
        c.setCtmTimesOfBuy(0);
        c.setCtmTotalMoneyBought(0);
        return c;
    }

    private void edit() {
        try {
            String cID = tblCustomer.getValueAt(index, 0).toString();
            Customer c = ctm.selectByID(cID);
            if (c != null) {
                setModel(c);
            }
        } catch (Exception e) {
        }
    }

    private void clearForm() {
        txtID.setText("");
        txtName.setText("");
        txtPN.setText("");
    }

    private void update() {
        Customer model = getModel();
        try {
            ctm.update(model);
            loadDataToTable(new CustomerDAO().selectAll());
            Mgsbox.alert(this, "Update successful!");
            clearForm();
            defaultButton(false);
            defaultText(false);
        } catch (Exception e) {
            Mgsbox.alert(this, "Update failed!");
        }
    }

    private void insert() {
        Customer model = getModel();
        try {
            ctm.insert(model);
            loadDataToTable(new CustomerDAO().selectAll());
            Mgsbox.alert(this, "Insert successful!");
            clearForm();
            defaultButton(false);
            defaultText(false);
        } catch (Exception e) {
            Mgsbox.alert(this, "Insert failed!");
        }
    }

    private void delete() {
        if (Mgsbox.comfirm(this, "Do you really want to delete this customer?")) {
            String cID = txtID.getText();
            try {
                ctm.delete(cID);
                loadDataToTable(new CustomerDAO().selectAll());
                this.clearForm();
                Mgsbox.alert(this, "Delete successfully!");
                defaultButton(false);
                defaultText(false);
            } catch (Exception e) {
                Mgsbox.alert(this, "Delete failed!");
            }
        }
    }

    private boolean isDuplicateCustomerID(JTextField txt) {
        txt.setBackground(white);
        if (ctm.selectByID(txt.getText()) != null) {
            return true;
        }
        return false;

    }

    private void editBtn(boolean b) {
        txtID.setEditable(b);
        txtName.setEditable(b);
        txtPN.setEditable(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnUpdate.setEnabled(b);
        btnEdit.setEnabled(!b);
    }

    private void BtnNew(boolean b) {
        txtID.setEditable(b);
        txtName.setEditable(b);
        txtPN.setEditable(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnDelete.setEnabled(b);
        btnUpdate.setEnabled(!b);
        btnEdit.setEnabled(!b);
    }

    private void btnEditStatus(boolean b) {
        txtID.setEditable(!b);
        txtName.setEditable(b);
        txtPN.setEditable(b);
        btnUpdate.setEnabled(b);
    }

    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    private void search(String text) {
        DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblCustomer.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(text));
    }
}
