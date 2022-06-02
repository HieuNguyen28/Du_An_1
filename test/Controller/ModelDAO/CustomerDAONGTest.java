/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package Controller.ModelDAO;

import Model.Customer;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Vo Thi Cam Nhung
 */
public class CustomerDAONGTest {
    
    public CustomerDAONGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testInsert() {
        System.out.println("insert");
        Customer entity = null;
        CustomerDAO instance = new CustomerDAO();
        instance.insert(entity);

    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Customer entity = null;
        CustomerDAO instance = new CustomerDAO();
        instance.update(entity);

    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        String ID = "";
        CustomerDAO instance = new CustomerDAO();
        instance.delete(ID);

    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        CustomerDAO instance = new CustomerDAO();
        List expResult = null;
        List result = instance.selectAll();
        assertEquals(result, expResult);

    }

    @Test
    public void testSelectByID() {
        System.out.println("selectByID");
        String ID = "";
        CustomerDAO instance = new CustomerDAO();
        Customer expResult = null;
        Customer result = instance.selectByID(ID);
        assertEquals(result, expResult);

    }

    @Test
    public void testSelectBySQL() {
        System.out.println("selectBySQL");
        String sql = "";
        Object[] args = null;
        CustomerDAO instance = new CustomerDAO();
        List expResult = null;
        List result = instance.selectBySQL(sql, args);
        assertEquals(result, expResult);

    }

    @Test
    public void testSelectByPN() {
        System.out.println("selectByPN");
        String pn = "";
        CustomerDAO instance = new CustomerDAO();
        Customer expResult = null;
        Customer result = instance.selectByPN(pn);

    }
    
}
