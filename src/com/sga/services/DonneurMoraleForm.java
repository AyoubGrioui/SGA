package com.sga.services;

import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

public class DonneurMoraleForm {
	
	private static final String CHAMP_NOM = "nomDonneurMorale";
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public DonneurMoral creerDonneurMorale(HttpServletRequest request) {

		String nom = getValeurChamp(request,CHAMP_NOM);
		
		DonneurMoral donneurMoral = new DonneurMoral();

		String message=getValidationMessage(donneurMoral,CHAMP_NOM);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM, message);
		}else {
			donneurMoral.setNom(nom);
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonneurMoralRepository();
		rep.create(donneurMoral);
		
		return donneurMoral;
	}

	// get validation message

	private String getValidationMessage(DonneurMoral obj, String champ) {
		Validator validator = Validation.byDefaultProvider()
				.configure()
				.messageInterpolator(
						(MessageInterpolator) new ResourceBundleMessageInterpolator(
								new PlatformResourceBundleLocator( "MyMessages" )
						)
				)
				.buildValidatorFactory()
				.getValidator();


		String	message = validator.validateProperty( obj,champ).iterator().next().getMessage();


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