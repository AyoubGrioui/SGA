package com.sga.services;

import com.sga.entities.Depense;
import com.sga.entities.DonVersement;
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

public class DonVersementForm {

	private static final String CHAMP_NUMERO_DE_COMPTE = "numeroCompteBanqueDonVersement";
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonVersement creerDonVersement(HttpServletRequest request) {

		String numCompte = getValeurChamp(request,CHAMP_NUMERO_DE_COMPTE);
		
		DonVersement donVersement = new DonVersement();

		String message=getValidationMessage(donVersement,CHAMP_NUMERO_DE_COMPTE);
		if(! (message == null)) {
			setErreurs(CHAMP_NUMERO_DE_COMPTE, message);
		}else {
			donVersement.setNom(numCompte);
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonVersementRepository();
		rep.create(donVersement);
		
		return donVersement;
	}

	// get validation message

	private String getValidationMessage(DonVersement obj, String champ) {
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
