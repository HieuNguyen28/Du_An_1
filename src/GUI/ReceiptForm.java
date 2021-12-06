package GUI;

import CustomizeGUI.Componets.PaymentSuccess;
import CustomizeGUI.Componets.FrameCameraQRCodeVoucher;
import Controller.Helper.DateSupport;
import Controller.Helper.Image_Auth;
import Controller.Helper.Mgsbox;
import Controller.ModelDAO.CustomerDAO;
import Controller.ModelDAO.InvoiceDAO;
import Controller.ModelDAO.MedicineDAO;
import Controller.ModelDAO.VoucherDAO;
import Controller.ModelDAO.WareHouseDAO;
import Model.Customer;
import Model.Invoice;
import Model.Medicine;
import Model.Voucher;
import Model.WareHouse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laxus
 */
public class ReceiptForm extends javax.swing.JPanel {

    /**
     * Creates new form Recept
     */
    public ReceiptForm() {
        initComponents();
        EditTable(tblReceipt);
        loadDataToRrugName();
        loadDataToCustomer();
        loadDataToVoucher();
        tblReceipt.addKeyListener(KA);
    }
    public static String TEXT_FROM_QRCODE_VOUCHER = null;
    private Double bHeight = 0.0;
    private InvoiceDAO idao = new InvoiceDAO();
    private MedicineDAO mdao = new MedicineDAO();
    private CustomerDAO cdao = new CustomerDAO();
    private WareHouseDAO wdao = new WareHouseDAO();
    private WareHouse wareHouse = null;
    private String maKH = "";

    private boolean check() {
        Invoice invoice = idao.selectByID(txtReceiptID.getText());
        if (txtReceiptID.getText().equals("") || txtReceiptID.getText().equalsIgnoreCase("HD")) {
            Mgsbox.alert(this, "Please fill out receipt ID");
            return false;
        } else if (cbbCustomerPhoneNumber.getSelectedIndex() == 0) {
            Mgsbox.alert(this, "Please choose customer");
            return false;
        } else if (invoice != null) {
            Mgsbox.alert(this, "Duplicate receipt ID");
            return false;
        } else if (!txtReceiptID.getText().matches("^(HD)[0-9]{1,6}$")) {
            Mgsbox.alert(this, "Invalid receipt ID. Ex:HD001");
            return false;
        } else if (tblReceipt.getRowCount() == 0) {
            Mgsbox.alert(this, "There is no drug in receipt");
            return false;
        } else if (Double.parseDouble(txtCash.getText()) < Double.parseDouble(lblTotal.getText())) {
            Mgsbox.error(this, "You not enough money to pay this bill!");
            return false;
        }
        return true;
    }

    private boolean checkID() {
        List<Invoice> invoice = idao.selectAll();
        for (Invoice list : invoice) {
            if (list.getIvID().equalsIgnoreCase(txtReceiptID.getText())) {
                return true;
            }
        }
        return false;
    }

    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(9);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int r = tblReceipt.getRowCount();
            ImageIcon icon = new ImageIcon("src/Icons/Logo_Print.png");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 12;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    g2d.drawImage(icon.getImage(), 80, 20, 90, 30, null);
                    y += yShift + 30;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("             Pharmacy        ", 12, y);
                    y += yShift;
                    g2d.drawString("  Gia re - uy tin - chat luong ! ", 12, y);
                    y += yShift;
                    g2d.drawString("          CTY TNHH Nhom 4 ", 12, y);
                    y += yShift;
                    g2d.drawString("      Ma so thue: 2923144574 ", 12, y);
                    y += yShift;
                    g2d.drawString("Phone: 0123456789 Fax: 0373 917333", 12, y);
                    y += yShift;
                    g2d.drawString("Date: " + DateSupport.now(), 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("          Phieu tinh tien", 10, y);
                    y += yShift;
                    g2d.drawString(" Item Name                  Price   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;

                    for (int s = 0; s < r; s++) {
                        g2d.drawString(" " + tblReceipt.getValueAt(s, 0) + "                            ", 10, y);
                        y += yShift;
                        g2d.drawString("      " + tblReceipt.getValueAt(s, 1) + " * " + tblReceipt.getValueAt(s, 2), 10, y);
                        double subtotal = Double.valueOf(tblReceipt.getValueAt(s, 1).toString()) * Double.valueOf(tblReceipt.getValueAt(s, 2).toString());
                        g2d.drawString(String.valueOf(subtotal), 160, y);
                        y += yShift;

                    }

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Total amount:               " + lblTotal.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Cash      :                 " + txtCash.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Balance   :                 " + lblBalance.getText() + "   ", 10, y);
                    y += yShift;

                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       Hotline: 0123456789          ", 10, y);
                    y += yShift;
                    g2d.drawString("   CONTACT: pharmacy@gmail.com       ", 10, y);
                    y += yShift;
                    y += yShift;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    private void EditTable(JTable a) {
        a.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        a.getTableHeader().setOpaque(false);
        a.getTableHeader().setBackground(new Color(32, 136, 203));
        a.getTableHeader().setForeground(new Color(255, 255, 255));
        a.setRowHeight(25);
    }

    private void loadDataToRrugName() {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbbDrugName.getModel();
        dcm.removeAllElements();
        dcm.addElement("Select...");
        try {
            List<Medicine> list = mdao.selectAll();
            for (Medicine medicine : list) {
                dcm.addElement(medicine.getMdcName());
            }
        } catch (Exception e) {
        }
        cbbDrugName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String drugName = cbbDrugName.getSelectedItem().toString();
                    loadDataToBatchID(drugName);
                }
            }
        });
    }

    private void loadDataToCustomer() {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbbCustomerPhoneNumber.getModel();
        dcm.removeAllElements();
        dcm.addElement("Select...");
        try {
            List<Customer> list = cdao.selectAll();
            for (Customer customer : list) {
                dcm.addElement(customer.getCtmNumberPhone());
            }
        } catch (Exception e) {
        }
        cbbCustomerPhoneNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cbbCustomerPhoneNumber.getSelectedIndex() != 0) {
                        String pn = (String) cbbCustomerPhoneNumber.getSelectedItem();
                        lblPN.setText(pn);
                        Customer customer = cdao.selectByPN(pn);
                        maKH = customer.getCtmID();
                        lblName.setText(customer.getCtmName());
                    } else {
                        lblPN.setText("");
                        lblName.setText("");
                    }
                }
            }
        });
    }

    private void loadDataToBatchID(String dn) {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbbBatchID.getModel();
        dcm.removeAllElements();
        dcm.addElement("Select...");
        try {
            List<Medicine> medicine = mdao.selectByMedicineName(dn);
            for (Medicine list : medicine) {
                dcm.addElement(list.getMdcBatchID());
            }
        } catch (Exception e) {
        }

        cbbBatchID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && cbbBatchID.getSelectedIndex() != 0) {
                    if (cbbBatchID.getSelectedIndex() != 0) {
                        String batchID = (String) cbbBatchID.getSelectedItem();
                        wareHouse = new WareHouseDAO().selectByID(batchID);
                        txtRemainingAmount.setText(String.valueOf(wareHouse.getWhRemainingAmout()));
                        txtExpirationDate.setText(String.valueOf(wareHouse.getWhExpiryDate()));
                    } else {
                        lblPN.setText("");
                        lblName.setText("");
                    }
                }
            }
        });
    }

    VoucherDAO vdao = new VoucherDAO();

    private void loadDataToVoucher() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbVoucher.getModel();
        model.removeAllElements();
        model.addElement("Select...");
        try {
            List<Voucher> list = vdao.selectAll();
            for (Voucher voucher : list) {
                model.addElement(voucher.getVcID());
            }
        } catch (Exception e) {
        }

        cbbVoucher.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && cbbVoucher.getSelectedIndex() != 0) {
                    double total = Double.valueOf(lblTotal.getText());
                    Voucher voucher = new VoucherDAO().selectByID(cbbVoucher.getSelectedItem().toString());
                    int i = DateSupport.now().compareTo(voucher.getVcEndDate());
                    if (total < voucher.getVcTotalBillCanAdd()) {
                        Mgsbox.alert(null, "Total bill doesn't enough");
                        cbbVoucher.setSelectedIndex(0);
                        TEXT_FROM_QRCODE_VOUCHER = null;
                    } else if (!checkVoucher()) {
                        Mgsbox.alert(null, "No discounted drugs in the bill");
                        cbbVoucher.setSelectedIndex(0);
                        TEXT_FROM_QRCODE_VOUCHER = null;
                    } else if (i > 0) {
                        Mgsbox.alert(null, "The voucher has expired");
                        cbbVoucher.setSelectedIndex(0);
                        TEXT_FROM_QRCODE_VOUCHER = null;
                    }
                }
            }
        });
    }

    private boolean checkVoucher() {
        Voucher voucher = new VoucherDAO().selectByID(cbbVoucher.getSelectedItem().toString());
        for (int i = 0; i < tblReceipt.getRowCount(); i++) {
            List<Medicine> list = new MedicineDAO().selectByMedicineName(tblReceipt.getValueAt(i, 0).toString());
            for (Medicine medicine : list) {
                if (medicine.getMdcID().equals(voucher.getVcMedicineID())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void clearForm() {
        lblName.setText("");
        lblPN.setText("");
        lblTotal.setText("0");
        DefaultTableModel model = (DefaultTableModel) tblReceipt.getModel();
        for (int i = 0; i < tblReceipt.getRowCount(); i++) {
            model.removeRow(i);
        }
        txtReceiptID.setText("HD");
        cbbCustomerPhoneNumber.setSelectedIndex(0);
        cbbVoucher.setSelectedIndex(0);
    }

//    private boolean checkIteamADD(){
//        if(cbbDrugName.getSelectedIndex()==0){
//            Mgsbox.error(this, "You must selected DrugName");
//        }else if(cbbCustomerPhoneNumber.getSelectedIndex()==0){
//            Mgsbox.error(this, "You must selected DrugName");
//        }
//        return true;
//        
//    }
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
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbbDrugName = new ComboBox_Suggestion.ComboBoxSuggestion();
        btnPay = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReceipt = new javax.swing.JTable();
        cbbCustomerPhoneNumber = new ComboBox_Suggestion.ComboBoxSuggestion();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
lblTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPN = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbbBatchID = new ComboBox_Suggestion.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRemainingAmount = new GUI.TextField();
        txtExpirationDate = new GUI.TextField();
        txtQuantity = new GUI.TextField();
        jLabel11 = new javax.swing.JLabel();
        txtReceiptID = new GUI.TextField();
        cbbVoucher = new ComboBox_Suggestion.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        txtCash = new GUI.TextField();
        lblQRVoucher = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 255));
        jLabel1.setText("Receipt");

        jLabel2.setText("Customer phone number:");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel3.setText("Drug name:");

        cbbDrugName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Thuốc ho", "Thuốc cảm", "Thuốc sổ mũi", " " }));

        btnPay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pay.png"))); // NOI18N
        btnPay.setText("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantity:");

        tblReceipt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Drug name", "Quantity", "Price", "Batch ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReceipt.setFocusable(false);
        tblReceipt.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblReceipt.setRowHeight(25);
        tblReceipt.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblReceipt.setShowVerticalLines(false);
        tblReceipt.getTableHeader().setReorderingAllowed(false);
        tblReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReceiptMouseClicked(evt);
            }
        });
        tblReceipt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblReceiptKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblReceipt);

        cbbCustomerPhoneNumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "0988585568", "0916175566", "0987898882", "0912040325", "0989965118", "0904352749", "0902210733", "0934447788", "0977891369", "0983266986", "0912177345" }));
        cbbCustomerPhoneNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbCustomerPhoneNumberMouseClicked(evt);
            }
        });
        cbbCustomerPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCustomerPhoneNumberActionPerformed(evt);
            }
        });

        jLabel5.setText("Name:");

        jLabel7.setText("Total:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotal.setText("0");

        jLabel6.setText("PN:");

        lblName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName.setText(" ");

        lblPN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPN.setText(" ");

        jLabel9.setText("Batch ID:");

        cbbBatchID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", " " }));

        jLabel8.setText("Remaining Amount:");

        jLabel10.setText("Expiration date:");

        txtRemainingAmount.setEditable(false);
        txtRemainingAmount.setBackground(new java.awt.Color(255, 255, 255));

        txtExpirationDate.setEditable(false);
        txtExpirationDate.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Receipt ID:");

        txtReceiptID.setText("HD");

        cbbVoucher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));
        cbbVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVoucherActionPerformed(evt);
            }
        });

        jLabel12.setText("Cash:");

        jLabel13.setText("Balance:");

        lblBalance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBalance.setText("0");

        txtCash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashKeyReleased(evt);
            }
        });

        lblQRVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/camera.png"))); // NOI18N
        lblQRVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQRVoucherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbbBatchID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtReceiptID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbDrugName, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtRemainingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbCustomerPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGap(4, 4, 4)
                                .addComponent(lblQRVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(cbbVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblBalance))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblTotal))) .addGap(0, 41, Short.MAX_VALUE)))
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cbbCustomerPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAdd)
                                    .addComponent(jLabel3)
                                    .addComponent(cbbDrugName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtReceiptID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemove)
                            .addComponent(cbbBatchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(txtRemainingAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtExpirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblName))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblPN))
                        .addGap(217, 217, 217)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblTotal))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblBalance))
                        .addGap(18, 18, 18)                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQRVoucher))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPay)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    List<Object> listData = new ArrayList<>();

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (cbbDrugName.getSelectedIndex() == 0) {
            Mgsbox.alert(this, "You must select DrugName!");
        } else if (Double.valueOf(txtQuantity.getText()) > Double.valueOf(txtRemainingAmount.getText())) {
            Mgsbox.alert(this, "Hết thuốc");
        } else if (cbbBatchID.getSelectedIndex() == 0) {
            Mgsbox.alert(this, "You must select a batch ID of medicine!");
        } else if (cbbCustomerPhoneNumber.getSelectedIndex() == 0) {
            Mgsbox.alert(this, "You must select CustomerPhoneNumber!");
        } else {
            String batchID = (String) cbbBatchID.getSelectedItem();
            String drugName = cbbDrugName.getSelectedItem().toString();
            Medicine medicine = mdao.selectByA(drugName, batchID);
            String price = String.valueOf(medicine.getMdcPriceSale());
            String data[] = {cbbDrugName.getSelectedItem().toString(), txtQuantity.getText(), price, cbbBatchID.getSelectedItem().toString()};
            DefaultTableModel dtm = (DefaultTableModel) tblReceipt.getModel();
            dtm.addRow(data);
            double total = medicine.getMdcPriceSale() * Double.valueOf(txtQuantity.getText()) + Double.valueOf(lblTotal.getText());
            lblTotal.setText(String.valueOf(total));
            txtQuantity.setText("");
            txtExpirationDate.setText("");
            txtRemainingAmount.setText("");
            cbbDrugName.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbbCustomerPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomerPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCustomerPhoneNumberActionPerformed

    private void cbbCustomerPhoneNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbCustomerPhoneNumberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCustomerPhoneNumberMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        String quantity = tblReceipt.getValueAt(index, 1).toString();
        String price = tblReceipt.getValueAt(index, 2).toString();
        double total = Double.valueOf(lblTotal.getText()) - Double.valueOf(price) * Double.valueOf(quantity);
        lblTotal.setText(String.valueOf(total));
        DefaultTableModel dtm = (DefaultTableModel) tblReceipt.getModel();
        dtm.removeRow(index);
    }//GEN-LAST:event_btnRemoveActionPerformed

    int index = 0;
    int flag = 0;
    private KeyAdapter KA = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("I'm in");
                double total = 0;
                for (int i = 0; i < tblReceipt.getRowCount(); i++) {
                    total += Double.parseDouble(tblReceipt.getValueAt(i, 1).toString()) * Double.parseDouble(tblReceipt.getValueAt(i, 2).toString());
                }
                lblTotal.setText(String.valueOf(total));
            }
        }
    };
    private void tblReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReceiptMouseClicked
        // TODO add your handling code here:
        index = tblReceipt.getSelectedRow();
        if (evt.getClickCount() == 2) {
            flag = 1;
            System.out.println(flag);
        }
    }//GEN-LAST:event_tblReceiptMouseClicked
    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (check()) {
            String maHD = txtReceiptID.getText();
            String maNB = Image_Auth.USER.getEpeID();
            String maV = "";
            if (cbbVoucher.getSelectedIndex() == 0) {
                maV = "SPK0";
            } else {
                maV = cbbVoucher.getSelectedItem().toString();
            }
            Date tgMua = DateSupport.now();
            String tienTruocV = lblTotal.getText();
            wdao.updateHoaDon(maHD, maKH, maNB, maV, tgMua, tienTruocV);

            for (int i = 0; i < tblReceipt.getRowCount(); i++) {
                String maLo = tblReceipt.getValueAt(i, 3).toString();
                int sl = Integer.valueOf(tblReceipt.getValueAt(i, 1).toString());
                wdao.updateSL(maLo, sl);

                String drungName = tblReceipt.getValueAt(i, 0).toString();
                String price = tblReceipt.getValueAt(i, 2).toString();
                wdao.updateHoaDonChiTiet(maHD, drungName, sl, price);
            }
            if (Mgsbox.comfirm(this, "Do you want to print receipt ?")) {
                bHeight = Double.valueOf(tblReceipt.getRowCount());
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new BillPrintable(), getPageFormat(pj));
                try {
                    pj.print();

                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            new PaymentSuccess().setVisible(true);
            clearForm();
            txtCash.setText("0");
            lblBalance.setText("0");

        }
    }//GEN-
 private void txtCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyReleased
     // TODO add your handling code here:
     if (evt.getKeyCode() == KeyEvent.VK_ENTER && Double.valueOf(txtCash.getText()) > Double.valueOf(lblTotal.getText())) {
         double balance = Double.valueOf(txtCash.getText()) - Double.valueOf(lblTotal.getText());
         lblBalance.setText(String.valueOf(balance));
     }
    }//GEN-LAST:event_txtCashKeyReleased

    private void tblReceiptKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblReceiptKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("I'm in");
            double total = 0;
            for (int i = 0; i < tblReceipt.getRowCount(); i++) {
                total += Double.parseDouble(tblReceipt.getValueAt(i, 1).toString()) * Double.parseDouble(tblReceipt.getValueAt(i, 2).toString());
            }
            lblTotal.setText(String.valueOf(total));
        } else {
            System.out.println("I'm not in");
        }
    }//GEN-LAST:event_tblReceiptKeyReleased
    private void cbbVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVoucherActionPerformed
    }//GEN-LAST:event_cbbVoucherActionPerformed

    private void lblQRVoucherMouseClicked(java.awt.event.MouseEvent evt) {
        new FrameCameraQRCodeVoucher().setVisible(true);
        Thread setImage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (TEXT_FROM_QRCODE_VOUCHER != null) {
                        System.out.println(TEXT_FROM_QRCODE_VOUCHER);
                        cbbVoucher.setSelectedItem(TEXT_FROM_QRCODE_VOUCHER);
                        // DO SOMETHING AT THERE
                        break;
                    }
                }
            }
        });
        setImage.start();
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRemove;
    private ComboBox_Suggestion.ComboBoxSuggestion cbbBatchID;
    private ComboBox_Suggestion.ComboBoxSuggestion cbbCustomerPhoneNumber;
    private ComboBox_Suggestion.ComboBoxSuggestion cbbDrugName;
    private ComboBox_Suggestion.ComboBoxSuggestion cbbVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPN;
    private javax.swing.JLabel lblQRVoucher;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblReceipt;
    private GUI.TextField txtCash;
    private GUI.TextField txtExpirationDate;
    private GUI.TextField txtQuantity;
    private GUI.TextField txtReceiptID;
    private GUI.TextField txtRemainingAmount;
}
