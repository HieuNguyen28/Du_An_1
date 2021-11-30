package GUI;

import Controller.Helper.DateSupport;
import Controller.Helper.Image_Auth;
import static Controller.Helper.Image_Auth.USER;
import Controller.Helper.Mgsbox;
import Controller.Helper.ValidateSupport;
import Controller.ModelDAO.EmployeeDAO;
import Model.Employee;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmployeeGUI extends javax.swing.JPanel {

    EmployeeDAO ED = new EmployeeDAO();
    JFileChooser fileChooser = new JFileChooser();
    int index = 0;
    File lastPast;
    String fileImg = "";
    
    public EmployeeGUI() {
        initComponents();
        EditTable(tblEmployee);
        setOpaque(false);
        lblImg.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        LoadDataToTable();
        fileChooser.setDialogTitle("Choose Logo for Employee");
//        btnAdd.setBounds(70,80,100,30);
//        btnAdd.setBorder(new Controller.Helper.RoundedBorder(4));
//        jScrollPane1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int rowCurrent = tblEmployee.getSelectedRow();
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    rowCurrent = rowCurrent < 0 ? tblEmployee.getRowCount() + 1 : rowCurrent;
//                    tblEmployee.setRowSelectionInterval(rowCurrent - 1, rowCurrent - 1);
//                    System.out.println("+");
//                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    rowCurrent = rowCurrent > tblEmployee.getRowCount() ? 0 : rowCurrent;
//                    tblEmployee.setRowSelectionInterval(rowCurrent + 1, rowCurrent + 1);
//                    System.out.println("-");
//                }
//            }
//
//        });
    }

    //CODE
    private void LoadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        model.setRowCount(0);
        try {
            List<Employee> listEmployee = ED.selectAll();
            listEmployee.forEach(e -> {
                model.addRow(new Object[]{
                    e.getEpeID(),
                    e.getEpeName(),
                    e.isEpeIsGender() ? "Male" : "Femail",
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
            e.printStackTrace();
        }
    }

    void insert(Employee model) {
        //Employee model = getModel();
        try {
            ED.insert(model);
            this.LoadDataToTable();
            this.clearForm();
            Mgsbox.alert(this, "Successfully added employees");
        } catch (Exception e) {
            Mgsbox.alert(this, "Add employee failed !!!");
        }

    }

    private void update(Employee model) {

        try {
            ED.update(model);
            this.LoadDataToTable();
            clearForm();
            Mgsbox.alert(this, "Update successful!");
        } catch (Exception e) {
            Mgsbox.alert(this, "Update failed!");
        }
    }

    private void delete(String EmployeeID) {
        if (Mgsbox.comfirm(this, "Do you really want to delete this employee?")) {
            try {
                ED.delete(EmployeeID);
                this.LoadDataToTable();
                this.clearForm();
                Mgsbox.alert(this, "Delete successfully!");
            } catch (Exception e) {
                Mgsbox.alert(this, "Delete failed!");
            }
        }
    }

    private void findAndLoadRowToControl(String EmployeeID) {
        try {
            setModel(ED.selectByID(EmployeeID));
        } catch (Exception e) {
            Mgsbox.error(this, "Data Query Error!!!");
        }
    }

    private void setModel(Employee model) {
        if (model != null) {
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
            if (model.getEpeImage() != null) {
                lblImg.setIcon(Image_Auth.readImage(model.getEpeImage()));
                lblImg.setToolTipText(model.getEpeImage());
            } else {
                lblImg.setIcon(Image_Auth.readImage("employee.png"));
            }
        } else {
            Mgsbox.error(this, "Can't not load this employee");
        }

    }

    private Employee getModel() {
        Employee model = new Employee();
        model.setEpeID(txtID.getText());
        model.setEpeName(txtName.getText());
        model.setEpeIsGender(rdoMale.isSelected());
        model.setEpeDayOfBirth(DateSupport.toDate(new SimpleDateFormat("yyyy-MM-dd").format(dcDate.getDate())));
        model.setEpeAddress(txtAddress.getText());
        model.setEpeNumberPhone(txtPhoneNumber.getText());
        model.setEpeEmail(txtEmail.getText());
        model.setEpeImage(lblImg.getToolTipText());
        model.setEpeIsRole(rdoManager.isSelected());
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
        txtID.requestFocus();
        lblImg.setIcon(Image_Auth.readImage("employee.png"));
        lblImg.setToolTipText("employee.png");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
    }

    private boolean isMySelf(String EmloyeeID) {
        return EmloyeeID.equals(USER.getEpeID());
    }

    void selectImage() {// Làm thêm lưu History
        if (lastPast != null) {
            fileChooser.setCurrentDirectory(lastPast);
        }
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (Image_Auth.saveImage(file)) {
                lblImg.setIcon(Image_Auth.readImage(file.getName()));
                lblImg.setToolTipText(file.getName());
            }
        }
         fileImg = fileChooser.getSelectedFile().getName();
         lastPast = fileChooser.getSelectedFile().getParentFile();
        
    }

    //DESIGN
    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    //Check Trung Ma
    private boolean isDuplicateID(JTextField txt) {
        txt.setBackground(white);
        if (ED.selectByID(txt.getText()) != null) {

            return true;
        }
        return false;

    }

    //ChecknullImage
    //EditStatus
    private void editBtn(boolean b) {
        txtID.setEditable(b);
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtEmail.setEditable(b);
        txtIdentityCard.setEditable(b);
        txtPassword.setEditable(b);
        txtPhoneNumber.setEditable(b);
        txtUsername.setEditable(b);
        rdoMale.setEnabled(b);
        rdoFemale.setEnabled(b);
        rdoManager.setEnabled(b);
        rdoStaff.setEnabled(b);
        dcDate.setEnabled(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnUpdate.setEnabled(b);
        btnEdit.setEnabled(!b);
    }

    private void BtnNew(boolean b) {
        txtID.setEditable(b);
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtEmail.setEditable(b);
        txtIdentityCard.setEditable(b);
        txtPassword.setEditable(b);
        txtPhoneNumber.setEditable(b);
        txtUsername.setEditable(b);
        rdoMale.setEnabled(b);
        rdoFemale.setEnabled(b);
        rdoManager.setEnabled(b);
        rdoStaff.setEnabled(b);
        dcDate.setEnabled(b);
        btnNew.setEnabled(!b);
        btnAdd.setEnabled(b);
        btnDelete.setEnabled(b);
        btnUpdate.setEnabled(!b);
        btnEdit.setEnabled(!b);
    }

    private void btnEditManager(boolean b) {
        txtID.setEditable(!b);
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtEmail.setEditable(b);
        txtIdentityCard.setEditable(b);
        txtPassword.setEditable(b);
        txtPhoneNumber.setEditable(b);
        txtUsername.setEditable(b);
        rdoMale.setEnabled(b);
        rdoFemale.setEnabled(b);
        rdoManager.setEnabled(b);
        rdoStaff.setEnabled(b);
        dcDate.setEnabled(b);
        btnUpdate.setEnabled(b);
    }

    private void btnStatusStaff(boolean b) {
        txtID.setEditable(b);
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtEmail.setEditable(b);
        txtIdentityCard.setEditable(b);
        txtPassword.setEditable(b);
        txtPhoneNumber.setEditable(b);
        txtUsername.setEditable(b);
        rdoMale.setEnabled(b);
        rdoManager.setEnabled(b);
        dcDate.setEnabled(b);
        btnUpdate.setEnabled(b);
        btnDelete.setEnabled(b);
        btnEdit.setEnabled(b);
        btnNew.setEnabled(b);
        btnAdd.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        lblID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        lblDate = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();
        lblAddress = new javax.swing.JLabel();
        lblCard = new javax.swing.JLabel();
        lblNumnerPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        rdoManager = new javax.swing.JRadioButton();
        rdoStaff = new javax.swing.JRadioButton();
        lblUserName = new javax.swing.JLabel();
        lblPassWord = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        btnChangePass = new javax.swing.JButton();
        txtID = new GUI.TextField();
        txtPhoneNumber = new GUI.TextField();
        txtUsername = new GUI.TextField();
        txtName = new GUI.TextField();
        txtAddress = new GUI.TextField();
        txtEmail = new GUI.TextField();
        txtIdentityCard = new GUI.TextField();
        txtPassword = new GUI.TextField();
        btnEdit = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(80, 23, 231));
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblEmployee.setBackground(new java.awt.Color(222, 221, 248));
        tblEmployee.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        tblEmployee.setGridColor(new java.awt.Color(15, 106, 205));
        tblEmployee.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblEmployee.setRowHeight(25);
        tblEmployee.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblEmployee.setShowVerticalLines(false);
        tblEmployee.getTableHeader().setReorderingAllowed(false);
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        tblEmployee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblEmployeeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEmployeeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        lblID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 0));
        lblID.setText("ID:");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setText("Name:");

        lblSex.setBackground(new java.awt.Color(146, 42, 225));
        lblSex.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSex.setText("Sex:");

        rdoMale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoMale);
        rdoMale.setText("Male");

        rdoFemale.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate.setText("Date:");

        dcDate.setBackground(new java.awt.Color(255, 255, 255));
        dcDate.setToolTipText("");
        dcDate.setDateFormatString("dd-MM-yyyy");

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAddress.setText("Address:");

        lblCard.setBackground(new java.awt.Color(146, 42, 225));
        lblCard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCard.setText("Identity card:");

        lblNumnerPhone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumnerPhone.setText("Phone number:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEmail.setText("Email:");

        lblRole.setBackground(new java.awt.Color(146, 42, 225));
        lblRole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRole.setText("Role:");

        rdoManager.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoManager);
        rdoManager.setText("Manager");

        rdoStaff.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoStaff);
        rdoStaff.setText("Staff");

        lblUserName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserName.setText("Username:");

        lblPassWord.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPassWord.setText("Password:");

        btnNew.setBackground(new java.awt.Color(92, 84, 179));
        btnNew.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/New_user_32px.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(92, 84, 179));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update_32px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(92, 84, 179));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete_32px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(92, 84, 179));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add_32px_1.png"))); // NOI18N
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

        btnChangePass.setBackground(new java.awt.Color(255, 255, 255));
        btnChangePass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChangePass.setForeground(new java.awt.Color(255, 0, 0));
        btnChangePass.setText("Change password ?");
        btnChangePass.setBorder(null);
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setForeground(new java.awt.Color(204, 0, 0));
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtUsername.setEditable(false);
        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtName.setEditable(false);
        txtName.setBackground(new java.awt.Color(255, 255, 255));
        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtAddress.setEditable(false);
        txtAddress.setBackground(new java.awt.Color(255, 255, 255));
        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmail.setEditable(false);
        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtIdentityCard.setEditable(false);
        txtIdentityCard.setBackground(new java.awt.Color(255, 255, 255));
        txtIdentityCard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPassword.setEditable(false);
        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnEdit.setBackground(new java.awt.Color(92, 84, 179));
        btnEdit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edit_32px.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblID)
                                .addComponent(lblDate)
                                .addComponent(lblNumnerPhone)
                                .addComponent(lblUserName))
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblName)
                                .addComponent(lblAddress)
                                .addComponent(lblEmail)
                                .addComponent(lblPassWord))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSex)
                                    .addGap(0, 0, 0)
                                    .addComponent(rdoMale)
                                    .addGap(0, 0, 0)
                                    .addComponent(rdoFemale))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblRole)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoManager)
                                    .addGap(0, 0, 0)
                                    .addComponent(rdoStaff))
                                .addComponent(btnChangePass)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblCard)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtIdentityCard, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(895, 895, 895)
                            .addComponent(btnChange)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(btnDelete)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnUpdate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChange)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblID)
                                .addGap(27, 27, 27)
                                .addComponent(lblDate)
                                .addGap(22, 22, 22)
                                .addComponent(lblNumnerPhone)
                                .addGap(19, 19, 19)
                                .addComponent(lblUserName))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblName)
                                .addGap(21, 21, 21)
                                .addComponent(lblAddress)
                                .addGap(25, 25, 25)
                                .addComponent(lblEmail)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPassWord)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChangePass)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lblSex))
                                    .addComponent(rdoMale)
                                    .addComponent(rdoFemale))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblCard))
                                    .addComponent(txtIdentityCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lblRole))
                                    .addComponent(rdoManager)
                                    .addComponent(rdoStaff)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // TODO add your handling code here:
        findAndLoadRowToControl(tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString());
        if (Image_Auth.USER.isEpeIsRole()) {
            editBtn(false);
        } else {
            txtPassword.setText("********");
            btnStatusStaff(false);
        }

    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (ValidateSupport.isNull(txtID)
                || ValidateSupport.isNull(txtName)
                || ValidateSupport.isNull(txtAddress)
                || ValidateSupport.isNull(txtEmail)
                || ValidateSupport.isNull(txtIdentityCard)
                || ValidateSupport.isNull(txtPhoneNumber)
                || ValidateSupport.isNull(txtUsername)
                || ValidateSupport.isNull(txtPassword)
                || ValidateSupport.isSeleted(rdoMale)
                || ValidateSupport.isSeleted(rdoFemale)
                || ValidateSupport.isSeleted(rdoManager)
                || ValidateSupport.isSeleted(rdoStaff)
             ) 
        {
            Mgsbox.error(this, "Please fill out the form...");
        } else if (!ValidateSupport.checkEmployeeID(txtID) || !ValidateSupport.checkSDT(txtPhoneNumber)
                || !ValidateSupport.isCCCD(txtIdentityCard)
                || !ValidateSupport.isEmail(txtEmail)) {
            Mgsbox.error(this, "Please fill in the correct format as required !!!");
        } else if (!isDuplicateID(txtID)) {
            insert(getModel());
            editBtn(false);   
            clearForm();
        }else{
            Mgsbox.error(this, "This employee code already exists");
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (Image_Auth.USER.isEpeIsRole()) {
            if (!isMySelf(txtID.getText())) {
                delete(txtID.getText());
                System.out.println(txtID.toString());
            }else{
                Mgsbox.alert(this, "You can't delete yourself!!!");
            } 
        } else {
            Mgsbox.error(this, "Just manager can delete!!!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            update(getModel());
            clearForm();
            editBtn(false);   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        BtnNew(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void lblImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMouseClicked
        // TODO add your handling code here:
        selectImage();
         
    }//GEN-LAST:event_lblImgMouseClicked

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed

        new ChangePasswordForm().setVisible(true);
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnEditManager(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblEmployeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeeKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblEmployeeKeyPressed

    private void btnChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            btnChange.setToolTipText("Cick 2 for change Background");
            setBackground(Color.decode("#9badf2"));
            Controller.Helper.BackgroundC1.ChangeRdo(rdoFemale);
            Controller.Helper.BackgroundC1.ChangeRdo(rdoMale);
            Controller.Helper.BackgroundC1.ChangeRdo(rdoManager);
            Controller.Helper.BackgroundC1.ChangeRdo(rdoStaff);
            Controller.Helper.BackgroundC1.ChangeTxt(txtID);
            Controller.Helper.BackgroundC1.ChangeTxt(txtAddress);
            Controller.Helper.BackgroundC1.ChangeTxt(txtEmail);
            Controller.Helper.BackgroundC1.ChangeTxt(txtIdentityCard);
            Controller.Helper.BackgroundC1.ChangeTxt(txtName);
            Controller.Helper.BackgroundC1.ChangeTxt(txtPassword);
            Controller.Helper.BackgroundC1.ChangeTxt(txtPhoneNumber);
            Controller.Helper.BackgroundC1.ChangeTxt(txtUsername);
            Controller.Helper.BackgroundC1.ChangeBtn(btnChange);
            Controller.Helper.BackgroundC1.ChangeBtn(btnChangePass);
        } else if (evt.getClickCount() == 2) {
            btnChange.setToolTipText("Cick 1 for change Background");
            setBackground(Color.white);
            rdoFemale.setBackground(Color.white);
            rdoMale.setBackground(Color.white);
            rdoManager.setBackground(Color.white);
            rdoStaff.setBackground(Color.white);
            btnChangePass.setBackground(Color.white);
            txtID.setBackground(Color.WHITE);
            txtAddress.setBackground(Color.WHITE);
            txtEmail.setBackground(Color.WHITE);
            txtIdentityCard.setBackground(Color.WHITE);
            txtName.setBackground(Color.WHITE);
            txtPassword.setBackground(Color.WHITE);
            txtPhoneNumber.setBackground(Color.WHITE);
            txtUsername.setBackground(Color.WHITE);
            dcDate.setBackground(Color.WHITE);
            btnChange.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btnChangeMouseClicked

    private void tblEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeeKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tblEmployeeKeyReleased

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCard;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumnerPhone;
    private javax.swing.JLabel lblPassWord;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoManager;
    private javax.swing.JRadioButton rdoStaff;
    public javax.swing.JTable tblEmployee;
    private GUI.TextField txtAddress;
    private GUI.TextField txtEmail;
    private GUI.TextField txtID;
    private GUI.TextField txtIdentityCard;
    private GUI.TextField txtName;
    private GUI.TextField txtPassword;
    private GUI.TextField txtPhoneNumber;
    private GUI.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
