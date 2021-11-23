/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Invoice;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class InvoiceDAO extends ModelDataAccessObject<Invoice, String> {

    private final String INSERT_SQL = "INSERT INTO HoaDon(MaHoaDon,MaKhachHang,MaNguoiBan,MaVoucher,TenHoaDon,ThoiGiaMua,TienTruocVoucher,TienVoucher,TienSauVoucher)"
            + "VALUES(?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE HoaDon SET MaKhachHang=?, MaNguoiBan=? MaVoucher=?,TenHoaDon=?,ThoiGiaMua=?,TienTruocVoucher=?,TienVoucher=?,TienSauVoucher=?"
            + "WHERE MaHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon=?";

    @Override
    public void insert(Invoice entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getIvID(),
                    entity.getIvCumtomerID(),
                    entity.getIvEmployeeSale(),
                    entity.getIvVoucherID(),
                    entity.getIvName(),
                    entity.getIvTimeBuy(),
                    entity.getIvTotalPricesBefore(),
                    entity.getIvPriceDiscount(),
                    entity.getIvTotalPricesBefore());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Invoice entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getIvVoucherID(),
                    entity.getIvName(),
                    entity.getIvTimeBuy(),
                    entity.getIvTotalPricesBefore(),
                    entity.getIvPriceDiscount(),
                    entity.getIvTotalPricesBefore(),
                    entity.getIvID(),
                    entity.getIvCumtomerID(),
                    entity.getIvEmployeeSale());
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
    public List<Invoice> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Invoice selectByID(String ID) {
        List<Invoice> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Invoice> selectBySQL(String sql, Object... args) {
        List<Invoice> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);

            while (rs.next()) {
                Invoice entity = new Invoice();
                entity.setIvID(rs.getString("MaHoaDon"));
                entity.setIvCumtomerID(rs.getString("MaKhachHang"));
                entity.setIvEmployeeSale(rs.getString("MaNguoiBan"));
                entity.setIvVoucherID(rs.getString("MaVoucher"));
                entity.setIvTimeBuy(rs.getDate("ThoiGianMua"));
                entity.setIvTotalPricesBefore(Double.parseDouble(rs.getString("TienTruocVoucher")));
                entity.setIvPriceDiscount(Double.parseDouble(rs.getString("TienVoucher")));
                entity.setIvToltalPricesAfter(Double.parseDouble(rs.getString("TienSauVoucher")));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
