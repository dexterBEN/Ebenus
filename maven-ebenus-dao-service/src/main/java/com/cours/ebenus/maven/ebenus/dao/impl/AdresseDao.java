package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cour.ebenus.maven.ebenus.dao.IDao.IDao;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Adresse;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.DaoUtils;
import com.cours.ebenus.maven.ebenus.utils.Queries;

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
        return sendQuery(Queries.getAdresseAllQuery,null);
    }

    @Override
    public Adresse findById(int id) {
        List<Adresse> adresseList = new ArrayList<>(sendQuery(Queries.getAdresseByIDQuery,null));
        return !adresseList.isEmpty() ? adresseList.get(Constants.firstIndice) : new Adresse();
    }

    @Override
    public List<Adresse> findByCriteria(String criteria, Object valueCriteria) {
        String query = null;
        List<Adresse> adresseListList = new ArrayList<>();
        if (valueCriteria != null && !criteria.isEmpty()) {
            if (criteria.equals(DaoUtils.Modules.ADRESSE.getIdUtilisateur())) {
                query = Queries.getGetByIdUtilisateurQuery.concat(valueCriteria + ";");
                adresseListList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ADRESSE.getCodePostal())) {
                query = Queries.getAdresseByCodePostalQuery.concat(valueCriteria + "%';");
                adresseListList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ADRESSE.getVille())) {
                query = Queries.getAdresseByVilleQuery.concat(valueCriteria + "%';");
                adresseListList.addAll(sendQuery(query, null));
            } else if (criteria.equals(DaoUtils.Modules.ADRESSE.getRue())) {
                query = Queries.getAdresseByRueQuery.concat(valueCriteria + "%';");
                adresseListList.addAll(sendQuery(query, null));
            }else if(criteria.equals(DaoUtils.Modules.ADRESSE.getPays())){
                query = Queries.getAdresseByPaysQuery.concat(valueCriteria + "%';");
                adresseListList.addAll(sendQuery(query, null));
            }
        }
        return adresseListList;
    }

    @Override
    public Adresse create(Adresse adresse) {
        List<Adresse> allAdress = findAll();
        boolean addressAlReadyExist = false;
        Iterator<Adresse> it = allAdress.iterator();
        while (!addressAlReadyExist && it.hasNext()){
            Adresse current = it.next();
            addressAlReadyExist = current.getStreet().equals(adresse.getStreet()) &&
                    current.getCodePostal().equals(adresse.getCodePostal()) &&
                    current.getTown().equals(adresse.getTown());
        }
        if(!addressAlReadyExist){
            List<Adresse> results = new ArrayList<>(sendQuery(Queries.createAdresseQuery, adresse));
            return !results.isEmpty()  ? results.get(Constants.firstIndice) : new Adresse();
        }else {
            return new Adresse();
        }
    }

    @Override
    public Adresse update(Adresse adresse) {
        List<Adresse> results = new ArrayList<>(sendQuery(Queries.updateAdresseQuery, adresse));
        return !results.isEmpty() ? results.get(Constants.firstIndice) : new Adresse();
    }

    @Override
    public boolean delete(Adresse adresse) {
        List<Adresse> results = new ArrayList<>(sendQuery(Queries.deleteAdresseQuery, adresse));
        return !results.isEmpty() && results.get(Constants.firstIndice) != null;
    }

    @Override
    public List<Adresse> sendQuery(String query, Adresse adresse) {
        List<Adresse> adresses = new ArrayList<>();
        for (Object object : DaoUtils.genericQuery(query, adresse, connection)) {
            if (object instanceof Adresse) {
                adresses.add((Adresse) object);
            }
        }
        return adresses;
    }

}
