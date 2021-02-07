package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
	public static final String CHAMP_STRUCTURE = "listStructure";
	private static final String ALGO_CHIFFREMENT = "SHA-256";

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
		String idStructure = getValeurChamp(request,CHAMP_STRUCTURE);
		
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
		
        try {
            validationPhoto(photo);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PHOTO, e.getMessage() );
        }
        adherent.setPhoto( photo );
		
        try {
            validationSexe(sexe);
        } catch ( Exception e ) {
            setErreurs( CHAMP_SEXE, e.getMessage() );
        }
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
            password = validationMotDePasse(motDePasse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
        adherent.setMotDePasse( password );

        Structure structure=null;
        try
		{
			structure =validationStructure(idStructure);
		}
		catch ( Exception e )
		{
			setErreurs( CHAMP_STRUCTURE,e.getMessage());
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
		String photo = getValeurChamp(request,CHAMP_PHOTO);
		String sexe = getValeurChamp(request,CHAMP_SEXE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String idStructure = getValeurChamp(request,CHAMP_STRUCTURE);
		
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
		
        try {
            validationPhoto(photo);
        } catch ( Exception e ) {
            setErreurs( CHAMP_PHOTO, e.getMessage() );
        }
        adherent.setPhoto( photo );
		
        try {
            validationSexe(sexe);
        } catch ( Exception e ) {
            setErreurs( CHAMP_SEXE, e.getMessage() );
        }
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
            password = validationMotDePasse(motDePasse);
        } catch ( Exception e ) {
            setErreurs( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
        adherent.setMotDePasse( password );

        Structure structure=null;
        try
		{
			structure =validationStructure(idStructure);
		}
		catch ( Exception e )
		{
			setErreurs( CHAMP_STRUCTURE,e.getMessage());
		}
        adherent.setStructure(structure);


		LigneFonctionForm ligneFonctionForm = new LigneFonctionForm();
		LigneFonction ligneFonction = ligneFonctionForm.modifierLigneFonction(request);

		adherent.setLigneFonction(ligneFonction);

		erreurs.putAll(ligneFonctionForm.getErreurs());

		
		//Persistance des Donnnée

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
	
	//Fonction de validation du de sexe
	private void validationSexe(String sexe) throws Exception {
		if (sexe == null) {
			if(! sexe.equalsIgnoreCase("male") || !sexe.equalsIgnoreCase("femelle") || !sexe.equalsIgnoreCase("autre")) {
				throw new Exception("le sexe doit etre soit 'male' , 'femelle' ou 'autre'.");
			}
		}
		else {
			throw new Exception("Merci d'entrer le sexe");
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
	}

	//Foction de validation du mot de passe
	private String validationMotDePasse( String motDePasse ) throws Exception {
	    if ( motDePasse == null  ) {
	        throw new Exception( "Merci de saisir un mot de passe." );
	    }
	    else if(motDePasse.length()<8)
	    {
			throw new Exception( "Le Mot depasse doit contenir au minimum 8 caractéres" );
		}
	    else
		{
			return traiterMotsDePasse(motDePasse);
		}
	}

	private String traiterMotsDePasse( String motDePasse ) throws Exception {

		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
		passwordEncryptor.setPlainDigest( false );
		String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

		return motDePasseChiffre;

	}

	private Structure validationStructure(String id_structure) throws Exception {
		if(id_structure == null)
		{
			throw new Exception( "Merci de choisir une structure");
		}
		else
		{
			try {
				Long idStructure = Long.parseLong(id_structure);
				HibernateStructurePersister structurePersister = new HibernateStructurePersister();
				Structure structure =structurePersister.read(idStructure);

				if(structure == null)
				{
					throw new Exception("Structure n'existe pas");
				}

				return structure;

			}
			catch (NumberFormatException n)
			{
				throw new Exception( "Structure invalid");
			}

		}
	}

	
}
