package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.Structure;

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


public class StructureForm {
	
	public static final String CHAMP_NOM="nomStructure"; 
	public static final String CHAMP_DATE_CREATION="dateCreationStructure";
	public static final String CHAMP_EMAIL="emailStructure";
	public static final String CHAMP_ADRESSE="adresseStructure";
	public static final String CHAMP_SITE_WEB="siteWeb";
	public static final String CHAMP_OBJECTIF="objectif";
	
	LocalDate temp;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public Structure creerStructure(HttpServletRequest request) {
		String nom = getValeurChamp(request,CHAMP_NOM);
		String dateCreation = getValeurChamp(request,CHAMP_DATE_CREATION);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String siteWeb = getValeurChamp(request,CHAMP_SITE_WEB);
		String objectif = getValeurChamp(request,CHAMP_OBJECTIF);
		
		Structure structure = null;
		
		String message=getValidationMessage(structure,CHAMP_NOM);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM, message);
		}else {
			structure.setNom(nom);
		}
		
		message=getValidationMessage(structure,CHAMP_DATE_CREATION);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_CREATION, message);
		}else {
			temp = LocalDate.parse(dateCreation, formatter);
			structure.setDateCreation(temp);
		}
	
		message=getValidationMessage(structure,CHAMP_EMAIL);
		if(! (message == null)) {
			setErreurs(CHAMP_EMAIL, message);
		}else {
			structure.setEmail(email);
		}
		
		message=getValidationMessage(structure,CHAMP_ADRESSE);
		if(! (message == null)) {
			setErreurs(CHAMP_ADRESSE, message);
		}else {
			structure.setAdresse(adresse);
		}
		
		message=getValidationMessage(structure,CHAMP_SITE_WEB);
		if(! (message == null)) {
			setErreurs(CHAMP_SITE_WEB, message);
		}else {
			structure.setSiteWeb(siteWeb);
		}
		
		message=getValidationMessage(structure,CHAMP_SITE_WEB);
		if(! (message == null)) {
			setErreurs(CHAMP_SITE_WEB, message);
		}else {
			structure.setSiteWeb(siteWeb);
		}
		
		message=getValidationMessage(structure,CHAMP_OBJECTIF);
		if(! (message == null)) {
			setErreurs(CHAMP_OBJECTIF, message);
		}else {
			structure.setObjectif(objectif);
		}
		
		return structure;
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

	private String getValidationMessage(Structure obj, String champ) {
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
