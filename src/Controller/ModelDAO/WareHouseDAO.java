/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import static Controller.Helper.Database.executeQuery;
import static Controller.Helper.Database.executeUpdate;
import Model.WareHouse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class WareHouseDAO extends ModelDataAccessObject<WareHouse, String> {

    private final String INSERT_SQL = "INSERT INTO Kho (MaLoThuoc, MaNhanVienNhap, NgayNhap, NhaPhanPhoi, NgaySanXuat, HanSuDung, SoLuong, SoLuongTonKho, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Kho SET MaNhanVienNhap=?, NgayNhap=?, NhaPhanPhoi=?, NgaySanXuat=?, HanSuDung=?, SoLuong=?, SoLuongTonKho=?, GhiChu=? WHERE MaLoThuoc=?";
    private final String DELETE_SQL = "DELETE FROM Kho WHERE MaLoThuoc=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Kho";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Kho WHERE MaLoThuoc=?";
    
    @Override
    public void insert(WareHouse entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getWhBatchID(),
                    entity.getWhEmployeeID(),
                    entity.getWhImportDay(),
                    entity.getWhDistributor(),
                    entity.getWhDateOfManufacture(),
                    entity.getWhExpiryDate(),
                    entity.getWhQuantity(),
                    entity.getWhRemainingAmout(),
                    entity.getWhNote());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(WareHouse entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getWhEmployeeID(),
                    entity.getWhImportDay(),
                    entity.getWhDistributor(),
                    entity.getWhDateOfManufacture(),
                    entity.getWhExpiryDate(),
                    entity.getWhQuantity(),
                    entity.getWhRemainingAmout(),
                    entity.getWhNote(),
                    entity.getWhBatchID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String ID) {
        try {
            Controller.Helper.Database.executeUpdate(DELETE_SQL, ID);
        } catch (Exception e) {
        }
    }

    @Override
    public List<WareHouse> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public WareHouse selectByID(String ID) {
        List<WareHouse> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<WareHouse> selectBySQL(String sql, Object... args) {
        List<WareHouse> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                WareHouse entity = new WareHouse();
                entity.setWhBatchID(rs.getString("MaLoThuoc"));
                entity.setWhEmployeeID(rs.getString("MaNhanVienNhap"));
                entity.setWhImportDay(rs.getDate("NgayNhap"));
                entity.setWhDistributor(rs.getString("NhaPhanPhoi"));
                entity.setWhDateOfManufacture(rs.getDate("NgaySanXuat"));
                entity.setWhExpiryDate(rs.getDate("HanSuDung"));
                entity.setWhQuantity(rs.getInt("SoLuong"));
                entity.setWhRemainingAmout(rs.getInt("SoLuongTonKho"));
                entity.setWhNote(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDuplicateWarehouseID(String ID) {
        return selectByID(ID) != null;
    }

    public void updateSL(String a, int b) {
        String sql = "{call sp_Kho_HoaDon (?,?)}";
        executeUpdate(sql, a, b);
    }
    
    public void updateHoaDon(String a, String b, String c, String d, Date e, String f){
        String sql = "{call sp_HoaDon (?,?,?,?,?,?)}";
        executeUpdate(sql, a, b, c, d, e, f);
    }
    
    public void updateHoaDonChiTiet(String a, String b, int c, String d){
        String sql = "{call sp_HoaDonChiTiet (?,?,?,?)}";
        executeUpdate(sql, a, b, c, d);
    }
}
