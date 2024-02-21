package org.example.ma.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        /*
		Se connecter à la base de BD pour récupérer la température
		*/
        System.out.println("version base de données");
        double temp = Math.random()*40;
        return temp;
    }
}
