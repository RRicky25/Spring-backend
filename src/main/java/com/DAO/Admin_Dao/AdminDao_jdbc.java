package com.DAO.Admin_Dao;

import java.sql.*;

public class AdminDao_jdbc implements AdminDao {

    Connection dbConnection = null;

    public AdminDao_jdbc(Connection _conn) {
        dbConnection = _conn;
    }

    @Override
    public boolean isValidAdmin(Admin admin) throws Exception {
        String checkQuery;
        PreparedStatement stmt = null;
        int countOfSuchAdmin = 0;
        checkQuery = "SELECT COUNT(*) FROM Admin_login where userid=? and password=?";

        try {
            stmt = dbConnection.prepareStatement(checkQuery);
            stmt.setString(1, admin.username);
            stmt.setString(2, admin.password);
            ResultSet countSet = stmt.executeQuery();
            countSet.next();
            countOfSuchAdmin = countSet.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Could not count the admins using the database. So doing rollback");
        }
        stmt.close();
        return (countOfSuchAdmin >= 1);
    }

    @Override
    public void addAdmin(Admin admin) throws Exception {
        String insertQuery;
        PreparedStatement stmt = null;
        insertQuery = "insert into Admin_login values (?,?)";

        try {
            stmt = dbConnection.prepareStatement(insertQuery);
            System.out.println(admin.getUsername() + admin.getPassword());
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            int insertStatus = stmt.executeUpdate();
            System.out.println("insert status is " + insertStatus);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Could not insert the admin in the database. So doing rollback");
        }
        stmt.close();
    }

}