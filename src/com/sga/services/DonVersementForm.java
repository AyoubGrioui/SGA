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
	private String resultat;
	
	
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
	public DonVersement creerDonVersement(HttpServletRequest request) {

		String numCompte = getValeurChamp(request,CHAMP_NUMERO_DE_COMPTE);
		DonVersement donVersement = new DonVersement();

		try {
			validationNumCompte(numCompte);
		}
		catch (Exception e) {
			setErreurs(CHAMP_NUMERO_DE_COMPTE, e.getMessage());
		}
		donVersement.setNumeroCompteBanque(numCompte);

		if(erreurs.isEmpty()) {
			resultat = "succes de la creation du depense";
		}
		else {
			resultat = "echec de la creation du depense";
		}

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonVersementRepository();
		rep.create(donVersement);
		
		return donVersement;
	}

	/*Fonction de validation d'un numero de compte */

	private void validationNumCompte(String numCompte) throws Exception{
		if(numCompte != null) {
			if(!numCompte.matches("^\\d+$") ) {
				throw new Exception("Le numero de compte doit uniquement contenir des chiffres.");
			}
			else if(numCompte.length() != 16) {
				throw new Exception("Le numero de compte doit contenir 16 chiffres.");
			}
		}
		else {
			throw new Exception ("Merci d'entrer un numero de compte.");
		}
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
