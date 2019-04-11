package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cour.ebenus.maven.ebenus.dao.IDao.IDao;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Commande;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.DaoUtils;
import com.cours.ebenus.maven.ebenus.utils.Queries;

public class CommandeDao implements IDao<Commande>{
	
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
        return sendQuery(Queries.getAllCommandQuery, null);
    }

    @Override
    public Commande findById(int id) {
        String query = Queries.getCommandByIDQuery.concat(id + ";");
        List<Commande> results = new ArrayList<>(sendQuery(query, null));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new Commande();
    }

    @Override
    public List<Commande> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<Commande> commandeList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdCommande())) {
                query = Queries.getByIdCommandQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdUtilisateur())) {
                query = Queries.getByIdUserQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getIdAdresse())) {
                query = Queries.getByIdAdresseQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.COMMANDE.getDateCommande())) {
                query = Queries.getByCommmadeDateQuery.concat(valueCriteria + ";");
                commandeList.addAll(sendQuery(query, null));
            }
        }
        return commandeList;
    }

    @Override
    public Commande create(Commande commande) {
        List<Commande> commandList = new ArrayList<>(sendQuery(Queries.createCommandeQuery, commande));
        return !commandList.isEmpty() ? commandList.get(Constants.firstIndice) : new Commande();
    }

    @Override
    public Commande update(Commande commande) {
        List<Commande> commandeList = new ArrayList<>(sendQuery(Queries.updateCommandeQuery, commande));
        return !commandeList.isEmpty() ? commandeList.get(Constants.firstIndice) : new Commande();
    }

    @Override
    public boolean delete(Commande commande) {
        List<Commande> commandeList = new ArrayList<>(sendQuery(Queries.deleteQuery, commande));
        return !commandeList.isEmpty() && commandeList.get(Constants.firstIndice) != null;

    }

    @Override
    public List<Commande> sendQuery(String query, Commande commande) {
        List<Commande> commandes = new ArrayList<>();
        for (Object object :  DaoUtils.genericQuery(query, commande, connection)) {
            if (object instanceof Commande) {
                commandes.add((Commande) object);
            }
        }
        return commandes;
    }

}
