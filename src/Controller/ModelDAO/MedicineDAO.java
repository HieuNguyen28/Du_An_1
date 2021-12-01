/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Customer;
import Model.Medicine;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class MedicineDAO extends ModelDataAccessObject<Medicine, String> {

    private final String INSERT_SQL = "INSERT INTO Thuoc(MaThuoc,MaLoaiThuoc,MaLoThuoc,MaNhaSanXuat,TenThuoc,ThanhPhan,HamLuong,DonViTinh,HuongDanSuDung,GiaNiemYet,HinhAnh,LaiSuat,GiaGoc)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE Thuoc SET TenThuoc=?,ThanhPhan=?,HamLuong=?,DonViTinh=?,HuongDanSuDung=?,GiaNiemYet=?,HinhAnh=?,LaiSuat=?,GiaGoc=? WHERE MaThuoc=?";
    private final String DELETE_SQL = "DELETE FROM Thuoc WHERE MaThuoc=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Thuoc";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Thuoc WHERE MaThuoc=?";
    private final String SELECT_BY_TYPE_OF_MEDICINE_SQL = "SELECT * FROM Thuoc WHERE MaLoaiThuoc=?";
    private final String SELECT_BY_BATCH_ID = "SELECT * FROM Thuoc WHERE MaLoThuoc=?";

    @Override
    public void insert(Medicine entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getMdcID(),
                    entity.getMdcTypeOfMedicineID(),
                    entity.getMdcBatchID(),
                    entity.getMdcProducerID(),
                    entity.getMdcName(),
                    entity.getMdcIngredient(),
                    entity.getMdcConcentration(),
                    entity.getMdcUnit(),
                    entity.getMdcUserManual(),
                    entity.getMdcPriceSale(),
                    entity.getMdcImage(),
                    entity.getMdcRate(),
                    entity.getMdcPriceImport());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Medicine entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getMdcName(),
                    entity.getMdcIngredient(),
                    entity.getMdcConcentration();
                    entity.getMdcUnit(),
                    entity.getMdcUserManual(),
                    entity.getMdcPriceSale(),
                    entity.getMdcImage(),
                    entity.getMdcRate(),
                    entity.getMdcPriceImport(),
                    entity.getMdcID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String ID) {
        try {
            Controller.Helper.Database.executeUpdate(DELETE_SQL, ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Medicine> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Medicine selectByID(String ID) {
        List<Medicine> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Medicine> selectBySQL(String sql, Object... args) {
        List<Medicine> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                Medicine entity = new Medicine();
                entity.setMdcID(rs.getString("MaThuoc"));
                entity.setMdcTypeOfMedicineID(rs.getString("MaLoaiThuoc"));
                entity.setMdcBatchID(rs.getString("MaLoThuoc"));
                entity.setMdcProducerID(rs.getString("MaNhaSanXuat"));
                entity.setMdcName(rs.getString("TenThuoc"));
                entity.setMdcIngredient(rs.getString("ThanhPhan"));
                entity.setMdcConcentration(rs.getString("HamLuong"));
                entity.setMdcUnit(rs.getString("DonViTinh"));
                entity.setMdcUserManual(rs.getString("HuongDanSuDung"));
                entity.setMdcPriceSale(Double.parseDouble(rs.getString("GiaNiemYet")));
                entity.setMdcImage(rs.getString("HinhAnh"));
                entity.setMdcRate(Float.parseFloat(rs.getString("LaiSuat")));
                entity.setMdcPriceImport(Double.parseDouble(rs.getString("GiaGoc")));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Medicine> selectByMedicineName(String mn) {
        String sql = "select * from Thuoc where TenThuoc = ?";
        return this.selectBySQL(sql, mn);
    }

    public Medicine selectByA(String... a) {
        String sql = "select * from Thuoc where TenThuoc = ? and MaLoThuoc = ?";
        List<Medicine> list = this.selectBySQL(sql, a);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Medicine selectByBatchID(String ID) {
        return selectBySQL(SELECT_BY_BATCH_ID, ID).get(0);
    }

    public List<Medicine> selectByTypeOfMedicineID(String ID) {
        List<Medicine> list = this.selectBySQL(SELECT_BY_TYPE_OF_MEDICINE_SQL, ID);
        return list.isEmpty() ? null : list;
    }
}
