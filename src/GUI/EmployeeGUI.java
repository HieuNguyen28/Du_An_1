package GUI;

import Controller.Helper.DateSupport;
import Controller.Helper.Image_Auth;
import static Controller.Helper.Image_Auth.USER;
import Controller.Helper.Mgsbox;
import Controller.ModelDAO.EmployeeDAO;
import Model.Employee;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmployeeGUI extends javax.swing.JPanel {
    
    EmployeeDAO ED = new EmployeeDAO();
    JFileChooser fileChooser = new JFileChooser();
    int index = 0;
    
    public EmployeeGUI() {
        initComponents();
        EditTable(tblEmployee);
        setOpaque(false);
        lblImg.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        LoadDataToTable();
        fileChooser.setDialogTitle("Choose Logo for Employee"); 
    }
    //CODE
    private void LoadDataToTable(){
        DefaultTableModel model= (DefaultTableModel) tblEmployee.getModel();
        model.setRowCount(0);
        try {
            List<Employee> listEmployee = ED.selectAll();
            listEmployee.forEach(e -> {
                model.addRow(new Object[] {
                    e.getEpeID(),
                    e.getEpeName(),
                    e.isEpeIsGender()? "Male" : "Femail",
                    e.getEpeDayOfBirth(),
                    e.getEpeAddress(),
                    e.getEpeNumberPhone(),
                    e.getEpeEmail(),
                    e.isEpeIsRole() ? "Manager" : "Employee",
                    e.getEpeUserName(),
                    hidePassWord(e.getEpePassword()),
                    e.getEpeAccountCreationDate()});
            });
        } catch (Exception e) {
        }
    }
    
    void insert() {
        Employee model = getModel();
        try {
            ED.insert(model);
            this.LoadDataToTable();
            this.clearForm();
            Mgsbox.alert(this, "Successfully added employees");
        } catch (Exception e) {
            Mgsbox.alert(this, "Add employee failed !!!");
        }

    }
    
    private void update() {
        Employee model = getModel();
        try {
            ED.update(model);
            this.LoadDataToTable();
            clearForm();
            Mgsbox.alert(this, "Update successful!");
        } catch (Exception e) {
            Mgsbox.alert(this, "Update failed!");
        }
    }
    
    private void delete() {
        if (Mgsbox.comfirm(this, "Do you really want to delete this employee?")) {
            String EmID = txtID.getText();
            try {
                ED.delete(EmID);
                this.LoadDataToTable();
                this.clearForm();
                Mgsbox.alert(this, "Delete successfully!");
            } catch (Exception e) {
                Mgsbox.alert(this, "Delete failed!");
            }
        }
    }
    
    private void edit() {
//       setTrang();
        try {
            String manv = (String) tblEmployee.getValueAt(this.index, 0);
            Employee model = ED.selectByID(manv);
            if (model != null) {
                this.setModel(model);
//                this.setStatus(false);
            }
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }
    
    private void setModel(Employee model) {
        txtID.setText(model.getEpeID());
        txtName.setText(model.getEpeName());
        rdoMale.setSelected(model.isEpeIsGender());
        rdoFemale.setSelected(!model.isEpeIsGender());
        dcDate.setDate(model.getEpeDayOfBirth());
        txtAddress.setText(model.getEpeAddress());
        txtIdentityCard.setText(model.getEpeCCCD());
        txtPhoneNumber.setText(model.getEpeNumberPhone());
        txtEmail.setText(model.getEpeEmail());
        rdoManager.setSelected(model.isEpeIsRole());
        rdoStaff.setSelected(!model.isEpeIsRole());
        txtUsername.setText(model.getEpeUserName());
        txtPassword.setText(model.getEpePassword());
        if (model.getEpeImage() != null){
            lblImg.setIcon(Image_Auth.readImage(model.getEpeImage()));
            lblImg.setToolTipText(model.getEpeImage());
        } else {
            lblImg.setIcon(Image_Auth.readImage("employee.png"));
        }
    }
    
    Employee getModel() {
        Employee model = new Employee();
        model.setEpeID(txtID.getText());
        model.setEpeName(txtName.getText());
        model.setEpeIsGender(rdoMale.isSelected());
        model.setEpeDayOfBirth(DateSupport.toDate(new SimpleDateFormat("yyyy-MM-dd").format(dcDate.getDate())));
        model.setEpeAddress(txtAddress.getText());
        model.setEpeNumberPhone(txtPhoneNumber.getText());
        model.setEpeEmail(txtEmail.getText());
        model.setEpeImage(lblImg.getToolTipText());
        model.setEpeIsRole(rdoMale.isSelected());
        model.setEpeUserName(txtUsername.getText());
        model.setEpePassword(txtPassword.getText());
        model.setEpeAccountCreationDate(DateSupport.now());
        model.setEpeCCCD(txtIdentityCard.getText());
        return model;
    }
    
    private String hidePassWord(String pass) {
        return pass.replace(pass, "*******");
    }
    
    private void clearForm() {
        final String a = "";
        txtID.setText(a);
        txtName.setText(a);
        txtAddress.setText(a);
        txtEmail.setText(a);
        txtIdentityCard.setText(a);
        txtPhoneNumber.setText(a);
        txtUsername.setText(a);
        txtPassword.setText(a);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
    }
    
    public boolean checkMySelf(JTextField txt) {
        Employee ep = this.ED.selectByID(txt.getText());
        if (ep.getEpeID().equals(USER.getEpeID())) {
            Mgsbox.alert(this, "You can not delete yourself.");
            return false;
        } else {
            return true;
        }
    }
    
    void selectImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { //nếu người dùng đã chọn đc file
            File file = fileChooser.getSelectedFile();    //lấy file người dùng chọn
            if (Image_Auth.saveImage(file)) {  //sao chép file đã chọn thư mục logos
                // Hiển thị hình lên form
                lblImg.setIcon(Image_Auth.readImage(file.getName())); //file.getName(); lấy tên của file
                //ImageIcon readLogo(String tenFile) đọc file trong thư mục logos theo tên file trả về ImageIcon
                //void setIcon(ImageIcon icon) set Icon cho lbl
                lblImg.setToolTipText(file.getName());
            }
        }
    }
    
    //DESIGN
    private void EditTable(JTable a){
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtIdentityCard = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdoManager = new javax.swing.JRadioButton();
        rdoStaff = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 255));
        jLabel1.setText("Employee Manager");

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Sex", "Date", "Address", "Phone number", "Email", "Role", "Username", "Password", "Date create"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.setFocusable(false);
        tblEmployee.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblEmployee.setRowHeight(25);
        tblEmployee.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblEmployee.setShowVerticalLines(false);
        tblEmployee.getTableHeader().setReorderingAllowed(false);
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        jLabel2.setText("ID:");

        jLabel3.setText("Name:");

        jLabel4.setText("Sex:");

        buttonGroup1.add(rdoMale);
        rdoMale.setText("Male");

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        jLabel5.setText("Date:");

        dcDate.setDateFormatString("dd-MM-yyyy");

        jLabel6.setText("Address:");

        jLabel7.setText("Identity card:");

        jLabel8.setText("Phone number:");

        jLabel9.setText("Email:");

        jLabel10.setText("Role:");

        buttonGroup2.add(rdoManager);
        rdoManager.setText("Manager");

        buttonGroup2.add(rdoStaff);
        rdoStaff.setText("Staff");

        jLabel11.setText("Username:");

        jLabel12.setText("Password:");

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

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/employee.png"))); // NOI18N
        lblImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/changePasswordIcon.png"))); // NOI18N
        jButton1.setText("Change password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                        .addComponent(txtEmail)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, 0)
                                        .addComponent(rdoMale)
                                        .addGap(0, 0, 0)
                                        .addComponent(rdoFemale))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoManager)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoStaff))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdentityCard))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel5)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel4))
                                    .addComponent(rdoMale)
                                    .addComponent(rdoFemale))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel7))
                                    .addComponent(txtIdentityCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(rdoManager)
                                            .addComponent(rdoStaff)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel9)
                                        .addGap(16, 16, 16)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDelete)
                                .addComponent(btnAdd)
                                .addComponent(btnUpdate)
                                .addComponent(btnNew))
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblEmployee.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (Image_Auth.USER.isEpeIsRole()) {
            if (checkMySelf(txtID)) {
                delete();
            }
        } else {
            Mgsbox.alert(this, "Chỉ trưởng phòng mới được phép xóa");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void lblImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblImgMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ChangePasswordForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImg;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoManager;
    private javax.swing.JRadioButton rdoStaff;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIdentityCard;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
