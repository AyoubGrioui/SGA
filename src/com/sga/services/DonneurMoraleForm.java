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
	private static final String CHAMP_EMAIL = "emailDonneurMorale";
	private static final String CHAMP_TELEPHONE = "telephoneDonneurMorale";
	private static final String CHAMP_ADRESSE = "adresseDonneurMorale";
	private static final String CHAMP_MOT_DE_PASSE = "motDePasseDonneurMorale";

	private Map<String,String> erreurs = new HashMap<String,String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public DonneurMoral creerDonneurMorale(HttpServletRequest request) {

		String nom = getValeurChamp(request,CHAMP_NOM);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);

		DonneurMoral donneurMoral = new DonneurMoral();

		String message=getValidationMessage(donneurMoral,CHAMP_NOM);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM, message);
		}else {
			donneurMoral.setNom(nom);
		}

		message=getValidationMessage(donneurMoral,CHAMP_EMAIL);
		if(! (message == null)) {
			setErreurs(CHAMP_EMAIL, message);
		}else {
			donneurMoral.setEmail(email);
		}

		message=getValidationMessage(donneurMoral,CHAMP_TELEPHONE);
		if(! (message == null)) {
			setErreurs(CHAMP_TELEPHONE, message);
		}else {
			donneurMoral.setTelephone(telephone);
		}

		message=getValidationMessage(donneurMoral,CHAMP_ADRESSE);
		if(! (message == null)) {
			setErreurs(CHAMP_ADRESSE, message);
		}else {
			donneurMoral.setAdresse(adresse);
		}

		message=getValidationMessage(donneurMoral,CHAMP_MOT_DE_PASSE);
		if(! (message == null)) {
			setErreurs(CHAMP_MOT_DE_PASSE, message);
		}else {
			donneurMoral.setMotDePasse(motDePasse);
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