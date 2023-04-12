package com;

import java.sql.*;
import org.springframework.stereotype.Component;
import com.DAO.Admin_Dao.AdminDao_jdbc;

@Component
public class DaoFactory {

    public enum TXN_STATUS { COMMIT, ROLLBACK };
    public boolean connectionActive;
    Connection dbconnection;

    public DaoFactory() {
        connectionActive = false;
        dbconnection = null;
    }

    public void getConnection() throws Exception
    {
        if (connectionActive)
            throw new Exception("connection is already active");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbconnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testing?characterEncoding=latin1&useConfigs=maxPerformance",
                    "root",
                    "ricky123");
            dbconnection.setAutoCommit(false);
            connectionActive = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public AdminDao_jdbc getAdminDao() throws Exception {
        if (connectionActive)
            return new AdminDao_jdbc(dbconnection);
        else
            throw new Exception("Connection is not active");
    }


    public void deactivateConnection( TXN_STATUS txn_status )
	{
		// Okay to keep deactivating an already deactivated connection
        connectionActive = false;
		if( dbconnection != null ){
			try{
				if( txn_status == TXN_STATUS.COMMIT )
					dbconnection.commit();
				else
					dbconnection.rollback();

				dbconnection.close();
				dbconnection = null;
			}
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
}