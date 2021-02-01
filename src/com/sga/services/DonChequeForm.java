package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.DonCheque;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DonChequeForm {
	public static final String CHAMP_NUMERO_COMPTE_BANQUE="numeroCompteBanqueDonCheque";
	public static final String CHAMP_DATE_CHEQUE="dateChequeDonCheque";
	public static final String CHAMP_DATE_DEPOT="dateDepotDonCheque";
	public static final String CHAMP_NOM_BANQUE="nomBanqueDonCheque";
	
	LocalDate temp;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonCheque creerDonCheque(HttpServletRequest request) {
		String numeroCompteBanque = getValeurChamp(request,CHAMP_NUMERO_COMPTE_BANQUE);
		String dateCheque = getValeurChamp(request,CHAMP_DATE_CHEQUE);
		String dateDepot = getValeurChamp(request,CHAMP_DATE_DEPOT);
		String nomBanque = getValeurChamp(request,CHAMP_NOM_BANQUE);

		DonCheque donCheque = null;
		
		String message=getValidationMessage(donCheque,CHAMP_NUMERO_COMPTE_BANQUE);
		if(! (message == null)) {
			setErreurs(CHAMP_NUMERO_COMPTE_BANQUE, message);
		}else {
			donCheque.setNumeroCompteBanque(numeroCompteBanque);
		}
		
		message=getValidationMessage(donCheque,CHAMP_DATE_CHEQUE);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_CHEQUE, message);
		}else {
			temp = LocalDate.parse(dateCheque, formatter);
			donCheque.setDateCheque(temp);
		}
		
		message=getValidationMessage(donCheque,CHAMP_DATE_DEPOT);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_DEPOT, message);
		}else {
			temp = LocalDate.parse(dateDepot, formatter);
			donCheque.setDateDepot(temp);
		}
		
		 message=getValidationMessage(donCheque,CHAMP_NOM_BANQUE);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM_BANQUE, message);
		}else {
			donCheque.setNomBanque(nomBanque);
		}
		
		return donCheque;
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
	// get validation message

	private String getValidationMessage(DonCheque obj, String champ) {
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
}
