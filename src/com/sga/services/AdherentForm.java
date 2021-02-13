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
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.sga.entities.Adherent;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;
import com.sga.helpers.FormValidationException;
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
	public static final String CHAMP_PHOTO="photoAdherent";
	public static final String CHAMP_SEXE="sexeAdherent";
	public static final String CHAMP_MOT_DE_PASSE="motDePasseAdherent";
	public static final String CHAMP_TELEPHONE="telephoneAdherent";
	public static final String CHAMP_ADRESSE="adresseAdherent";
	public static final String CHAMP_EMAIL="emailAdherent";
	public static final String CHAMP_STRUCTURE = "listStructure";
	private static final String ALGO_CHIFFREMENT = "SHA-256";

    private static final int    TAILLE_TAMPON   = 10240;                        // 10ko

	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Adherent creerAdherent(HttpServletRequest request, String chemin) {
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
		String idStructure = getValeurChamp(request,CHAMP_STRUCTURE);
		String photo = null;

		
		
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

        
        try {
        		photo = validationImage(request,chemin);
        } catch(FormValidationException e) {
        	setErreurs(CHAMP_PHOTO, e.getMessage());
        }
        adherent.setPhoto(photo);


		LigneFonctionForm ligneFonctionForm = new LigneFonctionForm();
		LigneFonction ligneFonction = ligneFonctionForm.creerLigneFonction(request);

		adherent.setLigneFonction(ligneFonction);

		erreurs.putAll(ligneFonctionForm.getErreurs());

		
		//Persistance des DonnnÃ©e

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

		
		//Persistance des DonnnÃ©e

		if(getErreurs().isEmpty())
		{
			HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();
			adherentPersister.update(adherent);
		}
		
		return adherent;
	}
	
	// fonction pour l'upload d'un image
	private String uploadFile(HttpServletRequest request ) {
		
		String message = null;
		try {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> uploadFiles = sf.parseRequest(request);
		
		for(FileItem item : uploadFiles) {
			item.write(new File("C:\\Users\\Mido\\IdeaProjects\\JeeProject\\uploadFiles" + item.getName()));
		}
		} catch(Exception e) {
			message = e.getMessage();
		}
		return message;
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
	            throw new Exception( "Le nom  doit contenir au moins 2 caractÃ©res." );
	        }
	    } else {
	        throw new Exception( "Merci d'entrer le nom de l'adherent" );
	    }
	}
	// Fonction de validation du prenom
	private void validationPrenom( String prenom ) throws Exception {
	    if ( prenom != null ) {
	        if ( prenom.length() < 2 ) {
	            throw new Exception( "Le prenom doit contenir au moins 2 caractÃ©res." );
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
			throw new Exception( "Le Mot depasse doit contenir au minimum 8 caractÃ©res" );
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
//-----------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	private String validationImage( HttpServletRequest request, String chemin ) throws FormValidationException {
        /*
         * Récupération du contenu du champ image du formulaire. Il faut ici
         * utiliser la méthode getPart().
         */
        String nomFichier = null;
        InputStream contenuFichier = null;
        try {
            Part part = request.getPart( CHAMP_PHOTO );
            nomFichier = getNomFichier( part );

            /*
             * Si la méthode getNomFichier() a renvoyé quelque chose, il s'agit
             * donc d'un champ de type fichier (input type="file").
             */
            if ( nomFichier != null && !nomFichier.isEmpty() ) {
                /*
                 * Antibug pour Internet Explorer, qui transmet pour une raison
                 * mystique le chemin du fichier local à la machine du client...
                 * 
                 * Ex : C:/dossier/sous-dossier/fichier.ext
                 * 
                 * On doit donc faire en sorte de ne sélectionner que le nom et
                 * l'extension du fichier, et de se débarrasser du superflu.
                 */
                nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
                        .substring( nomFichier.lastIndexOf( '\\' ) + 1 );

                /* Récupération du contenu du fichier */
                contenuFichier = part.getInputStream();

                /* Extraction du type MIME du fichier depuis l'InputStream */
                MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
                Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );

                /*
                 * Si le fichier est bien une image, alors son en-tête MIME
                 * commence par la chaîne "image"
                 */
                if ( mimeTypes.toString().startsWith( "image" ) ) {
                    /* Ecriture du fichier sur le disque */
                    ecrireFichier( contenuFichier, nomFichier, chemin );
                } else {
                    throw new FormValidationException( "Le fichier envoyé doit être une image." );
                }
            }
        } catch ( IllegalStateException e ) {
            /*
             * Exception retournée si la taille des données dépasse les limites
             * définies dans la section <multipart-config> de la déclaration de
             * notre servlet d'upload dans le fichier web.xml
             */
            e.printStackTrace();
            throw new FormValidationException( "Le fichier envoyé ne doit pas dépasser 1Mo." );
        } catch ( IOException e ) {
            /*
             * Exception retournée si une erreur au niveau des répertoires de
             * stockage survient (répertoire inexistant, droits d'accès
             * insuffisants, etc.)
             */
            e.printStackTrace();
            throw new FormValidationException( "Erreur de configuration du serveur." );
        } catch ( ServletException e ) {
            /*
             * Exception retournée si la requête n'est pas de type
             * multipart/form-data.
             */
            e.printStackTrace();
            throw new FormValidationException(
                    "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
        }

        return nomFichier;
    }

	/*
     * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
     * "content-disposition", et de vérifier si le paramètre "filename" y est
     * présent. Si oui, alors le champ traité est de type File et la méthode
     * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
     * la méthode retourne null.
     */
    private static String getNomFichier( Part part ) {
        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                /*
                 * Si "filename" est présent, alors renvoi de sa valeur,
                 * c'est-à-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin )
            throws FormValidationException {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                    TAILLE_TAMPON );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } catch ( Exception e ) {
            throw new FormValidationException( "Erreur lors de l'écriture du fichier sur le disque." );
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
    
    //----------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    

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
