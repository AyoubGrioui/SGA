package com.sga.services;

import com.sga.entities.Don;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DonForm {
	
	
	public static final String CHAMP_DATE_DON="dateDon";
	public static final String CHAMP_MONTANT="montantDon";
	
	private String resultat;
	private Map<String,String> erreurs=new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public Don creerDon(HttpServletRequest request) {
		Don don = null;
		
		return don;
	}
}
