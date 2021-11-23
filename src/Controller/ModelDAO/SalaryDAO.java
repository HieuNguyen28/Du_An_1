/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Salary;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class SalaryDAO extends ModelDataAccessObject<Salary, String> {

    private final String INSERT_SQL = "INSERT INTO Luong (STT, MaNhanVien, LuongCoBan, HoaHong, ThuongDoanhSo, NgayNghi) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Luong SET MaNhanVien=?, LuongCoBan=?, HoaHong=?, ThuongDoanhSo=?, NgayNghi=? WHERE STT=?";
    private final String DELETE_SQL = "DELETE FROM Luong WHERE STT=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Luong";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Luong WHERE STT=?";

    @Override
    public void insert(Salary entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getSlrEmployeeID(),
                    entity.getSlrBasicSalary(),
                    entity.getSlrCommission(),
                    entity.getSlrSalesBonus(),
                    entity.getSlrDaysOffOfMonth());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Salary entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getSlrEmployeeID(),
                    entity.getSlrBasicSalary(),
                    entity.getSlrCommission(),
                    entity.getSlrSalesBonus(),
                    entity.getSlrDaysOffOfMonth());
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
    public List<Salary> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Salary selectByID(String ID) {
        List<Salary> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Salary> selectBySQL(String sql, Object... args) {
        List<Salary> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);
            while (rs.next()) {
                Salary entity = new Salary();
                entity.setSlrEmployeeID(rs.getString("MaNhanVien"));
                entity.setSlrBasicSalary(rs.getInt("LuongCoBan"));
                entity.setSlrCommission(rs.getInt("ThuocKeDon"));
                entity.setSlrSalesBonus(rs.getInt("DoiTuongSuDung"));
                entity.setSlrDaysOffOfMonth(rs.getInt("NgayKetThuc"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
