package com.sga.services;

import com.sga.entities.Fonction;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FonctionForm {
	
	private static final String CHAMP_ROLE = "roleFonction";
	
	private Map<String,String> erreurs = new HashMap<String,String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Fonction creerFonction(HttpServletRequest request) {
		
		String role = getValeurChamp(request, CHAMP_ROLE);
		
		Fonction fonction = new Fonction();
		
		try {
			validationRole(role);
		} catch(Exception e) {
			setErreurs(CHAMP_ROLE, e.getMessage());
		}
		fonction.setRole(role);
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getFonctionRepository();
		rep.create(fonction);
		
		return fonction;
	}
	
	/*Fonction de validation du role dans un fonction */
	
	private void validationRole(String role) throws Exception
	{
		ArrayList<String> roles = new ArrayList<String>();
		//inserer
	if (role != null) {
			if(role.length()< 2) {
				throw new Exception("le role doit contenir au mois 2 caracteres");
			}
		}
		else {
			throw new Exception("Merci d'entrer un role");
		}
	}
	
	/* ajoute un message correspondant au champ specifie a la map des erreurs */
	
	private void setErreurs(String champ, String message) {
		erreurs.put(champ, message);
	}
	
	/* methode utilitaire qui retourne null si un champ est vide, et son contenu sinon */
	
	private static String getValeurChamp(HttpServletRequest request,String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length()==0) {
			return null;
		}
		else {
			return valeur;
		}
	}
}
