package com.sga.services;

import com.sga.entities.Depense;
import com.sga.entities.LigneFonction;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.servlet.http.HttpServletRequest;
import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LigneFonctionForm {

	private static final String CHAMP_DATE_DEBUT = "dateDebutLigneFonction";
	private static final String CHAMP_DATE_FIN = "dateFinLigneFonction";

	LocalDate temp;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


	private Map<String,String> erreurs = new HashMap<String,String>();
	private String resultat;

	
	
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public String getResultat() {
		return resultat;
	}


	public LigneFonction creerLigneFonction(HttpServletRequest request) {

		LigneFonction ligneFonction = new LigneFonction();

		String dateDebut = getValeurChamp(request,CHAMP_DATE_DEBUT);
		String dateFin = getValeurChamp(request,CHAMP_DATE_FIN);


		String message=getValidationMessage(ligneFonction,CHAMP_DATE_DEBUT);

		message=getValidationMessage(ligneFonction,CHAMP_DATE_DEBUT);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_DEBUT, message);
		}else {
			temp = LocalDate.parse(dateDebut, formatter);
			ligneFonction.setDateDebut(temp);
		}

		message=getValidationMessage(ligneFonction,CHAMP_DATE_FIN);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_FIN, message);
		}else {
			temp = LocalDate.parse(dateFin, formatter);
			ligneFonction.setDateFin(temp);
		}

		if(erreurs.isEmpty()) {
			resultat = "succes de la creation du depense";
		}
		else {
			resultat= "echec de la creation du depense";
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getLigneFonctionRepository();
		rep.create(ligneFonction);

		return ligneFonction;
	}

	// get validation message

	private String getValidationMessage(LigneFonction obj, String champ) {
		Validator validator = (Validator) Validation.byDefaultProvider()
				.configure()
				.messageInterpolator(
						(MessageInterpolator) new ResourceBundleMessageInterpolator(
								new PlatformResourceBundleLocator( "MyMessages" )
						)
				)
				.buildValidatorFactory()
				.getValidator();


		String	message = ((jakarta.validation.Validator) validator).validateProperty( obj,champ).iterator().next().getMessage();


		return message;

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