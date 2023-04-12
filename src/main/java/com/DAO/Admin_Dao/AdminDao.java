package com.DAO.Admin_Dao;

public interface AdminDao {
    public boolean isValidAdmin(Admin admin) throws Exception;
    public void addAdmin(Admin admin) throws Exception;
}
