package com.sga.services;

import com.sga.entities.Fonction;
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
import java.util.HashMap;
import java.util.Map;

public class FonctionForm {
	
	private static final String CHAMP_ROLE = "roleFonction";
	
	private Map<String,String> erreurs = new HashMap<String,String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Fonction creerFonction(HttpServletRequest request) {
		
		Fonction fonction = new Fonction();
		String role = getValeurChamp(request,CHAMP_ROLE);
		String message=getValidationMessage(fonction,CHAMP_ROLE);

		message=getValidationMessage(fonction,CHAMP_ROLE);
		if(! (message == null)) {
			setErreurs(CHAMP_ROLE, message);
		}else {
			fonction.setRole(role);
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getFonctionRepository();
		rep.create(fonction);
		
		return fonction;
	}

	// get validation message

	private String getValidationMessage(Fonction obj, String champ) {
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
