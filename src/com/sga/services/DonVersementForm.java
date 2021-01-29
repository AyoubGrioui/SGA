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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DonVersementForm {
	private static final String CHAMP_DATE_DON="dateDon";
	private static final String CHAMP_MONTANT="montant";
	private static final String CHAMP_NUMERO_DE_COMPTE = "numeroCompteBanqueDonVersement";

	//A faire aprés
	private static final String CHAMP_DONNEUR="donneur";


	LocalDate temp;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonVersement creerDonVersement(HttpServletRequest request) {

		String numCompte = getValeurChamp(request,CHAMP_NUMERO_DE_COMPTE);
		String dateDon = getValeurChamp(request,CHAMP_DATE_DON);
		String montant = getValeurChamp(request,CHAMP_MONTANT);
		
		DonVersement donVersement = new DonVersement();

		String message=getValidationMessage(donVersement,CHAMP_DATE_DON);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_DON, message);
		}else {
			temp = LocalDate.parse(dateDon, formatter);
			donVersement.setDateDon(temp);
		}

		message=getValidationMessage(donVersement,CHAMP_NUMERO_DE_COMPTE);
		if(! (message == null)) {
			setErreurs(CHAMP_NUMERO_DE_COMPTE, message);
		}else {
			donVersement.setNumeroCompteBanque(numCompte);
		}

		message=getValidationMessage(donVersement,CHAMP_MONTANT);
		double valeurMontant=-1;
		try {
			valeurMontant=validationMontant(montant);
		}catch(Exception e) {
			setErreurs(CHAMP_MONTANT, message);
		}
		donVersement.setMontant(valeurMontant);


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

	//validation du montant
	private double validationMontant( String montant ) throws Exception {
		double temp;
		if ( montant != null ) {
			try {
				temp = Double.parseDouble( montant );
				if ( temp < 0 ) {
					throw new Exception( "Le montant doit etre un nombre positif." );
				}
			} catch ( NumberFormatException e ) {
				temp = -1;
				throw new Exception( "Le montant doit etre un nombre." );
			}
		} else {
			temp = -1;
			throw new Exception( "Merci d'entrer un montant." );
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
