package com.sga.services;

import com.sga.entities.Donneur;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DonneurForm {
	
	public static final String CHAMP_EMAIL ="emailDonneur";
	public static final String CHAMP_TELEPHONE="telephoneDonneur";
	public static final String CHAMP_ADRESSE ="adresseDonneur";
	public static final String CHAMP_MOT_DE_PASSE ="motDePasseDonneur";
	
	private String resultat;
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public Donneur creerDonneur(HttpServletRequest request) {
		Donneur donneur = null;
		
		return donneur;
	}

	private void validationTelelhpne( String telephone ) throws Exception {
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
