/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ModelDAO;

import Controller.Helper.Database;
import Model.Employee;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class EmployeeDAO extends ModelDataAccessObject<Employee, String> {

    private final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien,HovaTen,GioiTinh,NgaySinh,DiaChi,CCCD,SoDienThoai,Email,Anh,VaiTro,TenTaiKhoan,MatKhau,NgayTaoTaiKhoan)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE NhanVien SET HovaTen=?,GioiTinh=?,NgaySinh=?,DiaChi=?,CCCD=?,SoDienThoai=?,Email=?,Anh=?,VaiTro=?,TenTaiKhoan=?,MatKhau=? WHERE MaNhanVien=?";
    private final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien=?";
    private final String SELECT_BY_USERNAME = "SELECT * FROM NhanVien WHERE TenTaiKhoan = ? ";

    @Override
    public void insert(Employee entity) {
        try {
            Controller.Helper.Database.executeUpdate(INSERT_SQL,
                    entity.getEpeID(),
                    entity.getEpeName(),
                    entity.isEpeIsGender(),
                    entity.getEpeDayOfBirth(),
                    entity.getEpeAddress(),
                    entity.getEpeCCCD(),
                    entity.getEpeNumberPhone(),
                    entity.getEpeEmail(),
                    entity.getEpeImage(),
                    entity.isEpeIsRole(),
                    entity.getEpeUserName(),
                    entity.getEpePassword(),
                    entity.getEpeAccountCreationDate());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Employee entity) {
        try {
            Controller.Helper.Database.executeUpdate(UPDATE_SQL,
                    entity.getEpeName(),
                    entity.isEpeIsGender(),
                    entity.getEpeDayOfBirth(),
                    entity.getEpeAddress(),
                    entity.getEpeCCCD(),
                    entity.getEpeNumberPhone(),
                    entity.getEpeEmail(),
                    entity.getEpeImage(),
                    entity.isEpeIsRole(),
                    entity.getEpeUserName(),
                    entity.getEpePassword(),
                    entity.getEpeID());
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
    public List<Employee> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public Employee selectByID(String ID) {
        List<Employee> list = this.selectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    public Employee selectByUsername(String username) {
        return selectBySQL(SELECT_BY_USERNAME, username).get(0);
    }

    @Override
    protected List<Employee> selectBySQL(String sql, Object... args) {
        List<Employee> list = new ArrayList<>();
        try {
            ResultSet rs = Database.executeQuery(sql, args);

            while (rs.next()) {
                Employee entity = new Employee();
                entity.setEpeID(rs.getString("MaNhanVien"));
                entity.setEpeName(rs.getString("HovaTen"));
                entity.setEpeIsGender(rs.getBoolean("GioiTinh"));
                entity.setEpeDayOfBirth(rs.getDate("NgaySinh"));
                entity.setEpeAddress(rs.getString("DiaChi"));
                entity.setEpeCCCD(rs.getString("CCCD"));
                entity.setEpeNumberPhone(rs.getString("SoDienThoai"));
                entity.setEpeEmail(rs.getString("Email"));
                entity.setEpeImage(rs.getString("Anh"));
                entity.setEpeIsRole(rs.getBoolean("VaiTro"));
                entity.setEpeUserName(rs.getString("TenTaiKhoan"));
                entity.setEpePassword(rs.getString("MatKhau"));
                entity.setEpeAccountCreationDate(rs.getDate("NgayTaoTaiKhoan"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
