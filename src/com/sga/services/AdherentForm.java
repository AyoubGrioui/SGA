package com.sga.services;

import com.sga.entities.Adherent;

import javax.servlet.http.HttpServletRequest;
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
	
	//date ++++++++++++++++++++++++++++++++++++++
	
	private String resultat;
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	
	//+++++++++++++++++code
	public Adherent creerAdherent(HttpServletRequest request) {
		Adherent adherent = null;
		
		
		return adherent;
	}
	




	private void validationTelephone( String telephone ) throws Exception {
        if ( telephone != null ) {
            if ( !telephone.matches( "^\\d+$" ) ) {
                throw new Exception( "Le num�ro de t�l�phone doit uniquement contenir des chiffres." );
            } else if ( telephone.length() != 10 ) {
                throw new Exception( "Le num�ro de t�l�phone doit contenir 10 chiffres." );
            }
        } else {
            throw new Exception( "Merci d'entrer un num�ro de t�l�phone." );
        }
    }
}
