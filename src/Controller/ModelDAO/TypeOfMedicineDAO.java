/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.TypeOfMedicine;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class TypeOfMedicineDAO extends ModelDataAccessObject<TypeOfMedicine, String> {

    private final String INSERT_SQL = "INSERT INTO LoaiThuoc (MaLoaiThuoc, TenLoaiThuoc, ThuocKeDon, DoiTuongSuDung, GhiChu) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE LoaiThuoc SET TenLoaiThuoc=?, ThuocKeDon=?, DoiTuongSuDung=?, GhiChu=? WHERE MaLoaiThuoc=?";
    private final String DELETE_SQL = "DELETE FROM LoaiThuoc WHERE MaLoaiThuoc=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM LoaiThuoc";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM LoaiThuoc WHERE MaLoaiThuoc=?";

    @Override
    public void insert(TypeOfMedicine entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getTomID(),
                    entity.getTomName(),
                    entity.isTomIsPrecription(),
                    entity.getTomUserObject(),
                    entity.getTomNote());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(TypeOfMedicine entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getTomName(),
                    entity.isTomIsPrecription(),
                    entity.getTomUserObject(),
                    entity.getTomNote(),
                    entity.getTomID());
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
    public List<TypeOfMedicine> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public TypeOfMedicine selectByID(String ID) {
        List<TypeOfMedicine> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TypeOfMedicine> selectBySQL(String sql, Object... args) {
        List<TypeOfMedicine> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                TypeOfMedicine entity = new TypeOfMedicine();
                entity.setTomID(rs.getString("MaLoaiThuoc"));
                entity.setTomName(rs.getString("TenLoaiThuoc"));
                entity.setTomIsPrecription(rs.getBoolean("ThuocKeDon"));
                entity.setTomUserObject(rs.getString("DoiTuongSuDung"));
                entity.setTomNote(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
