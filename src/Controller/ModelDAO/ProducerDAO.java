/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Producer;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ProducerDAO extends ModelDataAccessObject<Producer, String> {

    private final String INSERT_SQL = "INSERT INTO NhaSanXuat (MaNhaSanXuat, TenCongTy, NgayThanhLap, Email, Hotline) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE NhaSanXuat SET TenCongTy=?, NgayThanhLap=?, Email=?, Hotline=? WHERE MaNhaSanXuat=?";
    private final String DELETE_SQL = "DELETE FROM NhaSanXuat WHERE MaNhaSanXuat=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM NhaSanXuat";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM NhaSanXuat WHERE MaNhaSanXuat=?";

    @Override
    public void insert(Producer entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getPdcID(),
                    entity.getPdcCompanyName(),
                    entity.getPdcFoundingDate(),
                    entity.getPdcEmail(),
                    entity.getPdcHotline());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Producer entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getPdcCompanyName(),
                    entity.getPdcFoundingDate(),
                    entity.getPdcEmail(),
                    entity.getPdcHotline(),
                    entity.getPdcID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String ID) {
        try {
            Controller.Helper.Database.executeQuery(DELETE_SQL, ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Producer> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Producer selectByID(String ID) {
        List<Producer> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Producer> selectBySQL(String sql, Object... args) {
        List<Producer> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                Producer entity = new Producer();
                entity.setPdcID(rs.getString("MaNhaSanXuat"));
                entity.setPdcCompanyName(rs.getString("TenCongTy"));
                entity.setPdcFoundingDate(rs.getDate("NgayThanhLap"));
                entity.setPdcEmail(rs.getString("Email"));
                entity.setPdcHotline(rs.getString("Hotline"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
