/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Voucher;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class VoucherDAO extends ModelDataAccessObject<Voucher, String> {

    private final String INSERT_SQL = "INSERT INTO Voucher (MaVoucher, MaThuoc, NguoiThemVoucher, NgayBatDau, NgayKetThuc, GiaHoaDonApDung, TiLeGiam) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Voucher SET MaThuoc=?, NguoiThemVoucher=?, NgayBatDau=?, NgayKetThuc=?, GiaHoaDonApDung=?, TiLeGiam=? WHERE MaVoucher=?";
    private final String DELETE_SQL = "DELETE FROM Voucher WHERE MaVoucher=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Voucher";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Voucher WHERE MaVoucher=?";

    @Override
    public void insert(Voucher entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getVcID(),
                    entity.getVcMedicineID(),
                    entity.getVcEmployeeAddVoucher(),
                    entity.getVcStartDate(),
                    entity.getVcEndDate(),
                    entity.getVcTotalBillCanAdd(),
                    entity.getVcPriceDiscount());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Voucher entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getVcMedicineID(),
                    entity.getVcEmployeeAddVoucher(),
                    entity.getVcStartDate(),
                    entity.getVcEndDate(),
                    entity.getVcTotalBillCanAdd(),
                    entity.getVcPriceDiscount(),
                    entity.getVcID());
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
    public List<Voucher> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Voucher selectByID(String ID) {
        List<Voucher> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Voucher> selectBySQL(String sql, Object... args) {
        List<Voucher> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                Voucher entity = new Voucher();
                entity.setVcID(rs.getString("MaVoucher"));
                entity.setVcMedicineID(rs.getString("MaThuoc"));
                entity.setVcEmployeeAddVoucher(rs.getString("NguoiThemVoucher"));
                entity.setVcStartDate(rs.getDate("NgayBatDau"));
                entity.setVcEndDate(rs.getDate("NgayKetThuc"));
                entity.setVcTotalBillCanAdd(rs.getInt("GiaHoaDonApDung"));
                entity.setVcPriceDiscount(rs.getInt("TiLeGiam"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
