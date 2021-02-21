package com.sga.services;

import com.sga.entities.Fonction;
import com.sga.entities.LigneFonction;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.HibernateFonctionPersister;
import com.sga.repositories.HibernateLigneFonctionPersister;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.hibernate.Hibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LigneFonctionForm {

	private static final String CHAMP_DATE_DEBUT = "dateDebutLigneFonction";
	private static final String CHAMP_DATE_FIN = "dateFinLigneFonction";
	private static final String CHAMP_ID_LIGNE_FONCTION = "idLigneFonction";


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
			setErreurs(CHAMP_DATE_DEBUT, e.getMessage());
		}
		ligneFonction.setDateDebut(SGAUtil.StringToLocalDate(dateDebut));
		
		try {
			validationDate(dateFin);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateFin(SGAUtil.StringToLocalDate(dateFin));

		FonctionForm fonctionForm=new FonctionForm();
		Fonction fonction=fonctionForm.creerFonction(request);
		ligneFonction.setFonction(fonction);
		erreurs.putAll(fonctionForm.getErreurs());
		
		if(getErreurs().isEmpty())
		{
			HibernateLigneFonctionPersister ligneFonctionPersister=new HibernateLigneFonctionPersister();
			ligneFonctionPersister.create(ligneFonction);
		}
		
		return ligneFonction;
	}
	
public LigneFonction modifierLigneFonction(HttpServletRequest request) {
		
		
		String dateDebut = getValeurChamp(request, CHAMP_DATE_DEBUT);
		String dateFin = getValeurChamp(request, CHAMP_DATE_FIN);
		long id = Long.parseLong(getValeurChamp(request, CHAMP_ID_LIGNE_FONCTION));


		LigneFonction ligneFonction = new LigneFonction();
		ligneFonction.setIdLigneFonction(id);

		try {
			validationDate(dateDebut);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_DEBUT, e.getMessage());
		}
		ligneFonction.setDateDebut(SGAUtil.StringToLocalDate(dateDebut));
		
		try {
			validationDate(dateFin);
		}catch (Exception e) {
			setErreurs(CHAMP_DATE_FIN, e.getMessage());
		}
		ligneFonction.setDateFin(SGAUtil.StringToLocalDate(dateFin));

		FonctionForm fonctionForm=new FonctionForm();
		Fonction fonction=fonctionForm.creerFonction(request);
		
		ligneFonction.setFonction(fonction);
		erreurs.putAll(fonctionForm.getErreurs());
		
		if(getErreurs().isEmpty())
		{
			HibernateLigneFonctionPersister ligneFonctionPersister=new HibernateLigneFonctionPersister();
			ligneFonctionPersister.update(ligneFonction);
		}
		
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
