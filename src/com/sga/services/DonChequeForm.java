package com.sga.services;

import com.sga.entities.Adherent;
import com.sga.entities.DonCheque;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

import com.sga.helpers.SGAUtil;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DonChequeForm {
	private static final String CHAMP_DATE_DON="dateDon";
	private static final String CHAMP_MONTANT="montant";
	private static final String CHAMP_DONNEUR="donneur";
	public static final String CHAMP_NUMERO_COMPTE_BANQUE="numeroCompteBanqueDonCheque";
	public static final String CHAMP_DATE_CHEQUE="dateChequeDonCheque";
	public static final String CHAMP_DATE_DEPOT="dateDepotDonCheque";
	public static final String CHAMP_NOM_BANQUE="nomBanqueDonCheque";
	
	
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public DonCheque creerDonCheque(HttpServletRequest request) {
		String numeroCompteBanque = getValeurChamp(request,CHAMP_NUMERO_COMPTE_BANQUE);
		String dateCheque = getValeurChamp(request,CHAMP_DATE_CHEQUE);
		String dateDepot = getValeurChamp(request,CHAMP_DATE_DEPOT);
		String nomBanque = getValeurChamp(request,CHAMP_NOM_BANQUE);
		String montant = getValeurChamp(request,CHAMP_MONTANT);
		String dateDon =getValeurChamp(request,CHAMP_DATE_DON);

		DonCheque donCheque = new DonCheque();


		double valeurMontant=-1;
		try {
			valeurMontant=validationMontant(montant);
		}catch(Exception e) {
			setErreurs(CHAMP_MONTANT, e.getMessage());
		}
		donCheque.setMontant(valeurMontant);

        try {
        	validationDate( dateCheque );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_CHEQUE, e.getMessage() );
        }
        donCheque.setDateCheque(SGAUtil.StringToLocalDate(dateCheque));
		
        try {
        	validationDate( dateDepot );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_DEPOT, e.getMessage() );
        }
        donCheque.setDateDepot( SGAUtil.StringToLocalDate(dateDepot) );
		
        try {
        	validationDate( dateDon );
        } catch ( Exception e ) {
            setErreurs( CHAMP_DATE_DON, e.getMessage() );
        }
        donCheque.setDateDon(SGAUtil.StringToLocalDate(dateDon));
        
        try {
            validationNomBanque(nomBanque);
        } catch ( Exception e ) {
            setErreurs( CHAMP_NOM_BANQUE, e.getMessage() );
        }
        donCheque.setNomBanque( nomBanque );
        
        try {
        	validationNumBanque(numeroCompteBanque);
        }catch(Exception e) {
        	setErreurs(CHAMP_NUMERO_COMPTE_BANQUE,e.getMessage());
        }
        donCheque.setNumeroCompteBanque(numeroCompteBanque);
		

		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonVersementRepository();
		rep.create(donCheque);

		return donCheque;
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
	
	//validation date
	private void validationDate( String date ) throws Exception {
        
        if ( date == null )
        {
            throw new Exception( "Merci d'entrer une date." );
        }
    }
	
	//validation numero de banque
	private void validationNumBanque( String num ) throws Exception {
	    if ( num != null )
		{
			if (!num.matches("^\\d{10,}$"))
			{
				throw new Exception( "Merci d'entrer un numero de compte bacaire valide " );
			}
		}
	    else
	    {
	            throw new Exception( "Merci d'entrer le numero de compte du donneur " );
		}
	}
	
	//validation nom du banque
	private void validationNomBanque( String nom ) throws Exception {
	    if ( nom == null ) {	   
	        throw new Exception( "Merci d'entrer le nom de la banque" );
	    }
	}

}
