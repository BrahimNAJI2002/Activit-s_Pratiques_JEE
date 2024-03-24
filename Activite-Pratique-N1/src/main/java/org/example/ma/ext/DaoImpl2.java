package org.example.ma.ext;

import org.example.ma.dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version Capteurs");
        double temp = 6000;
        return temp;
    }
}

