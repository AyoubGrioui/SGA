package com.sga.services;

import com.sga.entities.Depense;
import com.sga.entities.DonVersement;
import com.sga.entities.DonneurPhysique;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DonneurPhysiqueForm {

	private static final String CHAMP_NOM = "nomDonneurPhysique";
	private static final String CHAMP_PRENOM = "prenomDonneurPhysique";
	private static final String CHAMP_CIN = "cinDonneurPhysique";
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonneurPhysique creerDonneurPhysique(HttpServletRequest request) {
		
		DonneurPhysique donneurPhysique = new DonneurPhysique();

		String nom = getValeurChamp(request,CHAMP_NOM);
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String cin = getValeurChamp(request,CHAMP_CIN);

		String message=getValidationMessage(donneurPhysique,CHAMP_NOM);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM, message);
		}else {
			donneurPhysique.setNom(nom);
		}

		message=getValidationMessage(donneurPhysique,CHAMP_PRENOM);
		if(! (message == null)) {
			setErreurs(CHAMP_PRENOM, message);
		}else {
			donneurPhysique.setPrenom(prenom);
		}


		message=getValidationMessage(donneurPhysique,CHAMP_CIN);
		if(! (message == null)) {
			setErreurs(CHAMP_CIN, message);
		}else {
			donneurPhysique.setCin(cin);
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonneurPhysiqueRepository();
		rep.create(donneurPhysique);

		return donneurPhysique;
	}

	// get validation message

	private String getValidationMessage(DonneurPhysique obj, String champ) {
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