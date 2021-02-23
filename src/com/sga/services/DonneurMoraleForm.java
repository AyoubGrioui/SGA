package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.Donneur;
import com.sga.entities.DonneurMoral;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateDonneurMoralPersister;
import com.sga.repositories.HibernateDonneurPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class DonneurMoraleForm {
	
	private static final String CHAMP_NOM = "nomDonneurMorale";
	private static final String CHAMP_EMAIL = "emailDonneurMorale";
	private static final String CHAMP_TELEPHONE = "telephoneDonneurMorale";
	private static final String CHAMP_ADRESSE = "adresseDonneurMorale";
	private static final String CHAMP_MOT_DE_PASSE = "motDePasseDonneurMorale";
	private static final String ATT_STRUCTURE = "structure";
	public static final String INTERNAL_ID_DONATEUR = "idDonneur";


	private Map<String,String> erreurs = new HashMap<String,String>();
	private String resultat;
	private String errorMessage;
	
	
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public String getResultat() {
		return resultat;
	}
	
	

	public DonneurMoral creerDonneurMorale(HttpServletRequest request) {
		
		String nom = getValeurChamp(request, CHAMP_NOM);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		
		DonneurMoral donneurMorale = new DonneurMoral();
		
		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreurs(CHAMP_NOM, e.getMessage());
		}
		donneurMorale.setNom(nom);
		
		donneurMorale.setMotDePasse(nom + "@" + LocalDate.now().getYear());
		
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreurs(CHAMP_EMAIL, e.getMessage());
		}
		donneurMorale.setEmail(email);
		
		try {
			validationTelephone(telephone);
		} catch (Exception e) {
			setErreurs(CHAMP_TELEPHONE, e.getMessage());
		}
		donneurMorale.setTelephone(telephone);
		
		try {
			validationAdresse(adresse);
		} catch (Exception e) {
			setErreurs(CHAMP_ADRESSE, e.getMessage());
		}
		donneurMorale.setAdresse(adresse);
		
		
		
		if(erreurs.isEmpty()) {
			resultat = "succes de la creation de donneur";
		}
		else {
			resultat= "echec de la creation de donneur morale";
		}

		 HttpSession session=request.getSession();
	        Structure structure=(Structure)session.getAttribute(ATT_STRUCTURE);
	        
	        if(structure == null )
	        {
	        	errorMessage = "Merci de Creer une structure pour l'Association";
	        	setErreurs(ATT_STRUCTURE, errorMessage);
	        }
		donneurMorale.setStructure(structure);

		
		if(getErreurs().isEmpty())
		{
			HibernateDonneurMoralPersister donneurMoralPersister = new HibernateDonneurMoralPersister();
			long id = donneurMoralPersister.create(donneurMorale);
			donneurMorale.setIdDonneur(id);
		}
		return donneurMorale;
	}	
	
public DonneurMoral modifierDonneurMorale(HttpServletRequest request) {
		
		String nom = getValeurChamp(request, CHAMP_NOM);
		String email = getValeurChamp(request,CHAMP_EMAIL);
		String telephone = getValeurChamp(request,CHAMP_TELEPHONE);
		String adresse = getValeurChamp(request,CHAMP_ADRESSE);
		String motDePasse = getValeurChamp(request,CHAMP_MOT_DE_PASSE);

        Long id = Long.parseLong(getValeurChamp(request,INTERNAL_ID_DONATEUR));
        HibernateDonneurMoralPersister donneurMoralePers = new HibernateDonneurMoralPersister();
        DonneurMoral donneurMorale = donneurMoralePers.read(id);
        
		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreurs(CHAMP_NOM, e.getMessage());
		}
		donneurMorale.setNom(nom);
		
		try {
		 if(email ==null || !email.equals(donneurMorale.getEmail()))
			validationEmail(email);
		} catch (Exception e) {
			setErreurs(CHAMP_EMAIL, e.getMessage());
		}
		donneurMorale.setEmail(email);
		
		try {
			validationTelephone(telephone);
		} catch (Exception e) {
			setErreurs(CHAMP_TELEPHONE, e.getMessage());
		}
		donneurMorale.setTelephone(telephone);
		
		try {
			validationAdresse(adresse);
		} catch (Exception e) {
			setErreurs(CHAMP_ADRESSE, e.getMessage());
		}
		donneurMorale.setAdresse(adresse);
		
		try
		{
			validationMotDePasse(motDePasse);
		}
		catch(Exception e)
		{
			setErreurs(CHAMP_MOT_DE_PASSE,e.getMessage());
		}
		donneurMorale.setMotDePasse(motDePasse);
		
		
		if(getErreurs().isEmpty())
		{
			HibernateDonneurMoralPersister donneurMoralPersister = new HibernateDonneurMoralPersister();
			donneurMoralPersister.update(donneurMorale);
		}
		return donneurMorale;
	}
	
/*Foction de validation d'un adresse email*/
	
	
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
	    	if(!isEmailUnique(email) )
		      throw new Exception( "l'email que vous avez entrer déja lier a un Donnateur ." );
	    	
	    }
	}
	
	private boolean isEmailUnique(String mail)
	{
		HibernateDonneurPersister donneurPersister=new HibernateDonneurPersister();
		Donneur donneur = donneurPersister.getByEmail(mail);
		
		if(donneur== null)
			return true;
		
		return false;
	}
	
/*Fonction de validation de numero de telephone */
	
	private void validationTelephone(String telephone) throws Exception{
		if(telephone != null) {
			if(!telephone.matches("^\\d{10}$") ) {
				throw new Exception("Le numero de telephone doit contenir des 10 chiffres.");
			}
		}
		else {
			throw new Exception ("Merci d'entrer un numero de telephone.");
		}
	}
	
/*Fonction de validation de l'adresse */
	
	private void validationAdresse(String adresse) throws Exception{
		if( adresse != null) {
			if(adresse.length()<6) {
				throw new Exception("l'adresse du client doit contenir au moins 6 caracteres");
			}
		}
		else {
			throw new Exception("Merci d'entrer un adresse du client");
		}
	}

	
	// Fonction de validation du nom
    private void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom  doit contenir au moins 2 caract�res." );
            }
        } else {
            throw new Exception( "Merci d'entrer le nom." );
        }
    }
    
  //Foction de validation du mot de passe
  	private void validationMotDePasse( String motDePasse ) throws Exception {
  	    if ( motDePasse == null  ) {
  	        throw new Exception( "Merci de saisir un mot de passe." );
  	    }
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



