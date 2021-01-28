package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.DonneurMoral;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

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


public class AdherentForm {
		
	public static final String CHAMP_NOM="nomAdherent"; 
	public static final String CHAMP_PRENOM="prenomAdherent";
	public static final String CHAMP_CIN="cinAdherent";
	public static final String CHAMP_DATE_NAISSANCE="dateNaissanceAdherent";
	public static final String CHAMP_LIEU_NAISSANCE="lieuNaissanceAdherent"; 
	public static final String CHAMP_DATE_ADHESION="dateadhesionAdherent";
	public static final String CHAMP_PROFESSION="professionAdherent";
	public static final String CHAMP_PHOTO="photoAdherent";
	public static final String CHAMP_SEXE="sexeAdherent";
	public static final String CHAMP_MOT_DE_PASSE="motDePasseAdherent";
	public static final String CHAMP_TELEPHONE="telephoneAdherent";
	public static final String CHAMP_ADRESSE="adresseAdherent";
	public static final String CHAMP_EMAIL="emailAdherent";
	
	LocalDate temp;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Adherent creerAdherent(HttpServletRequest request) {
		String nom = getValeurChamp(request,CHAMP_NOM);
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String cin = getValeurChamp(request,CHAMP_CIN);
		String dateNaissance = getValeurChamp(request,CHAMP_DATE_NAISSANCE);
		String lieuNaissance = getValeurChamp(request,CHAMP_LIEU_NAISSANCE);
		String dateAdhesion = getValeurChamp(request,CHAMP_DATE_ADHESION);
		String profession = getValeurChamp(request,CHAMP_PROFESSION);
		String photo = getValeurChamp(request,CHAMP_PHOTO);
		String sexe = getValeurChamp(request,CHAMP_SEXE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		
		Adherent adherent = null;
		
		String message=getValidationMessage(adherent,CHAMP_NOM);
		if(! (message == null)) {
			setErreurs(CHAMP_NOM, message);
		}else {
			adherent.setNom(nom);
		}
		
		message=getValidationMessage(adherent,CHAMP_PRENOM);
		if(! (message == null)) {
			setErreurs(CHAMP_PRENOM, message);
		}else {
			adherent.setPrenom(prenom);
		}
		
		message=getValidationMessage(adherent,CHAMP_CIN);
		if(! (message == null)) {
			setErreurs(CHAMP_CIN, message);
		}else {
			adherent.setCin(cin);
		}
		
		message=getValidationMessage(adherent,CHAMP_DATE_NAISSANCE);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_NAISSANCE, message);
		}else {
			temp = LocalDate.parse(dateNaissance, formatter);
			adherent.setDateNaissance(temp);
		}
		
		message=getValidationMessage(adherent,CHAMP_LIEU_NAISSANCE);
		if(! (message == null)) {
			setErreurs(CHAMP_LIEU_NAISSANCE, message);
		}else {
			adherent.setLieuNaissance(lieuNaissance);
		}
		
		message=getValidationMessage(adherent,CHAMP_DATE_ADHESION);
		if(! (message == null)) {
			setErreurs(CHAMP_DATE_ADHESION, message);
		}else {
			temp = LocalDate.parse(dateAdhesion, formatter);
			adherent.setDateAdhesion(temp);
		}
		
		message=getValidationMessage(adherent,CHAMP_PROFESSION);
		if(! (message == null)) {
			setErreurs(CHAMP_PROFESSION, message);
		}else {
			adherent.setProfession(profession);
		}
		
		message=getValidationMessage(adherent,CHAMP_PHOTO);
		if(! (message == null)) {
			setErreurs(CHAMP_PHOTO, message);
		}else {
			adherent.setPhoto(photo);
		}
		
		message=getValidationMessage(adherent,CHAMP_SEXE);
		if(! (message == null)) {
			setErreurs(CHAMP_SEXE, message);
		}else {
			adherent.setSexe(sexe);
		}
		
		message=getValidationMessage(adherent,CHAMP_MOT_DE_PASSE);
		if(! (message == null)) {
			setErreurs(CHAMP_MOT_DE_PASSE, message);
		}else {
			adherent.setSexe(motDePasse);
		}
		
		message=getValidationMessage(adherent,CHAMP_TELEPHONE);
		if(! (message == null)) {
			setErreurs(CHAMP_TELEPHONE, message);
		}else {
			adherent.setTelephone(telephone);
		}
		
		message=getValidationMessage(adherent,CHAMP_ADRESSE);
		if(! (message == null)) {
			setErreurs(CHAMP_ADRESSE, message);
		}else {
			adherent.setAdresse(adresse);
		}
		
		message=getValidationMessage(adherent,CHAMP_EMAIL);
		if(! (message == null)) {
			setErreurs(CHAMP_EMAIL, message);
		}else {
			adherent.setEmail(email);
		}
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonneurMoralRepository();
		rep.create(adherent);
		
		return adherent;
	}
	
	// ajoute un message correspondant au champ specifie a la map des erreurs 

	private void setErreurs(String champ, String message) {
		erreurs.put(champ, message);
	}

	//methode utilitaire qui retourne null si un champ est vide, et son contenu sinon 

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

	private String getValidationMessage(Adherent obj, String champ) {
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
