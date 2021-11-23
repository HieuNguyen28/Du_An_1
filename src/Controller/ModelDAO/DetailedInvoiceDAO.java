/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.DetailedInvoice;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DetailedInvoiceDAO extends ModelDataAccessObject<DetailedInvoice, String> {

    private final String INSERT_SQL = "INSERT INTO HoaDonChiTiet(MaHoaDon,MaThuoc,SoLuong,DonGia,GhiChu)"
            + "VALUES(?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE HoaDonChiTiet SET MaThuoc=? SoLuong=?, DonGia=?, GhiChu=?"
            + "WHERE MaHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM HoaDonChiTiet WHERE MaHoaDon=? and MaThuoc=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon=?";

    @Override
    public void insert(DetailedInvoice entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getDiInvoiceID(),
                    entity.getDiMedicineID(),
                    entity.getDiMedicineQuantity(),
                    entity.getDiPrices(),
                    entity.getDiNote());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DetailedInvoice entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getDiMedicineQuantity(),
                    entity.getDiPrices(),
                    entity.getDiNote(),
                    entity.getDiInvoiceID(),
                    entity.getDiMedicineID());
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
    public List<DetailedInvoice> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public DetailedInvoice selectByID(String ID) {
        List<DetailedInvoice> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DetailedInvoice> selectBySQL(String sql, Object... args) {
        List<DetailedInvoice> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);

            while (rs.next()) {
                DetailedInvoice entity = new DetailedInvoice();
                entity.setDiInvoiceID(rs.getString("MaHoaDon"));
                entity.setDiMedicineID(rs.getString("MaThuoc"));
                entity.setDiMedicineQuantity(Integer.parseInt(rs.getString("SoLuong")));
                entity.setDiPrices(Double.parseDouble(rs.getString("DonGia")));
                entity.setDiInvoiceID(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
