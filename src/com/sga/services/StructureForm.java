package com.sga.services;

import com.sga.entities.Structure;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author grioui
 *
 */
public class StructureForm {
	
	public static final String CHAMP_NOM="nomStructure"; 
	public static final String CHAMP_DATE_CREATION="dateCreationStructure";
	public static final String CHAMP_EMAIL="emailStructure";
	public static final String CHAMP_ADRESSE="adresseStructure";
	public static final String CHAMP_SITE_WEB="siteWeb";
	public static final String CHAMP_OBJECTIF="objectif";
	public static final String CHAMP_ID_STRUCTURE="idStructure";
	
	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() 
	{
		return erreurs;
	}

	private  String resultat ;
	
	
	

	public String getResultat() {
		return resultat;
	}

	
	public Structure creerStructure(HttpServletRequest request) 
	{
		String nom = getValeurChamp(request,CHAMP_NOM);
		String dateCreation = getValeurChamp(request,CHAMP_DATE_CREATION);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String siteWeb = getValeurChamp(request,CHAMP_SITE_WEB);
		String objectif = getValeurChamp(request,CHAMP_OBJECTIF);		
		
		Structure structure = new Structure();
		
		
        try {
        	validationDate( dateCreation );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_CREATION, e.getMessage() );
        }
        structure.setDateCreation(SGAUtil.StringToLocalDate(dateCreation));
        
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

        if(getErreurs().isEmpty())
        {	
        	HibernateStructurePersister structurePers = new HibernateStructurePersister();
        	structure.setIdStructure(structurePers.create(structure));
        }
        
        if(getErreurs().isEmpty())
        {
        	resultat ="Succès de la création de la Structure. ";
        }
        else
        {
        	resultat ="Echec de la création de la Structure.";
        }
        
		return structure;
	}
	
	public Structure modifierStructure(HttpServletRequest request) 
	{
		String nom = getValeurChamp(request,CHAMP_NOM);
		String dateCreation = getValeurChamp(request,CHAMP_DATE_CREATION);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String siteWeb = getValeurChamp(request,CHAMP_SITE_WEB);
		String objectif = getValeurChamp(request,CHAMP_OBJECTIF);
		String id = getValeurChamp(request,CHAMP_ID_STRUCTURE);
		
		HibernateStructurePersister structurePers =new HibernateStructurePersister();
		Structure structure= structurePers.getAll().get(0);
		
        try {
        	validationDate( dateCreation );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_CREATION, e.getMessage() );
        }
        structure.setDateCreation(SGAUtil.StringToLocalDate(dateCreation));
        
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

        if(getErreurs().isEmpty())
        {	
        	structurePers.update(structure);
        }
        
        if(getErreurs().isEmpty())
        {
        	resultat ="Succès de la modification de la Structure. ";
        }
        else
        {
        	resultat ="Echec de la modification de la Structure.";
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
	
	//validation date
	private void validationDate( String date ) throws Exception {

		if(date == null)
		{
			throw new Exception( "Merci d'entrer une date." );
		}
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
	    else if(email ==  null)
	    {
	        throw new Exception( "Merci de saisir une adresse mail." );
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

	private Structure validationStructure()
	{
		HibernateStructurePersister structurePersister=new HibernateStructurePersister();
		List<Structure> structureList=structurePersister.getAll();

		if(!structureList.isEmpty())
		{
			return structureList.get(0);
		}
		
		return null;
	}
}