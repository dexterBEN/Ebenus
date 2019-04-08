package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Commande;
import com.cours.ebenus.dao.entities.Product;
import com.cours.ebenus.utils.DaoUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.cours.ebenus.utils.Constants.firstIndice;
import static com.cours.ebenus.utils.DaoUtils.genericQuery;
import static com.cours.ebenus.utils.Queries.*;

public class CommandeDao implements IDao<Commande> {


    private Connection connection = null;

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
        String query = getCommandByIDQuery.concat(id + ";");
        List<Commande> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(firstIndice) : null;
    }

    @Override
    public List<Commande> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<Commande> commandeList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdCommande())) {
                query = getByIdCommandQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdUtilisateur())) {
                query = getByIdUserQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdAdresse())) {
                query = getByIdAdresseQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getDateCommande())) {
                query = getByCommmadeDateQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            }
        }
        return commandeList;
    }

    @Override
    public Commande create(Commande commande) {
        List<Commande> commandList = new ArrayList<>(sendQuery(createCommandeQuery, commande));
        return !commandList.isEmpty() ? commandList.get(firstIndice) : null;
    }

    @Override
    public Commande update(Commande commande) {
        List<Commande> commandeList = new ArrayList<>(sendQuery(updateCommandeQuery, commande));
        return !commandeList.isEmpty() ? commandeList.get(firstIndice) : null;
    }

    @Override
    public boolean delete(Commande commande) {
        List<Commande> commandeList = new ArrayList<>(sendQuery(deleteQuery, commande));
        return !commandeList.isEmpty() && commandeList.get(firstIndice) != null;

    }

    @Override
    public List<Commande> sendQuery(String query, Commande commande) {
        List<Commande> commandes = new ArrayList<>();
        for (Object object : genericQuery(query, commande, connection)) {
            if (object instanceof Commande) {
                commandes.add((Commande) object);
            }
        }
        return commandes;
    }
}
