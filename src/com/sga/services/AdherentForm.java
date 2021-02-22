package com.sga.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.sga.entities.Adherent;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateStructurePersister;

import eu.medsea.mimeutil.MimeUtil;


public class AdherentForm {
		
	public static final String CHAMP_NOM="nomAdherent"; 
	public static final String CHAMP_PRENOM="prenomAdherent";
	public static final String CHAMP_CIN="cinAdherent";
	public static final String CHAMP_DATE_NAISSANCE="dateNaissanceAdherent";
	public static final String CHAMP_LIEU_NAISSANCE="lieuNaissanceAdherent"; 
	public static final String CHAMP_DATE_ADHESION="dateadhesionAdherent";
	public static final String CHAMP_PROFESSION="professionAdherent";
	public static final String CHAMP_SEXE="sexeAdherent";
	public static final String CHAMP_MOT_DE_PASSE="motDePasseAdherent";
	public static final String CHAMP_TELEPHONE="telephoneAdherent";
	public static final String CHAMP_ADRESSE="adresseAdherent";
	public static final String CHAMP_EMAIL="emailAdherent";
	public static final String ATT_STRUCTURE = "structure";
	public static final String INTERNAL_ID_ADHERENT = "idAdherent";
	private static final String ALGO_CHIFFREMENT = "SHA-256";

    private static final int    TAILLE_TAMPON   = 10240;                        // 10ko

	
	private Map<String,String> erreurs=new HashMap<String,String>();
	private String errorMessage=null;
		
	public String getErrorMessage() {
		return errorMessage;
	}

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
		String sexe = getValeurChamp(request,CHAMP_SEXE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		
				
		
		Adherent adherent = new Adherent();

		String password = null;
		
		

        try {
        	validationDate( dateNaissance );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_NAISSANCE, e.getMessage() );
        }
        adherent.setDateNaissance(SGAUtil.StringToLocalDate(dateNaissance) );
		
        try {
            validationDate( dateAdhesion );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_ADHESION, e.getMessage() );
        }
        adherent.setDateAdhesion( SGAUtil.StringToLocalDate(dateAdhesion)  );
        
        try {
            validationNom(nom);
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM, e.getMessage() );
        }
        adherent.setNom( nom );
		
        try {
            validationPrenom(prenom);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PRENOM, e.getMessage() );
        }
        adherent.setPrenom( prenom );
		
        try {
            validationCin(cin);
        } catch ( Exception e ) {
            setErreurs( CHAMP_CIN, e.getMessage() );
        }
        adherent.setCin( cin );
		
        try {
            validationLieuNaissance(lieuNaissance);
        } catch ( Exception e ) {
            setErreurs( CHAMP_LIEU_NAISSANCE, e.getMessage() );
        }
        adherent.setLieuNaissance( lieuNaissance );
		
        try {
            validationProfession(profession);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PROFESSION, e.getMessage() );
        }
        adherent.setProfession( profession );
		
        adherent.setSexe( sexe );
        
        try {
            validationAdresse(adresse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_ADRESSE, e.getMessage() );
        }
        adherent.setAdresse( adresse );
		
        try {
            validationTelephone(telephone);
        } catch ( Exception e ) {
            setErreurs( CHAMP_TELEPHONE, e.getMessage() );
        }
        adherent.setTelephone( telephone );
		
        try {
            validationEmail(email);
        } catch ( Exception e ) {
            setErreurs( CHAMP_EMAIL, e.getMessage() );
        }
        adherent.setEmail( email );
		
        try {
            validationMotDePasse(motDePasse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
        adherent.setMotDePasse( motDePasse );

        HttpSession session=request.getSession();
        Structure structure=(Structure)session.getAttribute(ATT_STRUCTURE);
        
        if(structure == null )
        {
        	errorMessage = "Merci de Creer une structure pour l'Association";
        	setErreurs(ATT_STRUCTURE, errorMessage);
        }
                
        adherent.setStructure(structure);

        

		LigneFonctionForm ligneFonctionForm = new LigneFonctionForm();
		LigneFonction ligneFonction = ligneFonctionForm.creerLigneFonction(request);

		adherent.setLigneFonction(ligneFonction);

		erreurs.putAll(ligneFonctionForm.getErreurs());

		
		//Persistance des Donnnée
		if(getErreurs().isEmpty())
		{
			HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();
			adherentPersister.create(adherent);
		}		
				
		return adherent;
	}
	
	public Adherent modifierAdherent(HttpServletRequest request) {
		String nom = getValeurChamp(request,CHAMP_NOM);
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String cin = getValeurChamp(request,CHAMP_CIN);
		String dateNaissance = getValeurChamp(request,CHAMP_DATE_NAISSANCE);
		String lieuNaissance = getValeurChamp(request,CHAMP_LIEU_NAISSANCE);
		String dateAdhesion = getValeurChamp(request,CHAMP_DATE_ADHESION);
		String profession = getValeurChamp(request,CHAMP_PROFESSION);
		String sexe = getValeurChamp(request,CHAMP_SEXE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);
		System.out.println(motDePasse);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String idStructure = getValeurChamp(request,ATT_STRUCTURE);
		
		Adherent adherent = new Adherent();
		
		adherent.setIdAdherent(Long.parseLong(getValeurChamp(request, INTERNAL_ID_ADHERENT)));
		
		String password = null;
		
		

        try {
        	validationDate( dateNaissance );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_NAISSANCE, e.getMessage() );
        }
        adherent.setDateNaissance(SGAUtil.StringToLocalDate(dateNaissance) );
		
        try {
            validationDate( dateAdhesion );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_ADHESION, e.getMessage() );
        }
        adherent.setDateAdhesion( SGAUtil.StringToLocalDate(dateAdhesion)  );
        
        try {
            validationNom(nom);
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM, e.getMessage() );
        }
        adherent.setNom( nom );
		
        try {
            validationPrenom(prenom);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PRENOM, e.getMessage() );
        }
        adherent.setPrenom( prenom );
		
        try {
            validationCin(cin);
        } catch ( Exception e ) {
            setErreurs( CHAMP_CIN, e.getMessage() );
        }
        adherent.setCin( cin );
		
        try {
            validationLieuNaissance(lieuNaissance);
        } catch ( Exception e ) {
            setErreurs( CHAMP_LIEU_NAISSANCE, e.getMessage() );
        }
        adherent.setLieuNaissance( lieuNaissance );
		
        try {
            validationProfession(profession);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PROFESSION, e.getMessage() );
        }
        adherent.setProfession( profession );
		      
        adherent.setSexe( sexe );
        
        try {
            validationAdresse(adresse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_ADRESSE, e.getMessage() );
        }
        adherent.setAdresse( adresse );
		
        try {
            validationTelephone(telephone);
        } catch ( Exception e ) {
            setErreurs( CHAMP_TELEPHONE, e.getMessage() );
        }
        adherent.setTelephone( telephone );
		
        try {
            validationEmail(email);
        } catch ( Exception e ) {
            setErreurs( CHAMP_EMAIL, e.getMessage() );
        }
        adherent.setEmail( email );
		
        try {
            validationMotDePasse(motDePasse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
        adherent.setMotDePasse( motDePasse );

        HttpSession session=request.getSession();
        Structure structure=(Structure)session.getAttribute(ATT_STRUCTURE);
        
        if(structure == null )
        {
        	errorMessage = "Merci de Creer une structure pour l'Association";
        	setErreurs(ATT_STRUCTURE, errorMessage);
        }
                
        adherent.setStructure(structure);
        

		LigneFonctionForm ligneFonctionForm = new LigneFonctionForm();
		LigneFonction ligneFonction = ligneFonctionForm.modifierLigneFonction(request);

		adherent.setLigneFonction(ligneFonction);

		erreurs.putAll(ligneFonctionForm.getErreurs());

		
		//Persistance des Donnnée
		
		for(String er : getErreurs().values())
		{
			System.out.println(er);
		}


		if(getErreurs().isEmpty())
		{
			HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();
			adherentPersister.update(adherent);
		}
				
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
	
	//Fonction de validation du nom
	private void validationNom( String nom ) throws Exception {
	    if ( nom != null ) {
	        if ( nom.length() < 2 ) {
	            throw new Exception( "Le nom  doit contenir au moins 2 caractéres." );
	        }
	    } else {
	        throw new Exception( "Merci d'entrer le nom de l'adherent" );
	    }
	}
	// Fonction de validation du prenom
	private void validationPrenom( String prenom ) throws Exception {
	    if ( prenom != null ) {
	        if ( prenom.length() < 2 ) {
	            throw new Exception( "Le prenom doit contenir au moins 2 caractéres." );
	        }
	    } else {
	        throw new Exception( "Merci d'entrer le prenom de l'adherent" );
	    }
	}
	//Fonction de validation du cin
	private void validationCin( String cin ) throws Exception {
	    if ( cin != null ) {
	        if ( cin.length() < 5 || cin.length()>8 ) {
	            throw new Exception( "ce CIN n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "Merci d'entrer le CIN de l'adherent." );
	    }
	}
	
	//validation date
	private void validationDate( String date ) throws Exception {

        if ( date != null ) {
                if(LocalDate.now().isBefore(SGAUtil.StringToLocalDate(date)))
				{
					throw new Exception("Merci d'entrer une date valide");
				}
        } else {
            throw new Exception( "Merci d'entrer une date." );
        }
    }
	
	//Fonction de validation du lieu de naissance
	private void validationLieuNaissance( String lieuNaissance ) throws Exception {
	    if ( lieuNaissance == null ) {
	        throw new Exception( "Merci d'entrer le lieu de naissance de l'adherent." );
	    }
	}
	//Fonction de validation du profession
	private void validationProfession( String profession ) throws Exception {
	    if ( profession == null ) {
	        throw new Exception( "Merci d'entrer la profession." );
	    }
	}
	//Fonction de validation du photo
	private void validationPhoto( String photo ) throws Exception {
	    if ( photo == null ) {
	        throw new Exception( "Merci d'entrer une photo." );
	    }
	}
	
	
	 /*Fonction de validation de l'adresse */

	private void validationAdresse(String adresse) throws Exception{
		if( adresse != null) {
			if(adresse.length()<6) {
				throw new Exception("l'adresse de l'adherent doit contenir au moins 6 caracteres");
			}
		}
		else {
			throw new Exception("Merci d'entrer un adresse de l'adherent");
		}
	}

	/*Fonction de validation de numero de telephone */

	private void validationTelephone(String telephone) throws Exception{
		if(telephone != null) {
			if(!telephone.matches("^\\d{10}$") ) {
				throw new Exception("Le numero de telephone doit contenir des 10 chiffres .");
			}
		}
		else {
			throw new Exception ("Merci d'entrer un numero de telephone.");
		}
	}
	
	//Foction de validation d'un adresse email

	private void validationEmail( String email ) throws Exception {
	    if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	        throw new Exception( "Merci de saisir une adresse mail valide." );
	    }
	    else if( email == null )
	    {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	    else
	    {
	    	if(!isEmailUnique(email))
		      throw new Exception( "l'email que vous avez entrer ." );

	    }
	}
	
	private boolean isEmailUnique(String mail)
	{
		HibernateAdherentPersister adherentPersister=new HibernateAdherentPersister();
		Adherent adherent = adherentPersister.getByEmail(mail);
		
		if(adherent== null)
			return true;
		
		return false;
	}

	//Foction de validation du mot de passe
	private void validationMotDePasse( String motDePasse ) throws Exception {
	    if ( motDePasse == null  ) {
	        throw new Exception( "Merci de saisir un mot de passe." );
	    }
	    else if(motDePasse.length()<8)
	    {
			throw new Exception( "Le Mot depasse doit contenir au minimum 8 caractéres" );
		}
	}

	private String traiterMotsDePasse( String motDePasse ) throws Exception {

		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
		passwordEncryptor.setPlainDigest( false );
		String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

		return motDePasseChiffre;

	}

	
}