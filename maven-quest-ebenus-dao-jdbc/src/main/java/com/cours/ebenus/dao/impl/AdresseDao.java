package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Adresse;
import com.cours.ebenus.dao.entities.ArticleCommande;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.DaoUtils.genericQuery;

public class AdresseDao implements IDao<Adresse> {

    Connection connection = null;

    public AdresseDao() {
        try {
            connection = DriverManagerSingleton.getConnectionInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Adresse> findAll() {
        return null;
    }

    @Override
    public Adresse findById(int id) {
        return null;
    }

    @Override
    public List<Adresse> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public Adresse create(Adresse adresse) {
        return null;
    }

    @Override
    public Adresse update(Adresse adresse) {
        return null;
    }

    @Override
    public boolean delete(Adresse adresse) {
        return false;
    }

    @Override
    public List<Adresse> sendQuery(String query, Adresse adresse) {
        List<Adresse> adresses = new ArrayList<>();
        for (Object object : genericQuery(query, adresse, connection)) {
            if (object instanceof Adresse) {
                adresses.add((Adresse) object);
            }
        }
        return adresses;
    }
}
