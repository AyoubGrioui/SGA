package com.sga.services;

import com.sga.entities.LigneFonction;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LigneFonctionForm {

	private static final String CHAMP_DATE_DEBUT = "dateDebutLigneFonction";
	private static final String CHAMP_DATE_FIN = "dateFinLigneFonction";
	
	private Map<String,String> erreurs = new HashMap<String,String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public LigneFonction creerLigneFonction(HttpServletRequest request) {
		
		
		String dateDebut = getValeurChamp(request, CHAMP_DATE_DEBUT);
		String dateFin = getValeurChamp(request, CHAMP_DATE_FIN);

		LigneFonction ligneFonction = new LigneFonction();
		
		LocalDate dateDebutTemp = null; 
		try {
			dateDebutTemp = validationDate(dateDebut);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateDebut(dateDebutTemp);
		
		LocalDate dateFinTemp = null; 
		try {
			dateFinTemp = validationDate(dateFin);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateFin(dateFinTemp);
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getLigneFonctionRepository();
		rep.create(ligneFonction);
		
		return ligneFonction;
	}
	
	//validation date
		private LocalDate validationDate( String date ) throws Exception {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	LocalDate temp;
	        
	        if ( date != null ) {
	                temp = LocalDate.parse(date, formatter);      
	        } else {
	            throw new Exception( "Merci d'entrer une date." );
	        }
	        return temp;
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
