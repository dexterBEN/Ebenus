/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cours.ebenus.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.utils.Constants;
import com.mysql.jdbc.Statement;

import static com.cours.ebenus.utils.Constants.*;
import static com.cours.ebenus.utils.UserUtils.*;
import static com.cours.ebenus.utils.UserUtils.UserLib.*;

/**
 * @author ElHadji
 */
public class UtilisateurDao /* extends AbstractDao<Utilisateur> */ implements IUtilisateurDao {

    public static final Log log = LogFactory.getLog(UtilisateurDao.class);

    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    final RoleDao roleDao = new RoleDao();

    public UtilisateurDao() {
        // super(Utilisateur.class);
        try {
            conn = DriverManagerSingleton.getConnectionInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {

        List<Utilisateur> users = new ArrayList<>();

        try {
            statement = conn.prepareStatement(getAllUserQuery);
            // SQL request for table user
            ResultSet rs = statement.executeQuery();

            users.addAll(setUsers(rs));
            for (Utilisateur user : users) {
                System.out.println(user.toString());
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        Utilisateur user = null;
        try {
            statement = conn.prepareStatement(getUserByIDQuery + idUtilisateur);

            ResultSet rs = statement.executeQuery();
            user = setUsers(rs).get(0);
            System.out.print("id: " + user.getIdUtilisateur() + "\n civilité: " + user.getCivilite() + "\n prénom: "
                    + user.getPrenom());

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        List<Utilisateur> users = new ArrayList<Utilisateur>();

        try {
            statement = conn.prepareStatement(getUserByFirstNameQuery + prenom + cote);

            ResultSet rs = statement.executeQuery();

            users.addAll(setUsers(rs));

            for (Utilisateur user : users) {
                System.out.println(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        List<Utilisateur> users = new ArrayList<Utilisateur>();

        try {
            statement = conn.prepareStatement(getUserByNameQuery + nom + cote);

            ResultSet rs = statement.executeQuery();

            users.addAll(setUsers(rs));

            for (Utilisateur user : users) {
                System.out.println(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        List<Utilisateur> users = new ArrayList<Utilisateur>();

        try {
            statement = conn.prepareStatement(getUserIdentifaintQuery + identifiant + cote);

            ResultSet rs = statement.executeQuery();
            users.addAll(setUsers(rs));
            for (Utilisateur user : users) {
                System.out.println(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        ResultSet result = null;

        List<Utilisateur> users = new ArrayList<Utilisateur>();

        try {

            statement = conn.prepareStatement(getUserByIdRoleQuery);
            statement.setInt(1, idRole);

            result = statement.executeQuery();

            while (result.next()) {//todo (so complicate) just a simple query

                Utilisateur user = new Utilisateur(result.getInt("u.idUtilisateur"), result.getString("u.civilite"),
                        result.getString("u.prenom"), result.getString("u.nom"), result.getString("u.identifiant"),
                        result.getString("u.motPasse"), result.getDate("u.dateNaissance"),
                        result.getDate("u.dateCreation"), result.getDate("u.dateModification"),
                        result.getBoolean("u.actif"), result.getBoolean("u.marquerEffacer"), result.getInt("u.version"),
                        new Role(result.getInt("r.idRole"), result.getString("r.identifiant"),
                                result.getString("r.description"), result.getInt("r.version")));

                users.add(user);

            }

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            ConnectionHelper.closeSqlResources(statement, result);
        }

        return users;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        List<Utilisateur> users = new ArrayList<>();

        try {

            statement = conn.prepareStatement(
                    getUserByIdentifiantRoleQuery);
            statement.setString(1, identifiantRole);

            result = statement.executeQuery();

            while (result.next()) {//todo (so complicate) just a simple query

                Utilisateur user = new Utilisateur(result.getInt("u.idUtilisateur"), result.getString("u.civilite"),
                        result.getString("u.prenom"), result.getString("u.nom"), result.getString("u.identifiant"),
                        result.getString("u.motPasse"), result.getDate("u.dateNaissance"),
                        result.getDate("u.dateCreation"), result.getDate("u.dateModification"),
                        result.getBoolean("u.actif"), result.getBoolean("u.marquerEffacer"), result.getInt("u.version"),
                        new Role(result.getInt("r.idRole"), result.getString("r.identifiant"),
                                result.getString("r.description"), result.getInt("r.version")));

                users.add(user);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.closeSqlResources(statement, null);
        }
        return users;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {

        if (user != null) {
            try {
                int idRoleStandard = 3;
                int isErased = user.isMarquerEffacer() ? 1 : 0;
                int isActif = user.isActif() ? 1 : 0;
                Date currentTime = new Date(System.currentTimeMillis());
                if (user.getDateNaissance() == null) {
                    user.setDateNaissance(currentTime);
                }
                user.setDateCreation(currentTime);
                user.setDateModification(currentTime);

                statement = conn.prepareStatement(
                        createUserQuery
                        + idRoleStandard + coma + cote + user.getCivilite() + comaString + user.getPrenom()
                                + comaString + user.getNom() + comaString + user.getIdentifiant() + comaString + user.getMotPasse()
                                + cote +coma + isActif + coma + isErased + coma + user.getVersion() + ")" ,
                        Statement.RETURN_GENERATED_KEYS);


                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    int idUser = rs.getInt(1);
                    user.setIdUtilisateur(idUser);
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        if(user != null){
            String requete = updateUserQuery + ID_ROLE.getField() + equal + user.getRole().getIdRole() + coma +
                    CIVILITE.getField() + equal + cote
                    + user.getCivilite() + cote + coma  +  PRENOM.getField() + equal + cote + user.getPrenom() + cote
                    + coma + NOM.getField() + equal + cote + user.getNom() + cote + coma
                    +  IDENTIFIANT.getField() + equal + cote + user.getIdentifiant() + cote + coma
                    + MOT_PASSE.getField() + equal + cote + user.getMotPasse() + cote + coma + IS_ACTIF.getField() + equal
                    + parseBooleanToInteger(user.isActif()) + coma + IS_DELETED + equal
                    + parseBooleanToInteger(user.isMarquerEffacer()) + coma + VERSION.getField() + equal + user.getVersion()
                    + " WHERE" + ID.getField() + equal + user.getIdUtilisateur() + ";";
            try {
                statement = conn.prepareStatement(requete);

                if (statement.executeUpdate() > 0) {
                    return findUtilisateurByIdentifiant(user.getIdentifiant()).get(0);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private Integer parseBooleanToInteger(Boolean isActif) {
        return (isActif) ? 1 : 0;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {

        try {
            statement = conn.prepareStatement(
                    deleteUserQuery + user.getIdUtilisateur() + "");

            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la base
     * de données
     *
     * @param email    L'email de l'utilisateur
     * @param password Le password de l'utilisateur
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
        return null;
    }

    private ArrayList<Utilisateur> setUsers(ResultSet result) {
        ArrayList<Utilisateur> returnList = new ArrayList<>();
        if (result != null) {
            try {
                while (result.next()) {
                    String name = result.getString(UserUtils.UserLib.NOM.getField());
                    String firstName = result.getString(UserUtils.UserLib.PRENOM.getField());
                    String gender = result.getString(UserUtils.UserLib.CIVILITE.getField());
                    String mail = result.getString(UserUtils.UserLib.IDENTIFIANT.getField());
                    String password = result.getString(UserUtils.UserLib.MOT_PASSE.getField());
                    Date birthDate = result.getDate(UserUtils.UserLib.DATE_NAISSANCE.getField());
                    Date createDate = result.getDate(UserUtils.UserLib.DATE_CREATION.getField());
                    Date updateDate = result.getDate(UserUtils.UserLib.DATE_MODIFICATION.getField());
                    Boolean activityState = result.getBoolean(UserUtils.UserLib.IS_ACTIF.getField());
                    Boolean markAsErased = result.getBoolean(UserUtils.UserLib.IS_DELETED.getField());
                    int idUser = result.getInt(UserUtils.UserLib.ID.getField());
                    int version = result.getInt(UserUtils.UserLib.VERSION.getField());
                    int idRole = result.getInt(ID_ROLE.getField());
                    Role role = roleDao.findRoleById(idRole);//todo have to make query

                    Utilisateur user = new Utilisateur(idUser, gender, firstName, name, mail, password, birthDate,
                            createDate, updateDate, activityState, markAsErased, version, role);

                    returnList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
}