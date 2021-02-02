package com.sga.services;

import com.sga.entities.LigneFonction;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LigneFonctionForm {

	private static final String CHAMP_DATE_DEBUT = "dateDebutLigneFonction";
	private static final String CHAMP_DATE_FIN = "dateFinLigneFonction";
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	private String resultat;
	
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	public LigneFonction creerLigneFonction(HttpServletRequest request) {
		
		
		String dateDebut = getValeurChamp(request, CHAMP_DATE_DEBUT);
		String dateFin = getValeurChamp(request, CHAMP_DATE_FIN);

		LigneFonction ligneFonction = new LigneFonction();
		

		try {
			validationDate(dateDebut);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateDebut(SGAUtil.StringToLocalDate(dateDebut));
		
		try {
			validationDate(dateFin);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateFin(SGAUtil.StringToLocalDate(dateFin));
		
		
		if(erreurs.isEmpty()) {
			resultat = "succes de la creation du client";
		}
		else {
			resultat= "echec de la creation du client";
		}
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getLigneFonctionRepository();
		rep.create(ligneFonction);
		
		return ligneFonction;
	}
	
	//validation date
		private void validationDate( String date ) throws Exception {

	        if(date == null)
			{
	            throw new Exception( "Merci d'entrer une date." );
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
