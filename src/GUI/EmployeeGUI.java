package GUI;

import CustomizeGUI.Componets.FrameCamera;
import Controller.Helper.CreateExcel;
import Controller.Helper.DateSupport;
import Controller.Helper.EmailSupport;
import Controller.Helper.Image_Auth;
import static Controller.Helper.Image_Auth.USER;
import Controller.Helper.Mgsbox;
import Controller.Helper.QRCodeSupport;
import Controller.Helper.ValidateSupport;
import Controller.ModelDAO.CustomerDAO;
import Controller.ModelDAO.EmployeeDAO;
import Controller.ModelDAO.MedicineDAO;
import static GUI.ChangePasswordForm.REGEX_PASSWORD;
import Model.Customer;
import Model.Employee;
import Model.Medicine;
import com.google.zxing.WriterException;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EmployeeGUI extends javax.swing.JPanel {

    EmployeeDAO ED = new EmployeeDAO();
    File lastPast;
    String fileImg = "";
    public static BufferedImage IMAGE_WEBCAM = null;

    public EmployeeGUI() {
        initComponents();
        EditTable(tblEmployee);
        setOpaque(false);
        lblImage.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        LoadDataToTable(new EmployeeDAO().selectAll());
        loadDataToSort();
        setStatusControl(false);
        initRole();
    }

    private void initRole() {
        if (Image_Auth.USER.isEpeIsRole()) {
            btnUpdate.setVisible(false);
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
        } else {

            btnNew.setVisible(false);
            btnEdit.setVisible(false);
            btnUpdate.setVisible(false);
            btnAdd.setVisible(false);
            btnDelete.setVisible(false);
        }
    }

    private void LoadDataToTable(List<Employee> listEmployee) {
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        model.setRowCount(0);
        try {
            listEmployee.forEach(e -> {
                model.addRow(new Object[]{
                    e.getEpeID(),
                    e.getEpeName(),
                    e.isEpeIsGender() ? "Male" : "Femail",
                    e.getEpeDayOfBirth(),
                    e.getEpeNumberPhone(),
                    e.getEpeEmail(),
                    e.isEpeIsRole() ? "Manager" : "Employee"});
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> sortByLastName() {
        List<Employee> listSort = new EmployeeDAO().selectAll();
        Collections.sort(listSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                List<String> nhanVien1 = Arrays.asList(o1.getEpeName().split(" "));
                Collections.reverse(nhanVien1);
                List<String> nhanVien2 = Arrays.asList(o2.getEpeName().split(" "));
                Collections.reverse(nhanVien2);
                return nhanVien1.get(0).substring(0, 1).compareTo(nhanVien2.get(0).substring(0, 1));
            }
        });
        return listSort;
    }

    public List<Employee> SortByRole() {
        List<Employee> listSort = new EmployeeDAO().selectAll();
        Collections.sort(listSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee c1, Employee c2) {
                return Boolean.compare(c1.isEpeIsRole(), c2.isEpeIsRole());
            }
        });
        return listSort;
    }

    private void loadDataToSort() {
        cboSort.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() == 1) {
                    LoadDataToTable(sortByLastName());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() == 2) {
                    LoadDataToTable(SortByRole());
                } else if (e.getStateChange() == ItemEvent.SELECTED && cboSort.getSelectedIndex() < 1) {
                    LoadDataToTable(new EmployeeDAO().selectAll());
                }
            }
        });
    }

    private void insert(Employee model) {
        try {
            ED.insert(model);
            this.LoadDataToTable(new EmployeeDAO().selectAll());
            this.clearForm();
            Mgsbox.alert(this, "Successfully added employees");
        } catch (Exception e) {
            Mgsbox.alert(this, "Add employee failed !!!");
        }

    }

    private void update(Employee model) {

        try {
            ED.update(model);
            this.LoadDataToTable(new EmployeeDAO().selectAll());
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
                this.LoadDataToTable(new EmployeeDAO().selectAll());
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

    private void setModel(Employee model) throws WriterException {
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
            if (Image_Auth.USER.isEpeIsRole()) {
                txtUsername.setText(model.getEpeUserName());
                txtPassword.setText(model.getEpePassword());
            } else {
                txtUsername.setText(model.getEpeUserName().replace(model.getEpeUserName(), "Anonymous"));
                txtPassword.setText(hidePassWord(model.getEpePassword()));
            }
            lblCardNameEmployee.setText(model.getEpeName());
            lblCardRoleEmployee.setText(model.isEpeIsRole() ? "Manager" : "Employee");
            lblQRcode.setIcon(new ImageIcon(QRCodeSupport.createQRCode(model.getEpeUserName() + "/" + txtPassword.getText(), lblQRcode.getWidth(), lblQRcode.getHeight() + 30)));
            if (model.getEpeImage() != null) {
                lblImage.setIcon(Image_Auth.readImage(new File("Image", model.getEpeImage()), lblImage.getWidth(), lblImage.getHeight()));
                lblImage.setToolTipText(model.getEpeImage());
            } else {
                lblImage.setIcon(Image_Auth.readImage(new File("Image", "employee.png"), lblImage.getWidth(), lblImage.getHeight()));
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
        model.setEpeEmail(txtEmail.getText().trim());
        model.setEpeImage(lblImage.getToolTipText());
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
        lblImage.setIcon(Image_Auth.readImage(new File("Image", "employee.png"), lblImage.getWidth(), lblImage.getHeight()));
        lblImage.setToolTipText("employee.png");
        lblCardNameEmployee.setText("");
        lblCardRoleEmployee.setText("");
        lblQRcode.setIcon(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();

    }

    private boolean isMySelf(String EmloyeeID) {
        return EmloyeeID.equals(USER.getEpeID());
    }

    void selectImage() {
        lblImage.setVisible(true);
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter excelExtensionFilter = new FileNameExtensionFilter("Image file", "png", "jpg");
        fileChooser.setFileFilter(excelExtensionFilter);
        if (lastPast != null) {
            fileChooser.setCurrentDirectory(lastPast);
        }
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            lblImage.setIcon(Image_Auth.readImage(file, lblImage.getWidth(), lblImage.getHeight()));
            lblImage.setToolTipText(file.getName());
            Image_Auth.saveImage(file);
            fileImg = fileChooser.getSelectedFile().getName();
            lastPast = fileChooser.getSelectedFile().getParentFile();
        }

    }

    public void search(String text) {
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblEmployee.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(text));
    }

    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    public boolean isValidYearOlds(JDateChooser date) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date.getDate());
        c2.setTime(DateSupport.now());
        long a = (c2.getTime().getTime() - c1.getTime().getTime());
        if (a < 5843) {
            Mgsbox.error(this, "Employees are not of working age!");
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidateForm() {
        if (ValidateSupport.isNull(txtID) || ValidateSupport.isNull(txtName)
                || ValidateSupport.isNull(txtAddress) || ValidateSupport.isNull(txtEmail)
                || ValidateSupport.isNull(txtIdentityCard) || ValidateSupport.isNull(txtPhoneNumber)
                || ValidateSupport.isNull(txtUsername) || ValidateSupport.isNull(txtPassword)
                || buttonGroup1.getSelection() == null || buttonGroup2.getSelection() == null) {
            Mgsbox.error(this, "Please fill out the form...");
            return false;
        } else if (!ValidateSupport.checkEmployeeID(txtID)) {
            Mgsbox.error(this, "Employee ID is invalid characters");
            txtID.requestFocus();
            return false;
        } else if (!ValidateSupport.checkSDT(txtPhoneNumber)) {
            Mgsbox.error(this, "Your number phone is invalid!...");
            txtPhoneNumber.requestFocus();
            return false;
        } else if (!ValidateSupport.isCCCD(txtIdentityCard)) {
            Mgsbox.error(this, "Indentity Card must be 12 number!");
            txtIdentityCard.requestFocus();
            return false;
        } else if (!ValidateSupport.isEmail(txtEmail)) {
            Mgsbox.error(this, "Your email is invalid!");
            txtEmail.requestFocus();
            return false;
        } else if (isDuplicateID(txtID)) {
            Mgsbox.error(this, "This employee code already exists");
            txtID.requestFocus();
            return false;
        } else if (isDuplicateAccount(txtUsername.getText())) {
            Mgsbox.error(this, "Username already exists!");
            txtUsername.requestFocus();
            return false;
        } else if (!txtPassword.getText().matches(REGEX_PASSWORD)) {
            Mgsbox.alert(this, "New password is too weak. Try again!");
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isDuplicateID(JTextField txt) {
        txt.setBackground(white);
        if (ED.selectByID(txt.getText()) != null) {
            return true;
        }
        return false;

    }

    private boolean isDuplicateAccount(String Account) {
        return new EmployeeDAO().selectByUsername(Account) != null;
    }

    private void setStatusControl(boolean flag) {
        txtID.setEditable(flag);
        txtName.setEditable(flag);
        txtAddress.setEditable(flag);
        txtEmail.setEditable(flag);
        txtIdentityCard.setEditable(flag);
        txtPassword.setEditable(flag);
        txtPhoneNumber.setEditable(flag);
        txtUsername.setEditable(flag);
        rdoMale.setEnabled(flag);
        rdoFemale.setEnabled(flag);
        rdoManager.setEnabled(flag);
        rdoStaff.setEnabled(flag);
        dcDate.setEnabled(flag);
        btnOpenfile.setEnabled(flag);
        btnOpenCamera.setEnabled(flag);
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
        pnlCardEmployee = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        lblQRcode = new javax.swing.JLabel();
        lblCardRoleEmployee = new javax.swing.JLabel();
        lblText = new javax.swing.JLabel();
        lblCardNameEmployee = new javax.swing.JLabel();
        lblCardEmployee = new javax.swing.JLabel();
        btnOpenfile = new javax.swing.JButton();
        btnOpenCamera = new javax.swing.JButton();
        btnCreateExcel = new javax.swing.JButton();
        cboSort = new javax.swing.JComboBox<>();
        txtSearch = new GUI.TextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(80, 23, 231));
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblEmployee.setBackground(new java.awt.Color(222, 221, 248));
        tblEmployee.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Sex", "Date", "Phone number", "Email", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        rdoManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoManagerMouseClicked(evt);
            }
        });

        rdoStaff.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoStaff);
        rdoStaff.setText("Staff");
        rdoStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoStaffActionPerformed(evt);
            }
        });

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

        txtID.setForeground(new java.awt.Color(204, 0, 0));
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });

        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtIdentityCard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

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

        pnlCardEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlCardEmployee.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 100, 80));

        lblQRcode.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardEmployee.add(lblQRcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 60, 50));

        lblCardRoleEmployee.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCardRoleEmployee.setForeground(new java.awt.Color(204, 255, 204));
        lblCardRoleEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardRoleEmployee.setToolTipText("");
        pnlCardEmployee.add(lblCardRoleEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        lblText.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblText.setForeground(new java.awt.Color(255, 255, 255));
        lblText.setText("EMPLOYEE");
        pnlCardEmployee.add(lblText, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        lblCardNameEmployee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCardNameEmployee.setForeground(new java.awt.Color(0, 0, 204));
        lblCardNameEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardNameEmployee.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlCardEmployee.add(lblCardNameEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 160, 30));
        lblCardNameEmployee.getAccessibleContext().setAccessibleDescription("");

        lblCardEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/EmployeeCard.png"))); // NOI18N
        pnlCardEmployee.add(lblCardEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 270));

        btnOpenfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/folder.png"))); // NOI18N
        btnOpenfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenfileActionPerformed(evt);
            }
        });

        btnOpenCamera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Opencamera.png"))); // NOI18N
        btnOpenCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCameraActionPerformed(evt);
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

        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None Sort", "Sort Name Employee", "Sort Role" }));

        txtSearch.setText(" ");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search_24px.png"))); // NOI18N
        jLabel1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(895, 895, 895)
                .addComponent(btnChange))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlCardEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOpenfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOpenCamera))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreateExcel)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addGap(75, 75, 75)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblName)
                                .addGap(39, 39, 39)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSex)
                                .addGap(0, 0, 0)
                                .addComponent(rdoMale)
                                .addGap(0, 0, 0)
                                .addComponent(rdoFemale))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addGap(61, 61, 61)
                                .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAddress)
                                .addGap(25, 25, 25)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCard)
                                .addGap(10, 10, 10)
                                .addComponent(txtIdentityCard, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUserName)
                                .addGap(29, 29, 29)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPassWord)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(btnDelete)
                                .addGap(10, 10, 10)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNumnerPhone)
                                .addGap(4, 4, 4)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblEmail)
                                .addGap(40, 40, 40)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRole)
                                .addGap(18, 18, 18)
                                .addComponent(rdoManager)
                                .addGap(0, 0, 0)
                                .addComponent(rdoStaff))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(458, 458, 458)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnChange)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblID))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblName))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblSex))
                            .addComponent(rdoMale)
                            .addComponent(rdoFemale))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblDate))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAddress)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblCard))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtIdentityCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblNumnerPhone))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblEmail)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblRole))
                            .addComponent(rdoManager)
                            .addComponent(rdoStaff))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblUserName))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblPassWord))
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlCardEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOpenfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOpenCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreateExcel)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // TODO add your handling code here:
        findAndLoadRowToControl(tblEmployee.getValueAt(tblEmployee.getSelectedRow(), 0).toString());
        setStatusControl(false);
        if (Image_Auth.USER.isEpeIsRole()) {
            btnDelete.setVisible(true);
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (isValidateForm() && isValidYearOlds(dcDate)) {
            insert(getModel());
            Thread sendMail = new Thread(() -> {
                try {
                    EmailSupport.send(txtEmail.getText().trim(), "WELCOME TO YOU", "Welcome to our company",
                            Image_Auth.bufferedImageToFile(Image_Auth.getScreenShot(pnlCardEmployee), txtID.getText()));
                } catch (MessagingException | IOException ex) {
                    Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sendMail.start();
            clearForm();
            btnAdd.setVisible(false);
            btnNew.setVisible(true);
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (Image_Auth.USER.isEpeIsRole()) {
            if (!isMySelf(txtID.getText())) {
                delete(txtID.getText());
            } else {
                Mgsbox.alert(this, "You can't delete yourself!!!");
            }
        } else {
            Mgsbox.error(this, "Just manager can delete!!!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            Thread sendMail = new Thread(() -> {
                try {
                    EmailSupport.send(txtEmail.getText().trim(), "CHANGE YOUR INFORMATION", "Your information has changed so we send you Employee Card new!",
                            Image_Auth.bufferedImageToFile(Image_Auth.getScreenShot(pnlCardEmployee), txtID.getText()));
                } catch (MessagingException | IOException ex) {
                    Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sendMail.start();
            update(getModel());
            clearForm();
            btnUpdate.setVisible(false);
            btnEdit.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearForm();
        setStatusControl(true);
        btnNew.setVisible(false);
        btnAdd.setVisible(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnEdit.setVisible(false);
        btnUpdate.setVisible(true);
        setStatusControl(true);
        txtPassword.setEditable(false);
        txtID.setEditable(false);
        txtUsername.setEditable(false);
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
        } else if (evt.getClickCount() == 2) {
            btnChange.setToolTipText("Cick 1 for change Background");
            setBackground(Color.white);
            rdoFemale.setBackground(Color.white);
            rdoMale.setBackground(Color.white);
            rdoManager.setBackground(Color.white);
            rdoStaff.setBackground(Color.white);
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

    private void btnOpenfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenfileActionPerformed
        // TODO add your handling code here:
        selectImage();
    }//GEN-LAST:event_btnOpenfileActionPerformed

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        try {
            // TODO add your handling code here:
            lblQRcode.setIcon(new ImageIcon(QRCodeSupport.createQRCode(txtUsername.getText().trim() + "/" + txtPassword.getText().trim(), lblQRcode.getWidth(), lblQRcode.getHeight() + 30)));

        } catch (WriterException ex) {
            Logger.getLogger(EmployeeGUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            lblQRcode.setIcon(new ImageIcon(QRCodeSupport.createQRCode(txtUsername.getText().trim() + "/" + txtPassword.getText().trim(), lblQRcode.getWidth(), lblQRcode.getHeight() + 30)));

        } catch (WriterException ex) {
            Logger.getLogger(EmployeeGUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        // TODO add your handling code here:
        lblCardNameEmployee.setText(txtName.getText());
    }//GEN-LAST:event_txtNameKeyReleased

    private void rdoManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoManagerMouseClicked
        // TODO add your handling code here:
        lblCardRoleEmployee.setText("Manager");
    }//GEN-LAST:event_rdoManagerMouseClicked

    private void rdoStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoStaffActionPerformed
        // TODO add your handling code here:
        lblCardRoleEmployee.setText("Employee");
    }//GEN-LAST:event_rdoStaffActionPerformed

    private void btnOpenCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCameraActionPerformed
        // TODO add your handling code here:o
        if (txtID.getText().length() != 0) {
            new FrameCamera().setVisible(true);
            Thread setImage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (IMAGE_WEBCAM != null) {
                            lblImage.setIcon(
                                    Image_Auth.readImage(
                                            Image_Auth.imageCameraToFile(
                                                    IMAGE_WEBCAM, txtID.getText()), lblImage.getWidth(), lblImage.getHeight()));
                            IMAGE_WEBCAM = null;
                            lblImage.setToolTipText(txtID.getText() + ".png");
                            break;

                        }
                    }
                }

            });
            setImage.start();
        }

    }//GEN-LAST:event_btnOpenCameraActionPerformed

    private void btnCreateExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateExcelActionPerformed
        // TODO add your handling code here:
        CreateExcel.ExportToExcel(tblEmployee, "Employee");
    }//GEN-LAST:event_btnCreateExcelActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        search(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyPressed

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
    private javax.swing.JButton btnCreateExcel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpenCamera;
    private javax.swing.JButton btnOpenfile;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboSort;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCard;
    private javax.swing.JLabel lblCardEmployee;
    private javax.swing.JLabel lblCardNameEmployee;
    private javax.swing.JLabel lblCardRoleEmployee;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    public javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumnerPhone;
    private javax.swing.JLabel lblPassWord;
    private javax.swing.JLabel lblQRcode;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlCardEmployee;
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
    private GUI.TextField txtSearch;
    private GUI.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
