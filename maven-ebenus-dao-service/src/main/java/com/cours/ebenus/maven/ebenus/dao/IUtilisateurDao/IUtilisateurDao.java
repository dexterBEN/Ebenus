package com.cours.ebenus.maven.ebenus.dao.IUtilisateurDao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur;

public interface IUtilisateurDao {
	public List<Utilisateur> findAllUtilisateurs();

	public Utilisateur findUtilisateurById(int idUtilisateur);

	public List<Utilisateur> findUtilisateursByPrenom(String prenom);

	public List<Utilisateur> findUtilisateursByNom(String nom);

	public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant);

	public List<Utilisateur> findUtilisateursByIdRole(int idRole);

	public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole);

	public Utilisateur createUtilisateur(Utilisateur user);

	public Utilisateur updateUtilisateur(Utilisateur user);

	public boolean deleteUtilisateur(Utilisateur user);

	public Utilisateur authenticate(String email, String password);
}
