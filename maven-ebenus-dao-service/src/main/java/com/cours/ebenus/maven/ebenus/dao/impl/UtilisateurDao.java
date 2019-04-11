package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.utils.Queries;
import com.cours.ebenus.maven.ebenus.utils.RoleUtils;
import com.cours.ebenus.maven.ebenus.utils.UserUtils;
import com.mysql.jdbc.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cours.ebenus.maven.ebenus.dao.connection.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao.IUtilisateurDao;
import com.cours.ebenus.maven.ebenus.dao.connection.DriverManagerSingleton;


public class UtilisateurDao implements IUtilisateurDao{
	
	private static final String queryEnd = "'";
	
	public static final Log log = LogFactory.getLog(UtilisateurDao.class);
    public static final String ROLE_IDENTIFIANT = "roleIdent";
    public static final String ROLE_ID = "idRole";

	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	// final RoleDao roleDao = new RoleDao();

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
        ResultSet rs = null;

        try {
            statement = conn.prepareStatement(UserUtils.getAllUserQuery);
            // SQL request for table user
            rs = statement.executeQuery();

            users.addAll(getUsers(rs));
            for (Utilisateur user : users) {
                System.out.println(user.toString());
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            ConnectionHelper.closeSqlResources(statement, rs);
        }

        return users;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		Utilisateur user = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(UserUtils.getUserByIDQuery + idUtilisateur);

            rs = statement.executeQuery();
            ArrayList<Utilisateur> results = getUsers(rs);
            user = !results.isEmpty() ? results.get(0) : null;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            ConnectionHelper.closeSqlResources(statement, rs);
        }

        return user;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		 List<Utilisateur> users = new ArrayList<Utilisateur>();
	        ResultSet rs = null;

	        try {
	            statement = conn.prepareStatement(UserUtils.getUserByFirstNameQuery + prenom + Constants.qote);

	            rs = statement.executeQuery();

	            users.addAll(getUsers(rs));

	            for (Utilisateur user : users) {
	                System.out.println(user);
	            }

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, rs);
	        }

	        return users;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		 List<Utilisateur> users = new ArrayList<Utilisateur>();
	        ResultSet rs = null;

	        try {
	            statement = conn.prepareStatement(UserUtils.getUserByNameQuery + nom + Constants.qote);

	            rs = statement.executeQuery();

	            users.addAll(getUsers(rs));

	            for (Utilisateur user : users) {
	                System.out.println(user);
	            }

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, rs);
	        }
	        return users;
	}

	@Override
	public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
		 List<Utilisateur> users = new ArrayList<Utilisateur>();
	        ResultSet rs = null;

	        try {
	            statement = conn.prepareStatement(UserUtils.getUserIdentifaintQuery + identifiant + Constants.qote);

	            rs = statement.executeQuery();
	            users.addAll(getUsers(rs));
	            for (Utilisateur user : users) {
	                System.out.println(user);
	            }

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionHelper.closeSqlResources(statement, rs);
	        }

	        return users;
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
		/*ResultSet result = null;
		List<Utilisateur> users = new ArrayList<Utilisateur>();

		try {

			statement = conn.prepareStatement(
					"SELECT * FROM utilisateur INNER JOIN role r ON r.idRole = u.idRole WHERE u.idRole = ? ");
			statement.setInt(1, idRole);

			result = statement.executeQuery();

			while (result.next()) {

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

		return users;*/
		return new ArrayList<>(getUsersByRoleCriteria(idRole));
	}

	@Override
	public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
		/*List<Utilisateur> users = new ArrayList<>();

		try {

			statement = conn.prepareStatement(
					"SELECT * FROM Utilisateur u INNER JOIN role r ON r.idRole = u.idRole WHERE r.identifiant = ?");
			statement.setString(1, identifiantRole);

			result = statement.executeQuery();

			while (result.next()) {

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
		return users;*/
		return new ArrayList<>(getUsersByRoleCriteria(identifiantRole));
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		try {
			int isErased = user.isMarquerEffacer() ? 1 : 0;
			int isActif = user.isActif() ? 1 : 0;
			Date currentTime = new Date(System.currentTimeMillis());
			String birthDateParsed = new SimpleDateFormat("yyyy-MM-dd").format(user.getDateNaissance());
			String test = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);

			List<Utilisateur> identifiantUser = findUtilisateurByIdentifiant(user.getIdentifiant());

			if (identifiantUser.isEmpty()) {
				// STEP 2: Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");
				System.out.print("The role i create: " + user.getRole());
				statement = conn.prepareStatement(
						"INSERT INTO `Utilisateur` (`idRole`, `civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `dateNaissance`, `dateCreation`, `dateModification`, `actif`, `marquerEffacer`, `version`) VALUES ("
								+ user.getRole().getIdRole() + ", '" + user.getCivilite() + "', '" + user.getPrenom()
								+ "', '" + user.getNom() + "', '" + user.getIdentifiant() + "', '" + user.getMotPasse()
								+ "', '" + birthDateParsed + "', '" + test + "', '" + test + "', " + isActif + ", "
								+ isErased + ", '" + user.getVersion() + "');",
						Statement.RETURN_GENERATED_KEYS);

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();

				if (rs.next()) {
					int idUser = rs.getInt(1);
					user.setIdUtilisateur(idUser);
					user.setDateCreation(currentTime);
					user.setDateModification(currentTime);
				}
			} else {
				user = identifiantUser.get(0);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		
		System.out.println("We are in methods update " + user.getIdUtilisateur());
		
		if (user != null) {
            user.setDateModification(new Date(System.currentTimeMillis()));
            String requete = UserUtils.updateUserQuery + UserUtils.UserLib.ID_ROLE.getField() + Constants.equal + user.getRole().getIdRole() + Constants.coma +
            		UserUtils.UserLib.CIVILITE.getField() + Constants.equal + Constants.qote
                    + user.getCivilite() + Constants.qote + Constants.coma + UserUtils.UserLib.PRENOM.getField() + Constants.equal + Constants.qote + user.getPrenom() + Constants.qote
                    + Constants.coma + UserUtils.UserLib.NOM.getField() + Constants.equal + Constants.qote + user.getNom() + Constants.qote + Constants.coma
                    + UserUtils.UserLib.IDENTIFIANT.getField() + Constants.equal + Constants.qote + user.getIdentifiant() + Constants.qote + Constants.coma
                    + UserUtils.UserLib.MOT_PASSE.getField() + Constants.equal + Constants.qote + user.getMotPasse() + Constants.qote + Constants.coma + UserUtils.UserLib.IS_ACTIF.getField() + Constants.equal
                    + parseBooleanToInteger(user.isActif()) + Constants.coma + UserUtils.UserLib.IS_DELETED.getField() + Constants.equal
                    + parseBooleanToInteger(user.isMarquerEffacer()) + Constants.coma + UserUtils.UserLib.VERSION.getField() + Constants.equal + user.getVersion()
                    + " WHERE " + UserUtils.UserLib.ID.getField() + Constants.equal + user.getIdUtilisateur() + ";";
            try {
                statement = conn.prepareStatement(requete);

                if (statement.executeUpdate() > 0) {
                    return findUtilisateurByIdentifiant(user.getIdentifiant()).get(0);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
					"DELETE FROM `utilisateur` WHERE `idUtilisateur`=" + user.getIdUtilisateur() + "");

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

	@Override
	public Utilisateur authenticate(String email, String password) {
		// TODO Auto-generated method stub
		Utilisateur user = null;
		try {
			System.out.println("Connecting to database...");
			System.out.println("Fetch email : " + email + " & password : " + password);
			statement = conn.prepareStatement("SELECT * FROM utilisateur WHERE identifiant='" + email + "' AND motPasse='" + password + "' ");
			
			result = statement.executeQuery();
			
			System.out.println(result.toString());
			while(result.next()) {
				System.out.println("in loop");
				String name = result.getString("nom");
				String firstName = result.getString("prenom");
				String gender = result.getString("civilite");
				String mail = result.getString("identifiant");
				String passwordUser = result.getString("motPasse");
				Date birthDate = result.getDate("dateNaissance");
				Date createDate = result.getDate("dateCreation");
				Date updateDate = result.getDate("dateModification");
				Boolean activityState = result.getBoolean("actif");
				Boolean markAsErased = result.getBoolean("marquerEffacer");
				int idUser = result.getInt("idUtilisateur");
				int version = result.getInt("version");
				
				int idRole = result.getInt("idRole");
//				String identifiantRole = result.getString("title");
//				String description = result.getString("descr");
				String identifiantRole = "test";
				String description = "test";
				int versionRole = result.getInt("version");
				Role role = new Role(idRole, identifiantRole, description, versionRole);
				
				 user = new Utilisateur(idUser, gender, firstName, name, mail, passwordUser, birthDate,
						createDate, updateDate, activityState, markAsErased, version, role);
				 
				 System.out.println("1identifiant: "+ user.getIdentifiant()+" civilite: "+ user.getCivilite());
			}
			
			System.out.println("2identifiant: "+ user.getIdentifiant()+" civilite: "+ user.getCivilite());
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private ArrayList<Utilisateur> getUsers(ResultSet result) {
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

                    Role role = new Role(result.getInt(RoleUtils.RoleLib.ID.getField()), result.getString(ROLE_IDENTIFIANT),
                            result.getString(RoleUtils.RoleLib.DESCRIPTION.getField()), result.getInt(RoleUtils.RoleLib.VERSION.getField()));


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
	
	 private List<Utilisateur> getUsersByRoleCriteria(Object criteria) {
	        String id = "roleID";
	        String vers = "roleVers";
	        String desc = "roleDescrpt";
	        String ident = "roleIdent";
	        List<Utilisateur> users = new ArrayList<>();
	        if (criteria instanceof String) {
	            try {
	                statement = conn.prepareStatement(UserUtils.getGetUserByIdentifiantRoleJoinQuery + Constants.qote + criteria + Constants.qote);
	                result = statement.executeQuery();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	        } else if (criteria instanceof Integer) {
	            try {
	                statement = conn.prepareStatement(UserUtils.getGetUserByIdRoleJoinQuery + criteria);
	                result = statement.executeQuery();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        try {
	            if (result != null) {
	                while (result != null && result.next()) {
	                    Utilisateur user = new Utilisateur(result.getInt(RoleUtils.RoleLib.ID.getField()), result.getString(UserUtils.UserLib.CIVILITE.getField()),
	                            result.getString(UserUtils.UserLib.PRENOM.getField()), result.getString(UserUtils.UserLib.NOM.getField()), result.getString(UserUtils.UserLib.IDENTIFIANT.getField()),
	                            result.getString(UserUtils.UserLib.MOT_PASSE.getField()), result.getDate(UserUtils.UserLib.DATE_NAISSANCE.getField()),
	                            result.getDate(UserUtils.UserLib.DATE_CREATION.getField()), result.getDate(UserUtils.UserLib.DATE_MODIFICATION.getField()),
	                            result.getBoolean(UserUtils.UserLib.IS_ACTIF.getField()), result.getBoolean(UserUtils.UserLib.IS_DELETED.getField()), result.getInt(UserUtils.UserLib.VERSION.getField()),
	                            new Role(result.getInt(id), result.getString(ident),
	                                    result.getString(desc), result.getInt(vers)));

	                    users.add(user);

	                }
	            }

	        } catch (Throwable e) {
	            e.printStackTrace();

	        } finally {
	            ConnectionHelper.closeSqlResources(statement, result);
	        }
	        return users;
	    }


}
