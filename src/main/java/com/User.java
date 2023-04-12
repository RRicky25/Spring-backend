package com;

import java.io.Console;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.Admin_Dao.Admin;
import com.DAO.Admin_Dao.AdminDao;

@RestController
public class User {

    private final DaoFactory daoFactory;
    public User(DaoFactory daoFactoryBean) {
        this.daoFactory = daoFactoryBean;
    }

    @RequestMapping("/")
    @ResponseBody
    public Login greet(@RequestBody Login logger) {
        Admin admin = logger.admin;
        String loggedAs = logger.user;

        if (loggedAs != "Admin") { System.out.println("Not handling this now"); }
        else {
            try{
                daoFactory.getConnection();
                AdminDao myAdminDao = daoFactory.getAdminDao();
                if (myAdminDao.isValidAdmin(admin))
                    System.out.println("Is a valid admin");
                else
                    System.out.println("Is not a valid admin");
                daoFactory.deactivateConnection( DaoFactory.TXN_STATUS.COMMIT );
            } catch (Exception ex) {
                daoFactory.deactivateConnection( DaoFactory.TXN_STATUS.ROLLBACK );
            }
        }
        
        return logger;
    }

}
