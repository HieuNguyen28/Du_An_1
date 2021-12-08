/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.ModelDAO;

import static Controller.Helper.Database.executeQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laxus
 */
public class IdDAO {
    private final String receiptID = "select count(MaHoaDon) from HoaDon";
    private final String employeeID = "select count(MaNhanVien) from NhanVien";
    private final String batchID = "select count(MaLoThuoc) from Kho";
    private final String medicineID = "select count(MaThuoc) from Thuoc";
    private final String medicineTypeID = "select count(MaLoaiThuoc) from LoaiThuoc";
    private final String customerID = "select count(MaKhachHang) from KhachHang";
    private final String voucherID = "select count(MaVoucher) from Voucher";
    private final String producerID = "select count(MaNhaSanXuat) from NhaSanXuat";
    
    public List<Object> ID(String a){
        List<Object> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = executeQuery(a);
                while (rs.next()) {                    
                    int id = rs.getInt(1);
                    list.add(id);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Object> receiptID(){
        return ID(receiptID);
    }
    
    public List<Object> employeeID(){
        return ID(employeeID);
    }
    
    public List<Object> batchID(){
        return ID(batchID);
    }
    
    public List<Object> medicineID(){
        return ID(medicineID);
    }
    
    public List<Object> medicineTypeID(){
        return ID(medicineTypeID);
    }
    
    public List<Object> customerID(){
        return ID(customerID);
    }
    
    public List<Object> voucherID(){
        return ID(voucherID);
    }
    
    public List<Object> producerID(){
        return ID(producerID);
    }
}
