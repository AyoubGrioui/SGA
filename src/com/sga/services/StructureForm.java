package com.sga.services;

import com.sga.entities.Structure;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;


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
		
		Structure structure = new Structure();
		
        LocalDate date = null;
        try {
        	date = validationDate( dateCreation );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_CREATION, e.getMessage() );
        }
        structure.setDateCreation( date );
        
        try {
            validationNom(nom);
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM, e.getMessage() );
        }
        structure.setNom( nom );
		
        try {
            validationEmail(email);
        } catch ( Exception e ) {
            setErreurs( CHAMP_EMAIL, e.getMessage() );
        }
        structure.setEmail( email );
        
        try {
            validationAdresse(adresse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_ADRESSE, e.getMessage() );
        }
        structure.setAdresse( adresse );
        
        try {
            validationUrl(siteWeb);
        } catch ( Exception e ) {
            setErreurs( CHAMP_SITE_WEB, e.getMessage() );
        }
        structure.setSiteWeb( siteWeb );
        
        try {
        	validationObjectif(objectif);
        } catch ( Exception e ) {
            setErreurs( CHAMP_OBJECTIF, e.getMessage() );
        }
        structure.setObjectif( objectif );
		
		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getLigneFonctionRepository();
		rep.create(structure);
		
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
	
	//validation date
	
	private LocalDate validationDate( String date ) throws Exception {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate temp;
        
        if ( date != null ) {
                temp = LocalDate.parse(date, formatter);      
        } else {
            throw new Exception( "Merci d'entrer une date." );
        }
        return temp;
    }
	//Fonction de validation du nom
	
	private void validationNom( String nom ) throws Exception {
	    if ( nom != null ) {
	        if ( nom.length() < 2 ) {
	            throw new Exception( "Le nom  doit contenir au moins 2 caract�res." );
	        }
	    } else {
	        throw new Exception( "Merci d'entrer le nom de la structure." );
	    }
	}
	//Foction de validation d'un adresse email

	private void validationEmail( String email ) throws Exception {
	    if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	        throw new Exception( "Merci de saisir une adresse mail valide." );
	    }
	}
	
	//fonction de validation de la fonction
	
	private void validationObjectif( String fonction ) throws Exception {
	    if ( fonction == null ) {
	        throw new Exception( "Merci de saisir une fonction." );
	    }
	}
	//fonction de la validation du URL
	
	private void validationUrl(String url) throws Exception {
		if(url == null)
		{
			throw new Exception("Merci de donner un url valide.");
	}
}
	//fonction de la validation de l'adresse
	private void validationAdresse( String adresse ) throws Exception {
	    if ( adresse == null ) {
	        throw new Exception( "Merci de saisir une adresse." );
	    }
	}
}