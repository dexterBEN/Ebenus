package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Commande;

import java.util.List;

public class CommandeDao implements IDao<Commande> {

    @Override
    public List<Commande> findAll() {
        return null;
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
}
