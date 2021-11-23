/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Customer;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class CustomerDAO extends ModelDataAccessObject<Customer, String> {

    private final String INSERT_SQL = "INSERT INTO KhachHang(MaKhachHang,HovaTen,SoDienThoai,NgayBatDauMua,NgayCuoiCungMua,SoLanMua,TongSoTienDaMua)"
            + "VALUES(?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE KhachHang SET HovaTen=?,SoDienThoai=? WHERE MaKhachHang=?";
    private final String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKhachHang=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE MaKhachHang=?";

    @Override
    public void insert(Customer entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getCtmID(), 
                    entity.getCtmName(), 
                    entity.getCtmNumberPhone(),
                    entity.getCtmStartDateBuy(),
                    entity.getCtmLastDateBuy(),
                    entity.getCtmTimesOfBuy(),
                    entity.getCtmTotalMoneyBought());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Customer entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getCtmName(),
                    entity.getCtmNumberPhone(),
                    entity.getCtmID());
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
    public List<Customer> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Customer selectByID(String ID) {
        List<Customer> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Customer> selectBySQL(String sql, Object... args) {
        List<Customer> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);

            while (rs.next()) {
                Customer entity = new Customer();
                entity.setCtmID(rs.getString("MaKhachHang"));
                entity.setCtmName(rs.getString("HovaTen"));
                entity.setCtmNumberPhone(rs.getString("SoDienThoai"));
                entity.setCtmStartDateBuy(rs.getDate("NgayBatDauMua"));
                entity.setCtmLastDateBuy(rs.getDate("NgayCuoiCungMua"));
                entity.setCtmTimesOfBuy(Integer.parseInt(rs.getString("SoLanMua")));
                entity.setCtmTotalMoneyBought(Double.parseDouble(rs.getString("TongSoTienDaMua")));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Customer selectByPN(String pn) {
        String sql = "select * from KhachHang where SoDienThoai = ?";
        List<Customer> list = this.selectBySQL(sql, pn);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
