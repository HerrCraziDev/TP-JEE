package co.chen.service;

import co.chen.bean.Admin;
import co.chen.dao.AdminDAO;

public class AdminManager {
    public Admin validateAdminAccess(AdminDAO dao, String identifier, String password) {

        Admin admin = dao.getAdminById(identifier);

        if ( admin != null && admin.getPassword().equals(password) ) {
            return admin;
        }

        return null;
    }
}
