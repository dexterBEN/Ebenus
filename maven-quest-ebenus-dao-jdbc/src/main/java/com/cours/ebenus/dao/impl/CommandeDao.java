package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Commande;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.DaoUtils.genericQuery;
import static com.cours.ebenus.utils.Queries.getAllCommandQuery;

public class CommandeDao implements IDao<Commande> {


    Connection connection = null;

    public CommandeDao() {
        try {
            connection = DriverManagerSingleton.getConnectionInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commande> findAll() {
        return sendQuery(getAllCommandQuery, null);
    }

    @Override
    public Commande findById(int id) {
        return null;
    }

    @Override
    public List<Commande> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public Commande create(Commande commande) {
        return null;
    }

    @Override
    public Commande update(Commande commande) {
        return null;
    }

    @Override
    public boolean delete(Commande commande) {
        return false;
    }

    @Override
    public List<Commande> sendQuery(String query, Commande commande){
        List<Commande> commandes = new ArrayList<>();
        for (Object object : genericQuery(query, commande, connection)) {
            if(object instanceof Commande){
                commandes.add((Commande) object);
            }
        }
        return commandes;
    }
}
